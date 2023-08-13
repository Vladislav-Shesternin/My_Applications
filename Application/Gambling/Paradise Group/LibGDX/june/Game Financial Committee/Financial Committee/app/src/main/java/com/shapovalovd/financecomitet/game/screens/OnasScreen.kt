package com.shapovalovd.financecomitet.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.utils.Align
import com.shapovalovd.financecomitet.game.actors.button.AButton
import com.shapovalovd.financecomitet.game.actors.button.AButtonStyle
import com.shapovalovd.financecomitet.game.manager.FontTTFManager
import com.shapovalovd.financecomitet.game.manager.NavigationManager
import com.shapovalovd.financecomitet.game.manager.SpriteManager
import com.shapovalovd.financecomitet.game.utils.actor.setOnClickListener
import com.shapovalovd.financecomitet.game.utils.advanced.AdvancedGroup
import com.shapovalovd.financecomitet.game.utils.advanced.AdvancedScreen


class OnasScreen: AdvancedScreen() {

    private val aboutText = "Добро пожаловать в Финансовый Комитет - мощное и универсальное приложение, созданное для тех, кто стремится к финансовой независимости и управлению своими средствами с легкостью.\n" +
            "Финансовый Комитет предлагает вам уникальные возможности для эффективного управления вашими финансами. Наша биржа предоставляет доступ к широкому спектру финансовых инструментов, позволяя вам торговать акциями, валютами, сырьевыми товарами и другими активами. Вы сможете отслеживать рыночные тренды, делать обоснованные инвестиционные решения и расширять свой финансовый портфель.\n" +
            "  Но это еще не все! Мы также предоставляем мощный конвертер валют для более чем 150 стран. С легкостью переводите и сравнивайте валюты по актуальным курсам. Будь то путешествие или международные финансовые операции, наш конвертер валют поможет вам сделать точные расчеты и избежать лишних затрат."

    private val label      = Label(aboutText, Label.LabelStyle(FontTTFManager.NunitoSans.font_40.font, Color.WHITE))
    private val scrollPane = ScrollPane(label)
    private val back       = Image(SpriteManager.GameRegion.BACKCKCKACH.region)


    override fun AdvancedGroup.addActorsOnGroup() {
        addActor(scrollPane)
        scrollPane.setBounds(41f, 290f, 596f, 1130f)

        label.setAlignment(Align.center, Align.top)
        label.wrap = true


        addActor(back)
        back.apply {
            setBounds(472f, 85f, 164f, 166f)
            setOnClickListener { NavigationManager.back() }
        }
    }

}