package com.veldan.lbjt.game.actors.scroll

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.veldan.lbjt.R
import com.veldan.lbjt.game.actors.comment.AComment
import com.veldan.lbjt.game.actors.comment.AVeldanComment
import com.veldan.lbjt.game.data.User
import com.veldan.lbjt.game.utils.COMMENT_COUNT
import com.veldan.lbjt.game.utils.GameColor
import com.veldan.lbjt.game.utils.actor.animHide
import com.veldan.lbjt.game.utils.advanced.AdvancedGroup
import com.veldan.lbjt.game.utils.advanced.AdvancedScreen
import com.veldan.lbjt.game.utils.disposeAll
import com.veldan.lbjt.game.utils.font.FontParameter
import com.veldan.lbjt.game.utils.font.FontParameter.CharType

class ACommentScrollPanel(override val screen: AdvancedScreen): AdvancedGroup() {

    companion object {
        private const val MIN_TEXT_HEIGHT = 81f
    }

    override var standartW = 700f

    private val parameter = FontParameter()

    private val verticalGroup   = ACommentVerticalGroup(screen, 50f, endGap = 150f)
    private val scrollPanel     = ScrollPane(verticalGroup)
    private val testHeightLabel = Label("", Label.LabelStyle(screen.fontGeneratorInter_ExtraBold.generateFont(parameter.setCharacters(CharType.ALL).setSize(20)), GameColor.textGreen))
    private val commentVeldan   = AVeldanComment(screen)

    override fun addActorsOnGroup() {
        addTestHeightLabel()
        addScrollPanel()
    }

    override fun dispose() {
        super.dispose()
        disposeAll(verticalGroup)
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun addTestHeightLabel() {
        addActor(testHeightLabel)
        testHeightLabel.apply {
            animHide()
            width = 471f.toStandart
            wrap = true
        }
    }

    private fun addScrollPanel() {
        addActor(scrollPanel)
        scrollPanel.setBoundsStandart(0f, 0f, width, height)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun addCommentVeldan() {
        testHeightLabel.setText(screen.game.languageUtil.getStringResource(R.string.veldan_comment))
        commentVeldan.setSize(700f, (if (testHeightLabel.prefHeight < MIN_TEXT_HEIGHT) MIN_TEXT_HEIGHT else testHeightLabel.prefHeight) + 73f)
        verticalGroup.addActor(commentVeldan)
    }

    fun addComment(user: User) {
        testHeightLabel.setText(user.comment)
        verticalGroup.addActor(AComment(screen).apply {
            setSize(700f, (if (testHeightLabel.prefHeight < MIN_TEXT_HEIGHT) MIN_TEXT_HEIGHT else testHeightLabel.prefHeight) + 73f)
            update(user)
        })
        if (verticalGroup.children.size > COMMENT_COUNT) verticalGroup.removeActorAt(0, true)
    }

    fun scrollToVeldanComment() {
        scrollPanel.velocityY = 0f
        scrollPanel.scrollY   = 0f
        commentVeldan.animRed()
    }

}