package aiebu.kakono.tutokazalos.game

import aiebu.kakono.tutokazalos.game.utils.advanced.AdvancedGroup
import aiebu.kakono.tutokazalos.game.utils.advanced.AdvancedScreen
import aiebu.kakono.tutokazalos.game.utils.font.CharType
import aiebu.kakono.tutokazalos.game.utils.font.FontPath
import aiebu.kakono.tutokazalos.game.utils.font.setCharacters
import aiebu.kakono.tutokazalos.game.utils.font.setLinear
import aiebu.kakono.tutokazalos.game.utils.font.setSize
import aiebu.kakono.tutokazalos.game.utils.numStr
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align

class Itema(override val screen: AdvancedScreen): AdvancedGroup() {

    private val generatorRG = FreeTypeFontGenerator(Gdx.files.internal(FontPath.RG))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()

    private val font31 = generatorRG.generateFont(parameter.setCharacters(CharType.ALL).setSize(31))

    private val listN = listOf(
        "Зарплата",
        "Ресторан",
        "Такси",
        "Спорт",
        "Кофе",
        "Такси",
        "Велопрокат",
        "Продукты",
    )

    private val vertik = Image(screen.game.spriteUtil.itemList.random())
    private val labelk = Label(listN.random(), Label.LabelStyle(font31, Color.WHITE))
    private val ladebk = Label("${numStr(10,88,1)} ${numStr(0,9,3)}.${numStr(0,9,2)}", Label.LabelStyle(font31, Color.valueOf("27AE1C"))).apply { setAlignment(Align.right) }

    override fun addActorsOnGroup() {
        addActors(vertik, labelk, ladebk)
        vertik.setBounds(0f, 0f, 71f, 71f)
        labelk.setBounds(93f, 24f, 317f, 23f)
        ladebk.setBounds(449f, 24f, 187f, 23f)
    }

    override fun dispose() {
        generatorRG.dispose()
        font31.dispose()
        super.dispose()
    }

}