package com.veldan.lbjt.game.screens

import com.badlogic.gdx.math.Vector2
import com.veldan.lbjt.R
import com.veldan.lbjt.game.box2d.bodiesGroup.BGBorders
import com.veldan.lbjt.game.box2d.bodiesGroup.BGMenu
import com.veldan.lbjt.game.game
import com.veldan.lbjt.game.manager.SpriteManager
import com.veldan.lbjt.game.utils.TIME_ANIM_ALPHA
import com.veldan.lbjt.game.utils.actor.animHide
import com.veldan.lbjt.game.utils.actor.animShow
import com.veldan.lbjt.game.utils.advanced.AdvancedLBJTScreen
import com.veldan.lbjt.game.utils.advanced.AdvancedStage


class MenuScreen: AdvancedLBJTScreen() {

    // BodyGroup
    private val bgBorders = BGBorders(this )
    private val bgMenu    = BGMenu(this )


    override fun show() {
        stageUI.root.animHide()
        setUIBackground(SpriteManager.GameRegion.YIN_BACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_ALPHA)

        game.activity.setNavigationBarColor(R.color.yin)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createBG_Borders()
        createBG_Menu()

    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

//    private fun AdvancedStage.addUser() {
//
//    }

    // ------------------------------------------------------------------------
    // Create Body Group
    // ------------------------------------------------------------------------

    private fun createBG_Borders() {
        bgBorders.create(Vector2(0f, 0f), Vector2(700f, 1400f))
    }

    private fun createBG_Menu() {
        bgMenu.create(Vector2(117f, 444f), Vector2(466f, 1012f))
    }

}