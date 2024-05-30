//package com.roshevasternin.rozval.game.screens
//
//import com.roshevasternin.rozval.game.GDXGame
//import com.roshevasternin.rozval.game.actors.ATutorials
//import com.roshevasternin.rozval.game.actors.panel.APanelSettings
//import com.roshevasternin.rozval.game.utils.*
//import com.roshevasternin.rozval.game.utils.actor.*
//import com.roshevasternin.rozval.game.utils.advanced.AdvancedScreen
//import com.roshevasternin.rozval.game.utils.advanced.AdvancedStage
//import kotlinx.coroutines.launch
//
//class SettingsScreen(override val game: GDXGame): AdvancedScreen() {
//
//    private val panelSettings = APanelSettings(this).apply { color.a = 0f }
//    private val tutorials     = ATutorials(this).apply { color.a = 0f }
//
//    override fun show() {
//        setBackBackground(game.assetsLoader.background_purple.region)
//        super.show()
//    }
//
//    override fun AdvancedStage.addActorsOnStageUI() {
//        coroutine?.launch {
//            runGDX {
//                addPanel()
//                if (game.isTutorialsUtil.isTutorials) addTutorials()
//            }
//            animShowPanelSuspend(panelSettings)
//            if (game.isTutorialsUtil.isTutorials) tutorials.animShowSuspend(TIME_ANIM_SCREEN_ALPHA)
//        }
//    }
//
//    override fun hideScreen(block: Block) {
//        coroutine?.launch {
//            if (game.isTutorialsUtil.isTutorials) tutorials.animHideSuspend(TIME_ANIM_SCREEN_ALPHA)
//            animHidePanelSuspend(panelSettings) { block.invoke() }
//        }
//    }
//
//    private fun AdvancedStage.addPanel() {
//        addActor(panelSettings)
//        panelSettings.setBounds(0f, -HEIGHT_UI, WIDTH_UI, HEIGHT_UI)
//
//        panelSettings.apply {
//            collectMusicSound()
//            backBtn.setOnClickListener {
//                hideScreen {
//                    if (screen.game.isTutorialsUtil.isTutorials) ATutorials.nextStep()
//                    screen.game.navigationManager.back()
//                }
//            }
//        }
//    }
//
//    private fun AdvancedStage.addTutorials() {
//        addActor(tutorials)
//        tutorials.setSize(WIDTH_UI, HEIGHT_UI)
//
//        if (game.isTutorialsUtil.isTutorials) {
//            when (ATutorials.STEP) {
//                ATutorials.Static.Step.SettingsMusic -> {
//                    panelSettings.apply {
//                        children.onEach { it.disable() }
//                        musicProgress.enable()
//                    }
//                }
//                else -> {}
//            }
//        }
//    }
//
//    // Logic ------------------------------------------------------------------------
//
//    private fun APanelSettings.collectMusicSound() {
//        var musicCounter = 0
//        var soundCounter = 0
//
//        coroutine?.launch {
//            launch {
//                musicProgress.progressPercentFlow.collect {
//                    musicCounter++
//                    if (musicCounter == 2) {
//                        soundProgress.enable()
//                        tutorials.toSettingsSound()
//                    }
//                }
//            }
//            launch {
//                soundProgress.progressPercentFlow.collect {
//                    soundCounter++
//                    if (soundCounter == 2) {
//                        backBtn.enable()
//                        tutorials.toSettingsBack()
//                    }
//                }
//            }
//        }
//    }
//
//}