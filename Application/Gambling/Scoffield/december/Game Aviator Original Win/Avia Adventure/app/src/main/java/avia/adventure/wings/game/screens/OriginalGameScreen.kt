package avia.adventure.wings.game.screens

import avia.adventure.wings.game.LibGDXGame
import avia.adventure.wings.game.actors.AResultGroup
import avia.adventure.wings.game.actors.ATimerGroup
import avia.adventure.wings.game.actors.button.AButton
import avia.adventure.wings.game.actors.checkbox.ACheckBox
import avia.adventure.wings.game.box2d.AbstractBody
import avia.adventure.wings.game.box2d.AbstractJoint
import avia.adventure.wings.game.box2d.BodyId
import avia.adventure.wings.game.box2d.bodies.BAvia
import avia.adventure.wings.game.box2d.bodies.BCoin
import avia.adventure.wings.game.box2d.bodies.BEnemy
import avia.adventure.wings.game.box2d.bodies.standart.BStatic
import avia.adventure.wings.game.utils.TIME_ANIM_SCREEN_ALPHA
import avia.adventure.wings.game.utils.actor.animHide
import avia.adventure.wings.game.utils.actor.animShow
import avia.adventure.wings.game.utils.actor.setOnClickListener
import avia.adventure.wings.game.utils.advanced.AdvancedMouseScreen
import avia.adventure.wings.game.utils.advanced.AdvancedStage
import avia.adventure.wings.game.utils.font.FontParameter
import avia.adventure.wings.game.utils.region
import avia.adventure.wings.game.utils.runGDX
import avia.adventure.wings.game.utils.toB2
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class OriginalGameScreen(override val game: LibGDXGame) : AdvancedMouseScreen(game) {

    private val assets = game.gameAssets

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS).setSize(21)
    private val font          = fontGenerator_Bold.generateFont(fontParameter)

    // Actor
    private val aTimer   = ATimerGroup(this)
    private val aCoinLbl = Label("0", Label.LabelStyle(font, Color.WHITE))
    private val aResult  = AResultGroup(this).apply { color.a = 0f }
    private val aHarts   = List(3) { Image(assets.hart) }

    // Body
    private val bStatic = BStatic(this)
    private val bAvia   = BAvia(this)

    // Joint
    private val jPrismatic = AbstractJoint<PrismaticJoint, PrismaticJointDef>(this)

    // Field
    private var coinCount = 0
    private var hartCount = 0

    private val itemFlow = MutableSharedFlow<BCoin>(replay = 15)
    private val bombFlow = MutableSharedFlow<BEnemy>(replay = 5)


    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.gameAssets.OriginalGame.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createB_Static()
        createB_Avia()
        createJ_Prismatic()
        createB_Items()
        createB_Bomb()

        addBack()
        addPause()
        addCoinLbl()
        addTimer()
        addHarts()

        addAndFillActor(aResult)

        aTimer.startTimer {
            aTimer.isPause = true
            isWorldPause   = true

            aResult.update(true)
            aResult.animShow(TIME_ANIM_SCREEN_ALPHA)
            aResult.touchable = Touchable.enabled
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addBack() {
        val menu = AButton(this@OriginalGameScreen, AButton.Static.Type.MENU)
        addActor(menu)
        menu.setBounds(22f, 1326f, 60f, 60f)

        menu.setOnClickListener(game.soundUtil) {
            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }
        }
    }

    private fun AdvancedStage.addPause() {
        val pause = ACheckBox(this@OriginalGameScreen, ACheckBox.Static.Type.PAUSE)
        addActor(pause)
        pause.setBounds(566f, 1326f, 60f, 60f)

        pause.setOnCheckListener { isCheck ->
            aTimer.isPause = isCheck
            isWorldPause   = isCheck
        }
    }

    private fun AdvancedStage.addTimer() {
        addActor(aTimer)
        aTimer.setBounds(393f, 1345f, 123f, 18f)
    }

    private fun AdvancedStage.addCoinLbl() {
        addActor(aCoinLbl)
        aCoinLbl.apply {
            setBounds(193f, 1345f, 123f, 18f)
            setAlignment(Align.center)
        }
    }

    private fun AdvancedStage.addHarts() {
        var nx = 445f
        aHarts.onEach { hart ->
            addActor(hart)
            hart.setBounds(nx, 8f, 51f, 51f)
            nx += 11f + 51f
        }
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Static() {
        bStatic.create(-300f, 0f, 253f, 253f)
    }

    private fun createB_Avia() {
        bAvia.create(198f, 0f, 253f, 253f)

        bAvia.beginContactBlockArray.add(AbstractBody.ContactBlock { b2 ->
            when (b2.id) {
                BodyId.Game.COIN -> {
                    b2 as BCoin
                    if (b2.atomicBoolean.getAndSet(false)) {
                        itemFlow.tryEmit(b2)
                        game.soundUtil.apply { play(caught) }

                        coinCount += (10..20).random()
                        aCoinLbl.setText(coinCount)
                    }
                }
                BodyId.Game.ENEMY -> {
                    b2 as BEnemy
                    if (b2.atomicBoolean.getAndSet(false)) {
                        bombFlow.tryEmit(b2)
                        game.soundUtil.apply { play(bomb) }

                        aHarts[hartCount].remove()
                        hartCount++
                        if (hartCount == 3) {
                            aTimer.isPause = true
                            isWorldPause   = true

                            aResult.update(false)
                            aResult.animShow(TIME_ANIM_SCREEN_ALPHA)
                            aResult.touchable = Touchable.enabled
                        }

                        bAvia.body?.applyLinearImpulse(Vector2(listOf(-20000, 20000).random().toFloat(), 0f), bAvia.body?.worldCenter, true)
                    }
                }
            }
        })
    }

    private fun createB_Items() {
        repeat(15) {
            BCoin(this).also { bItem ->
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

                val size = (50..100).random().toFloat()
                bItem.create(-300f, 0f, size, size)

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
                    bItem.body?.apply {
                        setTransform((50..600).random().toFloat().toB2, 1405f.toB2, 0f)

                        gravityScale = 1f
                        isAwake      = true
                    }
                }
                delay(100)
                runGDX {
                    bItem.id = BodyId.Game.COIN
                    bItem.atomicBoolean.set(true)
                }
            }
        }
    }

    private fun createB_Bomb() {
        repeat(1) {
            BEnemy(this).also { bBomb ->
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
                val size = (100..250).random().toFloat()
                bBomb.create(-400f, 0f, size, size)

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

                        setTransform(Vector2(-400f, 0f).toB2, 0f)
                    }
                }
            }
        }
        coroutine?.launch {
            bombFlow.collect { bBomb ->
                delay((1000L..3000L).random())
                runGDX {
                    bBomb.body?.apply {
                        setTransform((100..500).random().toFloat().toB2, 1405f.toB2, 0f)

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
            bodyB = bAvia.body

            lowerTranslation = 185f.toB2
            upperTranslation = (725f).toB2
            enableLimit      = true
        })
    }

}