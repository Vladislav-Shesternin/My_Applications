package com.tutotoons.app.kpopsiescuteunicornpet.game.utils.dataStore

import com.tutotoons.app.kpopsiescuteunicornpet.game.manager.GameDataStoreManager
import com.tutotoons.app.kpopsiescuteunicornpet.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class RecordUtil(val coroutine: CoroutineScope, val levelUtil: LevelUtil) {

    var record = 0
        private set

    init {
        coroutine.launch {
            record = GameDataStoreManager.Record.get() ?: 0
            log("Store record = $record")
        }
    }

    fun update(value: Int) {
        if (value > record) {
            coroutine.launch {
                record = value

                when {
                    record in 10_000..19_999 -> 2
                    record in 20_000..29_999 -> 3
                    record >= 30_000               -> 4
                    else                           -> 1
                }.also { level: Int ->
                    levelUtil.update(level)
                }

                log("Store record update = $record")
                GameDataStoreManager.Record.update { record }
            }
        }
    }

}