package gazmm.klowsaklll.fiatskings.flowww.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import gazmm.klowsaklll.fiatskings.flowww.game.LibGDXGame
import gazmm.klowsaklll.fiatskings.flowww.game.utils.TIME_ANIM_SCREEN_ALPHA
import gazmm.klowsaklll.fiatskings.flowww.game.utils.actor.animHide
import gazmm.klowsaklll.fiatskings.flowww.game.utils.actor.animShow
import gazmm.klowsaklll.fiatskings.flowww.game.utils.actor.setOnClickListener
import gazmm.klowsaklll.fiatskings.flowww.game.utils.advanced.AdvancedScreen
import gazmm.klowsaklll.fiatskings.flowww.game.utils.advanced.AdvancedStage
import gazmm.klowsaklll.fiatskings.flowww.game.utils.region

class StartScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val uma = Actor()

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.BAPALAH.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(uma)
        uma.apply {
            setBounds(38f, 136f,616f, 100f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(HomekScreen::class.java.name) }
            }
        }
    }

}