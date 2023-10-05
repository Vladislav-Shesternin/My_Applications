package gazmm.klowsaklll.fiatskings.flowww.game.actors.scroll

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.utils.Align
import gazmm.klowsaklll.fiatskings.flowww.game.screens.AnalitikaScreen
import gazmm.klowsaklll.fiatskings.flowww.game.screens.codeNAME
import gazmm.klowsaklll.fiatskings.flowww.game.screens.toRub
import gazmm.klowsaklll.fiatskings.flowww.game.utils.TIME_ANIM_SCREEN_ALPHA
import gazmm.klowsaklll.fiatskings.flowww.game.utils.actor.animHide
import gazmm.klowsaklll.fiatskings.flowww.game.utils.actor.setOnTouchListener
import gazmm.klowsaklll.fiatskings.flowww.game.utils.advanced.AdvancedGroup
import gazmm.klowsaklll.fiatskings.flowww.game.utils.advanced.AdvancedScreen
import gazmm.klowsaklll.fiatskings.flowww.game.utils.numStr

class CurentItem(
    override val screen: AdvancedScreen,
    val cname: String,
    val coset: Double,
    labelStyle1: LabelStyle,
    labelStyle2: LabelStyle,
    labelStyle3: LabelStyle,
): AdvancedGroup() {

    private val linekal = Image(screen.game.spriteUtil.LINE)
    private val iconkal = Image(screen.game.spriteUtil.lisovka.random())
    private val nameLbl = Label(cname, labelStyle1)
    private val costLbl = Label("₽"+coset.toRub, labelStyle2).apply { setAlignment(Align.right) }
    private val percLbl = Label("+${numStr(10,90,1)}.${numStr(0,9,2)}", labelStyle3).apply { setAlignment(Align.right) }

    override fun addActorsOnGroup() {
        addActors(linekal, iconkal, nameLbl, costLbl, percLbl)
        linekal.setBounds(0f, 0f, 616f, 2f)
        iconkal.setBounds(0f, 25f, 77f, 77f)
        nameLbl.setBounds(108f, 40f, 231f, 47f)
        costLbl.setBounds(382f, 60f, 234f, 47f)
        percLbl.setBounds(466f, 21f, 150f, 31f)

        setOnTouchListener {
            codeNAME = cname
            screen.stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                screen.game.navigationManager.navigate(AnalitikaScreen::class.java.name, screen::class.java.name)
            }
        }
    }



}