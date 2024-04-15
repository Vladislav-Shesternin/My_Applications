package qbl.bisriymyach.QuickBall.hotvils

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import kotlinx.coroutines.launch
import qbl.bisriymyach.QuickBall.LibGDXGame
import qbl.bisriymyach.QuickBall.pitopilot.Selomon_donzeruha
import qbl.bisriymyach.QuickBall.bill
import qbl.bisriymyach.QuickBall.sudams.Materasu
import qbl.bisriymyach.QuickBall.pitopilot.bakugangbang
import qbl.bisriymyach.QuickBall.tidams.piza
import qbl.bisriymyach.QuickBall.tidams.yatayaaaya
import qbl.bisriymyach.QuickBall.tidams.liza
import qbl.bisriymyach.QuickBall.Ebufren
import qbl.bisriymyach.QuickBall.tidams.zavtra_v_shskull
import qbl.bisriymyach.QuickBall.tidams.doo
import qbl.bisriymyach.QuickBall.fastergan.mp489
import qbl.bisriymyach.QuickBall.fastergan.runGDX
import qbl.bisriymyach.QuickBall.tidams.toB2

class Palibresko(override val game: LibGDXGame) : Ebufren(Selomon_donzeruha()) {

    companion object {
        private val BLL_COUNT = 10
    }

    private val fontParameter = doo()
    private val font77 = fontGenerator_Jaldi.generateFont(
        fontParameter.setCharacters(
            doo.CharType.ALL
        ).setSize(77)
    )
    private val font46 = fontGenerator_Jaldi.generateFont(
        fontParameter.setCharacters(
            doo.CharType.ALL
        ).setSize(46)
    )

    private val aClickHire = Image(game.allAssets.culke)
    private val aPanelClicka = Image(game.allAssets.sokulele)
    private val aLina = Image(game.allAssets.luna)
    private val aCion = Image(game.allAssets.coin)
    private val aXerestes = Image(game.allAssets.xersers)
    private val aBnceLbl =
        Label("${game.iaiusjdf7.kalim.value}", Label.LabelStyle(font77, Color.WHITE))
    private val aBillLbl = Label(BLL_COUNT.toString(), Label.LabelStyle(font46, Color.WHITE))

    private val bList15Circle = List(15) { Materasu(this) }
    private val bList12Circle = List(8) { Materasu(this) }
    private val bListBall = MutableList(BLL_COUNT) { bill(this) }
    private val bListXXX = List(6) { Did(this) }

    private val bgBackground = bakugangbang(this)

    override fun show() {
        stageUI.root.hrom()
        setBackBackground(if (Sitriska.BACG == Sitriska.Baraboli.Right) game.loaderAssets.TABEX.mp489 else game.allAssets.FOREX.mp489)
        super.show()
        stageUI.root.zahovaqka(yatayaaaya) {
            aClickHire.addAction(
                Actions.forever(
                    Actions.sequence(
                        Actions.alpha(0.3f, 0.3f, Interpolation.sine),
                        Actions.alpha(1f, 0.3f, Interpolation.sine),
                    )
                )
            )
        }
    }

    override fun zavtra_v_shskull.addActorsOnStageUI() {
        addBLbl()
        addBillLbl()

        addClickHie()

        createBG_Boroda()

        createB_ListCircle_15()
        createB_ListCircle_12()

        createB_BallList()

        createB_ListFinal()

        addLuna()
        addPanelClikarka()
        addBacka()
        addCion()
        addXerestesr()

        if (Sitriska.boll == Sitriska.Baraboli.Right) {
            val img = Image(game.allAssets.bl_yellow)
            addActor(img)
            img.setBounds(867f, 1725f, 37f, 37f)
        }
    }

    private fun zavtra_v_shskull.addBLbl() {
        addActor(aBnceLbl)
        aBnceLbl.setBounds(571f, 1695f, 144f, 130f)

        coroutine?.launch {
            game.iaiusjdf7.kalim.collect {
                runGDX { aBnceLbl.setText(it.toString()) }
            }
        }
    }

    private fun zavtra_v_shskull.addBillLbl() {
        addActor(aBillLbl)
        aBillLbl.setBounds(1004f, 1704f, 43f, 78f)
    }

    private fun zavtra_v_shskull.addClickHie() {
        addActor(aClickHire)
        aClickHire.setBounds(389f, 1469f, 302f, 108f)
        aClickHire.piolka()
    }

