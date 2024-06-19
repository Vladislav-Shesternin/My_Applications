package com.duckduckmoosedesign.cpkid.game.screens

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.duckduckmoosedesign.cpkid.game.LibGDXGame
import com.duckduckmoosedesign.cpkid.game.actors.AButton
import com.duckduckmoosedesign.cpkid.game.actors.AProgress
import com.duckduckmoosedesign.cpkid.game.utils.TIME_ANIM
import com.duckduckmoosedesign.cpkid.game.utils.actor.animHide
import com.duckduckmoosedesign.cpkid.game.utils.actor.animShow
import com.duckduckmoosedesign.cpkid.game.utils.actor.setBounds
import com.duckduckmoosedesign.cpkid.game.utils.advanced.AdvancedScreen
import com.duckduckmoosedesign.cpkid.game.utils.advanced.AdvancedStage
import com.duckduckmoosedesign.cpkid.game.utils.region
import com.duckduckmoosedesign.cpkid.game.utils.runGDX
import kotlinx.coroutines.launch

class RulesScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val rules = Image(game.allAss.rudles)
    private val back  = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.loadAss.BEDROOM.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(rules.apply { setBounds(733f, 249f, 708f, 550f) })

        addActor(back)
        back.apply {
            setBounds(1309f, 21f, 202f, 96f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

        addPHANAMERA()
    }

    private fun AdvancedStage.addPHANAMERA() {
        val img = Image(game.allAss.paramon)
        addActor(img)
        img.apply {
            setBounds(63f, 0f, 557f, 693f)
            addAction(
                Actions.forever(
                    Actions.sequence(
                Actions.moveTo(0f, 0f, 1f),
                Actions.moveTo(199f, 0f, 2f),
                Actions.moveTo(63f, 0f, 0.5f),
            )))
        }
    }

}