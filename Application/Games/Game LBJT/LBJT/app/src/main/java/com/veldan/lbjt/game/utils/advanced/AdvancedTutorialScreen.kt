package com.veldan.lbjt.game.utils.advanced

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.veldan.lbjt.game.LibGDXGame
import com.veldan.lbjt.game.actors.label.ASpinningLabel
import com.veldan.lbjt.game.actors.scroll.tutorial.AJointMouseScrollPanel
import com.veldan.lbjt.game.actors.scroll.tutorial.TutorialScrollPanel
import com.veldan.lbjt.game.manager.SpriteManager
import com.veldan.lbjt.game.manager.util.SpriteUtil
import com.veldan.lbjt.game.utils.GameColor
import com.veldan.lbjt.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.veldan.lbjt.game.utils.actor.animHide
import com.veldan.lbjt.game.utils.actor.animShow
import com.veldan.lbjt.game.utils.font.FontParameter
import com.veldan.lbjt.game.utils.region

abstract class AdvancedTutorialScreen(override val game: LibGDXGame): AdvancedScreen() {

    abstract val title: String

    private val parameter              by lazy { FontParameter().setCharacters(title) }
    private val fontInter_ExtraBold_50 by lazy { fontGeneratorInter_ExtraBold.generateFont(parameter.setSize(50)) }

    // Assets
    open var spriteUtil : SpriteUtil.TutorialsAssets? = null

    open val loadableAtlasList   = mutableListOf<SpriteManager.AtlasData>()
    open val loadableTextureList = mutableListOf<SpriteManager.TextureData>()

    protected val assetManager    = AssetManager()
    protected var isFinishLoading = false

    // Actor
    open var aScrollPane: TutorialScrollPanel? = null

    private val aTitleLbl by lazy { ASpinningLabel(this, title, Label.LabelStyle(fontInter_ExtraBold_50, GameColor.textRed), alphaWidth = 0) }


    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.themeUtil.assets.BACKGROUND.region)
        loadAssets()
    }

    override fun render(delta: Float) {
        super.render(delta)
        loadingAssets()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addTitleLbl()
        addScrollPanel()

        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
        game.spriteManager.assetManager = game.assetManager
    }

    abstract fun loadingAssets()

    fun superShow() { super.show() }

    private fun loadAssets() {
        game.spriteManager.assetManager = assetManager

        with(game.spriteManager) {
            loadableAtlasList   = this@AdvancedTutorialScreen.loadableAtlasList
            loadAtlas()
            loadableTextureList = this@AdvancedTutorialScreen.loadableTextureList
            loadTexture()
        }
    }

    protected fun initAssets() {
        game.spriteManager.initAtlasAndTexture()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedStage.addTitleLbl() {
        addActor(aTitleLbl)
        aTitleLbl.setBounds(0f, 1315f, 700f, 61f)
    }

    private fun AdvancedStage.addScrollPanel() {
        addActor(aScrollPane)
        aScrollPane?.setBounds(25f, 0f, 650f, 1165f)
    }

}