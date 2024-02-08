package com.prog.zka.mac.game.screens

import android.graphics.Typeface
import android.os.Build
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.prog.zka.mac.R
import com.prog.zka.mac.game.actors.Backgrad
import com.prog.zka.mac.game.navigationManager
import com.prog.zka.mac.game.util.DENSITY
import com.prog.zka.mac.game.util.Screen
import com.prog.zka.mac.game.util.Size
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.prog.zka.mac.game.util.Layout.Geometrical as LG

class GeometricalScreen(
    val activity: FragmentActivity
): Screen(activity) {

    private companion object {
        val listFlagImage = listOf<Int>(
            R.drawable.a1,
            R.drawable.a2,
            R.drawable.a3,
            R.drawable.a4,
            R.drawable.a5,
            R.drawable.a6,
            R.drawable.a7,
            R.drawable.a8,
            R.drawable.a9,
            R.drawable.a10,
            R.drawable.a11,
            R.drawable.a12,
            R.drawable.a13,
            R.drawable.a14,
            R.drawable.a15,
            R.drawable.a16,
            R.drawable.a17,
            R.drawable.a18,
            R.drawable.a19,
            R.drawable.a20,
            R.drawable.a21,
            R.drawable.a22,
            R.drawable.a23,
            R.drawable.a24,
            R.drawable.a25,
            R.drawable.a26,
            R.drawable.a27,
        )
        val listFlagName = listOf<String>(
            "Алжир",
            "Андорра",
            "Ангола",
            "Антигуа",
            "Аргентина",
            "Армения",
            "Багамы",
            "Боливия",
            "Бангладеш",
            "Камерун",
            "Канада",
            "Конго",
            "Чехия",
            "Эквадор",
            "Доминикана",
            "Греция",
            "Гайана",
            "Гонконг",
            "Исландия",
            "Великобритания",
            "Япония",
            "Мексика",
            "Ямайка",
            "Ирландия",
            "Италия",
            "США",
            "Украина",
        )

        val listCountry = List(listFlagImage.size) { Country(listFlagImage[it], listFlagName[it]) }

        data class Country(val flag: Int, val name: String)
    }

    private val backrad = Backgrad(activity)
    private val country = listCountry.shuffled().first()
    private val names   = listFlagName.toMutableList().apply { remove(country.name) }.shuffled().take(3).toMutableList().apply { add(country.name) }.shuffled()

    private val circleImage = ImageView(activity)
    private val flagImage   = ImageView(activity)
    private val buttons     = List(4) { ImageView(activity) }
    private val labels      = List(4) { TextView(activity) }

//    private val debugList = listOf<View>(
//        circleImage,
//    )
    override fun ViewGroup.addActorsOnStage() {
        coroutine.launch(Dispatchers.Main) {
            addBackrad()
            addCircleImage()
            addFlagImage()
            addButtons()

            animShowFlag()
            animShowButtons()

           // debugList.onEach { it.setBackgroundResource(R.drawable.debug) }
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

    private fun ViewGroup.addCircleImage() {
        addView(circleImage)

        circleImage.apply {
            setImageResource(R.drawable.circle_deff)
            sizeConverter.setBounds(this, LG.circle)
            alpha = 0f
        }
    }

    private fun ViewGroup.addFlagImage() {
        addView(flagImage)

        flagImage.apply {
            setImageResource(country.flag)
            sizeConverter.setBounds(this, LG.flag)
            alpha = 0f
        }
    }

    private fun ViewGroup.addButtons() {
        buttons.onEachIndexed { index, btn ->
            addView(btn)
            val txt = labels[index]
            addView(txt)

            txt.apply {
                text = names[index]
                isAllCaps = false
                gravity = Gravity.CENTER
                setTextColor(ContextCompat.getColor(activity, R.color.black))
                typeface = Typeface.createFromAsset(activity.assets, "coolvetica rg.otf")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 24f
                sizeConverter.setBounds(this, LG.txtPos[index], LG.txtSize)
            }

            btn.apply {
                setBackgroundResource(R.drawable.btn_white)
                sizeConverter.setBounds(this, LG.btnStartPos[index], LG.btnSize)

                setOnClickListener {
                    coroutine.launch(Dispatchers.Main) {
                        if (txt.text == country.name) {
                            circleImage.setImageResource(R.drawable.circle_true)
                            isEnabled = false
                        } else {
                            circleImage.setImageResource(R.drawable.circle_false)
                            isSelected = true
                        }
                        delay(500)

                        animHideButtons()
                        animHideFlag { navigationManager.navigate(GeometricalScreen(activity)) }
                    }
                }
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun animShowFlag() {
        circleImage.animate().apply {
            alpha(1f)
            duration = 1000
        }.start()

        flagImage.animate().apply {
            alpha(1f)
            duration = 1000
        }.start()
    }

    private fun animHideFlag(block: () -> Unit) {
        circleImage.animate().apply {
            alpha(0f)
            duration = 1000
        }.start()

        flagImage.animate().apply {
            alpha(0f)
            duration = 1000
            withEndAction(block)
        }.start()
    }

    private fun animShowButtons() {
        buttons.onEachIndexed { index, btn ->
            btn.animate().apply {
                translationX(sizeConverter.getSizeX(LG.btnPos[index].x))
                duration = 500
            }.start()
        }
        labels.onEachIndexed { index, btn ->
            btn.animate().apply {
                alpha(1f)
                duration = 250
            }.start()
        }
    }

    private fun animHideButtons() {
        buttons.onEachIndexed { index, btn ->
            btn.animate().apply {
                translationX(sizeConverter.getSizeX(LG.btnStartPos[index].x))
                duration = 500
            }.start()
        }
        labels.onEachIndexed { index, btn ->
            btn.animate().apply {
                alpha(0f)
                duration = 250
            }.start()
        }
    }

}