package pro.ultimate.saver.game.manager

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import pro.ultimate.saver.game.manager.checkbox.ACheckBox
import pro.ultimate.saver.game.utils.actor.animHide
import pro.ultimate.saver.game.utils.actor.animShow
import pro.ultimate.saver.game.utils.actor.disable
import pro.ultimate.saver.game.utils.actor.setOnClickListener
import pro.ultimate.saver.game.utils.advanced.AdvancedGroup
import pro.ultimate.saver.game.utils.advanced.AdvancedScreen
import pro.ultimate.saver.game.utils.font.CharType
import pro.ultimate.saver.game.utils.font.FontPath
import pro.ultimate.saver.game.utils.font.setCharacters
import pro.ultimate.saver.game.utils.font.setLinear
import pro.ultimate.saver.game.utils.font.setSize

class CalendarGroup(override val screen: AdvancedScreen) : AdvancedGroup() {

    private val generatorBK = FreeTypeFontGenerator(Gdx.files.internal(FontPath.BK))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()

    private val font16 = generatorBK.generateFont(parameter.setCharacters(CharType.ALL).setSize(16))

    val dateList = listOf(
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December",
    )

    // Actor
    private val dateLbl = Label(dateList.random() + " 2023", Label.LabelStyle(font16, Color.WHITE))
    private val cbs     = mutableListOf<ACheckBox>()

    override fun addActorsOnGroup() {
        addAndFillActor(Image(screen.game.spriteUtil.calendula))
        addDateLbl()
        addCells()

        val btnLeftRight = Actor()
        addActor(btnLeftRight)
        btnLeftRight.apply {
            setBounds(250f, 283f, 67f, 31f)
            setOnClickListener { dateLbl.setText(dateList.random() + " 2023") }
        }
    }

    private fun addDateLbl() {
        addActor(dateLbl)
        dateLbl.setBounds(20f, 288f, 229f, 20f)
    }

    private fun addCells() {
        var nx = 20f
        var ny = 189.5f

        repeat(31) {
            val cb = ACheckBox(screen, ACheckBox.Type.DATE)
            cbs.add(cb)

            addActors(cb)
            cb.setBounds(nx, ny, 43f, 43f)
            nx += (43f - 1f)

            if (it.inc() % 7 == 0) {
                nx = 20f
                ny -= (43f - 1f)
            }

            cb.setOnCheckListener { isCheck -> if (isCheck) {
                animHide(0.5f)
                bbbbblock()
                disable()
                cb.uncheck()
            } }
        }
    }

    var bbbbblock = {}

}