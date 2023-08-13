package com.zeuse.zeusjackpotjamboree

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.zeuse.zeusjackpotjamboree.databinding.ActivityMainBinding
import com.zeuse.zeusjackpotjamboree.util.Lottie
import com.zeuse.zeusjackpotjamboree.util.Once
import com.zeuse.zeusjackpotjamboree.util.internetConnection
import com.zeuse.zeusjackpotjamboree.util.log
import com.zeuse.zeusjackpotjamboree.util.webView.WebViewFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
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

    val webViewFragment = WebViewFragment(this)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()

        log("Network = ${internetConnection()}")
        if (internetConnection().not()) lottie.showNotInternet()
        else {
            lottie.showLoader()
            coroutine.launch(Dispatchers.IO) {

                // ---------------------------------------------------
                // Андрей - startFragmentIdFlow(
                //      libGDXFragment - если нужна игра
                //      WEB_VIEW_ID - если нужно веб и не забудь перед этим webURL - изменить на ооффер линк
                // )
                // ---------------------------------------------------

                try {
                    startFragmentIdFlow.emit(R.id.libGDXFragment)
                } catch (e: Exception) {
                    log("e: ${e.message}")
                    startFragmentIdFlow.emit(WEB_VIEW_ID)
                }

                launch(Dispatchers.Main) {
                    startFragmentIdFlow.collect { fragmentId ->
                        when (fragmentId) {
                            R.id.libGDXFragment -> {
                                binding.root.also { rootCL ->
                                    ConstraintSet().apply {
                                        clone(rootCL)
                                        constrainPercentWidth(binding.loader.id, .2f)
                                    }.applyTo(rootCL)
                                }

                                webViewFragment.goneWebView()
                                setStartDestination(fragmentId)
                                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                            }
                            WEB_VIEW_ID -> {
                                webViewFragment.showAndOpenUrl()
                                setNavigationBarColor(R.color.black)
                                ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                            }
                            else                 -> ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                        }.also { requestedOrientation = it }
                    }
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        webViewFragment.onActivityResult(requestCode, resultCode, data)
    }

    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment)

        lottie = Lottie(binding)
        webViewFragment.onCreate(coroutine, binding.beb)

        coroutine.launch {
            while (isActive) {
                delay(2_000)
                if (internetConnection().not()) lottie.showNotInternet() else lottie.hideNotInternet()
            }
        }
    }

    private fun setStartDestination(
        @IdRes destinationId: Int,
        args: Bundle? = null
    ) { navController.run { navInflater.inflate(R.navigation.nav_graph).apply { setStartDestination(destinationId) }.also { setGraph(it, args) } } }


    // ---------------------------------------------------
    // Public
    // ---------------------------------------------------

    fun setNavigationBarColor(@ColorRes colorId: Int) {
        coroutine.launch(Dispatchers.Main) {
            window.navigationBarColor = ContextCompat.getColor(this@MainActivity, colorId)
        }
    }

}