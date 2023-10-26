package investgroup.program.gaz.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import investgroup.program.gaz.game.LibGDXGame
import investgroup.program.gaz.game.utils.TIME_ANIM_SCREEN_ALPHA
import investgroup.program.gaz.game.utils.actor.animHide
import investgroup.program.gaz.game.utils.actor.animShow
import investgroup.program.gaz.game.utils.actor.setOnClickListener
import investgroup.program.gaz.game.utils.advanced.AdvancedScreen
import investgroup.program.gaz.game.utils.advanced.AdvancedStage
import investgroup.program.gaz.game.utils.region

class SecretScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val barbe = Actor()

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.Secret.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(barbe)
        barbe.apply {
            setBounds(39f, 113f,626f, 101f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(TerbalinoScreen::class.java.name) }
            }
        }
    }

}