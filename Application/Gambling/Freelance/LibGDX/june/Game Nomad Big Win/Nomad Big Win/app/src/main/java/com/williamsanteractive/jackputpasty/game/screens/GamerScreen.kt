package com.williamsanteractive.jackputpasty.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.williamsanteractive.jackputpasty.game.actors.button.AButton
import com.williamsanteractive.jackputpasty.game.actors.button.AButtonStyle
import com.williamsanteractive.jackputpasty.game.box2d.BodyId
import com.williamsanteractive.jackputpasty.game.box2d.WorldUtil
import com.williamsanteractive.jackputpasty.game.box2d.bodies.BBit
import com.williamsanteractive.jackputpasty.game.box2d.bodies.BBottom
import com.williamsanteractive.jackputpasty.game.box2d.bodies.BItem
import com.williamsanteractive.jackputpasty.game.box2d.bodiesGroup.BGCrug
import com.williamsanteractive.jackputpasty.game.manager.FontTTFManager
import com.williamsanteractive.jackputpasty.game.manager.SpriteManager
import com.williamsanteractive.jackputpasty.game.utils.Layout
import com.williamsanteractive.jackputpasty.game.utils.Size
import com.williamsanteractive.jackputpasty.game.utils.actor.setBounds
import com.williamsanteractive.jackputpasty.game.utils.advanced.AdvancedBox2dScreen
import com.williamsanteractive.jackputpasty.game.utils.advanced.AdvancedGroup
import com.williamsanteractive.jackputpasty.game.utils.runGDX
import com.williamsanteractive.jackputpasty.game.utils.tmpVector2
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GamerScreen: AdvancedBox2dScreen(WorldUtil()) {

    companion object {
        private var balanceFlow = MutableStateFlow(5000)
    }

    private val pan = Image(SpriteManager.GameRegion.RECOTAPANELKA.region)
    private val bal = Label(balanceFlow.value.toString(), Label.LabelStyle(FontTTFManager.Money.font_55.font, Color.WHITE))
    private val btn = AButton(AButtonStyle.GO)
    private val bot = Image(SpriteManager.GameRegion.COSTAET.region)

    // Body

    private val bListBit = List(4) { BBit(this) }
    private val bBottom  = BBottom(this)

    // BodyGroup

    private val bgCrug1 = List(5) { BGCrug(this) }
    private val bgCrug2 = List(5) { BGCrug(this) }
    private val bgCrug3 = List(5) { BGCrug(this) }

    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addGirl()
        addPanBal()
        addGo()
        addBot()

        createB_Bits()
        createB_Bottom()

        createBG_Crug()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addGirl() {
        val girl = Image(SpriteManager.SplashRegion.GIRL.region)
        addActor(girl)
        girl.setBounds(Layout.Splash.girl)
        girl.addAction(Actions.forever(Actions.sequence(
            Actions.moveTo(871f, 0f, 10f, Interpolation.fade),
            Actions.moveTo(-121f, 0f, 10f, Interpolation.sine),
            Actions.moveTo(224f, 0f, 7f, Interpolation.fade),
        )))
    }
    private fun AdvancedGroup.addPanBal() {
        addActors(pan, bal)
        pan.setBounds(22f, 736f, 360f, 69f)
        bal.apply {
            setBounds(22f, 736f, 360f, 69f)
            setAlignment(Align.center)
        }

        coroutine.launch { balanceFlow.collect { runGDX { bal.setText(it) } } }
    }
    private fun AdvancedGroup.addGo() {
        addActor(btn)
        btn.apply {
            setBounds(1522f, 70f, 192f, 192f)
            setOnClickListener {
                disable()
                if (balanceFlow.value >= 100) {
                    balanceFlow.value -= 100
                    createB_Item()
                }
                coroutine.launch {
                    delay(500)
                    runGDX { enable() }
                }
            }
        }
    }
    private fun AdvancedGroup.addBot() {
        addActor(bot)
        bot.setBounds(100f, 0f, 1314f, 56f)
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Bits() {
        val listPos = listOf(
            Vector2(100f, 0f),
            Vector2(522f, 0f),
            Vector2(872f, 0f),
            Vector2(1225f, 0f),
        )

        val listCost = listOf(1000, 100, 200, 500)

        bListBit.onEachIndexed { index, bit ->
            bit.create(listPos[index], Size(189f, 56f))
            bit.cost = listCost[index]

            bit.beginContactBlock = { item ->
                if (item.id == BodyId.ITEM) {
                    runGDX {
                        balanceFlow.value += bit.cost
                        item.startDestroy()
                    }
                }
            }
        }
    }

    private fun createB_Bottom() {
        bBottom.create(0f, -68f, 1742f, 68f)
        bBottom.beginContactBlock = { item ->
            if (item.id == BodyId.ITEM) {
                runGDX { item.startDestroy() }
            }
        }
    }

    val t = BItem.Typ.values().toMutableList()

    private fun createB_Item() {
        t.shuffled().first().also { ttt ->
            BItem(this, ttt).create(
                tmpVector2.set((150..1500).shuffled().first().toFloat(), 800f), ttt.size
            )
        }
    }

    // ---------------------------------------------------
    // Create BodyGroup
    // ---------------------------------------------------

    private fun createBG_Crug() {
        var nx1 = 525f
        var nx2 = 244f
        var nx3 = 204f

        bgCrug1.onEach {
            it.create(Vector2(nx1, 546f), Size(82f, 82f))
            nx1 += 82f + 150f
        }
        bgCrug2.onEach {
            it.create(Vector2(nx2, 345f), Size(131f, 131f))
            nx2 += 131f + 150f
        }
        bgCrug3.onEach {
            it.create(Vector2(nx3, 207f), Size(82f, 82f))
            nx3 += 82f + 150f
        }
    }


}