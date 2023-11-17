package italodisco.fernando.lucherano.iopartew.sandes

import android.app.Activity
import android.net.ConnectivityManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity

const val sarana  = 590f


fun View.setVisible(visibility: Int) {
    if (this.visibility != visibility) this.visibility = visibility
}

fun Activity.internetConnection(): Boolean {
    var haveConnectedWifi   = false
    var haveConnectedMobile = false
    (getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager).allNetworkInfo.onEach { networkItem ->
        if (networkItem.typeName.equals(
                "WIFI",
                ignoreCase = true
            )
        ) if (networkItem.isConnected) haveConnectedWifi = true
        if (networkItem.typeName.equals(
                "MOBILE",
                ignoreCase = true
            )
        ) if (networkItem.isConnected) haveConnectedMobile = true
    }

    return haveConnectedWifi || haveConnectedMobile
}


                    const val Olopo = 1311f

const   val KoloVo = 0.5f
