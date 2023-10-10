package hekeur.buzzniess.megaglorr.game.utils.font

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter

fun FreeTypeFontParameter.setLinear(): FreeTypeFontParameter {
    minFilter = Texture.TextureFilter.Linear
    magFilter = Texture.TextureFilter.Linear
    return this
}
fun FreeTypeFontParameter.setSize(size: Int): FreeTypeFontParameter {
    this.size = size
    return this
}
fun FreeTypeFontParameter.setCharacters(characters: CharType): FreeTypeFontParameter {
    this.characters = characters.chars
    return this
}
fun FreeTypeFontParameter.setCharacters(chars: String): FreeTypeFontParameter {
    this.characters = chars
    return this
}

enum class CharType(val chars: String) {
    SYMBOLS ("\"!`?'.,;:()[]{}<>|/@\\^\$€-%+=#_&~*"),
    NUMBERS ("1234567890"),
    LATIN   ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"),
    CYRILLIC("АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя"),
    ALL     (SYMBOLS.chars.plus(NUMBERS.chars).plus(CYRILLIC.chars).plus(LATIN.chars))
}

object FontPath {
    const val ExtraBold = "font/Inter-ExtraBold.ttf"
    const val Regular   = "font/Inter-Regular.ttf"
    const val SemiBold  = "font/Inter-SemiBold.ttf"
}