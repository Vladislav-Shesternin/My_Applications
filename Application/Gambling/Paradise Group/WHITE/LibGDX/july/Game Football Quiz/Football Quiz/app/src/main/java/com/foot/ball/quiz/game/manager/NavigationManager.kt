package com.foot.ball.quiz.game.manager

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.foot.ball.quiz.game.game

object NavigationManager {

    private val backStack = mutableListOf<Screen>()
    var key: Int? = null
        private set

    fun navigate(to: Screen, from: Screen? = null, key: Int? = null) {
        Gdx.app.postRunnable {
            NavigationManager.key = key

            game.screen = to
            from?.let { backStack.add(it) }
            if (backStack.contains(to)) backStack.remove(to)
        }
    }

    fun back(key: Int? = null) {
        Gdx.app.postRunnable {
            NavigationManager.key = key

            if (backStack.isEmpty()) exit()
            else game.screen = backStack.removeLast().javaClass.newInstance()
        }
    }

    fun exit() {
        Gdx.app.postRunnable {
            Gdx.app.exit()
            game.activity.exit()
        }
    }

}