package fortune.tiger.avia.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import fortune.tiger.avia.game.LibGDXGame
import fortune.tiger.avia.game.utils.TIME_ANIM_SCREEN_ALPHA
import fortune.tiger.avia.game.utils.actor.animHide
import fortune.tiger.avia.game.utils.actor.setOnClickListener
import fortune.tiger.avia.game.utils.advanced.AdvancedScreen
import fortune.tiger.avia.game.utils.advanced.AdvancedStage
import fortune.tiger.avia.game.utils.region
import java.util.Random

class MenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    override fun show() {
        setBackgrounds(game.gameAssets.MENUIMG.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addMenu()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addMenu() {
        val ecranciki = listOf(
            if (Random().nextBoolean()) IgrabelnaGraScreen::class.java.name else IgrabelnaGraScreen2::class.java.name,
            LeyresesScreen::class.java.name,
            TessingsScreen::class.java.name,
            UlerusScreen::class.java.name,
            "EXIT",
        )

        var ny = 1374f

        ecranciki.onEach { cId ->
            val btn = Actor()
            addActor(btn)
            btn.setBounds(268f, ny, 544f, 137f)
            ny -= 57+137f

            btn.setOnClickListener(game.soundUtil) { navigateGo(cId) }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun navigateGo(id: String) {
        stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
            if (id == "EXIT") game.navigationManager.exit() else game.navigationManager.navigate(id, this::class.java.name)
        }
    }


}