package com.klubnika.bittracker.game.screens

import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.klubnika.bittracker.MainActivity
import com.klubnika.bittracker.R
import com.klubnika.bittracker.game.actors.SalidolGroup
import com.klubnika.bittracker.game.manager.GameDataStoreManager
import com.klubnika.bittracker.game.util.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BitTrackerCheckScreen(override val activity: MainActivity) : Screen(activity) {

    private val superImg = ImageView(activity)
    private val goneBox  = Button(activity)
    private val goneBtn  = Button(activity)

    var isCheckBox = false

    override fun show(parentStage: ConstraintLayout) {
//        parentStage.setBackgroundResource(R.drawable.background_one)
        super.show(parentStage)
    }

    override fun ViewGroup.addActorsOnStage() {
        activity.lottie.hideLoader()

        addMegaImg()

        val group = SalidolGroup(activity)
        addView(group.stage)
        group.stage.setBounds(0f, 0f, WIDTH, HEIGHT)
        group.show(WIDTH)
        disposableList.add(group)

        addDoneBox()
        addDoneBtn()

    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun ViewGroup.addMegaImg() {
        addView(superImg)

        superImg.apply {
            setImageResource(R.drawable.tarakan)
            setBounds(31f, 186f, 516f, 862f)
        }
    }

    private fun ViewGroup.addDoneBox() {
        addView(goneBox)

        goneBox.apply {
            setBackgroundResource(R.drawable.selector_box)
            setBounds(31f, 968f, 46f, 46f)

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
            setBounds(24f, 1071f, 524f, 93f)
            isEnabled = false

            setOnClickListener {
                isEnabled = false
                coroutine.launch(Dispatchers.IO) {
                    GameDataStoreManager.Olivec.update { true }
                    game.navigationManager.navigate(OlegLebedevScreen(activity))
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