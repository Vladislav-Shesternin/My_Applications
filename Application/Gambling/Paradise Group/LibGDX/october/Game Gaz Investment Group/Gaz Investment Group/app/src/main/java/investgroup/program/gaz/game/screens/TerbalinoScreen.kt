package investgroup.program.gaz.game.screens

import investgroup.program.gaz.Spiral
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Label
import investgroup.program.gaz.game.LibGDXGame
import investgroup.program.gaz.game.utils.TIME_ANIM_SCREEN_ALPHA
import investgroup.program.gaz.game.utils.actor.animHide
import investgroup.program.gaz.game.utils.actor.animShow
import investgroup.program.gaz.game.utils.actor.setOnClickListener
import investgroup.program.gaz.game.utils.advanced.AdvancedScreen
import investgroup.program.gaz.game.utils.advanced.AdvancedStage
import investgroup.program.gaz.game.utils.font.CharType
import investgroup.program.gaz.game.utils.font.FontPath
import investgroup.program.gaz.game.utils.font.setCharacters
import investgroup.program.gaz.game.utils.font.setLinear
import investgroup.program.gaz.game.utils.font.setSize
import investgroup.program.gaz.game.utils.numStr
import investgroup.program.gaz.game.utils.region
import investgroup.program.gaz.game.utils.runGDX
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TerbalinoScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val generatorSB = FreeTypeFontGenerator(Gdx.files.internal(FontPath.SB))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()

    private val font75 = generatorSB.generateFont(parameter.setCharacters(CharType.NUMBERS).setSize(75))

    private val babkiLbl = Label(numStr(1000,9999,1), Label.LabelStyle(font75, Color.BLACK))

    private val skoroBude = Spiral(this)

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.Terbalino.region)
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        coroutine?.launch {
            delay(300)

            runGDX {
                addActors(babkiLbl, skoroBude)
                babkiLbl.setBounds(227f, 1250f, 199f, 55f)
                skoroBude.setBounds(37f, 3f, 630f, 693f)

                val added = Actor()
                addActor(added)
                added.apply {
                    setBounds(80f, 878f, 541f, 97f)
                    setOnClickListener {
                        stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                game.navigationManager.navigate(UzumakiScreen::class.java.name, this@TerbalinoScreen::class.java.name)
                        }
                    }
                }
            }
        }
    }

    override fun dispose() {
        super.dispose()
        generatorSB.dispose()
        font75.dispose()
    }

}