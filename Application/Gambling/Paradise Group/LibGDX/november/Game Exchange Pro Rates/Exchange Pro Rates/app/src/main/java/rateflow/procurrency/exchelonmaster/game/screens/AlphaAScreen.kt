package rateflow.procurrency.exchelonmaster.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Action
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import rateflow.procurrency.exchelonmaster.game.LibGDXGame
import rateflow.procurrency.exchelonmaster.game.Mask
import rateflow.procurrency.exchelonmaster.game.utils.TIME_ANIM_SCREEN_ALPHA
import rateflow.procurrency.exchelonmaster.game.utils.TextureEmpty
import rateflow.procurrency.exchelonmaster.game.utils.actor.animHide
import rateflow.procurrency.exchelonmaster.game.utils.actor.animShow
import rateflow.procurrency.exchelonmaster.game.utils.actor.setOnClickListener
import rateflow.procurrency.exchelonmaster.game.utils.advanced.AdvancedScreen
import rateflow.procurrency.exchelonmaster.game.utils.advanced.AdvancedStage
import rateflow.procurrency.exchelonmaster.game.utils.font.CharType
import rateflow.procurrency.exchelonmaster.game.utils.font.FontPath
import rateflow.procurrency.exchelonmaster.game.utils.font.setCharacters
import rateflow.procurrency.exchelonmaster.game.utils.font.setLinear
import rateflow.procurrency.exchelonmaster.game.utils.font.setSize
import rateflow.procurrency.exchelonmaster.game.utils.region
import rateflow.procurrency.exchelonmaster.game.utils.runGDX
import java.util.Random

class AlphaAScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val generatorSB = FreeTypeFontGenerator(Gdx.files.internal(FontPath.SB))
    private val generatorRG = FreeTypeFontGenerator(Gdx.files.internal(FontPath.RG))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()
    private val font28      = generatorRG.generateFont(parameter.setCharacters(CharType.NUMBERS).setSize(28))
    private val font30      = generatorRG.generateFont(parameter.setCharacters(CharType.ALL).setSize(30))
    private val font36      = generatorSB.generateFont(parameter.setCharacters(CharType.ALL).setSize(36))

    private val countLbl    = Label("", Label.LabelStyle(font28, Color.valueOf("484848"))).apply {
        setAlignment(Align.center)
    }
    private val questionLbl = Label("", Label.LabelStyle(font36, Color.BLACK)).apply {
        setAlignment(Align.center)
        wrap = true
    }
    private val answer1Lbl  = Label("", Label.LabelStyle(font30, Color.valueOf("8B8B8B"))).apply { wrap = true }
    private val answer2Lbl  = Label("", Label.LabelStyle(font30, Color.valueOf("8B8B8B"))).apply { wrap = true }
    private val answer3Lbl  = Label("", Label.LabelStyle(font30, Color.valueOf("8B8B8B"))).apply { wrap = true }
    private val answer1Img  = Image()
    private val answer2Img  = Image()
    private val answer3Img  = Image()

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


    private val mask   = Mask(this, game.spriteUtil.maswa, 2000)
    private val backgr = Image(game.spriteUtil.framwa)
    private val belwa  = Image(game.spriteUtil.belwa)
    private val vrem   = Image(game.spriteUtil.vremachko)
    private val timoha = Image(game.spriteUtil.tuminute)


    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.AlphaA.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addTimachko()

        addActors(countLbl, questionLbl, answer1Lbl, answer2Lbl, answer3Lbl, answer1Img, answer2Img, answer3Img)
        countLbl.setBounds(183f, 1120f, 36f, 21f)
        questionLbl.setBounds(65f, 890f, 596f, 181f)
        answer1Lbl.setBounds(134f, 770f, 527f, 91f)
        answer2Lbl.setBounds(134f, 659f, 527f, 91f)
        answer3Lbl.setBounds(134f, 545f, 527f, 91f)
        answer1Img.setBounds(65f, 769f, 600f, 95f)
        answer2Img.setBounds(65f, 656f, 600f, 95f)
        answer3Img.setBounds(65f, 542f, 600f, 95f)

        val upisalasa = Actor()
        addActor(upisalasa)
        upisalasa.apply {
            setBounds(0f, 0f, 734f, 122f)
            setOnClickListener {
                countFlow.apply { if (value < 20 ) value++ else runGDX {
                    stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(ResultOprosaScreen::class.java.name) }
                } }
            }
        }

        listOf(answer1Img, answer2Img, answer3Img).onEach { it.setOnClickListener { actor ->

            (actor as Image).drawable = if (Random().nextBoolean()) TextureRegionDrawable(game.spriteUtil.good) else TextureRegionDrawable(game.spriteUtil.baad)

            coroutine?.launch {
                delay(300)
                runGDX { actor.drawable = TextureRegionDrawable(TextureEmpty) }
                countFlow.apply { if (value < 20 ) value++ else runGDX {
                    stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(ResultOprosaScreen::class.java.name) }
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

    private fun AdvancedStage.addTimachko() {
        addActors(backgr, mask, vrem, timoha)
        backgr.setBounds(40f, 1222f, 653f, 51f)
        vrem.setBounds(640f, 1226f, 42f, 42f)
        timoha.setBounds(104f, 1237f, 95f, 22f)
        mask.apply {
            setBounds(44f, 1227f, 645f, 43f)
            addActor(belwa)
            belwa.addAction(Actions.sequence(
                Actions.moveBy(-width, 0f, 120f),
                Actions.run { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(ResultOprosaScreen::class.java.name) } }
            ))
        }
    }

    override fun dispose() {
        super.dispose()
        generatorSB.dispose()
        generatorRG.dispose()
        font36.dispose()
        font30.dispose()
        font28.dispose()
    }

    data class Question(
        val question: String,
        val answer1: String,
        val answer2: String,
        val answer3: String,
    )

}