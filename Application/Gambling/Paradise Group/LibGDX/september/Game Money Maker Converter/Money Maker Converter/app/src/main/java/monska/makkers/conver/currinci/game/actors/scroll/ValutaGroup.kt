package monska.makkers.conver.currinci.game.actors.scroll

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.utils.Align
import monska.makkers.conver.currinci.game.screens.UtemScreen
import monska.makkers.conver.currinci.game.screens.VScreen
import monska.makkers.conver.currinci.game.screens.fulkaCoste
import monska.makkers.conver.currinci.game.screens.fulkaNamke
import monska.makkers.conver.currinci.game.utils.TIME_ANIM_SCREEN_ALPHA
import monska.makkers.conver.currinci.game.utils.actor.animHide
import monska.makkers.conver.currinci.game.utils.actor.setOnTouchListener
import monska.makkers.conver.currinci.game.utils.advanced.AdvancedGroup
import monska.makkers.conver.currinci.game.utils.advanced.AdvancedScreen
import java.util.Random

class ValutaGroup(
    override val screen: AdvancedScreen,
    val valuteName: String,
    val cosete: Double,
    labelStyle: LabelStyle,
): AdvancedGroup() {

    private val nameLbl = Label(valuteName.drop(3), labelStyle)

    override fun addActorsOnGroup() {
        addAndFillActor(Image(screen.game.spriteUtil.penel))
        addActor(nameLbl)
        nameLbl.setBounds(16f, 20f, 516f, 62f)
        if (Random().nextBoolean()) nameLbl.setAlignment(Align.left) else nameLbl.setAlignment(Align.right)

        nameLbl.setOnTouchListener {

            fulkaNamke = valuteName
            fulkaCoste = cosete

            screen.stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                screen.game.navigationManager.navigate(UtemScreen::class.java.name, VScreen::class.java.name)
            }
        }
    }



}