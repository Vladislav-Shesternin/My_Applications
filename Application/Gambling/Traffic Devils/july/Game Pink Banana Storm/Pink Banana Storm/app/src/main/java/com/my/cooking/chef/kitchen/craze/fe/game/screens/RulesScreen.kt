package com.my.cooking.chef.kitchen.craze.fe.game.screens

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.my.cooking.chef.kitchen.craze.fe.game.LibGDXGame
import com.my.cooking.chef.kitchen.craze.fe.game.utils.TIME_ANIM
import com.my.cooking.chef.kitchen.craze.fe.game.utils.actor.animHide
import com.my.cooking.chef.kitchen.craze.fe.game.utils.actor.animShow
import com.my.cooking.chef.kitchen.craze.fe.game.utils.actor.setOnClickListener
import com.my.cooking.chef.kitchen.craze.fe.game.utils.advanced.AdvancedScreen
import com.my.cooking.chef.kitchen.craze.fe.game.utils.advanced.AdvancedStage
import com.my.cooking.chef.kitchen.craze.fe.game.utils.region
import com.badlogic.gdx.scenes.scene2d.actions.Actions

class RulesScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val rules = Image(game.allOl.TEXESTEREWSWE)
    private val back  = Image(game.allOl.menu)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.loal.listB.random().region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(rules)
        rules.setBounds(0f, 357f, 540f, 543f)

        addActor(back)
        back.apply {
            setBounds(10f, 886f, 65f, 62f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

        val deva = Image(game.allOl.deva)
        val frtu = Image(game.allOl.fruitcandy)
        addActors(deva, frtu)
        deva.apply {
            setBounds(-24f, -46f, 269f, 433f)
            addAction(
                Actions.forever(
                    Actions.sequence(
                Actions.moveBy(15f, 30f, 3f, Interpolation.swingOut),
                Actions.moveBy(-15f, -30f, 3f, Interpolation.swing),
            )))
        }
        frtu.apply {
            setBounds(270f, -47f, 344f, 321f)
            addAction(
                Actions.forever(
                    Actions.sequence(
                Actions.moveBy(0f, 30f, 2f, Interpolation.swingOut),
                Actions.moveBy(0f, -30f, 2f, Interpolation.swing),
            )))
        }

    }

}