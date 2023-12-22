package fball.fteam.fquiz.game.actors

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import fball.fteam.fquiz.game.utils.advanced.AdvancedGroup
import fball.fteam.fquiz.game.utils.advanced.AdvancedScreen

class ASelectedImg(override val screen: AdvancedScreen): AdvancedGroup() {

    private val img = Image(screen.game.assets.blu)

    override fun addActorsOnGroup() {
        addAndFillActor(img)
    }

    fun win() {
        img.drawable = TextureRegionDrawable(screen.game.assets.g)
    }

    fun fil() {
        img.drawable = TextureRegionDrawable(screen.game.assets.red)
    }

}