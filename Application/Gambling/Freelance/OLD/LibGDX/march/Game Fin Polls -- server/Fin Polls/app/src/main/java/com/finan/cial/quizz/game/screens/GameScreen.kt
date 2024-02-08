package com.finan.cial.quizz.game.screens

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.finan.cial.quizz.game.actors.Money
import com.finan.cial.quizz.game.actors.Otvet
import com.finan.cial.quizz.game.actors.Panel
import com.finan.cial.quizz.game.manager.SpriteManager
import com.finan.cial.quizz.game.utils.actor.disable
import com.finan.cial.quizz.game.utils.actor.enable
import com.finan.cial.quizz.game.utils.actor.setBounds
import com.finan.cial.quizz.game.utils.actor.toClickable
import com.finan.cial.quizz.game.utils.advanced.AdvancedGroup
import com.finan.cial.quizz.game.utils.advanced.AdvancedScreen
import com.finan.cial.quizz.game.utils.runGDX
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.finan.cial.quizz.game.utils.Layout.Game as LG

class GameScreen : AdvancedScreen() {

    private val listText = listOf(
        Vidpovid(
            "При каком уровне дохода на одного члена семьи в месяц нужно начинать планирование семейного бюджета?",
            "Независимо от уровня дохода",
            "10 000 рублей в месяц",
            "50 000 рублей в месяц",
        ),
        Vidpovid(
            "Как называется человек, который занимается каким – нибудь частным делом?",
            "Продавец",
            "Банкир",
            "Бизнесмен",
        ),
        Vidpovid(
            "Учреждение, куда мы идём за кредитом",
            "Магазин",
            "Банк",
            "Почта",
        ),
        Vidpovid(
            "Займ денег в на определённый срок под проценты - это...",
            "Кредит",
            "Долг",
            "Покупка",
        ),
        Vidpovid(
            "Деньги, которые поступают в бюджет семьи  –  это…",
            "Доходы",
            "Расходы",
            "Проценты",
        ),
        Vidpovid(
            "Деньги, которые тратятся из бюджета семьи  – это…",
            "Доходы",
            "Расходы",
            "Прибыль",
        ),
        Vidpovid(
            "Ввоз иностранных товаров в страну - это",
            "Импорт",
            "Експорт",
            "Аеропорт",
        ),
        Vidpovid(
            "Вывоз товаров из страну - это",
            "Импорт",
            "Експорт",
            "Аеропорт",
        ),
    )

    private val panel   = Panel()
    private val answers = List(3) { Otvet() }

    private val vidpovid = MutableStateFlow(listText.shuffled().first())


    override fun show() {
        stageUI.addAction(Actions.alpha(0f))
        setBackBackground(SpriteManager.SplashRegion.BAC.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            runGDX {
                stageBack.addActor(Money().apply {
                    setBounds(0f, 0f, stageBack.width, stageBack.height)
                    animmm()
                })

                addPanel()
                addAnswers()
            }
            showStage()

            launch {
                vidpovid.collect { vidp ->
                    runGDX {
                        panel.update(vidp.text)
                        val ss = listOf(vidp.a, vidp.b, vidp.c).shuffled()
                        answers[0].update(ss[0])
                        answers[1].update(ss[1])
                        answers[2].update(ss[2])
                        mainGroup.enable()
                    }
                }
            }
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
        var ny = LG.vidpo.y
        answers.onEach {
            it.setBounds(LG.vidpo.x, ny, LG.vidpo.w, LG.vidpo.h)
            ny -= LG.vidpo.vs + LG.vidpo.h
            it.toClickable().setOnClickListener {
                mainGroup.disable()
                answers.shuffled().take(2).onEach { ov ->
                    ov.addAction(Actions.sequence(
                        Actions.moveTo(950f, ov.y, 0.5f),
                        Actions.moveTo(-900f, ov.y),
                        Actions.moveTo(19f, ov.y, 0.5f),
                        Actions.run { vidpovid.value = listText.shuffled().first() }
                    ))
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
                Actions.fadeIn(0.45f),
                Actions.run { continuation.resume(Unit) }
            ))
        }
    }

}

data class Vidpovid(
    val text : String,
    val a    : String,
    val b    : String,
    val c    : String,
)