package com.vbbb.time.game.game.screens

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.vbbb.time.game.game.actors.scroll.HorizontalGroup
import com.vbbb.time.game.game.manager.NavigationManager
import com.vbbb.time.game.game.manager.SpriteManager
import com.vbbb.time.game.game.utils.actor.setBounds
import com.vbbb.time.game.game.utils.actor.setOnClickListener
import com.vbbb.time.game.game.utils.advanced.AdvancedGroup
import com.vbbb.time.game.game.utils.advanced.AdvancedScreen
import com.vbbb.time.game.util.Once
import com.vbbb.time.game.util.log
import com.vbbb.time.game.game.utils.Layout.Levels as LL

class LevelsScreen: AdvancedScreen() {

    private val horizontalGroup = HorizontalGroup(50f)
    private val scroll          = ScrollPane(horizontalGroup)
    private val levelList       = listOf(
        SpriteManager.GameRegion.LEVEL_1.region,
        SpriteManager.GameRegion.LEVEL_2.region,
        SpriteManager.GameRegion.LEVEL_3.region,
        SpriteManager.GameRegion.LEVEL_4.region,
    )


    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.UKRAINE.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addScroll()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addScroll() {
        addActor(scroll)
        scroll.setBounds(LL.scroll)

        val levels = listOf(
            Level_1_Screen(),
            Level_2_Screen(),
            Level_3_Screen(),
            Level_4_Screen(),
        )
        val onceClick = Once()
        levelList.onEachIndexed { index, regionLevel ->
            Image(regionLevel).also { imageLevel ->
                imageLevel.setSize(1118f, 502f)
                horizontalGroup.addActor(imageLevel)

                imageLevel.addListener(object : ClickListener(){
                    override fun clicked(event: InputEvent?, x: Float, y: Float) {
                        onceClick.once { NavigationManager.navigate(levels[index], LevelsScreen()) }
                    }
                })
            }
        }

    }

}