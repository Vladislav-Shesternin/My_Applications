package investgroup.program.gaz.game

import investgroup.program.gaz.game.utils.advanced.AdvancedGroup
import investgroup.program.gaz.game.utils.advanced.AdvancedScreen
import investgroup.program.gaz.game.utils.font.CharType
import investgroup.program.gaz.game.utils.font.FontPath
import investgroup.program.gaz.game.utils.font.setCharacters
import investgroup.program.gaz.game.utils.font.setLinear
import investgroup.program.gaz.game.utils.font.setSize
import investgroup.program.gaz.game.utils.numStr
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import java.util.Random

class Itema(override val screen: AdvancedScreen): AdvancedGroup() {

    private val generatorSB = FreeTypeFontGenerator(Gdx.files.internal(FontPath.SB))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()

    private val font27 = generatorSB.generateFont(parameter.setCharacters(CharType.ALL).setSize(27))

    private val listN = listOf(
        "Ресторан",
        "Зарплата",
        "Спорт",
        "Такси",
        "Такси",
        "Кофе",
        "Продукты",
        "Велопрокат",
        "Путишествие",
        "Инвестиции",
        "Благотворительность",
    )

    private val ava = Image(screen.game.spriteUtil.znakiList.random())
    private val nadpisa = Label(listN.random(), Label.LabelStyle(font27, Color.BLACK))

    private val randim = Random().nextBoolean()

    private val cena = Label((if (randim) "+" else "-")+numStr(1000,9999, 1)+"P", Label.LabelStyle(font27, if (randim) Color.valueOf("10B982") else Color.valueOf("FE7373"))).apply { setAlignment(Align.right) }

    override fun addActorsOnGroup() {
        addActors(ava, nadpisa, cena)
        ava.setBounds(0f, 0f, 88f, 88f)
        nadpisa.setBounds(104f, 34f, 362f, 20f)
        cena.setBounds(490f, 34f, 136f, 20f)
    }

    override fun dispose() {
        generatorSB.dispose()
        font27.dispose()
        super.dispose()
    }

}