package com.zet.vest.app.game.util

import com.zet.vest.app.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import java.text.DecimalFormat
import java.util.*

data class Size(
    var width : Float = 0f,
    var height: Float = 0f,
) {
    fun set(width: Float, height: Float) {
        this.width = width
        this.height = height
    }

    fun set(size: Size) {
        width = size.width
        height = size.height
    }
}

data class Vector2(
    var x: Float = 0f,
    var y: Float = 0f,
) {
    fun set(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    fun set(vector2: Vector2) {
        x = vector2.x
        y = vector2.y
    }
}

interface Disposable {
    fun dispose()
}

fun List<Disposable>.disposeAll() {
    forEach { it.dispose() }
}

fun Double.toBalance(): String {
    var str = ""
    StringBuilder(DecimalFormat("#.##").format(this).replace(",", ".")).apply {
        if (length > 1) {
            if (toString()[lastIndex - 1] == '.') append("0") else if (toString()[lastIndex-2] != '.') append(".00")

            when (length) {
                7 -> insert(1, ".")
                8 -> insert(2, ".")
                9 -> insert(3, ".")
                10 -> {
                    insert(4, ".")
                    insert(1, ".")
                }
                else -> log("g - $this | $length")
            }
        } else append(".00")

        str = toString()
    }
    return str
}

val MONTH: Int = Calendar.getInstance().get(Calendar.MONTH)
val YEAR : Int = Calendar.getInstance().get(Calendar.YEAR)
val DAY  : Int = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)