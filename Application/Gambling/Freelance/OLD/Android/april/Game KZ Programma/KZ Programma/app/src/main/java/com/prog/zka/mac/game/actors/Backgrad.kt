package com.prog.zka.mac.game.actors

import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.prog.zka.mac.R
import com.prog.zka.mac.game.util.Group
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Backgrad(val activity: FragmentActivity): Group(activity) {

    private val oneImage = ImageView(activity)
    private val twoImage = ImageView(activity)
    private val thrImage = ImageView(activity)



    override fun ViewGroup.addActorsOnStage() {
        addView(oneImage)
        addView(twoImage)
        addView(thrImage)

        oneImage.apply {
            setBackgroundResource(R.drawable.splash)
            sizeConverter.setBounds(this, 0f, 0f, sizeConverter.fromSize.width, sizeConverter.fromSize.height)
        }
        twoImage.apply {
            setBackgroundResource(R.drawable.splash)
            rotationX = 180f
            sizeConverter.setBounds(this, 0f, -sizeConverter.fromSize.height, sizeConverter.fromSize.width, sizeConverter.fromSize.height)
        }
        thrImage.apply {
            setBackgroundResource(R.drawable.splash)
            sizeConverter.setBounds(this, 0f, -(sizeConverter.fromSize.height * 2), sizeConverter.fromSize.width, sizeConverter.fromSize.height)
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun startAnim() {
        coroutine.launch {
            MutableStateFlow(0).also { flow ->
                flow.collect {
                    withContext(Dispatchers.Main) {
                        stage.animate().apply {
                            translationY(stage.height.toFloat() * 2)
                            duration = 3000
                            withEndAction {
                                translationY(0f)
                                duration = 0
                                withEndAction {
                                    flow.value += 1
                                }
                            }
                        }.start()
                    }
                }
            }
        }
    }

}