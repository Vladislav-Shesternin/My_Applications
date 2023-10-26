package aiebu.kakono.tutokazalos.util.web

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import android.webkit.DownloadListener
import androidx.activity.addCallback
import im.delight.android.webview.AdvancedWebView
import aiebu.kakono.tutokazalos.MainActivity
import aiebu.kakono.tutokazalos.util.setVisible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import aiebu.kakono.tutokazalos.R

class AdvancedWebViewFragment(private val activity: MainActivity): AdvancedWebView.Listener {

    var backBlock: () -> Unit = { activity.exit() }
    var isVisible = false

    private var webView  : AdvancedWebView? = null
    private var coroutine: CoroutineScope? = null

    private lateinit var intent: Intent

//    private var isClearHistory = true

    override fun onPageFinished(url: String?) {
        activity.lottie.hideLoader()
//        if (isClearHistory) {
//            isClearHistory = false
//            webView?.clearHistory()
//        }
    }

    override fun onPageStarted(url: String?, favicon: Bitmap?) {
    }

    override fun onPageError(errorCode: Int, description: String?, failingUrl: String?) {
    }

    override fun onDownloadRequested(
        url: String?,
        suggestedFilename: String?,
        mimeType: String?,
        contentLength: Long,
        contentDisposition: String?,
        userAgent: String?
    ) { }

    override fun onExternalPageRequest(url: String?) {
        if (url == null) return

        when {
            url.startsWith("mailto:") -> {
                val intent = Intent(Intent.ACTION_SENDTO, Uri.parse(url))
                intent.putExtra(Intent.EXTRA_SUBJECT, "Email subject")
                intent.putExtra(Intent.EXTRA_TEXT, "Email body")
                activity.startActivity(intent)
            }
            url.startsWith("whatsapp:") -> {
                intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                intent.data = Uri.parse(url)
                activity.startActivity(intent)
            }
            url.startsWith("viber:") -> {
                intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                intent.data = Uri.parse(url)
                activity.startActivity(intent)
            }
            url.startsWith("tel:") -> {
                intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse(url)
                activity.startActivity(intent)
            }
            url.startsWith("https://t.me/joinchat") -> {
                intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                intent.data = Uri.parse(url)
                activity.startActivity(intent)
            }
            url.startsWith("tg:") -> {
                intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                intent.data = Uri.parse(url)
                activity.startActivity(intent)
            }

            Uri.parse(url).host == "localhost" -> {
                MainActivity.startFragmentIdFlow.tryEmit(R.id.libGDXFragment)
            }

            else -> if (url.startsWith("http://") || url.startsWith("https://")) webView?.loadUrl(url)
        }
    }

    fun onCreate(coroutine: CoroutineScope, webView: AdvancedWebView) {
        onBackPressed()

        this.coroutine = coroutine
        this.webView = webView.apply {
            setListener(activity, this@AdvancedWebViewFragment)
            setDownloadListener(getDownloadListener())
            addPermittedHostname("example.org");
        }

    }

    fun showAndOpenUrl() {
//        isClearHistory = true

        isVisible = true
        coroutine?.launch(Dispatchers.Main) {
            webView?.setVisible(View.VISIBLE)
            webView?.loadUrl(MainActivity.webURL)
        }
    }

    fun goneWebView() {
        isVisible = false
        coroutine?.launch(Dispatchers.Main) {
            webView?.setVisible(View.GONE)
        }
    }

    fun onResume() {
        webView?.onResume()
    }

    fun onPause() {
        webView?.onPause()
    }

    fun onDestroy() {
        webView?.onDestroy()
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        webView?.onActivityResult(requestCode, resultCode, data)
    }

    private fun getDownloadListener() = DownloadListener { url, _, _, _, _ ->
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        activity.startActivity(intent)
    }

    private fun onBackPressed() {
        activity.onBackPressedDispatcher.addCallback(activity) {
            if (webView?.canGoBack() == true) webView?.goBack() else backBlock()
        }
    }

}