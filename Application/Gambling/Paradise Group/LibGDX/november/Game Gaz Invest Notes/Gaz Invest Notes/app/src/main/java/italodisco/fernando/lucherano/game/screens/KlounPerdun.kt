package italodisco.fernando.lucherano.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import italodisco.fernando.lucherano.game.LibGDXGame
import italodisco.fernando.lucherano.game.utils.TIME_ANIM_SCREEN_ALPHA
import italodisco.fernando.lucherano.game.utils.actor.animHide
import italodisco.fernando.lucherano.game.utils.actor.animShow
import italodisco.fernando.lucherano.game.utils.actor.setOnClickListener
import italodisco.fernando.lucherano.game.utils.advanced.AdvancedScreen
import italodisco.fernando.lucherano.game.utils.advanced.AdvancedStage
import italodisco.fernando.lucherano.game.utils.region

class KlounPerdun(override val game: LibGDXGame): AdvancedScreen() {

    private val barbe = Actor()

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.Barboval.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(barbe)
        barbe.apply {
            setBounds(101f, 70f,385f, 100f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(FraoEngel::class.java.name) }
            }
        }
    }

}