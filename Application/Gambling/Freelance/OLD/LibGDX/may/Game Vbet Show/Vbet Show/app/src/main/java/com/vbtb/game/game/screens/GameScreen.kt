package com.vbtb.game.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.vbtb.game.game.box2d.AbstractBodyGroup
import com.vbtb.game.game.box2d.BodyId
import com.vbtb.game.game.box2d.WorldUtil
import com.vbtb.game.game.box2d.bodies.BBlock
import com.vbtb.game.game.box2d.bodies.BBowl
import com.vbtb.game.game.box2d.bodies.BGold
import com.vbtb.game.game.box2d.bodiesGroup.BGBorders
import com.vbtb.game.game.manager.FontTTFManager
import com.vbtb.game.game.utils.DEGTORAD
import com.vbtb.game.game.utils.Size
import com.vbtb.game.game.utils.advanced.AdvancedBox2dScreen
import com.vbtb.game.game.utils.advanced.AdvancedStage
import com.vbtb.game.game.utils.runGDX
import com.vbtb.game.game.utils.vector2
import com.vbtb.game.game.utils.Layout.Game as LG

class GameScreen: AdvancedBox2dScreen(WorldUtil()) {

    private var count = 0f

    // Actors
    private val countText = Label(count.toString(), Label.LabelStyle(FontTTFManager.RobotoFont.font_75.font, Color.WHITE))

    // Body
    private val bBowl   = BBowl(this)
    private val bBlockB = BBlock(this)
    private val bBlockT = BBlock(this)
    private val bBlockL = BBlock(this)
    private val bBlockR = BBlock(this)
    private val bGold   = BGold(this)

    // Joint

    // BodyGroup
    private val bgBorders = BGBorders(this)


    override fun AdvancedStage.addActorsOnStageUI() {
        createBG_Borders()
        createB_Block()
        createB_Gold()
        createB_Bowl()

        addCountText()

        addListener(getListener())
    }

    override fun dispose() {
        super.dispose()
        listOf<AbstractBodyGroup>(bgBorders)
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addCountText() {
        addActor(countText)
        countText.apply {
            setBounds(194f, 1335f, 357f, 111f)
            setAlignment(Align.center)
        }
    }

    // ------------------------------------------------------------------------
    // Create Body
    // ------------------------------------------------------------------------

    private fun createB_Bowl() {
        bBowl.fixtureDef.apply {
            density     = SettingsScreen.weight
            restitution = SettingsScreen.elasticity
            friction    = SettingsScreen.friction
        }
        bBowl.create(LG.bowl)
    }

    private fun createB_Gold() {
        bGold.create(LG.gold)
        bGold.apply {
            beginContactBlock = {
                if (it.id == BodyId.ORBIK) {
                    runGDX {
                        bGold.body?.setTransform(sizeConverterUIToBox.getSize(LG.gold.position()), 0f)
                        count++
                        countText.setText(count.toString())
                    }
                }
            }
        }
    }
    private fun createB_Block() {
        bBlockB.apply {
            create(200f, 0f, 344f, 37f)
            body?.apply { setTransform(worldCenter, 0 * DEGTORAD) }
            beginContactBlock = {
                if (it.id == BodyId.ORBIK) {
                    it.body?.apply {
                        runGDX { applyLinearImpulse(Vector2(0f, 100f), worldCenter, true) }
                    }
                }
            }
        }
        bBlockT.apply {
            create(200f, 1485f, 344f, 37f)
            body?.apply { setTransform(worldCenter, 180 * DEGTORAD) }
        }
        bBlockL.apply {
            create(-172f, 802f, 344f, 37f)
            body?.apply { setTransform(worldCenter, -90 * DEGTORAD) }
        }
        bBlockR.apply {
            create(573f, 802f, 344f, 37f)
            body?.apply { setTransform(worldCenter, 90 * DEGTORAD) }
        }
    }

    // ------------------------------------------------------------------------
    // Create Body Group
    // ------------------------------------------------------------------------

    private fun createBG_Borders() {
        bgBorders.create(Vector2(0f, 0f), Size(WIDTH, HEIGHT))
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Distance() {

    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun getListener() = object : InputListener() {
        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            touchDragged(event, x, y, pointer)
            return true
        }

        var pos = Vector2()

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            runGDX {
                pos = sizeConverterUIToBox.getSize(x, y).vector2
                bBlockB.body?.apply { setTransform(pos.x, position.y, angle) }
                bBlockT.body?.apply { setTransform(pos.x, position.y, angle) }
                bBlockL.body?.apply { setTransform(position.x, pos.y, angle) }
                bBlockR.body?.apply { setTransform(position.x, pos.y, angle) }
            }
        }
    }
}