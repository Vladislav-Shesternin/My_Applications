package atest.btest.lbjttest.game.box2d.bodiesGroup.gear

import atest.btest.lbjttest.game.actors.label.ALabel
import atest.btest.lbjttest.game.box2d.AbstractBody
import atest.btest.lbjttest.game.box2d.AbstractBodyGroup
import atest.btest.lbjttest.game.box2d.AbstractJoint
import atest.btest.lbjttest.game.utils.RADTODEG
import atest.btest.lbjttest.game.utils.WIDTH_UI
import atest.btest.lbjttest.game.utils.advanced.AdvancedBox2dScreen
import atest.btest.lbjttest.game.utils.advanced.AdvancedStage
import atest.btest.lbjttest.game.utils.font.FontParameter
import atest.btest.lbjttest.game.utils.toB2
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.physics.box2d.joints.GearJoint
import com.badlogic.gdx.physics.box2d.joints.GearJointDef
import com.badlogic.gdx.scenes.scene2d.ui.Label

class BGGearJoint(override val screenBox2d: AdvancedBox2dScreen): AbstractBodyGroup() {

    override val standartW = WIDTH_UI

    private val fontParameter1 = FontParameter().setCharacters(FontParameter.CharType.ALL).setSize(30)
    private val fontParameter2 = FontParameter().setCharacters(FontParameter.CharType.ALL).setSize(45)

    private val font1 = screenBox2d.fontGeneratorInter_Regular.generateFont(fontParameter1)
    private val font2 = screenBox2d.fontGeneratorInter_Bold.generateFont(fontParameter2)

    // Actors
    private val aTitleDegreesLbl = ALabel(screenBox2d,"RADIAN =", Label.LabelStyle(font1, Color.WHITE))
    private val aTitleMetersLbl  = ALabel(screenBox2d,"METERS =", Label.LabelStyle(font1, Color.WHITE))
    private val aDegreesLbl      = ALabel(screenBox2d,"0", Label.LabelStyle(font2, Color.RED))
    private val aMetersLbl       = ALabel(screenBox2d,"0", Label.LabelStyle(font2, Color.RED))

    // BodyGroup
    private val bgRevoluteJoint  = BGGearRevoluteJoint(screenBox2d)
    private val bgPrismaticJoint = BGGearPrismaticJoint(screenBox2d)

    // Joint
    private val jGear = AbstractJoint<GearJoint, GearJointDef>(screenBox2d)

    override fun create(x: Float, y: Float, w: Float, h: Float) {
        super.create(x, y, w, h)

        createBG_RevoluteJoint()
        createBG_PrismaticJoint()

        createJ_Gear()

        screenBox2d.stageUI.apply {
            addTitleLbl()
            addValueLbl()
        }
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedStage.addTitleLbl() {
        addActors(aTitleDegreesLbl, aTitleMetersLbl)
        aTitleDegreesLbl.setBoundsStandart(966f, 397f, 146f, 36f)
        aTitleMetersLbl.setBoundsStandart(966f, 332f, 146f, 36f)
    }

    private fun AdvancedStage.addValueLbl() {
        addActors(aDegreesLbl, aMetersLbl)
        aDegreesLbl.setBoundsStandart(1120f, 397f, 91f, 54f)
        aMetersLbl.setBoundsStandart(1120f, 328f, 91f, 54f)
    }

    // ---------------------------------------------------
    // Create BodyGroup
    // ---------------------------------------------------

    private fun createBG_RevoluteJoint() {
        createBodyGroup(bgRevoluteJoint, 584f, 369f, 231f, 231f)
    }

    private fun createBG_PrismaticJoint() {
        createBodyGroup(bgPrismaticJoint, 0f, 62f, 217f, 127f)
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Gear() {
        createJoint(jGear, GearJointDef().apply {
            bodyA = bgRevoluteJoint.bDynamicCircle.body
            bodyB = bgPrismaticJoint.bDynamicHorizontal.body
            collideConnected = true

            joint1 = bgRevoluteJoint.jRevolute.joint
            joint2 = bgPrismaticJoint.jPrismatic.joint

            ratio = -1f
        })

        bgRevoluteJoint.bDynamicCircle.renderBlockArray.add(AbstractBody.RenderBlock {
            bgRevoluteJoint.jRevolute.joint?.run { jointAngle }.also { degree ->
                aDegreesLbl.label.setText("$degree")
            }
            bgPrismaticJoint.bDynamicHorizontal.body?.position?.x?.also { bx ->
                aMetersLbl.label.setText("${bx - (217f.toB2 / 2f)}")
            }
        })
    }

}