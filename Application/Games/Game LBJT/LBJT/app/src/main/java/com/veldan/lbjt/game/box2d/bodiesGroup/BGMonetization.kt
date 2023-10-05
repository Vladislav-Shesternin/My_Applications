package com.veldan.lbjt.game.box2d.bodiesGroup

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.MotorJoint
import com.badlogic.gdx.physics.box2d.joints.MotorJointDef
import com.veldan.lbjt.game.actors.AIndicator
import com.veldan.lbjt.game.actors.button.AButton_Monetization
import com.veldan.lbjt.game.box2d.AbstractBody
import com.veldan.lbjt.game.box2d.AbstractBodyGroup
import com.veldan.lbjt.game.box2d.AbstractJoint
import com.veldan.lbjt.game.box2d.BodyId
import com.veldan.lbjt.game.box2d.BodyId.AboutAuthor.ITEM
import com.veldan.lbjt.game.box2d.bodies.BMonetizationBtn
import com.veldan.lbjt.game.box2d.bodies.BMonetizationMegaBtn
import com.veldan.lbjt.game.box2d.bodies.BStatic

import com.veldan.lbjt.game.utils.GameColor
import com.veldan.lbjt.game.utils.JOINT_WIDTH
import com.veldan.lbjt.game.utils.actor.disable
import com.veldan.lbjt.game.utils.actor.enable
import com.veldan.lbjt.game.utils.actor.setOnTouchListener
import com.veldan.lbjt.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.lbjt.game.utils.advanced.AdvancedGroup
import com.veldan.lbjt.game.utils.advanced.AdvancedStage
import com.veldan.lbjt.game.utils.toUI
import com.veldan.lbjt.util.BillingUtil

class BGMonetization(override val screenBox2d: AdvancedBox2dScreen): AbstractBodyGroup() {

    override val standartW = 622f

    // Actors
    private val aIndicator = AIndicator(screenBox2d)

    // Body
    private val bStatic              = BStatic(screenBox2d)
    private val bMonetizationBtnAds  = BMonetizationBtn(screenBox2d, AButton_Monetization.Type.ADS)
    private val bMonetizationBtnGift = BMonetizationBtn(screenBox2d, AButton_Monetization.Type.GIFT)
    private val bMonetizationBtnMega = BMonetizationMegaBtn(screenBox2d)

    // Joint
    private val jMotorAds  = AbstractJoint<MotorJoint, MotorJointDef>(screenBox2d)
    private val jMotorGift = AbstractJoint<MotorJoint, MotorJointDef>(screenBox2d)
    private val jMotorMega = AbstractJoint<MotorJoint, MotorJointDef>(screenBox2d)

    // Field
    private val drawer      = screenBox2d.drawerUtil.drawer

    override fun create(x: Float, y: Float, w: Float, h: Float) {
        super.create(x, y, w, h)

        initB_Monetization()

        createB_Static()
        createB_Monetization()

        createJ_MotorAds()

        screenBox2d.stageUI.apply { addIndicator() }
    }

    // ---------------------------------------------------
    // Init
    // ---------------------------------------------------

    private fun initB_Monetization() {
        arrayOf(bMonetizationBtnAds, bMonetizationBtnGift, bMonetizationBtnMega).onEach { it.apply {
            id = ITEM
            collisionList.addAll(arrayOf(BodyId.BORDERS, ITEM))
            bodyDef.fixedRotation = true
        } }

        bMonetizationBtnAds.actor?.setOnTouchListener { handlerTouchMonetizationBtnAds() }
        bMonetizationBtnGift.actor?.setOnTouchListener { handlerTouchMonetizationBtnGift(BillingUtil.Product.GIFT_1_DOLLAR) }
        bMonetizationBtnMega.actor?.setOnTouchListener { handlerTouchMonetizationBtnGift(BillingUtil.Product.GIFT_100_DOLLAR) }
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedStage.addIndicator() {
        addActor(aIndicator)
        aIndicator.setBoundsStandart(281f, 447f, 60f, 60f)
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Static() {
        createBody(bStatic, 303f, 310f, 15f, 15f)
    }

    private fun createB_Monetization() {
        createBody(bMonetizationBtnAds, 0f, 211f, 296f, 296f)
        createBody(bMonetizationBtnGift, 326f, 211f, 296f, 296f)
        createBody(bMonetizationBtnMega, 238f, 178f, 146f, 106f)
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_MotorAds() {
        listOf<MotorJointData>(
            MotorJointData(
                jMotorAds,
                bMonetizationBtnAds,
                offset = Vector2(-155f, 46f),
                anchorA = Vector2(-155f, -310f),
                anchorB = Vector2(147f, 6f)
            ),
            MotorJointData(
                jMotorGift,
                bMonetizationBtnGift,
                offset = Vector2(170f, 46f),
                anchorA = Vector2(170f, -310f),
                anchorB = Vector2(147f, 6f)
            ),
            MotorJointData(
                jMotorMega,
                bMonetizationBtnMega,
                offset = Vector2(7f, -79f),
                anchorA = Vector2(7f, -310f),
                anchorB = Vector2(72f, 6f)
            ),
        ).onEach { data ->
            createJoint(data.joint, MotorJointDef().apply {
                bodyA = bStatic.body
                bodyB = data.bodyB.body

                linearOffset.set(data.offset.subCenter(bodyA))

                maxForce = 200 * bodyB.mass
                correctionFactor = 0.95f

                data.bodyB.actor?.preDrawArray?.add(AdvancedGroup.PreDrawer { alpha ->
                    drawer.line(
                        bodyA.position.cpy().add(data.anchorA.cpy().subCenter(bodyA)).toUI,
                        bodyB.position.cpy().add(data.anchorB.cpy().subCenter(bodyB)).toUI,
                        GameColor.joint.apply { a = alpha }, JOINT_WIDTH
                    )
                })
            })
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun handlerTouchMonetizationBtnAds() {
        screenBox2d.stageUI.root.disable()
        aIndicator.animShowLoader()
//        game.activity.rewardedUtil.apply { loadAndShow(
//            successBlock = {
//                screenBox2d.stageUI.root.enable()
//                aIndicator.animHideLoader()
//            },
//            failedBlock  = this@BGMonetization::failedBlock
//        ) }
    }

    private fun handlerTouchMonetizationBtnGift(product: BillingUtil.Product) {
        screenBox2d.game.activity.billingUtil.also { billing ->
            billing.startConnection {
                billing.getProductDetails(product) { _, productDetailsList ->
                    productDetailsList.first { it.productId == product.id }.also { productDetails -> billing.launchBillingFlow(productDetails) }
                }
            }
        }
    }

    private fun failedBlock() {
        screenBox2d.stageUI.root.enable()
        val btns = listOf(bMonetizationBtnAds, bMonetizationBtnGift, bMonetizationBtnMega)

        btns.onEach { it.actor?.disable() }
        aIndicator.animShowNoWifi { btns.onEach { it.actor?.enable() } }
    }

    private data class MotorJointData(
        val joint  : AbstractJoint<MotorJoint, MotorJointDef>,
        val bodyB  : AbstractBody,
        val offset : Vector2,
        val anchorA: Vector2,
        val anchorB: Vector2,
    )

}