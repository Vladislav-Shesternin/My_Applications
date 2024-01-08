package mobile.jogodobicho.lucky.util

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.os.BatteryManager
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

fun log(message: String) {
    Log.i("VLAD", message)
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

fun Context.isUSB() = Settings.Secure.getInt(contentResolver, Settings.Secure.ADB_ENABLED, 0) == 1

fun Context.getBatteryPercentage(): Int {
    val bm = getSystemService(Context.BATTERY_SERVICE) as BatteryManager
    return bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
}

fun Context.isBatteryCharging(): Boolean {
    val bm = getSystemService(Context.BATTERY_SERVICE) as BatteryManager
    return bm.isCharging
}