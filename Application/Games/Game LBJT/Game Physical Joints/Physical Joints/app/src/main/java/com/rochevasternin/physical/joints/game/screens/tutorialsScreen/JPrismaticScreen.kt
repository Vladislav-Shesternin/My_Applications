package com.rochevasternin.physical.joints.game.screens.tutorialsScreen

import com.rochevasternin.physical.joints.game.LibGDXGame
import com.rochevasternin.physical.joints.game.actors.scroll.tutorial.AScrollPanel_JPrismatic
import com.rochevasternin.physical.joints.game.manager.SpriteManager
import com.rochevasternin.physical.joints.game.manager.util.SpriteUtil
import com.rochevasternin.physical.joints.game.screens.practicalScreen.Practical_JPrismaticScreen
import com.rochevasternin.physical.joints.game.utils.advanced.AdvancedTutorialScreen

class JPrismaticScreen(override val game: LibGDXGame): AdvancedTutorialScreen(game) {

    override val title               = "Prismatic Joint"
    override val practicalScreenName = Practical_JPrismaticScreen::class.java.name

    override var loadableAtlasList   = SpriteManager.EnumAtlas_JointPrismatic.values().map { it.data }.toMutableList()
    override val loadableTextureList = SpriteManager.EnumTexture_JointPrismatic.values().map { it.data }.toMutableList()

    override fun loadingAssets() {
        if (isFinishLoading.not()) {
            if (assetManager.update(16)) {
                isFinishLoading = true
                initAssets()

                spriteUtil  = SpriteUtil.JointPrismatic()
                aScrollPane = AScrollPanel_JPrismatic(this, spriteUtil as SpriteUtil.JointPrismatic)

                superShow()
            }
        }
    }

}