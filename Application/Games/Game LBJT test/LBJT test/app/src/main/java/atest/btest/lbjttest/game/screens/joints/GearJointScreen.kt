package atest.btest.lbjttest.game.screens.joints

import atest.btest.lbjttest.game.LibGDXGame
import atest.btest.lbjttest.game.box2d.WorldUtil
import atest.btest.lbjttest.game.box2d.bodiesGroup.BGBorders
import atest.btest.lbjttest.game.box2d.bodiesGroup.gear.BGGearJoint
import atest.btest.lbjttest.game.box2d.destroyAll
import atest.btest.lbjttest.game.utils.HEIGHT_UI
import atest.btest.lbjttest.game.utils.TIME_ANIM_SCREEN_ALPHA
import atest.btest.lbjttest.game.utils.WIDTH_UI
import atest.btest.lbjttest.game.utils.actor.animHide
import atest.btest.lbjttest.game.utils.actor.animShow
import atest.btest.lbjttest.game.utils.advanced.AdvancedMouseScreen
import atest.btest.lbjttest.game.utils.advanced.AdvancedStage
import atest.btest.lbjttest.game.utils.region
import com.badlogic.gdx.scenes.scene2d.ui.Image

class GearJointScreen(override val game: LibGDXGame): AdvancedMouseScreen(game) {

    // Actor
    private val aDegreesImg = Image(game.assets.DEGREES)
    private val aMetersImg  = Image(game.assets.METERS)

    // BodyGroup
    private val bgBorders   = BGBorders(this)
    private val bgGearJoint = BGGearJoint(this)

    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.assets.BACKGROUND.region)
        super.show()

        WorldUtil.isDebug = true
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createBG_Borders()
        createBG_GearJoint()

        stageUI.root.apply {
            addDegreesImg()
            addMetersImg()

            animShow(TIME_ANIM_SCREEN_ALPHA)
        }
    }

    override fun dispose() {
        listOf(bgBorders, bgGearJoint).destroyAll()
        super.dispose()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedStage.addDegreesImg() {
        addActor(aDegreesImg)
        aDegreesImg.setBounds(546f, 338f, 307f, 290f)
    }

    private fun AdvancedStage.addMetersImg() {
        addActor(aMetersImg)
        aMetersImg.setBounds(0f, 189f, 1400f, 28f)
    }

    // ------------------------------------------------------------------------
    // Create Body Group
    // ------------------------------------------------------------------------
    private fun createBG_Borders() {
        bgBorders.create(0f,0f,700f,1400f)
    }

    private fun createBG_GearJoint() {
        bgGearJoint.create(0f,0f, WIDTH_UI, HEIGHT_UI)
    }

}