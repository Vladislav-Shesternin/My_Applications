package com.fortunetiger.carnival.game.actors

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.fortunetiger.carnival.game.screens.CarnavalGame3Screen
import com.fortunetiger.carnival.game.screens.CarnavalGame4Screen
import com.fortunetiger.carnival.game.screens.CarnavalMenuScreen
import com.fortunetiger.carnival.game.screens.CarnavalMusicScreen
import com.fortunetiger.carnival.game.utils.TIME_ANIM
import com.fortunetiger.carnival.game.utils.actor.animHide
import com.fortunetiger.carnival.game.utils.actor.setOnClickListener
import com.fortunetiger.carnival.game.utils.advanced.AdvancedGroup
import com.fortunetiger.carnival.game.utils.advanced.AdvancedScreen

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
        val names = listOf(
            CarnavalMenuScreen::class.java.name,
            listOf(CarnavalGame3Screen::class.java.name, CarnavalGame4Screen::class.java.name).random(),
            CarnavalMusicScreen::class.java.name,
        )

        var nx = 154f

        names.onEach { sName ->
            val btn = Actor()
            addActor(btn)
            btn.setBounds(nx, 1261f, 233f, 233f)
            nx += 43f + 233f

            btn.setOnClickListener(screen.game.soundUtil) {
                screen.stageUI.root.animHide(TIME_ANIM) { screen.game.navigationManager.navigate(sName) }
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun update(isWin: Boolean) {
        if (isWin) {
            imgBackground.drawable = TextureRegionDrawable(screen.game.allAssets.OGO_SKA)
            screen.game.soundUtil.apply { play(win) }
        } else {
            imgBackground.drawable = TextureRegionDrawable(screen.game.allAssets.FUu_BLA)
            screen.game.soundUtil.apply { play(fail) }
        }
    }

}