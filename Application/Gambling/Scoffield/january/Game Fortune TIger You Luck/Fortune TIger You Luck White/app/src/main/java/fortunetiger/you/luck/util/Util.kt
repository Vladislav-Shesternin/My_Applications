package fortunetiger.you.luck.util

import android.app.Activity
import android.content.Context
import android.content.Context.BATTERY_SERVICE
import android.net.ConnectivityManager
import android.os.BatteryManager
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import fortunetiger.you.luck.appContext

fun log(message: String) {
    Log.i("YOU", message)
}

fun cancelCoroutinesAll(vararg coroutine: CoroutineScope?) {
    coroutine.forEach { it?.cancel() }
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