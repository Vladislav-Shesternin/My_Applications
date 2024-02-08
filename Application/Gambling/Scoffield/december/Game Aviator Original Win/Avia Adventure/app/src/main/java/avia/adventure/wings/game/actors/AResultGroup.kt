package avia.adventure.wings.game.actors

import avia.adventure.wings.game.actors.checkbox.ACheckBox
import avia.adventure.wings.game.screens.OriginalGameScreen
import avia.adventure.wings.game.utils.TIME_ANIM_SCREEN_ALPHA
import avia.adventure.wings.game.utils.actor.animHide
import avia.adventure.wings.game.utils.actor.setOnClickListener
import avia.adventure.wings.game.utils.advanced.AdvancedGroup
import avia.adventure.wings.game.utils.advanced.AdvancedScreen
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable

class AResultGroup(override val screen: AdvancedScreen): AdvancedGroup() {

    private val imgBackground = Image()

    override fun addActorsOnGroup() {
        touchable = Touchable.disabled
        addAndFillActor(imgBackground)
        addMusic()
        addBtns()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun addMusic() {
        val music = ACheckBox(screen, ACheckBox.Static.Type.MUSIC)
        addActor(music)
        music.setBounds(887f, 1721f, 177f, 176f)

        screen.game.musicUtil.music?.let { mmm ->
            if (mmm.isPlaying.not()) music.check(false)

            music.setOnCheckListener { if (it) mmm.pause() else mmm.play() }
        }
    }

    private fun addBtns() {
        val menu    = Actor()
        val restart = Actor()
        val next    = Actor()

        addActors(menu, restart, next)

        menu.apply {
            setBounds(127f, 473f, 90f, 90f)
            setOnClickListener(screen.game.soundUtil) {
                screen.stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { screen.game.navigationManager.back() }
            }
        }
        restart.apply {
            setBounds(435f, 473f, 90f, 90f)
            setOnClickListener(screen.game.soundUtil) {
                screen.stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { screen.game.navigationManager.navigate(OriginalGameScreen::class.java.name) }
            }
        }
        next.apply {
            setBounds(199f, 163f, 256f, 94f)
            setOnClickListener(screen.game.soundUtil) {
                screen.stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { screen.game.navigationManager.navigate(OriginalGameScreen::class.java.name) }
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun update(isWin: Boolean) {
        if (isWin) {
            imgBackground.drawable = TextureRegionDrawable(screen.game.gameAssets.OriginalWin)
            screen.game.soundUtil.apply { play(level_passed) }
        } else {
            imgBackground.drawable = TextureRegionDrawable(screen.game.gameAssets.OriginalLose)
            screen.game.soundUtil.apply { play(fail) }
        }
    }

}