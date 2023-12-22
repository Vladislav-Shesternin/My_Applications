package indiaquiz.winterenter.holiwensday.game.actors

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import indiaquiz.winterenter.holiwensday.game.utils.TextureEmpty
import indiaquiz.winterenter.holiwensday.game.utils.advanced.AdvancedGroup
import indiaquiz.winterenter.holiwensday.game.utils.advanced.AdvancedScreen
import indiaquiz.winterenter.holiwensday.game.utils.region

class AHeppyImg(override val screen: AdvancedScreen): AdvancedGroup() {

    private val img = Image(TextureEmpty.region)

    override fun addActorsOnGroup() {
        addAndFillActor(img)
    }

    fun happy() {
        img.drawable = TextureRegionDrawable(screen.game.assets.ggg)
    }

    fun dehappy() {
        img.drawable = TextureRegionDrawable(screen.game.assets.rrr)
    }

}