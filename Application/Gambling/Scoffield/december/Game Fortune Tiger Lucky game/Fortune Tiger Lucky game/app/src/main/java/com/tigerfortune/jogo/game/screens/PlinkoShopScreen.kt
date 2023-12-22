//package com.tigerfortune.jogo.game.screens
//
//import com.badlogic.gdx.graphics.Color
//import com.badlogic.gdx.scenes.scene2d.Actor
//import com.badlogic.gdx.scenes.scene2d.ui.Image
//import com.badlogic.gdx.scenes.scene2d.ui.Label
//import com.tigerfortune.jogo.game.LibGDXGame
//import com.tigerfortune.jogo.game.actors.button.AButton
//import com.tigerfortune.jogo.game.box2d.bodies.BallType
//import com.tigerfortune.jogo.game.box2d.bodies.StaticBallType
//import com.tigerfortune.jogo.game.box2d.bodies.StaticIsRed
//import com.tigerfortune.jogo.game.utils.TIME_ANIM_SCREEN_ALPHA
//import com.tigerfortune.jogo.game.utils.actor.animHide
//import com.tigerfortune.jogo.game.utils.actor.animShow
//import com.tigerfortune.jogo.game.utils.actor.setOnClickListener
//import com.tigerfortune.jogo.game.utils.advanced.AdvancedScreen
//import com.tigerfortune.jogo.game.utils.advanced.AdvancedStage
//import com.tigerfortune.jogo.game.utils.font.FontParameter
//import com.tigerfortune.jogo.game.utils.region
//
//var StaticAllCoin = 0
//
//class PlinkoShopScreen(override val game: LibGDXGame) : AdvancedScreen() {
//
//    private val assets = game.gameAssets
//
//    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS).setSize(50)
//    private val font          = fontGeneratorDefault.generateFont(fontParameter)
//
//    // Actor
//    private val coinPanelImg = Image(assets.COINS_BAR)
//    private val coinLbl      = Label(StaticAllCoin.toString(), Label.LabelStyle(font, Color.WHITE))
//
//    override fun show() {
//        stageUI.root.animHide()
//        setBackgrounds(game.splashAssets.PLINKO_BACKGROUND.region)
//        super.show()
//        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
//    }
//
//    override fun AdvancedStage.addActorsOnStageUI() {
//        addBackMenu()
//        addCoinPanel()
//        addShop()
//        addPlay()
//        addItems()
//    }
//
//    // ------------------------------------------------------------------------
//    // Add Actors
//    // ------------------------------------------------------------------------
//
//    private fun AdvancedStage.addBackMenu() {
//        val menuBtn = Image(assets.MENU_BTN)
//        addActor(menuBtn)
//        menuBtn.setBounds(47f, 1785f, 113f, 103f)
//
//        menuBtn.setOnClickListener(game.soundUtil) {
//            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }
//        }
//    }
//
//    private fun AdvancedStage.addCoinPanel() {
//        addActors(coinPanelImg, coinLbl)
//        coinPanelImg.setBounds(419f, 1779f, 243f, 80f)
//        coinLbl.setBounds(520f, 1789f, 98f, 59f)
//    }
//
//    private fun AdvancedStage.addShop() {
//        val shopBar = Image(assets.SHOP_BAR)
//        addActor(shopBar)
//        shopBar.setBounds(42f, 519f, 946f, 1178f)
//    }
//
//    private fun AdvancedStage.addPlay() {
//        val playBtn = AButton(this@PlinkoShopScreen, AButton.Static.Type.PLAY)
//        addActor(playBtn)
//        playBtn.setBounds(245f, 78f, 577f, 154f)
//
//        playBtn.setOnClickListener(game.soundUtil) {
//            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(PlinkoGameScreen::class.java.name) }
//        }
//    }
//
//    private fun AdvancedStage.addItems() {
//        var nx = 42f
//        var ny = 988f
//
//        listOf(
//            4000, 5000, 8500,
//            10_000, 20_000, 25_000
//        ).onEachIndexed { index, cost ->
//            Actor().also { actor ->
//                addActor(actor)
//                actor.apply {
//                    setBounds(nx, ny, 266f, 396f)
//                    nx += 74f+266f
//                    if (index.inc() % 3 == 0) {
//                        nx = 42f
//                        ny -= 73f+396f
//                    }
//
//                    setOnClickListener(game.soundUtil) {
//                        if (StaticAllCoin >= cost) {
//                            game.soundUtil.apply { play(buy) }
//
//                            StaticAllCoin -= cost
//                            coinLbl.setText(StaticAllCoin)
//
//                            when (cost) {
//                                4000   -> StaticIsRed = true
//                                5000   -> StaticBallType = BallType._5000
//                                8500   -> StaticBallType = BallType._8500
//                                10_000 -> StaticBallType = BallType._10000
//                                20_000 -> StaticTime += 60
//                                25_000 -> StaticBombCost = 100
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//    }
//
//}