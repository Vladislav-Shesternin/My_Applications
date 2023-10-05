package finann.promik.technikuss.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import finann.promik.technikuss.game.LibGDXGame
import finann.promik.technikuss.game.utils.TIME_ANIM_SCREEN_ALPHA
import finann.promik.technikuss.game.utils.actor.animHide
import finann.promik.technikuss.game.utils.actor.animShow
import finann.promik.technikuss.game.utils.actor.setOnClickListener
import finann.promik.technikuss.game.utils.advanced.AdvancedScreen
import finann.promik.technikuss.game.utils.advanced.AdvancedStage
import finann.promik.technikuss.game.utils.region

class HelloScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val barbe = Actor()

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.HELLOBACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(barbe)
        barbe.apply {
            setBounds(43f, 122f,687f, 111f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(QuizScreen::class.java.name) }
            }
        }
    }

}