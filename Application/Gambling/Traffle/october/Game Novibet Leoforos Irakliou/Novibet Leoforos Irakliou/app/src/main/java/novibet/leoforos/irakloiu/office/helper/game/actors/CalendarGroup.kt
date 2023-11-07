package novibet.leoforos.irakloiu.office.helper.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import novibet.leoforos.irakloiu.office.helper.game.actors.checkbox.ACheckBox
import novibet.leoforos.irakloiu.office.helper.game.utils.actor.animHide
import novibet.leoforos.irakloiu.office.helper.game.utils.actor.animShow
import novibet.leoforos.irakloiu.office.helper.game.utils.actor.disable
import novibet.leoforos.irakloiu.office.helper.game.utils.actor.setOnClickListener
import novibet.leoforos.irakloiu.office.helper.game.utils.advanced.AdvancedGroup
import novibet.leoforos.irakloiu.office.helper.game.utils.advanced.AdvancedScreen
import novibet.leoforos.irakloiu.office.helper.game.utils.font.FontParameter

class CalendarGroup(override val screen: AdvancedScreen) : AdvancedGroup() {

    private val parameter = FontParameter().setCharacters(FontParameter.CharType.LATIN_NUMBERS).setSize(25)
    private val font25    = screen.fontGeneratorInter_Black.generateFont(parameter)
    private val font14    = screen.fontGeneratorInter_Black.generateFont(parameter)

    private val dateList = listOf(
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
    private val dateLbl = Label(dateList.random() + " 2023", Label.LabelStyle(font25, Color.BLACK))
    private val cbs     = mutableListOf<ACheckBox>()

    override fun addActorsOnGroup() {
        addAndFillActor(Image(screen.game.spriteUtil.CALENDAR))
        addDateLbl()
        addCells()

        val btnLeftRight = Actor()
        addActor(btnLeftRight)
        btnLeftRight.apply {
            setBounds(392f, 444f, 105f, 48f)
            setOnClickListener { dateLbl.setText(dateList.random() + " 2023") }
        }
    }

    private fun addDateLbl() {
        addActor(dateLbl)
        dateLbl.setBounds(31f, 453f, 361f, 31f)
    }

    private fun addCells() {
        var nx = 31.6f
        var ny = 297.8f

        repeat(31) {
            val cb = ACheckBox(screen, ACheckBox.Type.DATE)
            val lb = Label(it.inc().toString(), Label.LabelStyle(font14, Color.WHITE))

            cbs.add(cb)

            addActors(cb, lb)
            cb.setBounds(nx, ny, 67.5f, 67.5f)
            lb.setBounds(nx, ny, 67.5f, 67.5f)
            nx += (67.5f - 1f)

            if (it.inc() % 7 == 0) {
                nx = 31.6f
                ny -= (67.5f - 1f)
            }

            lb.apply {
                disable()
                setAlignment(Align.center)
                animHide()
            }
            cb.setOnCheckListener { isCheck -> if (isCheck) lb.animShow() else lb.animHide() }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun update() {
        dateLbl.setText(dateList.random() + " 2023")
        cbs.onEach { it.uncheck() }
    }

}