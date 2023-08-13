package com.nicoledeonnit.cryptosignals.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.utils.Align
import com.nicoledeonnit.cryptosignals.game.manager.FontTTFManager
import com.nicoledeonnit.cryptosignals.game.manager.NavigationManager
import com.nicoledeonnit.cryptosignals.game.manager.SpriteManager
import com.nicoledeonnit.cryptosignals.game.utils.actor.animpokazat
import com.nicoledeonnit.cryptosignals.game.utils.actor.setOnClickListener
import com.nicoledeonnit.cryptosignals.game.utils.advanced.AdvancedScreen
import com.nicoledeonnit.cryptosignals.game.utils.advanced.AdvancedStage

var anatoliYouKakoitoTolik = "Добро пожаловать в мир Cryptosignals - передового приложения для крипто-торговли. Если вы активный трейдер или инвестор в мире криптовалют, то наше приложение поможет вам принимать обоснованные решения и достигать успеха на рынке.  Особенности Cryptosignals: Точные сигналы: Получайте точные сигналы для входа и выхода из позиций, основанные на анализе данных и индикаторов. Наша команда аналитиков следит за рынком 24/7, чтобы предоставлять вам самую актуальную информацию. Образовательный контент: Развивайте свои знания и навыки в мире криптовалют с помощью образовательного контента от профессионалов. Мы предоставляем обучающие материалы, аналитику и прогнозы, чтобы помочь вам принимать информированные решения. Графики и аналитика: Анализируйте динамику криптовалют с помощью графиков и технического анализа. Выявляйте тренды, уровни поддержки и сопротивления, чтобы определить оптимальные точки входа и выхода. Портфель и уведомления: Отслеживайте состояние своего крипто-портфеля и получайте уведомления о важных событиях на рынке. Наше приложение поможет вам быть в курсе событий и принимать своевременные решения.  С Cryptosignals вы получите не только доступ к точным сигналам, но и образовательным ресурсам, которые помогут вам стать успешным трейдером и инвестором. Присоединяйтесь к сообществу профессионалов и начните свой путь к успеху в мире криптовалют с Cryptosignals - вашим надежным партнером в крипто-торговле. Добро пожаловать в будущее финансовых возможностей!"

class AboutWithHigherScreen: AdvancedScreen() {

    private val texas by lazy { Label(anatoliYouKakoitoTolik, Label.LabelStyle(FontTTFManager.Regular.font_25.font, Color.WHITE)).apply {
        wrap = true
        setAlignment(Align.topLeft)
        addAction(Actions.alpha(0.8f))
    } }
    private val scroll = ScrollPane(texas)
    private val buton = Image(SpriteManager.Palas.PRODOZ.region)


    override fun AdvancedStage.addActorsOnStageUI() {
        sardar()
        root.animpokazat(0.8f)
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedStage.sardar() {
        addActor(scroll)
        scroll.setBounds(68f, 277f,741f, 1427f)
        addActor(buton)
        buton.setBounds(68f, 68f,741f, 111f)
        buton.setOnClickListener { NavigationManager.back() }
    }

}