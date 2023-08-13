package com.metanit.metrixmanager.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.utils.Align
import com.metanit.metrixmanager.R
import com.metanit.metrixmanager.game.game
import com.metanit.metrixmanager.game.manager.FontTTFManager
import com.metanit.metrixmanager.game.manager.NavigationManager
import com.metanit.metrixmanager.game.manager.SpriteManager
import com.metanit.metrixmanager.game.oranged
import com.metanit.metrixmanager.game.utils.GameColor
import com.metanit.metrixmanager.game.utils.actor.animHide
import com.metanit.metrixmanager.game.utils.actor.animShow
import com.metanit.metrixmanager.game.utils.actor.setOnClickListener
import com.metanit.metrixmanager.game.utils.advanced.AdvancedScreen
import com.metanit.metrixmanager.game.utils.advanced.AdvancedStage

var andreySudaTextVstav = "Добро пожаловать в мир MetrixManager - инновационного приложения для менеджмента и анализа статистических данных. MetrixManager создан для того, чтобы сделать вашу работу с данными легкой и эффективной. Независимо от того, крупное ли предприятие или стартап, наше приложение обеспечит вам мощные функциональные возможности и интуитивный интерфейс.  Особенности MetrixManager: Управление данными: Импортируйте данные из различных источников и управляйте ими в одном месте. Простая и удобная обработка информации позволит вам быстро и точно анализировать данные вашего бизнеса. Визуальные отчеты: Создавайте красочные диаграммы и графики, которые наглядно отображают ключевые метрики вашего бизнеса. Визуальный анализ данных поможет быстро выявить тенденции и принимать правильные решения. Гибкий анализ: MetrixManager предоставляет множество инструментов для глубокого анализа данных. Вы сможете проводить сегментацию, фильтрацию и агрегацию, чтобы получить полное представление о вашей статистике. Улучшение производительности: Анализируйте ключевые показатели эффективности вашего бизнеса и оптимизируйте процессы. MetrixManager поможет вам принимать обоснованные решения для достижения новых высот.  С MetrixManager вы станете мастером статистики, легко справляющимся с большими объемами данных и превращающим их в ценные знания. Поднимите свой бизнес на новый уровень с помощью MetrixManager - вашего верного спутника в мире менеджмента и анализа статистики. Развивайтесь и успех придет к вам! "

class OChemPoetKukushkaScreen: AdvancedScreen() {

    private val bumaga = Image(SpriteManager.GamePlay.beliy_papirus.region)
    private val texesexet by lazy { Label(andreySudaTextVstav, Label.LabelStyle(FontTTFManager.Regular.font_22.font, Color.BLACK)).apply {
        wrap = true
        setAlignment(Align.topLeft)
    } }
    private val palen = ScrollPane(texesexet)


    override fun AdvancedStage.addActorsOnStageUI() {
        oranged = GameColor.orda
        game.activity.setNavigationBarColor(R.color.orange)
        addAsdaGugliky()
        root.animShow(0.7f)
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedStage.addAsdaGugliky() {
        addActor(bumaga)
        bumaga.setBounds(37f, 30f,627f, 1469f)
        addActor(palen)
        palen.setBounds(71f, 122f,571f, 1338f)
    }

}