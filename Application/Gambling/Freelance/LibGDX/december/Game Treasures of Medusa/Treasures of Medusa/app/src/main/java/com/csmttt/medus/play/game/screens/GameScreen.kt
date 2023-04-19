package com.csmttt.medus.play.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.csmttt.medus.play.game.actors.InfoPanel
import com.csmttt.medus.play.game.actors.button.AButton
import com.csmttt.medus.play.game.actors.button.AButtonStyle
import com.csmttt.medus.play.game.box2d.bodies.BodyId
import com.csmttt.medus.play.game.box2d.bodies.element.Element
import com.csmttt.medus.play.game.box2d.bodies.mech.MechLeft
import com.csmttt.medus.play.game.box2d.bodies.mech.MechRight
import com.csmttt.medus.play.game.box2d.bodies.meduza.MeduzaBig
import com.csmttt.medus.play.game.box2d.bodies.meduza.MeduzaMini
import com.csmttt.medus.play.game.box2d.bodies.sikira.Sikira
import com.csmttt.medus.play.game.box2d.bodies.tarelka.Tarelka
import com.csmttt.medus.play.game.manager.SpriteManager
import com.csmttt.medus.play.game.util.Size
import com.csmttt.medus.play.game.util.advanced.AdvancedBox2dScreen
import com.csmttt.medus.play.game.util.advanced.AdvancedStage
import com.csmttt.medus.play.game.util.disable
import com.csmttt.medus.play.game.util.enable
import com.csmttt.medus.play.util.toMS
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.*
import com.csmttt.medus.play.game.util.Layout.Game as LG

class GameScreen: AdvancedBox2dScreen(1800f, 800f, 50f, 22.22f) {

    private val rightButton = AButton(AButtonStyle.right)
    private val leftButton  = AButton(AButtonStyle.left)
    private val tare        = Tarelka()

    private val elementOutFlow = MutableSharedFlow<Element>(replay = 24)

    private val elementList = (
            MutableList(3) {
                Element(Element.Data._1).apply {
                    id = BodyId.E1
                    originId = BodyId.E1
                }
            } + MutableList(3) {
                Element(Element.Data._2).apply {
                    id = BodyId.E2
                    originId = BodyId.E2
                }
            } + MutableList(3) {
                Element(Element.Data._3).apply {
                    id = BodyId.E3
                    originId = BodyId.E3
                }
            } + MutableList(3) {
                Element(Element.Data._4).apply {
                    id = BodyId.E4
                    originId = BodyId.E4
                }
            } + MutableList(3) {
                Element(Element.Data._5).apply {
                    id = BodyId.E5
                    originId = BodyId.E5
                }
            } + MutableList(3) {
                Element(Element.Data._6).apply {
                    id = BodyId.E6
                    originId = BodyId.E6
                }
            } + MutableList(3) {
                Element(Element.Data._7).apply {
                    id = BodyId.E7
                    originId = BodyId.E7
                }
            } + MutableList(3) {
                Element(Element.Data._8).apply {
                    id = BodyId.E8
                    originId = BodyId.E8
                }
            }
    ).shuffled()

    private val infoPanel  = InfoPanel()
    private val infoButton = AButton(AButtonStyle.info)



    override fun show() {
        super.show()
        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        coroutine.launch {
            createButtons()
            createTare()
            createMech()
            createSikira()
            createMeduzaBigList()
            createMeduzaMiniList()
            coroutine.launch { createElementList() }

            Gdx.app.postRunnable {
                addInfoButton()
                addInfoPanel()
            }
        }
    }

    // ------------------------------------------------------------------------
    // Create Bodies
    // ------------------------------------------------------------------------
    private fun createButtons() {
        val rightArea = Actor()
        val leftArea  = Actor()

        stageUI.addActors(rightArea, leftArea, rightButton, leftButton, )

        with(LG.right) { rightButton.setBounds(x, y, w, h) }
        with(LG.left) { leftButton.setBounds(x, y, w, h) }
        with(LG.rightArea) { rightArea.setBounds(x, y, w, h) }
        with(LG.leftArea) { leftArea.setBounds(x, y, w, h) }

        rightButton.addArea(rightArea)
        leftButton.addArea(leftArea)

        var longJob: Job? = null
        var newTareX = sizeConverterUIToBox.getSizeX(LG.tarelka.x)

        fun touchDownBlock(x: Float) {
            longJob = coroutine.launch {
                while (isActive) {
                    newTareX += x
                    Gdx.app.postRunnable {
                        newTareX = when {
                            newTareX > 43.4191915f -> 43.4191915f
                            newTareX < 0f -> 0f
                            else -> newTareX
                        }
                        tare.body.setTransform(newTareX, tare.body.position.y, 0f)
                    }
                    delay(7)
                }
            }
        }

        leftButton.apply {
            touchDownBlock = { _, _ ->
                rightButton.disable(false)
                rightArea.disable()
                touchDownBlock(-0.25f)
            }
            touchUpBlock = { _, _ ->
                rightButton.enable()
                rightArea.enable()
                longJob?.cancel()
            }
        }

        rightButton.apply {
            touchDownBlock = { _, _ ->
                leftButton.disable(false)
                leftArea.disable()
                touchDownBlock(0.25f)
            }
            touchUpBlock = { _, _ ->
                leftButton.enable()
                leftArea.enable()
                longJob?.cancel()
            }
        }
    }

