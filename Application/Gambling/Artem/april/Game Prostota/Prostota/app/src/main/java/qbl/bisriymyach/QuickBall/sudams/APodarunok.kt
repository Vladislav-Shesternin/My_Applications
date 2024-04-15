package qbl.bisriymyach.QuickBall.sudams

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.launch
import qbl.bisriymyach.QuickBall.fastergan.imporer
import qbl.bisriymyach.QuickBall.tidams.yatayaaaya
import qbl.bisriymyach.QuickBall.hotvils.hrom
import qbl.bisriymyach.QuickBall.hotvils.piolka
import qbl.bisriymyach.QuickBall.hotvils.gol
import qbl.bisriymyach.QuickBall.fastergan.suchka

class APodarunok constructor(override val screen: suchka, lblStyle: LabelStyle) : Oi_oi_uoi() {

    private val p100Lbl = Label("+100", lblStyle)
    private val p500Lbl = Label("+500", lblStyle)
    private val p1000Lbl = Label("+1000", lblStyle)

    private val listLbl = listOf(p100Lbl, p500Lbl, p1000Lbl)
    private val listImg = List(3) { Image(screen.game.allAssets.bigaras) }
    private val listSum = listOf(100, 500, 1000)
    private val indexexes = (0..2).shuffled()

    var clickBl = {}

    override fun addActorsOnGroup() {
        addActors(listLbl)
        addActors(listImg)

        var nx = 92f
        indexexes.onEach {
            listLbl[it].apply {
                setAlignment(Align.center)
                setBounds(nx, 101f, 147f, 132f)
            }
            nx += 192 + 147
        }

        var bnx = 0f
        listImg.onEachIndexed { index, it ->
            it.setBounds(bnx, 0f, 334f, 334f)
            bnx += 5 + 334

            it.gol(screen.game.soundUtil) {
                piolka()
                it.hrom(yatayaaaya) {

                    screen.game.soundUtil.apply { play(podrank, 50f) }

                    screen.game.iaiusjdf7.kalim.value += listSum[indexexes[index]]
                    screen.game.isBABAna = false
                    clickBl()

                    coroutine?.launch { imporer.Date.ubed { screen.game.currentDate } }

                }
            }
        }
    }

}