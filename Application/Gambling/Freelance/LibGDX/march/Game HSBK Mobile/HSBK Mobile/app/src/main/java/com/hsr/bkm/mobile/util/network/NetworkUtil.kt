package com.hsr.bkm.mobile.util.network

import android.app.Activity
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import com.hsr.bkm.mobile.util.data.NewsObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
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
    @GET
    suspend fun getJsonByUrl(@Url url: String): NewsObject
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