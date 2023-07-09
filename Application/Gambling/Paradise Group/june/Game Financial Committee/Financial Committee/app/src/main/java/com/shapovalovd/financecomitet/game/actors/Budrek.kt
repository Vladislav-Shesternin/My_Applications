package com.shapovalovd.financecomitet.game.actors

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.google.android.play.core.review.ReviewManagerFactory
import com.shapovalovd.financecomitet.BuildConfig.APPLICATION_ID
import com.shapovalovd.financecomitet.R
import com.shapovalovd.financecomitet.appContext
import com.shapovalovd.financecomitet.game.game
import com.shapovalovd.financecomitet.game.manager.NavigationManager
import com.shapovalovd.financecomitet.game.manager.SpriteManager
import com.shapovalovd.financecomitet.game.screens.HomeScreen
import com.shapovalovd.financecomitet.game.screens.OnasScreen
import com.shapovalovd.financecomitet.game.screens.TemaScreen
import com.shapovalovd.financecomitet.game.utils.actor.setOnClickListener
import com.shapovalovd.financecomitet.game.utils.advanced.AdvancedGroup
import com.shapovalovd.financecomitet.util.log

class Budrek : AdvancedGroup() {

    private val themeA = Actor()
    private val aboutA = Actor()
    private val sendA  = Actor()
    private val privaA = Actor()
    private val rateA  = Actor()
    private val back   = Actor()


    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(Image(SpriteManager.GameRegion.BADABURGERRA.region))
            addActorikas()
        }
    }

    private fun addActorikas() {
        addActors(themeA, rateA, aboutA, sendA, privaA, back)
        themeA.apply {
            setBounds(34f, 1010f, 363f, 54f)
            setOnClickListener { NavigationManager.navigate(TemaScreen(), HomeScreen()) }
        }
        aboutA.apply {
            setBounds(34f, 903f, 363f, 54f)
            setOnClickListener { NavigationManager.navigate(OnasScreen(), HomeScreen()) }
        }
        sendA.apply {
            setBounds(34f, 815f, 363f, 54f)
            setOnClickListener {
                val text = "Скачивай: ${game.activity.getString(R.string.app_name)}"

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
            setBounds(34f, 726f, 363f, 54f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://dmitrijsapavalov43.github.io/Financecomitet/pdkdkf"))) }
        }
        rateA.apply {
            setBounds(34f, 633f, 363f, 54f)
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
        back.apply {
            setBounds(472f, 85f, 164f, 166f)
            setOnClickListener { hideAct() }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun showAct() {
        clearActions()
        addAction(Actions.moveTo(0f, 0f, 0.3f))
    }

    fun hideAct() {
        clearActions()
        addAction(Actions.moveTo(0f, -1466f, 0.3f))
    }

}