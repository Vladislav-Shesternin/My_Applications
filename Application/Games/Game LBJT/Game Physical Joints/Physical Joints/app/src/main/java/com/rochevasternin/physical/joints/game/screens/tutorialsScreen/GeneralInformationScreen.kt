package com.rochevasternin.physical.joints.game.screens.tutorialsScreen

import com.badlogic.gdx.assets.AssetManager
import com.rochevasternin.physical.joints.R
import com.rochevasternin.physical.joints.game.LibGDXGame
import com.rochevasternin.physical.joints.game.actors.scroll.tutorial.AScrollPanel_GeneralInformation
import com.rochevasternin.physical.joints.game.manager.SpriteManager
import com.rochevasternin.physical.joints.game.manager.util.SpriteUtil
import com.rochevasternin.physical.joints.game.utils.GameColor
import com.rochevasternin.physical.joints.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.rochevasternin.physical.joints.game.utils.actor.animHide
import com.rochevasternin.physical.joints.game.utils.actor.animShow
import com.rochevasternin.physical.joints.game.utils.advanced.AdvancedScreen
import com.rochevasternin.physical.joints.game.utils.advanced.AdvancedStage
import com.rochevasternin.physical.joints.game.utils.region

class GeneralInformationScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val assetManager = AssetManager()

    // Assets
    private var isFinishLoading = false
    private var spriteUtilGeneralInfo: SpriteUtil.GeneralInformation? = null

    // Actor
    private var aGeneralInformationScrollPanel: AScrollPanel_GeneralInformation? = null

    override fun show() {
        game.apply { backgroundColor = GameColor.background }
        game.activity.apply { setNavigationBarColor(R.color.background) }

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

        game.apply { backgroundColor = themeUtil.backgroundColor }
        game.activity.apply { setNavigationBarColor(game.themeUtil.navBarColorId) }

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