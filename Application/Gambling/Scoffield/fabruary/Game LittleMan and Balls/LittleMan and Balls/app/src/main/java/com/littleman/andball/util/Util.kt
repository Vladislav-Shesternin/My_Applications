package com.littleman.andball.util

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

fun log(message: String) {
    Log.i("tiger", message)
}

fun cancelCoroutinesAll(vararg coroutine: CoroutineScope?) {
    coroutine.forEach { it?.cancel() }
}

val Float.toMS: Long get() = (this * 1000).toLong()

val Number.length: Int get() = toString().length

