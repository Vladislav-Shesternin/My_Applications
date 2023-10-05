package finann.promik.technikuss.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import finann.promik.technikuss.game.LibGDXGame
import finann.promik.technikuss.game.utils.TIME_ANIM_SCREEN_ALPHA
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

class FinishScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val generatorSB = FreeTypeFontGenerator(Gdx.files.internal(FontPath.SemiBold))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()
    private val font34      = generatorSB.generateFont(parameter.setCharacters(CharType.NUMBERS).setSize(34))

    private val barbe    = Actor()
    private val countLbl = Label(winAnswerCounter.toString(), Label.LabelStyle(font34, Color.WHITE)).apply {
        setAlignment(Align.center)
    }


    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.FINISHBACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(countLbl, barbe)
        countLbl.setBounds(402f, 904f, 39f, 25f)
        barbe.apply {
            setBounds(43f, 191f,687f, 111f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(QuizScreen::class.java.name) }
            }
        }
    }

    override fun dispose() {
        super.dispose()
        generatorSB.dispose()
        font34.dispose()
    }

}