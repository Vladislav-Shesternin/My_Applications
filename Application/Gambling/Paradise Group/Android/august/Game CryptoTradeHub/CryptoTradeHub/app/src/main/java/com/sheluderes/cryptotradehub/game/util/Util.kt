package com.sheluderes.cryptotradehub.game.util

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

fun numStr(min: Int, max: Int, count: Int): Long {
    var numStr = ""
    repeat(count) { numStr += (min..max).shuffled().first() }
    return numStr.toLong()
}