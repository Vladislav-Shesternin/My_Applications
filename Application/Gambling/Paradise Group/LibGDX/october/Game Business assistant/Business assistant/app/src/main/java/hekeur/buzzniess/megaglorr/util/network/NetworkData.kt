package hekeur.buzzniess.megaglorr.util.network

import com.google.gson.annotations.SerializedName

data class Currrent(
    @SerializedName("rates") var rates: MutableMap<String, Double>? = mutableMapOf<String, Double>(),
)

data class CurrentNames(
    @SerializedName("symbols") var symbols: MutableMap<String, String>? = mutableMapOf<String, String>(),
)