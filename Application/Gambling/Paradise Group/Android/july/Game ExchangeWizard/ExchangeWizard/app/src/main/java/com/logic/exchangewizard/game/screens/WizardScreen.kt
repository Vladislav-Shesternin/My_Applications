package com.logic.exchangewizard.game.screens

import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.logic.exchangewizard.MainActivity
import com.logic.exchangewizard.R
import com.logic.exchangewizard.game.actors.HidensGroup
import com.logic.exchangewizard.game.manager.GameDataStoreManager
import com.logic.exchangewizard.game.util.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WizardScreen(override val activity: MainActivity) : Screen(activity) {

    private val superImg = ImageView(activity)
    private val goneBox = Button(activity)
    private val goneBtn = Button(activity)

    var isCheckBox = false

    override fun show(parentStage: ConstraintLayout) {
        parentStage.setBackgroundResource(R.drawable.background_one)
        super.show(parentStage)
    }

    override fun ViewGroup.addActorsOnStage() {
        activity.lottie.hideLoader()

        addMegaImg()
        addDoneBox()
        addDoneBtn()

        val group = HidensGroup(activity)
        addView(group.stage)
        group.stage.setBounds(88f, 1161f, 476f, 107f)
        group.show(476f)
        disposableList.add(group)

    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun ViewGroup.addMegaImg() {
        addView(superImg)

        superImg.apply {
            setImageResource(R.drawable.monarh)
            setBounds(35f, 398f, 592f, 860f)
        }
    }

    private fun ViewGroup.addDoneBox() {
        addView(goneBox)

        goneBox.apply {
            setBackgroundResource(R.drawable.selector_box)
            setBounds(35f, 1188f, 52f, 52f)

            setOnClickListener {
                if (isCheckBox) {
                    isSelected = false
                    isCheckBox = false
                } else {
                    isSelected = true
                    isCheckBox = true
                }

                goneBtn.isEnabled = isCheckBox
            }
        }
    }

    private fun ViewGroup.addDoneBtn() {
        addView(goneBtn)

        goneBtn.apply {
            setBackgroundResource(R.drawable.selector_btn)
            setBounds(27f, 1281f, 575f, 106f)
            isEnabled = false

            setOnClickListener {
                isEnabled = false
                coroutine.launch(Dispatchers.IO) {
                    GameDataStoreManager.Qwest.update { true }
                    game.navigationManager.navigate(LinearScreen(activity))
                }
            }
        }
    }



//    private fun ViewGroup.addList() {
//        addView(scrollV.stage)
//
//        sizeConverter.apply { setBounds(scrollV.stage, LL.scroll) }
//        scrollV.initialize(LL.scroll.size()) {
//            var ny = 0f
//
//
//            indices.onEach { i ->
//                listD[i].also { d ->
//                    ImageView(activity).also { img ->
//                        img.setImageResource(d.first)
//                        scrollV.container.addView(img)
//                        img.layoutParams = FrameLayout.LayoutParams(0, 0)
//                        scrollV.sizeConverter.setBounds(img, 0f, ny, 661f, 315f)
//                        ny += 315f + 50f
//                        img.doOnPreDraw { scrollV.updateContainer((it.y + it.height).toInt()) }
//
//                        img.setOnClickListener { navigationManager.navigate(d.second, BanksScreen(activity)) }
//                    }
//                }
//            }
//        }
//    }

}