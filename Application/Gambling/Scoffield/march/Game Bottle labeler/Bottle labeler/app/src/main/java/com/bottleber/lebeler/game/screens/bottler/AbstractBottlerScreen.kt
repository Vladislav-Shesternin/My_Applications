package com.bottleber.lebeler.game.screens.bottler

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.QueryCallback
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ParticleEffectActor
import com.bottleber.lebeler.game.LibGDXGame
import com.bottleber.lebeler.game.box2d.AbstractBody
import com.bottleber.lebeler.game.box2d.BodyId
import com.bottleber.lebeler.game.box2d.WorldContactListener
import com.bottleber.lebeler.game.box2d.WorldUtil
import com.bottleber.lebeler.game.box2d.bodies.BBottle
import com.bottleber.lebeler.game.box2d.bodies.BPricel
import com.bottleber.lebeler.game.box2d.bodiesGroup.BGBorders
import com.bottleber.lebeler.game.box2d.bodiesGroup.BGBoxBorders
import com.bottleber.lebeler.game.utils.TIME_ANIM
import com.bottleber.lebeler.game.utils.actor.animHide
import com.bottleber.lebeler.game.utils.actor.animShow
import com.bottleber.lebeler.game.utils.actor.setOnClickListener
import com.bottleber.lebeler.game.utils.actor.setPosition
import com.bottleber.lebeler.game.utils.advanced.AdvancedBox2dScreen
import com.bottleber.lebeler.game.utils.advanced.AdvancedStage
import com.bottleber.lebeler.game.utils.font.FontParameter
import com.bottleber.lebeler.game.utils.region
import com.bottleber.lebeler.game.utils.runGDX
import com.bottleber.lebeler.game.utils.toUI
import com.bottleber.lebeler.util.log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Random

abstract class AbstractBottlerScreen(final override val game: LibGDXGame, BOTTLE_COUNT: Int): AdvancedBox2dScreen(WorldUtil()) {

    private val params = FontParameter().setCharacters(FontParameter.CharType.NUMBERS).setSize(71)
    private val font   = fontGenerator_InriaSans.generateFont(params)

    // Actor
    private val menu  = Image(game.allAssets.shot_menu)
    private val label = Label("", Label.LabelStyle(font, Color.valueOf("F6FFBE")))

    private val particleEffectActor = ParticleEffectActor(game.particleEffectUtil.BOTTLE,true)

    // BodyGroup
    private val bgBorders    by lazy { BGBorders(this) }
    private val bgBoxBorders by lazy { BGBoxBorders(this) }

    // Body
    private val bPricel by lazy { BPricel(this) }

    // Field
    private val countFlow = MutableStateFlow(BOTTLE_COUNT)

    private val impulseList = listOf(-1.5f, -1f, 1f, 1.5f)
    private val random      = Random()
    private val tmpVector   = Vector2()
    private val getImpulse get() = tmpVector.set(impulseList.random(), if (random.nextBoolean()) -random.nextFloat() else random.nextFloat())

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.loaderAssets.fonit.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM) {
            bPricel.body?.apply { applyLinearImpulse(getImpulse, worldCenter, true) }
        }
    }

    final override fun AdvancedStage.addActorsOnStageUI() {
        addMenu()
        addLabel()
        addBtns()

        createBG_Borders()
        createBG_BoxBorders()

        addActorsOnStage()

        addParticleEffectActor()
        createB_Pricel()
    }

    abstract fun AdvancedStage.addActorsOnStage()

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedStage.addMenu() {
        addActor(menu)
        menu.setBounds(417f, 0f, 1048f, 310f)
    }

    private fun AdvancedStage.addLabel() {
        addActor(label)
        label.setBounds(568f, 65f, 81f, 85f)

        coroutine?.launch {
            countFlow.collect { count ->
                runGDX {
                    label.setText(count)
                    if (count <= 0) {
                        menu.addAction(Actions.sequence(
                            Actions.delay(1f),
                            Actions.run { stageUI.root.animHide(TIME_ANIM) { game.navigationManager.back() } }
                        ))
                    }
                }
            }
        }
    }

    private fun AdvancedStage.addBtns() {
        val menuA = Actor()
        addActor(menuA)
        menuA.apply {
            setBounds(1343f, 88f, 122f, 42f)
            setOnClickListener(game.soundUtil) { stageUI.root.animHide(TIME_ANIM) { game.navigationManager.back() } }
        }

        val shotA = Actor()
        addActor(shotA)
        shotA.apply {
            setBounds(810f, 49f, 302f, 121f)
            setOnClickListener(game.soundUtil) { handlerPricel() }
        }
    }

    private fun AdvancedStage.addParticleEffectActor() {
        addActor(particleEffectActor)
    }

    // ---------------------------------------------------
    // create Body Group
    // ---------------------------------------------------

    private fun createBG_Borders() {
        bgBorders.create(0f, 0f, WIDTH, HEIGHT)

        val contactBlock = AbstractBody.ContactBlock {
            if (it.id == BodyId.BOTTLE) {
                runGDX { it.destroy() }
                countFlow.value--
            }
        }

        bgBorders.bodyList.onEach { it.beginContactBlockArray.add(contactBlock) }
    }

    private fun createBG_BoxBorders() {
        bgBoxBorders.create(163f, 289f, 1593f, 763f)
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Pricel() {
        bPricel.create(389f, 730f, 92f, 92f)

        bPricel.beginContactBlockArray.add(AbstractBody.ContactBlock {
            when(it.id) {
                BodyId.PRICEL_BOX, BodyId.PRICEL_BOX_DOWN -> {
                    bPricel.body?.apply {
                        setLinearVelocity(0f,0f)
                        applyLinearImpulse(getImpulse, worldCenter, true)
                    }
                }
            }
        })
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun handlerPricel() {
        bPricel.body?.setLinearVelocity(0f, 0f)

        bPricel.body?.let { b ->
            worldUtil.world.QueryAABB(getPricelCallback(),
                b.position.x - 0.01f,
                b.position.y - 0.01f,
                b.position.x + 0.01f,
                b.position.y + 0.01f
            )
        }

    }

    private fun getPricelCallback() = QueryCallback { fixture ->
        if (
            fixture.testPoint(bPricel.body?.position) &&
            (fixture.body?.userData as AbstractBody).id == BodyId.BOTTLE
        ) {
            log("BOTTLE")

            menu.apply {
                clearActions()
                addAction(Actions.sequence(
                    Actions.run {
                        game.soundUtil.apply { play(GLASS, 50f) }

                        (fixture.body?.userData as BBottle).destroy()
                        particleEffectActor.setPosition(bPricel.body!!.position.toUI)
                        particleEffectActor.start()

                        countFlow.value--
                    },
                    Actions.delay(0.5f),
                    Actions.run { bPricel.body?.apply { applyLinearImpulse(getImpulse, worldCenter, true) } }
                ))
            }
            return@QueryCallback false
        } else {
            log("MIMO")

            menu.apply {
                clearActions()
                addAction(Actions.sequence(
                    Actions.delay(0.5f),
                    Actions.run { bPricel.body?.apply { applyLinearImpulse(getImpulse, worldCenter, true) } }
                ))
            }
        }
        return@QueryCallback true
    }

}