package com.rochevasternin.physical.joints.game.screens

import com.rochevasternin.physical.joints.game.LibGDXGame
import com.rochevasternin.physical.joints.game.actors.ABox2dObject
import com.rochevasternin.physical.joints.game.box2d.bodiesGroup.BGTestStand
import com.rochevasternin.physical.joints.game.box2d.destroyAll
import com.rochevasternin.physical.joints.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.rochevasternin.physical.joints.game.utils.actor.animHide
import com.rochevasternin.physical.joints.game.utils.actor.animShow
import com.rochevasternin.physical.joints.game.utils.actor.setOnTouchListener
import com.rochevasternin.physical.joints.game.utils.advanced.AdvancedMouseScreen
import com.rochevasternin.physical.joints.game.utils.advanced.AdvancedStage
import com.rochevasternin.physical.joints.game.utils.font.FontParameter
import com.rochevasternin.physical.joints.game.utils.region

class TutorialIntroductionScreen(override val game: LibGDXGame): AdvancedMouseScreen(game) {

    private val parameter = FontParameter()
    private val font25    = fontGeneratorInter_ExtraBold.generateFont(parameter.setCharacters(FontParameter.CharType.ALL).setSize(25))

    // Actor
    private val aBox2dObjectList = List(4) { ABox2dObject(this, ABox2dObject.Static.Type.values()[it], font25) }

    // BodyGroup
    private val bgTestStand = BGTestStand(this)

    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.themeUtil.assets.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addBox2dObjectList()

        createBG_TestStand()

        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA) {
            bgTestStand.impulseB_RegularBtnNext()
        }
    }

    override fun dispose() {
        listOf(bgTestStand).destroyAll()
        super.dispose()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addBox2dObjectList() {
        val vs = 39f
        var ny = 1239f
        aBox2dObjectList.onEach { aBox2dObject ->
            addActor(aBox2dObject)
            aBox2dObject.setBounds(19f, ny, 665f, 128f)
            ny -= vs + 128f
        }
    }

    // ------------------------------------------------------------------------
    // Create Body Group
    // ------------------------------------------------------------------------
    private fun createBG_TestStand() {
        bgTestStand.create(0f,145f,700f,431f)

        bgTestStand.bRegularBtnNext.getActor()?.setOnTouchListener { navigateTo(TutorialsScreen::class.java.name) }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun navigateTo(screenName: String) {
        stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(screenName, TutorialIntroductionScreen::class.java.name) }
    }

}