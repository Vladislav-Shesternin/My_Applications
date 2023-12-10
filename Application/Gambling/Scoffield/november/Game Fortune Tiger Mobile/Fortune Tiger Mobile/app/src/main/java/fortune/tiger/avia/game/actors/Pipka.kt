package fortune.tiger.avia.game.actors

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import fortune.tiger.avia.game.utils.actor.animHide
import fortune.tiger.avia.game.utils.actor.animShow
import fortune.tiger.avia.game.utils.advanced.AdvancedGroup
import fortune.tiger.avia.game.utils.advanced.AdvancedScreen

class Pipka(override val screen: AdvancedScreen): AdvancedGroup() {

    private val backich = Image().apply { color.a = 0f }
    var id = -1

    override fun addActorsOnGroup() {
        addAndFillActor(Image(screen.game.gameAssets.PIPKA))
        addActor(backich)
        backich.setBounds(-14f, -11f, 320f, 410f)
    }

    fun showYes() {
        backich.clearActions()
        backich.drawable = TextureRegionDrawable(screen.game.gameAssets.TRUSIK)
        backich.animShow(0.2f)
    }

    fun showNot(block: () -> Unit) {
        backich.clearActions()
        backich.drawable = TextureRegionDrawable(screen.game.gameAssets.FALSIK)
        backich.addAction(Actions.sequence(
            Actions.delay(0.7f),
            Actions.parallel(
                Actions.fadeOut(0.3f),
                Actions.run(block),
            )
        ))
    }

    fun hide() {
        backich.clearActions()
        backich.animHide(0.2f) {
            backich.drawable = TextureRegionDrawable(screen.game.gameAssets.TRUSIK)
        }
    }

}