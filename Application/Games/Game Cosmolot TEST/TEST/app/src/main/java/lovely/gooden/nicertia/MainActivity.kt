package lovely.gooden.nicertia

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.appsflyer.AppsFlyerLib
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lovely.gooden.nicertia.databinding.ActivityMainBinding
import lovely.gooden.nicertia.util.AppsflyerUtil
import lovely.gooden.nicertia.util.DataStoreManager
import lovely.gooden.nicertia.util.Lottie
import lovely.gooden.nicertia.util.Once
import lovely.gooden.nicertia.util.internetConnection
import lovely.gooden.nicertia.util.log
import lovely.gooden.nicertia.util.setVisible
import lovely.gooden.nicertia.util.web.AdvancedWebViewFragment
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        const val WEB_VIEW_ID = 1000

        val startFragmentIdFlow = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
        var webURL              = "https://www.google.com/"
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val onceExit  = Once()

    private lateinit var binding      : ActivityMainBinding
    private lateinit var navController: NavController

    lateinit var lottie: Lottie

    val advancedWebView = AdvancedWebViewFragment(this)
    val okHttpClient    = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
        asyncCheckInternetConnection()
        lottie.showLoader()

        coroutine.launch(Dispatchers.IO) {

            webURL = "https://cosmolot.ua"
            startFragmentIdFlow.tryEmit(WEB_VIEW_ID)
//            startFragmentIdFlow.tryEmit(WEB_VIEW_ID)
//            checkDataStore()

            launch(Dispatchers.Main) {
                startFragmentIdFlow.collect { fragmentId ->
                    when (fragmentId) {
                        R.id.libGDXFragment -> {
                            /*binding.root.also { rootCL ->
                            ConstraintSet().apply {
                                clone(rootCL)
                                constrainPercentWidth(binding.loader.id, .23f)
                            }.applyTo(rootCL)
                        }*/

                            advancedWebView.goneWebView()
                            setNavigationBarColor(R.color.black)
                            setStartDestination(fragmentId)
                            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        }

                        WEB_VIEW_ID         -> {
                            advancedWebView.showAndOpenUrl()
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
        advancedWebView.onResume()
    }

    override fun onPause() {
        advancedWebView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        advancedWebView.onDestroy()
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
        advancedWebView.onActivityResult(requestCode, resultCode, data)
    }

    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment)
        lottie        = Lottie(binding)

        advancedWebView.onCreate(coroutine, binding.advancedWeb)
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

}