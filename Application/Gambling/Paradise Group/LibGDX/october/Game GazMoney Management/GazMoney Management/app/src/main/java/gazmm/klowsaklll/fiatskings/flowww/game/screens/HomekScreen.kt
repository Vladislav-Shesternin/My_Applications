package gazmm.klowsaklll.fiatskings.flowww.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Label
import gazmm.klowsaklll.fiatskings.flowww.game.LibGDXGame
import gazmm.klowsaklll.fiatskings.flowww.game.actors.scroll.CurentScrollPane
import gazmm.klowsaklll.fiatskings.flowww.game.utils.TIME_ANIM_SCREEN_ALPHA
import gazmm.klowsaklll.fiatskings.flowww.game.utils.actor.animHide
import gazmm.klowsaklll.fiatskings.flowww.game.utils.actor.animShow
import gazmm.klowsaklll.fiatskings.flowww.game.utils.actor.setOnClickListener
import gazmm.klowsaklll.fiatskings.flowww.game.utils.advanced.AdvancedScreen
import gazmm.klowsaklll.fiatskings.flowww.game.utils.advanced.AdvancedStage
import gazmm.klowsaklll.fiatskings.flowww.game.utils.font.CharType
import gazmm.klowsaklll.fiatskings.flowww.game.utils.font.FontPath
import gazmm.klowsaklll.fiatskings.flowww.game.utils.font.setCharacters
import gazmm.klowsaklll.fiatskings.flowww.game.utils.font.setLinear
import gazmm.klowsaklll.fiatskings.flowww.game.utils.font.setSize
import gazmm.klowsaklll.fiatskings.flowww.game.utils.numStr
import gazmm.klowsaklll.fiatskings.flowww.game.utils.region

class HomekScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val generator = FreeTypeFontGenerator(Gdx.files.internal(FontPath.Bold))
    private val generatorReg = FreeTypeFontGenerator(Gdx.files.internal(FontPath.Regular))
    private val parameter = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()
    private val font57    = generator.generateFont(parameter.setCharacters(CharType.NUMBERS.chars+",.").setSize(57))
    private val font23    = generatorReg.generateFont(parameter.setCharacters(CharType.LATIN).setSize(23))

    private val curentSP       = CurentScrollPane(this)
    private val balanceageType = Label("Russian rubles", Label.LabelStyle(font23, Color.WHITE))
    private val balanceage     = Label("${numStr(100, 999,1)}.${numStr(0,9,3)},${numStr(100, 999,1)}", Label.LabelStyle(font57, Color.WHITE))

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.HOMEK.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(balanceageType, balanceage, curentSP)
        balanceageType.setBounds(77f, 1195f, 559f, 28f)
        balanceage.setBounds(77f, 1112f, 542f, 70f)
        curentSP.setBounds(39f, 142f, 616f, 809f)

        val b2 = Actor()
        val b3 = Actor()
        addActors(b2, b3)
        b2.apply {
            setBounds(277f, 30f, 112f, 94f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(CatalogScreen::class.java.name, this::class.java.name) } }
        }
        b3.apply {
            setBounds(553f, 30f, 100f, 94f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(AnalitikaScreen::class.java.name, this::class.java.name) } }
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