    private fun createMech() {
        MechRight().also { mech ->
            mech.initialize(
                stageUI,
                sizeConverterUIToBox,
                sizeConverterBoxToUI,
                Vector2(LG.mechRight.x, LG.mechRight.y),
                Size(LG.mechRight.w, LG.mechRight.h)
            )
            mech.setRenderBlock {
                mech.body.apply { setTransform(position, angle + 0.1f) }
            }
        }
        MechLeft().also { mech ->
            mech.initialize(
                stageUI,
                sizeConverterUIToBox,
                sizeConverterBoxToUI,
                Vector2(LG.mechLeft.x, LG.mechLeft.y),
                Size(LG.mechLeft.w, LG.mechLeft.h)
            )
            mech.setRenderBlock {
                mech.body.apply { setTransform(position, angle - 0.1f) }
            }
        }
    }

    private fun createSikira() {
        Sikira().also { sikira ->
            sikira.initialize(
                stageUI,
                sizeConverterUIToBox,
                sizeConverterBoxToUI,
                Vector2(LG.sikira.x, LG.sikira.y),
                Size(LG.sikira.w, LG.sikira.h)
            )
            var a = 0.01f
            sikira.setRenderBlock {
                sikira.body.apply {
                    if (angle >= 1.7f) a = -0.05f
                    if (angle <= -1.7f) a = 0.05f
                    setTransform(position, angle + a)

                }
            }
        }
    }

    private fun createTare() {
        tare.initialize(
            stageUI,
            sizeConverterUIToBox,
            sizeConverterBoxToUI,
            Vector2(LG.tarelka.x, LG.tarelka.y),
            Size(LG.tarelka.w, LG.tarelka.h)
        )
        tare.setBeginContactBlock {
            when (it) {
                is Element -> {
                    it.also { bonus ->
                        when (bonus.id) {
                            BodyId.E1 -> infoPanel.updateText(0)
                            BodyId.E2 -> infoPanel.updateText(1)
                            BodyId.E3 -> infoPanel.updateText(2)
                            BodyId.E4 -> infoPanel.updateText(3)
                            BodyId.E5 -> infoPanel.updateText(4)
                            BodyId.E6 -> infoPanel.updateText(5)
                            BodyId.E7 -> infoPanel.updateText(6)
                            BodyId.E8 -> infoPanel.updateText(7)
                            else -> { }
                        }

                        bonus.bodyOut()
                        elementOutFlow.tryEmit(bonus)
                    }
                }
            }
        }
    }

    private fun createMeduzaBigList() {
        LG.meduzaBigPositionList.onEach{ pos ->
            MeduzaBig().initialize(
                stageUI,
                sizeConverterUIToBox,
                sizeConverterBoxToUI,
                Vector2(pos.x, pos.y),
                Size(LG.meduzaBigSize.width, LG.meduzaBigSize.height),
            )
        }
    }

    private fun createMeduzaMiniList() {

        fun Body.rotateLeft() = setTransform(position, angle - 0.05f)
        fun Body.rotateRight() = setTransform(position, angle + 0.05f)

        LG.meduzaMiniPositionList.onEach{ pos ->
            MeduzaMini().apply {
                initialize(
                    this@GameScreen.stageUI,
                    this@GameScreen.sizeConverterUIToBox,
                    this@GameScreen.sizeConverterBoxToUI,
                    Vector2(pos.x, pos.y),
                    Size(LG.meduzaMiniSize.width, LG.meduzaMiniSize.height),
                )
                val rotateBlock: () -> Unit = if (Random().nextBoolean()) { { body.rotateLeft() } } else { { body.rotateRight() } }
                setRenderBlock { rotateBlock() }
            }
        }
    }

    private suspend fun createElementList() {
        fun getX() = (LG.elementStart.toInt()..LG.elementEnd.toInt()).shuffled().first().toFloat()

        elementList.onEach { bonus ->
            delay(2f.toMS)

            bonus.fixtureDef.apply {
                restitution = Random().nextFloat()
                friction = Random().nextFloat()
            }

            bonus.initialize(
                stageUI,
                sizeConverterUIToBox,
                sizeConverterBoxToUI,
                Vector2(getX(), LG.elementY),
                LG.elementSize
            )

            Gdx.app.postRunnable {
                bonus.setRenderBlock {
                    if (bonus.actor.x + bonus.actor.width < 0f || bonus.actor.y + bonus.actor.height < 0f) {
                        bonus.bodyOut()
                        elementOutFlow.tryEmit(bonus)
                    }
                }
            }
        }

        coroutine.launch {
            elementOutFlow.collect { bonus ->
                delay((500..1000L).shuffled().first())

                Gdx.app.postRunnable {
                    bonus.body.setTransform(
                        sizeConverterUIToBox.getSizeX(getX()),
                        sizeConverterUIToBox.getSizeY(LG.elementY),
                        0f
                    )
                    bonus.id = bonus.originId
                    bonus.body.gravityScale = 1f
                    bonus.body.isAwake = true
                }
            }
        }

    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addInfoPanel() {
        addActor(infoPanel)
        infoPanel.apply {
            this.toFront()
            with(LG.infoPanel) { setBounds(x, y, w, h) }
        }
    }

    private var infoFlag = true
    private fun AdvancedStage.addInfoButton() {
        addActor(infoButton)
        infoButton.apply {
            with(LG.infoButton) { setBounds(x, y, w, h) }
            setOnClickListener {
                if (infoFlag) {
                    infoFlag = false
                    infoPanel.addAction(Actions.fadeIn(1f))
                }
                else {
                    infoFlag = true
                    infoPanel.addAction(Actions.fadeOut(1f))
                }
            }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun Element.bodyOut() {
        Gdx.app.postRunnable {
            id = BodyId.NONE
            body.apply {
                linearVelocity = Vector2(0f, 0f)
                setTransform(10f, 25f, 0f)
                isAwake = false
                gravityScale = 0f
            }
        }
    }

}