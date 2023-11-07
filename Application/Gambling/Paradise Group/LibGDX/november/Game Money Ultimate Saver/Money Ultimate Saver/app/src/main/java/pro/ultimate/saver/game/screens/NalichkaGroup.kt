package pro.ultimate.saver.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.utils.Align
import pro.ultimate.saver.game.VerticalGroup
import pro.ultimate.saver.game.manager.checkbox.ACheckBox
import pro.ultimate.saver.game.utils.actor.animHide
import pro.ultimate.saver.game.utils.actor.disable
import pro.ultimate.saver.game.utils.actor.setOnClickListener
import pro.ultimate.saver.game.utils.advanced.AdvancedGroup
import pro.ultimate.saver.game.utils.advanced.AdvancedScreen
import pro.ultimate.saver.game.utils.font.CharType
import pro.ultimate.saver.game.utils.font.FontPath
import pro.ultimate.saver.game.utils.font.setCharacters
import pro.ultimate.saver.game.utils.font.setLinear
import pro.ultimate.saver.game.utils.font.setSize

class NalichkaGroup(override val screen: AdvancedScreen) : AdvancedGroup() {

    private val generatorBK = FreeTypeFontGenerator(Gdx.files.internal(FontPath.BK))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()

    private val font16 = generatorBK.generateFont(parameter.setCharacters(CharType.ALL).setSize(16))

    private val vertg = VerticalGroup(screen, 11f, alignment = VerticalGroup.Alignment.TOP, direction = VerticalGroup.Direction.DOWN)
    private val scrpa = ScrollPane(vertg)

    val zapList = listOf(
        "Наличка",
        "Криптовалюта",
        "Облигации",
        "Кредит",
        "Акции",
        "Предоплата",
        "Аванс",
        "Карта",
        "Наличка",
        "Криптовалюта",
        "Облигации",
        "Кредит",
        "Акции",
        "Предоплата",
        "Аванс",
        "Карта",
    ).shuffled()

    // Actor
    private val dateLbl = List(zapList.size) { Label(zapList[it], Label.LabelStyle(font16, Color.WHITE)) }

    override fun addActorsOnGroup() {
        addAndFillActor(Image(screen.game.spriteUtil.ramor))
        addActor(scrpa)
        scrpa.setBounds(14f, 21f, 310f, 299f)

        dateLbl.onEach {
            vertg.addActor(it.apply {
                setAlignment(Align.center)
                setSize(310f, 20f)
                setOnClickListener {
                    this@NalichkaGroup.animHide(0.5f)
                    bbbbblock()
                    this@NalichkaGroup.disable()
                }
            })
        }
    }

    var bbbbblock = {}

}