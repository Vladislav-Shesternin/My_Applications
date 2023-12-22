package iconic.duo.hunt.game.screens

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import iconic.duo.hunt.game.actors.button.AButtonStyle
import iconic.duo.hunt.game.actors.button.AButtonText
import iconic.duo.hunt.game.actors.label.ALabelStyle
import iconic.duo.hunt.game.manager.NavigationManager
import iconic.duo.hunt.game.manager.SpriteManager
import iconic.duo.hunt.game.utils.MAIN_ANIM_SPEED
import iconic.duo.hunt.game.utils.actor.setBounds
import iconic.duo.hunt.game.utils.advanced.AdvancedGroup
import iconic.duo.hunt.game.utils.advanced.AdvancedScreen
import iconic.duo.hunt.game.utils.Layout.Menu as LM

class MenuScreen: AdvancedScreen() {

    private val play       = AButtonText("Play", AButtonStyle.btn, ALabelStyle.style(ALabelStyle.Regular._60))
    private val exit       = AButtonText("Exit", AButtonStyle.btn, ALabelStyle.style(ALabelStyle.Regular._60))

    private val btns = listOf(play, exit)


    override fun show() {
        setUIBackground(SpriteManager.SplashRegion.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        mainGroup.addAction(Actions.alpha(0f))
        addButtons()
        mainGroup.addAction(Actions.fadeIn(MAIN_ANIM_SPEED))
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addButtons() {
        addActors(play, exit)

        var ny = LM.buttons.y
        btns.onEach {
            with(LM.buttons) {
                it.setBounds(x, ny, w, h)
                ny -= vs + h
            }
        }

        play.setOnClickListener {

            navToGame() }
        exit.setOnClickListener { NavigationManager.exit() }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun navToGame() {
        mainGroup.addAction(Actions.sequence(
            Actions.fadeOut(MAIN_ANIM_SPEED),
            Actions.run { NavigationManager.navigate(GameScreen(), MenuScreen()) }
        ))
    }

}