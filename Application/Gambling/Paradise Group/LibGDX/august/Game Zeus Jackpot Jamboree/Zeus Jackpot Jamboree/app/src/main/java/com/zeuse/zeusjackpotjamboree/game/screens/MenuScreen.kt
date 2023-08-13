package com.zeuse.zeusjackpotjamboree.game.screens

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.zeuse.zeusjackpotjamboree.game.LibGDXGame
import com.zeuse.zeusjackpotjamboree.game.box2d.bodiesGroup.BGBorders
import com.zeuse.zeusjackpotjamboree.game.box2d.disposeAll
import com.zeuse.zeusjackpotjamboree.game.utils.TIME_ANIM_ALPHA
import com.zeuse.zeusjackpotjamboree.game.utils.actor.animHide
import com.zeuse.zeusjackpotjamboree.game.utils.actor.animShow
import com.zeuse.zeusjackpotjamboree.game.utils.actor.setOnClickListener
import com.zeuse.zeusjackpotjamboree.game.utils.advanced.AdvancedScreen
import com.zeuse.zeusjackpotjamboree.game.utils.advanced.AdvancedStage

class MenuScreen(override val game: LibGDXGame): AdvancedScreen() {

    companion object {
        private var isMusicPlay = false
    }

    // Actor
    private val aPlayImg = Image(game.themeUtil.trc.PLAY)
    private val aExitImg = Image(game.themeUtil.trc.EXIT)
    private val aSettingsImg = Image(game.themeUtil.trc.SETTINGS)

    override fun show() {
        game.apply { backgroundColor = themeUtil.backgroundColor }

        stageUI.root.animHide()
        setUIBackground(game.themeUtil.trc.BACKGROUND)
        super.show()
        stageUI.root.animShow(TIME_ANIM_ALPHA)

        game.activity.setNavigationBarColor(game.themeUtil.navBarColor)

        if (isMusicPlay.not()) game.musicUtil.apply { music = DEFAULT.apply { isLooping = true } }

    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addBtns()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addBtns() {
        addActors(aPlayImg, aExitImg, aSettingsImg)
        aPlayImg.apply {
            setBounds(236f, 232f, 346f, 321f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_ALPHA) { game.navigationManager.navigate(GameScreen(game), MenuScreen(game)) } }
        }
        aExitImg.apply {
            setBounds(1189f, 232f, 346f, 321f)
            setOnClickListener { game.navigationManager.exit() }
        }
        aSettingsImg.apply {
            setBounds(725f, 258f, 321f, 346f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_ALPHA) { game.navigationManager.navigate(SettingsScreen(game), MenuScreen(game)) } }
        }
    }

}