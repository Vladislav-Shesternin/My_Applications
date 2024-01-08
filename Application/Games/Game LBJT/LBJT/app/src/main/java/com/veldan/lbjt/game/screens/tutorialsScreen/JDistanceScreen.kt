package com.veldan.lbjt.game.screens.tutorialsScreen

import com.veldan.lbjt.game.LibGDXGame
import com.veldan.lbjt.game.actors.scroll.tutorial.AScrollPanel_JDistance
import com.veldan.lbjt.game.actors.scroll.tutorial.AScrollPanel_JMouse
import com.veldan.lbjt.game.manager.SpriteManager
import com.veldan.lbjt.game.manager.util.SpriteUtil
import com.veldan.lbjt.game.screens.practicalScreen.Practical_JDistanceScreen
import com.veldan.lbjt.game.screens.practicalScreen.Practical_JMouseScreen
import com.veldan.lbjt.game.utils.advanced.AdvancedTutorialScreen

class JDistanceScreen(override val game: LibGDXGame): AdvancedTutorialScreen(game) {

    override val title               = "Distance Joint"
    override val practicalScreenName = Practical_JDistanceScreen::class.java.name

    override var loadableAtlasList   = SpriteManager.EnumAtlas_JointDistance.values().map { it.data }.toMutableList()
    override val loadableTextureList = SpriteManager.EnumTexture_JointDistance.values().map { it.data }.toMutableList()

    override fun loadingAssets() {
        if (isFinishLoading.not()) {
            if (assetManager.update(16)) {
                isFinishLoading = true
                initAssets()

                spriteUtil  = SpriteUtil.JointDistance()
                aScrollPane = AScrollPanel_JDistance(this, spriteUtil as SpriteUtil.JointDistance)

                superShow()
            }
        }
    }

}