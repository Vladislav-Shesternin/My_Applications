package com.funsagon.hexagon.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.funsagon.hexagon.game.LibGDXGame
import com.funsagon.hexagon.game.utils.TIME_ANIM
import com.funsagon.hexagon.game.utils.actor.animHide
import com.funsagon.hexagon.game.utils.actor.animShow
import com.funsagon.hexagon.game.utils.actor.setOnClickListener
import com.funsagon.hexagon.game.utils.advanced.AdvancedScreen
import com.funsagon.hexagon.game.utils.advanced.AdvancedStage
import com.funsagon.hexagon.game.utils.region

class HexagonMenuScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val panelImg = Image(game.allAssets.menu)

    override fun show() {
        stageUI.root.animHide(TIME_ANIM)
        setBackBackground(game.startAssets.BACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addPanel()
        addButtons()
        addExit()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedStage.addPanel() {
        addActors(panelImg)
        panelImg.setBounds(519f, 234f, 882f, 726f)
    }

    private fun AdvancedStage.addButtons() {
        var ny = 355f
        arrayOf(
            HexagonRulesScreen::class.java.name,
            HexagonSettingsScreen::class.java.name,
            HexagonMapScreen::class.java.name,
        ).onEach { sName ->
            addActor(Actor().apply {
                setBounds(751f, ny, 430f, 105f)
                ny += (30+105)

                setOnClickListener(game.soundUtil) {
                    stageUI.root.animHide(TIME_ANIM) {
                        game.navigationManager.navigate(sName, HexagonMenuScreen::class.java.name)
                    }
                }
            })
        }
    }

    private fun AdvancedStage.addExit() {
        val exit = Actor()
        addActors(exit)
        exit.setBounds(1227f, 234f, 130f, 133f)
        exit.setOnClickListener(game.soundUtil) {
            stageUI.root.animHide(TIME_ANIM) { game.navigationManager.exit() }
        }
    }

}