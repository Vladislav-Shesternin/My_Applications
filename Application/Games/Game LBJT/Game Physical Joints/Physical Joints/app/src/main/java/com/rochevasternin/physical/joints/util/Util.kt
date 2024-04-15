package com.rochevasternin.physical.joints.util

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

fun log(message: String) {
    Log.i("Rostislav", message)
}

fun cancelCoroutinesAll(vararg coroutine: CoroutineScope?) {
    coroutine.forEach { it?.cancel() }
}

fun Uri.readBytes(context: Context): ByteArray? {
    var inputStream          : InputStream? = null
    var byteArrayOutputStream: ByteArrayOutputStream? = null

    try {
        inputStream           = context.contentResolver.openInputStream(this)
        byteArrayOutputStream = ByteArrayOutputStream()

        if (inputStream != null) {
            val buffer = ByteArray(1024)
            var bytesRead: Int

            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead)
            }

            return byteArrayOutputStream.toByteArray()
        }
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        inputStream?.close()
        byteArrayOutputStream?.close()
    }

    return null
}

fun View.setVisible(visibility: Int) {
    if (this.visibility != visibility) this.visibility = visibility
}