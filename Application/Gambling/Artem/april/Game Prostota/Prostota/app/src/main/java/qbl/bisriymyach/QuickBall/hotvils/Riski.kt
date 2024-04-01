package qbl.bisriymyach.QuickBall.hotvils

import com.badlogic.gdx.scenes.scene2d.ui.Image
import qbl.bisriymyach.QuickBall.LibGDXGame
import qbl.bisriymyach.QuickBall.tidams.yatayaaaya
import qbl.bisriymyach.QuickBall.fastergan.suchka
import qbl.bisriymyach.QuickBall.tidams.zavtra_v_shskull
import qbl.bisriymyach.QuickBall.fastergan.mp489

class Riski(override val game: LibGDXGame) : suchka() {

    private val rls = Image(game.allAssets.ROPENDE)

    override fun show() {
        stageUI.root.hrom()
        setBackBackground(game.allAssets.POLILINE.mp489)
        super.show()
        stageUI.root.zahovaqka(yatayaaaya)
    }

    override fun zavtra_v_shskull.addActorsOnStageUI() {
        addActor(rls)
        rls.setBounds(209f, 636f, 696f, 1103f)

        val beck = Image(game.allAssets.xrestiv)
        addActor(beck)
        beck.setBounds(464f, 107f, 170f, 170f)
        beck.gol(game.soundUtil) {
            stageUI.root.hrom(yatayaaaya) {
                game.navigationManager.back()
            }
        }
    }

}