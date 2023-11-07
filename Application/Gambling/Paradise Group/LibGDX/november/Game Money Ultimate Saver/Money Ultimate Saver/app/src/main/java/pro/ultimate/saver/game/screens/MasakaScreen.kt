package pro.ultimate.saver.game.screens

import pro.ultimate.saver.Spiral
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import pro.ultimate.saver.game.LibGDXGame
import pro.ultimate.saver.game.utils.TIME_ANIM_SCREEN_ALPHA
import pro.ultimate.saver.game.utils.actor.animHide
import pro.ultimate.saver.game.utils.actor.animShow
import pro.ultimate.saver.game.utils.actor.setOnClickListener
import pro.ultimate.saver.game.utils.advanced.AdvancedScreen
import pro.ultimate.saver.game.utils.advanced.AdvancedStage
import pro.ultimate.saver.game.utils.font.CharType
import pro.ultimate.saver.game.utils.font.FontPath
import pro.ultimate.saver.game.utils.font.setCharacters
import pro.ultimate.saver.game.utils.font.setLinear
import pro.ultimate.saver.game.utils.font.setSize
import pro.ultimate.saver.game.utils.numStr
import pro.ultimate.saver.game.utils.region

class MasakaScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val generatorSB = FreeTypeFontGenerator(Gdx.files.internal(FontPath.SB))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()

    private val font29 = generatorSB.generateFont(parameter.setCharacters(CharType.NUMBERS.chars+"Q").setSize(29))
    private val font21 = generatorSB.generateFont(parameter.setCharacters(CharType.NUMBERS.chars+",").setSize(21))

    private val babLbl = Label(numStr(1000,9999,1)+"Q", Label.LabelStyle(font29, Color.WHITE)).apply { setAlignment(Align.right) }
    private val pluLbl = Label(numStr(1000,9999,1)+",00", Label.LabelStyle(font21, Color.WHITE))
    private val minLbl = Label(numStr(1000,9999,1)+",00", Label.LabelStyle(font21, Color.WHITE))

    private val skoroBude = Spiral(this)

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.Mesac.region)
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(babLbl, pluLbl, minLbl, skoroBude)
        babLbl.setBounds(156f, 626f, 163f, 21f)
        pluLbl.setBounds(62f, 577f, 84f, 15f)
        minLbl.setBounds(222f, 577f, 84f, 15f)

        skoroBude.setBounds(20f, 110f, 322f, 327f)

        val added = Actor()
        addActor(added)
        added.apply {
            setBounds(152f, 22f, 60f, 60f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                    game.navigationManager.navigate(HuedrochevoScreen::class.java.name, this@MasakaScreen::class.java.name)
                }
            }
        }
    }

    override fun dispose() {
        super.dispose()
        generatorSB.dispose()
        font29.dispose()
        font21.dispose()
    }

}