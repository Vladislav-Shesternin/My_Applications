package qbl.bisriymyach.QuickBall.hotvils

import com.badlogic.gdx.scenes.scene2d.ui.Image
import qbl.bisriymyach.QuickBall.LibGDXGame
import qbl.bisriymyach.QuickBall.pipRen
import qbl.bisriymyach.QuickBall.sudams.Denca_Kuduro
import qbl.bisriymyach.QuickBall.tidams.yatayaaaya
import qbl.bisriymyach.QuickBall.fastergan.suchka
import qbl.bisriymyach.QuickBall.tidams.zavtra_v_shskull
import qbl.bisriymyach.QuickBall.fastergan.mp489

class Sitriska(override val game: LibGDXGame) : suchka() {

    companion object {
        var BACG = Baraboli.Right
            private set

        var boll = Baraboli.LEft
            private set
    }

    private val strr = Image(game.allAssets.SETERO)

    private val cbSnd = pipRen(this, pipRen.Static.Type.NO)
    private val cbMsc = pipRen(this, pipRen.Static.Type.NO)

    private val cbBcgLeft = pipRen(this, pipRen.Static.Type.BACKGROUND)
    private val cbBcgRight = pipRen(this, pipRen.Static.Type.BACKGROUND)

    private val cbBallLeft = pipRen(this, pipRen.Static.Type.BALL)
    private val cbBallRight = pipRen(this, pipRen.Static.Type.BALL)

    override fun show() {
        stageUI.root.hrom()
        setBackBackground(game.allAssets.POLILINE.mp489)
        super.show()
        stageUI.root.zahovaqka(yatayaaaya)
    }

    override fun zavtra_v_shskull.addActorsOnStageUI() {
        addActor(strr)
        strr.setBounds(194f, 315f, 723f, 1424f)

        val beck = Image(game.allAssets.xrestiv)
        addActor(beck)
        beck.setBounds(464f, 107f, 170f, 170f)
        beck.gol(game.soundUtil) {
            stageUI.root.hrom(yatayaaaya) {
                game.navigationManager.back()
            }
        }

        addCBJKdj()
        addCB_BGC()
        addCB_Bacll()
    }

    private fun zavtra_v_shskull.addCBJKdj() {
        addActors(cbSnd, cbMsc)
        cbSnd.apply {
            setBounds(308f, 1387f, 88f, 88f)
            setOnCheckListener {
                game.soundUtil.isPause = it
            }
            if (game.soundUtil.isPause) check(false)
        }
        cbMsc.apply {
            setBounds(694f, 1387f, 88f, 88f)
            setOnCheckListener {
                if (it) game.MUSICALka?.pause() else game.MUSICALka?.play()
            }
            if (game.MUSICALka?.isPlaying == false) check(false)
        }
    }

    private fun zavtra_v_shskull.addCB_BGC() {
        val cbg = Denca_Kuduro()
        addActors(cbBcgLeft, cbBcgRight)
        cbBcgLeft.apply {
            setBounds(276f, 740f, 280f, 420f)
            setOnCheckListener {
                if (it) BACG = Baraboli.LEft
            }
            checkBoxGroup = cbg
        }
        cbBcgRight.apply {
            setBounds(560f, 740f, 280f, 420f)
            setOnCheckListener {
                if (it) BACG =
                    Baraboli.Right
            }
            checkBoxGroup = cbg
        }
        if (BACG == Baraboli.LEft) cbBcgLeft.check(
            false
        ) else cbBcgRight.check(false)
    }

    private fun zavtra_v_shskull.addCB_Bacll() {
        val cbg = Denca_Kuduro()
        addActors(cbBallLeft, cbBallRight)
        cbBallLeft.apply {
            setBounds(168f, 289f, 329f, 329f)
            setOnCheckListener {
                if (it) boll = Baraboli.LEft
            }
            checkBoxGroup = cbg
        }
        cbBallRight.apply {
            setBounds(614f, 289f, 329f, 329f)
            setOnCheckListener {
                if (it) boll = Baraboli.Right
            }
            checkBoxGroup = cbg
        }
        if (boll == Baraboli.LEft) cbBallLeft.check(false) else cbBallRight.check(false)
    }

    enum class Baraboli {
        LEft, Right
    }

}