package uniwersal.pictures.present.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import uniwersal.pictures.present.game.LibGDXGame
import uniwersal.pictures.present.game.actors.scroll.BabloScrollPane
import uniwersal.pictures.present.game.utils.Ttime
import uniwersal.pictures.present.game.utils.actor.animHide
import uniwersal.pictures.present.game.utils.actor.animShow
import uniwersal.pictures.present.game.utils.actor.setOnClickListener
import uniwersal.pictures.present.game.utils.advanced.AdvancedScreen
import uniwersal.pictures.present.game.utils.advanced.AdvancedStage
import uniwersal.pictures.present.game.utils.font.CharType
import uniwersal.pictures.present.game.utils.font.FontPath
import uniwersal.pictures.present.game.utils.font.setCharacters
import uniwersal.pictures.present.game.utils.font.setLinear
import uniwersal.pictures.present.game.utils.font.setSize
import uniwersal.pictures.present.game.utils.region

class PrajkeScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val generator = FreeTypeFontGenerator(Gdx.files.internal(FontPath.Bold))
    private val generatorReg = FreeTypeFontGenerator(Gdx.files.internal(FontPath.Regular))
    private val parameter = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()
    private val font57    = generator.generateFont(parameter.setCharacters(CharType.NUMBERS.chars+",.").setSize(57))
    private val font23    = generatorReg.generateFont(parameter.setCharacters(CharType.LATIN).setSize(23))

    private val pipisa = BabloScrollPane(this)

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.Prajke.region)
        super.show()
        stageUI.root.animShow(Ttime) {
            game.ccc = Color.WHITE
        }
    }

    override fun AdvancedStage.addActorsOnStageUI() {

        addActor(pipisa)
        pipisa.setBounds(40f, 329f, 642f, 1073f)

        val b2 = Actor()
        val b3 = Actor()
        addActors(b2, b3)
        b2.apply {
            setBounds(314f, 32f, 103f, 98f)
            setOnClickListener { stageUI.root.animHide(Ttime) { game.navigationManager.navigate(
                SevakjeScreen::class.java.name, this@PrajkeScreen::class.java.name
            ) } }
        }
        b3.apply {
            setBounds(600f, 32f, 96f, 98f)
            setOnClickListener { stageUI.root.animHide(Ttime) { game.navigationManager.navigate(
                PulshjeScreen::class.java.name, this@PrajkeScreen::class.java.name
            ) } }
        }
    }

    override fun dispose() {
        super.dispose()
        generator.dispose()
        generatorReg.dispose()
        font57.dispose()
        font23.dispose()
    }

}