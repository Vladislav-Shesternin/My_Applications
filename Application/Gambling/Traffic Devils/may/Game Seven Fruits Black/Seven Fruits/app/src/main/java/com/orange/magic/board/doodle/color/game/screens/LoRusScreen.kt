package com.orange.magic.board.doodle.color.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.orange.magic.board.doodle.color.game.LidaGame
import com.orange.magic.board.doodle.color.game.actors.button.AButton
import com.orange.magic.board.doodle.color.game.utils.TIMI_TERNER
import com.orange.magic.board.doodle.color.game.utils.actor.animHide
import com.orange.magic.board.doodle.color.game.utils.actor.animShow
import com.orange.magic.board.doodle.color.game.utils.advanced.AdvancedScreen
import com.orange.magic.board.doodle.color.game.utils.advanced.AdvancedStage
import com.orange.magic.board.doodle.color.game.utils.region

class LoRusScreen(override val game: LidaGame): AdvancedScreen() {

    // Actor
    private val aMenu = AButton(
        this,
        AButton.Static.Type.MNU
    )

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.dgop.meduna.region)
        super.show()
        stageUI.root.animShow(TIMI_TERNER)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(Image(game.simagoche.rubeliy).apply {
            setBounds(357f, 1317f, 365f, 361f)
        })


        addActor(Image(game.simagoche.musishion).apply {
            setBounds(133f, 197f, 813f, 928f)
        })

        addMenu()
    }

    // ------------------------------------------------------------------------
    // Create Add Actor
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addMenu() {
        addActors(aMenu)

        aMenu.apply {
            setBounds(34f, 1773f, 112f, 112f)
            setOnClickListener {
                stageUI.root.animHide(TIMI_TERNER) { game.navigationManager.back() }
            }
        }
    }

}