package com.YovoGames.magicBo.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.YovoGames.magicBo.game.LibGDXGame
import com.YovoGames.magicBo.game.utils.TIME_ANIM
import com.YovoGames.magicBo.game.utils.WIDTH_UI
import com.YovoGames.magicBo.game.utils.actor.animHide
import com.YovoGames.magicBo.game.utils.actor.animShow
import com.YovoGames.magicBo.game.utils.actor.setOnClickListener
import com.YovoGames.magicBo.game.utils.advanced.AdvancedScreen
import com.YovoGames.magicBo.game.utils.advanced.AdvancedStage
import com.YovoGames.magicBo.game.utils.region

class Rom(override val game: LibGDXGame): AdvancedScreen() {

    override fun show() {
        stageUI.root.x = -WIDTH_UI
        setBackBackground(game.surgut.Soloha.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    private fun hideScreen(block: () -> Unit) {
        stageUI.root.animHide(TIME_ANIM) { block.invoke() }
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        val dialogImg = Image(game.jakarta.rom)
        addActor(dialogImg)
        dialogImg.setBounds(32f, 37f, 477f, 892f)

        val agreeActor    = Actor()
        val disagreeActor = Actor()

        addActors(agreeActor, disagreeActor)

        agreeActor.setBounds(127f, 113f, 222f, 68f)
        disagreeActor.setBounds(127f, 68f, 222f, 68f)

        agreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean("ooo(AsidaSdj", true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }
        disagreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean("ooo(AsidaSdj", true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }

    }

}