package com.prog.zka.mac.game.screens

import android.graphics.Typeface
import android.os.Build
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.prog.zka.mac.R
import com.prog.zka.mac.game.actors.Backgrad
import com.prog.zka.mac.game.navigationManager
import com.prog.zka.mac.game.util.DENSITY
import com.prog.zka.mac.game.util.Layout
import com.prog.zka.mac.game.util.Screen
import com.prog.zka.mac.game.util.Size
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.prog.zka.mac.game.util.Layout.Minutes as LM

class MinutesScreen(
    val activity: FragmentActivity
): Screen(activity) {

    private val backrad      = Backgrad(activity)
    private val ygadaImage   = ImageView(activity)
    private val stranuImage  = ImageView(activity)
    private val poFlaguImage = ImageView(activity)
    private val flagsImage   = ImageView(activity)
    private val igratButton  = Button(activity)
    private val musicButton  = Button(activity)
    private val exitButton   = Button(activity)


//    private val debugList = listOf<View>(
//        balanceTitleText,
//        balanceText,
//        startTradingButton,
//        portfolioImage,
//        emptyImage
//    )

    override fun show(parentStage: ConstraintLayout) {
        super.show(parentStage)
        //parent.setBackgroundResource(R.drawable.background)

//        debugList.onEach {
//            it.setBackgroundResource(R.drawable.debug)
//        }
    }

    override fun ViewGroup.addActorsOnStage() {
        coroutine.launch(Dispatchers.Main) {
            addBackrad()
            addTitleImage()
            addFlags()
            addButtons()

            animTitle()
        }
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun ViewGroup.addBackrad() {
        addView(backrad.stage)

        sizeConverter.setBounds(backrad.stage, 0f, 0f, WIDTH, HEIGHT)
        backrad.initialize(Size(WIDTH, HEIGHT)) {
            backrad.startAnim()
        }
    }

    private fun ViewGroup.addTitleImage() {
        addView(ygadaImage)
        addView(stranuImage)
        addView(poFlaguImage)

        ygadaImage.apply {
            setImageResource(R.drawable.ygada)
            sizeConverter.setBounds(this, LM.ygada)
        }
        stranuImage.apply {
            setImageResource(R.drawable.strana)
            sizeConverter.setBounds(this, LM.stran)
        }
        poFlaguImage.apply {
            setImageResource(R.drawable.flaga)
            sizeConverter.setBounds(this, LM.pofla)
        }
    }

    private fun ViewGroup.addFlags() {
        addView(flagsImage)

        flagsImage.apply {
            setImageResource(R.drawable.flags)
            sizeConverter.setBounds(this, LM.flags)
        }
    }

    private fun ViewGroup.addButtons() {
        addView(igratButton)
        addView(musicButton)
        addView(exitButton)

        igratButton.apply {
            setUpButton("играть", LM.b1)
            setOnClickListener {  }
        }
        musicButton.apply {
            setUpButton("музыка вкл", LM.b2)
            setOnClickListener {  }
        }
        exitButton.apply {
            setUpButton("выйти", LM.b3)
            setOnClickListener { navigationManager.exit() }
        }
    }

//
//    private fun ViewGroup.addTimerText() {
//        addView(timerText)
//
//        timerText.apply {
//            gravity = Gravity.CENTER
//            setTextColor(ContextCompat.getColor(activity, R.color.white))
//            typeface = Typeface.createFromAsset(activity.assets, "PlayfairDisplay-Bold.ttf")
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 10f
//            sizeConverter.setBounds(this, LP.timer)
//        }
//    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun Button.setUpButton(text: String, layout: Layout.LayoutData) {
        this.text = text
        isAllCaps = false
        gravity = Gravity.CENTER
        val pad = 30 * DENSITY
        setPadding(0, pad, 0, pad)
        setTextColor(ContextCompat.getColor(activity, R.color.white))
        typeface = Typeface.createFromAsset(activity.assets, "coolvetica rg.otf")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 10f
        setBackgroundResource(R.drawable.button)
        sizeConverter.setBounds(this, layout)
    }

    private fun animTitle() {
        coroutine.launch {
            MutableStateFlow(0).also { flow ->
                flow.collect {
                    withContext(Dispatchers.Main) {
                        ygadaImage.animate().apply {
                            translationX(sizeConverter.getSizeX(282f))
                            duration = 500
                            withEndAction {
                                translationX(sizeConverter.getSizeX(118f))
                                duration = 500
                            }
                        }.start()

                        poFlaguImage.animate().apply {
                            translationX(sizeConverter.getSizeX(272f))
                            duration = 500
                            withEndAction {
                                translationX(sizeConverter.getSizeX(418f))
                                duration = 500
                                withEndAction { flow.value += 1 }
                            }
                        }.start()
                    }
                }
            }
        }
    }

}