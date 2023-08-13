package com.mariam.cleverfinancier.game.screens

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.mariam.cleverfinancier.BuildConfig
import com.mariam.cleverfinancier.R
import com.mariam.cleverfinancier.game.actors.scroll.VerticalGroup
import com.mariam.cleverfinancier.game.game
import com.mariam.cleverfinancier.game.manager.FontTTFManager
import com.mariam.cleverfinancier.game.manager.NavigationManager
import com.mariam.cleverfinancier.game.manager.SpriteManager
import com.mariam.cleverfinancier.game.utils.GameColor
import com.mariam.cleverfinancier.game.utils.actor.setOnClickListener
import com.mariam.cleverfinancier.game.utils.advanced.AdvancedGroup
import com.mariam.cleverfinancier.game.utils.advanced.AdvancedScreen

class GlavniyScreen: AdvancedScreen() {

    private val namesksd    = listOf(
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
    private val surnamesksd = listOf(
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

    // MainGroup
    private val glavnaImage = Image(SpriteManager.GameRegion.GLAVNA.region)
    private val namesuLabel = Label(namesksd.shuffled().first() + " " + surnamesksd.shuffled().first(), Label.LabelStyle(FontTTFManager.Regular.font_21.font, GameColor.grayba))
    private val verticGroup = VerticalGroup(21f, startGap = 100f)
    private val scrollPanes = ScrollPane(verticGroup)
    private val menuheImage = Image(SpriteManager.GameRegion.M1.region)
    private val v1 = Actor()
    private val v2 = Actor()
    private val v3 = Actor()
    private val v4 = Actor()


    override fun AdvancedGroup.addActorsOnGroup() {
        addGlavna()
        addName()
        addItems()
        addMenu()
        addActorsV()
        addBack()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addGlavna() {
        addActor(glavnaImage)
        glavnaImage.setBounds(21f, 437f, 591f, 850f)
    }

    private fun AdvancedGroup.addName() {
        addActor(namesuLabel)
        namesuLabel.setBounds(21f, 1082f, 588f, 41f)
    }

    private fun AdvancedGroup.addItems() {
        addActor(scrollPanes)
        scrollPanes.setBounds(21f, 26f, 588f, 381f)

        SpriteManager.ListRegion.S.regionList.shuffled().onEach {
            verticGroup.addActor(Image(it).apply { setSize(588f, 112f) })
        }
    }

    private fun AdvancedGroup.addMenu() {
        addActor(menuheImage)
        menuheImage.setBounds(0f, 0f, 631f, 176f)

        val c1 = Actor()
        val c2 = Actor()
        val c3 = Actor()
        val c4 = Actor()

        addActors(c1, c2, c3, c4)
        c1.apply {
            setBounds(45f, 21f, 62f, 62f)
            setOnClickListener {  }
        }
        c2.apply {
            setBounds(201f, 21f, 62f, 62f)
            setOnClickListener { NavigationManager.navigate(StistikaScreen(), GlavniyScreen()) }
        }
        c3.apply {
            setBounds(362f, 21f, 62f, 62f)
            setOnClickListener { NavigationManager.navigate(OperciiScreen(), GlavniyScreen()) }
        }
        c4.apply {
            setBounds(523f, 21f, 62f, 62f)
            setOnClickListener { NavigationManager.navigate(GraphiikScreen(), GlavniyScreen()) }
        }
    }

    private fun AdvancedGroup.addActorsV() {
        addActors(v1, v2, v3, v4)
        v1.apply {
            setBounds(69f, 554f, 76f, 76f)
            setOnClickListener { NavigationManager.navigate(StstaskalakabumScreen(), GlavniyScreen()) }
        }
        v2.apply {
            setBounds(202f, 554f, 76f, 76f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(palitka))) }
        }
        v3.apply {
            setBounds(344f, 554f, 76f, 76f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(termisu))) }
        }
        v4.apply {
            setBounds(487f, 554f, 76f, 76f)
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
    }

    private fun AdvancedGroup.addBack() {
        val back = Actor()
        addActor(back)
        back.setBounds(0f, 1211f, 79f, 91f)
        back.setOnClickListener { NavigationManager.navigate(StstaskalakabumScreen(), GlavniyScreen()) }
    }


//    // ---------------------------------------------------
//    // Logic
//    // ---------------------------------------------------
//
//    private fun number(min: Int, max: Int, count: Int): Long {
//        var numStr = ""
//        repeat(count) { numStr += (min..max).shuffled().first() }
//        return numStr.toLong()
//    }

}