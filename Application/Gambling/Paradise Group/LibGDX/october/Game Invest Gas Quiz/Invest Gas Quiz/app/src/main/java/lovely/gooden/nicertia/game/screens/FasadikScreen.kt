package lovely.gooden.nicertia.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import lovely.gooden.nicertia.game.LibGDXGame
import lovely.gooden.nicertia.game.utils.TIME_ANIM_SCREEN_ALPHA
import lovely.gooden.nicertia.game.utils.actor.animHide
import lovely.gooden.nicertia.game.utils.actor.animShow
import lovely.gooden.nicertia.game.utils.actor.setOnClickListener
import lovely.gooden.nicertia.game.utils.advanced.AdvancedScreen
import lovely.gooden.nicertia.game.utils.advanced.AdvancedStage
import lovely.gooden.nicertia.game.utils.region

class FasadikScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val barbe = Actor()

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.invest_gaz_quiz_background.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(barbe)
        barbe.apply {
            setBounds(40f, 158f,639f, 104f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(
                    PedanticScreen::class.java.name) }
            }
        }
    }

}