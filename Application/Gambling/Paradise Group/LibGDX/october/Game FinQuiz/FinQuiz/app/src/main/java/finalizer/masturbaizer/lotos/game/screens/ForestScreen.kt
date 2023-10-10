package finalizer.masturbaizer.lotos.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import finalizer.masturbaizer.lotos.game.LibGDXGame
import finalizer.masturbaizer.lotos.game.utils.TIME_ANIM_SCREEN_ALPHA
import finalizer.masturbaizer.lotos.game.utils.actor.animHide
import finalizer.masturbaizer.lotos.game.utils.actor.animShow
import finalizer.masturbaizer.lotos.game.utils.actor.setOnClickListener
import finalizer.masturbaizer.lotos.game.utils.advanced.AdvancedScreen
import finalizer.masturbaizer.lotos.game.utils.advanced.AdvancedStage
import finalizer.masturbaizer.lotos.game.utils.region

class ForestScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val barbe = Actor()

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.f_bacgro.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(barbe)
        barbe.apply {
            setBounds(42f, 121f,682f, 110f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(VoprosikiScreen::class.java.name) }
            }
        }
    }

}