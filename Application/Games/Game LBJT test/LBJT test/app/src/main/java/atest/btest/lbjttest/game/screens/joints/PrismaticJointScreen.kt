package atest.btest.lbjttest.game.screens.joints

import atest.btest.lbjttest.game.LibGDXGame
import atest.btest.lbjttest.game.box2d.WorldUtil
import atest.btest.lbjttest.game.box2d.bodiesGroup.BGBorders
import atest.btest.lbjttest.game.box2d.bodiesGroup.prismatic.BGPrismaticJoint_Diagonal
import atest.btest.lbjttest.game.box2d.bodiesGroup.prismatic.BGPrismaticJoint_Horizontal
import atest.btest.lbjttest.game.box2d.bodiesGroup.prismatic.BGPrismaticJoint_Practical
import atest.btest.lbjttest.game.box2d.bodiesGroup.prismatic.BGPrismaticJoint_Vertical
import atest.btest.lbjttest.game.box2d.destroyAll
import atest.btest.lbjttest.game.utils.TIME_ANIM_SCREEN_ALPHA
import atest.btest.lbjttest.game.utils.actor.animHide
import atest.btest.lbjttest.game.utils.actor.animShow
import atest.btest.lbjttest.game.utils.advanced.AdvancedMouseScreen
import atest.btest.lbjttest.game.utils.advanced.AdvancedStage
import atest.btest.lbjttest.game.utils.region

class PrismaticJointScreen(override val game: LibGDXGame): AdvancedMouseScreen(game) {

    // BodyGroup
    private val bgBorders = BGBorders(this )
    private val bgPrismaticJoint_Vertical   = BGPrismaticJoint_Vertical(this)
    private val bgPrismaticJoint_Horizontal = BGPrismaticJoint_Horizontal(this)
    private val bgPrismaticJoint_Diagonal   = BGPrismaticJoint_Diagonal(this)
    private val bgPrismaticJoint_Practical  = BGPrismaticJoint_Practical(this)

    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.assets.BACKGROUND.region)
        super.show()

        WorldUtil.isDebug = true
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createBG_Borders()
        createBG_PrismaticJoint()

        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun dispose() {
        listOf(
            bgBorders,
            bgPrismaticJoint_Vertical, bgPrismaticJoint_Horizontal, bgPrismaticJoint_Diagonal,
            bgPrismaticJoint_Practical
        ).destroyAll()
        super.dispose()
    }

    // ------------------------------------------------------------------------
    // Create Body Group
    // ------------------------------------------------------------------------
    private fun createBG_Borders() {
        bgBorders.create(0f,0f,700f,1400f)
    }

    private fun createBG_PrismaticJoint() {
        //bgPrismaticJoint_Vertical.create(135f,0f,170f,435f)
        //bgPrismaticJoint_Horizontal.create(1095f,0f,170f,435f)
        //bgPrismaticJoint_Diagonal.create(615f,0f,170f,435f)
        bgPrismaticJoint_Practical.create(615f,0f,170f,435f)
    }

}