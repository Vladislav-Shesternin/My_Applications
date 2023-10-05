package cryptomis.gazik.analoutiks.util.network

import com.google.gson.annotations.SerializedName

object CryptoData {
    data class Crypto(
        @SerializedName("data") var data: ArrayList<Data> = arrayListOf(),
    )

    data class Data(
        @SerializedName("id") var id: Int? = null,
        @SerializedName("name") var name: String? = null,
        @SerializedName("symbol") var symbol: String? = null,
        @SerializedName("quote") var quote: Quote? = Quote()
    )

    data class Quote(
        @SerializedName("USD") var USD: USD? = USD(),
    )

    data class USD(
        @SerializedName("price") var price: Double? = null,
    )
}

object CryptoMetaData {
    data class MetaData(
        @SerializedName("data") var data: MutableMap<String, Crypto>? = mutableMapOf<String, Crypto>(),
    )

    data class Crypto (
        @SerializedName("logo") var logo: String? = null,
    )
}