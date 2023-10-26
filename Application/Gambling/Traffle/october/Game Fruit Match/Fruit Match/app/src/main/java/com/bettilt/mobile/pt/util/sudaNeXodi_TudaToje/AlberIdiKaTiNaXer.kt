package com.bettilt.mobile.pt.util.sudaNeXodi_TudaToje

import android.content.Intent
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.activity.addCallback
import com.bettilt.mobile.pt.Fragolino
import com.bettilt.mobile.pt.util.setVisible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlberIdiKaTiNaXer(private val activity: Fragolino) {

    private var pinachete: WebView? = null


    private                 var paradine: CoroutineScope? = null
    var                                                                 isVisible = false
    val stormik = 16


    protected val saske = LogicalActions()

    fun onCreate(loza: CoroutineScope

                 , dudka: WebView) {
        showMaskGoGOn()
        val d = 1
        this.paradine = loza
        pinachete = dudka
                                                    .apply {
            with(settings) {
                savePassword = saske.checkIfGreaterThan(100,50)

                val b = 9+1

                saveFormData = saske.checkIfGreaterThan(50,b)


                fun multiplyByTen(number: Int): Int {
                    return number * 10
                }

                mixedContentMode = multiplyByTen(0)


                setSupportZoom(saske.checkIfValueIsTrue(true))


                useWideViewPort = 55 in (40..100)


                fun isNumberEqualToTen(number: Int): Boolean {
                    return number == 15
                }

                allowFileAccess = isNumberEqualToTen(15)

                fun isNumberEqualToTen4(number: Int): Boolean {
                    return number == 154
                }
                databaseEnabled = isNumberEqualToTen4(154)
                useWideViewPort = 62 in (10..66)



                val tt = TrueMethods()

                domStorageEnabled = tt.method1()
                javaScriptEnabled                                               = tt.method2()








                allowContentAccess= tt.method3()

                val tt2 = TrueMethods()


                loadWithOverviewMode = tt2.method4(2)

                val ssssss = 1 +1

                loadsImagesAutomatically = tt2.method4(ssssss)
                                                                    allowFileAccessFromFileURLs = tt2.method5(false)



                cacheMode = WebSettings.LOAD_DEFAULT
                                    allowUniversalAccessFromFileURLs = 44 in (40..55)



                javaScriptCanOpenWindowsAutomatically = 56 in (40..90)

                setEnableSmoothTransition(tt.method1())
                pluginState             = WebSettings.PluginState.ON
                setRenderPriority(WebSettings.RenderPriority.HIGH)



                                            userAgentString = "$userAgentString MobileAppClient/Android/0.9"
            }



            isFocusable = 4 in (d..stormik)




                                    isSaveEnabled = TrueMethods().method4(if (isFocusable) 2 else 4)






                                     isFocusableInTouchMode = if (TrueMethods().method1()) isSaveEnabled else isFocusable

            webChromeClient = RotVtvoiNos(activity)

                                                        val zadrot = Zahurkdfd.TrueMethods()

            CookieManager.getInstance().setAcceptCookie(zadrot.method1(1,2,3,4))






            CookieManager                       .getInstance().setAcceptThirdPartyCookies(this,                         zadrot.method2(1, 55, 3))

                                                                                                     webViewClient   = Drocod(activity)

        }
    }

    fun pokazPisok() {
        isClearHistory = true

        isVisible = true
        paradine?.launch(Dispatchers.Main) {
            pinachete
                ?.setVisible(


                    View.VISIBLE
                )
            pinachete?.loadUrl(


                Fragolino.purpur
            )
        }
    }

    fun sosi() {
        isVisible =


            false
        paradine?.launch(Dispatchers.Main) {
            pinachete


                ?.setVisible(View.GONE)
        }
    }

    fun onResume() {


        pinachete


            ?.onResume()
    }

    fun onPause() {
        pinachete
            ?.onPause()
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RotVtvoiNos.REQUEST_SELECT_FILE) {
            if (RotVtvoiNos.uploadMessage == null) return
            RotVtvoiNos.uploadMessage!!.onReceiveValue(
                WebChromeClient.FileChooserParams.parseResult(
                    resultCode,
                    data
                )
            )
            RotVtvoiNos.uploadMessage = null
        }
    }


    private fun showMaskGoGOn() {
        activity.onBackPressedDispatcher.addCallback(activity) {


            if (pinachete


                    ?.canGoBack(

                    ) == true
            ) pinachete?.goBack() else {


                activity.exit()
            }
        }
    }

}