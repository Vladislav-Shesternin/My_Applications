package atest.btest.lbjttest.game.screens

import atest.btest.lbjttest.game.LibGDXGame
import atest.btest.lbjttest.game.box2d.WorldUtil
import atest.btest.lbjttest.game.screens.joints.DistanceJointScreen
import atest.btest.lbjttest.game.screens.joints.FrictionJointScreen
import atest.btest.lbjttest.game.screens.joints.GearJointScreen
import atest.btest.lbjttest.game.screens.joints.MotorJointScreen
import atest.btest.lbjttest.game.screens.joints.PrismaticJointScreen
import atest.btest.lbjttest.game.screens.joints.PulleyJointScreen
import atest.btest.lbjttest.game.screens.joints.RevoluteJointScreen
import atest.btest.lbjttest.game.screens.joints.RopeJointScreen
import atest.btest.lbjttest.game.screens.joints.WeldJointScreen
import atest.btest.lbjttest.game.screens.joints.WheelJointScreen
import atest.btest.lbjttest.game.utils.TIME_ANIM_SCREEN_ALPHA
import atest.btest.lbjttest.game.utils.actor.animHide
import atest.btest.lbjttest.game.utils.actor.animShow
import atest.btest.lbjttest.game.utils.actor.setOnClickListener
import atest.btest.lbjttest.game.utils.advanced.AdvancedScreen
import atest.btest.lbjttest.game.utils.advanced.AdvancedStage
import atest.btest.lbjttest.game.utils.region
import atest.btest.lbjttest.game.utils.runGDX
import com.badlogic.gdx.scenes.scene2d.ui.Image

class MenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val testImg = Image(game.assets.TEST_BTN)

    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.assets.BACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addTestImg()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addTestImg() {
        addActor(testImg)
        testImg.apply {
            setBounds(467f, 266f, 466f, 169f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                    game.navigationManager.navigate(GearJointScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
    }


}