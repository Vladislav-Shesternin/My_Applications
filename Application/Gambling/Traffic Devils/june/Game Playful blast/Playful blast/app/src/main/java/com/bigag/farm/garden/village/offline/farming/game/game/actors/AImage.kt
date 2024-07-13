package com.bigag.farm.garden.village.offline.farming.game.game.actors

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.bigag.farm.garden.village.offline.farming.game.game.utils.advanced.AdvancedGroup
import com.bigag.farm.garden.village.offline.farming.game.game.utils.advanced.AdvancedScreen

class AImage constructor(override val screen: AdvancedScreen): com.bigag.farm.garden.village.offline.farming.game.game.utils.advanced.AdvancedGroup() {

    private val image = Image()

    var drawable: Drawable = TextureRegionDrawable()
        get() = image.drawable
        set(value) {
            image.drawable = value
            field = value
        }

    constructor(screen: AdvancedScreen, region: TextureRegion) : this(screen) {
        image.drawable = TextureRegionDrawable(region)
    }
    constructor(screen: AdvancedScreen, texture: Texture) : this(screen) {
        image.drawable = TextureRegionDrawable(texture)
    }
    constructor(screen: AdvancedScreen, drawable: Drawable) : this(screen) {
        image.drawable = drawable
    }
    constructor(screen: AdvancedScreen, ninePatch: NinePatch) : this(screen) {
        image.drawable = NinePatchDrawable(ninePatch)
    }

    override fun addActorsOnGroup() {
        addAndFillActor(image)
    }

}