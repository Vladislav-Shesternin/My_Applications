package com.karpenkov.budgetgid.game.screens

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.utils.Align
import com.karpenkov.budgetgid.game.actors.button.AButton
import com.karpenkov.budgetgid.game.actors.button.AButtonStyle
import com.karpenkov.budgetgid.game.actors.checkbox.ACheckBox
import com.karpenkov.budgetgid.game.actors.checkbox.ACheckBoxStyle
import com.karpenkov.budgetgid.game.game
import com.karpenkov.budgetgid.game.manager.FontTTFManager
import com.karpenkov.budgetgid.game.manager.GameDataStoreManager
import com.karpenkov.budgetgid.game.manager.NavigationManager
import com.karpenkov.budgetgid.game.manager.SpriteManager
import com.karpenkov.budgetgid.game.utils.actor.disable
import com.karpenkov.budgetgid.game.utils.actor.enable
import com.karpenkov.budgetgid.game.utils.actor.setBounds
import com.karpenkov.budgetgid.game.utils.actor.setOnClickListener
import com.karpenkov.budgetgid.game.utils.advanced.AdvancedGroup
import com.karpenkov.budgetgid.game.utils.advanced.AdvancedScreen
import com.karpenkov.budgetgid.game.utils.runGDX
import kotlinx.coroutines.launch
import com.karpenkov.budgetgid.game.utils.Layout.Privacy as LP


class AboutUsScreen: AdvancedScreen() {

    private val aboutText = "Добро пожаловать в Бюджетный Гид - вашего верного спутника в финансовом путешествии! Наше приложение предоставляет вам инструменты для управления вашими финансами и обмена валюты, чтобы помочь вам эффективно планировать, контролировать и оптимизировать свои финансовые ресурсы.\n" +
            "\n" +
            "Особенности приложения:\n" +
            "\n" +
            "Конвертер валют: Мы предлагаем широкий выбор валют из разных стран, чтобы вы могли легко и быстро конвертировать суммы в разных валютах. Не важно, отправляете ли вы деньги друзьям за границей или путешествуете, наш конвертер валют поможет вам сделать точные расчеты без лишних хлопот.\n" +
            "Персонализация: Мы понимаем, что каждый пользователь уникален. Поэтому мы предлагаем вам возможность настроить тему приложения по своему вкусу. Выберите из различных цветовых схем и создайте атмосферу, которая вам нравится.\n" +
            "Интуитивно понятный интерфейс: Наше приложение разработано с учетом простоты использования. Вы сможете быстро овладеть всеми функциями и начать управлять своими финансами с легкостью.\n" +
            "Мы постоянно работаем над улучшением и обновлением нашего приложения, чтобы предоставить вам лучший опыт управления финансами. Ваши отзывы и предложения очень важны для нас, поэтому не стесняйтесь делиться своими мыслями и пожеланиями с нами.\n" +
            "\n" +
            "Присоединяйтесь к нам и дайте Бюджетному Гиду возможность помочь вам достичь финансовой стабильности и достижения ваших финансовых целей!"

    private val label      = Label(aboutText, Label.LabelStyle(FontTTFManager.RegularraFont.font_50.font, Color.WHITE))
    private val scrollPane = ScrollPane(label)
    private val back       = AButton(AButtonStyle.backower)
    override fun show() {
        setBackgrounds(background ?: SpriteManager.SplashRegion.DEFAULT_BACKGROUND.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addActor(scrollPane)
        scrollPane.setBounds(16f, 312f, 657f, 962f)

        label.setAlignment(Align.center, Align.top)
        label.wrap = true


        addActor(back)
        back.apply {
            setBounds(208f, 21f, 274f, 274f)
            setOnClickListener { NavigationManager.back() }
        }
    }

}