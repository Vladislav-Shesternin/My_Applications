//package com.zaykoa.investmentanalyzer.game.screens.android
//
//import android.net.Uri
//import android.view.ViewGroup
//import android.widget.VideoView
//import com.zaykoa.investmentanalyzer.MainActivity
//import com.zaykoa.investmentanalyzer.R
//import com.zaykoa.investmentanalyzer.game.game
//import com.zaykoa.investmentanalyzer.game.utils.advanced.AdvancedAndroidScreen
//
//class MemAndroidScreen(override val activity: MainActivity): AdvancedAndroidScreen() {
//
//    private val videoView by lazy { VideoView(game.activity) }
//
//    override fun ViewGroup.addActorsOnStage() {
////        addView(videoView)
////
////        val uri = Uri.parse("android.resource://" + activity.packageName + "/" + R.raw.myvideomem)
////
////        videoView.apply {
////            sizeConverter.setBounds(this, 38f, 857f, 686f, 386f)
////            videoView.setVideoURI(uri)
////            videoView.start()
////            videoView.setOnCompletionListener { videoView.start() }
////        }
//    }
//}