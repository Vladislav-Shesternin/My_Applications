package com.zet.vest.app.game.util

import com.zet.vest.app.game.manager.GameDataStoreManager
import com.zet.vest.app.util.Once
import com.zet.vest.app.util.cancelCoroutinesAll
import com.zet.vest.app.util.log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

object BalanceUtil: Disposable {

    private val coroutine = CoroutineScope(Dispatchers.IO)
    private val onceBlock = Once()

    val flow = MutableStateFlow(-1.0)


    fun initialize(block: () -> Unit) {
        coroutine.launch {
            GameDataStoreManager.Balance.collect { balance ->
                if (flow.value == -1.0) flow.value = balance ?: 1_000.0
                else cancel()
            }
        }
        coroutine.launch {
            flow.collect { balance ->
                if (balance != -1.0) {
                    withContext(Dispatchers.Main) { onceBlock.once { block() } }
                    GameDataStoreManager.Balance.update { balance }
                }
            }
        }
    }


    override fun dispose() {
        cancelCoroutinesAll(coroutine)
    }


}