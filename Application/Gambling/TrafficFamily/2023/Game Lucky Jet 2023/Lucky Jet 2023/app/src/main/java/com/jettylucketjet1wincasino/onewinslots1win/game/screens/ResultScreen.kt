package com.jettylucketjet1wincasino.onewinslots1win.game.screens

import android.content.Intent
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.jettylucketjet1wincasino.onewinslots1win.BuildConfig
import com.jettylucketjet1wincasino.onewinslots1win.R
import com.jettylucketjet1wincasino.onewinslots1win.game.actors.button.AButtonStyle
import com.jettylucketjet1wincasino.onewinslots1win.game.actors.button.AButtonText
import com.jettylucketjet1wincasino.onewinslots1win.game.actors.label.ALabelStyle
import com.jettylucketjet1wincasino.onewinslots1win.game.game
import com.jettylucketjet1wincasino.onewinslots1win.game.manager.NavigationManager
import com.jettylucketjet1wincasino.onewinslots1win.game.manager.SpriteManager
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.MAIN_ANIM_SPEED
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.actor.setBounds
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.advanced.AdvancedGroup
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.advanced.AdvancedScreen
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.Layout.Result as LR

class ResultScreen: AdvancedScreen() {

    private val countLabel    = Label("${GameScreen.tapCount}", ALabelStyle.style(ALabelStyle.Bold._19))
    private val percentLabel  = Label("${(10..99).shuffled().first()}%", ALabelStyle.style(ALabelStyle.Regular._19))
    private val restartButton = AButtonText("Играть", AButtonStyle.btn, ALabelStyle.style(ALabelStyle.Bold._21))
    private val sendButton    = AButtonText("Поделиться", AButtonStyle.nbtn, ALabelStyle.style(ALabelStyle.Bold._21))


    override fun show() {
        setBackgrounds(SpriteManager.GameRegion.BACKGROUND_2.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        mainGroup.addAction(Actions.alpha(0f))
        addResult()
        addButtons()
        mainGroup.addAction(Actions.fadeIn(MAIN_ANIM_SPEED))
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addResult() {
        addActors(countLabel, percentLabel)
        countLabel.apply {
            setBounds(LR.count)
            setAlignment(Align.center)
        }
        percentLabel.apply {
            setBounds(LR.percent)
            setAlignment(Align.center)
        }
    }

    private fun AdvancedGroup.addButtons() {
        addActors(restartButton, sendButton)
        restartButton.apply {
            setBounds(LR.restart)
            setOnClickListener { navToGame() }
        }
        sendButton.apply {
            setBounds(LR.send)
            setOnClickListener {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_SUBJECT, "⬇️ ${game.activity.getString(R.string.app_name)} ⬇️\n" + "Скачай и наслаждайся игрой!\n")
                    putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, "Отправить:")
                game.activity.startActivity(shareIntent)
            }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun navToGame() {
        mainGroup.addAction(Actions.sequence(
            Actions.fadeOut(MAIN_ANIM_SPEED),
            Actions.run { NavigationManager.navigate(GameScreen()) }
        ))
    }

}