package com.finan.cial.quizz.util.data

import com.google.gson.annotations.SerializedName

data class Finance(
  @SerializedName("fn_app")  val fn_app  : Int?    = null,
  @SerializedName("result")  val result  : String? = null,
  @SerializedName("booster") val booster : Int?    = null,
  @SerializedName("max")     val max     : String? = null,
  @SerializedName("min")     val min     : String? = null,
  @SerializedName("percent") val percent : Int?    = null,
  @SerializedName("low")     val low     : Int?    = null,
)