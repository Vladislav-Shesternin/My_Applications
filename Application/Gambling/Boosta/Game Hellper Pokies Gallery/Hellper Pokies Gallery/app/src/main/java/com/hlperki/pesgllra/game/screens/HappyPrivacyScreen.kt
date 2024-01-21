package com.hlperki.pesgllra.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.hlperki.pesgllra.R
import com.hlperki.pesgllra.game.LibGDXGame
import com.hlperki.pesgllra.game.actors.ACheckBox
import com.hlperki.pesgllra.game.utils.actor.animHide
import com.hlperki.pesgllra.game.utils.actor.animShow
import com.hlperki.pesgllra.game.utils.actor.disable
import com.hlperki.pesgllra.game.utils.actor.setOnClickListener
import com.hlperki.pesgllra.game.utils.advanced.AdvancedScreen
import com.hlperki.pesgllra.game.utils.advanced.AdvancedStage
import com.hlperki.pesgllra.game.utils.font.FontParameter
import com.hlperki.pesgllra.game.utils.region

class HappyPrivacyScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val assets = game.assets

    private val parameterFont  = FontParameter().setCharacters(FontParameter.CharType.ALL).setSize(35)
    private val generatedFont  = fontGeneratorGaladaRegular.generateFont(parameterFont)

    private val text = game.activity.resources.getString(R.string.privacy)

    private val blure = Image(assets.blure2)
    private val ok    = Image(assets.ok)
    private val label = Label(text, Label.LabelStyle(generatedFont, Color.WHITE)).apply { wrap = true }
    private val scrol = ScrollPane(label)

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(assets.Menu.region)
        super.show()
        stageUI.root.animShow(0.3f)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addBlure()
        addOk()
        addScroll()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addBlure() {
        addActor(blure)
        blure.setBounds(173f, 105f, 1154f, 523f)
    }

    private fun AdvancedStage.addOk() {
        addActor(ok)
        ok.setBounds(672f, 20f, 156f, 77f)
        ok.setOnClickListener { navToMenu() }
    }

    private fun AdvancedStage.addScroll() {
        addActor(scrol)
        scrol.setBounds(225f, 205f, 1049f, 351f)
    }

    private fun navToMenu() {
        stageUI.root.animHide(0.3f) {
            game.navigationManager.navigate(HappyMenuScreen::class.java.name)
        }
    }

}