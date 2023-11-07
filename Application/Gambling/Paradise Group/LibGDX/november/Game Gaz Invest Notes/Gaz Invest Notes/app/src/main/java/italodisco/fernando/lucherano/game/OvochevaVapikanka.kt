package italodisco.fernando.lucherano.game

import italodisco.fernando.lucherano.game.utils.advanced.AdvancedGroup
import italodisco.fernando.lucherano.game.utils.advanced.AdvancedScreen
import italodisco.fernando.lucherano.game.utils.font.CharType
import italodisco.fernando.lucherano.game.utils.font.FontPath
import italodisco.fernando.lucherano.game.utils.font.setCharacters
import italodisco.fernando.lucherano.game.utils.font.setLinear
import italodisco.fernando.lucherano.game.utils.font.setSize
import italodisco.fernando.lucherano.game.utils.numStr
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import java.util.Random

class OvochevaVapikanka(override val screen: AdvancedScreen): AdvancedGroup() {

    private val generatorSB = FreeTypeFontGenerator(Gdx.files.internal(FontPath.SB))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()

    private val font23 = generatorSB.generateFont(parameter.setCharacters(CharType.ALL).setSize(23))

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
        "Здоровье",
        "Благотворительность",
        "Зарплата",
        "Путишествия",
        "Такси",
        "Образование",
        "Спорт",
        "Кофе",
        "Прогулка",
        "Путишествие",
        "Кулинария",
        "Медицина",
        "Еда",
        "Ремонт",
        "Акции",
        "Облигации",
        "Инвестиции",
    )

    private val karina = Image(screen.game.spriteUtil.chtetka.random())
    private val nadapo = Label(listN.random(), Label.LabelStyle(font23, Color.WHITE))

    private val randos = Random().nextBoolean()

    private val cenadiy = Label((if (randos) "D +" else "D -")+numStr(1000,9999, 1), Label.LabelStyle(font23, if (randos) Color.valueOf("2EC045") else Color.valueOf("C02E2E"))).apply { setAlignment(Align.right) }

    override fun addActorsOnGroup() {
        addActors(karina, nadapo, cenadiy)
        karina.setBounds(0f, 0f, 73f, 73f)
        nadapo.setBounds(87f, 28f, 286f, 17f)
        cenadiy.setBounds(407f, 28f, 117f, 17f)
    }

    override fun dispose() {
        generatorSB.dispose()
        font23.dispose()
        super.dispose()
    }

}