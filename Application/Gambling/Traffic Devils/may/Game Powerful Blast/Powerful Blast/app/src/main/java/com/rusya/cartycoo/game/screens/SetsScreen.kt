package com.rusya.cartycoo.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.rusya.cartycoo.game.PoromGame
import com.rusya.cartycoo.game.actors.button.AButton
import com.rusya.cartycoo.game.actors.checkbox.ACheckBox
import com.rusya.cartycoo.game.actors.checkbox.ACheckBoxGroup
import com.rusya.cartycoo.game.utils.Poshlo_VREMA
import com.rusya.cartycoo.game.utils.actor.animHide
import com.rusya.cartycoo.game.utils.actor.animShow
import com.rusya.cartycoo.game.utils.actor.setBounds
import com.rusya.cartycoo.game.utils.advanced.AdvancedScreen
import com.rusya.cartycoo.game.utils.advanced.AdvancedStage
import com.rusya.cartycoo.game.utils.region

class SetsScreen(override val game: PoromGame): AdvancedScreen() {

    companion object {
        var isCandy = true
    }

    // Actor
    private val aMenu = AButton(this, AButton.Static.Type.Menu)

    private val solodkeBox = ACheckBox(this, ACheckBox.Static.Type.Posrate)
    private val fruktteBox = ACheckBox(this, ACheckBox.Static.Type.Posrate)


    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.guglas.lodrinking.region)
        super.show()
        stageUI.root.animShow(Poshlo_VREMA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(Image(game.faradeo.cucerci).apply {
            setBounds(57f, 253f, 1422f, 647f)
        })

        addMenu()

        val cbg = ACheckBoxGroup()
        addActors(solodkeBox, fruktteBox)
        solodkeBox.apply {
            checkBoxGroup = cbg
            setBounds(179f, 566f, 90f, 74f)

            if (isCandy) check(false)
            setOnCheckListener {
                if (it) isCandy = true
            }
        }
        fruktteBox.apply {
            checkBoxGroup = cbg
            setBounds(1285f, 218f, 90f, 74f)

            if (isCandy.not()) check(false)

            setOnCheckListener {
                if (it) isCandy = false
            }
        }
    }

    // ------------------------------------------------------------------------
    // Create Add Actor
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addMenu() {
        addActors(aMenu)

        aMenu.apply {
            setBounds(681f, 23f, 165f, 165f)
            setOnClickListener {
                stageUI.root.animHide(Poshlo_VREMA) { game.navigationManager.back() }
            }
        }
    }

}