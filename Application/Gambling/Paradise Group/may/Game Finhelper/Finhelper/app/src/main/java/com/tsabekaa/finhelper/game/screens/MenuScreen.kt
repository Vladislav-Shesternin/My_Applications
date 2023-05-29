package com.tsabekaa.finhelper.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.tsabekaa.finhelper.game.actors.button.AButton
import com.tsabekaa.finhelper.game.actors.button.AButtonStyle
import com.tsabekaa.finhelper.game.manager.FontTTFManager
import com.tsabekaa.finhelper.game.manager.NavigationManager
import com.tsabekaa.finhelper.game.manager.SpriteManager
import com.tsabekaa.finhelper.game.musicUtil
import com.tsabekaa.finhelper.game.utils.GameColor
import com.tsabekaa.finhelper.game.utils.actor.disable
import com.tsabekaa.finhelper.game.utils.actor.setOnClickListener
import com.tsabekaa.finhelper.game.utils.advanced.AdvancedGroup
import com.tsabekaa.finhelper.game.utils.advanced.AdvancedScreen
import com.tsabekaa.finhelper.game.utils.Layout.Menu as LM

class MenuScreen: AdvancedScreen() {
    companion object {
        private var isFirstOpen = true
    }

    private val p1Btn    = AButton(AButtonStyle.btn)
    private val p2Btn    = AButton(AButtonStyle.btn)
    private val p3Btn    = AButton(AButtonStyle.btn)
    private val p1Label  = Label("Продолжить", Label.LabelStyle(FontTTFManager.BoldFont.font_35.font, GameColor.blue))
    private val p2Label  = Label("Новости", Label.LabelStyle(FontTTFManager.BoldFont.font_35.font, GameColor.blue))
    private val p3Label  = Label("Выйти", Label.LabelStyle(FontTTFManager.BoldFont.font_35.font, GameColor.blue))
    private val setImage = Image(SpriteManager.GameRegion.SETTT.region)

    override fun show() {
        if (isFirstOpen) {
            isFirstOpen = false
            musicUtil.apply { music = MAIN.apply { isLooping = true } }
        }

        setBackgrounds(SpriteManager.SplashRegion.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addBtns()
        addSettings()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedGroup.addBtns() {
        addActors(p1Btn, p2Btn, p3Btn)
        addActors(p1Label, p2Label, p3Label)

        p1Label.setBounds(62f, 798f, 479f, 92f)
        p2Label.setBounds(62f, 619f, 479f, 92f)
        p3Label.setBounds(62f, 440f, 479f, 92f)
        p1Label.disable()
        p2Label.disable()
        p3Label.disable()
        p1Label.setAlignment(Align.center)
        p2Label.setAlignment(Align.center)
        p3Label.setAlignment(Align.center)

        LM.btn.also { lay ->
            var ny = lay.y

            p1Btn.apply {
                ny += 0f
                setBounds(lay.x, ny, lay.w, lay.h)
                setOnClickListener { NavigationManager.navigate(ListBankScreen(), MenuScreen()) }
            }
            p2Btn.apply {
                ny -= lay.vs + lay.h
                setBounds(lay.x, ny, lay.w, lay.h)
                setOnClickListener { NavigationManager.navigate(NewsScreen(), MenuScreen()) }
            }
            p3Btn.apply {
                ny -= lay.vs + lay.h
                setBounds(lay.x, ny, lay.w, lay.h)
                setOnClickListener { NavigationManager.exit() }
            }
        }
    }

    private fun AdvancedGroup.addSettings() {
        addActor(setImage)
        setImage.apply {
            setBounds(239f, 164f, 125f, 125f)
            setOnClickListener { NavigationManager.navigate(SettingsScreen(), MenuScreen()) }
        }
    }

}