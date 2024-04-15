package com.rochevasternin.physical.joints.game.screens.tutorialsScreen

import com.rochevasternin.physical.joints.game.LibGDXGame
import com.rochevasternin.physical.joints.game.actors.scroll.tutorial.AScrollPanel_JFriction
import com.rochevasternin.physical.joints.game.manager.SpriteManager
import com.rochevasternin.physical.joints.game.manager.util.SpriteUtil
import com.rochevasternin.physical.joints.game.screens.practicalScreen.Practical_JFrictionScreen
import com.rochevasternin.physical.joints.game.utils.advanced.AdvancedTutorialScreen

class JFrictionScreen(override val game: LibGDXGame): AdvancedTutorialScreen(game) {

    override val title               = "Friction Joint"
    override val practicalScreenName = Practical_JFrictionScreen::class.java.name

    override var loadableAtlasList   = SpriteManager.EnumAtlas_JointFriction.values().map { it.data }.toMutableList()
    override val loadableTextureList = SpriteManager.EnumTexture_JointFriction.values().map { it.data }.toMutableList()

    override fun loadingAssets() {
        if (isFinishLoading.not()) {
            if (assetManager.update(16)) {
                isFinishLoading = true
                initAssets()

                spriteUtil  = SpriteUtil.JointFriction()
                aScrollPane = AScrollPanel_JFriction(this, spriteUtil as SpriteUtil.JointFriction)

                superShow()
            }
        }
    }

}