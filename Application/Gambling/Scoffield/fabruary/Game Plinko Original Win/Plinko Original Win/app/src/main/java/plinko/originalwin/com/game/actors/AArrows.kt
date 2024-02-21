package plinko.originalwin.com.game.actors

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import plinko.originalwin.com.game.utils.advanced.AdvancedGroup
import plinko.originalwin.com.game.utils.advanced.AdvancedScreen

class AArrows(override val screen: AdvancedScreen): AdvancedGroup() {

    private val assets = screen.game.allAssets

    private val arrowsImg = Image(assets.arrows)

    override fun addActorsOnGroup() {
        addColorfuleImg()
    }

    private fun addColorfuleImg() {
        addAndFillActor(arrowsImg)

        arrowsImg.addAction(Actions.forever(Actions.sequence(
            Actions.alpha(0.3f, 0.4f),
            Actions.fadeIn(0.4f),
        )))
    }

}