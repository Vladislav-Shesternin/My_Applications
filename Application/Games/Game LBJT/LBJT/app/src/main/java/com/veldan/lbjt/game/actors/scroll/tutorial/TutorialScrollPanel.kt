package com.veldan.lbjt.game.actors.scroll.tutorial

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.utils.Align
import com.github.tommyettinger.textra.Font
import com.github.tommyettinger.textra.Font.FontFamily
import com.github.tommyettinger.textra.TypingLabel
import com.veldan.lbjt.game.actors.scroll.VerticalGroup
import com.veldan.lbjt.game.actors.scroll.tutorial.actors.AAbstractList
import com.veldan.lbjt.game.actors.scroll.tutorial.actors.ACodePanel
import com.veldan.lbjt.game.actors.scroll.tutorial.actors.AList_Label
import com.veldan.lbjt.game.actors.scroll.tutorial.actors.AList_TypingLabel
import com.veldan.lbjt.game.actors.scroll.tutorial.actors.ALongQuote_TypingLabel
import com.veldan.lbjt.game.utils.advanced.AdvancedGroup
import com.veldan.lbjt.game.utils.advanced.AdvancedScreen
import com.veldan.lbjt.game.utils.disposeAll
import com.veldan.lbjt.game.utils.font.FontParameter

abstract class TutorialScrollPanel(final override val screen: AdvancedScreen): AdvancedGroup() {

    private val parameter              = FontParameter().setCharacters(FontParameter.CharType.ALL)
    private val fontInter_ExtraBold_50 = screen.fontGeneratorInter_ExtraBold.generateFont(parameter.setSize(50))
    private val fontInter_Bold_30      = screen.fontGeneratorInter_Bold.generateFont(parameter.setSize(30))
    private val fontInter_Medium_30    = screen.fontGeneratorInter_Medium.generateFont(parameter.setSize(30))
    private val fontInter_Regular_30   = screen.fontGeneratorInter_Regular.generateFont(parameter.setSize(30))
    private val fontInter_Regular_35   = screen.fontGeneratorInter_Regular.generateFont(parameter.setSize(35))
    private val fontInter_Light_30     = screen.fontGeneratorInter_Light.generateFont(parameter.setSize(30))

    // Actor
    private val verticalGroup   = VerticalGroup(screen, endGap = 25f, alignment = VerticalGroup.Alignment.TOP, direction = VerticalGroup.Direction.DOWN)
    private val scrollPanel     = ScrollPane(verticalGroup)

    // Field
    private var thisWidth     = 0f
    private val languageUtil  = screen.game.languageUtil

    private var tmpText    = ""
    private val typingFont = Font()

    private var tmpFont      : BitmapFont?   = null
    private var tmpTypingFont: Font?         = null

    final override fun addActorsOnGroup() {
        thisWidth = width

        addActors(scrollPanel)
        scrollPanel.setSize(width, height)
        verticalGroup.addActorsOnVerticalGroup()

        verticalGroup.children.onEach { it.debug() }
    }

    override fun dispose() {
        super.dispose()
        disposeAll(verticalGroup)
    }

    abstract fun VerticalGroup.addActorsOnVerticalGroup()

    fun VerticalGroup.addSpace(space: Space) {
        addActor(Actor().also { it.setSize(thisWidth, space.space) })
    }

    fun VerticalGroup.addImage(region: TextureRegion, height: Float) {
        addActor(Image(region).also { it.setSize(thisWidth, height) })
    }

    fun VerticalGroup.addImage(texture: Texture, height: Float) {
        addActor(Image(texture).also { it.setSize(thisWidth, height) })
    }

    fun VerticalGroup.addLabel(stringId: Int, textType: LabelFont, color: Color, align: Int = Align.left) {
        tmpText = languageUtil.getStringResource(stringId)
        tmpFont = textType.getFont()

        addActor(Label(tmpText, Label.LabelStyle(tmpFont, color)).also {
            it.wrap   = true
            it.width  = thisWidth
            it.height = it.prefHeight
            it.setAlignment(align)
        })
    }

