package com.rcks.scaloi.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.rcks.scaloi.game.actors.checkbox.CheckBox
import com.rcks.scaloi.game.actors.checkbox.CheckBoxStyle
import com.rcks.scaloi.game.manager.NavigationManager
import com.rcks.scaloi.game.manager.SpriteManager
import com.rcks.scaloi.game.util.advanced.AdvancedScreen
import com.rcks.scaloi.game.util.advanced.AdvancedStage
import com.rcks.scaloi.game.util.listeners.toClickable

class SettingsScreen: AdvancedScreen() {

    private val snow1      = Image(SpriteManager.CommonRegion.SNOW.region)
    private val snow2      = Image(SpriteManager.CommonRegion.SNOW.region)

    override fun show() {
        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStage() {
        addActors(snow1, snow2)

        snow1.setBounds(0f, 0f, com.rcks.scaloi.game.util.WIDTH, com.rcks.scaloi.game.util.HEIGHT)
        snow2.setBounds(0f,
            com.rcks.scaloi.game.util.HEIGHT,
            com.rcks.scaloi.game.util.WIDTH,
            com.rcks.scaloi.game.util.HEIGHT
        )

        snow1.addAction(Actions.forever(Actions.sequence(
            Actions.moveBy(0f, -com.rcks.scaloi.game.util.HEIGHT, 10f),
            Actions.moveBy(0f, com.rcks.scaloi.game.util.HEIGHT, 0f),
        )))
        snow2.addAction(Actions.forever(Actions.sequence(
            Actions.moveBy(0f, -com.rcks.scaloi.game.util.HEIGHT, 10f),
            Actions.moveBy(0f, com.rcks.scaloi.game.util.HEIGHT, 0f),
        )))

        addActorsek()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addActorsek() {
        val back = Image(SpriteManager.GameRegion.BACK.region)
        addActor(back)
        back.apply {
            setBounds(751f, 87f, 417f, 117f)
            toClickable().setOnClickListener { NavigationManager.back() }
        }

        val music = CheckBox(CheckBoxStyle.music)
        val sound = CheckBox(CheckBoxStyle.sound)
        addActors(music, sound)
        music.apply {
            if (isMusic.not()) check()
            setBounds(677f, 560f, 564f, 159f)
            setOnCheckListener {
                isMusic = !it
                if (isMusic) musical?.play() else musical?.pause()
            }
        }
        sound.apply {
            if (isSound.not()) check()
            setBounds(677f, 362f, 564f, 159f)
            setOnCheckListener { isSound = !it }
        }

    }

}