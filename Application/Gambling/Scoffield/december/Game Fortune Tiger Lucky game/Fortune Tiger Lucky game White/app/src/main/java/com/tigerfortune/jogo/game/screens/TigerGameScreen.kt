package com.tigerfortune.jogo.game.screens

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.tigerfortune.jogo.game.LibGDXGame
import com.tigerfortune.jogo.game.actors.AItemPanel
import com.tigerfortune.jogo.game.actors.AResultGroup
import com.tigerfortune.jogo.game.actors.ATimerGroup
import com.tigerfortune.jogo.game.actors.button.AButton
import com.tigerfortune.jogo.game.actors.checkbox.ACheckBox
import com.tigerfortune.jogo.game.box2d.AbstractBody
import com.tigerfortune.jogo.game.box2d.AbstractJoint
import com.tigerfortune.jogo.game.box2d.BodyId
import com.tigerfortune.jogo.game.box2d.bodies.BItem
import com.tigerfortune.jogo.game.box2d.bodies.BItemBomb
import com.tigerfortune.jogo.game.box2d.bodies.BTiger
import com.tigerfortune.jogo.game.box2d.bodies.standart.BStatic
import com.tigerfortune.jogo.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.tigerfortune.jogo.game.utils.actor.animHide
import com.tigerfortune.jogo.game.utils.actor.animShow
import com.tigerfortune.jogo.game.utils.actor.setOnClickListener
import com.tigerfortune.jogo.game.utils.advanced.AdvancedMouseScreen
import com.tigerfortune.jogo.game.utils.advanced.AdvancedStage
import com.tigerfortune.jogo.game.utils.region
import com.tigerfortune.jogo.game.utils.runGDX
import com.tigerfortune.jogo.game.utils.toB2
import com.tigerfortune.jogo.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class TigerGameScreen(override val game: LibGDXGame) : AdvancedMouseScreen(game) {

    private val assets = game.gameAssets

    // Actor
    private val aTimer         = ATimerGroup(this)
    private val aItemPanelList = List(3) { AItemPanel(this) }
    private val aResult        = AResultGroup(this).apply { color.a = 0f }

    // Body
    private val bStatic = BStatic(this)
    private val bTiger  = BTiger(this)

    // Joint
    private val jPrismatic = AbstractJoint<PrismaticJoint, PrismaticJointDef>(this)

    // Field
    private val randomIndexList = (0..6).shuffled().take(3)
    private val randomCountList = List<Int>(3) { 1/*(3..4).random()*/ }
    private val tigerWidth      = getTigerWidthByType()

    private val itemFlow = MutableSharedFlow<BItem>(replay = 15)
    private val bombFlow = MutableSharedFlow<BItemBomb>(replay = 1)


    override fun show() {
        stageUI.root.animHide()
        setUIBackground(getBackgroundByType().region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createB_Static()
        createB_Tiger()
        createJ_Prismatic()
        createB_Items()
        createB_Bomb()

        addBack()
        addPause()
        addTimer()
        addItemPanelList()

        addAndFillActor(aResult)

        aTimer.startTimer {
            aTimer.isPause = true
            isWorldPause   = true

            aResult.update(false)
            aResult.animShow(TIME_ANIM_SCREEN_ALPHA)
            aResult.touchable = Touchable.enabled
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addBack() {
        val menu = AButton(this@TigerGameScreen, AButton.Static.Type.MENU)
        addActor(menu)
        menu.setBounds(45f, 1721f, 177f, 176f)

        menu.setOnClickListener(game.soundUtil) {
            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }
        }
    }

    private fun AdvancedStage.addPause() {
        val pause = ACheckBox(this@TigerGameScreen, ACheckBox.Static.Type.PAUSE)
        addActor(pause)
        pause.setBounds(887f, 1721f, 177f, 176f)

        pause.setOnCheckListener { isCheck ->
            aTimer.isPause = isCheck
            isWorldPause   = isCheck
        }
    }

    private fun AdvancedStage.addTimer() {
        addActor(aTimer)
        aTimer.setBounds(435f, 1812f, 209f, 64f)
    }

    private fun AdvancedStage.addItemPanelList() {
        var nx = 299f

        aItemPanelList.onEachIndexed { index, aItemPanel ->
            addActor(aItemPanel)
            aItemPanel.setBounds(nx, 1627f, 117f, 145f)
            nx += 39f + 117f

            aItemPanel.update(randomCountList[index], assets.items[randomIndexList[index]])
        }
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Static() {
        bStatic.create(-100f, 0f, 100f, 100f)
    }

    private fun createB_Tiger() {
        when(TigerChooseScreen.TIGER_TYPE) {
            TigerChooseScreen.TigerType.TIGER_1 -> bTiger.create(291f, 40f, 498f, 672f)
            TigerChooseScreen.TigerType.TIGER_2 -> bTiger.create(232f, 40f, 615f, 645f)
        }

        val itemId1 = BodyId.Game.items[randomIndexList[0]]
        val itemId2 = BodyId.Game.items[randomIndexList[1]]
        val itemId3 = BodyId.Game.items[randomIndexList[2]]

        bTiger.collisionList.addAll(arrayOf(BodyId.Game.BOMB, itemId1, itemId2, itemId3))

        bTiger.beginContactBlockArray.add(AbstractBody.ContactBlock { bEnemy ->

            when (bEnemy.id) {
                BodyId.Game.BOMB -> {
                    bEnemy as BItemBomb
                    if (bEnemy.atomicBoolean.getAndSet(false)) {
                        bombFlow.tryEmit(bEnemy)
                        game.soundUtil.apply { play(bomb) }
                        bTiger.body?.applyLinearImpulse(Vector2(listOf(-20000, 20000).random().toFloat(), 0f), bTiger.body?.worldCenter, true)
                    }
                }
                itemId1 -> {
                    bEnemy as BItem
                    if (bEnemy.atomicBoolean.getAndSet(false)) {
                        itemFlow.tryEmit(bEnemy)
                        aItemPanelList[0].caught()
                        checkAllItemPanel()

                        game.soundUtil.apply { play(caught) }
                    }
                }
                itemId2 -> {
                    bEnemy as BItem
                    if (bEnemy.atomicBoolean.getAndSet(false)) {
                        itemFlow.tryEmit(bEnemy)
                        aItemPanelList[1].caught()
                        checkAllItemPanel()

                        game.soundUtil.apply { play(caught) }
                    }
                }
                itemId3 -> {
                    bEnemy as BItem
                    if (bEnemy.atomicBoolean.getAndSet(false)) {
                        itemFlow.tryEmit(bEnemy)
                        aItemPanelList[2].caught()
                        checkAllItemPanel()

                        game.soundUtil.apply { play(caught) }
                    }
                }
            }
        })
    }

    private fun createB_Items() {
        repeat(15) {
            BItem(this).also { bItem ->
                bItem.collisionList.add(BodyId.Game.TIGER)

                bItem.renderBlockArray.add(AbstractBody.RenderBlock {
                    bItem.body?.let {
                        if (it.position.y <= 0f) {
                            if (bItem.atomicBoolean.getAndSet(false)) {
                                itemFlow.tryEmit(bItem)
                            }
                        }
                    }
                })

                bItem.bodyDef.gravityScale = 0f
                bItem.create(-300f, 0f, 208f, 208f)

                itemFlow.tryEmit(bItem)
            }
        }

        coroutine?.launch {
            itemFlow.collect { bItem ->
                runGDX {
                    bItem.body?.apply {
                        bItem.setNoneId()

                        setLinearVelocity(0f, 0f)
                        gravityScale = 0f
                        isAwake = false

                        setTransform(Vector2(-300f, 0f).toB2, 0f)
                    }
                }
            }
        }
        coroutine?.launch {
            itemFlow.collect { bItem ->
                delay((500L..1000L).random())
                runGDX {
                    bItem.updateImage()
                    bItem.body?.apply {
                        setTransform((104..976).random().toFloat().toB2, 2024f.toB2, 0f)

                        gravityScale = 1f
                        isAwake      = true
                    }
                }
                delay(100)
                runGDX {
                    bItem.updateId()
                    bItem.atomicBoolean.set(true)
                }
            }
        }
    }

    private fun createB_Bomb() {
        repeat(1) {
            BItemBomb(this).also { bBomb ->
                bBomb.renderBlockArray.add(AbstractBody.RenderBlock {
                    bBomb.body?.let {
                        if (it.position.y <= 0f) {
                            if (bBomb.atomicBoolean.getAndSet(false)) {
                                bombFlow.tryEmit(bBomb)
                            }
                        }
                    }
                })

                bBomb.bodyDef.gravityScale = 0f
                bBomb.create(-300f, 0f, 208f, 208f)

                bombFlow.tryEmit(bBomb)
            }
        }

        coroutine?.launch {
            bombFlow.collect { bBomb ->
                runGDX {
                    bBomb.body?.apply {
                        setLinearVelocity(0f, 0f)
                        gravityScale = 0f
                        isAwake = false

                        setTransform(Vector2(-300f, 0f).toB2, 0f)
                    }
                }
            }
        }
        coroutine?.launch {
            bombFlow.collect { bBomb ->
                delay((1000L..3000L).random())
                runGDX {
                    bBomb.body?.apply {
                        setTransform((104..976).random().toFloat().toB2, 2024f.toB2, 0f)

                        gravityScale = 1f
                        isAwake      = true
                    }
                }
                delay(100)
                bBomb.atomicBoolean.set(true)
            }
        }
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Prismatic() {
        jPrismatic.create(PrismaticJointDef().apply {
            bodyA = bStatic.body
            bodyB = bTiger.body

            lowerTranslation = 50f.toB2
            upperTranslation = (1130f-tigerWidth).toB2
            enableLimit      = true
        })
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun getBackgroundByType() = when(TigerChooseScreen.BACKGROUND_TYPE) {
        TigerChooseScreen.BackgroundType.B1 -> assets.BG1
        TigerChooseScreen.BackgroundType.B2 -> assets.BG2
        TigerChooseScreen.BackgroundType.B3 -> assets.BG3
        TigerChooseScreen.BackgroundType.B4 -> assets.BG4
    }

    private fun getTigerWidthByType() = when (TigerChooseScreen.TIGER_TYPE) {
        TigerChooseScreen.TigerType.TIGER_1 -> 498f
        TigerChooseScreen.TigerType.TIGER_2 -> 615f
    }

    private fun checkAllItemPanel() {
        if (aItemPanelList.all { it.counter == 0 }) {
            aTimer.isPause = true
            isWorldPause   = true

            aResult.update(true)
            aResult.animShow(TIME_ANIM_SCREEN_ALPHA)
            aResult.touchable = Touchable.enabled
        }
    }

}