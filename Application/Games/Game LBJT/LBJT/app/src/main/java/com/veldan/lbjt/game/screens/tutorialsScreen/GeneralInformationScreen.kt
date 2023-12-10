package com.veldan.lbjt.game.screens.tutorialsScreen

import com.badlogic.gdx.assets.AssetManager
import com.veldan.lbjt.game.LibGDXGame
import com.veldan.lbjt.game.actors.scroll.tutorial.AScrollPanel_GeneralInformation
import com.veldan.lbjt.game.manager.SpriteManager
import com.veldan.lbjt.game.manager.util.SpriteUtil
import com.veldan.lbjt.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.veldan.lbjt.game.utils.actor.animHide
import com.veldan.lbjt.game.utils.actor.animShow
import com.veldan.lbjt.game.utils.advanced.AdvancedScreen
import com.veldan.lbjt.game.utils.advanced.AdvancedStage
import com.veldan.lbjt.game.utils.region

class GeneralInformationScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val assetManager = AssetManager()

    // Assets
    private var isFinishLoading = false
    private var spriteUtilGeneralInfo: SpriteUtil.GeneralInformation? = null

    // Actor
    private var aGeneralInformationScrollPanel: AScrollPanel_GeneralInformation? = null

    override fun show() {
        game.activity.lottie.showLoader()
        stageUI.root.animHide()
        setUIBackground(game.themeUtil.assets.BACKGROUND.region)
        loadAssets()
    }

    override fun render(delta: Float) {
        super.render(delta)
        loadingAssets()
    }

    private fun loadAssets() {
        game.spriteManager.assetManager = assetManager

        with(game.spriteManager) {
            loadableAtlasList   = SpriteManager.EnumAtlas_GeneralInformation.values().map { it.data }.toMutableList()
            loadAtlas()
            loadableTextureList = SpriteManager.EnumTexture_GeneralInformation.values().map { it.data }.toMutableList()
            loadTexture()
        }
    }

    private fun loadingAssets() {
        if (isFinishLoading.not()) {
            if (assetManager.update(16)) {
                isFinishLoading = true
                initAssets()

                spriteUtilGeneralInfo          = SpriteUtil.GeneralInformation()
                aGeneralInformationScrollPanel = AScrollPanel_GeneralInformation(this, spriteUtilGeneralInfo!!)

                super.show()
            }
        }
    }

    private fun initAssets() {
        game.spriteManager.initAtlasAndTexture()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addGeneralInformationScrollPanel()
        game.activity.lottie.hideLoader()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
        game.spriteManager.assetManager = game.assetManager
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedStage.addGeneralInformationScrollPanel() {
        addActor(aGeneralInformationScrollPanel)
        aGeneralInformationScrollPanel?.setBounds(25f, 0f, 650f, 1400f)
    }

}