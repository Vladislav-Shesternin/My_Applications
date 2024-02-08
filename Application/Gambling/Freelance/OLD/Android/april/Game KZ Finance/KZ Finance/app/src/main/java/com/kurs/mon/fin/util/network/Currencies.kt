package com.kurs.mon.fin.util.network

import com.google.gson.annotations.SerializedName

data class Currencies(
    @SerializedName("date" ) var date : String?              = null,
    @SerializedName("kzt"  ) var kzt  : Map<String, Double>? = null,
)