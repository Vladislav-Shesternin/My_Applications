package com.vbt.game.sptr.util.network

import com.vbt.game.sptr.MainActivity
import com.vbt.game.sptr.util.log
import org.json.JSONObject

object Gist {
    private const val GIST_URL = "https://gist.githubusercontent.com/dorotdo/7ff52df7b9b6127c2286392e21a0706c/raw/com.vbt.game.sptr"

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