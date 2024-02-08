package com.jumping.cubuletus.util

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

val Float.toMS: Long get() = (this * 1000).toLong()



fun log(message: String) {
    Log.i("jumping", message)
}

fun cancelCoroutinesAll(vararg coroutine: CoroutineScope) {
    coroutine.forEach { it.cancel() }
}