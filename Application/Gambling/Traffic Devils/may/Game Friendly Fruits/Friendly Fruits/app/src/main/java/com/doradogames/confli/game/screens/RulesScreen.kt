package com.doradogames.confli.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.doradogames.confli.game.LibGDXGame
import com.doradogames.confli.game.actors.AButton
import com.doradogames.confli.game.utils.TIME_ANIM
import com.doradogames.confli.game.utils.actor.animHide
import com.doradogames.confli.game.utils.advanced.AdvancedScreen
import com.doradogames.confli.game.utils.advanced.AdvancedStage
import com.doradogames.confli.game.utils.region

class RulesScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val rules = Image(game.assets.rules)
    private val back  = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        setBackBackground(game.load.Loading.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(rules)
        rules.setBounds(107f, 465f, 850f, 1274f)

        addActor(back)
        back.apply {
            setBounds(204f, 190f, 656f, 187f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }
    }

}