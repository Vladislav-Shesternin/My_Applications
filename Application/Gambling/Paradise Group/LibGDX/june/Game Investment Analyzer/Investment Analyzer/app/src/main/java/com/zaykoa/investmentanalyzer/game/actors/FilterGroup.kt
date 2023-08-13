package com.zaykoa.investmentanalyzer.game.actors

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.zaykoa.investmentanalyzer.game.actors.checkbox.ACheckBox
import com.zaykoa.investmentanalyzer.game.actors.checkbox.ACheckBoxStyle
import com.zaykoa.investmentanalyzer.game.actors.scroll.VerticalGroup
import com.zaykoa.investmentanalyzer.game.manager.SpriteManager
import com.zaykoa.investmentanalyzer.game.utils.actor.animHide
import com.zaykoa.investmentanalyzer.game.utils.actor.disable
import com.zaykoa.investmentanalyzer.game.utils.actor.setBounds
import com.zaykoa.investmentanalyzer.game.utils.actor.setOnClickListener
import com.zaykoa.investmentanalyzer.game.utils.advanced.AdvancedGroup

class FilterGroup: AdvancedGroup() {

    var blockich: () -> Unit = {}

    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(Image(SpriteManager.GameRegion.FILTER.region))

            val xA     = Actor()
            val applyA = Actor()
            addActors(xA, applyA)
            xA.apply {
                setBounds(518f, 879f, 83f, 83f)
                setOnClickListener {
                    this@FilterGroup.animHide(0.66f)
                    this@FilterGroup.disable()
                }
            }
            applyA.apply {
                setBounds(35f, 74f, 560f, 101f)
                setOnClickListener {
                    this@FilterGroup.animHide(0.66f)
                    this@FilterGroup.disable()
                    blockich()
                }
            }

            val popA = ACheckBox(ACheckBoxStyle.POP)
            val gloA = ACheckBox(ACheckBoxStyle.GLOB)
            val lucA = ACheckBox(ACheckBoxStyle.LUC)
            val avtA = ACheckBox(ACheckBoxStyle.AVT)
            val zdoA = ACheckBox(ACheckBoxStyle.ZDO)
            val putA = ACheckBox(ACheckBoxStyle.PUT)
            val proA = ACheckBox(ACheckBoxStyle.PRO)
            val obuA = ACheckBox(ACheckBoxStyle.OBU)
            val kraA = ACheckBox(ACheckBoxStyle.KRA)

            addActors(popA, gloA, lucA, avtA, zdoA, putA, proA, obuA, kraA)
            popA.setBounds(43f,702f,258f,72f)
            gloA.setBounds(319f,702f,241f,72f)
            lucA.setBounds(43f,608f,350f,72f)
            avtA.setBounds(43f,435f,239f,72f)
            zdoA.setBounds(301f,435f,196f,72f)
            putA.setBounds(43f,341f,245f,72f)
            proA.setBounds(301f,341f,289f,72f)
            obuA.setBounds(43f,249f,208f,72f)
            kraA.setBounds(269f,249f,175f,72f)

        }
    }


    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------


}