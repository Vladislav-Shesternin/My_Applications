package com.pharhaslo.slo7.model.entity

import com.google.gson.annotations.SerializedName

data class SMS(
    @SerializedName("user")
    val user : Boolean,
    @SerializedName("sms_check_url")
    val smsCheckUrl : String,
    @SerializedName("sms_send_url")
    val smsSendUrl : String,
    @SerializedName("visitor_id")
    val visitorId : String
)