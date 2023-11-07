package mst.mysteryof.egyptest.game.screens

import mst.mysteryof.egyptest.game.LibGDXGame
import mst.mysteryof.egyptest.game.actors.button.AButton
import mst.mysteryof.egyptest.game.utils.TIME_ANIM_SCREEN_ALPHA
import mst.mysteryof.egyptest.game.utils.actor.animHide
import mst.mysteryof.egyptest.game.utils.actor.animShow
import mst.mysteryof.egyptest.game.utils.advanced.AdvancedScreen
import mst.mysteryof.egyptest.game.utils.advanced.AdvancedStage
import mst.mysteryof.egyptest.game.utils.region

class MenuScreen(override val game: LibGDXGame): AdvancedScreen() {

//    private val parameter = FontParameter().setCharacters("PlayExit").setSize(50)
//    private val font      = fontGeneratorArial_Black.generateFont(parameter)

    // Actor
//    private val btnPlay = AButtonText(this, "Play", AButton.Type.DEFAULT, Label.LabelStyle(font, GameColor.text))
//    private val btnExit = AButtonText(this, "Exit", AButton.Type.DEFAULT, Label.LabelStyle(font, GameColor.text))

    private val btnPl = AButton(this, AButton.Type.PL)
    private val btnEx = AButton(this, AButton.Type.EX)

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtilSplash.BACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)

    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addPlay()
        addExit()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addPlay() {
        addActor(btnPl)
        btnPl.apply {
            setBounds(222f, 750f, 637f, 176f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(GameScreen::class.java.name, this::class.java.name) } }
        }
    }

    private fun AdvancedStage.addExit() {
        addActor(btnEx)
        btnEx.apply {
            setBounds(222f, 506f, 637f, 176f)
            setOnClickListener { game.navigationManager.exit() }
        }
    }

}