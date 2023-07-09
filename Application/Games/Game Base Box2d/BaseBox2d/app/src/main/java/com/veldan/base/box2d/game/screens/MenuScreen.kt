package com.veldan.base.box2d.game.screens

import com.badlogic.gdx.math.Vector2
import com.veldan.base.box2d.R
import com.veldan.base.box2d.game.box2d.bodiesGroup.BGMenu
import com.veldan.base.box2d.game.game
import com.veldan.base.box2d.game.manager.SpriteManager
import com.veldan.base.box2d.game.utils.TIME_ANIM_ALPHA
import com.veldan.base.box2d.game.utils.actor.animHide
import com.veldan.base.box2d.game.utils.actor.animShow
import com.veldan.base.box2d.game.utils.advanced.AdvancedLBJTScreen
import com.veldan.base.box2d.game.utils.advanced.AdvancedStage


class MenuScreen: AdvancedLBJTScreen() {

    // BodyGroup
    private val bgMenu = BGMenu(this )


    override fun show() {
        stageUI.root.animHide()
        setUIBackground(SpriteManager.GameRegion.YIN_BACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_ALPHA)

        game.activity.setNavigationBarColor(R.color.yin)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
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

    private fun createBG_Menu() {
        bgMenu.create(Vector2(117f, 444f), Vector2(466f, 1012f))
    }

}