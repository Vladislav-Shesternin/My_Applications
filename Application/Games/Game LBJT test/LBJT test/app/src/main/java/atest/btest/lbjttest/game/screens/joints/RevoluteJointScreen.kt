package atest.btest.lbjttest.game.screens.joints

import atest.btest.lbjttest.game.LibGDXGame
import atest.btest.lbjttest.game.box2d.WorldUtil
import atest.btest.lbjttest.game.box2d.bodiesGroup.BGBorders
import atest.btest.lbjttest.game.box2d.bodiesGroup.distance.BGDistanceJoint
import atest.btest.lbjttest.game.box2d.bodiesGroup.revolute.BGRevoluteJoint_1
import atest.btest.lbjttest.game.box2d.bodiesGroup.revolute.BGRevoluteJoint_2
import atest.btest.lbjttest.game.box2d.bodiesGroup.revolute.BGRevoluteJoint_3
import atest.btest.lbjttest.game.box2d.destroyAll
import atest.btest.lbjttest.game.utils.TIME_ANIM_SCREEN_ALPHA
import atest.btest.lbjttest.game.utils.actor.animHide
import atest.btest.lbjttest.game.utils.actor.animShow
import atest.btest.lbjttest.game.utils.advanced.AdvancedMouseScreen
import atest.btest.lbjttest.game.utils.advanced.AdvancedStage
import atest.btest.lbjttest.game.utils.region

class RevoluteJointScreen(override val game: LibGDXGame): AdvancedMouseScreen(game) {

    // BodyGroup
    private val bgBorders         = BGBorders(this )
    private val bgRevoluteJoint_1 = BGRevoluteJoint_1(this)
    private val bgRevoluteJoint_2 = BGRevoluteJoint_2(this)
    private val bgRevoluteJoint_3 = BGRevoluteJoint_3(this)

    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.assets.BACKGROUND.region)
        super.show()

        WorldUtil.isDebug = true
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createBG_Borders()
        createBG_RevoluteJoint()

        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun dispose() {
        listOf(
            bgBorders,
            bgRevoluteJoint_1, bgRevoluteJoint_2, bgRevoluteJoint_3
        ).destroyAll()
        super.dispose()
    }

    // ------------------------------------------------------------------------
    // Create Body Group
    // ------------------------------------------------------------------------
    private fun createBG_Borders() {
        bgBorders.create(0f,0f,700f,1400f)
    }

    private fun createBG_RevoluteJoint() {
        //bgRevoluteJoint_1.create(162f,258f,205f,205f)
        //bgRevoluteJoint_2.create(615f,265f,170f,170f)
        //bgRevoluteJoint_3.create(1033f,208f,170f,325f)

        bgRevoluteJoint_1.create(598f,258f,205f,205f)
    }

}