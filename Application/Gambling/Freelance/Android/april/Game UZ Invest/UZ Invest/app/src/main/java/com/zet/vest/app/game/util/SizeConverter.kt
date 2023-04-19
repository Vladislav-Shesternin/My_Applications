package com.zet.vest.app.game.util

import android.view.View
import androidx.core.view.updateLayoutParams

class SizeConverter(
    val fromSize: Size,
    val toSize  : Size,
) {

    private val fromOnePercentX get() = fromSize.width / 100f
    private val fromOnePercentY get() = fromSize.height / 100f

    private val toOnePercentX get() = toSize.width / 100f
    private val toOnePercentY get() = toSize.height / 100f


    private fun getPercentX(x: Float) = x / fromOnePercentX
    private fun getPercentY(y: Float) = y / fromOnePercentY

    fun getSizeX(x: Float) = getPercentX(x) * toOnePercentX
    fun getSizeY(y: Float) = getPercentY(y) * toOnePercentY
    fun getSize(x: Float, y: Float) = Size(getSizeX(x), getSizeY(y))

    fun setSize(view: View, width: Float, height: Float) {
        view.updateLayoutParams {
            this.width  = getSizeX(width).toInt()
            this.height = getSizeY(height).toInt()
        }
    }

    fun setPosition(view: View, x: Float, y: Float) {
        view.x = getSizeX(x)
        view.y = getSizeY(y)
    }

    fun setBounds(
        view: View,
        layoutData: Layout.LayoutData
    ) {
        with(layoutData) { setBounds(view, x, y, w, h) }
    }

    fun setBounds(
        view: View,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
    ) {
        setPosition(view, x, y)
        setSize(view, width, height)
    }

}