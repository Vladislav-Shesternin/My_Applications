package aviator.original.fly.game.actors

import aviator.original.fly.game.screens.AviatorPlaygraundScreen
import aviator.original.fly.game.utils.TIME_ANIM_SCREEN_ALPHA
import aviator.original.fly.game.utils.actor.animHide
import aviator.original.fly.game.utils.actor.setOnClickListener
import aviator.original.fly.game.utils.advanced.AdvancedGroup
import aviator.original.fly.game.utils.advanced.AdvancedScreen
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable

class AResultGroup(override val screen: AdvancedScreen): AdvancedGroup() {

    private val result  = Image()

    override fun addActorsOnGroup() {
        addAndFillActor(result)
        addBtns()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun addBtns() {
        val restart = Actor()
        val menu    = Actor()
        val exit    = Actor()
        val next    = Actor()

        addActors(restart, menu, exit, next)

        restart.apply {
            setBounds(131f, 423f, 99f, 99f)
            setOnClickListener(screen.game.soundUtil) { screen.stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { screen.game.navigationManager.navigate(AviatorPlaygraundScreen::class.java.name) } }
        }
        menu.apply {
            setBounds(365f, 423f, 99f, 99f)
            setOnClickListener(screen.game.soundUtil) { screen.stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { screen.game.navigationManager.back() } }
        }
        exit.apply {
            setBounds(30f, 68f, 235f, 89f)
            setOnClickListener(screen.game.soundUtil) { screen.stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { screen.game.navigationManager.back() } }
        }
        next.apply {
            setBounds(331f, 68f, 235f, 89f)
            setOnClickListener(screen.game.soundUtil) { screen.stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { screen.game.navigationManager.navigate(AviatorPlaygraundScreen::class.java.name) } }
        }
    }



    fun update(isWin: Boolean) {
        if (isWin) {
            result.drawable = TextureRegionDrawable(screen.game.gameAssets.AviatorResultTrue)
        } else {
            result.drawable = TextureRegionDrawable(screen.game.gameAssets.AviatorResultFalse)
        }
    }

}