package finalizer.masturbaizer.lotos.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import finalizer.masturbaizer.lotos.game.LibGDXGame
import finalizer.masturbaizer.lotos.game.utils.TIME_ANIM_SCREEN_ALPHA
import finalizer.masturbaizer.lotos.game.utils.TextureEmpty
import finalizer.masturbaizer.lotos.game.utils.actor.animHide
import finalizer.masturbaizer.lotos.game.utils.actor.animShow
import finalizer.masturbaizer.lotos.game.utils.actor.setOnClickListener
import finalizer.masturbaizer.lotos.game.utils.advanced.AdvancedScreen
import finalizer.masturbaizer.lotos.game.utils.advanced.AdvancedStage
import finalizer.masturbaizer.lotos.game.utils.font.CharType
import finalizer.masturbaizer.lotos.game.utils.font.FontPath
import finalizer.masturbaizer.lotos.game.utils.font.setCharacters
import finalizer.masturbaizer.lotos.game.utils.font.setLinear
import finalizer.masturbaizer.lotos.game.utils.font.setSize
import finalizer.masturbaizer.lotos.game.utils.region
import finalizer.masturbaizer.lotos.game.utils.runGDX
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Random

class VoprosikiScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val generatorEB = FreeTypeFontGenerator(Gdx.files.internal(FontPath.ExtraBold))
    private val generatorSB = FreeTypeFontGenerator(Gdx.files.internal(FontPath.SemiBold))
    private val generatorRG = FreeTypeFontGenerator(Gdx.files.internal(FontPath.Regular))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()
    private val font25      = generatorRG.generateFont(parameter.setCharacters(CharType.NUMBERS).setSize(25))
    private val font47      = generatorSB.generateFont(parameter.setCharacters(CharType.ALL).setSize(47))
    private val font34      = generatorRG.generateFont(parameter.setCharacters(CharType.ALL).setSize(34))

    private val countLbl    = Label("", Label.LabelStyle(font25, Color.WHITE)).apply {
        setAlignment(Align.right)
    }
    private val questionLbl = Label("", Label.LabelStyle(font47, Color.WHITE)).apply {
        setAlignment(Align.center)
        wrap = true
    }
    private val answer1Lbl  = Label("", Label.LabelStyle(font34, Color.WHITE)).apply { wrap = true }
    private val answer2Lbl  = Label("", Label.LabelStyle(font34, Color.WHITE)).apply { wrap = true }
    private val answer3Lbl  = Label("", Label.LabelStyle(font34, Color.WHITE)).apply { wrap = true }
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
    ).shuffled().shuffled().shuffled()

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.barabanovka.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(countLbl, questionLbl, answer1Lbl, answer2Lbl, answer3Lbl, answer1Img, answer2Img, answer3Img)
        countLbl.setBounds(333f, 1587f, 35f, 19f)
        questionLbl.setBounds(58f, 934f, 652f, 534f)
        answer1Lbl.setBounds(121f, 719f, 587f, 90f)
        answer2Lbl.setBounds(121f, 589f, 587f, 90f)
        answer3Lbl.setBounds(121f, 459f, 587f, 90f)
        answer1Img.setBounds(42f, 710f, 680f, 108f)
        answer2Img.setBounds(42f, 580f, 680f, 108f)
        answer3Img.setBounds(42f, 450f, 680f, 108f)

        val abasatsa = Actor()
        addActor(abasatsa)
        abasatsa.apply {
            setBounds(42f, 185f, 682f, 110f)
            setOnClickListener {
                countFlow.apply { if (value < 20 ) value++ else runGDX {
                    stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(WinnerScreen::class.java.name) }
                } }
            }
        }

        listOf(answer1Img, answer2Img, answer3Img).onEach { it.setOnClickListener { actor ->

            (actor as Image).drawable = if (Random().nextBoolean()) TextureRegionDrawable(game.spriteUtil.www) else TextureRegionDrawable(game.spriteUtil.qqq)

            coroutine?.launch {
                delay(400)
                runGDX { actor.drawable = TextureRegionDrawable(TextureEmpty) }
                countFlow.apply { if (value < 20 ) value++ else runGDX {
                    stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(WinnerScreen::class.java.name) }
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
        font25.dispose()
        font34.dispose()
        font47.dispose()
    }

    data class Question(
        val question: String,
        val answer1: String,
        val answer2: String,
        val answer3: String,
    )

}