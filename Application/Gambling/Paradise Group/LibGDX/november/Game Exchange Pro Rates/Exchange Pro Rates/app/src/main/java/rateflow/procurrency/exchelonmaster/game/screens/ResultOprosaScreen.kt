package rateflow.procurrency.exchelonmaster.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import rateflow.procurrency.exchelonmaster.game.LibGDXGame
import rateflow.procurrency.exchelonmaster.game.utils.TIME_ANIM_SCREEN_ALPHA
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

class ResultOprosaScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val barbe = Actor()


    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()
    private val generatorSB = FreeTypeFontGenerator(Gdx.files.internal(FontPath.SB))
    private val generatorRG = FreeTypeFontGenerator(Gdx.files.internal(FontPath.RG))
    private val font28      = generatorRG.generateFont(parameter.setCharacters(CharType.NUMBERS).setSize(28))
    private val font81      = generatorSB.generateFont(parameter.setCharacters(CharType.NUMBERS).setSize(81))

    private val randomN = (1..20).shuffled().random().toString()

    private val countBigLbl    = Label(randomN, Label.LabelStyle(font81, Color.BLACK)).apply { setAlignment(Align.center) }
    private val countLbl    = Label(randomN, Label.LabelStyle(font28, Color.valueOf("7B7B7B"))).apply { setAlignment(Align.center) }

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.ResultOprosa.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(barbe)
        barbe.apply {
            setBounds(0f, 0f,734f, 122f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(AlphaAScreen::class.java.name) }
            }
        }
        addActors(countBigLbl, countLbl)
        countBigLbl.setBounds(237f, 938f, 113f, 59f)
        countLbl.setBounds(377f, 630f, 36f, 21f)
    }

    override fun dispose() {
        super.dispose()
        generatorSB.dispose()
        generatorRG.dispose()
        font81.dispose()
        font28.dispose()
    }

}