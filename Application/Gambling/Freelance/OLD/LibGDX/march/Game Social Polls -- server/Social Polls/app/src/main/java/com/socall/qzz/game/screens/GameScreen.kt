package com.socall.qzz.game.screens

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.socall.qzz.game.actors.Answer
import com.socall.qzz.game.actors.Panel
import com.socall.qzz.game.manager.SpriteManager
import com.socall.qzz.game.utils.actor.disable
import com.socall.qzz.game.utils.actor.setBounds
import com.socall.qzz.game.utils.actor.toClickable
import com.socall.qzz.game.utils.advanced.AdvancedGroup
import com.socall.qzz.game.utils.advanced.AdvancedScreen
import com.socall.qzz.game.utils.runGDX
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.socall.qzz.game.utils.Layout.Game as LG

class GameScreen : AdvancedScreen() {

    private val listText = listOf(
        Quiz(
            "В каком году Титаник утонул в Атлантическом океане 15 апреля во время своего первого плавания из Саутгемптона?",
            "1912",
            "1907",
            "1920",
        ),
        Quiz(
            "Как называется первый фильм «Carry On», снятый и выпущенный в 1958 году?",
            "Проводить сержант",
            "Провожает майор",
            "Встречает мать",
        ),
        Quiz(
            "Как называется крупнейшая технологическая компания в Южной Корее?",
            "Samsung",
            "Huawei",
            "Xiaomi",
        ),
        Quiz(
            "Какой певец выступал в поп-группе Showaddywaddy 1970-х годов?",
            "Дейв Бартрам",
            "Енрике Еглесиас",
            "Мики Рурк",
        ),
        Quiz(
            "Какой теперь известный телевизионный шеф начал готовить в возрасте восьми лет в пабе своих родителей «The Cricketers» в Клаверинге, Эссекс?",
            "Джейми Оливер",
            "Давид Гуетта",
            "Джереми Кларксон",
        ),
        Quiz(
            "Какой голландский игрок в дартс выиграл чемпионат мира BDO 2012 года в загородном клубе Lakeside, Frimley Green, 15 января?",
            "Кристиан Кист",
            "Людвиг Вандер",
            "Сильвестр Адю",
        ),
        Quiz(
            "Какой металл был открыт Гансом Кристианом Эрстедом в 1825 году?",
            "алюминий",
            "латунь",
            "серебро",
        ),
        Quiz(
            "Какая столица Португалии?",
            "Лиссабон",
            "Цюррих",
            "Люксембург",
        ),
        Quiz(
            "Сколько вдохов делает человеческое тело ежедневно?",
            "20,000",
            "30,000",
            "15,000",
        ),
        Quiz(
            "Кто был премьер-министр Великобритании с 1841 по 1846 год?",
            "Роберт Пил",
            "Гаус Воск",
            "Бернард Швац",
        ),
        Quiz(
            "Какой химический символ для серебра?",
            "Ag",
            "Au",
            "Ar",
        ),
        Quiz(
            "Кто изобрел Cat's Eyes в 1934 году для повышения безопасности дорожного движения?",
            "Перси Шоу",
            "Джони Дейс",
            "Ван де Бор",
        ),
        Quiz(
            "Какая самая маленькая птица в мире?",
            "Пчела колибри",
            "Муха воробей",
            "Мини-сура",
        ),
        Quiz(
            "Кто играл «Боди» и «Дойл» в «Профессионалах»?",
            "Льюис Коллинз и Мартин Шоу",
            "Перси Джексон",
            "Фанни де Виль и Джодани Вик",
        ),
        Quiz(
            "Какая кукла, Барби, полное имя?",
            "Барбара Миллисент Робертс",
            "Стелла Родригес",
            "Барбуа де Би",
        ),
    )

    private val panel   = Panel()
    private val answers = List(3) { Answer() }


    override fun show() {
        stageUI.addAction(Actions.alpha(0f))
        setBackgrounds(SpriteManager.SplashRegion.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            runGDX {
                addPanel()
                addAnswers()
                update()
            }
            showStage()
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addPanel() {
        addActor(panel)
        panel.setBounds(LG.panel)
    }

    private fun AdvancedGroup.addAnswers() {
        addActors(answers)
        var nx = 34f
        answers.onEach {
            it.setBounds(nx, 46f, 354f, 94f)
            nx += 386f
            it.toClickable().setOnClickListener {
                coroutine.launch {
                    runGDX {
                        answers.onEach { a ->
                            if (a.isWin) {
                                a.win()
                                a.disable()
                            } else a.fail()
                        }
                    }
                    delay(1000)
                    runGDX { update() }
                }
            }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private suspend fun showStage() = suspendCoroutine<Unit> { continuation ->
        runGDX {
            stageUI.addAction(Actions.sequence(
                Actions.fadeIn(0.5f),
                Actions.run { continuation.resume(Unit) }
            ))
        }
    }

    private fun update() {
        val q = listText.shuffled().first()
        panel.text.setText(q.text)
        answers.onEach { it.def() }

        answers.shuffled().apply {
            get(0).apply {
                text.setText(q.win)
                isWin = true
            }
            get(1).text.setText(q.fail1)
            get(2).text.setText(q.fail2)
        }
    }


    data class Quiz(
        val text : String,
        val win  : String,
        val fail1: String,
        val fail2: String,
    )

}