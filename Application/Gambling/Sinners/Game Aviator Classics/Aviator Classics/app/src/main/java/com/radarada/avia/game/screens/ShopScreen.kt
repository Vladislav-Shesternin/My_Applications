package com.radarada.avia.game.screens

import com.badlogic.gdx.math.Vector2
import com.radarada.avia.game.LibGDXGame
import com.radarada.avia.game.actors.button.AButton
import com.radarada.avia.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.radarada.avia.game.utils.actor.animHide
import com.radarada.avia.game.utils.actor.animShow
import com.radarada.avia.game.utils.actor.setOnClickListener
import com.radarada.avia.game.utils.advanced.AdvancedScreen
import com.radarada.avia.game.utils.advanced.AdvancedStage
import com.radarada.avia.game.utils.region
import com.badlogic.gdx.scenes.scene2d.Actor
import com.radarada.avia.game.utils.actor.setBounds

class ShopScreen(override val game: LibGDXGame) : AdvancedScreen() {

    companion object {
        var AVIA = AviaType._100
            private set
    }


    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.gameAssets.sipos.region)
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
        val menu = AButton(this@ShopScreen, AButton.Static.Type.MENU)
        addActor(menu)
        menu.setBounds(21f, 1257f, 102f, 102f)

        menu.setOnClickListener(game.soundUtil) {
            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }
        }
    }

    private fun AdvancedStage.addAvias() {
        arrayOf(
            Vector2(21f, 868f),
            Vector2(351f, 868f),
            Vector2(186f, 481f),
            Vector2(21f, 83f),
            Vector2(351f, 83f),
        ).onEachIndexed { index, pos ->
            Actor().also { a ->
                addActor(a)
                a.setBounds(pos,Vector2(275f, 347f))
                a.setOnClickListener(game.soundUtil) {
                    AVIA = when(index) {
                        0 -> AviaType._100
                        1 -> AviaType._200
                        2 -> AviaType._1000
                        3 -> AviaType._500
                        4 -> AviaType._850
                        else -> AviaType._100
                    }
                    stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                        game.navigationManager.navigate(GameScreen::class.java.name, ShopScreen::class.java.name)
                    }
                }
            }
        }
    }

    // ---------------------------------------------------
    // classes
    // ---------------------------------------------------

    enum class AviaType(val avia_index: Int) {
        _100(0), _200(1), _500(2), _850(3), _1000(4)
    }

}