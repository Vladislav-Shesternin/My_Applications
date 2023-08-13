package com.vitaliyi.financeanalizator.game.screens

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.vitaliyi.financeanalizator.BuildConfig
import com.vitaliyi.financeanalizator.R
import com.vitaliyi.financeanalizator.game.actors.LOA
import com.vitaliyi.financeanalizator.game.actors.scroll.HorizontalGroup
import com.vitaliyi.financeanalizator.game.game
import com.vitaliyi.financeanalizator.game.manager.FontTTFManager
import com.vitaliyi.financeanalizator.game.manager.NavigationManager
import com.vitaliyi.financeanalizator.game.manager.SpriteManager
import com.vitaliyi.financeanalizator.game.utils.GameColor
import com.vitaliyi.financeanalizator.game.utils.actor.setOnClickListener
import com.vitaliyi.financeanalizator.game.utils.advanced.AdvancedGroup
import com.vitaliyi.financeanalizator.game.utils.advanced.AdvancedScreen

class StataScreen: AdvancedScreen() {

    // MainGroup
    private val textImage     = Image(SpriteManager.GameRegion.STATA.region)
    private val dengiLabel    = Label("₽${number(10, 99, 1)} ${number(100, 999, 1)},${number(0, 9, 2)}", Label.LabelStyle(FontTTFManager.RRegular.font_66.font, Color.WHITE))
    private val menukuhaImage = Image(SpriteManager.GameRegion.MENKUHA.region)
    private val viborImage    = Image(SpriteManager.GameRegion.VIBOR.region)
    private val srsImage      = Image(SpriteManager.GameRegion.STS.region)
    private val bukImage      = Image(SpriteManager.GameRegion.BUKOVKI.region)
    private val babloLabel    = Label("₽${number(1, 9, 1)} ${number(100, 999, 1)},${number(0, 9, 2)}", Label.LabelStyle(FontTTFManager.RRegular.font_66.font, GameColor.white))
    private val mabloLabel    = Label("₽${number(100, 999, 1)}", Label.LabelStyle(FontTTFManager.RLight.font_66.font, GameColor.pink))
    private val upppp         = Image(SpriteManager.GameRegion.UVERH.region)
    private val horG       = HorizontalGroup(57f)
    private val scrollPane = ScrollPane(horG)


    override fun AdvancedGroup.addActorsOnGroup() {
        addText()
        addDengi()
        addMenucha()
        addVibor()
        addSts()
        addBablo()
        addAB()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addText() {
        addActor(textImage)
        textImage.setBounds(81f, 1131f, 514f, 226f)
    }

    private fun AdvancedGroup.addDengi() {
        addActor(dengiLabel)
        dengiLabel.setBounds(81f, 1171f, 483f, 78f)
    }

    private fun AdvancedGroup.addMenucha() {
        addActor(menukuhaImage)
        menukuhaImage.setBounds(54f, 72f, 568f, 135f)

        val star    = Actor()
        val message = Actor()
        val home    = Actor()
        addActors(star, message, home)

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
        home.apply {
            setBounds(120f, 121f, 47f, 46f)
            setOnClickListener { NavigationManager.back() }
        }
    }

    private fun AdvancedGroup.addVibor() {
        addActor(viborImage)
        viborImage.setBounds(391f, 78f, 32f, 32f)
    }

    private fun AdvancedGroup.addSts() {
        addActors(srsImage, bukImage)
        srsImage.setBounds(79f, 619f, 512f, 366f)
        bukImage.setBounds(79f, 581f, 514f, 22f)
    }

    private fun AdvancedGroup.addBablo() {
        addActors(babloLabel, mabloLabel, upppp)
        babloLabel.setBounds(81f, 1045f, 329f, 76f)
        mabloLabel.setBounds(413f, 1045f, 162f, 76f)
        upppp.setBounds(575f, 1061f, 27f, 50f)
    }

    private fun AdvancedGroup.addAB() {
        addActor(scrollPane)
        scrollPane.setBounds(0f, 285f, 677f, 242f)

        SpriteManager.ListRegion.B.regionList.onEachIndexed { index, region ->
            horG.addActor(LOA(region, SpriteManager.ListRegion.A.regionList[index]).apply {
                setSize(177f, 242f)
            })
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