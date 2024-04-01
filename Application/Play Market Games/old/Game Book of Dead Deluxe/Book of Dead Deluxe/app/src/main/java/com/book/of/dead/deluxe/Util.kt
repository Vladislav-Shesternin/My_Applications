package com.book.of.dead.deluxe

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

class Once {

    private var event = Event.NOT_WAS



    fun once(block: () -> Unit) {
        if (event == Event.NOT_WAS) {
            event = Event.WAS
            block()
        }
    }


    enum class Event {
        WAS, NOT_WAS
    }

}

val Number.length: Int get() = toString().length

val Float.toMS: Long get() = (this * 1000).toLong()

fun probability(percent: Int, block: () -> Unit = {}): Boolean {
    val randomNum = (0..100).shuffled().first()
    return if (randomNum <= percent) {
        block()
        true
    } else false
}

fun log(message: String) {
    Log.i("VAliK", message)
}

fun cancelCoroutinesAll(vararg coroutine: CoroutineScope) {
    coroutine.forEach { it.cancel() }
}