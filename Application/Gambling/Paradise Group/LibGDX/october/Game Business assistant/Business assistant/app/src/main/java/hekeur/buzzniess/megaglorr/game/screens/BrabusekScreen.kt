package hekeur.buzzniess.megaglorr.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import hekeur.buzzniess.megaglorr.game.LibGDXGame
import hekeur.buzzniess.megaglorr.game.utils.TIME_ANIM_SCREEN_ALPHA
import hekeur.buzzniess.megaglorr.game.utils.actor.animHide
import hekeur.buzzniess.megaglorr.game.utils.actor.animShow
import hekeur.buzzniess.megaglorr.game.utils.actor.setOnClickListener
import hekeur.buzzniess.megaglorr.game.utils.advanced.AdvancedScreen
import hekeur.buzzniess.megaglorr.game.utils.advanced.AdvancedStage
import hekeur.buzzniess.megaglorr.game.utils.region

class BrabusekScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val barbe = Actor()

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.fistingbackground.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(barbe)
        barbe.apply {
            setBounds(34f, 97f,545f, 88f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(DarotteScreen::class.java.name) }
            }
        }
    }

}