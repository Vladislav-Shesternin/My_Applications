package finann.promik.technikuss.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import finann.promik.technikuss.game.LibGDXGame
import finann.promik.technikuss.game.utils.TIME_ANIM_SCREEN_ALPHA
import finann.promik.technikuss.game.utils.TextureEmpty
import finann.promik.technikuss.game.utils.actor.animHide
import finann.promik.technikuss.game.utils.actor.animShow
import finann.promik.technikuss.game.utils.actor.setOnClickListener
import finann.promik.technikuss.game.utils.advanced.AdvancedScreen
import finann.promik.technikuss.game.utils.advanced.AdvancedStage
import finann.promik.technikuss.game.utils.font.CharType
import finann.promik.technikuss.game.utils.font.FontPath
import finann.promik.technikuss.game.utils.font.setCharacters
import finann.promik.technikuss.game.utils.font.setLinear
import finann.promik.technikuss.game.utils.font.setSize
import finann.promik.technikuss.game.utils.region
import finann.promik.technikuss.game.utils.runGDX
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Random

var winAnswerCounter = 0
    private set

class QuizScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val generatorSB = FreeTypeFontGenerator(Gdx.files.internal(FontPath.SemiBold))
    private val generatorRG = FreeTypeFontGenerator(Gdx.files.internal(FontPath.Regular))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()
    private val font53      = generatorSB.generateFont(parameter.setCharacters(CharType.ALL).setSize(53))
    private val font40      = generatorSB.generateFont(parameter.setCharacters(CharType.NUMBERS).setSize(40))
    private val font34      = generatorRG.generateFont(parameter.setCharacters(CharType.ALL).setSize(34))

    private val countLbl    = Label("", Label.LabelStyle(font40, Color.valueOf("2D4BD2"))).apply {
        setAlignment(Align.right)
    }
    private val questionLbl = Label("", Label.LabelStyle(font53, Color.BLACK)).apply {
        setAlignment(Align.center)
        wrap = true
    }
    private val answer1Lbl  = Label("", Label.LabelStyle(font34, Color.valueOf("000D26"))).apply { wrap = true }
    private val answer2Lbl  = Label("", Label.LabelStyle(font34, Color.valueOf("000D26"))).apply { wrap = true }
    private val answer3Lbl  = Label("", Label.LabelStyle(font34, Color.valueOf("000D26"))).apply { wrap = true }
    private val answer1Img  = Image()
    private val answer2Img  = Image()
    private val answer3Img  = Image()


    private val countFlow        = MutableStateFlow(1)
    private val questions        = listOf(
        Question(
            "Что представляют собой облигации?",
            "Доли в уставном капитале компании",
            "Долговые ценные бумаги",
            "Акции крупных корпораций",
        ),
        Question(
            "Что означает термин диверсификация инвестиций?",
            "Инвестирование в один вид активов",
            "Разделение инвестиций между разными видами активов",
            "Инвестирование только в недвижимость",
        ),
        Question(
            "Что такое процентная ставка?",
            "Курс обмена валюты",
            "Плата за использование чужих денег",
            "Стоимость товаров и услуг",
        ),
        Question(
            "При выборе банка необходимо в первую очередь обратить внимание на:",
            "Возраст банка",
            "Универсальность банка",
            "Рейтинг банка",
        ),
        Question(
            "Что из этого не относится к регулярным источникам дохода?",
            "Выигрыш в лотерею",
            "Зароботная плата",
            "Проценты по депозиту",
        ),
        Question(
            "Бесплатную информацию о своей кредитной истории вы можете получить…",
            "5 раз",
            "1 раз",
            "Бесконечно",
        ),
        Question(
            "Процедура, которая подразумевает пересмотр условий кредита...",
            "Реструкторизация",
            "Обнуление",
            "Рефинансирование",
        ),
        Question(
            "Кто вам вернёт вклад, если банк разорится?",
            "Банк",
            "Прокуратура",
            "Агенство",
        ),
        Question(
            "Выберите самую высокую ставку из перечисленных ниже",
            "1,5% в день",
            "50% в год",
            "15% в месяц",
        ),
        Question(
            "Банковский кредит, долги друзьям, алименты – все это относится к...",
            "Активам",
            "Пассивам",
            "Вложениям",
        ),
        Question(
            "Какой финансовый инструмент является самым безопасным?",
            "Акции",
            "Облигации",
            "Фючерсы",
        ),
        Question(
            "Какие данные вы не указываете при оплате покупок в интернет-магазинах?",
            "Номер карты",
            "CVV",
            "ПИН-код",
        ),
        Question(
            "Вы положили 500 рублей на банковский счет под 5% годовых. Какая сумма на счету будет у вас через год?",
            "525",
            "515",
            "25",
        ),
    )

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.QUIZBACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(countLbl, questionLbl, answer1Lbl, answer2Lbl, answer3Lbl, answer1Img, answer2Img, answer3Img)
        countLbl.setBounds(568f, 1495f, 58f, 30f)
        questionLbl.setBounds(43f, 868f, 688f, 434f)
        answer1Lbl.setBounds(124f, 556f, 594f, 88f)
        answer2Lbl.setBounds(124f, 425f, 594f, 88f)
        answer3Lbl.setBounds(124f, 294f, 594f, 88f)
        answer1Img.setBounds(43f, 545f, 687f, 109f)
        answer2Img.setBounds(43f, 414f, 687f, 109f)
        answer3Img.setBounds(43f, 283f, 687f, 109f)

        var result        = 0
        var colorDrawable = game.spriteUtil.ROM

        listOf(answer1Img, answer2Img, answer3Img).onEach { it.setOnClickListener { actor ->

            if (Random().nextBoolean()) {
                result = 1
                colorDrawable = game.spriteUtil.ROM
            } else {
                result = 0
                colorDrawable = game.spriteUtil.REDON
            }

            (actor as Image).drawable = TextureRegionDrawable(colorDrawable)
            winAnswerCounter += result

            coroutine?.launch {
                delay(500)
                runGDX { actor.drawable = TextureRegionDrawable(TextureEmpty) }
                countFlow.apply { if (value < 20 ) value++ else runGDX {
                    stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(FinishScreen::class.java.name) }
                } }
            }
        } }


        coroutine?.launch {
            countFlow.collect { count ->
                countLbl.setText(count)

                questions[(0..questions.lastIndex).random()].apply {
                    questionLbl.setText(question)
                    answer1Lbl.setText(answer1)
                    answer2Lbl.setText(answer2)
                    answer3Lbl.setText(answer3)
                }
            }
        }
    }

    override fun dispose() {
        super.dispose()
        generatorSB.dispose()
        generatorRG.dispose()
        font53.dispose()
        font40.dispose()
        font34.dispose()
    }

    data class Question(
        val question: String,
        val answer1: String,
        val answer2: String,
        val answer3: String,
    )

}