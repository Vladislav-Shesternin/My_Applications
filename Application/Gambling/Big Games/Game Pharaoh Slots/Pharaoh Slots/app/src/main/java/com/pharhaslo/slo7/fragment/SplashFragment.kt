package com.pharhaslo.slo7.fragment

import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.appsflyer.AppsFlyerLib
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.Gson
import com.onesignal.OneSignal
import com.pharhaslo.slo7.BuildConfig
import com.pharhaslo.slo7.MainActivity
import com.pharhaslo.slo7.MainViewModel
import com.pharhaslo.slo7.R
import com.pharhaslo.slo7.databinding.FragmentSplashBinding
import com.pharhaslo.slo7.model.entity.SMS
import com.pharhaslo.slo7.util.OkHttpCustomClient
import com.pharhaslo.slo7.util.logFragment
import com.pharhaslo.slo7.util.mainScope
import kotlinx.coroutines.*
import okhttp3.Request
import org.json.JSONObject


@ExperimentalCoroutinesApi
class SplashFragment : Fragment() {
    companion object{
        const val START_URL = "https://app-server-integration.site/"
        const val AF_KEY = "iqu4KZGw8eqAEu85xnRnXD"
    }
    private lateinit var binding : FragmentSplashBinding
    private lateinit var sharedPrefs : SharedPreferences
    private lateinit var viewModel : MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedPrefs = requireActivity().getSharedPreferences("save", 0)

        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = (activity as MainActivity).viewModel
        binding.imageViewLoader.setImageResource(R.mipmap.ic_launcher)

        startAnimation()


        AppsFlyerLib.getInstance().enableFacebookDeferredApplinks(false)
        AppsFlyerLib.getInstance().init(AF_KEY, null, requireContext())
        AppsFlyerLib.getInstance().start(requireContext())


        val advertisingIdJob = mainScope.async(Dispatchers.IO){
            withTimeoutOrNull(10000){
                var adid = sharedPrefs.getString("adid", "")
                if (adid?.isEmpty() == true) {
                    adid = AdvertisingIdClient.getAdvertisingIdInfo(requireContext()).id ?: ""
                    sharedPrefs.edit().putString("adid", adid).apply()
                }
                return@withTimeoutOrNull adid ?: ""
            } ?: ""
        }

        mainScope.launch(Dispatchers.Main) {
            awaitAll(advertisingIdJob)
            setOnesignal(advertisingIdJob.getCompleted())
            if (sharedPrefs.getBoolean("phoneChecked", false)) {
                findNavController().navigate(R.id.navigation_main)
            } else {
                checkUser(advertisingIdJob.getCompleted())
            }
        }
    }


    private fun checkUser(adid: String){
        val appsflyerJob = mainScope.async {
            AppsFlyerLib.getInstance().getAppsFlyerUID(requireContext()) ?: ""
        }

        val appId = BuildConfig.APPLICATION_ID
        val getDataJob = mainScope.async(Dispatchers.IO) {
            awaitAll(appsflyerJob)
            val buildUrl = "${START_URL}api/user/check?bundle_id=$appId" +
                    "&version=v1&advertising_id=$adid&appsflyer_device_id=${appsflyerJob.getCompleted()}"

                return@async OkHttpCustomClient.getOkHttpClient().newCall(Request.Builder().url(buildUrl).build()).execute().body?.string() ?: ""
        }
        mainScope.launch(Dispatchers.IO) {
            awaitAll(getDataJob)
            val jsonResponse = JSONObject(getDataJob.getCompleted())

            if(jsonResponse.isNull("user")) {
                goToDemo(false)
                return@launch
            }

            if(!jsonResponse.getBoolean("user")) {
                goToDemo(false)
                sharedPrefs.edit().putBoolean("isPhoneGood", false).apply()
                OneSignal.unsubscribeWhenNotificationsAreDisabled(true)
                return@launch
            }
            sharedPrefs.edit().putString("phone_mask", jsonResponse.getString("phone_mask")).apply()
            sharedPrefs.edit().putString("visitor_id", jsonResponse.getString("visitor_id")).apply()
            viewModel.setPhoneMask()
            viewModel.smsObject.postValue(Gson().fromJson(jsonResponse.toString(), SMS::class.java))
            withContext(Dispatchers.Main) { goToDemo(true) }
        }
    }
    private fun goToDemo(isUserValidated : Boolean = false) = mainScope.launch(Dispatchers.Main) {
        viewModel.isFullGameButtonActivated.postValue(isUserValidated)
        findNavController().navigate(R.id.navigation_game)
    }

    private fun setOnesignal(adid: String){
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.setExternalUserId(adid)
        OneSignal.unsubscribeWhenNotificationsAreDisabled(true)
        OneSignal.initWithContext(requireContext())
    }

    private fun startAnimation() {
        val blinkAnimation = AnimationUtils.loadAnimation(requireActivity().applicationContext,
            R.anim.blink_slow_animation)
        binding.imageViewLoader.startAnimation(blinkAnimation)
    }




    override fun onStart() {
        super.onStart()
        FirebaseAnalytics.getInstance(requireContext()).logFragment(this)
    }
}