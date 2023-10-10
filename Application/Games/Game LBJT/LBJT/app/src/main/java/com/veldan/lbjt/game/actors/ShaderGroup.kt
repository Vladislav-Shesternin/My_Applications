package com.veldan.lbjt.game.actors

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.lbjt.game.utils.advanced.AdvancedGroup
import com.veldan.lbjt.game.utils.advanced.AdvancedScreen


class ShaderGroup(override val screen: AdvancedScreen): AdvancedGroup() {

    private val image = Image(screen.game.themeUtil.assets.LANGUAGE_UK)

    override fun addActorsOnGroup() {
        addAndFillActor(image)
    }

// val t = ScreenUtils.getFrameBufferTexture(
//     stage.viewport.screenX,
//     stage.viewport.screenY,
//     stage.viewport.screenWidth,
//     stage.viewport.screenHeight,
// )
// PixmapIO.writePNG(Gdx.files.internal("mypixmap.png"), pixmap, Deflater.DEFAULT_COMPRESSION, true);
// image.drawable = TextureRegionDrawable(Texture())

}