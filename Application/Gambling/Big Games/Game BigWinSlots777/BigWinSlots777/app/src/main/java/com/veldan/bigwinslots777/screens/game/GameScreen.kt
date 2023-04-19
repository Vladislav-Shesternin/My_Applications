package com.veldan.bigwinslots777.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.veldan.bigwinslots777.R
import com.veldan.bigwinslots777.actors.button.ButtonClickable
import com.veldan.bigwinslots777.actors.button.ButtonClickableStyle
import com.veldan.bigwinslots777.actors.checkbox.CheckBox
import com.veldan.bigwinslots777.actors.checkbox.CheckBoxStyle
import com.veldan.bigwinslots777.actors.label.LabelStyle
import com.veldan.bigwinslots777.actors.label.spinning.SpinningLabel
import com.veldan.bigwinslots777.actors.slot.slotGroup.SlotGroup
import com.veldan.bigwinslots777.actors.super_game.SuperGameGroup
import com.veldan.bigwinslots777.advanced.AdvancedScreen
import com.veldan.bigwinslots777.advanced.AdvancedStage
import com.veldan.bigwinslots777.advanced.group.AdvancedGroup
import com.veldan.bigwinslots777.manager.assets.SpriteManager
import com.veldan.bigwinslots777.manager.assets.util.MusicUtil
import com.veldan.bigwinslots777.utils.disable
import com.veldan.bigwinslots777.utils.language.Language
import com.veldan.bigwinslots777.utils.listeners.toClickable
import com.veldan.bigwinslots777.utils.region
import com.veldan.bigwinslots777.utils.transformToBalanceFormat
import kotlinx.coroutines.*
import com.veldan.bigwinslots777.layout.Layout.Game as LG
import com.veldan.bigwinslots777.layout.Layout.Game.MINI_GAME_DIALOG as LGMGD

class GameScreen : AdvancedScreen() {
    override val controller = GameScreenController(this)

    // gameGroup
    val gameGroup         = AdvancedGroup()
    // balance
    val balancePanelGroup = AdvancedGroup()
    val balancePanelImage = Image(SpriteManager.GameRegion.BALANCE_PANEL.region)
    val balanceTextLabel  = SpinningLabel("", LabelStyle.rockwell_60)
    // bet
    val betPanelGroup     = AdvancedGroup()
    val betPanelImage     = Image(SpriteManager.GameRegion.BET_PANEL.region)
    val betTextLabel      = SpinningLabel("", LabelStyle.rockwell_60)
    val betPlusButton     = ButtonClickable(ButtonClickableStyle.plus)
    val betMinusButton    = ButtonClickable(ButtonClickableStyle.minus)
    // music
    val musicCheckBox     = CheckBox(CheckBoxStyle.music)
    // autospin
    val autoSpinButton    = ButtonClickable(ButtonClickableStyle.autoSpin)
    // spin
    val spinButton        = ButtonClickable(ButtonClickableStyle.spin)
    val spinTextLabel     by lazy { SpinningLabel(Language.getStringResource(R.string.spin), LabelStyle.font_50) }
    // slotGroup
    val slotGroup         = SlotGroup()

    // tutorialGroup
//    val tutorialGroup      = TutorialGroup()
    // miniGameGroup
    val dialogGroup        = AdvancedGroup()
    val dialogImage        = Image(SpriteManager.GameRegion.DIALOG_PANEL.region)
    val miniGameLabel      = Label(Language.getStringResource(R.string.mini_game), LabelStyle.font_60).apply { setAlignment(Align.center) }
    val miniGameTextLabel  = SpinningLabel("", LabelStyle.font_50)
    val miniGamePhaseLabel = Label("", LabelStyle.font_30).apply { setAlignment(Align.center) }
    // superGameGroup
    lateinit var superGameGroup: SuperGameGroup



    override fun show() {
        super.show()
        with(MusicUtil) { currentMusic = MAIN }
        setBackgrounds(SpriteManager.SourceTexture.BACKGROUND.data.texture.region)
        stage.addActorsOnStage()
    }



    private fun AdvancedStage.addActorsOnStage() {
        addGameGroup()
     //   addTutorialGroup()
    }

    // ------------------------------------------------------------------------
    // GameGroup
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addGameGroup() {
        addAndFillActor(gameGroup)
        addActorsOnGameGroup()
    }

