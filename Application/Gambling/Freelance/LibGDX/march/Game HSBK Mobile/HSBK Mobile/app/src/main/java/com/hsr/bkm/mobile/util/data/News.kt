package com.hsr.bkm.mobile.util.data

import com.google.gson.annotations.SerializedName

data class NewsObject(
  @SerializedName("data")   val news  : List<News>? = null,
  @SerializedName("status") val status: Int?        = null,
  @SerializedName("err")    val err   : String?     = null,
)

data class News(
  @SerializedName("headline") val headline: String? = null,
  @SerializedName("image")    val image   : String? = null,
  @SerializedName("url")      val url     : String? = null,
)