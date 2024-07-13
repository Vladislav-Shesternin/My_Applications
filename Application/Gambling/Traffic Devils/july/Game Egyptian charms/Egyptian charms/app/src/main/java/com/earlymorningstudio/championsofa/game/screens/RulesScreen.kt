package com.earlymorningstudio.championsofa.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.earlymorningstudio.championsofa.game.LibGDXGame
import com.earlymorningstudio.championsofa.game.actors.AButton
import com.earlymorningstudio.championsofa.game.utils.TIME_ANIM
import com.earlymorningstudio.championsofa.game.utils.actor.animHide
import com.earlymorningstudio.championsofa.game.utils.advanced.AdvancedScreen
import com.earlymorningstudio.championsofa.game.utils.advanced.AdvancedStage
import com.earlymorningstudio.championsofa.game.utils.region

class RulesScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val icon  = Image(game.LICHIKO.uubm)
    private val rules = Image(game.LICHIKO.pzqfgm)
    private val back  = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        setBackBackground(game.SRAKA.PUNDIC.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(icon.apply { setBounds(-28f, -5f, 788f, 611f) })

        addActor(rules)
        rules.setBounds(612f, 241f, 867f, 611f)

        addActor(back)
        back.apply {
            setBounds(1184f, 25f, 295f, 156f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

    }

}