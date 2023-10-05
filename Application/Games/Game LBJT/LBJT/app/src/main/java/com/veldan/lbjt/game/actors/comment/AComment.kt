package com.veldan.lbjt.game.actors.comment

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.veldan.lbjt.game.actors.AIcon
import com.veldan.lbjt.game.actors.label.SpinningLabel
import com.veldan.lbjt.game.data.User
import com.veldan.lbjt.game.utils.GameColor
import com.veldan.lbjt.game.utils.actor.setBounds
import com.veldan.lbjt.game.utils.advanced.AdvancedGroup
import com.veldan.lbjt.game.utils.advanced.AdvancedScreen
import com.veldan.lbjt.game.utils.font.FontParameter
import com.veldan.lbjt.game.utils.font.FontParameter.CharType

class AComment(override val screen: AdvancedScreen): AdvancedGroup() {

    override var standartW = 700f

    private val parameter = FontParameter()

    private val icon     = AIcon(screen)
    private val nickname = SpinningLabel(screen,"", Label.LabelStyle(screen.fontGeneratorInter_ExtraBold.generateFont(parameter.setCharacters(CharType.ALL).setSize(35)), GameColor.textGreen), alignment = Align.left)
    private val panel    = Image(screen.game.themeUtil.assets.PANEL)
    private val comment  = Label("", Label.LabelStyle(screen.fontGeneratorInter_ExtraBold.generateFont(parameter.setCharacters(CharType.ALL).setSize(20)), GameColor.textGreen))


    override fun addActorsOnGroup() {
        addIcon()
        addNickname()
        addPanel()
        addComment()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun addIcon() {
        addActor(icon)
        icon.setBounds(Vector2(15f, height-154f).toStandart, Vector2(154f, 154f).toStandart)
    }

    private fun addNickname() {
        addActor(nickname)
        nickname.setBounds(Vector2(194f, height-52f).toStandart, Vector2(491f, 52f).toStandart)
    }

    private fun addPanel() {
        addActor(panel)
        panel.setBounds(Vector2(184f, 0f).toStandart, Vector2(501f, height-53f).toStandart)
    }

    private fun addComment() {
        addActor(comment)
        comment.apply {
            setBounds(Vector2(199f, 10f).toStandart, Vector2(471f, this@AComment.height-73f).toStandart)
            wrap = true
            setAlignment(Align.topLeft)
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun update(user: User) {
        user.icon    ?.let { icon.updateIcon(it)  }
        user.nickname?.let { nickname.setText(it) }
        user.comment ?.let { comment.setText(it)  }
    }

}