package com.vi.bt.online.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.vi.bt.online.game.actors.label.LabelStyle
import com.vi.bt.online.game.manager.SpriteManager
import com.vi.bt.online.game.util.WIDTH
import com.vi.bt.online.game.util.advanced.AdvancedGroup
import com.vi.bt.online.game.util.advanced.AdvancedScreen
import com.vi.bt.online.game.util.advanced.AdvancedStage
import com.vi.bt.online.util.log
import com.vi.bt.online.util.toMS
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.*
import com.vi.bt.online.game.util.Layout.Game as LG

class GameScreen: AdvancedScreen() {

    private val tabloGroup  = AdvancedGroup()
    private val tabloText   = Label("0", LabelStyle.alataWhite_70)
    private val personGroup = AdvancedGroup()
    private val ballList    = List(10) { Image(SpriteManager.GameRegion.BALL.region) }
    private val basket      = Actor()

    private val ballTabloFlow = MutableStateFlow(0)
    private val ballFlow = MutableSharedFlow<Image>(replay = 3, extraBufferCapacity = 10)



    override fun show() {
        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStage() {
        addTabloGroup()
        addPersonGroup()
        addBallList()

        addActor(basket)
        basket.apply { with(LG.basket) { setBounds(x, y, w, h) } }
    }

    override fun render(delta: Float) {
        super.render(delta)

        Gdx.input.accelerometerY.apply {
            personGroup.x = when {
                personGroup.x < 0f -> 0f
                personGroup.x > WIDTH - personGroup.width -> WIDTH - personGroup.width
                else -> personGroup.x + (this * 5)
            }
            basket.x = personGroup.x + 98f
        }

        ballList.onEach { ball ->
            coroutineMain.launch {
                when {
                    (ball.y + ball.height) < 0f -> {
                        log("Не поймал")
                        ballFlow.emit(ball)
                    }
                    ball.x in (basket.x..(basket.x + basket.width)) &&
                            ball.y in (basket.y..(basket.y + basket.height))
                                                -> {
                        log("Поймал")
                        ballTabloFlow.value += 1
                        ballFlow.emit(ball)
                    }
                }
            }
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addTabloGroup() {
        addActor(tabloGroup)
        tabloGroup.apply {
            with(LG.tablo) { setBounds(x, y, w, h) }
            addAndFillActors(Image(SpriteManager.GameRegion.TABLO.region), tabloText)

            tabloText.setAlignment(Align.center)
            updateTablo()
        }

    }

    private fun AdvancedStage.addPersonGroup() {
        addActor(personGroup)
        personGroup.apply {
            with(LG.person) { setBounds(x, y, w, h) }
            addAndFillActors(Image(SpriteManager.GameRegion.PERSON.region))
        }

    }

    private fun AdvancedStage.addBallList() {

        ballList.onEach {
            addActor(it)
            it.apply {
                with(LG.ball) { setBounds(x, y, w, h) }
                setOrigin(Align.center)
            }
        }

        ballList.onEach {
            ballFlow.tryEmit(it)
        }


        coroutineMain.launch {
            ballFlow.collect { ball ->
                Gdx.app.postRunnable {
                    ball.apply {
                        clear()

                        y = 700f
                        x = (50..(WIDTH - personGroup.width).toInt()).random().toFloat()

                        addAction(
                            Actions.parallel(
                                Actions.moveTo(x, -(height * 2), (2..5).random().toFloat()),
                                Actions.forever(Actions.rotateBy((360f * if (Random().nextBoolean()) -1 else 1), (1..5).random().toFloat())),
                            )
                        )

                    }
                }



            }
        }

    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------
    private fun updateTablo() {
        coroutineMain.launch {
            ballTabloFlow.collect {
                tabloText.setText(it)
            }
        }
    }

}