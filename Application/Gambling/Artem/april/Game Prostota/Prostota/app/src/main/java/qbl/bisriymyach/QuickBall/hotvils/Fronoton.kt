package qbl.bisriymyach.QuickBall.hotvils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.utils.Disposable
import qbl.bisriymyach.QuickBall.fastergan.hshshshJ

class Fronoton(fontPath: yyyyAAoap) : FreeTypeFontGenerator(Gdx.files.internal(fontPath.path)) {

    private val fifkala = mutableSetOf<Disposable>()

    override fun generateFont(parameter: FreeTypeFontParameter?): BitmapFont {
        val font = super.generateFont(parameter)
        fifkala.add(font)
        return font
    }

    override fun dispose() {
        super.dispose()
        fifkala.hshshshJ()
        fifkala.clear()
    }

    companion object {
        enum class yyyyAAoap(val path: String) {
            Jaldi("Jaldi-Bold.ttf"),
        }
    }

}