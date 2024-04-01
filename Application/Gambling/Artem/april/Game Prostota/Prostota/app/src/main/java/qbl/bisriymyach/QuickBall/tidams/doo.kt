package qbl.bisriymyach.QuickBall.tidams

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter

class doo : FreeTypeFontParameter() {

    init {
        setLinear()
    }

    fun setLinear(): doo {
        minFilter = Texture.TextureFilter.Linear
        magFilter = Texture.TextureFilter.Linear
        return this
    }
    fun setSize(size: Int): doo {
        this.size = size
        return this
    }
    fun setCharacters(characters: CharType): doo {
        this.characters = characters.chars
        return this
    }
    fun setCharacters(chars: String): doo {
        this.characters = chars
        return this
    }

    enum class CharType(val chars: String) {
        SYMBOLS       ("\"!`?'•.,;:()[]{}<>|/@\\^\$€—%-+=#_&~*’…«»❤"                      ),
        NUMBERS       ("1234567890"                                                        ),
        LATIN         ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"              ),
        CYRILLIC      ("АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЄЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэєюяІЇії"),

        LATIN_CYRILLIC(LATIN.chars.plus(CYRILLIC.chars)                                         ),
        ALL           (SYMBOLS.chars.plus(NUMBERS.chars).plus(LATIN.chars).plus(CYRILLIC.chars) ),
    }

}