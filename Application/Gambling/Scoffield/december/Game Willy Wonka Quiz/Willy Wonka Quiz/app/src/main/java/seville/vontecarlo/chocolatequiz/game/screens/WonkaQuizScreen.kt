package seville.vontecarlo.chocolatequiz.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import seville.vontecarlo.chocolatequiz.R
import seville.vontecarlo.chocolatequiz.game.LibGDXGame
import seville.vontecarlo.chocolatequiz.game.actors.AAnswerImg
import seville.vontecarlo.chocolatequiz.game.actors.label.ASpinningLabel
import seville.vontecarlo.chocolatequiz.game.manager.SpriteManager
import seville.vontecarlo.chocolatequiz.game.utils.actor.animHide
import seville.vontecarlo.chocolatequiz.game.utils.actor.animShow
import seville.vontecarlo.chocolatequiz.game.utils.actor.disable
import seville.vontecarlo.chocolatequiz.game.utils.actor.setOnClickListener
import seville.vontecarlo.chocolatequiz.game.utils.advanced.AdvancedScreen
import seville.vontecarlo.chocolatequiz.game.utils.advanced.AdvancedStage
import seville.vontecarlo.chocolatequiz.game.utils.font.FontParameter
import seville.vontecarlo.chocolatequiz.game.utils.region
import java.util.Random

class WonkaQuizScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val parameterFont  = FontParameter().setCharacters(FontParameter.CharType.ALL)
    private val generatedFontQuestion  = fontGeneratorJudsonBold.generateFont(parameterFont.setSize(36))
    private val generatedFontAnswer    = fontGeneratorJudsonBold.generateFont(parameterFont.setSize(55))

    private val assets = game.assets

    private val id            = (0..19).random()
    private val wonkaQuestion = game.activity.resources.getStringArray(R.array.wonka_quiz)[id]
    private val wonkaAnswer   = Answer.answers[id].toList().shuffled()

    private val blurImg  = Image(assets.question)
    private val question = Label(wonkaQuestion, Label.LabelStyle(generatedFontQuestion, Color.WHITE))
    private val btns     = List(3) { Image(assets.answer) }
    private val lbls     = List(3) { ASpinningLabel(this, wonkaAnswer[it], Label.LabelStyle(generatedFontAnswer, Color.WHITE)) }
    private val answers  = List(3) { AAnswerImg(this) }

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(SpriteManager.EnumTexture.BACKGROUND.data.texture.region)
        super.show()
        stageUI.root.animShow(0.33f)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addBlur()
        addQuestion()
        addBtns()
        addLbls()
        addAnswers()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addBlur() {
        addActor(blurImg)
        blurImg.setBounds(10f, 982f, 780f, 504f)
    }

    private fun AdvancedStage.addQuestion() {
        addActor(question)
        question.apply {
            setBounds(33f, 999f, 741f, 475f)
            setAlignment(Align.center)
            wrap = true
        }
    }

    private fun AdvancedStage.addBtns() {
        var ny = 720f
        btns.onEach {
            addActor(it)
            it.setBounds(31f, ny, 739f, 159f)
            ny -= 80f+159f
        }
    }

    private fun AdvancedStage.addLbls() {
        var ny = 765f
        lbls.onEach {
            addActor(it)
            it.setBounds(36f, ny, 729f, 70f)
            ny -= 169f+70f
        }
    }

    private fun AdvancedStage.addAnswers() {
        var ny = 701f
        answers.onEach {
            addActor(it)
            it.setBounds(0f, ny, 800f, 199f)
            ny -= 40f+199f

            it.setOnClickListener { aa ->
                if (Random().nextBoolean()) it.wonkaPr() else it.wonkaDa()
                stageUI.root.disable()
                aa.addAction(Actions.sequence(
                    Actions.delay(0.55f),
                    Actions.run { game.navigationManager.navigate(WonkaQuizScreen::class.java.name) }
                ))
            }
        }
    }

}