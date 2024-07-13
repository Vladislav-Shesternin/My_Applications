package com.my.cooking.chef.kitchen.craze.fe.game.screens

import com.JindoBlu.game.actors.checkbox.ACheckBoxGroup
import com.badlogic.gdx.math.Interpolation
import com.my.cooking.chef.kitchen.craze.fe.game.LibGDXGame
import com.my.cooking.chef.kitchen.craze.fe.game.actors.checkbox.ACheckBox
import com.my.cooking.chef.kitchen.craze.fe.game.utils.TIME_ANIM
import com.my.cooking.chef.kitchen.craze.fe.game.utils.actor.animHide
import com.my.cooking.chef.kitchen.craze.fe.game.utils.actor.animShow
import com.my.cooking.chef.kitchen.craze.fe.game.utils.actor.setOnClickListener
import com.my.cooking.chef.kitchen.craze.fe.game.utils.advanced.AdvancedScreen
import com.my.cooking.chef.kitchen.craze.fe.game.utils.advanced.AdvancedStage
import com.my.cooking.chef.kitchen.craze.fe.game.utils.region
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image

class PeregGameScreen(override val game: LibGDXGame): AdvancedScreen() {

    companion object {
        var isBall_1 = true
            private set
    }

    private val back = Image(game.allOl.menu)
    private val play = Image(game.allOl.pla)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.loal.listB.random().region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addPanel()
        addActor(play.apply {
            setBounds(196f, 454f, 147f, 142f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(GameScreen::class.java.name, PeregGameScreen::class.java.name)
                }
            }
        })

        addActor(back)
        back.apply {
            setBounds(10f, 886f, 65f, 62f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

        val b1 = Image(game.allOl.bll1)
        val b2 = Image(game.allOl.bll2)
        addActors(b1,b2)
        b1.setBounds(82f, 689f, 96f, 96f)
        b2.setBounds(350f, 689f, 96f, 96f)


    }

    private fun AdvancedStage.addPanel() {
        val ball1 = ACheckBox(
            this@PeregGameScreen,
            ACheckBox.Static.Type.Off_ON
        )
        val ball2 = ACheckBox(
            this@PeregGameScreen,
            ACheckBox.Static.Type.Off_ON
        )
        addActors(ball1, ball2)
        val cbg = ACheckBoxGroup()
        ball1.apply {
            setBounds(19f, 650f, 233f, 161f)
            checkBoxGroup = cbg
            if (isBall_1) check(false)
            setOnCheckListener {
                if (it) isBall_1 = true
            }
        }
        ball2.apply {
            setBounds(287f, 650f, 233f, 161f)
            checkBoxGroup = cbg
            if (isBall_1.not()) check(false)
            setOnCheckListener {
                if (it) isBall_1 = false

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