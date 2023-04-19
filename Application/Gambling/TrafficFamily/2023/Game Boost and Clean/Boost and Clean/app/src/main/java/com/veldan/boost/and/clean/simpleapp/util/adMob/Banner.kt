package com.veldan.boost.and.clean.simpleapp.util.adMob

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.veldan.boost.and.clean.simpleapp.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class Banner(
    val adView: AdView,
    val coroutineMain: CoroutineScope
) {

    fun load() {
        coroutineMain.launch { adView.loadAd(AdRequest.Builder().build()) }
    }

}