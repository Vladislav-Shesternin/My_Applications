package com.bricks.vs.balls.game.utils.dataStoreUtil

import com.bricks.vs.balls.game.manager.GameDataStoreManager
import com.bricks.vs.balls.util.log
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
            if (newLevel <= 10) level = newLevel
            log("Store level update = $level")
            GameDataStoreManager.Level.update { level }
        }
    }

}