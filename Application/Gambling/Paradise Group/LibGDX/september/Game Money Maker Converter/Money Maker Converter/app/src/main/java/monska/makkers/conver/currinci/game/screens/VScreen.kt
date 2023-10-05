package monska.makkers.conver.currinci.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import monska.makkers.conver.currinci.game.LibGDXGame
import monska.makkers.conver.currinci.game.actors.scroll.ValutaScrollPane
import monska.makkers.conver.currinci.game.utils.TIME_ANIM_SCREEN_ALPHA
import monska.makkers.conver.currinci.game.utils.actor.animHide
import monska.makkers.conver.currinci.game.utils.actor.animShow
import monska.makkers.conver.currinci.game.utils.actor.setOnClickListener
import monska.makkers.conver.currinci.game.utils.advanced.AdvancedScreen
import monska.makkers.conver.currinci.game.utils.advanced.AdvancedStage
import monska.makkers.conver.currinci.game.utils.font.CharType
import monska.makkers.conver.currinci.game.utils.font.FontPath
import monska.makkers.conver.currinci.game.utils.font.setCharacters
import monska.makkers.conver.currinci.game.utils.font.setLinear
import monska.makkers.conver.currinci.game.utils.font.setSize

class VScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val generator = FreeTypeFontGenerator(Gdx.files.internal(FontPath.Jaldi))
    private val parameter = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()
    private val font51    = generator.generateFont(parameter.setCharacters(CharType.LATIN).setSize(51))

    private val terms   = Actor()
    private val privacy = Actor()

    private val valutaList = ValutaScrollPane(this, font51)

    private val incon = Image(game.spriteUtil.couchas)

    override fun show() {
        stageUI.root.animHide()
//        setUIBackground(drawerUtil.getRegion(Color.BLUE))
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(incon)
        addActors(terms, privacy, valutaList)
        incon.setBounds(27f, 882f, 567f, 421f)

        terms.apply {
            setBounds(34f, 1226f, 128f, 76f)
            setOnClickListener { valuta?.terms?.let { url -> game.activity.webViewFragment.openInWeb(url) } }
        }
        privacy.apply {
            setBounds(433f, 1226f, 152f, 76f)
            setOnClickListener { valuta?.privacy?.let { url -> game.activity.webViewFragment.openInWeb(url) } }
        }
        valutaList.setBounds(35f, 0f, 553f, 870f)
    }

    override fun dispose() {
        super.dispose()
        generator.dispose()
        font51.dispose()
    }

}