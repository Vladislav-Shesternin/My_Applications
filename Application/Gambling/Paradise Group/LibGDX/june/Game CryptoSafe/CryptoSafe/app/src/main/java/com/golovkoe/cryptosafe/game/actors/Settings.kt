package com.golovkoe.cryptosafe.game.actors

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.golovkoe.cryptosafe.BuildConfig
import com.golovkoe.cryptosafe.R
import com.golovkoe.cryptosafe.game.game
import com.golovkoe.cryptosafe.game.manager.NavigationManager
import com.golovkoe.cryptosafe.game.manager.SpriteManager
import com.golovkoe.cryptosafe.game.screens.AccountScreen
import com.golovkoe.cryptosafe.game.screens.MainScreen
import com.golovkoe.cryptosafe.game.screens.MuScreen
import com.golovkoe.cryptosafe.game.utils.actor.setOnClickListener
import com.golovkoe.cryptosafe.game.utils.advanced.AdvancedGroup

class Settings : AdvancedGroup() {

    private val accountA = Actor()
    private val viewA    = Actor()
    private val prefA    = Actor()
    private val gethalpA = Actor()
    private val aboutA   = Actor()
    private val exitA    = Actor()


    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(Image(SpriteManager.GameRegion.SETTINGESSAK.region))
            addActkkkkddsksa()
        }
    }

    private fun addActkkkkddsksa() {
        addActors(accountA, viewA, prefA, gethalpA, aboutA, exitA)
        accountA.apply {
            setBounds(0f, 759f, 621f, 56f)
            setOnClickListener { NavigationManager.navigate(AccountScreen(), MainScreen()) }
        }
        viewA.apply {
            setBounds(0f, 608f, 624f, 55f)
            setOnClickListener {
                val text = "Скачивай: ${game.activity.getString(R.string.app_name)}"

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_SUBJECT, text)
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}"
                    )
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, "Отправить:")
                game.activity.startActivity(shareIntent)
            }
        }
        prefA.apply {
            setBounds(0f, 456f, 622f, 56f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://golovkoevgenij835.github.io/CryptoSafe/tdfodfo"))) }
        }
        gethalpA.apply {
            setBounds(0f, 306f, 620f, 54f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://golovkoevgenij835.github.io/CryptoSafe/pdfdfdf"))) }
        }
        aboutA.apply {
            setBounds(0f, 151f, 618f, 59f)
            setOnClickListener { NavigationManager.navigate(MuScreen(), MainScreen()) }
        }
        exitA.apply {
            setBounds(0f, 0f, 624f, 55f)
            setOnClickListener { NavigationManager.exit() }
        }
    }

}