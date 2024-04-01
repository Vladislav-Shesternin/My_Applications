package qbl.bisriymyach.QuickBall

import com.badlogic.gdx.utils.viewport.FitViewport
import qbl.bisriymyach.QuickBall.pitopilot.Selomon_donzeruha
import qbl.bisriymyach.QuickBall.tidams.holik
import qbl.bisriymyach.QuickBall.tidams.piza
import qbl.bisriymyach.QuickBall.tidams.iIAisdoaod9
import qbl.bisriymyach.QuickBall.tidams.liza
import qbl.bisriymyach.QuickBall.fastergan.suchka
import qbl.bisriymyach.QuickBall.tidams.log

abstract class Ebufren(
    val tya9: Selomon_donzeruha,
    val uiW  : Float = liza,
                 val uiH  : Float = piza,
    val baka : Float = iIAisdoaod9,
                                         val boxH : Float = holik,
): suchka(uiW, uiH) {


        private val viewportBox2d by lazy { FitViewport(baka, boxH) }


    override fun resize(width: Int, height: Int) {
        viewportBox2d.update(width, height, true)
        super.resize(width, height)
    }
    var isWorldPause = false

    override fun render(delta: Float) {
        if (isWorldPause.not()) tya9.update(delta)
                                    super.render(delta)




        tya9.bbeh(viewportBox2d.camera.combined)
    }

    override fun dispose() {
        log("dispose AdvancedBox2dScreen: ${this::class.java.name.substringAfterLast('.')}")
                                                super.dispose()
        tya9.dispose()
    }

}