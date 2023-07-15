package com.forvovim.smartconverter.game.screens

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.forvovim.smartconverter.game.actors.VerticalList
import com.forvovim.smartconverter.game.manager.FontTTFManager
import com.forvovim.smartconverter.game.manager.NavigationManager
import com.forvovim.smartconverter.game.manager.SpriteManager
import com.forvovim.smartconverter.game.utils.GameColor
import com.forvovim.smartconverter.game.utils.actor.animShow
import com.forvovim.smartconverter.game.utils.advanced.AdvancedGroup
import com.forvovim.smartconverter.game.utils.advanced.AdvancedScreen

class Andrey_Zdarova_Bandit_Screen: AdvancedScreen() {

    private val groupSuper = AdvancedGroup()
    private val spusokfImg = Image(SpriteManager.GameRegion.SPISOCHEK_NA_ZEMELKE.region)
    private val textField  = TextField("Поиск валюти", TextField.TextFieldStyle(FontTTFManager.GilReg.font_24.font, GameColor.GrttaA, null, null, null))

    private val veveve = VerticalList()

    override fun show() {
        super.show()
        mainGroup.addActor(groupSuper)
        groupSuper.apply {
            setBounds(615f, 615f, WIDTH, HEIGHT)
            addAction(Actions.alpha(0f))
            addVse()
        }.animShow(0.5f)
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addVse() {
        addActors(spusokfImg, textField, veveve)
        spusokfImg.apply {
            setBounds(27f, 1038f,562f, 258f)
        }
        textField.apply {
            setBounds(51f, 1072f,425f, 29f)
            var s = true
            setTextFieldListener { _, _ ->
                if (s) {
                    s = false
                    textField.text = ""
                }
                veveve.update()
            }
        }

        veveve.apply {
            setBounds(34f, 0f, 547f, 1026f)
            block = {
                if (isFrom) fromIndex = it else toooIndex = it
                NavigationManager.back()
            }
        }

    }

}