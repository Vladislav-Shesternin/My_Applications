package com.hgrt.wrld.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.hgrt.wrld.game.actors.checkbox.CheckBox
import com.hgrt.wrld.game.actors.checkbox.CheckBoxStyle
import com.hgrt.wrld.game.actors.slot.SlotGroup
import com.hgrt.wrld.game.manager.NavigationManager
import com.hgrt.wrld.game.manager.SpriteManager
import com.hgrt.wrld.game.util.advanced.AdvancedScreen
import com.hgrt.wrld.game.util.advanced.AdvancedStage
import com.hgrt.wrld.game.util.setOnClickListener
import com.hgrt.wrld.game.util.Layout.Game as LG

class MenuScreen: AdvancedScreen() {

    private val btnsImg = Image(SpriteManager.GameRegion.PLAY_EXIT.region)
    private val musicCB = CheckBox(CheckBoxStyle.music)

    override fun show() {
        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStage() {
        addSlotGroup()
        addMusicCB()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addSlotGroup() {
        addActor(btnsImg)
        btnsImg.setBounds(453f, 140f, 495f, 468f)

        val play = Actor()
        val exit = Actor()

        addActors(play, exit)

        play.apply {
            setBounds(490f, 440f, 422f, 135f)
            setOnClickListener { NavigationManager.navigate(GameScreen(), MenuScreen()) }
        }
        exit.apply {
            setBounds(573f, 160f, 254f, 81f)
            setOnClickListener { NavigationManager.exit() }
        }
    }

    private fun AdvancedStage.addMusicCB() {
        addActor(musicCB)
        musicCB.setBounds(524f, 262f, 352f, 143f)

        musicUtil?.music?.let { mmm ->
            if (mmm.isPlaying.not()) musicCB.check()

            musicCB.setOnCheckListener { if (it) mmm.pause() else mmm.play() }
        }
    }

}