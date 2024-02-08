package com.ottplay.ottpl.game.screens

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.ottplay.ottpl.game.LibGDXGame
import com.ottplay.ottpl.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.ottplay.ottpl.game.utils.actor.animHide
import com.ottplay.ottpl.game.utils.actor.animShow
import com.ottplay.ottpl.game.utils.actor.setBounds
import com.ottplay.ottpl.game.utils.actor.setOnClickListener
import com.ottplay.ottpl.game.utils.advanced.AdvancedScreen
import com.ottplay.ottpl.game.utils.advanced.AdvancedStage
import com.ottplay.ottpl.game.utils.region

class ShopScreen(override val game: LibGDXGame) : AdvancedScreen() {

    companion object {
        var AVIA = AviaType._300
            private set
    }


    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.allAssets.shop.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addBack()
        addAvias()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addBack() {
        val menu = Actor()
        addActor(menu)
        menu.setBounds(36f, 505f, 66f, 66f)

        menu.setOnClickListener(game.soundUtil) {
            //stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                game.navigationManager.back()
            //}
        }
    }

    private fun AdvancedStage.addAvias() {
        arrayOf(
            Vector2(63f, 72f) to Vector2(193f, 223f),
            Vector2(266f, 238f) to Vector2(247f, 285f),
            Vector2(816f, 238f) to Vector2(247f, 285f),
            Vector2(1073f, 72f) to Vector2(193f, 223f),
            Vector2(518f, 25f) to Vector2(275f, 318f),
        ).onEachIndexed { index, data ->
            Actor().also { a ->
                addActor(a)
                a.setBounds(data.first, data.second)
                a.setOnClickListener(game.soundUtil) {
                    AVIA = AviaType.values()[index]
                    //stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                        game.navigationManager.navigate(GameScreen::class.java.name, ShopScreen::class.java.name)
                    //}
                }
            }
        }
    }

    // ---------------------------------------------------
    // classes
    // ---------------------------------------------------

    enum class AviaType(val avia_index: Int) {
        _300(0), _500(1), _700(2), _800(3), _1000(4)
    }

}