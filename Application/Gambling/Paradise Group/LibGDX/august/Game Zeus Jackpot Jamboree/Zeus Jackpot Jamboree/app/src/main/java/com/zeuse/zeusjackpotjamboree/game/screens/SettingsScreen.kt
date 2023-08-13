package com.zeuse.zeusjackpotjamboree.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.zeuse.zeusjackpotjamboree.game.LibGDXGame
import com.zeuse.zeusjackpotjamboree.game.utils.TIME_ANIM_ALPHA
import com.zeuse.zeusjackpotjamboree.game.utils.ThemeUtil
import com.zeuse.zeusjackpotjamboree.game.utils.actor.animHide
import com.zeuse.zeusjackpotjamboree.game.utils.actor.animShow
import com.zeuse.zeusjackpotjamboree.game.utils.actor.setOnClickListener
import com.zeuse.zeusjackpotjamboree.game.utils.advanced.AdvancedScreen
import com.zeuse.zeusjackpotjamboree.game.utils.advanced.AdvancedStage

class SettingsScreen(override val game: LibGDXGame): AdvancedScreen() {

    // Actor
    private val aWhiteImg = Image(game.themeUtil.trc.WHITE)
    private val aBlackImg = Image(game.themeUtil.trc.BLACK)
    private val aSettingsImg = Image(game.themeUtil.trc.SETTINGS)

    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.themeUtil.trc.BACKGROUND)
        super.show()
        stageUI.root.animShow(TIME_ANIM_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addBtns()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addBtns() {
        addActors(aWhiteImg, aBlackImg, aSettingsImg)
        aWhiteImg.apply {
            setBounds(236f, 232f, 346f, 321f)
            setOnClickListener {
                game.themeUtil.update(ThemeUtil.Type.WHITE)
                game.apply { backgroundColor = themeUtil.backgroundColor }
                setUIBackground(game.themeUtil.trc.BACKGROUND)
                game.activity.setNavigationBarColor(game.themeUtil.navBarColor)
            }
        }
        aBlackImg.apply {
            setBounds(1189f, 232f, 346f, 321f)
            setOnClickListener {
                game.themeUtil.update(ThemeUtil.Type.BLACK)
                game.apply { backgroundColor = themeUtil.backgroundColor }
                setUIBackground(game.themeUtil.trc.BACKGROUND)
                game.activity.setNavigationBarColor(game.themeUtil.navBarColor)
            }
        }
        aSettingsImg.apply {
            setBounds(725f, 258f, 321f, 346f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_ALPHA) { game.navigationManager.back() } }
        }
    }

}