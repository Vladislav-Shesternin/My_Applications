package com.kurs.mon.fin.game.actors

import android.graphics.Typeface
import android.os.Build
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.kurs.mon.fin.R
import com.kurs.mon.fin.game.util.Group
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.math.cos

class Currency(val activity: FragmentActivity, val name: String, val cost: Double): Group(activity) {

    private val nameText      = TextView(activity)
    private val ellipsisImage = ImageView(activity)

    var blockSelectCurrency: (String, Double) -> Unit = { a, b -> }

    override fun ViewGroup.addActorsOnStage() {
        addName()
        addEllipsis()

        stage.setOnClickListener { blockSelectCurrency(name, cost) }
    }

    // ---------------------------------------------------
    // Add View
    // ---------------------------------------------------

    private fun ViewGroup.addName() {
        addView(nameText)

        nameText.apply {
            text = name
            isAllCaps = true
            gravity = Gravity.CENTER
            typeface = Typeface.createFromAsset(activity.assets, "DMMono-Medium.ttf")
            setTextColor(ContextCompat.getColor(activity, R.color.white))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 10f
            sizeConverter.setBounds(this, 0f, 0f, 208f, 65f)
        }
    }

    private fun ViewGroup.addEllipsis() {
        addView(ellipsisImage)

        ellipsisImage.apply {
            setImageResource(R.drawable.ellipsis)
            sizeConverter.setBounds(this, 86f, 68f, 37f, 4f)
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

}