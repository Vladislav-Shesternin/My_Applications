package com.polovinka.gurieva.game.screens

import android.net.Uri
import android.widget.VideoView
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.polovinka.gurieva.R
import com.polovinka.gurieva.game.actors.ABurger
import com.polovinka.gurieva.game.actors.button.AButton
import com.polovinka.gurieva.game.actors.button.AButtonStyle
import com.polovinka.gurieva.game.game
import com.polovinka.gurieva.game.manager.NavigationManager
import com.polovinka.gurieva.game.manager.SpriteManager
import com.polovinka.gurieva.game.musicUtil
import com.polovinka.gurieva.game.screens.android.MemAndroidScreen
import com.polovinka.gurieva.game.utils.advanced.AdvancedGroup
import com.polovinka.gurieva.game.utils.advanced.AdvancedScreen

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