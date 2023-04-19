package com.hk.stck.nord.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.hk.stck.nord.game.actors.button.AButtonStyle
import com.hk.stck.nord.game.actors.button.AButtonText
import com.hk.stck.nord.game.manager.FontTTFManager
import com.hk.stck.nord.game.manager.NavigationManager
import com.hk.stck.nord.game.manager.SpriteManager
import com.hk.stck.nord.game.musicUtil
import com.hk.stck.nord.game.utils.advanced.AdvancedGroup
import com.hk.stck.nord.game.utils.advanced.AdvancedScreen
import com.hk.stck.nord.game.utils.runGDX
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.hk.stck.nord.game.utils.Layout.Menu as LM

class MenuScreen : AdvancedScreen() {

    companion object {
        private var isMusic = true
    }

    private val start = AButtonText("Stock", AButtonStyle.big, LabelStyle(FontTTFManager.AlegreyaSansSC_Regular.font_71.font, Color.WHITE))
    private val music = AButtonText("Music", AButtonStyle.big, LabelStyle(FontTTFManager.AlegreyaSansSC_Regular.font_71.font, Color.WHITE))
    private val exit  = AButtonText("Exit", AButtonStyle.big, LabelStyle(FontTTFManager.AlegreyaSansSC_Regular.font_71.font, Color.WHITE))


    override fun show() {
        if(isMusic) musicUtil.apply { music = MAIN.apply { isLooping = true } }

        stageUI.addAction(Actions.alpha(0f))
        setBackBackground(SpriteManager.SplashRegion.BACKGROUND.region)
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

        start.setOnClickListener { hideStage { NavigationManager.navigate(GameScreen(), MenuScreen()) } }
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
                Actions.fadeIn(0.4f),
                Actions.run { continuation.resume(Unit) }
            ))
        }
    }

    private fun hideStage(block: () -> Unit) {
        stageUI.addAction(Actions.sequence(
            Actions.fadeOut(0.4f),
            Actions.run { block() }
        ))
    }

}