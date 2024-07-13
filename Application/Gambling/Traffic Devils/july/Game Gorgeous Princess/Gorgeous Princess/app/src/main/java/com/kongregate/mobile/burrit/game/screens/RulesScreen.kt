package com.kongregate.mobile.burrit.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.kongregate.mobile.burrit.game.LibGDXGame
import com.kongregate.mobile.burrit.game.actors.AButton
import com.kongregate.mobile.burrit.game.utils.TIME_ANIM
import com.kongregate.mobile.burrit.game.utils.actor.animHide
import com.kongregate.mobile.burrit.game.utils.actor.animShow
import com.kongregate.mobile.burrit.game.utils.actor.setOnClickListener
import com.kongregate.mobile.burrit.game.utils.advanced.AdvancedScreen
import com.kongregate.mobile.burrit.game.utils.advanced.AdvancedStage
import com.kongregate.mobile.burrit.game.utils.region

class RulesScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val rules = Image(game.alpha.rluse)
    private val back  = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.lobster.bacMini.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(rules)
        rules.setBounds(63f, 453f, 954f, 1331f)

        addActor(back)
        back.apply {
            setBounds(348f, 96f, 384f, 155f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

    }

}