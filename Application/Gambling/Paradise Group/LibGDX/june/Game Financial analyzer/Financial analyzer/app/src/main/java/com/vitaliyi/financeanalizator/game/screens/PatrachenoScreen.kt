package com.vitaliyi.financeanalizator.game.screens

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.vitaliyi.financeanalizator.BuildConfig
import com.vitaliyi.financeanalizator.R
import com.vitaliyi.financeanalizator.game.actors.LoaderGroup
import com.vitaliyi.financeanalizator.game.game
import com.vitaliyi.financeanalizator.game.manager.FontTTFManager
import com.vitaliyi.financeanalizator.game.manager.NavigationManager
import com.vitaliyi.financeanalizator.game.manager.SpriteManager
import com.vitaliyi.financeanalizator.game.utils.GameColor
import com.vitaliyi.financeanalizator.game.utils.actor.setOnClickListener
import com.vitaliyi.financeanalizator.game.utils.advanced.AdvancedGroup
import com.vitaliyi.financeanalizator.game.utils.advanced.AdvancedScreen
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class PatrachenoScreen: AdvancedScreen() {

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
    private val cartImage     = Image(SpriteManager.GameRegion.KARTA.region)
    private val nameLabel     = Label(namesksd.shuffled().first() + " " + surnamesksd.shuffled().first(), Label.LabelStyle(FontTTFManager.RMedium.font_34.font, Color.WHITE))
    private val menuhaImage   = Image(SpriteManager.GameRegion.MENUHA.region)
    private val babkiLabel    = Label("₽${number(10, 99, 2)},${number(0, 9, 2)}", Label.LabelStyle(FontTTFManager.RRegular.font_66.font, Color.WHITE))
    private val textushImage  = Image(SpriteManager.GameRegion.TEXTUSHKA.region)
    private val menukuhaImage = Image(SpriteManager.GameRegion.MENKUHA.region)
    private val viborImage    = Image(SpriteManager.GameRegion.VIBOR.region)
    private val whiteLoader   = LoaderGroup(SpriteManager.GameRegion.WHITE.region)
    private val gradeLoader   = LoaderGroup(SpriteManager.GameRegion.GRADIK.region)
    private val whiteLabel    = Label("$${number(100, 999, 1)},${number(0, 9, 2)}", Label.LabelStyle(FontTTFManager.RMedium.font_34.font, GameColor.white))
    private val gradiLabel    = Label("$${number(100, 999, 1)},${number(0, 9, 2)}", Label.LabelStyle(FontTTFManager.RMedium.font_34.font, Color.WHITE))



    override fun AdvancedGroup.addActorsOnGroup() {
        addCartka()
        addName()
        addMenuha()
        addBabki()
        addTextuha()
        addMenucha()
        addVibor()
        addLoaders()
        addButtons()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addCartka() {
        addActor(cartImage)
        cartImage.setBounds(-3f, 891f, 684f, 494f)
    }

    private fun AdvancedGroup.addName() {
        addActor(nameLabel)
        nameLabel.setBounds(81f, 1323f, 568f, 46f)
    }

    private fun AdvancedGroup.addMenuha() {
        addActor(menuhaImage)
        menuhaImage.setBounds(63f, 571f, 568f, 316f)
    }

    private fun AdvancedGroup.addBabki() {
        addActor(babkiLabel)
        babkiLabel.setBounds(81f, 639f, 374f, 78f)
    }

    private fun AdvancedGroup.addTextuha() {
        addActor(textushImage)
        textushImage.setBounds(81f, 246f, 501f, 23f)
    }

    private fun AdvancedGroup.addMenucha() {
        addActor(menukuhaImage)
        menukuhaImage.setBounds(54f, 72f, 568f, 135f)

        val star    = Actor()
        val message = Actor()
        val stata   = Actor()
        addActors(star, message, stata)

        message.apply {
            setBounds(509f, 122f, 47f, 43f)
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
        star.apply {
            setBounds(248f, 117f, 57f, 55f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(politka))) }
        }
        stata.apply {
            setBounds(386f, 126f, 41f, 36f)
            setOnClickListener { NavigationManager.navigate(StataScreen(), PatrachenoScreen()) }
        }
    }

    private fun AdvancedGroup.addVibor() {
        addActor(viborImage)
        viborImage.setBounds(128f, 78f, 32f, 32f)
    }

    private fun AdvancedGroup.addLoaders() {
        val backWhite = Image(SpriteManager.GameRegion.BACKGROUNDING_CIRCLE.region)
        val backGradi = Image(SpriteManager.GameRegion.BACKGROUNDING_CIRCLE.region)

        addActors(backWhite, backGradi)

        backWhite.setBounds(70f, 305f, 243f, 243f)
        backGradi.setBounds(370f, 305f, 243f, 243f)


        addActors(whiteLoader, gradeLoader, whiteLabel, gradiLabel)
        whiteLoader.setBounds(70f, 305f, 243f, 243f)
        gradeLoader.setBounds(370f, 305f, 243f, 243f)

        whiteLabel.apply {
            setBounds(93f, 407f, 198f, 40f)
            setAlignment(Align.center)
        }
        gradiLabel.apply {
            setBounds(393f, 407f, 198f, 40f)
            setAlignment(Align.center)
        }

        coroutine.launch {

            var whiteProgress = 0
            var gradiProgress = 0

            val whiteMax = (10..100).shuffled().first()
            val gradiMax = (10..100).shuffled().first()

            launch {
                while (isActive) {
                    if (whiteProgress <= whiteMax) {
                        delay(30)
                        whiteLoader.setProgress(whiteProgress)
                        whiteProgress++
                    } else cancel()
                }
            }
            launch {
                while (isActive) {
                    if (gradiProgress <= gradiMax) {
                        delay(30)
                        gradeLoader.setProgress(gradiProgress)
                        gradiProgress++
                    } else cancel()
                }
            }
        }
    }

    private fun AdvancedGroup.addButtons() {
        val copyNumCard = Actor()
        val showCVV     = Actor()
        val send        = Actor()

        addActors(copyNumCard, showCVV, send)

        copyNumCard.apply {
            setBounds(63f, 803f, 324f, 84f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(politka))) }
        }
        showCVV.apply {
            setBounds(415f, 803f, 216f, 84f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(termsak))) }
        }
        send.apply {
            setBounds(543f, 642f, 81f, 81f)
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

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun number(min: Int, max: Int, count: Int): Long {
        var numStr = ""
        repeat(count) { numStr += (min..max).shuffled().first() }
        return numStr.toLong()
    }

}