package com.internetdes.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.internetdes.game.LibGDXGame
import com.internetdes.game.actors.AButton
import com.internetdes.game.utils.TIME_ANIM
import com.internetdes.game.utils.actor.animHide
import com.internetdes.game.utils.advanced.AdvancedScreen
import com.internetdes.game.utils.advanced.AdvancedStage
import com.internetdes.game.utils.region

class RulesScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val rules = Image(game.aALL.COLP)
    private val back  = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        setBackBackground(game.aLOA.BAGRATION.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(rules)
        rules.setBounds(0f, 0f, 1527f, 887f)

        addActor(back)
        back.apply {
            setBounds(44f, 702f, 185f, 185f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

    }

}