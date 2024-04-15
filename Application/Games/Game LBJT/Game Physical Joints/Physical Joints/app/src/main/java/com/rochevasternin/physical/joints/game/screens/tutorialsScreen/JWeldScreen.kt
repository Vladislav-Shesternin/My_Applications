package com.rochevasternin.physical.joints.game.screens.tutorialsScreen

import com.rochevasternin.physical.joints.game.LibGDXGame
import com.rochevasternin.physical.joints.game.actors.scroll.tutorial.AScrollPanel_JWeld
import com.rochevasternin.physical.joints.game.manager.SpriteManager
import com.rochevasternin.physical.joints.game.manager.util.SpriteUtil
import com.rochevasternin.physical.joints.game.screens.practicalScreen.Practical_JWeldScreen
import com.rochevasternin.physical.joints.game.utils.advanced.AdvancedTutorialScreen

class JWeldScreen(override val game: LibGDXGame): AdvancedTutorialScreen(game) {

    override val title               = "Weld Joint"
    override val practicalScreenName = Practical_JWeldScreen::class.java.name

    override var loadableAtlasList   = SpriteManager.EnumAtlas_JointWeld.values().map { it.data }.toMutableList()
    override val loadableTextureList = SpriteManager.EnumTexture_JointWeld.values().map { it.data }.toMutableList()

    override fun loadingAssets() {
        if (isFinishLoading.not()) {
            if (assetManager.update(16)) {
                isFinishLoading = true
                initAssets()

                spriteUtil  = SpriteUtil.JointWeld()
                aScrollPane = AScrollPanel_JWeld(this, spriteUtil as SpriteUtil.JointWeld)

                superShow()
            }
        }
    }

}