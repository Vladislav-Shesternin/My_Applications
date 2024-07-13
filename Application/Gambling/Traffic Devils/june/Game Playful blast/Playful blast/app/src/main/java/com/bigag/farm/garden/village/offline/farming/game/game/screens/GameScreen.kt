package com.bigag.farm.garden.village.offline.farming.game.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.WeldJoint
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.bigag.farm.garden.village.offline.farming.game.game.LibGDXGame
import com.bigag.farm.garden.village.offline.farming.game.game.actors.AButton
import com.bigag.farm.garden.village.offline.farming.game.game.box2d.AbstractBody
import com.bigag.farm.garden.village.offline.farming.game.game.box2d.AbstractJoint
import com.bigag.farm.garden.village.offline.farming.game.game.box2d.BodyId
import com.bigag.farm.garden.village.offline.farming.game.game.box2d.bodies.BItem
import com.bigag.farm.garden.village.offline.farming.game.game.box2d.bodies.BStatic
import com.bigag.farm.garden.village.offline.farming.game.game.box2d.bodies.BKoleso
import com.bigag.farm.garden.village.offline.farming.game.game.utils.*
import com.bigag.farm.garden.village.offline.farming.game.game.utils.actor.animHide
import com.bigag.farm.garden.village.offline.farming.game.game.utils.actor.animShow
import com.bigag.farm.garden.village.offline.farming.game.game.utils.actor.setOnClickListener
import com.bigag.farm.garden.village.offline.farming.game.game.utils.advanced.AdvancedStage
import com.bigag.farm.garden.village.offline.farming.game.game.utils.advanced.AdvancedUserScreen
import com.bigag.farm.garden.village.offline.farming.game.game.utils.font.FontParameter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameScreen(override val game: LibGDXGame) : AdvancedUserScreen() {

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS).setSize(53)
    private val font          = fontGenerator_LoveYaLikeASister.generateFont(fontParameter)

    // Actor
    private val resultPanelImg = Image(game.assets.rude)
    private val aRecordLbl     = Label("0", Label.LabelStyle(font, Color.WHITE))
    private val aBack          = AButton(this, AButton.Static.Type.Back)

    // Body
    private val bItemList = List(10) { BItem(this) }
    private val bStatic   = BStatic(this)
    private val bKolo     = BKoleso(this)


    // Joint
    private val jWeld = AbstractJoint<WeldJoint, WeldJointDef>(this)

    // Fluid
    private val itemFlow = MutableSharedFlow<BItem>(10)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.splash.Splash.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createB_Item()

        createB_Static()
        createB_Kolo()

        createJ_Sobaka()

        addAAA()
    }

    // Add
    private fun AdvancedStage.addAAA() {
        addActor(resultPanelImg)
        resultPanelImg.setBounds(235f, 152f, 593f, 101f)

        addActor(aBack)
        aBack.apply {
            setBounds(338f, 24f, 386f, 95f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

        addActor(aRecordLbl)
        aRecordLbl.apply {
            setBounds(334f, 169f, 394f, 66f)
            setAlignment(Align.center)
        }
    }

    // ------------------------------------------------------------------------
    // Create Body
    // ------------------------------------------------------------------------

    var counter = 0L
    val listok  = listOf(10_000, 50_000, 100_000)
    val losiaa  = listOf(10_000, 15_000, 20_000)
    val pep get() = listok.random()
    val beb get() = losiaa.random()

    private fun createB_Item() {
        bItemList.onEach { bItem ->
            bItem.id = BodyId.ITEM
            bItem.create(0f, HEIGHT + 500, 250f, 250f)

            bItem.actor?.setOnClickListener {
                if (bItem.isOnStart.getAndSet(false)) {
                    game.soundUtil.apply { play(bonus, 100f) }

                    counter += pep
                    aRecordLbl.setText(counter.toString())
                    itemFlow.tryEmit(bItem)
                }
            }

            var timer = 0f
            bItem.renderBlockArray.add(AbstractBody.RenderBlock {
                timer += it
                if (timer >= 1) {
                    timer = 0f
                    if ((bItem.body?.position?.y ?: 0f) <= 0f) {
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
                        setTransform(startPos.set(0f, HEIGHT + 500).toB2, 0f)
                        bItem.isOnStart.set(true)
                    }
                }
            }
        }
        coroutine?.launch {
            itemFlow.collect { bItem ->
                delay((300..780L).random())

                runGDX {
                    bItem.body?.apply {
                        setTransform(bKolo.body?.position, 0f)
                        gravityScale = 1f
                        isAwake = true
                        applyLinearImpulse(Vector2( if(Random.nextBoolean()) -1 * (1000..5000).random().toFloat() else (1000..5000).random().toFloat(), beb.toFloat()), worldCenter, true)
                    }
                }
            }
        }

    }

    private fun createB_Static() {
        bStatic.create(425f, 1291f, 213f, 213f)
    }

    private fun createB_Kolo() {
        bKolo.id = BodyId.SOBAKA
        bKolo.create(298f, 1157f, 466f, 478f)
    }

    // Joints ------------------------------------------------------------------------

    private fun createJ_Sobaka() {
        jWeld.create(WeldJointDef().apply {
            bodyA = bStatic.body
            bodyB = bKolo.body
        })
    }

}