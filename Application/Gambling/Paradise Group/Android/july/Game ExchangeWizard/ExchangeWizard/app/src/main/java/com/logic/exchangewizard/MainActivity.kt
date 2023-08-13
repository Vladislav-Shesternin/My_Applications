package com.logic.exchangewizard

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.logic.exchangewizard.databinding.ActivityMainBinding
import com.logic.exchangewizard.util.Lottie
import com.logic.exchangewizard.util.log
import com.logic.exchangewizard.util.network.internetConnection
import com.logic.exchangewizard.util.webView.WebViewFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    companion object {
        const val WEB_VIEW_ID = 1000

        val startFragmentIdFlow = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
        var webURL              = "https://www.google.com/"
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)

    lateinit var binding      : ActivityMainBinding private set
    lateinit var navController: NavController       private set
    lateinit var lottie       : Lottie              private set

    val webViewFragment = WebViewFragment(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()

        log("isNetwork = ${internetConnection()}")
        if (internetConnection().not()) lottie.showNotInternet()
        else {
            lottie.showLoader()
            coroutine.launch(Dispatchers.Main) {

                startFragmentIdFlow.emit(R.id.gameFragment)

                startFragmentIdFlow.collect { fragmentId ->
                    when (fragmentId) {
                        R.id.gameFragment -> {
                            webViewFragment.goneWebView()
                            setStartDestination(fragmentId)
                            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        }
                        WEB_VIEW_ID -> {
                            webViewFragment.showAndOpenUrl()
                            setNavigationBarColor(R.color.black)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        webViewFragment.onActivityResult(requestCode, resultCode, data)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment)
        lottie = Lottie(binding)

        webViewFragment.onCreate(coroutine, binding.love)

        coroutine.launch {
            while (isActive) {
                delay(2_000)
                if (internetConnection().not()) lottie.showNotInternet() else lottie.hideNotInternet()
            }
        }
    }

    private fun setStartDestination(@IdRes destinationId: Int) {
        navController.run { navInflater.inflate(R.navigation.nav_graph).apply { setStartDestination(destinationId) }.also { setGraph(it, null) } }
    }

    fun setNavigationBarColor(@ColorRes colorId: Int) {
        coroutine.launch(Dispatchers.Main) { window.navigationBarColor = ContextCompat.getColor(this@MainActivity, colorId) }
    }

    fun exit() {
        finishAndRemoveTask()
        //exitProcess(0)
    }

}