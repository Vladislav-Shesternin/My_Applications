package pro.ultimate.saver.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import pro.ultimate.saver.game.LibGDXGame
import pro.ultimate.saver.game.utils.TIME_ANIM_SCREEN_ALPHA
import pro.ultimate.saver.game.utils.actor.animHide
import pro.ultimate.saver.game.utils.actor.animShow
import pro.ultimate.saver.game.utils.actor.setOnClickListener
import pro.ultimate.saver.game.utils.advanced.AdvancedScreen
import pro.ultimate.saver.game.utils.advanced.AdvancedStage
import pro.ultimate.saver.game.utils.region

class BalabolinoScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val barbe = Actor()

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.Saver.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(barbe)
        barbe.apply {
            setBounds(20f, 58f,320f, 52f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(MasakaScreen::class.java.name) }
            }
        }
    }

}