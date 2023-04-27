package com.invt.nard.app.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Action
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.invt.nard.app.game.LibGDXGame
import com.invt.nard.app.game.actors.button.AButton
import com.invt.nard.app.game.actors.button.AButtonStyle
import com.invt.nard.app.game.box2d.AbstractBodyGroup
import com.invt.nard.app.game.box2d.WorldUtil
import com.invt.nard.app.game.box2d.bodies.BBall
import com.invt.nard.app.game.box2d.bodies.BBit
import com.invt.nard.app.game.box2d.bodies.BCir
import com.invt.nard.app.game.box2d.bodiesGroup.BGBorders
import com.invt.nard.app.game.manager.FontTTFManager
import com.invt.nard.app.game.manager.SpriteManager
import com.invt.nard.app.game.utils.Layout
import com.invt.nard.app.game.utils.Size
import com.invt.nard.app.game.utils.actor.setBounds
import com.invt.nard.app.game.utils.advanced.AdvancedBox2dScreen
import com.invt.nard.app.game.utils.advanced.AdvancedGroup
import com.invt.nard.app.game.utils.advanced.AdvancedStage
import com.invt.nard.app.game.utils.runGDX
import kotlinx.coroutines.launch
import com.invt.nard.app.game.utils.Layout.Game as LG

class GameScreen: AdvancedBox2dScreen(WorldUtil()) {

    // Actors
    private val recordLabel = Label("Рекорд:", Label.LabelStyle(FontTTFManager.SemiBold.font_50.font, Color.WHITE))
    private val recoLabel   = Label("", Label.LabelStyle(FontTTFManager.SemiBold.font_50.font, Color.WHITE))
    private val rightI      = Image(SpriteManager.GameRegion.RIGHT.region)
    private val leftI       = Image(SpriteManager.GameRegion.LEFT.region)

    // Body
    private val bBall    = BBall(this, BBall.Type.values()[ProfileScreen.indexBall])
    private val bCirList = List(4) { BCir(this) }
    private val bBitcoin = BBit(this)

    // Joint

    // BodyGroup
    private val bgBorders = BGBorders(this)

    // Fields
    private var record = 0

    override fun show() {
        super.show()
        setBackBackground(SpriteManager.SplashRegion.BACKGROUND.region)
        mainGroup.addListener(getListener())
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addRecord()
        addReco()
        addRightLeft()

        createBG_Borders()
        createB_Cir()
        createB_Bitcoin()
        createB_Ball()
    }

    override fun dispose() {
        super.dispose()
        if (record > LibGDXGame.recordFlow.value) LibGDXGame.recordFlow.value = record
        listOf<AbstractBodyGroup>(bgBorders)
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedGroup.addRecord() {
        addActor(recordLabel)
        recordLabel.apply {
            setBounds(LG.record)
        }
    }
    private fun AdvancedGroup.addReco() {
        addActor(recoLabel)
        recoLabel.apply {
            setBounds(Layout.Profile.reco)
            setText(record)
        }
    }
    private fun AdvancedGroup.addRightLeft() {
        addActors(rightI, leftI)
        rightI.setBounds(LG.right)
        leftI.setBounds(LG.left)
    }

    // ------------------------------------------------------------------------
    // Create Body
    // ------------------------------------------------------------------------
    private fun createB_Ball() {
        bBall.create(669f, 305f, 132f, 132f)
        bBall.body?.gravityScale = 0f
    }

    private fun createB_Bitcoin() {
        bBitcoin.create(695f, 529f, 80f, 80f)

        bBitcoin.endContactBlock = {
            record++
            recoLabel.setText(record)
            bitcoinChangPos()
        }
    }

    private fun createB_Cir() {
        var nx = -251f
        bCirList.onEachIndexed { index, cir ->
            cir.create(nx, -118f, 502f, 236f)
            nx += 502f
            if (index.inc() == 2) nx -= 36
        }
    }

    // ------------------------------------------------------------------------
    // Create Body Group
    // ------------------------------------------------------------------------

    private fun createBG_Borders() {
        bgBorders.create(Vector2(-92f, -61f), Size(1641f, 864f))
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Distance() {

    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private var isStart = true

    private fun getListener() = object : InputListener() {
        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            if (isStart) {
                isStart = false
                rightI.addAction(Actions.fadeOut(1f))
                leftI.addAction(Actions.fadeOut(1f))
                bBall.body?.gravityScale = 1f
            }
            return true
        }

        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            if (x < WIDTH / 2) {
                bBall.body?.apply {
                    applyLinearImpulse(Vector2(-20f, 15f), worldCenter, true)
                }
            } else {
                bBall.body?.apply {
                    applyLinearImpulse(Vector2(20f, 15f), worldCenter, true)
                }
            }
        }
    }

    private fun bitcoinChangPos() {
        val randomX = (0..1390).shuffled().first().toFloat()
        val randomY = (139..662).shuffled().first().toFloat()

        runGDX {
            bBitcoin.body?.apply {
                setTransform(Vector2(randomX, randomY).toBoxSize, 0f)
            }
        }
    }
}