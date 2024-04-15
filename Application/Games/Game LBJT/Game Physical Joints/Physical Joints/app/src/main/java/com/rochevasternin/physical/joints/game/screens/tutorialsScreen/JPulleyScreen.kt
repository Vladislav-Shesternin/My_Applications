package com.rochevasternin.physical.joints.game.screens.tutorialsScreen

import com.rochevasternin.physical.joints.game.LibGDXGame
import com.rochevasternin.physical.joints.game.actors.scroll.tutorial.AScrollPanel_JPulley
import com.rochevasternin.physical.joints.game.manager.SpriteManager
import com.rochevasternin.physical.joints.game.manager.util.SpriteUtil
import com.rochevasternin.physical.joints.game.screens.practicalScreen.Practical_JPulleyScreen
import com.rochevasternin.physical.joints.game.utils.advanced.AdvancedTutorialScreen

class JPulleyScreen(override val game: LibGDXGame): AdvancedTutorialScreen(game) {

    override val title               = "Pulley Joint"
    override val practicalScreenName = Practical_JPulleyScreen::class.java.name

    override var loadableAtlasList   = SpriteManager.EnumAtlas_JointPulley.values().map { it.data }.toMutableList()
    override val loadableTextureList = SpriteManager.EnumTexture_JointPulley.values().map { it.data }.toMutableList()

    override fun loadingAssets() {
        if (isFinishLoading.not()) {
            if (assetManager.update(16)) {
                isFinishLoading = true
                initAssets()

                spriteUtil  = SpriteUtil.JointPulley()
                aScrollPane = AScrollPanel_JPulley(this, spriteUtil as SpriteUtil.JointPulley)

                superShow()
            }
        }
    }

}