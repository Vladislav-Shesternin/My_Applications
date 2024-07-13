package com.earlymorningstudio.championsofa.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.earlymorningstudio.championsofa.game.LibGDXGame
import com.earlymorningstudio.championsofa.game.utils.TIME_ANIM
import com.earlymorningstudio.championsofa.game.utils.actor.animHide
import com.earlymorningstudio.championsofa.game.utils.actor.setOnClickListener
import com.earlymorningstudio.championsofa.game.utils.advanced.AdvancedScreen
import com.earlymorningstudio.championsofa.game.utils.advanced.AdvancedStage
import com.earlymorningstudio.championsofa.game.utils.region

class FortepianoPrivet(override val game: LibGDXGame): AdvancedScreen() {

    private val icon = Image(game.LICHIKO.uubm)

    override fun show() {
        setBackBackground(game.SRAKA.PUNDIC.region)
        super.show()
    }

    private fun hideScreen(block: () -> Unit) {
        stageUI.root.animHide(TIME_ANIM) { block.invoke() }
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(icon.apply {
            setBounds(1078f, -16f, 564f, 438f)
            setOrigin(Align.center)
            rotation = 22f
        })
        val dialogImg = Image(game.LICHIKO.fortepiano)
        addActor(dialogImg)
        dialogImg.setBounds(327f, 26f, 867f, 809f)

        val agreeActor    = Actor()
        val disagreeActor = Actor()

        addActors(agreeActor, disagreeActor)

        agreeActor.setBounds(802f, 26f, 295f, 156f)
        disagreeActor.setBounds(424f, 26f, 295f, 156f)

        agreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean("arjadasjdk", true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }
        disagreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean("arjadasjdk", true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }

    }

}