package investgroup.program.gaz.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
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

class UzumakiScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val generatorLT = FreeTypeFontGenerator(Gdx.files.internal(FontPath.LT))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()

    private val font17 = generatorLT.generateFont(parameter.setCharacters(CharType.NUMBERS.chars+"/P").setSize(17))

    private val babkiList = List(6) { Label("${numStr(1,9,1)} ${numStr(100,999,1)} / ${numStr(1,9,1)} ${numStr(100,999,1)} P", Label.LabelStyle(font17, Color.WHITE)) }
    private val progrList = List(6) { Image(game.spriteUtil.progressoList.random()) }

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.Uzumaki.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        var aNX = 59f
        var aNY = 795f
        babkiList.onEachIndexed { index, lbl ->
            addActor(lbl)
            lbl.setBounds(aNX, aNY, 252f, 15f)

            aNX += 252f+80f
            if (index.inc() % 2 == 0) {
                aNX = 59f
                aNY -= (290f+15f)
            }
        }

        var bNX = 58f
        var bNY = 780f
        progrList.onEachIndexed { index, img ->
            addActor(img)
            img.setBounds(bNX, bNY, 254f, 7f)

            bNX += 254f+79f
            if (index.inc() % 2 == 0) {
                bNX = 58f
                bNY -= (298f+7f)
            }
        }

        val baBeck = Actor()
        addActor(baBeck)
        baBeck.apply {
            setBounds(25f, 1412f, 242f, 97f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                game.navigationManager.back()
            } }
        }

        val bigBeck = Actor()
        addActor(bigBeck)
        bigBeck.apply {
            setBounds(39f, 142f, 626f, 876f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                game.navigationManager.back()
            } }
        }

    }

    override fun dispose() {
        super.dispose()
        generatorLT.dispose()
        font17.dispose()
    }

}