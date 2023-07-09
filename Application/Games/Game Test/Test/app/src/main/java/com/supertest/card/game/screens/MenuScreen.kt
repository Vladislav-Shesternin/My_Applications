package com.supertest.card.game.screens

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.utils.Align
import com.supertest.card.game.actors.button.AButtonStyle
import com.supertest.card.game.actors.button.AButtonText
import com.supertest.card.game.actors.label.ALabelStyle
import com.supertest.card.game.manager.NavigationManager
import com.supertest.card.game.manager.SpriteManager
import com.supertest.card.util.AppsflyerUtil
import com.supertest.card.game.utils.MAIN_ANIM_SPEED
import com.supertest.card.game.utils.actor.setBounds
import com.supertest.card.game.utils.advanced.AdvancedGroup
import com.supertest.card.game.utils.advanced.AdvancedScreen
import com.supertest.card.game.utils.Layout.Menu as LM

class MenuScreen: AdvancedScreen() {

    private val play       = AButtonText("Play", AButtonStyle.btn, ALabelStyle.style(ALabelStyle.Regular._60))
    private val appsflayer = AButtonText("Appsflayer", AButtonStyle.btn, ALabelStyle.style(ALabelStyle.Regular._60))
    private val exit       = AButtonText("Exit", AButtonStyle.btn, ALabelStyle.style(ALabelStyle.Regular._60))
    private val appsLabel  = Label("${AppsflyerUtil.conversionFlow.value}", ALabelStyle.style(ALabelStyle.Regular._60))
    private val appsScroll = ScrollPane(appsLabel)

    private val btns = listOf(play, appsflayer, exit)

    private var isShowApps = false



    override fun show() {
        setUIBackground(SpriteManager.SplashRegion.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        mainGroup.addAction(Actions.alpha(0f))
        addAppsLabel()
        addButtons()
        mainGroup.addAction(Actions.fadeIn(MAIN_ANIM_SPEED))
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addButtons() {
        addActors(play, appsflayer, exit)

        var ny = LM.buttons.y
        btns.onEach {
            with(LM.buttons) {
                it.setBounds(x, ny, w, h)
                ny -= vs + h
            }
        }

        play.setOnClickListener { navToGame() }
        appsflayer.setOnClickListener { if (isShowApps) hideAppsflayerData() else showAppsflayerData()}
        exit.setOnClickListener { NavigationManager.exit() }
    }

    private fun AdvancedGroup.addAppsLabel() {
        addActor(appsScroll)
        appsLabel.apply {
            wrap = true
            setAlignment(Align.center)
        }
        appsScroll.apply {
            addAction(Actions.alpha(0f))
            setBounds(LM.scroll)
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun navToGame() {
        mainGroup.addAction(Actions.sequence(
            Actions.fadeOut(MAIN_ANIM_SPEED),
            Actions.run { NavigationManager.navigate(GameScreen(), MenuScreen()) }
        ))
    }

    private fun showAppsflayerData() {
        appsflayer.disable()
        isShowApps = true

        btns.onEach {
            it.addAction(Actions.moveTo(LM.btnX, it.y, MAIN_ANIM_SPEED))
        }
        appsScroll.addAction(Actions.sequence(
            Actions.fadeIn(MAIN_ANIM_SPEED * 2),
            Actions.run { appsflayer.enable() }
        ))
    }

    private fun hideAppsflayerData() {
        appsflayer.disable()
        isShowApps = false

        btns.onEach {
            it.addAction(Actions.moveTo(LM.buttons.x, it.y, MAIN_ANIM_SPEED))
        }
        appsScroll.addAction(Actions.sequence(
            Actions.fadeOut(MAIN_ANIM_SPEED / 2),
            Actions.run { appsflayer.enable() }
        ))
    }

}