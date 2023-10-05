package monska.makkers.conver.currinci.util.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

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
    @GET("http://api.exchangerate.host/live?access_key=c6404587afb38116a8e56f6e549c1f56")
    suspend fun getValute(): Valuta

    @GET("http://api.exchangerate.host/list?access_key=c6404587afb38116a8e56f6e549c1f56")
    suspend fun getValuteNameta(): ValutaNameta
}
