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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import plinko.testyouluck.com.databinding.ActivityMainBinding
import plinko.testyouluck.com.util.DataStoreManager
import plinko.testyouluck.com.util.Lottie
import plinko.testyouluck.com.util.Once
import plinko.testyouluck.com.util.internetConnection
import plinko.testyouluck.com.util.log
import plinko.testyouluck.com.util.setVisible
import plinko.testyouluck.com.webView.WebViewFragment
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        const val WEB_KEY = 333
        var offerUrl   = "https://fex.net/"

        val startFragmentId = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
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

//            checkDataStore()
            startFragmentId.tryEmit(R.id.libGDXFragment)
//            startFragmentID.tryEmit(INTERPOL)

            coroutine.launch(Dispatchers.Main) {
                startFragmentId.collect { fragmentId ->
                    when (fragmentId) {
                        R.id.libGDXFragment -> {
                            webViewFragment.goneWebView()
                            setNavigationBarColor(R.color.black)
                            setStartDestination(fragmentId)
                            //banner.setVisible(View.VISIBLE)
                            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        }
                        WEB_KEY             -> {
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
        webViewFragment.onCreate(coroutine, binding.webview)
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

//    private fun checkDataStore() {
//        coroutine.launch(Dispatchers.IO) {
//            when (DataStoreManager.Key.get()) {
//                "Web" -> {
//                    DataStoreManager.Link.get()?.let {
//                        log("DataStoreManager Key = SUCCESS | link = $it")
//                        offerUrl = it
//                        startFragmentId.emit(WEB_KEY)
//                    }
//                }
//                "Game" -> {
//                    log("DataStoreManager Key = GAME")
//                    startFragmentId.emit(R.id.libGDXFragment)
//                }
//                else -> {
//                    log("DataStoreManager Key = NONE")
//                    startFragmentId.emit(R.id.libGDXFragment)
//                }
//            }
//        }
//    }

//    fun openGame() {
//        coroutine.launch(Dispatchers.IO) {
//            DataStoreManager.Key.update { "Game" }
//            startFragmentId.tryEmit(R.id.libGDXFragment)
//        }
//    }

    fun openWeb(urulu: String) {
        offerUrl = urulu
        startFragmentId.tryEmit(WEB_KEY)

       // coroutine.launch(Dispatchers.IO) {
       //     DataStoreManager.Key.update { "Web" }
       //     DataStoreManager.Link.update { offerUrl }
       // }
    }

}