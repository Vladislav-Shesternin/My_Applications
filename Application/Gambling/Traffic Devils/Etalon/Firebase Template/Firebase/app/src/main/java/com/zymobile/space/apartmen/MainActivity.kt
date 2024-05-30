package com.zymobile.space.apartmen

import android.os.Bundle
import android.os.RemoteException
import android.provider.Settings
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.firebase.messaging.FirebaseMessaging
import com.zymobile.space.apartmen.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URLEncoder
import java.util.UUID
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var installReferrerClient: InstallReferrerClient
    private var iR = ""

    val permissionRequestLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
//        TODO("Тут все также")
        if (Settings.Global.getInt(contentResolver, Settings.Global.ADB_ENABLED, 0) == 1) {
            startGame()
        } else FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
//        TODO("А от тут началось =DDD")
//        TODO("Для работы пуш сообщений нам нужен этот токен, но он с экспирацией, по этому нам надо получать его при каждом заходе")
            mainLogic(task.result)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        installReferrerClient = InstallReferrerClient.newBuilder(this).build()
        installReferrerClient.startConnection(installReferrerStateListener)
//        TODO("Вся логика разведена после ответа юзера на POST_NOTIFICATIONS диалог")
        permissionRequestLauncher.launch(arrayOf("android.permission.POST_NOTIFICATIONS"))
    }

    private fun mainLogic(frbToken: String) = CoroutineScope(Dispatchers.Main).launch {
        val urlFromStore = getPreferences(MODE_PRIVATE).getString("url", "") ?: ""
        if (urlFromStore.isNotEmpty()) {
//        TODO("Если есть сохраненный юрл - добавляем вконце токен и открываем в ВВ. Токен енкодим, в нем бывают символы, которые могут поламать юрл")
            showURL("$urlFromStore&=frbToken=${URLEncoder.encode(frbToken, "UTF-8")}")
        } else {
//        TODO("Если нет сохраненного юрл - получаем adId, собираем его с основным ЮРЛ, сохраняем и добавляем вконце токен и открываем в ВВ")
            val advertisingIdInfo = withContext(Dispatchers.IO) { adId() }
            val url = "http://fposttestb.xyz/?adId=$advertisingIdInfo&installReferer=$iR"
            getPreferences(MODE_PRIVATE).edit().putString("url", url).apply()
            showURL("$url&=frbToken=${URLEncoder.encode(frbToken, "UTF-8")}")
        }
    }

    private fun showURL(url: String) {
        Log.d("kek","Fool url $url")
//        TODO("Тут ваша логика инициализации ВВ и открытия полученной ссылки")
    }

    private fun startGame() {
//        TODO("При первом заходе - показываем диалог на разрешение использовать adId и редирект на игру, при последующих - сразу редирект на игру")
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

    suspend fun adId() = suspendCoroutine { continuation ->
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

}