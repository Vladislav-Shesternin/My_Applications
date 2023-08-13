package com.zaykoa.investmentanalyzer.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.utils.Align
import com.zaykoa.investmentanalyzer.game.actors.checkbox.ACheckBox
import com.zaykoa.investmentanalyzer.game.actors.checkbox.ACheckBoxGroup
import com.zaykoa.investmentanalyzer.game.actors.checkbox.ACheckBoxStyle
import com.zaykoa.investmentanalyzer.game.actors.scroll.VerticalGroup
import com.zaykoa.investmentanalyzer.game.manager.FontTTFManager
import com.zaykoa.investmentanalyzer.game.manager.SpriteManager
import com.zaykoa.investmentanalyzer.game.screens.namesik
import com.zaykoa.investmentanalyzer.game.utils.actor.animHide
import com.zaykoa.investmentanalyzer.game.utils.actor.disable
import com.zaykoa.investmentanalyzer.game.utils.actor.setBounds
import com.zaykoa.investmentanalyzer.game.utils.actor.setOnClickListener
import com.zaykoa.investmentanalyzer.game.utils.advanced.AdvancedGroup
private val cbg = ACheckBoxGroup()

class DataGroup(val txt: String): AdvancedGroup() {

    val cbox = ACheckBox(ACheckBoxStyle.gb).apply { checkBoxGroup = cbg }
    private val text = Label(txt, Label.LabelStyle(FontTTFManager.GilMed.font_37.font, Color.BLACK))

    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(cbox)
            addAndFillActor(text)
            text.apply {
                setAlignment(Align.center)
                disable()
            }
        }
    }


    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------


}