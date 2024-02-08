package avia.puzzle.wings.game.screens

import avia.puzzle.wings.adAppOpen
import com.badlogic.gdx.math.Vector2
import avia.puzzle.wings.game.LibGDXGame
import avia.puzzle.wings.game.actors.checkbox.ACheckBox
import avia.puzzle.wings.game.utils.Layout
import avia.puzzle.wings.game.utils.TIME_ANIM_SCREEN_ALPHA
import avia.puzzle.wings.game.utils.actor.animHide
import avia.puzzle.wings.game.utils.actor.setBounds
import avia.puzzle.wings.game.utils.actor.setOnClickListener
import avia.puzzle.wings.game.utils.advanced.AdvancedScreen
import avia.puzzle.wings.game.utils.advanced.AdvancedStage
import avia.puzzle.wings.game.utils.region
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image

var isMusic = true

class AviatorMenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val musicCB = ACheckBox(this, ACheckBox.Static.Type.MUSIC)
    private val exitBtn = Image(game.gameAssets.exit)
    private val menuImg = Image(game.gameAssets.menu)
    private val btns    = List(3) { Actor() }


    override fun show() {
        adAppOpen?.showAd(game.activity)

        setUIBackground(game.splashAssets.AviatorLoading.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addMusicCB()
        addExitBtn()
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

    private fun AdvancedStage.addExitBtn() {
        addActor(exitBtn)
        exitBtn.apply {
            setBounds(182f, 78f, 236f, 90f)
            setOnClickListener { navigateGo("EXIT") }
        }
    }

    private fun AdvancedStage.addBtns() {
        addActor(menuImg)
        menuImg.setBounds(27f, 353f, 547f, 594f)

        val nameS = listOf(
            AviatorPuzzleScreen::class.java.name,
            AviatorRulesScreen::class.java.name,
            AviatorSettingsScreen::class.java.name,
        )

        btns.onEachIndexed { index, aButton ->
            addActor(aButton)
            aButton.setBounds(Layout.Buttons.btns[index], Vector2(295f, 113f))
            aButton.setOnClickListener { navigateGo(nameS[index]) }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun navigateGo(id: String) {
        if (id == "EXIT") game.navigationManager.exit()
        else {
            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(id, AviatorMenuScreen::class.java.name) }
        }
    }


}