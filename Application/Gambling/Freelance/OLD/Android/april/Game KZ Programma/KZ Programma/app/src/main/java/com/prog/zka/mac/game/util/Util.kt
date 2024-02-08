package com.prog.zka.mac.game.util

import com.prog.zka.mac.util.log

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

fun Long.toBalance(): String = StringBuilder(toString()).apply {
    when (length) {
        4 -> insert(1, ".")
        5 -> insert(2, ".")
        6 -> insert(3, ".")
        7 -> {
            insert(4, ".")
            insert(1, ".")
        }
        8 -> {
            insert(5, ".")
            insert(2, ".")
        }
        9 -> {
            insert(6, ".")
            insert(3, ".")
        }
        else -> log("Util.Long.toBalance else: this=$this | length=$length")
    }
}.toString()