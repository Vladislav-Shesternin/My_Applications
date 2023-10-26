package aiebu.kakono.tutokazalos.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import aiebu.kakono.tutokazalos.game.LibGDXGame
import aiebu.kakono.tutokazalos.game.utils.TIME_ANIM_SCREEN_ALPHA
import aiebu.kakono.tutokazalos.game.utils.actor.animHide
import aiebu.kakono.tutokazalos.game.utils.actor.animShow
import aiebu.kakono.tutokazalos.game.utils.actor.setOnClickListener
import aiebu.kakono.tutokazalos.game.utils.advanced.AdvancedScreen
import aiebu.kakono.tutokazalos.game.utils.advanced.AdvancedStage
import aiebu.kakono.tutokazalos.game.utils.region

class VstrechaScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val barbe = Actor()

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.BANAKIR.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(barbe)
        barbe.apply {
            setBounds(44f, 103f,638f, 103f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(SaskapotScreen::class.java.name) }
            }
        }
    }

}