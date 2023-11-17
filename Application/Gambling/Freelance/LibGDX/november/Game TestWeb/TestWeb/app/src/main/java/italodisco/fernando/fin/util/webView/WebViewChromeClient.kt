package italodisco.fernando.fin.util.webView

import android.content.ActivityNotFoundException
import android.net.Uri
import android.webkit.PermissionRequest
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import italodisco.fernando.fin.MainActivity

class WebViewChromeClient(private val activity: MainActivity) : WebChromeClient() {

    companion object {
        const val REQUEST_SELECT_FILE = 1000

        var uploadMessage: ValueCallback<Array<Uri>>? = null
    }



    override fun onShowFileChooser(webView: WebView?, filePathCallback: ValueCallback<Array<Uri>>?, fileChooserParams: FileChooserParams?): Boolean {
        uploadMessage = filePathCallback
        val intent = fileChooserParams!!.createIntent()
        try {
            activity.startActivityForResult(intent, REQUEST_SELECT_FILE)
        } catch (e: ActivityNotFoundException) {
            uploadMessage = null
            return false
        }
        return true
    }

}

