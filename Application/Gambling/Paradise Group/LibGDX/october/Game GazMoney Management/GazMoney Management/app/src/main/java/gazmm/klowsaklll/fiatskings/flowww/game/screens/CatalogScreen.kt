package gazmm.klowsaklll.fiatskings.flowww.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
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

class CatalogScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val generator = FreeTypeFontGenerator(Gdx.files.internal(FontPath.Bold))
    private val parameter = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()
    private val font38    = generator.generateFont(parameter.setCharacters(CharType.LATIN).setSize(38))

    private val image    = Image(drawerUtil.getRegion(Color.WHITE))
    private val curentSP = CurentScrollPane(this)
    private val catalog  = Label("Currency catalog", Label.LabelStyle(font38, Color.valueOf("061237")))

    override fun show() {
        stageUI.root.animHide()
//        setBackground(game.spriteUtil.HOMEK.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(catalog, curentSP, image)
        catalog.setBounds(38f, 1376f, 311f, 47f)
        curentSP.setBounds(39f, 136f, 616f, 1230f)
        image.setBounds(0f, 0f, 694f, 154f)

        val b1 = Image(game.spriteUtil._hpre)
        val b2 = Image(game.spriteUtil._cdef)
        val b3 = Image(game.spriteUtil._apre)
        addActors(b1, b2, b3)
        b1.apply {
            setBounds(40f, 29f, 74f, 94f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(HomekScreen::class.java.name, this::class.java.name) } }
        }
        b2.apply {
            setBounds(278f, 29f, 112f, 94f)
//            setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(CatalogScreen::class.java.name) } }
        }
        b3.apply {
            setBounds(553f, 29f, 100f, 94f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(AnalitikaScreen::class.java.name, this::class.java.name) } }
        }
    }

    override fun dispose() {
        super.dispose()
        generator.dispose()
        font38.dispose()
    }

}