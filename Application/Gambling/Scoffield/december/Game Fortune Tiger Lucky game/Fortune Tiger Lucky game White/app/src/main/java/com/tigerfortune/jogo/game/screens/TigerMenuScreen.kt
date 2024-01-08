package com.tigerfortune.jogo.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.tigerfortune.jogo.game.LibGDXGame
import com.tigerfortune.jogo.game.actors.checkbox.ACheckBox
import com.tigerfortune.jogo.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.tigerfortune.jogo.game.utils.actor.animHide
import com.tigerfortune.jogo.game.utils.actor.animShow
import com.tigerfortune.jogo.game.utils.actor.setOnClickListener
import com.tigerfortune.jogo.game.utils.advanced.AdvancedScreen
import com.tigerfortune.jogo.game.utils.advanced.AdvancedStage
import com.tigerfortune.jogo.game.utils.region

class TigerMenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.gameAssets.TWO_TIGER.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addMusic()
        addMenu()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addMusic() {
        val music = ACheckBox(this@TigerMenuScreen, ACheckBox.Static.Type.MUSIC)
        addActor(music)
        music.setBounds(887f, 1721f, 177f, 176f)

        game.musicUtil.music?.let { mmm ->
            if (mmm.isPlaying.not()) music.check(false)

            music.setOnCheckListener { if (it) mmm.pause() else mmm.play() }
        }
    }

    private fun AdvancedStage.addMenu() {
        val menuBar = Image(game.gameAssets.PANEL_MENU)
        addActor(menuBar)
        menuBar.setBounds(51f, 406f, 979f, 1236f)

        val names = listOf(
            TigerChooseScreen::class.java.name,
            TigerRulesScreen::class.java.name,
            TigerSettingsScreen::class.java.name,
            "exit",
        )

        var ny = 1158f

        names.onEach { sName ->
            val btn = Actor()
            addActor(btn)
            btn.setBounds(334f, ny, 419f, 204f)
            ny -= 23f+204f

            btn.setOnClickListener(game.soundUtil) { navigateGo(sName) }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun navigateGo(sName: String) {
        stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
            if (sName=="exit") game.navigationManager.exit()
            else game.navigationManager.navigate(sName, TigerMenuScreen::class.java.name)
        }
    }


}