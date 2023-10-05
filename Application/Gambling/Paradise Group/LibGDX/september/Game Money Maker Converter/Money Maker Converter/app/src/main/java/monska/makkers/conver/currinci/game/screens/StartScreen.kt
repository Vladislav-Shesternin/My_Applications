package monska.makkers.conver.currinci.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import monska.makkers.conver.currinci.game.LibGDXGame
import monska.makkers.conver.currinci.game.utils.TIME_ANIM_SCREEN_ALPHA
import monska.makkers.conver.currinci.game.utils.actor.animHide
import monska.makkers.conver.currinci.game.utils.actor.animShow
import monska.makkers.conver.currinci.game.utils.actor.setOnClickListener
import monska.makkers.conver.currinci.game.utils.advanced.AdvancedScreen
import monska.makkers.conver.currinci.game.utils.advanced.AdvancedStage

class StartScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val uma = Image(game.spriteUtil.prodojit)
    private val tlt = Image(game.spriteUtil.monika)

    override fun show() {
        stageUI.root.animHide()
//        setUIBackground(SpriteUtil.CommonAssets().BACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(uma, tlt)
        tlt.setBounds(72f, 689f, 476f, 146f)
        uma.apply {
            setBounds(34f, 162f,554f, 89f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(VScreen::class.java.name) }
            }
        }
    }

}