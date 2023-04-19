package com.pharhaslo.slo7.model.entity

import com.google.gson.annotations.SerializedName

data class PhoneMask (
    @SerializedName("phone_mask")
    val phoneMask : String,
    @SerializedName("phone_code")
    val countryCode : String,
    @SerializedName("country_flag_url")
    val flagImageUrl : String
)
