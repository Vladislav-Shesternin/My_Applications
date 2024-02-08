package com.littleman.andball.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.littleman.andball.game.actors.label.LabelStyle
import com.littleman.andball.game.manager.SpriteManager
import com.littleman.andball.game.util.advanced.AdvancedScreen
import com.littleman.andball.game.util.advanced.AdvancedStage
import com.littleman.andball.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import com.littleman.andball.game.util.Layout.Game as LG

class GameScreen : AdvancedScreen() {

    private val anim     = Animation(0.030f, *SpriteManager.ListRegion.ANIM.regionList.toTypedArray())
    private val player   = Image(SpriteManager.ListRegion.ANIM.regionList.first())
    private var time     = 0f
    private val ballList = List(10) { Image(SpriteManager.GameRegion.BALL.region) }
    private val ballFlow = MutableSharedFlow<Image>(replay = 10)
    private val panel   = Image(SpriteManager.GameRegion.PANEL.region)
    private val tablo   = Label("0", LabelStyle.fanta_45)



    override fun show() {
        setBackBackground(SpriteManager.CommonRegion.BACKGROUND.region)
        super.show()
    }

    override fun render(delta: Float) {
        super.render(delta)
        time += delta
        player.drawable = TextureRegionDrawable(anim.getKeyFrame(time, true))

        Gdx.input.accelerometerY.also { ay ->
            player.x = when {
                player.x < 0f    -> 0f
                player.x > 1304f -> 1304f
                else             -> player.x + (ay * 5)
            }
        }
        Gdx.input.accelerometerX.also { ax ->
            player.y = when {
                player.y < 0f   -> 0f
                player.y > 755f -> 755f
                else            -> player.y - (ax * 5)
            }
        }



        ballList.onEach { ball ->
            coroutine.launch {
                when {
                    ball.x in (player.x..(player.x + player.width)) &&
                            ball.y in (player.y..(player.y + player.height))
                    -> {
                        log("Поймал")
                        tablo.setText(++count)
                        ball.ballOut()
                        ballFlow.emit(ball)
                    }
                }
            }
        }

    }

    var count = 0

    override fun AdvancedStage.addActorsOnStage() {
        addPlayer()
        addPanel()
        addBallList()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addPlayer() {
        addActor(player)
        player.apply {
            with(LG.pStart) { setBounds(x, y, w, h) }
        }
    }

    private fun AdvancedStage.addBallList() {
        ballList.onEach { ball ->
            addActor(ball)
            with(LG.bStart) { ball.setBounds(x, y, w, h) }
            ballFlow.tryEmit(ball)
        }

        coroutine.launch {
            ballFlow.collect { ball ->
                delay((500..1500).shuffled().first().toLong())
                Gdx.app.postRunnable {
                    ball.apply {
                        y = (0..840).shuffled().first().toFloat()
                        x = (0..1358).shuffled().first().toFloat()
                    }
                }
            }
        }

    }

    private fun AdvancedStage.addPanel() {
        addActors(panel, tablo)
        panel.apply {
            with(LG.panel) { setBounds(x, y, w, h) }
        }
        tablo.apply {
            setAlignment(Align.center)
            with(LG.tablo) { setBounds(x, y, w, h) }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun Image.ballOut() {
        LG.bStart.apply { setPosition(x, y) }
    }

}