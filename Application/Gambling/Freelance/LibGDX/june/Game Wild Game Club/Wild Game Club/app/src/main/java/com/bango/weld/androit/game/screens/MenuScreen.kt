package com.bango.weld.androit.game.screens

import android.app.Notification.Action
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.bango.weld.androit.game.actors.checkbox.ACheckBox
import com.bango.weld.androit.game.actors.checkbox.ACheckBoxStyle
import com.bango.weld.androit.game.manager.NavigationManager
import com.bango.weld.androit.game.manager.SpriteManager
import com.bango.weld.androit.game.musicUtil
import com.bango.weld.androit.game.utils.actor.setOnClickListener
import com.bango.weld.androit.game.utils.advanced.AdvancedGroup
import com.bango.weld.androit.game.utils.advanced.AdvancedScreen

class MenuScreen: AdvancedScreen() {
    companion object {
        private var isFirstOpen = true
        private var isPauseMusic = false
    }

    private val backgran = Image(SpriteManager.GameRegion.BADABPUMBA.region)
    private val musicBox = ACheckBox(ACheckBoxStyle.music)
    private val knopkice = Image(SpriteManager.GameRegion.EXITPLAY.region)

    override fun show() {
        super.show()

        if (isFirstOpen) {
            isFirstOpen = false
            musicUtil.apply { music = MAIN.apply { isLooping = true } }
        }
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addBackgran()
        addMusic()
        addKnopkice()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addBackgran() {
        addActor(backgran)

        backgran.apply {
            setBounds(0f, -44f, 1754f, 856f)

            addAction(Actions.forever(Actions.sequence(
                Actions.moveBy(0f, 44f, 3f),
                Actions.moveBy(0f, -88f, 6f),
                Actions.moveBy(0f, 44f, 3f),
            )))
        }
    }

    private fun AdvancedGroup.addKnopkice() {
        addActor(knopkice)

        knopkice.apply {
            setBounds(105f, 244f, 1543f, 281f)
        }

        val ext = Actor()
        val ply = Actor()

        addActors(ext, ply)

        ext.apply {
            setBounds(105f, 244f, 404f, 281f)
            setOnClickListener { NavigationManager.exit() }
        }
        ply.apply {
            setBounds(1244f, 244f, 404f, 281f)
            setOnClickListener { NavigationManager.navigate(IgraScreen(), MenuScreen()) }
        }

    }
    private fun AdvancedGroup.addMusic() {
        addActor(musicBox)

        musicBox.apply {
            setBounds(797f, 107f, 159f, 159f)

            if (isPauseMusic) check() else uncheck()

            setOnCheckListener {
                if (it) {
                    isPauseMusic = true
                    musicUtil.music?.pause()
                } else {
                    isPauseMusic = false
                    musicUtil.music?.play()
                }
            }
        }
    }

}