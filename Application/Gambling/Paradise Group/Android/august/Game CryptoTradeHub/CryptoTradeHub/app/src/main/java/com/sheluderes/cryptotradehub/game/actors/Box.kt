package com.sheluderes.cryptotradehub.game.actors

import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.updateLayoutParams
import com.sheluderes.cryptotradehub.MainActivity
import com.sheluderes.cryptotradehub.game.util.Group

class Box(override val activity: MainActivity, val resId: Int): Group(activity) {

    val img = Button(activity)
    var isCheck = false
        private set

    override fun ViewGroup.addActorsOnStage() {
        addView(img)
        img.setBackgroundResource(resId)
        img.x = 0f
        img.y = 0f
        img.updateLayoutParams {
            this.width  = this@addActorsOnStage.width
            this.height = this@addActorsOnStage.height
        }
    }

    fun check() {
        img.isSelected = true
        isCheck = true
    }

    fun uncheck() {
        img.isSelected = false
        isCheck = false

    }

}