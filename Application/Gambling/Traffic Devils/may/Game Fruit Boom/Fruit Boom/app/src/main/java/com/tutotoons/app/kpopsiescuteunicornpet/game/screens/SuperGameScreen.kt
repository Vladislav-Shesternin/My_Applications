package com.tutotoons.app.kpopsiescuteunicornpet.game.screens

import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef
import com.badlogic.gdx.physics.box2d.joints.WeldJoint
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.utils.Align
import com.tutotoons.app.kpopsiescuteunicornpet.game.GDXGame
import com.tutotoons.app.kpopsiescuteunicornpet.game.actors.ATutorials
import com.tutotoons.app.kpopsiescuteunicornpet.game.actors.button.AButton
import com.tutotoons.app.kpopsiescuteunicornpet.game.box2d.AbstractBody
import com.tutotoons.app.kpopsiescuteunicornpet.game.box2d.AbstractJoint
import com.tutotoons.app.kpopsiescuteunicornpet.game.box2d.BodyId
import com.tutotoons.app.kpopsiescuteunicornpet.game.box2d.WorldContactListener
import com.tutotoons.app.kpopsiescuteunicornpet.game.box2d.bodies.BItem
import com.tutotoons.app.kpopsiescuteunicornpet.game.box2d.bodies.BItemSensor
import com.tutotoons.app.kpopsiescuteunicornpet.game.box2d.bodiesGroup.BGBorders
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.*
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.actor.animHide
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.actor.animShowSuspend
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.actor.disable
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.actor.enable
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedStage
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedUserScreen
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.font.FontParameter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SuperGameScreen(override val game: GDXGame): AdvancedUserScreen() {

    private val parameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS)
    private val font50    = fontGenerator_MiltonianTattoo.generateFont(parameter.setSize(50))

    // Actor
    private val tutorials      = ATutorials(this).apply { color.a = 0f }

    private val backBtn        = AButton(this, AButton.Static.Type.Back).apply { color.a = 0f }
    private val fruitPanel     = Image(game.assetsAll.fruit_panel).apply { color.a = 0f }
    private val fruitRecordLbl = Label("0", LabelStyle(font50, GameColor.golden)).apply { color.a = 0f }

    // Body
    private val bItemList   = List(50) { BItem(this) }
    private val bItemSensor = BItemSensor(this)

    // Body Group
    private val bgBorders = BGBorders(this)

    // Field
    private val flowBItem       = MutableSharedFlow<BItem>(50)
    private val fruitRecordFlow = MutableStateFlow(0)

    private val randomItemSize get() = (100..150).random().toFloat()
    private val randomItemX    get() = (55..1405).random().toFloat()

    override fun show() {
        game.musicUtil.apply { music = game.apply {
            isLooping = true
            coff      = 0.25f
        } }

        setBackBackground(game.assetsLoader.background_purple.region)
        super.show()
    }

    override fun hideScreen(block: Block) {
        stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {

            game.musicUtil.apply { music = main.apply {
                isLooping = true
                coff      = 0.15f
            } }

            game.recordUtil.update(fruitRecordFlow.value)
            block.invoke()
        }
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        runGDX {
            addBack()
            addFruitPanel()

            createBG_Borders()
            createB_ItemSensor()

            if (game.isTutorialsUtil.isTutorials) addTutorials() else createB_ItemList()
        }

        coroutine?.launch {
            fruitPanel.animShowSuspend(TIME_ANIM_SCREEN_ALPHA)
            fruitRecordLbl.animShowSuspend(TIME_ANIM_SCREEN_ALPHA)
            backBtn.animShowSuspend(TIME_ANIM_SCREEN_ALPHA)
            if (game.isTutorialsUtil.isTutorials) tutorials.animShowSuspend(TIME_ANIM_SCREEN_ALPHA)
        }
    }

    // Actor ------------------------------------------------------------------------

    private fun AdvancedStage.addTutorials() {
        addAndFillActor(tutorials)

        if (game.isTutorialsUtil.isTutorials) {
            when (ATutorials.STEP) {
                ATutorials.Static.Step.Game -> {
                    stageUI.root.children.onEach { it.disable() }
                    tutorials.enable()
                    tutorials.gameStartGameBlock = {
                        game.isTutorialsUtil.update(false)
                        createB_ItemList()
                        tutorials.animHide(TIME_ANIM_SCREEN_ALPHA) {
                            tutorials.addAction(Actions.removeActor())
                            stageUI.root.children.onEach { it.enable() }
                        }
                    }
                }
                else -> {}
            }
        }
    }

    private fun AdvancedStage.addBack() {
        addActor(backBtn)
        backBtn.setBounds(1481f, 825f, 106f, 106f)
        backBtn.setOnClickListener { hideScreen { game.navigationManager.back() } }
    }

    private fun AdvancedStage.addFruitPanel() {
        addActor(fruitPanel)
        fruitPanel.setBounds(477f, 740f, 651f, 206f)

        addActor(fruitRecordLbl)
        fruitRecordLbl.apply {
            setBounds(668f, 799f, 445f, 67f)
            setAlignment(Align.center)
        }

        collectFruitRecord()
    }

    // Body ------------------------------------------------------------------------

    private fun createB_ItemList() {
        bItemList.onEach { bItem ->
            flowBItem.tryEmit(bItem)
            bItem.boomBlock = {
                fruitRecordFlow.value += (10..50).random()
            }
        }

        collectItemToStart()
        collectItemLaunch()

        var joinCounter = 0
        worldUtil.contactListener.beginContactBlockArray.add(WorldContactListener.ContactBlock { bodyA, bodyB ->
            if (bodyA is BItem && bodyB is BItem) {
                if (bodyA.id == bodyB.id) {

                    when {
                        bodyA.joinGroup == -1 && bodyB.joinGroup == -1 -> {
                            bodyA.joinGroup = joinCounter
                            bodyB.joinGroup = joinCounter
                            createJ_Group(bodyA, bodyB)
                        }
                        bodyA.joinGroup != -1 && bodyB.joinGroup != -1 -> {
                            createJ_Group(bodyA, bodyB)
                            boomPair(bodyA.joinGroup, bodyB.joinGroup)
                        }
                        bodyA.joinGroup != -1 -> {
                            bodyB.joinGroup = bodyA.joinGroup
                            createJ_Group(bodyA, bodyB)
                            checkBItemByJoinGroupAndBoom(bodyA.joinGroup)
                        }

                        bodyB.joinGroup != -1 -> {
                            bodyA.joinGroup = bodyB.joinGroup
                            createJ_Group(bodyB, bodyA)
                            checkBItemByJoinGroupAndBoom(bodyB.joinGroup)
                        }
                    }
                    joinCounter++
                }
            }
        })
    }

    private fun createB_ItemSensor() {
        bItemSensor.create(0f, 733f, 1605f, 40f)
    }

    // Body Group ------------------------------------------------------------------------

    private fun createBG_Borders() {
        bgBorders.create(0f, 0f, WIDTH_UI, HEIGHT_UI)
    }

    // Logic ------------------------------------------------------------------------

    private fun collectItemToStart() {
        coroutine?.launch {
            var itemSize: Float

            flowBItem.collect { bItem ->
                runGDX {
                    itemSize = randomItemSize
                    bItem.apply {
                        update()
                        bodyDef.awake = false
                        addEffect()

                        create(
                            randomItemX + bItem.center.x.toUI,
                            HEIGHT_UI + 200f,
                            itemSize, itemSize
                        )

                        bItem.beginContactBlockArray.add(AbstractBody.ContactBlock { enemy, contact ->
                            when(enemy.id) {
                                BodyId.ITEM_SENSOR -> bItem.setOriginalId()
                            }
                        })
                    }
                }
            }
        }
    }

    private fun collectItemLaunch() {
        coroutine?.launch {
            flowBItem.collect { bItem ->
                delay((200..500L).random())

                bItem.isStart.set(true)

                runGDX {
                    bItem.body?.apply {
                        gravityScale = (20..40).random() / 10f
                        isAwake      = true
                    }
                }

            }
        }
    }

    private fun collectFruitRecord() {
        coroutine?.launch {
            fruitRecordFlow.collect { record ->
                runGDX {
                    fruitRecordLbl.setText(record)
                }
            }
        }
    }

    private fun checkBItemByJoinGroupAndBoom(joinGroup: Int) {
        if (bItemList.count { it.joinGroup == joinGroup } >= 3) {
            bItemList.filter { it.joinGroup == joinGroup }.onEach {
                it.boom()
                flowBItem.tryEmit(it)
            }
        }
    }

    private fun boomPair(joinGroupA: Int, joinGroupB: Int) {
        bItemList.filter { it.joinGroup == joinGroupA || it.joinGroup == joinGroupB }.onEach {
            it.boom()
            flowBItem.tryEmit(it)
        }
    }

    private fun createJ_Group(body1: AbstractBody, body2: AbstractBody) {
        runGDX {
            AbstractJoint<WeldJoint, WeldJointDef>(this).create(WeldJointDef().apply {
                bodyA = body1.body
                bodyB = body2.body

                localAnchorA.set(body1.center)
                localAnchorB.set(body2.center.cpy().scl(-1f))

            })
        }
    }

}