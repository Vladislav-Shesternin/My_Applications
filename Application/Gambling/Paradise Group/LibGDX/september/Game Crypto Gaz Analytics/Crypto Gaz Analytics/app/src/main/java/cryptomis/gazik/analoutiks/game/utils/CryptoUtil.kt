package cryptomis.gazik.analoutiks.game.utils

object CryptoUtil {

    val cryptoList = mutableListOf<Crypto>()

    class Crypto {
        var id    : Int    = -1
        var name  : String = "-"
        var symbol: String = "-"
        var price : Double = 0.0
        var logo  : String? = null

        override fun toString(): String {
            return "id=$id | name=$name | symbol=$symbol | price=$price | logo=$logo"
        }
    }
}