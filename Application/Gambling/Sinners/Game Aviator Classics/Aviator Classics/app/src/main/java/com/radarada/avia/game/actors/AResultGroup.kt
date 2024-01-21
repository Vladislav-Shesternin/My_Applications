package com.radarada.avia.game.actors

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.radarada.avia.game.screens.GameScreen
import com.radarada.avia.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.radarada.avia.game.utils.actor.animHide
import com.radarada.avia.game.utils.actor.setOnClickListener
import com.radarada.avia.game.utils.advanced.AdvancedGroup
import com.radarada.avia.game.utils.advanced.AdvancedScreen

class AResultGroup(override val screen: AdvancedScreen): AdvancedGroup() {

    private val imgBackground = Image()

    override fun addActorsOnGroup() {
        touchable = Touchable.disabled
        addAndFillActor(imgBackground)
        addBtns()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun addBtns() {
        val menu    = Actor()
        val next    = Actor()

        addActors(menu, next)

        menu.apply {
            setBounds(118f, 405f, 102f, 102f)
            setOnClickListener(screen.game.soundUtil) {
                screen.stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { screen.game.navigationManager.back() }
            }
        }
        next.apply {
            setBounds(278f, 401f, 252f, 110f)
            setOnClickListener(screen.game.soundUtil) {
                screen.stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { screen.game.navigationManager.navigate(GameScreen::class.java.name) }
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun update(isWin: Boolean) {
        if (isWin) {
            imgBackground.drawable = TextureRegionDrawable(screen.game.gameAssets.win)
            screen.game.soundUtil.apply { play(bonus) }
        } else {
            imgBackground.drawable = TextureRegionDrawable(screen.game.gameAssets.Fail)
            screen.game.soundUtil.apply { play(lose) }
        }
    }

}