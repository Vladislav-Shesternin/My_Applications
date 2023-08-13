package com.foot.ball.quiz.game.screens

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.foot.ball.quiz.game.actors.checkbox.CheckBoxGroup
import com.foot.ball.quiz.game.actors.checkbox.CheckBoxStyle
import com.foot.ball.quiz.game.actors.label.LabelStyle
import com.foot.ball.quiz.game.actors.label.spinning.SpinningLabel
import com.foot.ball.quiz.game.manager.NavigationManager
import com.foot.ball.quiz.game.manager.SpriteManager
import com.foot.ball.quiz.game.util.advanced.AdvancedScreen
import com.foot.ball.quiz.game.util.advanced.AdvancedStage
import com.foot.ball.quiz.game.util.disable
import com.foot.ball.quiz.game.util.Layout.Game as LG

class GameScreen: AdvancedScreen() {

    private val panel     = Image(SpriteManager.GameRegion.PANEL.region)
    private val panelText = Label("", LabelStyle.aleoWhite_50).apply {
        wrap = true
        setAlignment(Align.center)
    }
    private val checkGroup = CheckBoxGroup()
    private val cbList = List(3) { com.foot.ball.quiz.game.actors.checkbox.CheckBox(CheckBoxStyle.cb).apply { checkBoxGroup = checkGroup } }
    private val answer = List(3) { Image(SpriteManager.GameRegion.ANS_DEF.region) }
    private val answerText = List(3) { SpinningLabel("", LabelStyle.aleoWhite_50).apply { disable() } }

    data class Data(
        val ans: String,
        val t: String,
        val f1: String,
        val f2: String,
    )

    private val otvet = listOf(
        Data(
            "Кто является самым титулованным футболистом в мире?",
            "Дани Алвес",
            "Леонель Месси",
            "Пеле",
        ),
        Data(
            "Какой клуб записал больше всего поражений в истории Премьер-лиги?",
            "Эвертон",
            "Ньюкасл",
            "Вест Хэм",
        ),
        Data(
            "В чем особенность сезона Премьер-лиги 2020 – 21?",
            "Это единственный сезон, в котором побед на выезде было больше, чем домашних",
            "Было назначено меньше пенальти, чем в любом другом сезоне",
            "Было забито больше голов, чем в любом другом сезоне",
        ),
        Data(
            "Сколько клубов не проиграли больше 25 выездных матчей за весь сезон в Премьер-лиге?",
            "Два",
            "Один",
            "Четыре",
        ),
        Data(
            "Что общего у Сульшера, Хассельбайнка, Эрншоу, Адебайора и Лукаку?",
            "Они все забивали хет-трики, выйдя на замену в матчах Премьер-лиги",
            "Все они забили по пять голов в одной игре",
            "Все они забили гол и автогол в одном матче Премьер-лиги",
        ),
        Data(
            "Ни одна команда не забила 10 голов в матче Премьер-лиги. Сколько команд забили девять голов за игру?",
            "Три",
            "Пять",
            "Одна",
        ),
        Data(
            "Кто выиграл первый чемпионат мира по футболу?",
            "Уругвай",
            "Бразилия",
            "Аргентина",
        ),
        Data(
            "В каком году СССР стал чемпионом Европы по футболу?",
            "1960",
            "1970",
            "1980",
        ),
        Data(
            "Какой вес самых легких футбольных бутс?",
            "99 грамм",
            "127 грфмм",
            "80 грамм",
        ),
        Data(
            "Играя за какой клуб Фабио Каннаваро выиграл \"Золотой Мяч\"?",
            "Реал Мадрид",
            "Интер",
            "Ювентус",
        ),
        Data(
            "Сборные каких стран трижды становились чемпионами Европы?",
            "Германии и Испании",
            "Украины и Бразилии",
            "Англии и Португалии",
        ),
        Data(
            "Какая команда чаще всего выступала в финальной части на чемпионатах Европы и не добивалась побед?",
            "Англия",
            "Германия",
            "Испания",
        ),
        Data(
            "Какой вратарь допустил роковую ошибку в матче Россия – Украина отборочного турнира Чемпионата Европы 2000 года?",
            "Филимонов",
            "Черчесов",
            "Акинфеев",
        ),
        Data(
            "На пути к чемпионскому титулу 2004 года Греция дважды обыграла хозяев сборной Португалии. С какими результатами она выигрывала у португальцев?",
            "2:1 и 1:0",
            "1:0 и 3:2",
            "1:0 и 1:0",
        ),
        Data(
            "Игрок какой сборной забил самый быстрый гол на Чемпионате мира за всю историю?",
            "Турция",
            "Англия",
            "Дания",
        ),
        Data(
            "В каком году был самый результативный по голам чемпионат Европы?",
            "2021",
            "2016",
            "2008",
        ),
    ).shuffled().first()

    override fun show() {
        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStage() {
        root.addAction(Actions.alpha(0f))
        addPanel()
        addCb()
        addAnswer()
        addAnswerText()
        root.addAction(Actions.fadeIn(1f))
    }

    var i = (0..1).shuffled().first()

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addPanel() {
        addActors(panel, panelText)
        val s = ScrollPane(panelText)
        addActor(s)

        panel.apply {
            with(LG.panel) { setBounds(x, y, w, h) }
        }
        panelText.apply {
            with(LG.textPanel) {
                setBounds(x, y, w, h)
                s.setBounds(x, y, w, h)
            }
            setText(otvet.ans)
        }
    }

    private fun AdvancedStage.addCb() {
        var ny = LG.cb.y
        cbList.onEachIndexed { index, cb ->
            addActor(cb)
            with(LG.cb) {
                cb.setBounds(x, ny, w, h)
                ny -= h+vs
            }
            cb.setOnCheckListener { if (it) {
                stage.root.disable()
                answer[index].drawable = TextureRegionDrawable(SpriteManager.GameRegion.ANS_FALSE.region)

                stage.root.addAction(Actions.sequence(
                    Actions.delay(1f),
                    Actions.fadeOut(1f),
                    Actions.run { NavigationManager.navigate(GameScreen()) }
                ))
            } }
        }

        cbList[i].setOnCheckListener { if (it){
            stage.root.disable()

            answer[i].drawable = TextureRegionDrawable(SpriteManager.GameRegion.ANS_TRUE.region)

            stage.root.addAction(Actions.sequence(
                Actions.delay(1f),
                Actions.fadeOut(1f),
                Actions.run { NavigationManager.navigate(GameScreen()) }
            ))
        } }
    }

    private fun AdvancedStage.addAnswer() {
        var ny = LG.text.y
        answer.onEach { cb ->
            addActor(cb)
            with(LG.text) {
                cb.setBounds(x, ny, w, h)
                ny -= h+vs
            }
        }
    }

    private fun AdvancedStage.addAnswerText() {
        var ny = LG.text.y
        answerText.onEachIndexed { index, cb ->
            addActor(cb)
            with(LG.text) {
                cb.setBounds(x, ny, w, h)
                ny -= h+vs
            }
            if (index != i) {
                cb.setText(otvet.f1)
            }
        }
        answerText[i].setText(otvet.t)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

}