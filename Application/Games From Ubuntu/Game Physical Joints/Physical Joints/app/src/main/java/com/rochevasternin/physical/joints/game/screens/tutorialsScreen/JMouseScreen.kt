package com.rochevasternin.physical.joints.game.screens.tutorialsScreen

import com.rochevasternin.physical.joints.game.LibGDXGame
import com.rochevasternin.physical.joints.game.actors.scroll.tutorial.AScrollPanel_JMouse
import com.rochevasternin.physical.joints.game.manager.SpriteManager
import com.rochevasternin.physical.joints.game.manager.util.SpriteUtil
import com.rochevasternin.physical.joints.game.screens.practicalScreen.Practical_JMouseScreen
import com.rochevasternin.physical.joints.game.utils.advanced.AdvancedTutorialScreen

class JMouseScreen(override val game: LibGDXGame): AdvancedTutorialScreen(game) {

    override val title               = "Mouse Joint"
    override val practicalScreenName = Practical_JMouseScreen::class.java.name

    override var loadableAtlasList   = SpriteManager.EnumAtlas_JointMouse.values().map { it.data }.toMutableList()
    override val loadableTextureList = SpriteManager.EnumTexture_JointMouse.values().map { it.data }.toMutableList()

    override fun loadingAssets() {
        if (isFinishLoading.not()) {
            if (assetManager.update(16)) {
                isFinishLoading = true
                initAssets()

                spriteUtil  = SpriteUtil.JointMouse()
                aScrollPane = AScrollPanel_JMouse(this, spriteUtil as SpriteUtil.JointMouse)

                superShow()
            }
        }
    }

}