    fun VerticalGroup.addTypingLabel(stringId: Int, fontFamily: TypingLabelFontFamily) {
        tmpText       = languageUtil.getStringResource(stringId)
        tmpTypingFont = fontFamily.getFont()

        addActor(TypingLabel(tmpText, tmpTypingFont).also {
            it.wrap = true
            it.width = thisWidth
            it.height = it.prefHeight
        })
    }

    fun VerticalGroup.addCodePanel(stringId: Int, codePanelHeight: CodePanelHeight) {
        tmpText = languageUtil.getStringResource(stringId)
        addActor(ACodePanel(screen, tmpText, fontInter_Light_30).also { it.setSize(thisWidth, codePanelHeight.height) })
    }

    fun VerticalGroup.addList_Label(stringIds: Array<Int>, align: AAbstractList.Align = AAbstractList.Align.Left, symbol: AAbstractList.Symbol = AAbstractList.Symbol.Bullet) {
        addActor(AList_Label(screen, fontInter_Regular_30, stringIds.map { languageUtil.getStringResource(it) }, align, symbol, fontInter_Regular_30).also { it.width = thisWidth })
    }

    fun VerticalGroup.addList_Label(strings: List<String>, align: AAbstractList.Align = AAbstractList.Align.Left, symbol: AAbstractList.Symbol = AAbstractList.Symbol.Bullet) {
        addActor(AList_Label(screen, fontInter_Regular_30, strings, align, symbol, fontInter_Regular_30).also { it.width = thisWidth })
    }

    fun VerticalGroup.addList_TypingLabel(stringIds: Array<Int>, align: AAbstractList.Align = AAbstractList.Align.Left, symbol: AAbstractList.Symbol = AAbstractList.Symbol.Bullet) {
        addActor(AList_TypingLabel(screen, TypingLabelFontFamily.Inter_RegularBold_30.getFont(), stringIds.map { languageUtil.getStringResource(it) }, align, symbol, fontInter_Regular_30).also { it.width = thisWidth })
    }

    fun VerticalGroup.addList_TypingLabel(strings: List<String>, align: AAbstractList.Align = AAbstractList.Align.Left, symbol: AAbstractList.Symbol = AAbstractList.Symbol.Bullet) {
        addActor(AList_TypingLabel(screen, TypingLabelFontFamily.Inter_RegularBold_30.getFont(), strings, align, symbol, fontInter_Regular_30).also { it.width = thisWidth })
    }

    fun VerticalGroup.addLongQuote(stringId: Int) {
        tmpText = languageUtil.getStringResource(stringId)
        addActor(ALongQuote_TypingLabel(screen, tmpText, TypingLabelFontFamily.Inter_RegularBold_30.getFont()).also { it.width = thisWidth })
    }


    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun LabelFont.getFont(): BitmapFont = when(this) {
        LabelFont.Inter_ExtraBold_50 -> fontInter_ExtraBold_50
        LabelFont.Inter_Regular_35   -> fontInter_Regular_35
    }

    private fun TypingLabelFontFamily.getFont(): Font = typingFont.also { fnt ->
        when(this) {
            TypingLabelFontFamily.Inter_MeniumBold_30 -> fnt.setFamily(FontFamily(arrayOf(
                Font(fontInter_Medium_30).setName("Inter_Medium"), Font(fontInter_Bold_30).setName("Inter_Bold")
            )))
            TypingLabelFontFamily.Inter_RegularBold_30 -> fnt.setFamily(FontFamily(arrayOf(
                Font(fontInter_Regular_30).setName("Inter_Regular"), Font(fontInter_Bold_30).setName("Inter_Bold")
            )))
        }
    }

    enum class LabelFont {
        Inter_ExtraBold_50,
        Inter_Regular_35,
    }

    enum class TypingLabelFontFamily {
        Inter_MeniumBold_30,
        Inter_RegularBold_30,
    }

    enum class Space(val space: Float) {
        _80(80f), _25(25f)
    }

    enum class CodePanelHeight(val height: Float) {
        _110(110f), _400(400f)
    }

}