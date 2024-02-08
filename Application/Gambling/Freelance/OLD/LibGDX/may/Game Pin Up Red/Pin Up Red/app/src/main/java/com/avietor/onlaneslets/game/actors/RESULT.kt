package com.avietor.onlaneslets.game.actors

import com.avietor.onlaneslets.game.actors.button.AButtonStyle
import com.avietor.onlaneslets.game.actors.button.AButtonText
import com.avietor.onlaneslets.game.manager.FontTTFManager
import com.avietor.onlaneslets.game.manager.NavigationManager
import com.avietor.onlaneslets.game.manager.SpriteManager
import com.avietor.onlaneslets.game.screens.levels.LevelScreen
import com.avietor.onlaneslets.game.utils.actor.disable
import com.avietor.onlaneslets.game.utils.actor.enable
import com.avietor.onlaneslets.game.utils.actor.setOnClickListener
import com.avietor.onlaneslets.game.utils.advanced.AdvancedGroup
import com.avietor.onlaneslets.game.utils.advanced.AdvancedScreen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align

class RESULT: AdvancedGroup() {

    private val backgro = Image()
    private val restart = AButtonText("Restart", AButtonStyle.default, Label.LabelStyle(FontTTFManager.RegularFont.font_90.font, Color.WHITE))
    private val menu    = AButtonText("Menu", AButtonStyle.default, Label.LabelStyle(FontTTFManager.RegularFont.font_90.font, Color.WHITE))
    private val text    = Label("", Label.LabelStyle(FontTTFManager.RegularFont.font_90.font, Color.WHITE))


    override fun sizeChanged() {
        if (width>0&&height>0) addAcaarar()
    }

    private fun addAcaarar() {
        addAndFillActor(backgro)
        addActors(restart, menu, text)
        restart.apply {
            setBounds(118f, 721f, 465f, 180f)
        }
        menu.apply {
            setBounds(118f, 500f, 465f, 180f)
            setOnClickListener { NavigationManager.back() }
        }
        text.apply {
            setBounds(118f, 942f, 465f, 106f)
            setAlignment(Align.center)
        }

        disable()
        addAction(Actions.alpha(0f))
    }


    fun show(screen: LevelScreen, type: Type) {

        restart.setOnClickListener { NavigationManager.navigate(screen) }

        when(type) {
            Type.WIN -> {
                backgro.drawable = TextureRegionDrawable(SpriteManager.GameRegion.WIN.region)
                text.setText("WIN =)")
            }
            Type.FAIL -> {
                backgro.drawable = TextureRegionDrawable(SpriteManager.GameRegion.FAIL.region)
                text.setText("FAIL =(")
            }
        }

        enable()
        addAction(Actions.fadeIn(0.4f))
    }

    enum class Type {
        WIN, FAIL
    }



}