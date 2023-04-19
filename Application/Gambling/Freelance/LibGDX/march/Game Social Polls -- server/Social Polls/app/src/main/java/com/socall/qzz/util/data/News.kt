package com.socall.qzz.util.data

import com.google.gson.annotations.SerializedName

data class SocOpros(
  @SerializedName("social")  val social : String? = null,
  @SerializedName("name")    val name   : String? = null,
  @SerializedName("adv")     val adv    : String? = null,
  @SerializedName("hold")    val hold   : String? = null,
  @SerializedName("result")  val result : String? = null,
  @SerializedName("percent") val percent: String? = null,
)