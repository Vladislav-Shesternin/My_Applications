package com.bricks.vs.balls.game.utils.dataStoreUtil

import com.bricks.vs.balls.game.manager.GameDataStoreManager
import com.bricks.vs.balls.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class IsTutorialsUtil(val coroutine: CoroutineScope) {

    var isTutorials = false
        private set

    init {
        coroutine.launch {
            isTutorials = GameDataStoreManager.IsTutorials.get() ?: true
            log("Store isTutorials = $isTutorials")
        }
    }

    fun update(flag: Boolean) {
        coroutine.launch {
            isTutorials = flag
            log("Store isTutorials update = $isTutorials")
            GameDataStoreManager.IsTutorials.update { isTutorials }
        }
    }

}