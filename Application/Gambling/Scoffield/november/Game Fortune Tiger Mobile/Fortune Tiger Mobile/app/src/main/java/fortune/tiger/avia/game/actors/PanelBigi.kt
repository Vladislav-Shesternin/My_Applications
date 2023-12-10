package fortune.tiger.avia.game.actors

import com.badlogic.gdx.scenes.scene2d.ui.Image
import fortune.tiger.avia.game.utils.advanced.AdvancedGroup
import fortune.tiger.avia.game.utils.advanced.AdvancedScreen

class PanelBigi(override val screen: AdvancedScreen): AdvancedGroup() {

    override fun addActorsOnGroup() {
        addAndFillActor(Image(screen.game.gameAssets.PANEL_BIG))
    }

}