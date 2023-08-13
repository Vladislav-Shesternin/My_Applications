package com.nikelodeon.coursecalculator.game.screens

import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.nikelodeon.coursecalculator.MainActivity
import com.nikelodeon.coursecalculator.R
import com.nikelodeon.coursecalculator.game.actors.ViewsGroup
import com.nikelodeon.coursecalculator.game.manager.GameDataStoreManager
import com.nikelodeon.coursecalculator.game.util.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CourseCalculatorScreen(override val activity: MainActivity) : Screen(activity) {

    private val megaImg = ImageView(activity)
    private val doneBox = Button(activity)
    private val doneBtn = Button(activity)

    var isCheck = false

    override fun ViewGroup.addActorsOnStage() {
        activity.lottie.hideLoader()

        addMegaImg()
        addDoneBox()
        addDoneBtn()

        val group = ViewsGroup(activity)
        addView(group.stage)
        group.stage.setBounds(85f, 1080f, 495f, 104f)
        group.show(495f)
        disposableList.add(group)

    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun ViewGroup.addMegaImg() {
        addView(megaImg)

        megaImg.apply {
            setImageResource(R.drawable.mega_img)
            setBounds(34f, 192f, 589f, 985f)
        }
    }

    private fun ViewGroup.addDoneBox() {
        addView(doneBox)

        doneBox.apply {
            setBackgroundResource(R.drawable.selector_box)
            setBounds(34f, 1086f, 51f, 51f)

            setOnClickListener {
                if (isCheck) {
                    isSelected = false
                    isCheck = false
                } else {
                    isSelected = true
                    isCheck = true
                }

                doneBtn.isEnabled = isCheck
            }
        }
    }

    private fun ViewGroup.addDoneBtn() {
        addView(doneBtn)

        doneBtn.apply {
            setBackgroundResource(R.drawable.selector_btn)
            setBounds(34f, 1210f, 569f, 88f)
            isEnabled = false

            setOnClickListener {
                isEnabled = false
                coroutine.launch(Dispatchers.IO) {
                    GameDataStoreManager.Answer.update { true }
                    game.navigationManager.navigate(BlackScreen(activity))
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