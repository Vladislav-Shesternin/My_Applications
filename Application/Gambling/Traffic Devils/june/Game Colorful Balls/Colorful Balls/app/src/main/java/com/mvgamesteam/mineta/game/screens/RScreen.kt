package com.mvgamesteam.mineta.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.mvgamesteam.mineta.game.LibGDXGame
import com.mvgamesteam.mineta.game.actors.AButton
import com.mvgamesteam.mineta.game.utils.TIME_ANIM
import com.mvgamesteam.mineta.game.utils.actor.animHide
import com.mvgamesteam.mineta.game.utils.advanced.AdvancedScreen
import com.mvgamesteam.mineta.game.utils.advanced.AdvancedStage
import com.mvgamesteam.mineta.game.utils.region

class RScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val rules = Image(game.Jer.CLRFL)
    private val back  = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        setBackBackground(game.Sap.Splash.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(rules)
        rules.setBounds(99f, 237f, 1324f, 574f)

        addActor(back)
        back.apply {
            setBounds(1255f, 19f, 234f, 125f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

    }

}