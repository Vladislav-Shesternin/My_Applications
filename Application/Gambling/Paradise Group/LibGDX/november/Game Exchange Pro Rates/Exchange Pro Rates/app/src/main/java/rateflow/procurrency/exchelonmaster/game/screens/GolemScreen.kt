package rateflow.procurrency.exchelonmaster.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import rateflow.procurrency.exchelonmaster.game.LibGDXGame
import rateflow.procurrency.exchelonmaster.game.utils.TIME_ANIM_SCREEN_ALPHA
import rateflow.procurrency.exchelonmaster.game.utils.actor.animHide
import rateflow.procurrency.exchelonmaster.game.utils.actor.animShow
import rateflow.procurrency.exchelonmaster.game.utils.actor.setOnClickListener
import rateflow.procurrency.exchelonmaster.game.utils.advanced.AdvancedScreen
import rateflow.procurrency.exchelonmaster.game.utils.advanced.AdvancedStage
import rateflow.procurrency.exchelonmaster.game.utils.region

class GolemScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val barbe = Actor()

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.Golem.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(barbe)
        barbe.apply {
            setBounds(40f, 158f,639f, 104f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(AlphaAScreen::class.java.name) }
            }
        }
    }

}