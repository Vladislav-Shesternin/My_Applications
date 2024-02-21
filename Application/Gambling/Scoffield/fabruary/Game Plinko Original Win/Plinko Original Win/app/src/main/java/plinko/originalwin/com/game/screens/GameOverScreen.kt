package plinko.originalwin.com.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import plinko.originalwin.com.game.LibGDXGame
import plinko.originalwin.com.game.utils.TIME_ANIM_ALPHA
import plinko.originalwin.com.game.utils.actor.animHide
import plinko.originalwin.com.game.utils.actor.animShow
import plinko.originalwin.com.game.utils.actor.setOnClickListener
import plinko.originalwin.com.game.utils.advanced.AdvancedScreen
import plinko.originalwin.com.game.utils.advanced.AdvancedStage
import plinko.originalwin.com.game.utils.font.FontParameter
import plinko.originalwin.com.game.utils.runGDX

class GameOverScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars + "$").setSize(110)
    private val font          = fontGenerator_ZenDots_Regular.generateFont(fontParameter)

    private val balanceLbl = Label("", Label.LabelStyle(font, Color.WHITE))

    override fun show() {
        stageUI.root.animHide()
        super.show()
        stageUI.root.animShow(TIME_ANIM_ALPHA)
        game.soundUtil.apply { play(sound_result) }
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addPanel()
        addMenu()
        addBalanceLbl()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addPanel() {
        val panel = Image(game.allAssets.GAME_OVER)
        addActor(panel)
        panel.setBounds(0f, 240f, 1080f, 1440f)
    }

    private fun AdvancedStage.addMenu() {
        val play = Actor()
        addActor(play)
        play.setBounds(359f, 566f, 362f, 125f)

        play.setOnClickListener(game.soundUtil) {
            stageUI.root.animHide(TIME_ANIM_ALPHA) {
                game.navigationManager.back()
            }
        }
    }

    private fun AdvancedStage.addBalanceLbl() {
        addActor(balanceLbl)
        balanceLbl.apply {
            setBounds(242f, 886f, 596f, 149f)
            setAlignment(Align.center)
        }

        coroutine?.launch {
            var counter = 0
            while (isActive && counter < GameScreen.balance) {
                counter += 1
                runGDX { balanceLbl.setText("$$counter") }
                delay((3..7L).random())
            }
        }
    }

}