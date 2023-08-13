package com.zeuse.zeusjackpotjamboree.game.utils.advanced

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.scenes.scene2d.actions.Actions.show
import com.zeuse.zeusjackpotjamboree.game.utils.runGDX
import com.zeuse.zeusjackpotjamboree.util.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class AdvancedGame: ApplicationListener {

    private var backScreen: AdvancedScreen? = null
    private var frontScreen: AdvancedScreen? = null

    var screen: AdvancedScreen? = null
        set(value) {
            field = value

            backScreen?.let { frontScreen = it }
            backScreen = value
            backScreen?.apply {
                show()
                resize(Gdx.graphics.width, Gdx.graphics.height)
            }
        }


    override fun render() {
        backScreen?.render(Gdx.graphics.deltaTime)
        frontScreen?.render(Gdx.graphics.deltaTime)

       if (frontScreen != null) {
           frontScreen!!.dispose()
           frontScreen = null
       }
    }

    override fun resize(width: Int, height: Int) {
        backScreen?.resize(width, height)
    }

    override fun pause() {
        backScreen?.pause()
    }

    override fun resume() {
        backScreen?.resume()
    }

    override fun dispose() {
        backScreen?.dispose()
    }

}