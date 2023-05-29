package aer.com.gamesas.mobile.slot.game.screens

import aer.com.gamesas.mobile.slot.game.actors.button.AButton
import aer.com.gamesas.mobile.slot.game.actors.button.AButtonStyle
import aer.com.gamesas.mobile.slot.game.box2d.BodyId
import aer.com.gamesas.mobile.slot.game.box2d.WorldUtil
import aer.com.gamesas.mobile.slot.game.box2d.bodies.BBall
import aer.com.gamesas.mobile.slot.game.box2d.bodies.BCirc
import aer.com.gamesas.mobile.slot.game.box2d.bodies.BRect
import aer.com.gamesas.mobile.slot.game.manager.FontTTFManager
import aer.com.gamesas.mobile.slot.game.manager.NavigationManager
import aer.com.gamesas.mobile.slot.game.manager.SpriteManager
import aer.com.gamesas.mobile.slot.game.utils.Layout
import aer.com.gamesas.mobile.slot.game.utils.actor.setBounds
import aer.com.gamesas.mobile.slot.game.utils.advanced.AdvancedBox2dScreen
import aer.com.gamesas.mobile.slot.game.utils.advanced.AdvancedGroup
import aer.com.gamesas.mobile.slot.game.utils.advanced.AdvancedScreen
import aer.com.gamesas.mobile.slot.game.utils.runGDX
import aer.com.gamesas.mobile.slot.util.log
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Random
import aer.com.gamesas.mobile.slot.game.utils.Layout.Plinko as LP

class PlinkoScreen: AdvancedBox2dScreen(WorldUtil()) {

    private val goBtn        = AButton(AButtonStyle.go)
    private val gold         = Image(SpriteManager.GameRegion.MONY.region)
    private val point        = Image(SpriteManager.GameRegion.POINT.region)
    private val balanceLabel = Label("0$", Label.LabelStyle(FontTTFManager.RegularFont.font_75.font, Color.WHITE))
    private val countLabel   = Label("10/10", Label.LabelStyle(FontTTFManager.RegularFont.font_50.font, Color.WHITE)).apply { setAlignment(Align.center) }
    private val win1Label    = Label("1000", Label.LabelStyle(FontTTFManager.RegularFont.font_20.font, Color.WHITE)).apply { setAlignment(Align.center) }
    private val win2Label    = Label("500", Label.LabelStyle(FontTTFManager.RegularFont.font_20.font, Color.WHITE)).apply { setAlignment(Align.center) }
    private val win3Label    = Label("1000", Label.LabelStyle(FontTTFManager.RegularFont.font_20.font, Color.WHITE)).apply { setAlignment(Align.center) }

    // Body

    private val bRectList = List(3) { BRect(this) }
    private val bCircList = List(16) { BCirc(this) }
    private val bBallGoldList  = List(5) { BBall(this, BBall.Type.GOLD) }
    private val bBallGreenList = List(5) { BBall(this, BBall.Type.GREEN) }
    private val bBalls = (bBallGoldList + bBallGreenList).shuffled()


    // Fields

    private var countBall = 10
    private var balance   = 0

    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addGold()
        addCount()
        addWin()
        addBalance()
        addGo()
        addPoint()

        createRects()
        createCirc()
        createBalls()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedGroup.addGold() {
        addActor(gold)
        gold.setBounds(21f, 1393f, 117f, 130f)
    }

    private fun AdvancedGroup.addCount() {
        addActor(countLabel)
        countLabel.setBounds(166f, 79f, 154f, 90f)
    }

    private fun AdvancedGroup.addWin() {
        addActors(win1Label, win2Label, win3Label)
        win1Label.setBounds(164f, 269f, 60f, 35f)
        win2Label.setBounds(324f, 269f, 60f, 35f)
        win3Label.setBounds(484f, 269f, 60f, 35f)
    }

    private fun AdvancedGroup.addBalance() {
        addActor(balanceLabel)
        balanceLabel.apply {
            setBounds(138f, 1414f, 515f, 91f)
        }
    }

    private fun AdvancedGroup.addGo() {
        addActor(goBtn)
        goBtn.apply {
            setBounds(348f, 79f, 193f, 90f)

            var countDestroy = 0

            setOnClickListener {
                if (countBall - 1 >= 0) runGDX {
                    bBalls[--countBall].also {
                        it.actor.addAction(Actions.fadeIn(0.2f))
                        it.body?.apply {
                            gravityScale = 1f

                            applyForce(if (Random().nextBoolean()) 50f else -50f, if (Random().nextBoolean()) 50f else 100f, worldCenter.x, worldCenter.y, true)
                        }
                        var flagA = true
                        it.renderBlock = { time ->
                            if ((it.body?.position?.x ?: -1f) < 0f || (it.body?.position?.x ?: -1f) > boxW || (it.body?.position?.y ?: -1f) < 0) {
                                if (flagA) {
                                    flagA = false
                                    it.startDestroy()
                                    countDestroy++
                                    log("count destroy = $countDestroy")
                                    if (countDestroy == 10) NavigationManager.navigate(MenuScreen())
                                }
                            }
                        }
                        var flagB = true
                        it.beginContactBlock = { enemyBody ->
                            if (flagB) {
                                if (enemyBody.id == BodyId.RECT) {
                                    flagB = false
                                    balance += (enemyBody as BRect).win
                                    balanceLabel.setText(balance.toString())

                                    it.startDestroy()
                                    countDestroy++
                                    log("count destroy = $countDestroy")
                                    if (countDestroy == 10) NavigationManager.navigate(MenuScreen())
                                }
                            }
                        }
                    }

                    if (countBall==0) disable()
                    countLabel.setText("$countBall/10")
                }
            }
        }
    }

    private fun AdvancedGroup.addPoint() {
        addActor(point)
        point.apply {
            setBounds(346f, 1268f, 16f, 16f)
        }
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createRects() {
        val costList = listOf(1000, 500, 1000)

        var nx = LP.rect.x
        bRectList.onEachIndexed { index, bRect ->
            bRect.win = costList[index]
            with(LP.rect) {
                bRect.create(nx, y, w, h)
                nx += w+hs
            }
        }
    }

    private fun createCirc() {
        bCircList.onEachIndexed { index, bCirc ->
            bCirc.create(LP.circList[index])
        }
    }

    private fun createBalls() {
        bBalls.onEach {  bBall ->
            bBall.apply {
                actor.addAction(Actions.alpha(0f))
                bodyDef.gravityScale = 0f
                create(317f, 1239f, 75f, 75f)
            }
        }
    }

}