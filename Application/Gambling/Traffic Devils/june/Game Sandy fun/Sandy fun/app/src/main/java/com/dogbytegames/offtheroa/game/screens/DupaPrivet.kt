package com.dogbytegames.offtheroa.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.dogbytegames.offtheroa.game.LibGDXGame
import com.dogbytegames.offtheroa.game.utils.TIME_ANIM
import com.dogbytegames.offtheroa.game.utils.actor.animHide
import com.dogbytegames.offtheroa.game.utils.actor.setOnClickListener
import com.dogbytegames.offtheroa.game.utils.advanced.AdvancedScreen
import com.dogbytegames.offtheroa.game.utils.advanced.AdvancedStage
import com.dogbytegames.offtheroa.game.utils.region

class DupaPrivet(override val game: LibGDXGame): AdvancedScreen() {

    private val icon  = Image(game.aAlibaba.nubasina)

    override fun show() {
        setBackBackground(game.aLdnr.LDR.region)
        super.show()
    }

    private fun hideScreen(block: () -> Unit) {
        stageUI.root.animHide(TIME_ANIM) { block.invoke() }
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(icon.apply { setBounds(0f, 0f, 638f, 656f) })
        val dialogImg = Image(game.aAlibaba.dupa)
        addActor(dialogImg)
        dialogImg.setBounds(507f, 66f, 857f, 732f)

        val agreeActor    = Actor()
        val disagreeActor = Actor()

        addActors(agreeActor, disagreeActor)

        agreeActor.setBounds(1073f, 67f, 291f, 95f)
        disagreeActor.setBounds(684f, 67f, 291f, 95f)

        agreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean("dupa", true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }
        disagreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean("dupa", true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }

    }

}