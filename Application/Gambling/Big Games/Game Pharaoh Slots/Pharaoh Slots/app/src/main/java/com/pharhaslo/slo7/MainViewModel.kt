package com.pharhaslo.slo7

import android.app.Application
import android.text.Editable
import android.util.Log


import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.pharhaslo.slo7.util.OkHttpCustomClient
import com.pharhaslo.slo7.model.entity.PhoneMask
import com.pharhaslo.slo7.model.entity.Product
import com.pharhaslo.slo7.model.entity.SMS
import com.pharhaslo.slo7.util.Resource
import com.pharhaslo.slo7.fragment.SplashFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject


@ExperimentalCoroutinesApi
class MainViewModel(
    private val mApplication: Application
)  : AndroidViewModel(mApplication) {

    val isFullGameButtonActivated = MutableLiveData<Boolean>()
    val products = MutableLiveData<Resource<List<Product>>>()
    val messageSent = MutableLiveData<Resource<Boolean>>()
    val codeVerified = MutableLiveData<Resource<Boolean>>()
    val smsObject = MutableLiveData<SMS>()
    val siteUrl = MutableLiveData<String>()
    val phoneMask = MutableLiveData<PhoneMask>()
    val editTextPhone = MutableLiveData<Editable>()


    private val appName = mApplication.packageName





    fun sendSMS(phoneNumber: String, visitorId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            messageSent.postValue(Resource.Loading())
            try {
                val preparedPhoneNumber =
                    if (phoneNumber[0] == '+') StringBuilder(phoneNumber).deleteCharAt(0)
                        .toString() else phoneNumber
                smsObject.value?.smsSendUrl?.let {
                    val requestText = ("{\r\n   " +
                            "\"phone_number\":$preparedPhoneNumber,\r\n   " +
                            "\"app_name\":\"$appName\",\r\n   " +
                            "\"visitor_id\":\"$visitorId\"\r\n}").toRequestBody("application/json".toMediaType())

                    Log.d("testretest","{\r\n   " +
                            "\"phone_number\":$preparedPhoneNumber,\r\n   " +
                            "\"app_name\":\"$appName\",\r\n   " +
                            "\"visitor_id\":\"$visitorId\"\r\n}" )
                    var jsonSms: JSONObject? = null
                    try {
                        val request = Request.Builder()
                            .url(it)
                            .post(requestText)
                            .build()

                        val response = OkHttpCustomClient.getOkHttpClient().newCall(request)
                            .execute().body?.string() ?: ""

                        jsonSms = JSONObject(response)
                    } catch (e: Exception) {
                        messageSent.postValue(Resource.Error(mApplication.getString(R.string.error_phone_number_text)))
                    }

                    if (jsonSms?.isNull("request_status") == true ||
                        jsonSms?.getString("request_status") != "succeed"
                    ) {
                        messageSent.postValue(Resource.Error(mApplication.getString(R.string.error_phone_number_text)))
                    } else {
                        messageSent.postValue(Resource.Success(true))
                    }
                }
            } catch (e: Exception) {
                messageSent.postValue(Resource.Error(mApplication.getString(R.string.error_no_internet_connection)))
            }
        }
    }

    fun isSMSCodeValid(verificationCode: String, phoneNumber: String, visitorId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val preparedPhoneNumber =
                if (phoneNumber[0] == '+') StringBuilder(phoneNumber).deleteCharAt(0)
                    .toString() else phoneNumber
            codeVerified.postValue(Resource.Loading())
            try {
                smsObject.value?.smsCheckUrl?.let {
                    val requestText = ("{\r\n   " +
                            "\"phone_number\":$preparedPhoneNumber,\r\n   " +
                            "\"app_name\":\"$appName\",\r\n   " +
                            "\"verification_code\":\"$verificationCode\",\r\n   " +
                            "\"visitor_id\":\"$visitorId\"\r\n}").toRequestBody("application/json".toMediaType())
                    val request = Request.Builder()
                        .url(it)
                        .post(requestText)
                        .build()

                    val response = OkHttpCustomClient.getOkHttpClient().newCall(request)
                        .execute().body?.string() ?: ""
                    val jsonResponse = JSONObject(response)

                    val codeValid = jsonResponse.getBoolean("is_valid")

                    if (codeValid)
                        codeVerified.postValue(Resource.Success(codeValid))
                    else
                        codeVerified.postValue(Resource.Error(mApplication.getString(R.string.error_code_not_valid)))
                }
            } catch (e: Exception) {
                codeVerified.postValue(Resource.Error(mApplication.getString(R.string.error_no_internet_connection)))
            }

        }
    }


    fun getCasinos(visitorId: String) {
        if(mApplication.getSharedPreferences("save", 0).getString("savedUrl", "")?.isEmpty() == true) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    products.postValue(Resource.Loading())

                    val tt = "{\"bundle_id\":\"$appName\" , \"visitor_id\": \"$visitorId\"}"
                    println(tt)

                    val requestText =
                        ("{\"bundle_id\":\"$appName\" , \"visitor_id\": \"$visitorId\"}").toRequestBody(
                            "application/json".toMediaType()
                        )


                    val request = Request.Builder()
                        .url("${SplashFragment.START_URL}api/user/data/")
                        .post(requestText)
                        .build()

                    val response =
                        OkHttpCustomClient.getOkHttpClient().newCall(request)
                            .execute().body?.string()
                            ?: ""

                        try {
                            val jsonResponse = JSONObject(response)
                            if (jsonResponse.getString("message") == "no products available" ||
                                jsonResponse.getString("message") == "user_status false") {
                                products.postValue(Resource.Error("We don't have offers for you"))
                            }
                    } catch (e: Exception) {
                    }

                    val productsObjects =
                        Gson().fromJson(response, Array<Product>::class.java).toList()
                    products.postValue(Resource.Success(productsObjects))


                } catch (e: Exception) {
                    products.postValue(Resource.Error("No internet connection"))
                }
            }
        }
    }


    fun setPhoneMask()  {
        viewModelScope.launch(Dispatchers.IO){
            try{
                val request = Request.Builder()
                    .url("${SplashFragment.START_URL}api/user/phone/?visitor_id=${getVisitorId()}&bundle_id=${appName}")
                    .build()

                val response =
                    OkHttpCustomClient.getOkHttpClient().newCall(request)
                        .execute().body?.string()
                        ?: ""

                phoneMask.postValue(Gson().fromJson(response, PhoneMask::class.java))
            } catch (e : Exception){
                phoneMask.postValue(PhoneMask("xxx-xxx-xx-xx", "+380", "https://google.com"))
            }
        }

    }


    fun saveUser() {
            mApplication.getSharedPreferences("save", 0).edit {
                putBoolean("user", true)
                apply()
            }
        }

    fun getVisitorId() =
        mApplication.getSharedPreferences("save", 0).getString("visitor_id", "") ?: ""

    }
