package seville.vontecarlo.chocolatequiz.game.actors

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import seville.vontecarlo.chocolatequiz.game.utils.TextureEmpty
import seville.vontecarlo.chocolatequiz.game.utils.advanced.AdvancedGroup
import seville.vontecarlo.chocolatequiz.game.utils.advanced.AdvancedScreen
import seville.vontecarlo.chocolatequiz.game.utils.region

class AAnswerImg(override val screen: AdvancedScreen): AdvancedGroup() {

    private val img = Image(TextureEmpty.region)

    override fun addActorsOnGroup() {

            addAndFillActor(img)
    }

    fun wonkaPr() {
        img.drawable = TextureRegionDrawable(screen.game.assets.falses)
    }

    fun wonkaDa() {
        img.drawable = TextureRegionDrawable(screen.game.assets.trues)
    }

}