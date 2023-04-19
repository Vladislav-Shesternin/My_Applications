package com.gs.vest.ask.game.util

import com.gs.vest.ask.game.manager.GameDataStoreManager
import com.gs.vest.ask.util.Once
import com.gs.vest.ask.util.cancelCoroutinesAll
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

object BalanceUtil: Disposable {

    private val coroutine = CoroutineScope(Dispatchers.IO)
    private val onceBlock = Once()

    val flow = MutableStateFlow(-1L)


    fun initialize(block: () -> Unit) {
        coroutine.launch {
            GameDataStoreManager.Balance.collect { balance ->
                if (flow.value == -1L) flow.value = balance ?: 1_000_000L
                else cancel()
            }
        }
        coroutine.launch {
            flow.collect { balance ->
                if (balance != -1L) {
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