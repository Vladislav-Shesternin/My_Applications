package qbl.bisriymyach.QuickBall.hotvils

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import kotlinx.coroutines.launch
import qbl.bisriymyach.QuickBall.LibGDXGame
import qbl.bisriymyach.QuickBall.Appapapap
import qbl.bisriymyach.QuickBall.pipRen
import qbl.bisriymyach.QuickBall.tidams.yatayaaaya
import qbl.bisriymyach.QuickBall.fastergan.suchka
import qbl.bisriymyach.QuickBall.tidams.zavtra_v_shskull
import qbl.bisriymyach.QuickBall.tidams.doo
import qbl.bisriymyach.QuickBall.fastergan.mp489
import qbl.bisriymyach.QuickBall.fastergan.runGDX

class Miski(override val game: LibGDXGame) : suchka() {

    private val fontParameter = doo()
    private val font77        = fontGenerator_Jaldi.generateFont(fontParameter.setCharacters(
        doo.CharType.ALL).setSize(77))

    private val muha = Image(game.allAssets.knipko)

    override fun show() {
        stageUI.root.hrom()
        setBackBackground(game.allAssets.POLILINE.mp489)
        super.show()
        stageUI.root.zahovaqka(yatayaaaya)

    }
    private val cino = Image(game.allAssets.coin)

    override fun zavtra_v_shskull.addActorsOnStageUI() {
        addMuha()

        addActors(cino, aBnceLbl)
        cino.setBounds(421f, 1505f, 62f, 81f)
        aBnceLbl.setBounds(516f, 1482f, 144f, 130f)

        val podranuk = pipRen(this@Miski, pipRen.Static.Type.PORD)
        addActor(podranuk)
        podranuk.setBounds(853f, 1706f, 188f, 188f)

        podranuk.gol(game.soundUtil) {
                podranuk.disable()
                apd.zahovaqka(yatayaaaya) {
                    apd.frop()
                }
        }

        if (game.isBABAna) podranuk.check(false) else podranuk.disable()

        addAndFillActor(apd)
        apd.piolka()


        coroutine?.launch {
            game.iaiusjdf7.kalim.collect {
                runGDX {
                    aBnceLbl.setText(it.toString())
                }
            }
        }
    }

    private fun zavtra_v_shskull.addMuha() {
        addActor(muha)
        muha.setBounds(193f, 775f, 694f, 542f)

        val pl = Actor()
        val st = Actor()
        val rl = Actor()
        val ex = Actor()
        addActors(pl, st, rl, ex)
        pl.apply {
            setBounds(413f, 1063f, 254f, 254f)
            gol(game.soundUtil) {
                stageUI.root.hrom(yatayaaaya) {
                    game.navigationManager.navigate(Palibresko::class.java.name, Miski::class.java.name)
                }
            }
        }
        st.apply {
            setBounds(193f, 775f, 170f, 170f)
            gol(game.soundUtil) {
                stageUI.root.hrom(yatayaaaya) {
                    game.navigationManager.navigate(Sitriska::class.java.name, Miski::class.java.name)
                }
            }
        }
        rl.apply {
            setBounds(455f, 775f, 170f, 170f)
            gol(game.soundUtil) {
                stageUI.root.hrom(yatayaaaya) {
                    game.navigationManager.navigate(Riski::class.java.name, Miski::class.java.name)
                }
            }
        }
        ex.apply {
            setBounds(717f, 775f, 170f, 170f)
            gol(game.soundUtil) { game.navigationManager.exit() }
        }
    }

    private val aBnceLbl = Label("${game.iaiusjdf7.kalim.value}", Label.LabelStyle(font77, Color.WHITE))
    private val apd = Appapapap(this, Label.LabelStyle(font77, Color.WHITE)).apply { color.a = 0f }


}