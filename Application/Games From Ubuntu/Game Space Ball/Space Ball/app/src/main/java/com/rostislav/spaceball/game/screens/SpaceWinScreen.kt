package com.rostislav.spaceball.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.rostislav.spaceball.game.GdxGame
import com.rostislav.spaceball.game.utils.TIME_ANIM_ALPHA
import com.rostislav.spaceball.game.utils.actor.animHide
import com.rostislav.spaceball.game.utils.actor.animShow
import com.rostislav.spaceball.game.utils.actor.setOnClickListener
import com.rostislav.spaceball.game.utils.advanced.AdvancedScreen
import com.rostislav.spaceball.game.utils.advanced.AdvancedStage
import com.rostislav.spaceball.game.utils.region

class SpaceWinScreen(override val game: GdxGame): AdvancedScreen() {

    // Actor
    private val winImg = Image(game.assetsAllUtil.WIN)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.assetsLoaderUtil.backgrounds.random().region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addWinImg()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addWinImg() {
        addActor(winImg)
        winImg.setBounds(137f, 596f, 806f, 728f)

        val menu = Actor()
        val rest = Actor()
        addActors(menu, rest)
        menu.apply {
            setBounds(201f, 641f, 210f, 97f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM_ALPHA) {
                    game.navigationManager.back()
                }
            }
        }
        rest.apply {
            setBounds(691f, 641f, 165f, 97f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM_ALPHA) {
                    AbstractGameScreen.level = (0..3).random()
                    game.navigationManager.navigate(AbstractGameScreen::class.java.name)
                }
            }
        }

    }

}