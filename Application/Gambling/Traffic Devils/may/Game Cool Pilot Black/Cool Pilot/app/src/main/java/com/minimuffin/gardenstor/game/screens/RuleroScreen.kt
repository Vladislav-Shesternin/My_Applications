package com.minimuffin.gardenstor.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.minimuffin.gardenstor.game.SuberGame
import com.minimuffin.gardenstor.game.actors.button.AButton
import com.minimuffin.gardenstor.game.utils.actor.animHide
import com.minimuffin.gardenstor.game.utils.actor.animShow
import com.minimuffin.gardenstor.game.utils.actor.setBounds
import com.minimuffin.gardenstor.game.utils.actor.setOnClickListener
import com.minimuffin.gardenstor.game.utils.advanced.AdvancedScreen
import com.minimuffin.gardenstor.game.utils.advanced.AdvancedStage
import com.minimuffin.gardenstor.game.utils.region
import com.minimuffin.gardenstor.game.utils.vremia_ANIM

class RuleroScreen(override val game: SuberGame): AdvancedScreen() {

    // Actor
    private val aBack = AButton(this, AButton.Static.Type.mEnU)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.fisters.zagruzon.region)
        super.show()
        stageUI.root.animShow(vremia_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(Image(game.assets.welcome).apply {
            setBounds(340f, 187f, 918f, 682f)
        })

        addBack()
    }

    // ------------------------------------------------------------------------
    // Create Add Actor
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addBack() {
        addActors(aBack)

        aBack.apply {
            setBounds(96f, 458f, 156f, 92f)
            setOnClickListener {
                stageUI.root.animHide(vremia_ANIM) { game.navigationManager.back() }
            }
        }
    }

}