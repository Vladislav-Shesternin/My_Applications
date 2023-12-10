package com.veldan.lbjt.game.actors.scroll.tutorial.actors

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.veldan.lbjt.R
import com.veldan.lbjt.game.actors.button.AButton_Regular
import com.veldan.lbjt.game.box2d.BodyId
import com.veldan.lbjt.game.screens.AboutAuthorScreen
import com.veldan.lbjt.game.utils.Block
import com.veldan.lbjt.game.utils.GameColor
import com.veldan.lbjt.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.veldan.lbjt.game.utils.actor.animHide
import com.veldan.lbjt.game.utils.advanced.AdvancedGroup
import com.veldan.lbjt.game.utils.advanced.AdvancedScreen
import com.veldan.lbjt.util.log

class ABtnPanel(
    override val screen : AdvancedScreen,
    font    : BitmapFont,
    val practicalScreenName: String,
): AdvancedGroup() {

    private val btnThanks = AButton_Regular(screen, screen.game.languageUtil.getStringResource(R.string.give_thanks), Label.LabelStyle(font, GameColor.textGreen))
    private val btnPlay   = AButton_Regular(screen, screen.game.languageUtil.getStringResource(R.string.play), Label.LabelStyle(font, GameColor.textGreen))

    override fun addActorsOnGroup() {
        addActors(btnThanks, btnPlay)
        btnThanks.setBounds(25f, 0f, 250f, 90f)
        btnPlay.setBounds(375f, 0f, 250f, 90f)

        btnThanks.setOnClickListener {
            screen.stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                screen.game.navigationManager.navigate(AboutAuthorScreen::class.java.name, screen::class.java.name)
            }
        }
        btnPlay.setOnClickListener {
            screen.stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                screen.game.navigationManager.navigate(practicalScreenName, screen::class.java.name)
            }
        }
    }

}