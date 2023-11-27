package com.veldan.lbjt.game.utils.advanced

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.veldan.lbjt.R
import com.veldan.lbjt.game.LibGDXGame
import com.veldan.lbjt.game.actors.button.AButton_Regular
import com.veldan.lbjt.game.actors.label.ASpinningLabel
import com.veldan.lbjt.game.actors.scroll.AScrollPane
import com.veldan.lbjt.game.actors.scroll.VerticalGroup
import com.veldan.lbjt.game.actors.scroll.tutorial.AJointMouseScrollPanel
import com.veldan.lbjt.game.actors.scroll.tutorial.TutorialScrollPanel
import com.veldan.lbjt.game.manager.SpriteManager
import com.veldan.lbjt.game.manager.util.SpriteUtil
import com.veldan.lbjt.game.screens.AboutAuthorScreen
import com.veldan.lbjt.game.utils.GameColor
import com.veldan.lbjt.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.veldan.lbjt.game.utils.actor.animHide
import com.veldan.lbjt.game.utils.actor.animShow
import com.veldan.lbjt.game.utils.font.FontParameter
import com.veldan.lbjt.game.utils.region
import com.veldan.lbjt.util.log

abstract class AdvancedTutorialScreen(override val game: LibGDXGame): AdvancedScreen() {

    abstract val title: String

    private val textPlay by lazy { game.languageUtil.getStringResource(R.string.play) }

    private val parameter              by lazy { FontParameter().setCharacters(title + textPlay) }
    private val fontInter_ExtraBold_50 by lazy { fontGeneratorInter_ExtraBold.generateFont(parameter.setSize(50)) }

    // Assets
    open var spriteUtil : SpriteUtil.TutorialsAssets? = null

    open val loadableAtlasList   = mutableListOf<SpriteManager.AtlasData>()
    open val loadableTextureList = mutableListOf<SpriteManager.TextureData>()

    protected val assetManager    = AssetManager()
    protected var isFinishLoading = false

    // Actor
    open var aScrollPane: TutorialScrollPanel? = null

    private val aPlayBtn  by lazy { AButton_Regular(this, textPlay, Label.LabelStyle(fontInter_ExtraBold_50, GameColor.textGreen)) }
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
        addPlayBtn()
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

    private fun AdvancedStage.addPlayBtn() {
        addActor(aPlayBtn)
        aPlayBtn.setBounds(225f, 1198f, 250f, 90f)
        aPlayBtn.setOnClickListener {
            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                game.navigationManager.navigate(AboutAuthorScreen::class.java.name, this::class.java.name)
            }
        }
    }

    var isShow = true

    private fun AdvancedStage.addScrollPanel() {
        addActor(aScrollPane)
        aScrollPane?.setBounds(25f, -235f, 650f, 1400f)

        aScrollPane?.scrollPanel?.scrollNextBlock = AScrollPane.Static.ScrollBlock { isScrollNext ->
            if (isScrollNext && isShow) {
                    isShow = false
                    animHideTop()
            }
            if (isScrollNext.not() && isShow.not()) {
                isShow = true
                animShowTop()
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun animHideTop() {
        aTitleLbl.apply {
            clearActions()
            animHide(0.3f)
        }
        aPlayBtn.apply {
            clearActions()
            animHide(0.3f)
        }
        aScrollPane?.apply {
            clearActions()
            addAction(Actions.moveBy(0f, 235f, 0.6f, Interpolation.sine))
        }
    }

    private fun animShowTop() {
        aTitleLbl.apply {
            clearActions()
            animShow(0.9f)
        }
        aPlayBtn.apply {
            clearActions()
            animShow(0.9f)
        }
        aScrollPane?.apply {
            clearActions()
            addAction(Actions.moveBy(0f, -235f, 0.6f, Interpolation.sine))
        }
    }

}