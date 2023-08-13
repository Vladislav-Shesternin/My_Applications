package com.plugoya.rosgpb.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.plugoya.rosgpb.game.actors.ABurger
import com.plugoya.rosgpb.game.actors.button.AButton
import com.plugoya.rosgpb.game.actors.button.AButtonStyle
import com.plugoya.rosgpb.game.game
import com.plugoya.rosgpb.game.manager.NavigationManager
import com.plugoya.rosgpb.game.manager.SpriteManager
import com.plugoya.rosgpb.game.musicUtil
import com.plugoya.rosgpb.game.screens.android.MemAndroidScreen
import com.plugoya.rosgpb.game.utils.advanced.AdvancedGroup
import com.plugoya.rosgpb.game.utils.advanced.AdvancedScreen

class NewsScreen: AdvancedScreen() {

    private val iconImage  = Image(SpriteManager.GameRegion.NEWSES.region)
    private val backButton = AButton(AButtonStyle.pibk)

    private lateinit var testAndScr: MemAndroidScreen

    override fun show() {
        musicUtil.volumeLevelFlow.value = 0f
        setBackgrounds(SpriteManager.SplashRegion.BA_KG.region)
        testAndScr = MemAndroidScreen(game.activity)
        testAndScr.show()
        super.show()
    }

    override fun dispose() {
        testAndScr.dispose()
        super.dispose()
        musicUtil.volumeLevelFlow.value = ABurger.musicPercent.toFloat()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addIcon()
        addButtonBack()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedGroup.addIcon() {
        addActor(iconImage)
        iconImage.setBounds(50f, 794f, 663f, 740f)
    }

    private fun AdvancedGroup.addButtonBack() {
        addActor(backButton)
        backButton.apply {
            setBounds(255f, 73f, 243f, 148f)
            setOnClickListener { NavigationManager.back() }
        }
    }

}