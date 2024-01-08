package atest.btest.lbjttest.game.screens.joints

import atest.btest.lbjttest.game.LibGDXGame
import atest.btest.lbjttest.game.box2d.WorldUtil
import atest.btest.lbjttest.game.box2d.bodiesGroup.BGBorders
import atest.btest.lbjttest.game.box2d.bodiesGroup.rope.BGRopeJoint
import atest.btest.lbjttest.game.box2d.destroyAll
import atest.btest.lbjttest.game.utils.TIME_ANIM_SCREEN_ALPHA
import atest.btest.lbjttest.game.utils.actor.animHide
import atest.btest.lbjttest.game.utils.actor.animShow
import atest.btest.lbjttest.game.utils.advanced.AdvancedMouseScreen
import atest.btest.lbjttest.game.utils.advanced.AdvancedStage
import atest.btest.lbjttest.game.utils.region

class RopeJointScreen(override val game: LibGDXGame): AdvancedMouseScreen(game) {

    // BodyGroup
    private val bgBorders   = BGBorders(this)
    private val bgRopeJoint = BGRopeJoint(this)

    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.assets.BACKGROUND.region)
        super.show()

        WorldUtil.isDebug = true
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createBG_Borders()
        createBG_RopeJoint()

        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun dispose() {
        listOf(bgBorders, bgRopeJoint).destroyAll()
        super.dispose()
    }

    // ------------------------------------------------------------------------
    // Create Body Group
    // ------------------------------------------------------------------------
    private fun createBG_Borders() {
        bgBorders.create(0f,0f,700f,1400f)
    }

    private fun createBG_RopeJoint() {
        bgRopeJoint.create(665f,29f,70f,642f)
    }

}