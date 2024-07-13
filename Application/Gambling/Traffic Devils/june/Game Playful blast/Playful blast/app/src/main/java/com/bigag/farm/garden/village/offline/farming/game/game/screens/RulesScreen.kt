package com.bigag.farm.garden.village.offline.farming.game.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.bigag.farm.garden.village.offline.farming.game.game.LibGDXGame
import com.bigag.farm.garden.village.offline.farming.game.game.actors.AButton
import com.bigag.farm.garden.village.offline.farming.game.game.utils.TIME_ANIM
import com.bigag.farm.garden.village.offline.farming.game.game.utils.actor.animHide
import com.bigag.farm.garden.village.offline.farming.game.game.utils.advanced.AdvancedScreen
import com.bigag.farm.garden.village.offline.farming.game.game.utils.advanced.AdvancedStage
import com.bigag.farm.garden.village.offline.farming.game.game.utils.region

class RulesScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val rules = Image(game.assets.RIOPLS)
    private val back  = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        setBackBackground(game.splash.Splash.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(rules.apply { setBounds(68f, 443f, 927f, 1355f) })

        addActor(back)
        back.apply {
            setBounds(161f, 130f, 741f, 183f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

    }

}