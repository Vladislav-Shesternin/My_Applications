package mobile.jogodobicho.lucky.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import mobile.jogodobicho.lucky.game.LibGDXGame
import mobile.jogodobicho.lucky.game.actors.AResultGroup
import mobile.jogodobicho.lucky.game.actors.button.AButton
import mobile.jogodobicho.lucky.game.actors.checkbox.ACheckBox
import mobile.jogodobicho.lucky.game.actors.isPoganerToN
import mobile.jogodobicho.lucky.game.actors.progress.ATimer
import mobile.jogodobicho.lucky.game.actors.puzzle.APuzzlePanel
import mobile.jogodobicho.lucky.game.utils.TIME_ANIM_SCREEN_ALPHA
import mobile.jogodobicho.lucky.game.utils.actor.animHide
import mobile.jogodobicho.lucky.game.utils.actor.animShow
import mobile.jogodobicho.lucky.game.utils.actor.disable
import mobile.jogodobicho.lucky.game.utils.actor.enable
import mobile.jogodobicho.lucky.game.utils.actor.setOnClickListener
import mobile.jogodobicho.lucky.game.utils.advanced.AdvancedScreen
import mobile.jogodobicho.lucky.game.utils.advanced.AdvancedStage
import mobile.jogodobicho.lucky.game.utils.font.FontParameter
import mobile.jogodobicho.lucky.game.utils.puzzle.Puzzles
import mobile.jogodobicho.lucky.game.utils.region
import mobile.jogodobicho.lucky.util.log

class BullGameScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val parameter = FontParameter()

    private val puzzleRegion = game.gameAssets.puziles.random().region

    private val pauseCB    = ACheckBox(this, ACheckBox.Static.Type.PAUSE)
    private val menuBtn    = AButton(this, AButton.Static.Type.MENU)
    private val panel      = Image(game.gameAssets.PALANKA)
    private val levelLabel = Label(BullChooseLevelScreen.LEVEL_STATIC.getName(), Label.LabelStyle(fontJejuHallasan_Regular.generateFont(parameter.setCharacters(FontParameter.CharType.ALL).setSize(110)), Color.WHITE))
    private val timer      = ATimer(this)

    private val puzzlesPanel = APuzzlePanel(this, puzzleRegion)
    private val puzzleImg    = Image(puzzleRegion)
    private val result       = AResultGroup(this).apply { color.a = 0f }


    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.splashAssets.SUPER_PUPER_BACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun dispose() {
        Puzzles.dispose()
        super.dispose()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addPause()
        addMenu()
        addPalanka()
        addLevelLabel()
        addTimer()
        addPuzzle()
        addResult()

        timer.startTimer {
            result.apply {
                isPoganerToN = false
                upgradularute()
                enable()
                animShow(TIME_ANIM_SCREEN_ALPHA)
            }
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addPause() {
        addActor(pauseCB)
        pauseCB.setBounds(95f, 1740f, 121f, 130f)
        pauseCB.setOnCheckListener { timer.isPause = it }
    }

    private fun AdvancedStage.addMenu() {
        addActor(menuBtn)
        menuBtn.setBounds(874f, 1740f, 121f, 130f)
        menuBtn.setOnClickListener { back() }
    }

    private fun AdvancedStage.addPalanka() {
        addActor(panel)
        panel.setBounds(5f, 422f, 1072f, 1074f)
    }

    private fun AdvancedStage.addLevelLabel() {
        addActor(levelLabel)
        levelLabel.setBounds(173f, 1454f, 735f, 89f)
        levelLabel.setAlignment(Align.center)
    }

    private fun AdvancedStage.addTimer() {
        addActor(timer)
        timer.setBounds(402f, 1784f, 276f, 77f)
    }

    private fun AdvancedStage.addPuzzle() {
        addActor(puzzlesPanel)
        puzzlesPanel.setBounds(135f, 555f, 810f, 810f)

        puzzlesPanel.finishBlock = {
            result.apply {
                isPoganerToN = true
                upgradularute()
                enable()
                animShow(TIME_ANIM_SCREEN_ALPHA)
            }
        }

        addActor(puzzleImg)
        puzzleImg.setBounds(344f, 79f, 346f, 346f)
    }

    private fun AdvancedStage.addResult() {
        addAndFillActor(result)
        result.disable()
    }


    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun back() {
        stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }
    }


}