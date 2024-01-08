package atest.btest.lbjttest.game.screens.joints

import atest.btest.lbjttest.game.LibGDXGame
import atest.btest.lbjttest.game.box2d.WorldUtil
import atest.btest.lbjttest.game.box2d.bodiesGroup.BGBorders
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

class WheelJointScreen(override val game: LibGDXGame): AdvancedMouseScreen(game) {

    // Actor
    private val aArrowsImg = Image(game.assets.WHEEL_ARROWS)

    // BodyGroup
    private val bgBorders               = BGBorders(this)
    private val bgWheelJoint_Vertical   = BGWheelJoint_Vertical(this)
    private val bgWheelJoint_Horizontal = BGWheelJoint_Horizontal(this)
    private val bgWheelJoint_Diagonal   = BGWheelJoint_Diagonal(this)
    private val bgWheelJoint_Practical  = BGWheelJoint_Practical(this)

    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.assets.BACKGROUND.region)
        super.show()

        WorldUtil.isDebug = true
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createBG_Borders()
        createBG_WheelJoint()

        stageUI.root.apply {
            //addArrowImg()
            animShow(TIME_ANIM_SCREEN_ALPHA)
        }
    }

    override fun dispose() {
        listOf(bgBorders, bgWheelJoint_Vertical, bgWheelJoint_Horizontal, bgWheelJoint_Diagonal, bgWheelJoint_Practical).destroyAll()
        super.dispose()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedStage.addArrowImg() {
        addActor(aArrowsImg)
        aArrowsImg.setBounds(38f, 540f, 1024f, 90f)
    }

    // ------------------------------------------------------------------------
    // Create Body Group
    // ------------------------------------------------------------------------
    private fun createBG_Borders() {
        bgBorders.create(0f,0f,700f,1400f)
    }

    private fun createBG_WheelJoint() {
        //bgWheelJoint_Vertical.create(135f,265f,170f,435f)
        //bgWheelJoint_Horizontal.create(1095f,265f,170f,435f)
        //bgWheelJoint_Diagonal.create(615f,265f,170f,435f)
        bgWheelJoint_Practical.create(615f,265f,170f,435f)
    }

}