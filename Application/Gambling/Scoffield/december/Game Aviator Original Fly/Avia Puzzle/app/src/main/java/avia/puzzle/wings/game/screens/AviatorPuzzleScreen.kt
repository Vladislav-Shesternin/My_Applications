package avia.puzzle.wings.game.screens

import avia.puzzle.wings.game.LibGDXGame
import avia.puzzle.wings.game.actors.button.AButton
import avia.puzzle.wings.game.actors.checkbox.ACheckBox
import avia.puzzle.wings.game.utils.TIME_ANIM_SCREEN_ALPHA
import avia.puzzle.wings.game.utils.actor.animHide
import avia.puzzle.wings.game.utils.actor.setOnClickListener
import avia.puzzle.wings.game.utils.advanced.AdvancedScreen
import avia.puzzle.wings.game.utils.advanced.AdvancedStage
import avia.puzzle.wings.game.utils.region
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image

var AVIA_INDEX = 0

class AviatorPuzzleScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val musicCB = ACheckBox(this, ACheckBox.Static.Type.MUSIC)
    private val menuBtn = AButton(this, AButton.Static.Type.MENU)
    private val aviaBtn = Image(game.gameAssets.avia_btns)
    private val exitBtn = Image(game.gameAssets.exit)


    override fun show() {
        setUIBackground(game.splashAssets.AviatorLoading.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addMusicCB()
        addMenuBtn()
        addExitBtn()
        addAviaBtn()
        addBtns()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addMusicCB() {
        addActor(musicCB)
        musicCB.apply {
            setBounds(505f, 1211f, 76f, 75f)
            if (isMusic.not()) check(false)

            setOnCheckListener { isCheck ->
                if (isCheck) {
                    isMusic = false
                    game.musicUtil.music?.pause()
                } else {
                    isMusic = true
                    game.musicUtil.music?.play()
                }
            }

        }
    }

    private fun AdvancedStage.addMenuBtn() {
        addActor(menuBtn)
        menuBtn.apply {
            setBounds(19f, 1211f, 76f, 75f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() } }
        }
    }

    private fun AdvancedStage.addExitBtn() {
        addActor(exitBtn)
        exitBtn.apply {
            setBounds(182f, 78f, 236f, 90f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() } }
        }
    }

    private fun AdvancedStage.addAviaBtn() {
        addActor(aviaBtn)
        aviaBtn.setBounds(49f, 214f, 504f, 1016f)
    }

    private fun AdvancedStage.addBtns() {
        var nx = 49f
        var ny = 886f

        repeat(8) { index ->
            Actor().also { actor ->
                addActor(actor)
                actor.setBounds(nx, ny, 216f, 200f)
                nx += 73f + 216f
                if (index.inc() % 2 == 0) {
                    nx = 49f
                    ny -= 25f + 200f
                }

                actor.setOnClickListener(game.soundUtil) {
                    stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                        AVIA_INDEX = index
                        game.navigationManager.navigate(AviatorPlaygraundScreen::class.java.name, AviatorPuzzleScreen::class.java.name)
                    }
                }
            }
        }
    }


}