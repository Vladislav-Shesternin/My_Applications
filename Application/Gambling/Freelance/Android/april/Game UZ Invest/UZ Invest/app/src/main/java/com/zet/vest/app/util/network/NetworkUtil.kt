package com.zet.vest.app.util.network

import android.app.Activity
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

object NetworkUtil {
    private const val BASE_URL = "https://google.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val service: APIService by lazy {
        retrofit.create(APIService::class.java)
    }
}

interface APIService {
    @Headers(
        "X-CMC_PRO_API_KEY: 9db2ccaf-a9d1-430c-bc83-37d684ed963f",
        "Accept: application/json",
    )
    @GET("https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest")
    suspend fun getCrypto(): CryptoData.Crypto

    @Headers(
        "X-CMC_PRO_API_KEY: 9db2ccaf-a9d1-430c-bc83-37d684ed963f",
        "Accept: application/json",
    )
    @GET("https://pro-api.coinmarketcap.com/v2/cryptocurrency/info?aux=logo")
    suspend fun getMetaData(@Query("id") id: String): CryptoMetaData.MetaData
}

fun Activity.internetConnection(): Boolean {
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
