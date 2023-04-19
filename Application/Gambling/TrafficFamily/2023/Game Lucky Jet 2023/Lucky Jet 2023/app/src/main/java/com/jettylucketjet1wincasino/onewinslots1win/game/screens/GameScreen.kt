package com.jettylucketjet1wincasino.onewinslots1win.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.jettylucketjet1wincasino.onewinslots1win.game.actors.Jet
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.jettylucketjet1wincasino.onewinslots1win.game.actors.Panel
import com.jettylucketjet1wincasino.onewinslots1win.game.manager.NavigationManager
import com.jettylucketjet1wincasino.onewinslots1win.game.manager.SpriteManager
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.MAIN_ANIM_SPEED
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.actor.disable
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.actor.enable
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.actor.setBounds
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.actor.toClickable
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.advanced.AdvancedGroup
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.advanced.AdvancedScreen
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.runGDX
import com.jettylucketjet1wincasino.onewinslots1win.game.manager.SpriteManager.GameRegion as SMG
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.Layout.Game as LG

class GameScreen : AdvancedScreen() {

    companion object {
        var tapCount = 0
            private set
    }

    private var isGameOver = false

    private val panel = Panel()
    private val item  = Image(SMG.A.region)
    private val two   = Image(SMG.B.region)
    private val chas  = Image(SMG.C.region)
    private val jet   = Jet()


    override fun show() {
        tapCount = 0
        setBackgrounds(SpriteManager.SplashRegion.BACKGROUND_1.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        mainGroup.addAction(Actions.alpha(0f))
        addPanel()
        addItem()
        addTwo()
        addChas()
        addJet()
        mainGroup.addAction(Actions.sequence(
            Actions.fadeIn(MAIN_ANIM_SPEED),
            Actions.delay(0.5f),
            Actions.run { startGame() }
        ))
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addPanel() {
        addActor(panel)
        panel.apply {
            setBounds(LG.panel)
            finishBlock = { endGame() }
        }
    }

    private fun AdvancedGroup.addItem() {
        addActor(item)
        item.apply {
            setSize(LG.itemSize.width, LG.itemSize.height)
            disable()
            move()
            toClickable().setOnClickListener {
                panel.tapFlow.value = ++tapCount
                move()
            }
        }
    }

    private fun AdvancedGroup.addTwo() {
        addActor(two)
        two.apply {
            setSize(LG.itemSize.width, LG.itemSize.height)
            disable()
            addAction(Actions.alpha(0f))

            toClickable().setOnClickListener {
                disable()
                tapCount *= 2
                panel.tapFlow.value = tapCount
                addAction(Actions.alpha(0f))
            }
        }
    }

    private fun AdvancedGroup.addChas() {
        addActor(chas)
        chas.apply {
            setSize(LG.itemSize.width, LG.itemSize.height)
            disable()
            addAction(Actions.alpha(0f))

            toClickable().setOnClickListener {
                disable()
                panel.timerTimeFlow.value += 10
                addAction(Actions.alpha(0f))
            }
        }
    }

    private fun AdvancedGroup.addJet() {
        addActor(jet)
        jet.disable()
        jet.setBounds(LG.jet)
        jet.setOrigin(Align.center)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun Actor.move() {
        val randomX = (LG.itemMinPos.x.toInt()..LG.itemMaxPos.x.toInt()).shuffled().first().toFloat()
        val randomY = (LG.itemMinPos.y.toInt()..LG.itemMaxPos.y.toInt()).shuffled().first().toFloat()

        addAction(Actions.moveTo(randomX, randomY))
    }

    private fun moveJet() {
        coroutine.launch {
            var randomX: Float
            var randomY: Float
            var time   : Float
            var angle  : Float
            var scale  : Float

            val repeatableFlow = MutableStateFlow(0.00f)

            repeatableFlow.collect {
                if(isGameOver.not()) {
                    randomX = (LG.jetMinPos.x.toInt()..LG.jetMaxPos.x.toInt()).shuffled().first().toFloat()
                    randomY = (LG.jetMinPos.y.toInt()..LG.jetMaxPos.y.toInt()).shuffled().first().toFloat()
                    time    = ((30..70).shuffled().first() / 100f)
                    angle   = (-35..35).shuffled().first().toFloat()
                    scale   = ((20..120).shuffled().first() / 100f)

                    runGDX {
                        jet.apply {
                            clearActions()
                            addAction(Actions.sequence(
                                Actions.parallel(
                                    Actions.rotateTo(angle, time),
                                    Actions.moveTo(randomX, randomY, time),
                                    Actions.sequence(
                                        Actions.scaleBy(scale, scale, time),
                                        Actions.scaleBy(-scale, -scale, time),
                                    ),
                                ),
                                Actions.run { repeatableFlow.value += 0.1f }
                            ))
                        }
                    }
                }
            }
        }
    }

    private fun launchTwo() {
        coroutine.launch {
            while (isGameOver.not()) {
                delay(7_000)
                //delay((5..10).shuffled().first().toFloat().toMS)
                if (isGameOver.not()) runGDX { two.apply {
                    move()
                    enable()
                    addAction(Actions.alpha(1f))
                } }
            }
        }
    }

    private fun launchChas() {
        coroutine.launch {
            while (isGameOver.not()) {
                delay(10_000)
                //delay((5..10).shuffled().first().toFloat().toMS)
                if (isGameOver.not()) runGDX { chas.apply {
                    move()
                    enable()
                    addAction(Actions.alpha(1f))
                } }
            }
        }
    }

    private fun startGame() {
        item.enable()
        moveJet()
        launchTwo()
        launchChas()
        panel.isStartTimerFlow.value = true
    }

    private fun endGame() {
        isGameOver = true

        mainGroup.disable()

        mainGroup.addAction(Actions.sequence(
            Actions.fadeOut(MAIN_ANIM_SPEED),
            Actions.run { NavigationManager.navigate(ResultScreen()) }
        ))
    }

}