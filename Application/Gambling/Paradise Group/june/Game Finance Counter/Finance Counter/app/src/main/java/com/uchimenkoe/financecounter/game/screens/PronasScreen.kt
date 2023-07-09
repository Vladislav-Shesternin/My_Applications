package com.uchimenkoe.financecounter.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.utils.Align
import com.uchimenkoe.financecounter.game.manager.FontTTFManager
import com.uchimenkoe.financecounter.game.manager.NavigationManager
import com.uchimenkoe.financecounter.game.manager.SpriteManager
import com.uchimenkoe.financecounter.game.utils.actor.setOnClickListener
import com.uchimenkoe.financecounter.game.utils.advanced.AdvancedGroup
import com.uchimenkoe.financecounter.game.utils.advanced.AdvancedScreen


class PronasScreen: AdvancedScreen() {

    private val aboutText = "Счётчик Финансов\" - ваш надежный спутник в управлении финансами. Будьте в курсе своих доходов и расходов, установите финансовые цели и следите за их достижением. Счётчик Финансов предлагает удобный интерфейс и мощные функции для учёта и анализа ваших финансовых операций. Отслеживайте свои финансы, контролируйте бюджет и принимайте осознанные финансовые решения. Взгляните на свои финансы с новой точки зрения с помощью Счётчика Финансов."

    private val label      = Label(aboutText, Label.LabelStyle(FontTTFManager.DaysOneRegular.font_50.font, Color.WHITE))
    private val scrollPane = ScrollPane(label)
    private val back       = Image(SpriteManager.GameRegion.BACKA.region)


    override fun AdvancedGroup.addActorsOnGroup() {
        addActor(scrollPane)
        scrollPane.setBounds(26f, 0f, 792f, 1625f)

        label.setAlignment(Align.center, Align.top)
        label.wrap = true


        addActor(back)
        back.apply {
            setBounds(511f, 64f, 295f, 295f)
            setOnClickListener { NavigationManager.back() }
        }
    }

}