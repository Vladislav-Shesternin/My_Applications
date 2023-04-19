package com.veldan.junglego.advanced

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen

abstract class AdvancedGame: ApplicationListener {

    private var backScreen: Screen? = null
    private var frontScreen: Screen? = null

    var screen: Screen? = null
        set(value) {
            field = value

            frontScreen?.let { backScreen = it }
            frontScreen = value
            frontScreen?.apply {
                show()
                resize(Gdx.graphics.width, Gdx.graphics.height)
            }
        }




    override fun render() {
        backScreen?.render(Gdx.graphics.deltaTime)
        frontScreen?.render(Gdx.graphics.deltaTime)

        if (backScreen != null) {
            backScreen!!.hide()
            backScreen = null
        }
    }

    override fun resize(width: Int, height: Int) {
        frontScreen?.resize(width, height)
    }

    override fun pause() {
        frontScreen?.pause()
    }

    override fun resume() {
        frontScreen?.resume()
    }

    override fun dispose() {
        frontScreen?.hide()
    }

}