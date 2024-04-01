package com.winterria.jumpilo.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.winterria.jumpilo.game.LibGDXGame
import com.winterria.jumpilo.game.utils.TIME_ANIM
import com.winterria.jumpilo.game.utils.actor.animHide
import com.winterria.jumpilo.game.utils.actor.setOnClickListener
import com.winterria.jumpilo.game.utils.advanced.AdvancedScreen
import com.winterria.jumpilo.game.utils.advanced.AdvancedStage
import com.winterria.jumpilo.game.utils.region

class MenuScreen(override val game: LibGDXGame): AdvancedScreen() {

    companion object {
        var indexSnow = 0
            private set
    }

    private val panelImg = Image(game.allAssets.panel)
    private val snowImg  = Image(game.allAssets.snows[indexSnow])
    private val left     = Actor()
    private val right    = Actor()

    override fun show() {
        setBackBackground(game.loaderAssets.WINTER.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addPanel()
        addSnow()
        addBtns()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedStage.addPanel() {
        addActors(panelImg)
        panelImg.setBounds(530f, 339f, 860f, 401f)
    }

    private fun AdvancedStage.addSnow() {
        addActor(snowImg)
        snowImg.setBounds(795f, 375f, 330f, 330f)

        snowImg.setOnClickListener(game.soundUtil) {
            stageUI.root.animHide(TIME_ANIM) {
                game.navigationManager.navigate(GameScreen::class.java.name, MenuScreen::class.java.name)
            }
        }
    }

    private fun AdvancedStage.addBtns() {
        addActors(left, right)
        left.setBounds(530f, 464f, 110f, 152f)
        left.setOnClickListener(game.soundUtil) {
            indexSnow = if (indexSnow-1 >= 0) indexSnow-1 else game.allAssets.snows.lastIndex
            snowImg.drawable = TextureRegionDrawable(game.allAssets.snows[indexSnow])
        }
        right.setBounds(1280f, 464f, 110f, 152f)
        right.setOnClickListener(game.soundUtil) {
            indexSnow = if (indexSnow+1 <= game.allAssets.snows.lastIndex) indexSnow+1 else 0
            snowImg.drawable = TextureRegionDrawable(game.allAssets.snows[indexSnow])
        }
    }

}