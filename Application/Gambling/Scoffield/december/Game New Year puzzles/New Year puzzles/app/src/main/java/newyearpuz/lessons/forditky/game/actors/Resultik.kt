package newyearpuz.lessons.forditky.game.actors

import com.badlogic.gdx.scenes.scene2d.ui.Image
import newyearpuz.lessons.forditky.game.utils.advanced.AdvancedGroup
import newyearpuz.lessons.forditky.game.utils.advanced.AdvancedScreen

class Resultik(override val screen: AdvancedScreen): AdvancedGroup() {

    override fun addActorsOnGroup() {
        addAndFillActor(Image(screen.game.gameAssets.back))
        val win = Image(screen.game.gameAssets.win)
        addActor(win)
        win.setBounds(68f, 488f, 944f, 944f)
    }

}