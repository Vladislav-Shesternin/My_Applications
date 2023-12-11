package lycky.fortune.tiger.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import lycky.fortune.tiger.game.actors.button.AButton
import lycky.fortune.tiger.game.screens.ManyToysScreen
import lycky.fortune.tiger.game.utils.actor.disable
import lycky.fortune.tiger.game.utils.actor.setOnClickListener
import lycky.fortune.tiger.game.utils.advanced.AdvancedGroup
import lycky.fortune.tiger.game.utils.advanced.AdvancedScreen

class AResult(override val screen: AdvancedScreen): AdvancedGroup() {

    private val menu     = AButton(screen, AButton.Static.Type.MENU)

    private val result = Image()

    override fun addActorsOnGroup() {
        addAndFillActor(Image(screen.drawerUtil.getRegion(Color.valueOf("9709DA").apply { a = 0.57f })))

        addActor(menu)
        menu.setBounds(16f, 1693f, 227f, 227f)
        menu.setOnClickListener { screen.game.navigationManager.back() }

        addResult()
        addBtns()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun addResult() {
        addActor(result)
        result.setBounds(71f, 449f, 938f, 925f)
    }

    private fun addBtns() {
        val restart = Actor()
        addActor(restart)
        restart.apply {
            setBounds(367f, 441f, 344f, 341f)
            setOnClickListener { screen.game.navigationManager.navigate(ManyToysScreen::class.java.name) }
        }
    }

    fun updateAndShow(isgreeat: Boolean) {
        result.drawable = TextureRegionDrawable(if (isgreeat) screen.game.gameAssets.GREEAT else screen.game.gameAssets.BAD)
        if (isgreeat) screen.game.soundUtil.apply { play(bonus) } else screen.game.soundUtil.apply { play(bad) }
    }

}