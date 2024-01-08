package com.tigerfortune.jogo.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.tigerfortune.jogo.game.LibGDXGame
import com.tigerfortune.jogo.game.actors.button.AButton
import com.tigerfortune.jogo.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.tigerfortune.jogo.game.utils.actor.animHide
import com.tigerfortune.jogo.game.utils.actor.animShow
import com.tigerfortune.jogo.game.utils.actor.setOnClickListener
import com.tigerfortune.jogo.game.utils.advanced.AdvancedScreen
import com.tigerfortune.jogo.game.utils.advanced.AdvancedStage
import com.tigerfortune.jogo.game.utils.region

class TigerChooseScreen(override val game: LibGDXGame) : AdvancedScreen() {

    companion object {
        var TIGER_TYPE = TigerType.TIGER_1
            private set
        var BACKGROUND_TYPE = BackgroundType.B2
            private set
    }


    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.gameAssets.TIGERCHOOSE.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addBack()
        addCharacter()
        addBackground()
        addStart()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addBack() {
        val menu = AButton(this@TigerChooseScreen, AButton.Static.Type.MENU)
        addActor(menu)
        menu.setBounds(45f, 1721f, 177f, 176f)

        menu.setOnClickListener(game.soundUtil) {
            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }
        }
    }

    private fun AdvancedStage.addCharacter() {
        val tiger1 = Actor()
        val tiger2 = Actor()

        addActors(tiger1, tiger2)

        tiger1.apply {
            setBounds(24f, 1127f, 455f, 377f)
            setOnClickListener(game.soundUtil) { TIGER_TYPE = TigerType.TIGER_1 }
        }
        tiger2.apply {
            setBounds(596f, 1127f, 455f, 377f)
            setOnClickListener(game.soundUtil) { TIGER_TYPE = TigerType.TIGER_2 }
        }
    }

    private fun AdvancedStage.addBackground() {
        var nx = 33f
        var ny = 602f

        repeat(4) { i ->
            Actor().also { a ->
                addActor(a)
                a.setBounds(nx, ny, 455f, 377f)
                nx += 106f+455f
                if (i.inc() % 2 == 0) {
                    nx = 33f
                    ny -= 49f+377f
                }
                a.setOnClickListener(game.soundUtil) {
                    BACKGROUND_TYPE = BackgroundType.values()[i]
                }
            }
        }
    }

    private fun AdvancedStage.addStart() {
        val start = Actor()
        addActor(start)
        start.setBounds(412f, 15f, 258f, 126f)

        start.setOnClickListener(game.soundUtil) {
            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(TigerGameScreen::class.java.name, TigerChooseScreen::class.java.name) }
        }
    }

    // ---------------------------------------------------
    // classes
    // ---------------------------------------------------

    enum class TigerType {
        TIGER_1, TIGER_2
    }

    enum class BackgroundType {
        B1, B2, B3, B4
    }

}