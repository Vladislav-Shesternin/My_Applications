package finalizer.masturbaizer.lotos.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import finalizer.masturbaizer.lotos.game.LibGDXGame
import finalizer.masturbaizer.lotos.game.utils.TIME_ANIM_SCREEN_ALPHA
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

class WinnerScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val generatorRG = FreeTypeFontGenerator(Gdx.files.internal(FontPath.ExtraBold))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()
    private val font85      = generatorRG.generateFont(parameter.setCharacters(CharType.NUMBERS).setSize(85))

    private val barbe = Actor()

    private val countLbl    = Label((5..20).random().toString(), Label.LabelStyle(font85, Color.valueOf("55C80E"))).apply {
        setAlignment(Align.right)
    }

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.rezult_backig.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(barbe, countLbl)
        countLbl.setBounds(245f, 516f, 118f, 62f)
        barbe.apply {
            setBounds(42f, 179f,682f, 111f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(VoprosikiScreen::class.java.name) }
            }
        }
    }

}