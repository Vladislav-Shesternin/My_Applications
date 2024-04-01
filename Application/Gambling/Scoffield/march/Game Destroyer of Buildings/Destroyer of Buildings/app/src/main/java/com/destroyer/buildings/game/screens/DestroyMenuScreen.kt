package com.destroyer.buildings.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.destroyer.buildings.game.LibGDXGame
import com.destroyer.buildings.game.utils.TIME_ANIM
import com.destroyer.buildings.game.utils.actor.animHide
import com.destroyer.buildings.game.utils.actor.animShow
import com.destroyer.buildings.game.utils.actor.setOnClickListener
import com.destroyer.buildings.game.utils.advanced.AdvancedScreen
import com.destroyer.buildings.game.utils.advanced.AdvancedStage
import com.destroyer.buildings.game.utils.region

class DestroyMenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val menuImg = Image(game.allAssets.MENU)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.loaderAssets.BUILDINGS_AREA.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addMenuImg()
        addExit()
        addBtns()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedStage.addMenuImg() {
        addActor(menuImg)
        menuImg.setBounds(226f, 353f, 1468f, 452f)
    }

    private fun AdvancedStage.addExit() {
        val exit = Actor()
        addActor(exit)
        exit.setBounds(828f, 353f, 264f, 137f)
        exit.setOnClickListener(game.soundUtil) {
            stageUI.root.animHide(TIME_ANIM) { game.navigationManager.exit() }
        }
    }

    private fun AdvancedStage.addBtns() {
        var nx = 226f

        listOf(
            DestroyGameScreen_1::class.java.name,
            DestroyGameScreen_2::class.java.name,
            DestroyGameScreen_3::class.java.name,
        ).onEach { className ->
            val actor = Actor()
            addActor(actor)
            actor.setBounds(nx, 567f, 456f, 238f)
            nx += 50+456
            actor.setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(className, DestroyMenuScreen::class.java.name)
                }
            }
        }
    }

}