    private fun addActorsOnGameGroup() {
        with(gameGroup) {
            addBalancePanel()
            addBetPanel()
            addBetPlusButton()
            addBetMinusButton()
            addMusicCheckBox()
            addAutoSpinButton()
            addSpinButton()
            addSlotGroup()
        }
    }

    private fun AdvancedGroup.addBalancePanel() {
        addActor(balancePanelGroup)
        balancePanelGroup.apply {
            setBounds(LG.BALANCE_PANEL_X, LG.BALANCE_PANEL_Y, LG.BALANCE_PANEL_W, LG.BALANCE_PANEL_H)

            addAndFillActor(balancePanelImage)
            addActor(balanceTextLabel)

            balanceTextLabel.apply {
                setBounds(LG.BALANCE_TEXT_X, LG.BALANCE_TEXT_Y, LG.BALANCE_TEXT_W, LG.BALANCE_TEXT_H)
                this@GameScreen.controller.updateBalance()
            }
        }
    }

    private fun AdvancedGroup.addBetPanel() {
        addActor(betPanelGroup)
        betPanelGroup.apply {
            setBounds(LG.BET_PANEL_X, LG.BET_PANEL_Y, LG.BET_PANEL_W, LG.BET_PANEL_H)

            addAndFillActor(betPanelImage)
            addActors(betTextLabel)

            betTextLabel.apply {
                setBounds(LG.BET_TEXT_X, LG.BET_TEXT_Y, LG.BET_TEXT_W, LG.BET_TEXT_H)
                this@GameScreen.controller.updateBet()
            }
        }
    }

    private fun AdvancedGroup.addBetPlusButton() {
        addActor(betPlusButton)
        betPlusButton.apply {
            setBounds(LG.BET_PLUS_X, LG.BET_PLUS_Y, LG.BET_PLUS_W, LG.BET_PLUS_H)
            controller.setOnClickListener { this@GameScreen.controller.betPlusHandler() }
        }
    }

    private fun AdvancedGroup.addBetMinusButton() {
        addActor(betMinusButton)
        betMinusButton.apply {
            setBounds(LG.BET_MINUS_X, LG.BET_MINUS_Y, LG.BET_MINUS_W, LG.BET_MINUS_H)
            controller.setOnClickListener { this@GameScreen.controller.betMinusHandler() }
        }
    }

    private fun AdvancedGroup.addMusicCheckBox() {
        addActor(musicCheckBox)
        musicCheckBox.apply {
            setBounds(LG.MUSIC_X, LG.MUSIC_Y, LG.MUSIC_W, LG.MUSIC_H)
            controller.setOnCheckListener { isCheck ->
                MusicUtil.isPause = isCheck
                if (isCheck) MusicUtil.currentMusic?.pause()
                else MusicUtil.currentMusic?.play()
            }
            if (MusicUtil.isPause.not()) controller.uncheck() else controller.check()
        }
    }

    private fun AdvancedGroup.addAutoSpinButton() {
        addActor(autoSpinButton)
        autoSpinButton.apply {
            setBounds(LG.AUTO_SPIN_X, LG.AUTO_SPIN_Y, LG.AUTO_SPIN_W, LG.AUTO_SPIN_H)
            controller.setOnClickListener { this@GameScreen.controller.autoSpinHandler() }
        }
    }

    private fun AdvancedGroup.addSpinButton() {
        addActor(spinButton)
        spinButton.apply {
            setBounds(LG.SPIN_X, LG.SPIN_Y, LG.SPIN_W, LG.SPIN_H)

            addActor(spinTextLabel)
            spinTextLabel.setBounds(LG.SPIN_TEXT_X, LG.SPIN_TEXT_Y, LG.SPIN_TEXT_W, LG.SPIN_TEXT_H)

            controller.setOnClickListener { this@GameScreen.controller.spinHandler() }
        }
    }

    private fun AdvancedGroup.addSlotGroup() {
        addActor(slotGroup)
        slotGroup.setPosition(LG.SLOT_GROUP_X, LG.SLOT_GROUP_Y)
    }

