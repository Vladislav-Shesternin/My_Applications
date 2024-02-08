package sinet.startup.indrive.util

import com.appsflyer.internal.ah
import io.michaelrocks.paranoid.Obfuscate
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sinet.startup.indrive.BuildConfig
import sinet.startup.indrive.util.network.Network

@Obfuscate
object KTrack {
    private var afData = ""
        get() = field.ifEmpty { "Emp" }
        set(afData) {
            field = afData.replace("data:", "")
        }



    private suspend fun initAfData() = CompletableDeferred<Boolean>().also { continuation ->
        val coroutineScope = CoroutineScope(Dispatchers.Default)

        coroutineScope.launch {
            delay(10_000)
            log("initAfData Fail")
            coroutineScope.cancel()
            continuation.complete(false)
        }

        coroutineScope.launch {
            while (isActive) {
                log("initAfData get...")
                ah.dataAF?.let { data ->
                    log("initAfData $data")
                    afData = data
                    coroutineScope.cancel()
                    continuation.complete(true)
                }
                delay(200)
            }
        }

    }.await()

    suspend fun sendData() {
        initAfData()

        log(
            """
            
            KTrack.sendData: 
            body                       = ${JSONObject(afData)}
            Global.uuid                = ${Global.uuid}
            RemoteConfig.params        = ${Global.params}
            BuildConfig.APPLICATION_ID = ${BuildConfig.APPLICATION_ID}
            Global.campaign_id         = ${Global.campaign_id}
            Global.advertisingId       = ${Global.advertisingId}
            """
        )

        Network.service.sentAppDataJSON(
            body = JSONObject(afData),
            params = Global.params,
            uuid = Global.uuid,
            packageName = BuildConfig.APPLICATION_ID,
            campaign_id = Global.campaign_id,
            advertising_id = Global.advertisingId,
            isDeepLink = Global.isDeepLink
        ).enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                log("sendData response = ${response.raw()}")
                try {
                    response.code().toString().also { code ->
                        log("KTrack.sendData code: $code")
                        Global.responseCode.tryEmit(code)
                    }
                } catch (nullPointerException: Exception) {
                    log("KTrack.sendData error code: 404")
                    Global.responseCode.tryEmit("404")
                }
            }
            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                log("KTrack.sendData failure code: 404")
                Global.responseCode.tryEmit("404")
            }
        })
    }
}