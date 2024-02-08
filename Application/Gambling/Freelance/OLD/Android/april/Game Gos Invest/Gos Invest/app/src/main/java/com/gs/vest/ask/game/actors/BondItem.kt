package com.gs.vest.ask.game.actors

import android.graphics.Typeface
import android.os.Build
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.gs.vest.ask.R
import com.gs.vest.ask.game.util.Group
import com.gs.vest.ask.game.util.data.BondUtil
import com.gs.vest.ask.game.util.toBalance
import java.text.DecimalFormat
import com.gs.vest.ask.game.util.Layout.BondItem as LB

class BondItem(val activity: FragmentActivity, val bond: BondUtil.Bond): Group(activity) {

    private val nameText       = TextView(activity)
    private val logoImage      = ImageView(activity)
    private val costText       = TextView(activity)
    private val percentText    = TextView(activity)
    private val amountText     = TextView(activity)
    private val profitText     = TextView(activity)

//    private val debugList = listOf<View>(
//      //  arrowImage,
//      //  logoImage,
//      //  nameText,
//    )

    var block: () -> Unit = {}


    override fun ViewGroup.addActorsOnStage() {
        val backgroundImage = ImageView(activity)
        addView(backgroundImage)
        backgroundImage.setImageResource(R.drawable.obligacia)
        sizeConverter.setBounds(backgroundImage, 0f, 0f, sizeConverter.fromSize.width, sizeConverter.fromSize.height)

        addNameText()
        addLogoImage()
        addCostText()
        addPercentText()
        addAmountText()
        addProfitText()

//        debugList.onEach {
//            it.setBackgroundResource(R.drawable.debug)
//        }

        stage.setOnClickListener { block() }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun ViewGroup.addNameText() {
        addView(nameText)

        nameText.apply {
            text = bond.name
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(activity, R.color.game_blue))
            typeface = Typeface.createFromAsset(activity.assets, "PlayfairDisplay-Black.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 10f
            sizeConverter.setBounds(this, LB.name)
        }
    }

    private fun ViewGroup.addLogoImage() {
        addView(logoImage)

        logoImage.apply {
            setImageResource(bond.logo)
            sizeConverter.setBounds(this, LB.logo)
        }
    }

    private fun ViewGroup.addCostText() {
        addView(costText)

        costText.apply {
            text = "$".plus(" ").plus(bond.cost.toBalance())
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(activity, R.color.game_yellow))
            typeface = Typeface.createFromAsset(activity.assets, "PlayfairDisplay-Bold.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 10f
            sizeConverter.setBounds(this, LB.cost)
        }
    }

    private fun ViewGroup.addPercentText() {
        addView(percentText)

        percentText.apply {
            text = "%".plus(" ").plus(DecimalFormat("#.##").format(bond.percent)).replace(",",".")
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(activity, R.color.game_green))
            typeface = Typeface.createFromAsset(activity.assets, "PlayfairDisplay-Bold.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 10f
            sizeConverter.setBounds(this, LB.percent)
        }
    }

    private fun ViewGroup.addAmountText() {
        addView(amountText)

        amountText.apply {
            text = bond.amount.toString()
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(activity, R.color.white))
            typeface = Typeface.createFromAsset(activity.assets, "PlayfairDisplay-Black.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 10f
            sizeConverter.setBounds(this, LB.amount)
        }
    }

    private fun ViewGroup.addProfitText() {
        addView(profitText)

        profitText.apply {
            text = "$".plus(" ").plus(bond.profit.toBalance())
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(activity, R.color.game_yellow))
            typeface = Typeface.createFromAsset(activity.assets, "PlayfairDisplay-Black.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 10f
            sizeConverter.setBounds(this, LB.profit)
        }
    }

}