package plinko.testyouluck.com.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import plinko.testyouluck.com.game.screens.PlinkoGameScreen
import plinko.testyouluck.com.game.screens.PlinkoShopScreen
import plinko.testyouluck.com.game.utils.TIME_ANIM_SCREEN_ALPHA
import plinko.testyouluck.com.game.utils.actor.animHide
import plinko.testyouluck.com.game.utils.actor.setOnClickListener
import plinko.testyouluck.com.game.utils.advanced.AdvancedGroup
import plinko.testyouluck.com.game.utils.advanced.AdvancedScreen
import plinko.testyouluck.com.game.utils.advanced.AdvancedStage
import plinko.testyouluck.com.game.utils.font.FontParameter

class AResultGroup(override val screen: AdvancedScreen): AdvancedGroup() {

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars + "x")
    private val font146       = screen.fontGeneratorDefault.generateFont(fontParameter.setSize(146))
    private val font73        = screen.fontGeneratorDefault.generateFont(fontParameter.setSize(73))

    private val xxLbl   = Label("x" + listOf(5,10,15,25,50,75,100).random().toString(), Label.LabelStyle(font146, Color.valueOf("23FD00")))
    private val coinLbl = Label("0", Label.LabelStyle(font73, Color.valueOf("FFF6D9")))


    override fun addActorsOnGroup() {
        addAndFillActor(Image(screen.game.gameAssets.PLINKO_RESULT))
        addBackMenu()
        addXX()
        addCoin()
        addBtns()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun addBackMenu() {
        val menuBtn = Image(screen.game.gameAssets.MENU_BTN)
        addActor(menuBtn)
        menuBtn.setBounds(47f, 1785f, 113f, 103f)

        menuBtn.setOnClickListener(screen.game.soundUtil) {
            screen.stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { screen.game.navigationManager.back() }
        }
    }

    private fun addXX() {
        addActor(xxLbl)
        xxLbl.setBounds(458f, 920f, 417f, 167f)
    }

    private fun addCoin() {
        addActor(coinLbl)
        coinLbl.setBounds(486f, 690f, 229f, 83f)
    }

    private fun addBtns() {
        val next = Actor()
        val shop = Actor()

        addActors(next, shop)

        next.apply {
            setBounds(228f, 426f, 257f, 136f)
            setOnClickListener(screen.game.soundUtil) {
                screen.stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { screen.game.navigationManager.navigate(PlinkoGameScreen::class.java.name) }
            }
        }
        shop.apply {
            setBounds(596f, 426f, 257f, 136f)
            setOnClickListener(screen.game.soundUtil) {
                screen.stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { screen.game.navigationManager.navigate(PlinkoShopScreen::class.java.name) }
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun updateCoin(coins: Int) {
        coinLbl.setText(coins)
    }

}