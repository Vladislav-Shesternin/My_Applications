package hekeur.buzzniess.megaglorr.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import hekeur.buzzniess.megaglorr.game.LibGDXGame
import hekeur.buzzniess.megaglorr.game.utils.TIME_ANIM_SCREEN_ALPHA
import hekeur.buzzniess.megaglorr.game.utils.TextureEmpty
import hekeur.buzzniess.megaglorr.game.utils.actor.animHide
import hekeur.buzzniess.megaglorr.game.utils.actor.animShow
import hekeur.buzzniess.megaglorr.game.utils.actor.setOnClickListener
import hekeur.buzzniess.megaglorr.game.utils.advanced.AdvancedScreen
import hekeur.buzzniess.megaglorr.game.utils.advanced.AdvancedStage
import hekeur.buzzniess.megaglorr.game.utils.font.CharType
import hekeur.buzzniess.megaglorr.game.utils.font.FontPath
import hekeur.buzzniess.megaglorr.game.utils.font.setCharacters
import hekeur.buzzniess.megaglorr.game.utils.font.setLinear
import hekeur.buzzniess.megaglorr.game.utils.font.setSize
import hekeur.buzzniess.megaglorr.game.utils.region
import hekeur.buzzniess.megaglorr.game.utils.runGDX
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Random

class DarotteScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val generatorEB = FreeTypeFontGenerator(Gdx.files.internal(FontPath.ExtraBold))
    private val generatorSB = FreeTypeFontGenerator(Gdx.files.internal(FontPath.SemiBold))
    private val generatorRG = FreeTypeFontGenerator(Gdx.files.internal(FontPath.Regular))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()
    private val font32      = generatorEB.generateFont(parameter.setCharacters(CharType.NUMBERS).setSize(32))
    private val font37      = generatorSB.generateFont(parameter.setCharacters(CharType.ALL).setSize(37))
    private val font27      = generatorRG.generateFont(parameter.setCharacters(CharType.ALL).setSize(27))

    private val countLbl    = Label("", Label.LabelStyle(font32, Color.WHITE)).apply {
        setAlignment(Align.right)
    }
    private val questionLbl = Label("", Label.LabelStyle(font37, Color.WHITE)).apply {
        setAlignment(Align.center)
        wrap = true
    }
    private val answer1Lbl  = Label("", Label.LabelStyle(font27, Color.valueOf("000D27"))).apply { wrap = true }
    private val answer2Lbl  = Label("", Label.LabelStyle(font27, Color.valueOf("000D27"))).apply { wrap = true }
    private val answer3Lbl  = Label("", Label.LabelStyle(font27, Color.valueOf("000D27"))).apply { wrap = true }
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
    ).shuffled()

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.gaming.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(countLbl, questionLbl, answer1Lbl, answer2Lbl, answer3Lbl, answer1Img, answer2Img, answer3Img)
        countLbl.setBounds(440f, 1192f, 66f, 24f)
        questionLbl.setBounds(34f, 451f, 545f, 183f)
        answer1Lbl.setBounds(98f, 367f, 464f, 53f)
        answer2Lbl.setBounds(98f, 265f, 464f, 53f)
        answer3Lbl.setBounds(98f, 155f, 464f, 53f)
        answer1Img.setBounds(35f, 350f, 545f, 87f)
        answer2Img.setBounds(35f, 248f, 545f, 87f)
        answer3Img.setBounds(35f, 144f, 545f, 87f)

        listOf(answer1Img, answer2Img, answer3Img).onEach { it.setOnClickListener { actor ->

            (actor as Image).drawable = TextureRegionDrawable(game.spriteUtil.frramek)

            coroutine?.launch {
                delay(500)
                runGDX { actor.drawable = TextureRegionDrawable(TextureEmpty) }
                countFlow.apply { if (value < 20 ) value++ else runGDX {
                    stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(DarotteScreen::class.java.name) }
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
        generatorEB.dispose()
        generatorSB.dispose()
        generatorRG.dispose()
        font37.dispose()
        font32.dispose()
        font27.dispose()
    }

    data class Question(
        val question: String,
        val answer1: String,
        val answer2: String,
        val answer3: String,
    )

}