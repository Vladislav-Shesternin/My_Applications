package com.flaregames.olympusrisin.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.flaregames.olympusrisin.game.LibGDXGame
import com.flaregames.olympusrisin.game.actors.AButton
import com.flaregames.olympusrisin.game.utils.TIME_ANIM
import com.flaregames.olympusrisin.game.utils.actor.animHide
import com.flaregames.olympusrisin.game.utils.advanced.AdvancedScreen
import com.flaregames.olympusrisin.game.utils.advanced.AdvancedStage
import com.flaregames.olympusrisin.game.utils.region

class RulesScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val rules = Image(game.assets.rules)
    private val back  = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        setBackBackground(game.load.Loader.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(rules)
        rules.setBounds(175f, 206f, 1177f, 619f)

        addActor(back)
        back.apply {
            setBounds(529f, 26f, 469f, 147f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }
    }

}