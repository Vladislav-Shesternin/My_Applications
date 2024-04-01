package qbl.bisriymyach.QuickBall

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import qbl.bisriymyach.QuickBall.tidams.yatayaaaya
import qbl.bisriymyach.QuickBall.hotvils.hrom
import qbl.bisriymyach.QuickBall.hotvils.piolka
import qbl.bisriymyach.QuickBall.fastergan.suchka
import qbl.bisriymyach.QuickBall.sudams.APodarunok
import qbl.bisriymyach.QuickBall.sudams.Oi_oi_uoi

class Appapapap constructor(override val screen: suchka, lblStyle: LabelStyle): Oi_oi_uoi() {

    private val spd = APodarunok(screen, lblStyle)

    override fun addActorsOnGroup() {
        addAndFillActor(Image(screen.drawerUtil.gggssgsg(Color.valueOf("5D42FF").apply { a = 0.67f })))
        addActor(spd)
        spd.setBounds(34f, 749f, 1012f, 334f)

        spd.clickBl = {
            piolka()
            hrom(yatayaaaya)
        }
    }

}