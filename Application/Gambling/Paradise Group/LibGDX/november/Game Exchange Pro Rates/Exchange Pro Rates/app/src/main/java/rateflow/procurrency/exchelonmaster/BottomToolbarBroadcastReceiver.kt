package rateflow.procurrency.exchelonmaster

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.browser.customtabs.CustomTabsIntent.EXTRA_REMOTEVIEWS_CLICKED_ID
import rateflow.procurrency.exchelonmaster.util.log


class BottomToolbarBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent) {
        val url          = intent.dataString
        val remoteViewId = intent.getIntExtra(EXTRA_REMOTEVIEWS_CLICKED_ID, -1)

        when (remoteViewId) {
            R.id.ct_toolbar_next -> {
                MainActivity.eventFlow.value = EnumEvent.CANCEL
            }

            R.id.ct_toolbar_previous -> {
                MainActivity.eventFlow.value = EnumEvent.APPLY

            }
        }
    }

}