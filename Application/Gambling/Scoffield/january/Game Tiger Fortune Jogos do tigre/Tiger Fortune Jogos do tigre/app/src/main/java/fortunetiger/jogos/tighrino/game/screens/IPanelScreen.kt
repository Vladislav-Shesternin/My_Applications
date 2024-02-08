package fortunetiger.jogos.tighrino.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import fortunetiger.jogos.tighrino.game.LibGDXGame
import fortunetiger.jogos.tighrino.game.utils.advanced.AdvancedScreen
import fortunetiger.jogos.tighrino.game.utils.advanced.AdvancedStage
import fortunetiger.jogos.tighrino.game.utils.region

abstract class IPanelScreen(final override val game: LibGDXGame) : AdvancedScreen() {

    val panelImg = Image(game.allAssets.VAL_PANEL)

    override fun show() {
        setBackgrounds(game.loadingAssets.ValBackground.region)
        super.show()
    }

    final override fun AdvancedStage.addActorsOnStageUI() {
        addPanel()
        addActorsOnStage()
    }

    abstract fun AdvancedStage.addActorsOnStage()

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addPanel() {
        addActor(panelImg)
        panelImg.setBounds(90f, 325f, 907f, 1208f)
    }

}