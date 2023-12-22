package mobile.jogodobicho.lucky.game.actors

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import mobile.jogodobicho.lucky.game.screens.BullGameScreen
import mobile.jogodobicho.lucky.game.utils.TIME_ANIM_SCREEN_ALPHA
import mobile.jogodobicho.lucky.game.utils.actor.animHide
import mobile.jogodobicho.lucky.game.utils.actor.setOnClickListener
import mobile.jogodobicho.lucky.game.utils.advanced.AdvancedGroup
import mobile.jogodobicho.lucky.game.utils.advanced.AdvancedScreen

var isPoganerToN = true
class AResultGroup(override val screen: AdvancedScreen): AdvancedGroup() {

    private val region  = if (isPoganerToN) screen.game.gameAssets.LEVELPASSED else screen.game.gameAssets.LEVELFAILED
    private val helonel = Image(region)

    override fun addActorsOnGroup() {
        addAndFillActor(helonel)
        addRestart()
        addExit()
        addNext()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun addRestart() {
        val r = Actor()
        addActor(r)
        r.apply {
            setBounds(467f,571f, 145f, 155f)
            setOnClickListener { screen.stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { screen.game.navigationManager.navigate(BullGameScreen::class.java.name) } }
        }
    }

    private fun addExit() {
        val r = Actor()
        addActor(r)
        r.apply {
            setBounds(53f,223f, 449f, 133f)
            setOnClickListener { screen.stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { screen.game.navigationManager.back() } }
        }
    }

    private fun addNext() {
        val r = Actor()
        addActor(r)
        r.apply {
            setBounds(596f,223f, 449f, 133f)
            setOnClickListener { screen.stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { screen.game.navigationManager.navigate(BullGameScreen::class.java.name) } }
        }
    }

    fun upgradularute() {
        helonel.drawable = TextureRegionDrawable(if (isPoganerToN) screen.game.gameAssets.LEVELPASSED else screen.game.gameAssets.LEVELFAILED)
        if (isPoganerToN) screen.game.soundUtil.apply { play(win) }
        else screen.game.soundUtil.apply { play(fail) }
    }

}