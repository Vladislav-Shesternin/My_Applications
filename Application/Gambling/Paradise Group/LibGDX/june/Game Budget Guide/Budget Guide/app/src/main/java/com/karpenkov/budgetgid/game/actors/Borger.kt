package com.karpenkov.budgetgid.game.actors

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.karpenkov.budgetgid.BuildConfig.APPLICATION_ID
import com.karpenkov.budgetgid.R
import com.karpenkov.budgetgid.appContext
import com.karpenkov.budgetgid.game.game
import com.karpenkov.budgetgid.game.manager.NavigationManager
import com.karpenkov.budgetgid.game.manager.SpriteManager
import com.karpenkov.budgetgid.game.screens.AboutUsScreen
import com.karpenkov.budgetgid.game.screens.CurrencyConverter
import com.karpenkov.budgetgid.game.screens.ThemeScreen
import com.karpenkov.budgetgid.game.utils.actor.setOnClickListener
import com.karpenkov.budgetgid.game.utils.advanced.AdvancedGroup
import com.karpenkov.budgetgid.util.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Borger : AdvancedGroup() {

    private val themeA = Actor()
    private val rateA  = Actor()
    private val aboutA = Actor()
    private val sendA  = Actor()
    private val privaA = Actor()
    private val polzunok = Image(SpriteManager.GameRegion.TO.region)

    private var isRight = true
    var block: (Boolean) -> Unit = {}

    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(Image(SpriteManager.GameRegion.BANAR.region))
            addActirs()
        }
    }

    private fun addActirs() {
        addActors(themeA, rateA, aboutA, sendA, privaA, polzunok)
        themeA.apply {
            setBounds(33f, 1105f, 493f, 110f)
            setOnClickListener { NavigationManager.navigate(ThemeScreen(), CurrencyConverter()) }
        }
        rateA.apply {
            setBounds(33f, 960f, 493f, 110f)
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
        aboutA.apply {
            setBounds(33f, 815f, 493f, 110f)
            setOnClickListener { NavigationManager.navigate(AboutUsScreen(), CurrencyConverter()) }
        }
        sendA.apply {
            setBounds(33f, 670f, 493f, 110f)
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
            setBounds(33f, 525f, 493f, 110f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://karpenkovalentin009.github.io/Budgetgid/pdfdfdf"))) }
        }
        polzunok.apply {
            setBounds(549f, 1164f, 93f, 75f)
            setOnClickListener {
                block(isRight)
                if (isRight) {
                    isRight = false
                    polzunok.drawable = TextureRegionDrawable(SpriteManager.GameRegion.BACK.region)
                } else {
                    isRight = true
                    polzunok.drawable = TextureRegionDrawable(SpriteManager.GameRegion.TO.region)
                }
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------


}