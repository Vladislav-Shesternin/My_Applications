package com.doradogames.conflictnations.worldwar.game.utils.dataStore

import com.doradogames.conflictnations.worldwar.game.manager.GameDataStoreManager
import com.doradogames.conflictnations.worldwar.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LevelUtil(val coroutine: CoroutineScope) {

    var level = 0
        private set

    init {
        coroutine.launch {
            level = GameDataStoreManager.Level.get() ?: 1
            log("Store level = $level")
        }
    }

    fun update(newLevel: Int) {
        coroutine.launch {
            if (newLevel <= 4) level = newLevel
            log("Store level update = $level")
            GameDataStoreManager.Level.update { level }
        }
    }

}