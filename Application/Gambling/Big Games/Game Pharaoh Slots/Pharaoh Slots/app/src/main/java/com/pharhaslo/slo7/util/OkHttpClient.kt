package com.pharhaslo.slo7.util

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object OkHttpCustomClient {
    private lateinit var  customClient : OkHttpClient
    fun setOkHttpClient(userAgent : String): OkHttpClient {
    if(!this::customClient.isInitialized){
       customClient = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            .callTimeout(10000, TimeUnit.MILLISECONDS)
           .readTimeout(10000, TimeUnit.MILLISECONDS)
           .writeTimeout(10000, TimeUnit.MILLISECONDS)
           .addNetworkInterceptor { chain ->
                chain.proceed(
                    chain.request()
                        .newBuilder()
                        .header("User-Agent", userAgent)
                        .build()
                )
            }.build()
    }
        return customClient
    }

    fun getOkHttpClient() = customClient
}