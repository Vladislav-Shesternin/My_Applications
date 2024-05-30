package com.rochevasternin.physical.joints.game.screens.tutorialsScreen

import com.rochevasternin.physical.joints.game.LibGDXGame
import com.rochevasternin.physical.joints.game.actors.scroll.tutorial.AScrollPanel_JWheel
import com.rochevasternin.physical.joints.game.manager.SpriteManager
import com.rochevasternin.physical.joints.game.manager.util.SpriteUtil
import com.rochevasternin.physical.joints.game.screens.practicalScreen.Practical_JWheelScreen
import com.rochevasternin.physical.joints.game.utils.advanced.AdvancedTutorialScreen

class JWheelScreen(override val game: LibGDXGame): AdvancedTutorialScreen(game) {

    override val title               = "Wheel Joint"
    override val practicalScreenName = Practical_JWheelScreen::class.java.name

    override var loadableAtlasList   = SpriteManager.EnumAtlas_JointWheel.values().map { it.data }.toMutableList()
    override val loadableTextureList = SpriteManager.EnumTexture_JointWheel.values().map { it.data }.toMutableList()

    override fun loadingAssets() {
        if (isFinishLoading.not()) {
            if (assetManager.update(16)) {
                isFinishLoading = true
                initAssets()

                spriteUtil  = SpriteUtil.JointWheel()
                aScrollPane = AScrollPanel_JWheel(this, spriteUtil as SpriteUtil.JointWheel)

                superShow()
            }
        }
    }

}