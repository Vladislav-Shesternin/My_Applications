package uniwersal.pictures.present

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import uniwersal.pictures.present.databinding.ActivityMainBinding
import uniwersal.pictures.present.game.screens.SevakjeScreen
import uniwersal.pictures.present.util.DataStoreManager
import uniwersal.pictures.present.util.Lottie
import uniwersal.pictures.present.util.Once
import uniwersal.pictures.present.util.internetConnection
import uniwersal.pictures.present.util.log
import uniwersal.pictures.present.util.web.AdvancedWebViewFragment
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        const val brehna = 1000

        val poilo = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
        var strekoza              = "https://www.google.com/"
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val onceExit  = Once()

    private lateinit var binding      : ActivityMainBinding
    private lateinit var navController: NavController

    lateinit var lottie: Lottie

    val adronomias = AdvancedWebViewFragment(this)
    val okHttpClient    = OkHttpClient()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
        if (internetConnection().not()) lottie.showNotInternet()
        else {
            asyncCheckInternetConnection()
            lottie.showLoader()

            coroutine.launch(Dispatchers.IO) {

                //            startFragmentIdFlow.tryEmit(R.id.libGDXFragment)
                checkDataStore()

                launch(Dispatchers.Main) {
                    poilo.collect { fragmentId ->
                        when (fragmentId) {
                            R.id.libGDXFragment -> {
                                /*binding.root.also { rootCL ->
                                ConstraintSet().apply {
                                    clone(rootCL)
                                    constrainPercentWidth(binding.loader.id, .23f)
                                }.applyTo(rootCL)
                            }*/

                                adronomias.goneWebView()
                                setNavigationBarColor(R.color.black)
                                setStartDestination(fragmentId)
                                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                            }

                            brehna -> {
                                adronomias.showAndOpenUrl()
                                setNavigationBarColor(R.color.white)
                                ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                            }

                            else                -> ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                        }.also { requestedOrientation = it }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        adronomias.onResume()
    }

    override fun onPause() {
        adronomias.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        adronomias.onDestroy()
        super.onDestroy()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        adronomias.onActivityResult(requestCode, resultCode, data)
    }

    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment)
        lottie        = Lottie(binding)

        adronomias.onCreate(coroutine, binding.webStage)

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

    private fun checkDataStore() {
        coroutine.launch(Dispatchers.IO) {
            when (DataStoreManager.Key.get()) {
                "Potate" -> {
                    DataStoreManager.Link.get()?.let {
                        log("DataStoreManager Key = SUCCESS | link = $it")
                        strekoza = it
                        poilo.emit(brehna)
                    }
                }
                "Hram" -> {
                    log("DataStoreManager Key = GAME")
                    poilo.emit(R.id.libGDXFragment)
                }
                else -> {
                    log("DataStoreManager Key = NONE")
                    SevakjeScreen.Parantago(this@MainActivity).german(okHttpClient, binding)
                }
            }
        }
    }





}