package crapto.makasinik.cryptoinsightspro.game.screens

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import crapto.makasinik.cryptoinsightspro.game.actors.button.AButton
import crapto.makasinik.cryptoinsightspro.game.actors.button.AButtonStyle
import crapto.makasinik.cryptoinsightspro.game.actors.checkbox.ACheckBox
import crapto.makasinik.cryptoinsightspro.game.actors.checkbox.ACheckBoxStyle
import crapto.makasinik.cryptoinsightspro.game.game
import crapto.makasinik.cryptoinsightspro.game.manager.GameDataStoreManager
import crapto.makasinik.cryptoinsightspro.game.manager.NavigationManager
import crapto.makasinik.cryptoinsightspro.game.manager.SpriteManager
import crapto.makasinik.cryptoinsightspro.game.utils.actor.animAlpha0
import crapto.makasinik.cryptoinsightspro.game.utils.actor.animAlpha1
import crapto.makasinik.cryptoinsightspro.game.utils.actor.setOnClickListener
import crapto.makasinik.cryptoinsightspro.game.utils.advanced.AdvancedGroup
import crapto.makasinik.cryptoinsightspro.game.utils.advanced.AdvancedScreen
import crapto.makasinik.cryptoinsightspro.game.utils.runGDX
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch

class PpoolliiscScreen: AdvancedScreen() {

    companion object {
        const val PRI_VAC_Y = "https://maksimvasuk65.github.io/InvestmentManager/pwewerwroowriwor"
        const val TE_RMS    = "https://maksimvasuk65.github.io/InvestmentManager/tjkfjgfjgkfgjfkgjk"
    }

    private val svinkaImg = Image(SpriteManager.IgraRegion.WANT_TO_INVEST.region)
    private val solomaImg = Image(SpriteManager.IgraRegion.BY_CONTINUING.region)
    private val solomaBox = ACheckBox(ACheckBoxStyle.charodey)
    private val solomaBtn = AButton(AButtonStyle.contex)


    private var isSoloma = false



    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            runGDX {
                addSolodkaKara()
                addCbAndBtn()
            }
            animSvinka()
            animSoloma()
        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedGroup.addSolodkaKara() {
        addActor(svinkaImg)
        svinkaImg.apply {
            setBounds(98f, 560f, 483f, 504f)
            animAlpha0()
            setOrigin(Align.center)
            addAction(Actions.scaleTo(0f, 0f))
        }
    }

    private fun AdvancedGroup.addCbAndBtn() {
        addActors(solomaImg, solomaBtn, solomaBox)
        solomaBox.apply {
            setBounds(-56f, 234f,56f, 56f)
            animAlpha0()

            setOnCheckListener {
                isSoloma = it
                if (isSoloma) solomaBtn.enable() else solomaBtn.disable()
            }
        }
        solomaBtn.apply {
            setBounds(-604f, 111f,604f, 85f)
            animAlpha0()
            disable()

            setOnClickListener {
                if (isSoloma) {
                    disable()
                    coroutine.launch {
                        GameDataStoreManager.FlagPrivata.update { true }
                        mainGroup.animAlpha0(0.3f) { runGDX { NavigationManager.navigate(MarketsBeginnersGuideScreen()) } }
                    }
                }
            }
        }
        solomaImg.apply {
            setBounds(-547f, 224f,547f, 66f)
            animAlpha0()
        }

        val para = Actor()
        val tara = Actor()
        addActors(para, tara)
        para.apply {
            setBounds(436f, 258f, 201f, 30f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(PRI_VAC_Y))) }
        }
        tara.apply {
            setBounds(94f, 225f, 325f, 30f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(TE_RMS))) }
        }
    }

    // ---------------------------------------------------
    // Anim
    // ---------------------------------------------------

    private suspend fun animSvinka(time: Float = 0.2f) = CompletableDeferred<Unit>().also { co ->
        runGDX {
            svinkaImg.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.fadeIn(time),
                    Actions.scaleTo(1f, 1f, time, Interpolation.elasticOut)
                ),
                Actions.run { co.complete(Unit) }
            ))
        }
    }.await()

    private suspend fun animSoloma(time: Float = 1.4f) = CompletableDeferred<Unit>().also { co ->
        runGDX {
            solomaImg.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.fadeIn(time),
                    Actions.moveTo(94f, 224f, time, Interpolation.elasticOut)
                ),
                Actions.run { co.complete(Unit) }
            ))
            solomaBtn.addAction(Actions.sequence(
                Actions.delay(0.3f),
                Actions.parallel(
                    Actions.fadeIn(time),
                    Actions.moveTo(37f, 111f, time, Interpolation.elasticOut)
                ),
            ))
            solomaBox.addAction(Actions.sequence(
                Actions.delay(0.3f),
                Actions.parallel(
                    Actions.fadeIn(time),
                    Actions.moveTo(37f, 234f, time, Interpolation.elasticOut)
                ),
            ))
        }
    }.await()

}