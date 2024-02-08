package com.jumping.cubuletus.game.screens

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.jumping.cubuletus.game.actors.image.AImage
import com.jumping.cubuletus.game.actors.label.ALabelStyle
import com.jumping.cubuletus.game.box2d.bodies.BodyId
import com.jumping.cubuletus.game.box2d.bodies.batut.BBatut
import com.jumping.cubuletus.game.box2d.bodies.coin.BCoin
import com.jumping.cubuletus.game.box2d.bodies.coub.BCoub
import com.jumping.cubuletus.game.box2d.bodies.frame.BFrame
import com.jumping.cubuletus.game.manager.SpriteManager
import com.jumping.cubuletus.game.util.advanced.AdvancedBox2dScreen
import com.jumping.cubuletus.game.util.advanced.AdvancedStage
import com.jumping.cubuletus.game.util.listeners.toClickable
import com.jumping.cubuletus.game.util.runGDX
import com.jumping.cubuletus.game.util.setBounds
import com.jumping.cubuletus.util.log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.jumping.cubuletus.game.util.Layout.Game as LG
import com.jumping.cubuletus.game.util.Layout.Splash as LS

class GameScreen: AdvancedBox2dScreen() {

    private val panelImage    = AImage(SpriteManager.GameRegion.PANEL.region)
    private val miniCoinImage = AImage(SpriteManager.GameRegion.MINI_COIN.region)
    private val coinLabel     = Label("0", ALabelStyle.akshar_white_25)
    private val upImage       = AImage(SpriteManager.GameRegion.UP.region)

    private val coub      = BCoub()
    private val frame     = BFrame()
    private val coin      = BCoin()
    private val batutList = List(3) { BBatut() }

    private val coinFlow = MutableStateFlow(0)



    override fun show() {
        super.show()
        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        coroutine.launch {
            createFrame()
            createBatutList()
            createCoin()
            createCoub()

            runGDX {
                addPanel()
                addUp()
            }
        }
    }

    // ------------------------------------------------------------------------
    // Create Bodies
    // ------------------------------------------------------------------------
    private fun createFrame() {
        frame.also { frame ->
            frame.initialize(
                stageUI,
                sizeConverterUIToBox,
                sizeConverterBoxToUI,
                LG.frame.position(),
                LG.frame.size()
            )
        }
    }

    private fun createBatutList() {
        var nx = LG.batut.x
        batutList.onEach { batut ->
            batut.initialize(
                stageUI,
                sizeConverterUIToBox,
                sizeConverterBoxToUI,
                Vector2(nx, LG.batut.y),
                LG.batut.size()
            )
            with(LG.batut) { nx += w + hs}
        }
    }

    private fun createCoin() {
        coin.also { coin ->
            coin.initialize(
                stageUI,
                sizeConverterUIToBox,
                sizeConverterBoxToUI,
                Vector2(
                    (LG.coin.x.toInt()..LG.coinEnd.toInt()).shuffled().first().toFloat(),
                    LG.coin.y
                ),
                LG.coin.size()
            )
        }
    }

    private fun createCoub() {
        coub.also { coub ->
            coub.initialize(
                stageUI,
                sizeConverterUIToBox,
                sizeConverterBoxToUI,
                LG.coub.position(),
                LG.coub.size()
            )
            coub.setBeginContactBlock {
                when(it.id) {
                    BodyId.COIN -> {
                        runGDX {
                            bonusJump?.play(1f)

                            val nx = (LG.coin.x.toInt()..LG.coinEnd.toInt()).shuffled().first().toFloat()
                            coin.body.apply { setTransform(sizeConverterUIToBox.getSizeX(nx), position.y, 0f) }
                            coinFlow.value += 1
                        }
                    }
                    else -> {}
                }
            }
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addPanel() {
        addActors(panelImage, miniCoinImage, coinLabel)
        panelImage.setBounds(LS.panel)
        miniCoinImage.setBounds(LG.miniCoin)
        coinLabel.setBounds(LG.coinLabel)

        coroutine.launch { collectCoin() }
    }

    private fun AdvancedStage.addUp() {
        addActor(upImage)
        upImage.apply {
            setBounds(LG.up)
            toClickable().setOnClickListener {
                log("click - ${coub.isOnGround}")
                if (coub.isOnGround) coub.body.apply {
                    soundJump?.play(1f)
                    applyLinearImpulse(0f, 50f, position.x, position.y, true)
                }
            }
        }
    }


    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------
    private suspend fun collectCoin() {
        coinFlow.collect { coin ->
            runGDX { coinLabel.setText(coin) }
        }
    }

}