  /*  // ------------------------------------------------------------------------
    // TutorialGroup
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addTutorialGroup() {
        CoroutineScope(Dispatchers.IO).launch {

            suspend fun startTutorial() {
                gameGroup.disable()
                addAndFillActor(tutorialGroup)
                tutorialGroup.controller.start()
                removeTutorialGroup()
                cancel()
            }

            if (DataStoreManager.Tutorial.get() == null) {
                DataStoreManager.Tutorial.update { false }
                startTutorial()
            }
            else if (DataStoreManager.Tutorial.get()!!) startTutorial()
        }
    }

    private suspend fun removeTutorialGroup() = CompletableDeferred<Boolean>().also { continuation ->
        tutorialGroup.addAction(Actions.sequence(
            Actions.fadeOut(GameScreenController.TIME_HIDE_GROUP),
            Actions.run { continuation.complete(true) },
            Actions.removeActor(),
        ))
        gameGroup.enable()
    }.await()*/

    // ------------------------------------------------------------------------
    // SuperGameGroup
    // ------------------------------------------------------------------------
    fun addSuperGameGroup() {
        superGameGroup = SuperGameGroup()
        superGameGroup.disable()

        with(stage) {
            superGameGroup.addAction(Actions.alpha(0f))
            addAndFillActor(superGameGroup)
        }
    }

    fun addSuperGameElementGroup() {
        gameGroup.addActor(superGameGroup.elementGroup)
        superGameGroup.elementGroup.setPosition(LG.SUPER_ELEMENT_X, LG.SUPER_ELEMENT_Y)
    }

    fun removeSuperGameElementGroup() {
        Gdx.app.postRunnable { superGameGroup.elementGroup.remove() }
    }

    fun removeSuperGameGroup() {
        Gdx.app.postRunnable { superGameGroup.remove() }
    }

    // ------------------------------------------------------------------------
    // MiniGame
    // ------------------------------------------------------------------------
    fun addMiniGameStartDialog() {
        with(stage) {
            dialogGroup.addAction(Actions.alpha(0f))
            addActor(dialogGroup)
            dialogGroup.apply {
                setBounds(LGMGD.X, LGMGD.Y, LGMGD.W, LGMGD.H)

                addAndFillActor(dialogImage)
                addActors(miniGameLabel, miniGamePhaseLabel, miniGameTextLabel)

                miniGameLabel.setBounds(LGMGD.LABEL_X, LGMGD.LABEL_Y, LGMGD.LABEL_W, LGMGD.LABEL_H)
                miniGamePhaseLabel.apply {
                    setText(Language.getStringResource(R.string.start))
                    setBounds(LGMGD.PHASE_X, LGMGD.PHASE_Y, LGMGD.PHASE_W, LGMGD.PHASE_H)
                }
                miniGameTextLabel.apply {
                    controller.setText(Language.getStringResource(R.string.mini_game_text_1))
                    setBounds(LGMGD.TEXT_X, LGMGD.TEXT_Y, LGMGD.TEXT_W, LGMGD.TEXT_H)
                }

                toClickable().setOnClickListener { this@GameScreen.controller.isStartMiniGameFlow.value = true }
            }
        }
    }

    fun addMiniGameFinishDialog() {
        with(stage) {
            dialogGroup.addAction(Actions.alpha(0f))
            addActor(dialogGroup)
            dialogGroup.apply {
                setBounds(LGMGD.X, LGMGD.Y, LGMGD.W, LGMGD.H)

                addAndFillActor(dialogImage)
                addActors(miniGameLabel, miniGamePhaseLabel, miniGameTextLabel)

                miniGameLabel.setBounds(LGMGD.LABEL_X, LGMGD.LABEL_Y, LGMGD.LABEL_W, LGMGD.LABEL_H)
                miniGamePhaseLabel.apply {
                    setText(Language.getStringResource(R.string.finish))
                    setBounds(LGMGD.PHASE_X, LGMGD.PHASE_Y, LGMGD.PHASE_W, LGMGD.PHASE_H)
                }
                miniGameTextLabel.apply {
                    controller.setText("${Language.getStringResource(R.string.mini_game_text_2)} ${this@GameScreen.controller.miniGameSum.transformToBalanceFormat()}.")
                    setBounds(LGMGD.TEXT_X, LGMGD.TEXT_Y, LGMGD.TEXT_W, LGMGD.TEXT_H)
                }

                toClickable().setOnClickListener { this@GameScreen.controller.isFinishMiniGameFlow.value = true }
            }
        }
    }

    fun removeMiniGameDialog() {
        dialogGroup.remove()
    }
}