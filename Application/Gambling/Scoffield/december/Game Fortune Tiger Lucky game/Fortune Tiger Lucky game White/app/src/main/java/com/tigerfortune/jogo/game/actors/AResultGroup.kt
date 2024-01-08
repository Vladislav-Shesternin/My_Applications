package com.tigerfortune.jogo.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.tigerfortune.jogo.game.actors.checkbox.ACheckBox
import com.tigerfortune.jogo.game.screens.TigerGameScreen
import com.tigerfortune.jogo.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.tigerfortune.jogo.game.utils.actor.animHide
import com.tigerfortune.jogo.game.utils.actor.setOnClickListener
import com.tigerfortune.jogo.game.utils.advanced.AdvancedGroup
import com.tigerfortune.jogo.game.utils.advanced.AdvancedScreen

class AResultGroup(override val screen: AdvancedScreen): AdvancedGroup() {

    private val imgBackground = Image()
    private val imgStars      = Image()

    override fun addActorsOnGroup() {
        touchable = Touchable.disabled
        addAndFillActor(imgBackground)
        addMusic()
        addStars()
        addBtns()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun addMusic() {
        val music = ACheckBox(screen, ACheckBox.Static.Type.MUSIC)
        addActor(music)
        music.setBounds(887f, 1721f, 177f, 176f)

        screen.game.musicUtil.music?.let { mmm ->
            if (mmm.isPlaying.not()) music.check(false)

            music.setOnCheckListener { if (it) mmm.pause() else mmm.play() }
        }
    }

    private fun addStars() {
        addActor(imgStars)
        imgStars.setBounds(65f, 477f, 951f, 950f)
    }

    private fun addBtns() {
        val restartAndNext = Actor()
        val menu           = Actor()

        addActors(restartAndNext, menu)

        restartAndNext.apply {
            setBounds(232f, 517f, 418f, 215f)
            setOnClickListener(screen.game.soundUtil) {
                screen.stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { screen.game.navigationManager.navigate(TigerGameScreen::class.java.name) }
            }
        }
        menu.apply {
            setBounds(710f, 544f, 136f, 136f)
            setOnClickListener(screen.game.soundUtil) {
                screen.stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { screen.game.navigationManager.back() }
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun update(isWin: Boolean) {
        if (isWin) {
            imgBackground.drawable = TextureRegionDrawable(screen.drawerUtil.getRegion(Color.valueOf("F7C07A").apply { a = 0.65f }))
            imgStars.drawable      = TextureRegionDrawable(screen.game.gameAssets.WIN)
            screen.game.soundUtil.apply { play(level_passed) }
        } else {
            imgBackground.drawable = TextureRegionDrawable(screen.drawerUtil.getRegion(Color.valueOf("302D2A").apply { a = 0.65f }))
            imgStars.drawable      = TextureRegionDrawable(screen.game.gameAssets.FAIL)
            screen.game.soundUtil.apply { play(fail) }
        }
    }

}