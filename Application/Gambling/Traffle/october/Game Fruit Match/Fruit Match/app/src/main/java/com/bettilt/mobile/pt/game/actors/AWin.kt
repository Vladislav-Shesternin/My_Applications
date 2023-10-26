package com.bettilt.mobile.pt.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.bettilt.mobile.pt.game.actors.button.AButton
import com.bettilt.mobile.pt.game.actors.button.AButtonText
import com.bettilt.mobile.pt.game.screens.GameScreen
import com.bettilt.mobile.pt.game.utils.GameColor
import com.bettilt.mobile.pt.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.bettilt.mobile.pt.game.utils.actor.animHide
import com.bettilt.mobile.pt.game.utils.actor.disable
import com.bettilt.mobile.pt.game.utils.actor.setOnClickListener
import com.bettilt.mobile.pt.game.utils.advanced.AdvancedGroup
import com.bettilt.mobile.pt.game.utils.advanced.AdvancedScreen
import com.bettilt.mobile.pt.game.utils.font.FontParameter

class AWin(override val screen: AdvancedScreen): AdvancedGroup() {

    private val parameter = FontParameter().setCharacters(FontParameter.CharType.ALL)
    private val font35    = screen.fontGeneratorKadwa_Bold.generateFont(parameter.setSize(35))


    private val background = Image(screen.drawerUtil.getRegion(GameColor.darkGreen.apply { a = 0.8f }))
    private val win        = Image(screen.game.spriteUtil.WIN)
    private val menuBtn    = AButtonText(screen, "Menu", AButton.Type.DEFAULT, Label.LabelStyle(font35, Color.BLACK))
    private val nextBtn    = AButtonText(screen, "Next", AButton.Type.DEFAULT, Label.LabelStyle(font35, Color.BLACK))
    private val scoreLbl   = Label("", Label.LabelStyle(font35, GameColor.green))


    override fun addActorsOnGroup() {
        disable()

        addAndFillActor(background)
        addWin()
        addBtns()
        addScoreLbl()

    }

    private fun addWin() {
        addActor(win)
        win.setBounds(352f, 251f, 858f, 459f)
    }

    private fun addBtns() {
        addActors(menuBtn, nextBtn)
        menuBtn.apply {
            setBounds(415f, 57f, 353f, 87f)
            setOnClickListener { screen.apply {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }
            } }
        }
        nextBtn.apply {
            setBounds(794f, 57f, 353f, 87f)
            setOnClickListener {
                screen.apply {
                    stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(GameScreen::class.java.name) }
                }
            }
        }
    }

    private fun addScoreLbl() {
        addActor(scoreLbl)
        scoreLbl.apply {
            setBounds(653f, 187f, 256f, 66f)
            setAlignment(Align.center)
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun setScore(scoreCurrent: Int, score: Int) {
        scoreLbl.setText("$scoreCurrent / $score")
    }

}