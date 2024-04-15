package qbl.bisriymyach.QuickBall

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import qbl.bisriymyach.QuickBall.fastergan.suchka
import qbl.bisriymyach.QuickBall.sudams.Oi_oi_uoi

class malto constructor(override val screen: suchka) : Oi_oi_uoi() {

    private val image = Image()

    constructor(screen: suchka, region: TextureRegion) : this(screen) {
        image.drawable = TextureRegionDrawable(region)
    }

    override fun addActorsOnGroup() {
        addAndFillActor(image)
    }

}