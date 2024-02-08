package jogos.tigerfortune.tighrino.game.screens

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Image
import jogos.tigerfortune.tighrino.game.LibGDXGame
import jogos.tigerfortune.tighrino.game.actors.AItemPanel
import jogos.tigerfortune.tighrino.game.actors.AResultGroup
import jogos.tigerfortune.tighrino.game.actors.ATimerGroup
import jogos.tigerfortune.tighrino.game.actors.button.AButton
import jogos.tigerfortune.tighrino.game.actors.checkbox.ACheckBox
import jogos.tigerfortune.tighrino.game.box2d.AbstractBody
import jogos.tigerfortune.tighrino.game.box2d.AbstractJoint
import jogos.tigerfortune.tighrino.game.box2d.BodyId
import jogos.tigerfortune.tighrino.game.box2d.bodies.BItem
import jogos.tigerfortune.tighrino.game.box2d.bodies.BItemBomb
import jogos.tigerfortune.tighrino.game.box2d.bodies.BTiger
import jogos.tigerfortune.tighrino.game.box2d.bodies.standart.BStatic
import jogos.tigerfortune.tighrino.game.utils.TIME_ANIM_SCREEN_ALPHA
import jogos.tigerfortune.tighrino.game.utils.actor.animHide
import jogos.tigerfortune.tighrino.game.utils.actor.animShow
import jogos.tigerfortune.tighrino.game.utils.actor.setOnClickListener
import jogos.tigerfortune.tighrino.game.utils.advanced.AdvancedMouseScreen
import jogos.tigerfortune.tighrino.game.utils.advanced.AdvancedStage
import jogos.tigerfortune.tighrino.game.utils.region
import jogos.tigerfortune.tighrino.game.utils.runGDX
import jogos.tigerfortune.tighrino.game.utils.toB2
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class TGameScreen(override val game: LibGDXGame) : AdvancedMouseScreen(game) {

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
    private val randomIndexList = (0..4).shuffled().take(3)
    private val randomCountList = List<Int>(3) { (3..4).random() }
    private val tigerWidth      = getTigerWidthByType()

    private val itemFlow = MutableSharedFlow<BItem>(replay = 15)
    private val bombFlow = MutableSharedFlow<BItemBomb>(replay = 1)


    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(getBackgroundByType().region)
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
        addItemPanelList()
        addTimer()

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
        val menu = AButton(this@TGameScreen, AButton.Static.Type.MENU)
        addActor(menu)
        menu.setBounds(44f, 1754f, 114f, 119f)

        menu.setOnClickListener(game.soundUtil) {
            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }
        }
    }

    private fun AdvancedStage.addPause() {
        val pause = ACheckBox(this@TGameScreen, ACheckBox.Static.Type.PAUSE)
        addActor(pause)
        pause.setBounds(922f, 1754f, 114f, 119f)

        pause.setOnCheckListener { isCheck ->
            aTimer.isPause = isCheck
            isWorldPause   = isCheck
        }
    }

    private fun AdvancedStage.addTimer() {
        addActor(aTimer)
        aTimer.setBounds(527f, 1801f, 125f, 55f)
    }

    private fun AdvancedStage.addItemPanelList() {
        val panel = Image(game.gameAssets.panel)
        addActor(panel)
        panel.setBounds(191f, 1661f, 698f, 227f)

        var nx = 200f

        aItemPanelList.onEachIndexed { index, aItemPanel ->
            addActor(aItemPanel)
            aItemPanel.setBounds(nx, 1670f, 153f, 57f)
            nx += 97f + 153f

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
        when(TChooseScreen.TIGER_TYPE) {
            TChooseScreen.TigerType.TIGER_1 -> bTiger.create(333f, 40f, tigerWidth, 583f)
            TChooseScreen.TigerType.TIGER_2 -> bTiger.create(248f, 40f, tigerWidth, 628f)
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
                bItem.create(-300f, 10f, 160f, 160f)

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

                        setTransform(Vector2(-300f, 10f).toB2, 0f)
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

                        setTransform(Vector2(-300f, 10f).toB2, 0f)
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

    private fun getBackgroundByType() = when(TChooseScreen.BACKGROUND_TYPE) {
        TChooseScreen.BackgroundType.B1 -> assets.BG1
        TChooseScreen.BackgroundType.B2 -> assets.BG2
        TChooseScreen.BackgroundType.B3 -> assets.BG3
        TChooseScreen.BackgroundType.B4 -> assets.BG4
    }

    private fun getTigerWidthByType() = when (TChooseScreen.TIGER_TYPE) {
        TChooseScreen.TigerType.TIGER_1 -> 413f
        TChooseScreen.TigerType.TIGER_2 -> 584f
    }

    private fun checkAllItemPanel() {
        if (aItemPanelList.all { it.counter == 0 }) {
            aTimer.isPause = true
            isWorldPause   = true

            aResult.update(true, aTimer.getTime)
            aResult.animShow(TIME_ANIM_SCREEN_ALPHA)
            aResult.touchable = Touchable.enabled
        }
    }

}