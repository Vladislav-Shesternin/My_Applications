package aiebu.kakono.tutokazalos.soloha

import aiebu.kakono.tutokazalos.soloha.pisoha.manija.AdvancedGroup
import aiebu.kakono.tutokazalos.soloha.pisoha.front.AdvancedScreen
import aiebu.kakono.tutokazalos.soloha.pisoha.front.CharType
import aiebu.kakono.tutokazalos.soloha.pisoha.front.FontPath
import aiebu.kakono.tutokazalos.soloha.pisoha.front.setCharacters
import aiebu.kakono.tutokazalos.soloha.pisoha.front.setLinear
import aiebu.kakono.tutokazalos.soloha.pisoha.front.setSize
import aiebu.kakono.tutokazalos.soloha.pisoha.numStr
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