package com.gusarove.digitalexchange.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.utils.Align
import com.gusarove.digitalexchange.game.manager.FontTTFManager
import com.gusarove.digitalexchange.game.manager.NavigationManager
import com.gusarove.digitalexchange.game.manager.SpriteManager
import com.gusarove.digitalexchange.game.utils.actor.setOnClickListener
import com.gusarove.digitalexchange.game.utils.advanced.AdvancedGroup
import com.gusarove.digitalexchange.game.utils.advanced.AdvancedScreen


class StataScreen: AdvancedScreen() {
    private val r get() = (0..9).shuffled().first()

    private val statisticka = Image(SpriteManager.GameRegion.STATICA.region)
    private val labelIncome      = Label("$${r}${r},${r}${r}${r}", Label.LabelStyle(FontTTFManager.LeilaBold.font_51.font, Color.WHITE)).apply { setAlignment(Align.center) }
    private val labelExpert      = Label("$${r}${r},${r}${r}${r}", Label.LabelStyle(FontTTFManager.LeilaBold.font_51.font, Color.WHITE)).apply { setAlignment(Align.center) }
    private val ponel = Image(SpriteManager.GameRegion.PONEL.region)
    private val home  = Image(SpriteManager.GameRegion.DOM_DF.region)
    private val stat  = Image(SpriteManager.GameRegion.STATA_CK.region)


    override fun AdvancedGroup.addActorsOnGroup() {
        addActor(statisticka)
        addActors(
            labelIncome,
                    labelExpert,
                    ponel,
                    home,
                    stat,
        )
statisticka.setBounds(0f, 178f, 867f, 1557f)
        ponel.setSize(867f, 178f)
        home.setBounds(210f, 24f, 128f, 128f)
        stat.setBounds(528f, 24f, 128f, 128f)
        home.setOnClickListener { NavigationManager.back() }

        labelIncome.setBounds(59f, 1194f, 327f, 79f)
        labelExpert.setBounds(486f, 1194f, 327f, 79f)
    }

}