package uniwersal.pictures.present.game.actors.scroll

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.utils.Align
import uniwersal.pictures.present.game.screens.PulshjeScreen
import uniwersal.pictures.present.game.utils.Ttime
import uniwersal.pictures.present.game.utils.actor.animHide
import uniwersal.pictures.present.game.utils.actor.setOnTouchListener
import uniwersal.pictures.present.game.utils.advanced.AdvancedGroup
import uniwersal.pictures.present.game.utils.advanced.AdvancedScreen
import uniwersal.pictures.present.game.utils.numStr

class BabloItem(
    override val screen: AdvancedScreen,
    labelStyle1: LabelStyle,
    labelStyle2: LabelStyle,
    labelStyle3: LabelStyle,
): AdvancedGroup() {

    private val iconkaa = Image(screen.game.spriteUtil.teterkaList.random())
    private val nameLbl = Label(BABLO.random(), labelStyle1)
    private val costLbl = Label("R"+"${numStr(10,90,1)},${numStr(10,99,1)}", labelStyle2).apply { setAlignment(Align.right) }
    private val percLbl = Label("+${numStr(5,40,1)}.${numStr(0,9,2)}", labelStyle3).apply { setAlignment(Align.right) }

    override fun addActorsOnGroup() {
        addActors(iconkaa, nameLbl, costLbl, percLbl)
        iconkaa.setBounds(0f, 27f, 80f, 80f)
        nameLbl.setBounds(112f, 42f, 129f, 49f)
        costLbl.setBounds(434f, 63f, 208f, 49f)
        percLbl.setBounds(462f, 22f, 180f, 33f)

        setOnTouchListener {
            screen.stageUI.root.animHide(Ttime) {
                screen.game.navigationManager.navigate(PulshjeScreen::class.java.name, screen::class.java.name)
            }
        }
    }



}


val BABLO = listOf(
    "RUB",
    "USD",
    "UAH",
    "PHP",
    "CRC",
    "BRL",
    "AUD",
    "CVC",
    "DVD",
    "PLH",
    "CSH",
    "POX",
    "ROT",
    "LOG",
)

val BABLO_NAMKA = listOf(
    "Российский рубль",
    "Голандский феник",
    "Парадский лод",
    "Миховский токч",
    "Рановый сом",
    "Балийский диор",
    "Гугловский свист",
    "Бабух улетарный",
    "Садовый говик",
    "Махарин гвинт",
    "Лодосский епос",
    "Гордей улетаев",
    "Машонин ернест",
    "Логаев мексик",
)