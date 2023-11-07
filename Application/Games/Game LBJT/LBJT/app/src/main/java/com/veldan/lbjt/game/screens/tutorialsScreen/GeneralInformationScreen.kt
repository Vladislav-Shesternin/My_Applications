package com.veldan.lbjt.game.screens.tutorialsScreen

import com.veldan.lbjt.game.LibGDXGame
import com.veldan.lbjt.game.actors.scroll.tutorial.AGeneralInformationScrollPanel
import com.veldan.lbjt.game.manager.SpriteManager
import com.veldan.lbjt.game.manager.util.SpriteUtil
import com.veldan.lbjt.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.veldan.lbjt.game.utils.actor.animHide
import com.veldan.lbjt.game.utils.actor.animShow
import com.veldan.lbjt.game.utils.advanced.AdvancedScreen
import com.veldan.lbjt.game.utils.advanced.AdvancedStage
import com.veldan.lbjt.game.utils.region

class GeneralInformationScreen(override val game: LibGDXGame): AdvancedScreen() {

    // Assets
    private var isFinishLoading         = false
    private val loadableTextureDataList = SpriteManager.EnumTexture_GeneralInformation.values().map { it.data }.toMutableList()
    private val spriteUtilGeneralInfo   by lazy { SpriteUtil.GeneralInformation() }

    // Actor
    private val aGeneralInformationScrollPanel by lazy { AGeneralInformationScrollPanel(this, spriteUtilGeneralInfo) }

    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.themeUtil.assets.BACKGROUND.region)
        loadAssets()
    }

    override fun render(delta: Float) {
        super.render(delta)
        loadingAssets()
    }

    private fun loadAssets() {
        with(game.spriteManager) {
            loadableTextureList = loadableTextureDataList
            loadTexture()
        }
    }

    private fun loadingAssets() {
        if (isFinishLoading.not()) {
            if (game.assetManager.update(16)) {
                isFinishLoading = true
                initAssets()

                super.show()
            }
        }
    }

    private fun initAssets() {
        game.spriteManager.initTexture()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addGeneralInformationScrollPanel()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun dispose() {
        loadableTextureDataList.onEach { game.assetManager.unload(it.path) }
        super.dispose()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedStage.addGeneralInformationScrollPanel() {
        addActor(aGeneralInformationScrollPanel)
        aGeneralInformationScrollPanel.setBounds(25f, 25f, 650f, 1350f)
    }

}