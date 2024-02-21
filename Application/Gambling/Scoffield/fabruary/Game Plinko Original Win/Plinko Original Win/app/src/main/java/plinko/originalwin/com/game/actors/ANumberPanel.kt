package plinko.originalwin.com.game.actors

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import plinko.originalwin.com.game.utils.actor.animHide
import plinko.originalwin.com.game.utils.actor.setOnClickListener
import plinko.originalwin.com.game.utils.advanced.AdvancedGroup
import plinko.originalwin.com.game.utils.advanced.AdvancedScreen

class ANumberPanel(override val screen: AdvancedScreen): AdvancedGroup() {

    private val assets = screen.game.allAssets

    private val colorfuleImg = Image(assets.state_a)

    override fun addActorsOnGroup() {
        addColorfuleImg()
        addAndFillActor(Image(assets.wins))
    }

    private fun addColorfuleImg() {
        addAndFillActor(colorfuleImg)

        colorfuleImg.addAction(Actions.forever(Actions.sequence(
            Actions.delay(0.4f),
            Actions.run { colorfuleImg.drawable = TextureRegionDrawable(assets.state_b) },
            Actions.delay(0.4f),
            Actions.run { colorfuleImg.drawable = TextureRegionDrawable(assets.state_a) },
        )))
    }

}