package monska.makkers.conver.currinci.util.network

import com.google.gson.annotations.SerializedName

data class Valuta(
    @SerializedName("terms"     ) var terms     : String?  = null,
    @SerializedName("privacy"   ) var privacy   : String?  = null,
    @SerializedName("timestamp" ) var timestamp : Int?     = null,
    @SerializedName("quotes"    ) var quotes    : MutableMap<String, Double>? = mutableMapOf<String, Double>(),
)

data class ValutaNameta(
    @SerializedName("currencies") var currencies    : MutableMap<String, String>? = mutableMapOf<String, String>(),
)