package indiaquiz.winterenter.holiwensday.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import indiaquiz.winterenter.holiwensday.R
import indiaquiz.winterenter.holiwensday.game.LibGDXGame
import indiaquiz.winterenter.holiwensday.game.actors.AHeppyImg
import indiaquiz.winterenter.holiwensday.game.actors.label.ASpinningLabel
import indiaquiz.winterenter.holiwensday.game.utils.actor.animHide
import indiaquiz.winterenter.holiwensday.game.utils.actor.animShow
import indiaquiz.winterenter.holiwensday.game.utils.actor.disable
import indiaquiz.winterenter.holiwensday.game.utils.actor.setOnClickListener
import indiaquiz.winterenter.holiwensday.game.utils.advanced.AdvancedScreen
import indiaquiz.winterenter.holiwensday.game.utils.advanced.AdvancedStage
import indiaquiz.winterenter.holiwensday.game.utils.font.FontParameter
import indiaquiz.winterenter.holiwensday.game.utils.region
import java.util.Random

class HappyQuizScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val parameterFont  = FontParameter().setCharacters(FontParameter.CharType.ALL).setSize(30)
    private val generatedFont  = fontGeneratorGaladaRegular.generateFont(parameterFont)
    private val generatedFont2 = fontGeneratorGaladaRegular.generateFont(parameterFont.setSize(33))

    private val assets = game.assets

    private val stringQuestion = game.activity.resources.getStringArray(R.array.questions).toList().shuffled().last()
    private val stringAnswers  = game.activity.resources.getStringArray(R.array.answers).toList().shuffled().takeLast(3)

    private val menuImg  = Image(assets.menuuu)
    private val blurImg  = Image(assets.blure)
    private val blur3Img = Image(assets.blure_3)
    private val answrLbl = Label(stringQuestion, Label.LabelStyle(generatedFont, Color.WHITE))
    private val lbls     = List(3) { ASpinningLabel(this, stringAnswers[it], Label.LabelStyle(generatedFont2, Color.WHITE)) }
    private val happys   = List(3) { AHeppyImg(this) }

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(assets.Menu.region)
        super.show()
        stageUI.root.animShow(0.3f)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addMenu()
        addBlure()
        addBlure3()

        addLbl()
        addLbls()
        addHappys()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addMenu() {
        addActor(menuImg)
        menuImg.setBounds(0f, 310f, 156f, 77f)
        menuImg.setOnClickListener { stageUI.root.animHide(0.3f) { game.navigationManager.back() } }
    }

    private fun AdvancedStage.addBlure() {
        addActor(blurImg)
        blurImg.setBounds(384f, 286f, 732f, 332f)
    }

    private fun AdvancedStage.addBlure3() {
        addActor(blur3Img)
        blur3Img.setBounds(205f, 72f, 1089f, 112f)
        blur3Img.disable()
    }

    private fun AdvancedStage.addLbl() {
        addActor(answrLbl)
        answrLbl.setBounds(394f, 296f, 712f, 312f)
        answrLbl.setAlignment(Align.center)
        answrLbl.wrap = true
    }

    private fun AdvancedStage.addLbls() {
        var nx = 214f
        lbls.onEach {
            it.disable()
            addActor(it)
            it.setBounds(nx, 104f, 285f, 49f)
            nx += 108f+285f
        }
    }

    private fun AdvancedStage.addHappys() {
        var nx = 161f
        happys.onEach {
            addActor(it)
            it.setBounds(nx, 39f, 391f, 178f)
            nx += 393f

            it.setOnClickListener { aa ->
                if (Random().nextBoolean()) it.happy() else it.dehappy()
                stageUI.root.disable()
                aa.addAction(Actions.sequence(
                    Actions.delay(0.6f),
                    Actions.run { game.navigationManager.navigate(this@HappyQuizScreen::class.java.name) }
                ))
            }
        }
    }

}