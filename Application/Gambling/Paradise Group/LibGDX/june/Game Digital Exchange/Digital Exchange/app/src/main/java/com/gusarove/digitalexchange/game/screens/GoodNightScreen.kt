package com.gusarove.digitalexchange.game.screens

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.google.android.play.core.review.ReviewManagerFactory
import com.gusarove.digitalexchange.BuildConfig.APPLICATION_ID
import com.gusarove.digitalexchange.R
import com.gusarove.digitalexchange.appContext
import com.gusarove.digitalexchange.game.actors.Slow
import com.gusarove.digitalexchange.game.game
import com.gusarove.digitalexchange.game.manager.FontTTFManager
import com.gusarove.digitalexchange.game.manager.NavigationManager
import com.gusarove.digitalexchange.game.manager.SpriteManager
import com.gusarove.digitalexchange.game.utils.actor.setOnClickListener
import com.gusarove.digitalexchange.game.utils.advanced.AdvancedGroup
import com.gusarove.digitalexchange.game.utils.advanced.AdvancedScreen
import com.gusarove.digitalexchange.util.log

class GoodNightScreen: AdvancedScreen() {

    private val paneljanImage  = Image(SpriteManager.GameRegion.PANELJAN.region)
    private val balanceLabel   = Label("$" + (((1..70).shuffled().first() * 1000) + (1..9).shuffled().first() * 100).toString(), Label.LabelStyle(FontTTFManager.LeilaBold.font_80.font, Color.WHITE))
    private val sendA = Actor()
    private val abouA = Actor()
    private val privA = Actor()
    private val rateA = Actor()
    private val slow  = Slow()
    private val ponel = Image(SpriteManager.GameRegion.PONEL.region)
    private val home  = Image(SpriteManager.GameRegion.DOM_PRS.region)
    private val stat  = Image(SpriteManager.GameRegion.STAT_DDF.region)


    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.GARAG.region)
        super.show()
    }


    override fun AdvancedGroup.addActorsOnGroup() {
        addPaneljan()
        addBalance()
        addPanelActors()
        addSlow()

        addActors(ponel, home, stat)
        ponel.setBounds(0f, 0f, 867f, 178f)
        home.setBounds(210f, 24f, 128f, 128f)
        stat.setBounds(528f, 24f, 128f, 128f)
        stat.setOnClickListener { NavigationManager.navigate(StataScreen(), GoodNightScreen()) }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addPaneljan() {
        addActor(paneljanImage)
        paneljanImage.setBounds(72f, 1027f, 722f, 572f)
    }

    private fun AdvancedGroup.addBalance() {
        addActor(balanceLabel)
        balanceLabel.setBounds(155f, 1351f, 557f, 124f)
        balanceLabel.setAlignment(Align.center)
    }

    private fun AdvancedGroup.addPanelActors() {
        addActors(sendA, abouA, privA, rateA)
        sendA.apply {
            setBounds(72f, 1096f, 128f, 126f)
            setOnClickListener {
                val text = "Скачать: ${game.activity.getString(R.string.app_name)}"

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_SUBJECT, text)
                    putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=$APPLICATION_ID")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, "Отправить:")
                game.activity.startActivity(shareIntent)
            }
        }
        abouA.apply {
            setBounds(270f, 1096f, 128f, 126f)
            setOnClickListener { NavigationManager.navigate(AtlantusScreen(), GoodNightScreen()) }
        }
        privA.apply {
            setBounds(468f, 1096f, 128f, 126f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://gusaroveug12311.github.io/Digitalexchange/psddd"))) }
        }
        rateA.apply {
            setBounds(666f, 1096f, 128f, 126f)
            setOnClickListener {
                val manager = ReviewManagerFactory.create(appContext)

                val request = manager.requestReviewFlow()
                request.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val reviewInfo = task.result

                        val flow = manager.launchReviewFlow(game.activity, reviewInfo)
                        flow.addOnCompleteListener { log("ReviewManagerFactory Complete") }
                    } else log(task.exception?.message ?: "ReviewManagerFactory Error")

                }
            }
        }
    }

    private fun AdvancedGroup.addSlow() {
        addActor(slow)
        slow.setBounds(0f, -822f, 867f, 1721f)
    }

}