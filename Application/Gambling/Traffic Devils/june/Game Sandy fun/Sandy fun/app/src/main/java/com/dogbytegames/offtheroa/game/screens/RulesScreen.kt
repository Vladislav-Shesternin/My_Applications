package com.dogbytegames.offtheroa.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.dogbytegames.offtheroa.game.LibGDXGame
import com.dogbytegames.offtheroa.game.actors.AButton
import com.dogbytegames.offtheroa.game.utils.TIME_ANIM
import com.dogbytegames.offtheroa.game.utils.actor.animHide
import com.dogbytegames.offtheroa.game.utils.advanced.AdvancedScreen
import com.dogbytegames.offtheroa.game.utils.advanced.AdvancedStage
import com.dogbytegames.offtheroa.game.utils.region

class RulesScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val icon  = Image(game.aAlibaba.nubasina)
    private val rules = Image(game.aAlibaba.vegasina)
    private val back  = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        setBackBackground(game.aLdnr.LDR.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(icon.apply { setBounds(0f, 0f, 638f, 656f) })

        addActor(rules)
        rules.setBounds(507f, 229f, 856f, 569f)

        addActor(back)
        back.apply {
            setBounds(915f, 50f, 433f, 142f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

    }

}