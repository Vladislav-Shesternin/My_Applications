package pro.ultimate.saver.game

import pro.ultimate.saver.game.utils.advanced.AdvancedGroup
import pro.ultimate.saver.game.utils.advanced.AdvancedScreen
import pro.ultimate.saver.game.utils.font.CharType
import pro.ultimate.saver.game.utils.font.FontPath
import pro.ultimate.saver.game.utils.font.setCharacters
import pro.ultimate.saver.game.utils.font.setLinear
import pro.ultimate.saver.game.utils.font.setSize
import pro.ultimate.saver.game.utils.numStr
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import java.util.Random

class Bubeka(override val screen: AdvancedScreen): AdvancedGroup() {

    private val generatorSB = FreeTypeFontGenerator(Gdx.files.internal(FontPath.SB))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()

    private val font14 = generatorSB.generateFont(parameter.setCharacters(CharType.ALL).setSize(14))

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
        "Зарплата",
        "Путишествия",
        "Такси",
        "Еда",
        "Спорт",
        "Кофе",
        "Прогулка",
        "Путишествие",
        "Образование",
        "Кулинария",
        "Медицина",
        "Здоровье",
        "Ремонт",
        "Акции",
        "Облигации",
        "Инвестиции",
    )

    private val ava = Image(screen.game.spriteUtil.shtukiList.random())
    private val nadpisa = Label(listN.random(), Label.LabelStyle(font14, Color.WHITE))

    private val randim = Random().nextBoolean()

    private val cena = Label((if (randim) "+" else "-")+numStr(1000,9999, 1)+"Q", Label.LabelStyle(font14, if (randim) Color.valueOf("10B982") else Color.valueOf("FE7373"))).apply { setAlignment(Align.right) }

    override fun addActorsOnGroup() {
        addActors(ava, nadpisa, cena)
        ava.setBounds(0f, 0f, 45f, 45f)
        nadpisa.setBounds(53f, 17f, 202f, 10f)
        cena.setBounds(264f, 17f, 57f, 10f)
    }

    override fun dispose() {
        generatorSB.dispose()
        font14.dispose()
        super.dispose()
    }

}