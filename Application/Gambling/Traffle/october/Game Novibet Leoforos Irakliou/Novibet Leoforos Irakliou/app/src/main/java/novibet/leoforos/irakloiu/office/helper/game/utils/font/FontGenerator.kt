package novibet.leoforos.irakloiu.office.helper.game.utils.font

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.utils.Disposable
import novibet.leoforos.irakloiu.office.helper.game.utils.disposeAll

class FontGenerator(fontPath: FontPath): FreeTypeFontGenerator(Gdx.files.internal(fontPath.path)) {

    private val disposableSet = mutableSetOf<Disposable>()

    override fun generateFont(parameter: FreeTypeFontParameter?): BitmapFont {
        val font = super.generateFont(parameter)
        disposableSet.add(font)
        return font
    }

    override fun dispose() {
        super.dispose()
        disposableSet.disposeAll()
        disposableSet.clear()
    }

    enum class FontPath(val path: String) {
        Inter_Black          ("font/Inter-Black.ttf"          ),
        LilitaOne_Regular    ("font/LilitaOne-Regular.ttf"    ),
        LondrinaSolid_Regular("font/LondrinaSolid-Regular.ttf"),
    }

}