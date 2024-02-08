//package com.sca.rab.que.stgame.game.screens
//
//import com.badlogic.gdx.graphics.Color
//import com.badlogic.gdx.scenes.scene2d.ui.Image
//import com.badlogic.gdx.scenes.scene2d.ui.Label
//import com.badlogic.gdx.utils.Align
//import com.sca.rab.que.stgame.game.LibGDXGame
//import com.sca.rab.que.stgame.game.actors.AResultGroup
//import com.sca.rab.que.stgame.game.actors.button.AButton
//import com.sca.rab.que.stgame.game.actors.checkbox.ACheckBox
//import com.sca.rab.que.stgame.game.actors.masks.Mask
//import com.sca.rab.que.stgame.game.actors.progress.ATimer
//import com.sca.rab.que.stgame.game.actors.puzzle.APuzzlePanel
//import com.sca.rab.que.stgame.game.utils.actor.animShow
//import com.sca.rab.que.stgame.game.utils.actor.animShow_Suspend
//import com.sca.rab.que.stgame.game.utils.actor.enable
//import com.sca.rab.que.stgame.game.utils.advanced.AdvancedScreen
//import com.sca.rab.que.stgame.game.utils.advanced.AdvancedStage
//import com.sca.rab.que.stgame.game.utils.font.FontParameter
//import com.sca.rab.que.stgame.game.utils.puzzle.Puzzles
//import com.sca.rab.que.stgame.game.utils.region
//import com.sca.rab.que.stgame.game.utils.runGDX
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//
//class LoaPuzzleScreen(override val game: LibGDXGame) : AdvancedScreen() {
//
//    private val parameter = FontParameter()
//    private val font      = fontGeneratorLionKing.generateFont(parameter.setCharacters(
//        LoaLevelScreen.Level.text).setSize(128))
//
//    private val puzzleRegion = game.gameAssets.puzzleList.random().region
//
//    private val menuBtn   = AButton(this, AButton.Static.Type.MENU).apply { color.a = 0f }
//    private val pauseCB   = ACheckBox(this, ACheckBox.Static.Type.PAUSE).apply { color.a = 0f }
//    private val panelImg  = Image(game.gameAssets.LOA_GAME).apply { color.a = 0f }
//    private val timer     = ATimer(this).apply { color.a = 0f }
//    private val puzzlesPanel = APuzzlePanel(this, puzzleRegion).apply { color.a = 0f }
//    private val puzzleImg    = Image(puzzleRegion).apply { color.a = 0f }
//    private val puzzleImgB   = Image(game.gameAssets.pikalka).apply { color.a = 0f }
//    private val levelLabel   = Label(LoaLevelScreen.Level.text, Label.LabelStyle(font, Color.BLACK)).apply { color.a = 0f }
//    private val resultGroup  = AResultGroup(this)
//
//    override fun show() {
//        setBackgrounds(game.splashAssets.LOALOAD_MAIN.region)
//        super.show()
//    }
//
//    override fun AdvancedStage.addActorsOnStageUI() {
//        coroutine?.launch {
//            runGDX {
//                addMenuBtn()
//                addPauseCB()
//                addGame()
//                addPuzzlePanel()
//                addPuzzleImg()
//                addLevelLbl()
//                addTimer()
//                addAndFillActor(resultGroup)
//            }
//            delay(470)
//            menuBtn.animShow_Suspend(com.sca.rab.que.stgame.game.utils.TIME_ANIM_SCREEN_ALPHA)
//            delay(50)
//            pauseCB.animShow_Suspend(com.sca.rab.que.stgame.game.utils.TIME_ANIM_SCREEN_ALPHA)
//            delay(50)
//            panelImg.animShow_Suspend(com.sca.rab.que.stgame.game.utils.TIME_ANIM_SCREEN_ALPHA)
//            delay(50)
//            puzzlesPanel.animShow_Suspend(com.sca.rab.que.stgame.game.utils.TIME_ANIM_SCREEN_ALPHA)
//            delay(50)
//            puzzleImgB.animShow_Suspend(com.sca.rab.que.stgame.game.utils.TIME_ANIM_SCREEN_ALPHA)
//            delay(50)
//            puzzleImg.animShow_Suspend(com.sca.rab.que.stgame.game.utils.TIME_ANIM_SCREEN_ALPHA)
//            delay(50)
//            levelLabel.animShow_Suspend(com.sca.rab.que.stgame.game.utils.TIME_ANIM_SCREEN_ALPHA)
//            timer.animShow_Suspend(com.sca.rab.que.stgame.game.utils.TIME_ANIM_SCREEN_ALPHA)
//
//            runGDX {
//                timer.startTimer {
//                    resultGroup.apply {
//                        timer.isPause = true
//                        enable()
//                        game.soundUtil.apply { play(NEGATIVE) }
//                        showResult(false)
//                        animShow(com.sca.rab.que.stgame.game.utils.TIME_ANIM_SCREEN_ALPHA)
//                    }
//                }
//            }
//        }
//    }
//
//    override fun dispose() {
//        Puzzles.dispose()
//        super.dispose()
//    }
//
//    // ------------------------------------------------------------------------
//    // Add Actors
//    // ------------------------------------------------------------------------
//
//    private fun AdvancedStage.addMenuBtn() {
//        addActor(menuBtn)
//        menuBtn.setBounds(39f, 1717f, 156f, 162f)
//        menuBtn.setOnClickListener { animHideScreen { game.navigationManager.back() } }
//    }
//
//    private fun AdvancedStage.addPauseCB() {
//        addActor(pauseCB)
//        pauseCB.apply {
//            setBounds(891f, 1725f, 156f, 162f)
//
//            setOnCheckListener { timer.isPause = it }
//        }
//    }
//
//    private fun AdvancedStage.addGame() {
//        addActor(panelImg)
//        panelImg.setBounds(93f, 512f, 908f, 953f)
//    }
//
//    private fun AdvancedStage.addPuzzlePanel() {
//        addActor(puzzlesPanel)
//        puzzlesPanel.setBounds(198f, 644f, 684f, 684f)
//
//        puzzlesPanel.finishBlock = {
//            resultGroup.apply {
//                enable()
//                game.soundUtil.apply { play(POSITIVE) }
//                showResult(true)
//                animShow(com.sca.rab.que.stgame.game.utils.TIME_ANIM_SCREEN_ALPHA)
//            }
//        }
//    }
//
//    private fun AdvancedStage.addPuzzleImg() {
//        addActor(puzzleImgB)
//        puzzleImgB.setBounds(381f, 57f, 383f, 386f)
//
//        val maska = Mask(this@LoaPuzzleScreen, game.gameAssets.LILIK)
//        addActor(maska)
//        maska.setBounds(407f, 108f, 315f, 315f)
//
//        maska.addAndFillActor(puzzleImg)
//    }
//
//    private fun AdvancedStage.addLevelLbl() {
//        addActor(levelLabel)
//        levelLabel.setBounds(246f, 1726f, 632f, 147f)
//        levelLabel.setAlignment(Align.center)
//    }
//
//    private fun AdvancedStage.addTimer() {
//        addActor(timer)
//        timer.setBounds(212f, 1506f, 666f, 90f)
//    }
//
//}