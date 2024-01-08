package atest.btest.lbjttest.game.screens.joints

import atest.btest.lbjttest.game.LibGDXGame
import atest.btest.lbjttest.game.box2d.WorldUtil
import atest.btest.lbjttest.game.box2d.bodiesGroup.BGBorders
import atest.btest.lbjttest.game.box2d.bodiesGroup.weld.BGWeldJoint_1
import atest.btest.lbjttest.game.box2d.bodiesGroup.weld.BGWeldJoint_2
import atest.btest.lbjttest.game.box2d.bodiesGroup.weld.BGWeldJoint_3
import atest.btest.lbjttest.game.box2d.bodiesGroup.weld.BGWeldJoint_Practical
import atest.btest.lbjttest.game.box2d.bodiesGroup.wheel.BGWheelJoint_Diagonal
import atest.btest.lbjttest.game.box2d.bodiesGroup.wheel.BGWheelJoint_Horizontal
import atest.btest.lbjttest.game.box2d.bodiesGroup.wheel.BGWheelJoint_Practical
import atest.btest.lbjttest.game.box2d.bodiesGroup.wheel.BGWheelJoint_Vertical
import atest.btest.lbjttest.game.box2d.destroyAll
import atest.btest.lbjttest.game.utils.TIME_ANIM_SCREEN_ALPHA
import atest.btest.lbjttest.game.utils.actor.animHide
import atest.btest.lbjttest.game.utils.actor.animShow
import atest.btest.lbjttest.game.utils.advanced.AdvancedMouseScreen
import atest.btest.lbjttest.game.utils.advanced.AdvancedStage
import atest.btest.lbjttest.game.utils.region
import com.badlogic.gdx.scenes.scene2d.ui.Image

class WeldJointScreen(override val game: LibGDXGame): AdvancedMouseScreen(game) {

    // BodyGroup
    private val bgBorders     = BGBorders(this)
    private val bgWeldJoint_1 = BGWeldJoint_1(this)
    private val bgWeldJoint_2 = BGWeldJoint_2(this)
    private val bgWeldJoint_3 = BGWeldJoint_3(this)
    private val bgWeldJoint_Practical = BGWeldJoint_Practical(this)

    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.assets.BACKGROUND.region)
        super.show()

        WorldUtil.isDebug = true
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createBG_Borders()
        createBG_WeldJoint()

        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun dispose() {
        listOf(bgBorders, bgWeldJoint_1, bgWeldJoint_2, bgWeldJoint_3, bgWeldJoint_Practical).destroyAll()
        super.dispose()
    }

    // ------------------------------------------------------------------------
    // Create Body Group
    // ------------------------------------------------------------------------
    private fun createBG_Borders() {
        bgBorders.create(0f,0f,700f,1400f)
    }

    private fun createBG_WeldJoint() {
//        bgWeldJoint_3.create(169f,202f,170f,296f)
//        bgWeldJoint_2.create(552f,202f,296f,296f)
//        bgWeldJoint_1.create(1106f,202f,170f,296f)
        bgWeldJoint_Practical.create(615f,202f,170f,296f)
    }

}