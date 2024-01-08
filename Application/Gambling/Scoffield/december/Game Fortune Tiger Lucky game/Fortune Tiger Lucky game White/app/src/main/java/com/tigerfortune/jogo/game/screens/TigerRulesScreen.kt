package com.tigerfortune.jogo.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.tigerfortune.jogo.game.LibGDXGame
import com.tigerfortune.jogo.game.actors.button.AButton
import com.tigerfortune.jogo.game.actors.checkbox.ACheckBox
import com.tigerfortune.jogo.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.tigerfortune.jogo.game.utils.actor.animHide
import com.tigerfortune.jogo.game.utils.actor.animShow
import com.tigerfortune.jogo.game.utils.actor.setOnClickListener
import com.tigerfortune.jogo.game.utils.advanced.AdvancedScreen
import com.tigerfortune.jogo.game.utils.advanced.AdvancedStage
import com.tigerfortune.jogo.game.utils.region

class TigerRulesScreen(override val game: LibGDXGame) : AdvancedScreen() {

    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.gameAssets.TWO_TIGER.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addBack()
        addMusic()
        addRules()
        addOk()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addBack() {
        val menu = AButton(this@TigerRulesScreen, AButton.Static.Type.MENU)
        addActor(menu)
        menu.setBounds(45f, 1721f, 177f, 176f)

        menu.setOnClickListener(game.soundUtil) {
            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }
        }
    }

    private fun AdvancedStage.addMusic() {
        val music = ACheckBox(this@TigerRulesScreen, ACheckBox.Static.Type.MUSIC)
        addActor(music)
        music.setBounds(887f, 1721f, 177f, 176f)

        game.musicUtil.music?.let { mmm ->
            if (mmm.isPlaying.not()) music.check(false)

            music.setOnCheckListener { if (it) mmm.pause() else mmm.play() }
        }
    }

    private fun AdvancedStage.addRules() {
        val rules = Image(game.gameAssets.PANEL_RULES)
        addActor(rules)
        rules.setBounds(51f, 406f, 979f, 1236f)
    }

    private fun AdvancedStage.addOk() {
        val ok = AButton(this@TigerRulesScreen, AButton.Static.Type.OK)
        addActor(ok)
        ok.setBounds(412f, 15f, 258f, 126f)

        ok.setOnClickListener(game.soundUtil) {
            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }
        }
    }

}