    private fun zavtra_v_shskull.addPanelClikarka() {
        val tmpVector = Vector2()
        var isFirst = true

        addActor(aPanelClicka)
        aPanelClicka.setBounds(22f, 1463f, 1036f, 119f)
        aPanelClicka.addListener(object : InputListener() {
            override fun touchDown(
                event: InputEvent?,
                x: Float,
                y: Float,
                pointer: Int,
                button: Int
            ): Boolean {
                return true
            }

            override fun touchUp(
                event: InputEvent?,
                x: Float,
                y: Float,
                pointer: Int,
                button: Int
            ) {
                if (isFirst) {
                    isFirst = false
                    aClickHire.addAction(Actions.sequence(Actions.removeActor()))
                }
                runGDX {
                    bListBall.removeFirstOrNull()?.also { ball ->
                        ball.setOriginalId()
                        ball.body?.apply {
                            setTransform(tmpVector.set(22f + x, 1463f + y).toB2, 0f)
                            gravityScale = 1f
                            applyForceToCenter(0f, 1f, true)
                        }
                        ball.isWork.set(true)

                        game.soundUtil.apply { play(clk) }

                        aBillLbl.setText(bListBall.size)
                    }
                }
            }
        })
    }

    private fun zavtra_v_shskull.addBacka() {
        val beck = Image(game.allAssets.xrestiv)
        addActor(beck)
        beck.setBounds(73f, 1675f, 170f, 170f)
        beck.gol(game.soundUtil) {
            stageUI.root.hrom(yatayaaaya) {
                game.navigationManager.back()
            }
        }
    }

    private fun zavtra_v_shskull.addLuna() {
        addActor(aLina)
        aLina.setBounds(865f, 1704f, 184f, 175f)
    }

    private fun zavtra_v_shskull.addCion() {
        addActor(aCion)
        aCion.setBounds(469f, 1719f, 62f, 81f)
    }

    private fun zavtra_v_shskull.addXerestesr() {
        addActor(aXerestes)
        aXerestes.setBounds(58f, 112f, 964f, 133f)
    }

    private fun createBG_Boroda() {
        bgBackground.mackro(0f, 0f, liza, piza)
    }

    private fun createB_ListCircle_15() {
        var nx = 110f
        var ny = 1252f
        bList15Circle.onEachIndexed { index, bCircle ->
            bCircle.create(nx, ny, 100f, 100f)
            nx += 90 + 100
            if (index.inc() % 5 == 0) {
                nx = 110f
                ny -= 290 + 100
            }
        }
    }

    private fun createB_ListCircle_12() {
        var nx = 205f
        var ny = 1057f
        bList12Circle.onEachIndexed { index, bCircle ->
            bCircle.create(nx, ny, 100f, 100f)
            nx += 90 + 100
            if (index.inc() % 4 == 0) {
                nx = 205f
                ny -= 290 + 100
            }
        }
        Materasu(this).create(-50f, 1057f, 100f, 100f)
        Materasu(this).create(1030f, 1057f, 100f, 100f)
        Materasu(this).create(-50f, 667f, 100f, 100f)
        Materasu(this).create(1030f, 667f, 100f, 100f)

        Materasu(this).apply {
            ultima?.hrom()
            create(-50f, 145f, 100f, 100f)
        }
        Materasu(this).apply {
            ultima?.hrom()
            create(1030f, 145f, 100f, 100f)
        }
    }

    private fun createB_BallList() {
        bListBall.onEach { ball ->
            ball.apply {
                id = Omred.BALL
                collisionList.addAll(
                    arrayOf(
                        Omred.STATIC,
                        Omred.BALL,
                        Omred.x01,
                        Omred.x05,
                        Omred.x15,
                        Omred.x10
                    )
                )
                setNoneId()
                ronaldo.gravityScale = 0f
                create(-100f, -100f, 80f, 80f)
            }
        }
    }

    private fun createB_ListFinal() {
        var nx = 58f

        val xxx = listOf(
            Omred.x01, Omred.x05, Omred.x15,
            Omred.x10, Omred.x05, Omred.x01,
        )
        bListXXX.onEachIndexed { index, win ->
            win.id = xxx[index]
            win.collisionList.add(Omred.BALL)

            win.create(nx, 127f, 135f, 103f)
            nx += 24 + 135

            win.beginContactBlockArray.add(Bibash.ContactBlock { enemy ->
                if (enemy.id == Omred.BALL) {
                    enemy as bill
                    if (enemy.isWork.getAndSet(false)) {
                        runGDX {

                            when (win.id) {
                                Omred.x01 -> {
                                    game.iaiusjdf7.kalim.value -= 90
                                }

                                Omred.x05 -> {
                                    game.iaiusjdf7.kalim.value -= 50
                                }

                                Omred.x15 -> {
                                    game.iaiusjdf7.kalim.value += 50
                                }

                                Omred.x10 -> {
                                    game.iaiusjdf7.kalim.value += 1000
                                }
                            }

                            game.soundUtil.apply { play(funil, volumeLevel) }

                            enemy.body?.apply {
                                gravityScale = 0f
                                isAwake = false
                                setLinearVelocity(0f, 0f)
                                setTransform(-20f, -20f, 0f)
                                bListBall.add(enemy)
                            }

                            aBillLbl.setText(bListBall.size)
                        }
                    }
                }
            })
        }
    }

}