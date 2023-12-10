package fortune.tiger.avia

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Message
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.PermissionRequest
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class BlankFragment : Fragment() {
    private lateinit var webView: WebView
    private val CAMERA_PERMISSION_CODE = 101
    lateinit var rootView: FrameLayout
    private val exception = CoroutineExceptionHandler { _, throwable ->
      //
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
        webView = WebView(requireContext())
        webView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        rootView= FrameLayout(requireContext())
        rootView.addView(webView)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backPressed()
        webSettins(webView)
        webChrome(webView)
        webClient(webView)
        webView.loadUrl("random_url")
    }



    private fun webClient(webView: WebView) {
        webView.webViewClient = object : WebViewClient() {

            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                return when {
                    request.url.toString().startsWith("mailto:") -> {
                        val intent = Intent(Intent.ACTION_SEND)
                        intent.type = "plain/text"
                        intent.putExtra(
                            Intent.EXTRA_EMAIL,
                            request.url.toString().replace("mailto:", "")
                        )
                        requireContext().startActivity(Intent.createChooser(intent, "Send email"))
                        true
                    }

                    request.url.toString().startsWith("tel:") -> {
                        CoroutineScope(Dispatchers.Main).launch(exception) {
                            val intent = Intent(Intent.ACTION_DIAL)
                            intent.data = request.url
                            requireContext().startActivity(intent)
                        }
                        true
                    }

                    request.url.toString().startsWith("tg:") -> {
                        try {
                            val intent = Intent(Intent.ACTION_VIEW, request.url)
                            requireContext().startActivity(intent)
                        } catch (e: ActivityNotFoundException) {
                           //
                        } catch (e: Exception) {
                            //
                        }

                        true
                    }
                    request.url.toString().startsWith("whatsapp:") -> {
                        try {
                            val intent = Intent(Intent.ACTION_VIEW, request.url)
                            requireContext().startActivity(intent)
                        } catch (e: ActivityNotFoundException) {
                            //
                        } catch (e: Exception) {
                            //
                        }
                        true
                    }

                    else -> !(request.url.toString().startsWith("http://") || request.url.toString()
                        .startsWith("https://"))
                }
            }
            override fun onPageFinished(view: WebView, url: String) {
                CookieManager.getInstance().flush()
            }
        }
    }
    private var currentPhotoUri: Uri? = null
    private var filePath: ValueCallback<Array<Uri>>? = null

    val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                Log.d("myLog Webview", "RESULT_OK $filePath")


                if (result.data?.data == null) {
                    filePath?.onReceiveValue(arrayOf(currentPhotoUri!!))
                } else {
                    filePath?.onReceiveValue(
                        WebChromeClient.FileChooserParams.parseResult(result.resultCode, result.data)
                    )
                }

                filePath = null
            } else if (result.resultCode == Activity.RESULT_CANCELED) {
                Log.d("myLog Webview", "RESULT_CANCELED $filePath")
                filePath?.onReceiveValue(null)
            }
        }

    private var webArray= mutableListOf<WebView>()
    private fun webChrome(webView: WebView) {
        webView.webChromeClient = object : WebChromeClient() {
            override fun onCreateWindow(
                view: WebView?,
                isDialog: Boolean,
                isUserGesture: Boolean,
                resultMsg: Message?
            ): Boolean {

                val newWebView = WebView(requireContext())
                webSettins(newWebView)
                webClient(newWebView)
                webChrome(newWebView)
                newWebView.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webArray.add(newWebView)

                rootView.addView(newWebView)
                val transport = resultMsg?.obj as WebView.WebViewTransport
                transport.webView = newWebView
                resultMsg.sendToTarget()
                return true
            }

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
            }

            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri>>?,
                fileChooserParams: FileChooserParams?
            ): Boolean {
                if (ContextCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.CAMERA
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        arrayOf(Manifest.permission.CAMERA),
                        CAMERA_PERMISSION_CODE
                    )
                    return false
                } else {
                    this@BlankFragment.filePath = filePathCallback

                    val contentIntent = Intent(Intent.ACTION_GET_CONTENT)
                    contentIntent.type = "image/*"
                    contentIntent.addCategory(Intent.CATEGORY_OPENABLE)
                    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    val cameraPhotoUri = createCameraPhotoUri()

                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraPhotoUri)

                    val chooserIntent = Intent.createChooser(contentIntent, "Choose an action")
                    chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(cameraIntent))

                    this@BlankFragment.resultLauncher.launch(chooserIntent)
                    return true
                }

            }
            override fun onPermissionRequest(request: PermissionRequest) {
                if (ContextCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.CAMERA
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        arrayOf(Manifest.permission.CAMERA),
                        CAMERA_PERMISSION_CODE
                    )
                } else {
                    for (resource in request.resources) {
                        if (resource == PermissionRequest.RESOURCE_VIDEO_CAPTURE) {
                            request.grant(request.resources)
                            return
                        }
                    }
                }
                super.onPermissionRequest(request)
            }

        }
    }

    private fun createCameraPhotoUri(): Uri {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "JPEG_${timeStamp}_"
        val storageDir: File? = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        val photoFile: File? = try {
            File.createTempFile(imageFileName, ".jpg", storageDir)
        } catch (ex: IOException) {
            null
        }

        photoFile?.let {
            currentPhotoUri = FileProvider.getUriForFile(
                requireContext(),
                "${requireContext().packageName}.fileprovider",
                it
            )
            return currentPhotoUri!!
        }

        throw RuntimeException("Не удалось создать файл для фотографии")
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun webSettins(webView: WebView) {
        val userAgent=System.getProperty("http.agent")
        webView.settings.javaScriptEnabled = true
        webView.settings.useWideViewPort = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.allowFileAccess = true
        webView.settings.domStorageEnabled = true
        webView.settings.userAgentString = userAgent
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.settings.setSupportMultipleWindows(true)
        webView.settings.displayZoomControls = false
        webView.settings.builtInZoomControls = true
        webView.settings.setSupportZoom(true)
        webView.settings.pluginState = WebSettings.PluginState.ON
        webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        webView.settings.allowContentAccess = true
        webView.settings.cacheMode= WebSettings.LOAD_CACHE_ELSE_NETWORK
        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        cookieManager.setAcceptThirdPartyCookies(webView, true)
    }

    private fun backPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if(webView.canGoBack()){
                        webView.goBack()
                    }
                    else if(webArray.isNotEmpty()){
                        rootView.removeView(webArray[webArray.size-1])
                        webArray[webArray.size-1].destroy()
                        webArray.removeAt(webArray.size - 1)
                    }

                }
            })
    }
}