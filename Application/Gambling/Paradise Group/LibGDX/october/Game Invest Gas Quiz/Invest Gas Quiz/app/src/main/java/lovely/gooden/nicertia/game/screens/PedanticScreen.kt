package lovely.gooden.nicertia.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import lovely.gooden.nicertia.game.LibGDXGame
import lovely.gooden.nicertia.game.utils.TIME_ANIM_SCREEN_ALPHA
import lovely.gooden.nicertia.game.utils.TextureEmpty
import lovely.gooden.nicertia.game.utils.actor.animHide
import lovely.gooden.nicertia.game.utils.actor.animShow
import lovely.gooden.nicertia.game.utils.actor.setOnClickListener
import lovely.gooden.nicertia.game.utils.advanced.AdvancedScreen
import lovely.gooden.nicertia.game.utils.advanced.AdvancedStage
import lovely.gooden.nicertia.game.utils.font.CharType
import lovely.gooden.nicertia.game.utils.font.FontPath
import lovely.gooden.nicertia.game.utils.font.setCharacters
import lovely.gooden.nicertia.game.utils.font.setLinear
import lovely.gooden.nicertia.game.utils.font.setSize
import lovely.gooden.nicertia.game.utils.region
import lovely.gooden.nicertia.game.utils.runGDX
import java.util.Random

class PedanticScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val generatorSB = FreeTypeFontGenerator(Gdx.files.internal(FontPath.SB))
    private val generatorRG = FreeTypeFontGenerator(Gdx.files.internal(FontPath.RG))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()
    private val font27      = generatorRG.generateFont(parameter.setCharacters(CharType.NUMBERS).setSize(27))
    private val font44      = generatorSB.generateFont(parameter.setCharacters(CharType.ALL).setSize(44))
    private val font32      = generatorRG.generateFont(parameter.setCharacters(CharType.ALL).setSize(32))

    private val countLbl    = Label("", Label.LabelStyle(font27, Color.valueOf("474747"))).apply {
        setAlignment(Align.center)
    }
    private val questionLbl = Label("", Label.LabelStyle(font44, Color.WHITE)).apply {
        setAlignment(Align.center)
        wrap = true
    }
    private val answer1Lbl  = Label("", Label.LabelStyle(font32, Color.valueOf("7B7B7B"))).apply { wrap = true }
    private val answer2Lbl  = Label("", Label.LabelStyle(font32, Color.valueOf("7B7B7B"))).apply { wrap = true }
    private val answer3Lbl  = Label("", Label.LabelStyle(font32, Color.valueOf("7B7B7B"))).apply { wrap = true }
    private val answer1Img  = Image()
    private val answer2Img  = Image()
    private val answer3Img  = Image()

    private val panel = Image(game.spriteUtil.liba.random())
    private val progress = Image(game.spriteUtil.progressList.first())

    private val countFlow        = MutableStateFlow(1)
    private val questions        = listOf(
        Question(
            "Что такое процентная ставка?",
            "Курс обмена валюты",
            "Плата за использование чужих денег",
            "Стоимость товаров и услуг",
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
            "При выборе банка необходимо в первую очередь обратить внимание на:",
            "Возраст банка",
            "Универсальность банка",
            "Рейтинг банка",
        ),
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
            "Вы положили 500 рублей на банковский счет под 5% годовых. Какая сумма на счету будет у вас через год?",
            "525",
            "515",
            "25",
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
    ).shuffled()

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.gameka.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(progress, countLbl, panel, questionLbl, answer1Lbl, answer2Lbl, answer3Lbl, answer1Img, answer2Img, answer3Img)
        countLbl.setBounds(370f, 1427f, 35f, 20f)
        questionLbl.setBounds(54f, 771f, 612f, 584f)
        answer1Lbl.setBounds(114f, 607f, 546f, 82f)
        answer2Lbl.setBounds(114f, 488f, 546f, 82f)
        answer3Lbl.setBounds(114f, 366f, 546f, 82f)
        answer1Img.setBounds(40f, 597f, 639f, 102f)
        answer2Img.setBounds(40f, 478f, 639f, 102f)
        answer3Img.setBounds(40f, 356f, 639f, 102f)

        progress.setBounds(36f, 1473f, 648f, 13f)
        panel.setBounds(40f, 743f, 639f, 639f)

        val upisalasa = Actor()
        addActor(upisalasa)
        upisalasa.apply {
            setBounds(40f, 114f, 639f, 120f)
            setOnClickListener {
                countFlow.apply { if (value < 20 ) value++ else runGDX {
                    stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(PedanticScreen::class.java.name) }
                } }
            }
        }

        listOf(answer1Img, answer2Img, answer3Img).onEach { it.setOnClickListener { actor ->

            (actor as Image).drawable = if (Random().nextBoolean()) TextureRegionDrawable(game.spriteUtil.reg) else TextureRegionDrawable(game.spriteUtil.ger)

            coroutine?.launch {
                delay(300)
                runGDX { actor.drawable = TextureRegionDrawable(TextureEmpty) }
                countFlow.apply { if (value < 20 ) value++ else runGDX {
                    stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(PedanticScreen::class.java.name) }
                } }
            }
        } }


        coroutine?.launch {
            countFlow.collectIndexed { index, count ->
                countLbl.setText(count)

                questions[(0..questions.lastIndex).random()].apply {
                    questionLbl.setText(question)
                    answer1Lbl.setText(answer1)
                    answer2Lbl.setText(answer2)
                    answer3Lbl.setText(answer3)
                }

                panel.drawable = TextureRegionDrawable(game.spriteUtil.liba.random())

                if (index < 19) {
                    progress.drawable = TextureRegionDrawable(game.spriteUtil.progressList[index.inc()])
                }
            }
        }
    }

    override fun dispose() {
        super.dispose()
        generatorSB.dispose()
        generatorRG.dispose()
        font44.dispose()
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