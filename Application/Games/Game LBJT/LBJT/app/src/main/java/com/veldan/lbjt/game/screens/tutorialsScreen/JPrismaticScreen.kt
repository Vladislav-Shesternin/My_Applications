package com.veldan.lbjt.game.screens.tutorialsScreen

import com.veldan.lbjt.game.LibGDXGame
import com.veldan.lbjt.game.actors.scroll.tutorial.AScrollPanel_JDistance
import com.veldan.lbjt.game.actors.scroll.tutorial.AScrollPanel_JMouse
import com.veldan.lbjt.game.actors.scroll.tutorial.AScrollPanel_JPrismatic
import com.veldan.lbjt.game.actors.scroll.tutorial.AScrollPanel_JRevolute
import com.veldan.lbjt.game.manager.SpriteManager
import com.veldan.lbjt.game.manager.util.SpriteUtil
import com.veldan.lbjt.game.screens.practicalScreen.Practical_JDistanceScreen
import com.veldan.lbjt.game.screens.practicalScreen.Practical_JMouseScreen
import com.veldan.lbjt.game.screens.practicalScreen.Practical_JPrismaticScreen
import com.veldan.lbjt.game.screens.practicalScreen.Practical_JRevoluteScreen
import com.veldan.lbjt.game.utils.advanced.AdvancedTutorialScreen

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