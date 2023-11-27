package plinko.games.mega.game.actors

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import plinko.games.mega.game.utils.advanced.AdvancedGroup
import plinko.games.mega.game.utils.advanced.AdvancedScreen

class AWinStar(override val screen: AdvancedScreen): AdvancedGroup() {

    private val sorce = listOf(
        screen.game.gameAssets.LOST,
        screen.game.gameAssets.NOTBAD,
        screen.game.gameAssets.COOL,
        screen.game.gameAssets.GOOD,
    )

    private val image   = Image(sorce.first())
    val restart = Actor()
    val next    = Actor()

    override fun addActorsOnGroup() {
        addAndFillActor(image)

        addActors(restart, next)
        restart.setBounds(42f, 0f, 358f, 105f)
        next.setBounds(467f, 0f, 358f, 105f)

    }

    fun updateScore(vl: Int) {
        image.drawable = TextureRegionDrawable(sorce[vl])
    }

}