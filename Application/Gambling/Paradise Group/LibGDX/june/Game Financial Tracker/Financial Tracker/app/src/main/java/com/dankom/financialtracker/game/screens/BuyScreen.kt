package com.dankom.financialtracker.game.screens

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.dankom.financialtracker.BuildConfig
import com.dankom.financialtracker.R
import com.dankom.financialtracker.game.actors.ListGroup
import com.dankom.financialtracker.game.actors.scroll.HorizontalGroup
import com.dankom.financialtracker.game.colorneucherus
import com.dankom.financialtracker.game.game
import com.dankom.financialtracker.game.manager.NavigationManager
import com.dankom.financialtracker.game.manager.SpriteManager
import com.dankom.financialtracker.game.utils.GameColor
import com.dankom.financialtracker.game.utils.actor.setOnClickListener
import com.dankom.financialtracker.game.utils.advanced.AdvancedGroup
import com.dankom.financialtracker.game.utils.advanced.AdvancedScreen
import com.dankom.financialtracker.game.utils.runGDX
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BuyScreen: AdvancedScreen() {

    private val nastroikiImg = Image(SpriteManager.GameRegion.NASTROIKI.region)
    private val hG = HorizontalGroup(34f)
    private val sP = ScrollPane(hG)
    private val white4Img = Image(SpriteManager.GameRegion.WHITI_CHOTIRKI.region)
    private val listG     = ListGroup()
    private val domikImg  = Image(SpriteManager.GameRegion.DOMIK.region)

    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            withContext(Dispatchers.Main) {
                colorneucherus = GameColor.whiti
                game.activity.window.navigationBarColor = ContextCompat.getColor(game.activity, R.color.white2)
            }
            runGDX {
                addNastroiki()
                addKartki()
                addW4()
                addListG()
                addDomik()
            }

            nastroikiImg.animShow(0.5f)
            sP.animShow(0.5f)
            white4Img.animShow(0.3f)
            listG.animShow(0.4f)
            animDomik()
        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedGroup.addNastroiki() {
        addActor(nastroikiImg)
        nastroikiImg.apply {
            setBounds(35f, 1272f, 585f, 87f)
            addAction(Actions.alpha(0f))
        }

        val v1 = Actor()
        val v2 = Actor()
        addActors(v1,v2)
        v1.apply {
            setBounds(35f, 1272f, 87f, 87f)
            setOnClickListener { NavigationManager.back() }
        }
        v2.apply {
            setBounds(532f, 1272f, 87f, 87f)
            setOnClickListener { NavigationManager.back() }
        }
    }

    private fun AdvancedGroup.addKartki() {
        addActor(sP)
        sP.setBounds(0f, 891f, 655f, 303f)
        sP.addAction(Actions.alpha(0f))

        listOf(SpriteManager.GameRegion.K1.region, SpriteManager.GameRegion.K2.region).onEach {
            hG.addActor(Image(it).apply { setSize(465f, 301f) })
        }
    }

    private fun AdvancedGroup.addW4() {
        addActor(white4Img)
        white4Img.setBounds(33f, 663f, 594f, 147f)
        white4Img.addAction(Actions.alpha(0f))

        val v1 = Actor()
        val v2 = Actor()
        val v3 = Actor()
        val v4 = Actor()

        addActors(v1, v2, v3, v4)
        v1.apply {
            setBounds(32f, 664f, 144f, 144f)
            setOnClickListener {
                val text = "Скачивай: ${game.activity.getString(R.string.app_name)}"

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_SUBJECT, text)
                    putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, "Отправить:")
                game.activity.startActivity(shareIntent)

            }
        }
        v2.apply {
            setBounds(181f, 664f, 144f, 144f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(TT))) }
        }
        v3.apply {
            setBounds(330f, 664f, 144f, 144f)
            setOnClickListener { NavigationManager.back() }
        }
        v4.apply {
            setBounds(480f, 664f, 144f, 144f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(PP))) }
        }
    }

    private fun AdvancedGroup.addListG() {
        addActor(listG)
        listG.apply {
            setBounds(46f, 138f, 563f, 468f)
            addAction(Actions.alpha(0f))
        }
    }

    private fun AdvancedGroup.addDomik() {
        addActor(domikImg)
        domikImg.apply {
            setBounds(0f, -257f, 655f, 257f)
            addAction(Actions.alpha(0f))
        }

        val v2 = Actor()
        val v3 = Actor()
        val v4 = Actor()

        addActors(v2, v3, v4)
        v2.apply {
            setBounds(225f, 49f, 76f, 76f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(TT))) }
        }
        v3.apply {
            setBounds(353f, 49f, 76f, 76f)
            setOnClickListener { NavigationManager.navigate(BookScreen(), BuyScreen()) }
        }
        v4.apply {
            setBounds(487f, 49f, 76f, 76f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(PP))) }
        }
    }

    // ---------------------------------------------------
    // Anim
    // ---------------------------------------------------

    private suspend fun Actor.animShow(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            addAction(Actions.sequence(
                Actions.fadeIn(time),
                Actions.run { continuation.complete(Unit) }
            ))
        }
    }.await()

    private suspend fun animDomik() = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            domikImg.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.fadeIn(0.75f),
                    Actions.moveTo(0f, 0f, 0.75f)
                ),
                Actions.run { continuation.complete(Unit) }
            ))
        }
    }.await()

}