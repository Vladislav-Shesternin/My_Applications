package com.finan.cial.quizz.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.finan.cial.quizz.game.actors.button.AButtonStyle
import com.finan.cial.quizz.game.actors.button.AButtonText
import com.finan.cial.quizz.game.manager.FontTTFManager
import com.finan.cial.quizz.game.manager.NavigationManager
import com.finan.cial.quizz.game.manager.SpriteManager
import com.finan.cial.quizz.game.musicUtil
import com.finan.cial.quizz.game.utils.actor.setBounds
import com.finan.cial.quizz.game.utils.advanced.AdvancedGroup
import com.finan.cial.quizz.game.utils.advanced.AdvancedScreen
import com.finan.cial.quizz.game.utils.runGDX
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.finan.cial.quizz.game.utils.Layout.Menu as LM

class MenuScreen : AdvancedScreen() {

    companion object {
        private var isMusic = true
    }

    private val start  = AButtonText("START", AButtonStyle.btn, LabelStyle(FontTTFManager.Jura.font_78.font, Color.WHITE))
    private val music = AButtonText("MUSIC", AButtonStyle.btn, LabelStyle(FontTTFManager.Jura.font_78.font, Color.WHITE))
    private val exit  = AButtonText("EXIT", AButtonStyle.btn, LabelStyle(FontTTFManager.Jura.font_78.font, Color.WHITE))


    override fun show() {
        if(isMusic) musicUtil.apply { music = MAIN.apply { isLooping = true } }

        stageUI.addAction(Actions.alpha(0f))
        setBackgrounds(SpriteManager.SplashRegion.BAC.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            runGDX { addButtons() }
            showStage()
        }
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addButtons() {
        addActors(start, music, exit)
        var ny = LM.btn.y
        listOf(start, music, exit).onEach { btn ->
            with(LM.btn) {
                btn.setBounds(x, ny, w, h)
                ny -= vs + h
            }
        }

        start.setOnClickListener { NavigationManager.navigate(GameScreen(), MenuScreen()) }
        exit.setOnClickListener { NavigationManager.exit() }

        if (isMusic.not()) {
            music.disable()
            music.touchable = Touchable.enabled
        } else music.enable()

        music.setOnClickListener {
            isMusic = !isMusic
            if (isMusic) {
                music.enable()
                musicUtil.music?.play()
            } else {
                music.disable()
                music.touchable = Touchable.enabled
                musicUtil.music?.pause()
            }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private suspend fun showStage() = suspendCoroutine<Unit> { continuation ->
        runGDX {
            stageUI.addAction(Actions.sequence(
                Actions.fadeIn(0.45f),
                Actions.run { continuation.resume(Unit) }
            ))
        }
    }

}