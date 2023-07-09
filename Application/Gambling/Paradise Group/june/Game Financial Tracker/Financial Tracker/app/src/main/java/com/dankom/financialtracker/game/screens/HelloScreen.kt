package com.dankom.financialtracker.game.screens

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.dankom.financialtracker.BuildConfig
import com.dankom.financialtracker.R
import com.dankom.financialtracker.game.actors.PanelGroup
import com.dankom.financialtracker.game.colorneucherus
import com.dankom.financialtracker.game.game
import com.dankom.financialtracker.game.manager.FontTTFManager
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

class HelloScreen: AdvancedScreen() {

    private val namki = listOf(
       "Алексей",
       "Анна",
       "Иван",
       "Екатерина",
       "Михаил",
       "Ольга",
       "Александра",
       "Дмитрий",
       "Мария",
       "Николай",
       "Елена",
       "Сергей",
       "Антон",
       "Юлия",
       "Владимир",
       "Татьяна",
       "Артем",
       "Ирина",
       "Павел",
       "Наталья",
       "Константин",
       "Светлана",
       "Андрей",
       "Олеся",
       "Виктор",
       "Алина",
       "Георгий",
       "Ангелина",
       "Василий",
       "Евгения",
       "Роман",
       "Анастасия",
       "Владислав",
       "Людмила",
       "Артур",
       "Валерия",
       "Илья",
       "Марина",
       "Станислав",
       "Елизавета",
       "Лилия",
    )
    private val surki = listOf(
       "Иванов",
       "Смирнов",
       "Кузнецов",
       "Попов",
       "Васильев",
       "Петров",
       "Соколов",
       "Михайлов",
       "Новиков",
       "Федоров",
       "Морозов",
       "Волков",
       "Алексеев",
       "Лебедев",
       "Семенов",
       "Егоров",
       "Павлов",
       "Козлов",
       "Степанов",
       "Николаев",
       "Орлов",
       "Андреев",
       "Макаров",
       "Никитин",
       "Захаров",
       "Зайцев",
       "Соловьев",
       "Борисов",
       "Яковлев",
       "Григорьев",
       "Романов",
       "Воробьев",
       "Сергеев",
       "Кузьмин",
       "Фролов",
       "Александров",
       "Дмитриев",
       "Королев",
       "Гусев",
       "Киселев",
       "Ильин",
    )

    private val lrImg      = Image(SpriteManager.GameRegion.LR.region)
    private val privetImg  = Image(SpriteManager.GameRegion.PRIVET.region)
    private val nsLbl      = Label(namki.shuffled().first() + " " + surki.shuffled().first(), Label.LabelStyle(FontTTFManager.ProstoOneRegular.font_34.font, Color.WHITE))
    private val balanLbl   = Label("$${number(1, 9, 3)},${number(1, 9, 3)}", Label.LabelStyle(FontTTFManager.PoppinsSemiBold.font_52.font, Color.WHITE))
    private val chotiryImg = Image(SpriteManager.GameRegion.CHOTIRY.region)
    private val v1 = Actor()
    private val v2 = Actor()
    private val v3 = Actor()
    private val v4 = Actor()
    private val panel = PanelGroup()


    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            withContext(Dispatchers.Main) {
                colorneucherus = GameColor.background
                game.activity.window.navigationBarColor = ContextCompat.getColor(game.activity, R.color.blue2)
            }
            runGDX {
                addLR()
                addHello()
                addNameSurname()
                addBalan()
                addChotiry()
                addV4()
                addPanel()
            }

            panel.animShow(0.5f)
            lrImg.animShow(0.5f)
            privetImg.animShow(0.5f)
            nsLbl.animShow(0.3f)
            balanLbl.animShow(0.3f)
            chotiryImg.animShow(0.5f)

        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addLR() {
        addActor(lrImg)
        lrImg.setBounds(0f, 669f, 655f, 750f)
        lrImg.addAction(Actions.alpha(0f))
    }

    private fun AdvancedGroup.addHello() {
        addActor(privetImg)
        privetImg.setBounds(40f, 1123f, 337f, 188f)
        privetImg.addAction(Actions.alpha(0f))
    }

    private fun AdvancedGroup.addNameSurname() {
        addActor(nsLbl)
        nsLbl.setBounds(147f, 1215f, 476f, 42f)
        nsLbl.addAction(Actions.alpha(0f))
    }

    private fun AdvancedGroup.addBalan() {
        addActor(balanLbl)
        balanLbl.setBounds(36f, 1039f, 583f, 78f)
        balanLbl.setAlignment(Align.center)
        balanLbl.addAction(Actions.alpha(0f))
    }

    private fun AdvancedGroup.addChotiry() {
        addActor(chotiryImg)
        chotiryImg.setBounds(59f, 821f, 542f, 147f)
        chotiryImg.addAction(Actions.alpha(0f))
    }

    private fun AdvancedGroup.addV4() {
        addActors(v1, v2, v3, v4)
        v1.apply {
            setBounds(59f, 821f, 131f, 147f)
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
            setBounds(207f, 821f, 103f, 147f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(PP))) }
        }
        v3.apply {
            setBounds(340f, 821f, 116f, 147f)
            setOnClickListener { NavigationManager.navigate(BuyScreen(), HelloScreen()) }
        }
        v4.apply {
            setBounds(466f, 821f, 135f, 147f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(TT))) }
        }
    }

    private fun AdvancedGroup.addPanel() {
        addActor(panel)
        panel.setBounds(0f, -629f, 655f, 1403f)
        panel.addAction(Actions.alpha(0f))
    }

    // ---------------------------------------------------
    // Anim
    // ---------------------------------------------------

    private suspend fun Actor.animShow(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            addAction(
                Actions.sequence(
                Actions.fadeIn(time),
                Actions.run { continuation.complete(Unit) }
            ))
        }
    }.await()

    private suspend fun Actor.animHide(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            addAction(
                Actions.sequence(
                Actions.fadeOut(time),
                Actions.run { continuation.complete(Unit) }
            ))
        }
    }.await()

    private fun Actor.animHide(time: Float, block: () -> Unit) {
        runGDX {
            addAction(
                Actions.sequence(
                Actions.fadeOut(time),
                Actions.run { block() }
            ))
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun number(min: Int, max: Int, count: Int): Long {
        var numStr = ""
        repeat(count) { numStr += (min..max).shuffled().first() }
        return numStr.toLong()
    }

}