package com.amayasoft.cars.kids.toddlers.garage.ga.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.amayasoft.cars.kids.toddlers.garage.ga.game.LibGDXGame
import com.amayasoft.cars.kids.toddlers.garage.ga.game.actors.AButton
import com.amayasoft.cars.kids.toddlers.garage.ga.game.box2d.AbstractBody
import com.amayasoft.cars.kids.toddlers.garage.ga.game.box2d.AbstractJoint
import com.amayasoft.cars.kids.toddlers.garage.ga.game.box2d.BodyId
import com.amayasoft.cars.kids.toddlers.garage.ga.game.box2d.bodies.BCircle
import com.amayasoft.cars.kids.toddlers.garage.ga.game.box2d.bodies.BItem
import com.amayasoft.cars.kids.toddlers.garage.ga.game.box2d.bodies.BStatic
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.*
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.actor.animHide
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.actor.animShow
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.advanced.AdvancedStage
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.advanced.AdvancedUserScreen
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.font.FontParameter
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class GameScreen(override val game: LibGDXGame) : AdvancedUserScreen() {

    companion object {
        var fruitses = 0
    }

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars).setSize(43)
    private val font          = fntBold.generateFont(fontParameter)

    // Actor
    private val aBack      = AButton(this, AButton.Static.Type.Back)
    private val aRecordLbl = Label(fruitses.toProbelchiky(), Label.LabelStyle(font, Color.WHITE))

    // Body
    private val bItemList = List(40) { BItem(this) }
    private val bStatic   = BStatic(this)
    private val bCircle   = BCircle(this)

    // Joint
    private val jPrisma = AbstractJoint<PrismaticJoint, PrismaticJointDef>(this)

    // Fluid
    private val itemFlow = MutableSharedFlow<BItem>(40)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.S3.Splash.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createB_Static()
        addWSE()

        createB_Item()
        createB_Circle()

        createJ_P()
    }

    // Add
    private fun AdvancedStage.addWSE() {
        addActor(aBack)
        aBack.apply {
            setBounds(673f, 365f, 78f, 78f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

        val pnlImg = Image(game.G1.pinrel)
        addActor(pnlImg)
        pnlImg.setBounds(204f, 366f, 355f, 78f)

        addActor(aRecordLbl)
        aRecordLbl.apply {
            setBounds(320f, 379f, 195f, 52f)
            setAlignment(Align.center)
        }

        val fruits = Image(game.G1.XCBVVBN)
        addActor(fruits)
        fruits.apply {
            color.a = 0.6f
            setBounds(-3f, -42f, 769f, 230f)
            addAction(
                Actions.forever(
                    Actions.sequence(
                Actions.moveBy(0f, 39f, TIME_ANIM, Interpolation.sine),
                Actions.moveBy(0f, -39f, TIME_ANIM, Interpolation.sine),
            )))
        }
    }

    // ------------------------------------------------------------------------
    // Create Body
    // ------------------------------------------------------------------------

    private fun createB_Item() {
        bItemList.onEach { bItem ->
            bItem.apply {
                id = BodyId.ITEM
                collisionList.addAll(arrayOf(BodyId.RULT, BodyId.ITEM))
            }
            bItem.create(0f, HEIGHT_UI+135f, 70f, 70f)

            var timer = 0f
            bItem.renderBlockArray.add(AbstractBody.RenderBlock {
                timer += it
                if (timer >= 1) {
                    timer = 0f
                    if ((bItem.body?.position?.y ?: 0f) < 0f) {
                        if (bItem.isOnStart.getAndSet(false)) {
                            itemFlow.tryEmit(bItem)
                        }
                    }
                }
            })

            itemFlow.tryEmit(bItem)
        }

        val startPos = Vector2()

        coroutine?.launch {
            itemFlow.collect { bItem ->
                bItem.body?.apply {
                    setLinearVelocity(0f, 0f)
                    isAwake = false
                    gravityScale = 0f

                    runGDX {
                        setTransform(startPos.set((0..500).random().toFloat(), HEIGHT_UI+135f).toB2, 0f)
                        bItem.isOnStart.set(true)
                    }
                }
            }
        }
        coroutine?.launch {
            itemFlow.collect { bItem ->
                delay((80..200L).random())

                bItem.body?.apply {
                    gravityScale = 1f
                    isAwake = true
                    applyLinearImpulse(Vector2((7..10).random().toFloat(), 0f), worldCenter, true)
                }
            }
        }

    }

    private fun createB_Static() {
        bStatic.create(-13f, 2f, 143f, 143f)
    }

    private fun createB_Circle() {
        bCircle.apply {
            id = BodyId.RULT
            collisionList.addAll(arrayOf(BodyId.ITEM))

            beginContactBlockArray.add(AbstractBody.ContactBlock {
                if (it.id == BodyId.ITEM) {
                    game.soundUtil.apply { play(collect, 65f) }

                    fruitses += (1000..7000).random()
                    aRecordLbl.setText(fruitses.toProbelchiky())
                    itemFlow.tryEmit(it as BItem)
                }
            })
        }
        bCircle.create(320f, 9f, 123f, 123f)
    }

    // Joints ------------------------------------------------------------------------

    private fun createJ_P() {
        jPrisma.create(PrismaticJointDef().apply {
            bodyA = bStatic.body
            bodyB = bCircle.body

            enableLimit = true
            lowerTranslation = 0f
            upperTranslation = (WIDTH_UI-80f).toB2
        })
    }

}