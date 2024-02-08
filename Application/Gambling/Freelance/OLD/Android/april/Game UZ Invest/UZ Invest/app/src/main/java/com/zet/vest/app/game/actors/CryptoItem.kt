package com.zet.vest.app.game.actors

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.zet.vest.app.R
import com.zet.vest.app.game.util.Group
import com.zet.vest.app.game.util.Layout
import com.zet.vest.app.game.util.Layout.CryptoItem as LC

class CryptoItem(val activity: FragmentActivity): Group(activity) {

    private val separatorImage = ImageView(activity)
    private val arrowImage     = ImageView(activity)
    private val logoImage      = ImageView(activity)
    private val nameText       = TextView(activity)

    private val debugList = listOf<View>(
      //  arrowImage,
      //  logoImage,
      //  nameText,
    )

    override fun ViewGroup.addActorsOnStage() {
        addSeparatorImage()
        addArrowImage()
        addLogoImage()
        addNameText()

        debugList.onEach {
            it.setBackgroundResource(R.drawable.debug)
        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun ViewGroup.addSeparatorImage() {
        addView(separatorImage)

        separatorImage.apply {
            setImageResource(R.drawable.separator)
            sizeConverter.setBounds(this, LC.separator)
        }
    }

    private fun ViewGroup.addArrowImage() {
        addView(arrowImage)

        arrowImage.apply {
            setImageResource(R.drawable.arrow)
            sizeConverter.setBounds(this, LC.arrow)
        }
    }

    private fun ViewGroup.addLogoImage() {
        addView(logoImage)

        logoImage.apply {
            sizeConverter.setBounds(this, LC.logo)
        }
    }

    private fun ViewGroup.addNameText() {
        addView(nameText)

        nameText.apply {
            setTextColor(ContextCompat.getColor(activity, R.color.white))
            typeface = Typeface.createFromAsset(activity.assets, "Amiko-Bold.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 15f
            sizeConverter.setBounds(this, LC.name)
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun setLogoAndName(logoUrl: String, name: String, symbol: String) {
        Glide.with(activity)
            .load(logoUrl)
            .into(logoImage)

        nameText.text = name.plus(" ($symbol)")
    }


}