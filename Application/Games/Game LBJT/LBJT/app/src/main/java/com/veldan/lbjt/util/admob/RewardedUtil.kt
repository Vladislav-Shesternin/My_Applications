package com.veldan.lbjt.util.admob

import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.veldan.lbjt.MainActivity
import com.veldan.lbjt.R
import com.veldan.lbjt.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class RewardedUtil(private val activity : MainActivity, val coroutine: CoroutineScope) {

    private val adRequest by lazy { AdRequest.Builder().build() }
    private var rewardedAd: RewardedAd? = null

    private fun load(successBlock: () -> Unit, failedBlock: () -> Unit) {
        coroutine.launch(Dispatchers.Main) {
            RewardedAd.load(activity, activity.getString(R.string.reward_UID), adRequest,
                object : RewardedAdLoadCallback() {
                    override fun onAdLoaded(ad: RewardedAd) {
                        log("RewardedUtil: loaded.")
                        rewardedAd = ad.apply {
                            fullScreenContentCallback = object : FullScreenContentCallback() {
                                override fun onAdClicked() {
                                    log("Ad was clicked.")
                                }

                                override fun onAdDismissedFullScreenContent() {
                                    log("Ad dismissed fullscreen content.")
                                    rewardedAd = null
                                }

                                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                                    log("Ad failed to show fullscreen content.")
                                    rewardedAd = null
                                }

                                override fun onAdImpression() {
                                    log("Ad recorded an impression.")
                                }

                                override fun onAdShowedFullScreenContent() {
                                    log("Ad showed fullscreen content.")
                                }
                            }
                        }
                        successBlock()
                        cancel()
                    }

                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        log("onAdFailedToLoad: ${adError.message}")
                        rewardedAd = null
                        failedBlock()
                        cancel()
                    }
                })
        }
    }

    private fun show(successBlock: (RewardItem) -> Unit, failedBlock: () -> Unit) {
        rewardedAd?.let { ad ->
            ad.show(activity) { rewardItem ->
                log("User earned the reward.")
                successBlock(rewardItem)
            }
        } ?: run {
            log("The rewarded ad wasn't ready yet.")
            failedBlock()
        }
    }

    fun loadAndShow(successBlock: (RewardItem) -> Unit, failedBlock: () -> Unit) {
        load(
            successBlock = { show(successBlock, failedBlock) },
            failedBlock  = failedBlock
        )
    }

}