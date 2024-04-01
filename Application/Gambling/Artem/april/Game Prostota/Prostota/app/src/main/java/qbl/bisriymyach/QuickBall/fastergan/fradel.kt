package qbl.bisriymyach.QuickBall.fastergan

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Disposable
import space.earlygrey.shapedrawer.ShapeDrawer

class fradel(batch: Batch): Disposable {

    private val frundel
                = mutableSetOf<Disposable>()

    val drawer








    = ShapeDrawer(batch, gggssgsg())

    override fun dispose() {
        frundel.hshshshJ()
    }

    fun update() {
        drawer.update()
    }

    fun gggssgsg(color: Color = Color.WHITE): TextureRegion {
        val pixmap = Pixmap(1, 1, Pixmap.Format.RGBA8888)



                  pixmap.setColor(color)



                  pixmap.drawPixel(0, 0)

        val baba = Texture(pixmap)
        frundel.add(baba)

        pixmap.dispose()
        return TextureRegion(baba, 0, 0, 1, 1)
    }

}