package com.centurygames.idlecouri.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.centurygames.idlecouri.game.LibGDXGame
import com.centurygames.idlecouri.game.actors.AButton
import com.centurygames.idlecouri.game.utils.TIME_ANIM
import com.centurygames.idlecouri.game.utils.actor.animHide
import com.centurygames.idlecouri.game.utils.actor.animShow
import com.centurygames.idlecouri.game.utils.actor.setOnClickListener
import com.centurygames.idlecouri.game.utils.advanced.AdvancedScreen
import com.centurygames.idlecouri.game.utils.advanced.AdvancedStage
import com.centurygames.idlecouri.game.utils.region

class RulesScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val rules = Image(game.alpha.ghg)
    private val back  = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.lobster.lister.random().region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(rules)
        rules.setBounds(38f, 255f, 552f, 816f)

        addActor(back)
        back.apply {
            setBounds(156f, 82f, 315f, 92f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

    }

}