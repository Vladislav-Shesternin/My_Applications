package com.veldan.lbjt.game.screens.tutorialsScreen

import com.veldan.lbjt.game.LibGDXGame
import com.veldan.lbjt.game.actors.scroll.tutorial.AScrollPanel_JDistance
import com.veldan.lbjt.game.actors.scroll.tutorial.AScrollPanel_JMouse
import com.veldan.lbjt.game.actors.scroll.tutorial.AScrollPanel_JPrismatic
import com.veldan.lbjt.game.actors.scroll.tutorial.AScrollPanel_JRevolute
import com.veldan.lbjt.game.actors.scroll.tutorial.AScrollPanel_JWeld
import com.veldan.lbjt.game.actors.scroll.tutorial.AScrollPanel_JWheel
import com.veldan.lbjt.game.manager.SpriteManager
import com.veldan.lbjt.game.manager.util.SpriteUtil
import com.veldan.lbjt.game.screens.practicalScreen.Practical_JDistanceScreen
import com.veldan.lbjt.game.screens.practicalScreen.Practical_JMouseScreen
import com.veldan.lbjt.game.screens.practicalScreen.Practical_JPrismaticScreen
import com.veldan.lbjt.game.screens.practicalScreen.Practical_JRevoluteScreen
import com.veldan.lbjt.game.screens.practicalScreen.Practical_JWeldScreen
import com.veldan.lbjt.game.screens.practicalScreen.Practical_JWheelScreen
import com.veldan.lbjt.game.utils.advanced.AdvancedTutorialScreen

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