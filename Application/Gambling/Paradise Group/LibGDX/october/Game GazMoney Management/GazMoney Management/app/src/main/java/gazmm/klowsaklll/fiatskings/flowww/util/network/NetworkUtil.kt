package gazmm.klowsaklll.fiatskings.flowww.util.network

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
    @GET("http://api.exchangeratesapi.io/v1/latest?access_key=2da6b95ce33a048e1ecdec0a7e765fc3")
    suspend fun getCurent(): Currrent

    @GET("http://api.exchangeratesapi.io/v1/symbols?access_key=2da6b95ce33a048e1ecdec0a7e765fc3")
    suspend fun getCurentNames(): CurrentNames
}
