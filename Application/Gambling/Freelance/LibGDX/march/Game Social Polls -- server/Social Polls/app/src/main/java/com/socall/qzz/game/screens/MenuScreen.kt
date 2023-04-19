package com.socall.qzz.game.screens

import android.annotation.SuppressLint
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.utils.Align
import com.socall.qzz.MainActivity
import com.socall.qzz.game.actors.button.AButtonStyle
import com.socall.qzz.game.actors.button.AButtonText
import com.socall.qzz.game.actors.checkbox.ACheckBox
import com.socall.qzz.game.actors.checkbox.ACheckBoxStyle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.socall.qzz.game.game
import com.socall.qzz.game.manager.FontTTFManager
import com.socall.qzz.game.manager.NavigationManager
import com.socall.qzz.game.manager.SpriteManager
import com.socall.qzz.game.musicUtil
import com.socall.qzz.game.utils.actor.setBounds
import com.socall.qzz.game.utils.advanced.AdvancedGroup
import com.socall.qzz.game.utils.advanced.AdvancedScreen
import com.socall.qzz.game.utils.runGDX
import com.socall.qzz.util.log
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.socall.qzz.game.utils.Layout.Menu as LM

class MenuScreen : AdvancedScreen() {

    companion object {
        private var isMusic = true
    }

    private val quiz  = AButtonText("QUIZ", AButtonStyle.btn, LabelStyle(FontTTFManager.Amatic.font_80.font, Color.WHITE))
    private val exit  = AButtonText("EXIT", AButtonStyle.btn, LabelStyle(FontTTFManager.Amatic.font_80.font, Color.WHITE))
    private val music = ACheckBox(ACheckBoxStyle.music)


    override fun show() {
        if(isMusic) musicUtil.apply { music = MAIN.apply { isLooping = true } }

        stageUI.addAction(Actions.alpha(0f))
        setBackgrounds(SpriteManager.SplashRegion.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            runGDX {
                addButtons()
                addMusic()
            }
            showStage()
        }
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addButtons() {
        addActors(quiz, exit)
        quiz.apply {
            setBounds(LM.quiz)
            setOnClickListener { NavigationManager.navigate(GameScreen(), MenuScreen()) }
        }
        exit.apply {
            setBounds(LM.exit)
            setOnClickListener { NavigationManager.exit() }
        }
    }

    private fun AdvancedGroup.addMusic() {
        addActor(music)
        music.apply {
            setBounds(LM.music)

            if (isMusic) check() else uncheck()
            setOnCheckListener {
                isMusic = it
                if (isMusic) musicUtil.music?.play() else musicUtil.music?.pause()
            }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private suspend fun showStage() = suspendCoroutine<Unit> { continuation ->
        runGDX {
            stageUI.addAction(Actions.sequence(
                Actions.fadeIn(0.5f),
                Actions.run { continuation.resume(Unit) }
            ))
        }
    }

}