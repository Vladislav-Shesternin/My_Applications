package com.centurygames.idlecouri.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.centurygames.idlecouri.game.LibGDXGame
import com.centurygames.idlecouri.game.utils.TIME_ANIM
import com.centurygames.idlecouri.game.utils.actor.animHide
import com.centurygames.idlecouri.game.utils.actor.animShow
import com.centurygames.idlecouri.game.utils.actor.setOnClickListener
import com.centurygames.idlecouri.game.utils.advanced.AdvancedScreen
import com.centurygames.idlecouri.game.utils.advanced.AdvancedStage
import com.centurygames.idlecouri.game.utils.region

class WelcomeScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val img = Image(game.alpha.text).apply { setSize(370f, 1055f) }
    private val scr = ScrollPane(img)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.lobster.lister.random().region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    private fun hideScreen(block: () -> Unit) {
        stageUI.root.animHide(TIME_ANIM) { block.invoke() }
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        val dialogImg = Image(game.alpha.Welcome)
        addActor(dialogImg)
        dialogImg.setBounds(38f, 109f, 552f, 962f)

        addActor(scr)
        scr.setBounds(49f, 307f, 531f, 529f)

        val agreeActor    = Actor()
        val disagreeActor = Actor()

        addActors(agreeActor, disagreeActor)

        agreeActor.setBounds(48f, 111f, 248f, 74f)
        disagreeActor.setBounds(318f, 111f, 248f, 74f)

        agreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean("PERliNA", true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }
        disagreeActor.setOnClickListener(game.soundUtil) {
            game.prefsDialog.edit().putBoolean("PERliNA", true).apply()
            hideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }

    }

}