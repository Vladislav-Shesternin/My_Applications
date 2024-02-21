package com.elastices.platferma.game.screens

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.elastices.platferma.game.actors.label.ALabelStyle
import com.elastices.platferma.game.box2d.BodyId
import com.elastices.platferma.game.box2d.WorldUtil
import com.elastices.platferma.game.box2d.bodies.BBorders
import com.elastices.platferma.game.box2d.bodies.BOrb
import com.elastices.platferma.game.box2d.bodies.BPanel
import com.elastices.platferma.game.box2d.bodies.BRec
import com.elastices.platferma.game.manager.NavigationManager
import com.elastices.platferma.game.utils.advanced.AdvancedBox2dScreen
import com.elastices.platferma.game.utils.advanced.AdvancedStage
import com.elastices.platferma.game.utils.runGDX
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameScreen: AdvancedBox2dScreen(WorldUtil()) {

    // Actors

    private val clickPanel = Actor()
    private val label      = Label("0", ALabelStyle.style(ALabelStyle.Roboto._48))

    // Body

    private val bBorders = BBorders(this)
    private val bOrb     = BOrb(this)
    private val bRec     = BRec(this)
    private val bPanel   = BPanel(this)

    private var count = 0


    override fun AdvancedStage.addActorsOnStageUI() {
        createB_Borders()
        createB_Orb()
        createB_Rec()
        createB_Panel()

        addClickPanel()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addClickPanel() {
        addActor(clickPanel)
        clickPanel.setBounds(0f, 0f, WIDTH, HEIGHT)
        clickPanel.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                bPanel.body?.linearVelocity = Vector2(0f, 0f)
                if (x < WIDTH / 2) bPanel.body?.applyForceToCenter(-10_000f, 0f, true)
                if (x > WIDTH / 2) bPanel.body?.applyForceToCenter(10_000f, 0f, true)
                return false
            }
        })

        addActor(label)
        label.setBounds(25f, 589f, 192f, 86f)
        label.setAlignment(Align.center)
    }

    // ------------------------------------------------------------------------
    // Create Body
    // ------------------------------------------------------------------------

    private fun createB_Borders() {
        bBorders.create(0f, 0f, WIDTH, HEIGHT)
    }

    private fun createB_Orb() {
        bOrb.create(674f, 341f, 53f, 53f)

        bOrb.apply {
            beginContactBlock = {
                coroutine.launch {
                    delay(50)
                    runGDX {
                        if (it.id == BodyId.PANEL) {
                            metalSound?.play(1f)
                            count++
                            label.setText(count)
                            body?.applyLinearImpulse(Vector2(0f, 400f), body?.worldCenter, true)
                        }
                        if (it.id == BodyId.BORDERS) {
                            NavigationManager.navigate(GameScreen())
                        }
                    }
                }
            }
        }
    }

    private fun createB_Rec() {
        bRec.create(42f, 111f, 1316f, 9f)
    }

    private fun createB_Panel() {
        bPanel.bodyDef.gravityScale = -1f
        bPanel.create(585f, 79f, 231f, 30f)
    }


}