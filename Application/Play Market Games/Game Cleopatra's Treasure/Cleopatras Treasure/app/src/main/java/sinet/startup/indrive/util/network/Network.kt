package sinet.startup.indrive.util.network

import android.app.Activity
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import io.michaelrocks.paranoid.Obfuscate
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap

@Obfuscate
interface APIService {
    @POST("link")
    fun sentAppDataJSON(
        @Body                    body          : JSONObject?,
        @QueryMap                params        : Map<String, String>,
        @Query("uuid")           uuid          : String?,
        @Query("package")        packageName   : String?,
        @Query("campaign_id")    campaign_id   : String?,
        @Query("advertising_id") advertising_id: String?,
        @Query("deepLink")       isDeepLink    : Boolean
    ): Call<ResponseBody>
}

@Obfuscate
object Network {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(RemoteConfig.linkerUrl ?: "https://google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val service: APIService by lazy {
        retrofit.create(APIService::class.java)
    }
}

fun Activity.haveNetworkConnection(): Boolean {
    var haveConnectedWifi   = false
    var haveConnectedMobile = false
    (getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager).allNetworkInfo.onEach { networkItem ->
        if (networkItem.typeName.equals(
                "WIFI",
                ignoreCase = true
            )
        ) if (networkItem.isConnected) haveConnectedWifi = true
        if (networkItem.typeName.equals(
                "MOBILE",
                ignoreCase = true
            )
        ) if (networkItem.isConnected) haveConnectedMobile = true
    }

    return haveConnectedWifi || haveConnectedMobile
}