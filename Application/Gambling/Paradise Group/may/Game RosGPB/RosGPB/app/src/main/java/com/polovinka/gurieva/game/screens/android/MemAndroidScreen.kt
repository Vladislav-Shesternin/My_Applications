package com.polovinka.gurieva.game.screens.android

import android.net.Uri
import android.view.ViewGroup
import android.widget.VideoView
import com.polovinka.gurieva.MainActivity
import com.polovinka.gurieva.R
import com.polovinka.gurieva.game.game
import com.polovinka.gurieva.game.utils.advanced.AdvancedAndroidScreen

class MemAndroidScreen(override val activity: MainActivity): AdvancedAndroidScreen() {

    private val videoView by lazy { VideoView(game.activity) }

    override fun ViewGroup.addActorsOnStage() {
        addView(videoView)

        val uri = Uri.parse("android.resource://" + activity.packageName + "/" + R.raw.myvideomem)

        videoView.apply {
            sizeConverter.setBounds(this, 38f, 857f, 686f, 386f)
            videoView.setVideoURI(uri)
            videoView.start()
            videoView.setOnCompletionListener { videoView.start() }
        }
    }
}