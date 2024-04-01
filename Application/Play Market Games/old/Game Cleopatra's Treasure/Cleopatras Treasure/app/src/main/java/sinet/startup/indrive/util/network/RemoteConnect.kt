package sinet.startup.indrive.util.network

import androidx.annotation.Keep
import io.michaelrocks.paranoid.Obfuscate
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import sinet.startup.indrive.BuildConfig
import sinet.startup.indrive.MainActivity
import sinet.startup.indrive.util.CryptUtil
import sinet.startup.indrive.util.log
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Obfuscate
object RemoteConnect {
    private const val REMOTE_CONFIG_URL = "https://gist.githubusercontent.com/SKM853/ff715c91e8cdf7ec67a8769d23b94ff9/raw/sinet.startup.indrive"

    private var remoteConfig: JSONObject? = null

    private val okHttpClient    by lazy {
        OkHttpClient.Builder()
            .retryOnConnectionFailure(false)
            .connectTimeout(12, TimeUnit.SECONDS)
            .writeTimeout(12, TimeUnit.SECONDS)
            .readTimeout(12, TimeUnit.SECONDS)
            .build()
    }
    private val executorService by lazy { Executors.newSingleThreadExecutor() }



    fun setRemoteConfig(): JSONObject {
        remoteConfig = if (remoteConfig == null) try {
            val request = Request.Builder()
                .url(REMOTE_CONFIG_URL)
                .build()
            var data = executorService.submit<String> { okHttpClient.newCall(request).execute().body?.string() ?: "" }[20, TimeUnit.SECONDS]
            if (data.contains('[')) data = data.replace("[", "").replace("]", "")
            data = URLDecoder.decode(data, StandardCharsets.UTF_8.name())
            data = CryptUtil.decrypt(BuildConfig.APPLICATION_ID, data)
            JSONObject(data)
        } catch (e: Exception) {
            log("setRemoteConfig error - $e")
            
            JSONObject()
        }
        else JSONObject()

        return remoteConfig ?: JSONObject()
    }
}

@Obfuscate
@Keep
object RemoteConfig {
    var linkerUrl     : String?  = null
    var offerUrl      : String?  = null
    var isDisplayOffer: Boolean? = null
}