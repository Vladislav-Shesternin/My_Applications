package mst.mysteryof.antientegyptua.game.screens

import mst.mysteryof.antientegyptua.game.LibGDXGame
import mst.mysteryof.antientegyptua.game.actors.button.AButton
import mst.mysteryof.antientegyptua.game.utils.TIME_ANIM_SCREEN_ALPHA
import mst.mysteryof.antientegyptua.game.utils.actor.animHide
import mst.mysteryof.antientegyptua.game.utils.actor.animShow
import mst.mysteryof.antientegyptua.game.utils.advanced.AdvancedScreen
import mst.mysteryof.antientegyptua.game.utils.advanced.AdvancedStage
import mst.mysteryof.antientegyptua.game.utils.region

class MenuScreen(override val game: LibGDXGame): AdvancedScreen() {

//    private val parameter = FontParameter().setCharacters("PlayExit").setSize(50)
//    private val font      = fontGeneratorArial_Black.generateFont(parameter)

    // Actor
//    private val btnPlay = AButtonText(this, "Play", AButton.Type.DEFAULT, Label.LabelStyle(font, GameColor.text))
//    private val btnExit = AButtonText(this, "Exit", AButton.Type.DEFAULT, Label.LabelStyle(font, GameColor.text))

    private val btnPl = AButton(this, AButton.Type.PL)
    private val btnEx = AButton(this, AButton.Type.EX)

    companion object {
        const val declineKey = "viveC4nzZ/19F0ln3YCvUYGIb8vnnJ4Rlq1sQ2X3hAI="
    }

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
            setBounds(464f, 507f, 524f, 104f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(GameScreen::class.java.name, this::class.java.name) } }
        }
    }

    private fun AdvancedStage.addExit() {
        addActor(btnEx)
        btnEx.apply {
            setBounds(464f, 364f, 524f, 104f)
            setOnClickListener { game.navigationManager.exit() }
        }
    }

}