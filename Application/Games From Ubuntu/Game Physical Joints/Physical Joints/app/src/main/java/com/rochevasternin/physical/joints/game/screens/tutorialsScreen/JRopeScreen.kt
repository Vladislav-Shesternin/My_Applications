package com.rochevasternin.physical.joints.game.screens.tutorialsScreen

import com.rochevasternin.physical.joints.game.LibGDXGame
import com.rochevasternin.physical.joints.game.actors.scroll.tutorial.AScrollPanel_JRope
import com.rochevasternin.physical.joints.game.manager.SpriteManager
import com.rochevasternin.physical.joints.game.manager.util.SpriteUtil
import com.rochevasternin.physical.joints.game.screens.practicalScreen.Practical_JRopeScreen
import com.rochevasternin.physical.joints.game.utils.advanced.AdvancedTutorialScreen

class JRopeScreen(override val game: LibGDXGame): AdvancedTutorialScreen(game) {

    override val title               = "Rope Joint"
    override val practicalScreenName = Practical_JRopeScreen::class.java.name

    override var loadableAtlasList   = SpriteManager.EnumAtlas_JointRope.values().map { it.data }.toMutableList()
    override val loadableTextureList = SpriteManager.EnumTexture_JointRope.values().map { it.data }.toMutableList()

    override fun loadingAssets() {
        if (isFinishLoading.not()) {
            if (assetManager.update(16)) {
                isFinishLoading = true
                initAssets()

                spriteUtil  = SpriteUtil.JointRope()
                aScrollPane = AScrollPanel_JRope(this, spriteUtil as SpriteUtil.JointRope)

                superShow()
            }
        }
    }

}