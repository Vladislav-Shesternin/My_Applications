package com.uchimenkoe.financecounter.game.actors

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.google.android.play.core.review.ReviewManagerFactory
import com.uchimenkoe.financecounter.BuildConfig.APPLICATION_ID
import com.uchimenkoe.financecounter.R
import com.uchimenkoe.financecounter.appContext
import com.uchimenkoe.financecounter.game.game
import com.uchimenkoe.financecounter.game.manager.NavigationManager
import com.uchimenkoe.financecounter.game.manager.SpriteManager
import com.uchimenkoe.financecounter.game.screens.CalculatorScreen
import com.uchimenkoe.financecounter.game.screens.PronasScreen
import com.uchimenkoe.financecounter.game.screens.TemkaMoyaScreen
import com.uchimenkoe.financecounter.game.utils.actor.setOnClickListener
import com.uchimenkoe.financecounter.game.utils.advanced.AdvancedGroup
import com.uchimenkoe.financecounter.util.log

class Bugar : AdvancedGroup() {

    private val themeA = Actor()
    private val aboutA = Actor()
    private val sendA  = Actor()
    private val privaA = Actor()
    private val rateA  = Actor()
    private val exit   = Actor()


    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(Image(SpriteManager.GameRegion.BUDGER.region))
            addActur()
        }
    }

    private fun addActur() {
        addActors(themeA, rateA, aboutA, sendA, privaA, exit)
        themeA.apply {
            setBounds(86f, 1139f, 370f, 54f)
            setOnClickListener { NavigationManager.navigate(TemkaMoyaScreen(), CalculatorScreen()) }
        }
        aboutA.apply {
            setBounds(86f, 1409f, 370f, 52f)
            setOnClickListener { NavigationManager.navigate(PronasScreen(), CalculatorScreen()) }
        }
        sendA.apply {
            setBounds(86f, 1274f, 370f, 54f)
            setOnClickListener {
                val text = "Скачай: ${game.activity.getString(R.string.app_name)}"

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
        privaA.apply {
            setBounds(86f, 1005f, 370f, 54f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://andrijsicuk.github.io/FinanceCounter/posdosdsd"))) }
        }
        rateA.apply {
            setBounds(86f, 866f, 370f, 58f)
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
        exit.apply {
            setBounds(73f, 57f, 499f, 172f)
            setOnClickListener { NavigationManager.exit() }
        }

        setOnClickListener { hideAct() }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun showAct() {
        clearActions()
        addAction(Actions.moveTo(0f, 0f, 0.25f))
    }

    fun hideAct() {
        clearActions()
        addAction(Actions.moveTo(-845f, 0f, 0.25f))
    }

}