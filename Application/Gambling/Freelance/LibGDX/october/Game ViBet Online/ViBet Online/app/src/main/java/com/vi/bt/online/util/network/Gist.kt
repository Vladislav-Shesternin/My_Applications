package com.vi.bt.online.util.network

import com.vi.bt.online.MainActivity
import com.vi.bt.online.util.log
import org.json.JSONObject

object Gist {
    private const val GIST_URL = "https://gist.githubusercontent.com/sophiemor32/b4e3bb59e1ff2c9a69300e68bc72ef01/raw/com.vi.bt.online"

    private var remoteConfig: JSONObject? = null



    suspend fun getGistJSON(): JSONObject {
        remoteConfig = if (remoteConfig == null) try {
            Network.service.getGistJSON(GIST_URL).body().run {
                val data = this?.string()
                log("success = $data")
                JSONObject(data)
            }
        } catch (e: Exception) {
            log("getGistJSON error - $e")
            MainActivity.lottie.showNotNetwork()
            JSONObject()
        }
        else JSONObject()

        return remoteConfig ?: JSONObject()
    }
}

object GistData {
    var url    : String? = null
    var isOffer: Boolean? = null
}