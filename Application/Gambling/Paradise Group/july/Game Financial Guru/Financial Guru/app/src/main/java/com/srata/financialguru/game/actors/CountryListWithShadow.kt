package com.srata.financialguru.game.actors

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.srata.financialguru.game.manager.SpriteManager
import com.srata.financialguru.game.utils.actor.setOnClickListener
import com.srata.financialguru.game.utils.advanced.AdvancedGroup

class CountryListWithShadow: AdvancedGroup() {

    private val popImg  = Image(SpriteManager.GameRegion.POPKA.region)
    private val btnImg  = Image(SpriteManager.GameRegion.SMOTRIK.region)
    private val spComp  = CountryListWithShadowScrollPane()

    private var isSHow = true

    var showBlock = {}
    var hideBlock = {}

    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addActors(spComp, popImg, btnImg)
            spComp.setBounds(0f, 0f, 635f, 1216f)
            popImg.setBounds(0f, 1232f, 309f, 39f)
            btnImg.apply {
                setBounds(478f, 1238f, 148f, 26f)
                setOnClickListener {
                    if (isSHow) {
                        isSHow = false
                        btnImg.drawable = TextureRegionDrawable(SpriteManager.GameRegion.ANONIM.region)
                        showBlock()
                        showAnim()
                    } else {
                        isSHow = true
                        btnImg.drawable = TextureRegionDrawable(SpriteManager.GameRegion.SMOTRIK.region)
                        hideBlock()
                        hideAnim()
                    }
                }
            }
        }
    }

    // ---------------------------------------------------
    // Anim
    // ---------------------------------------------------

    private fun showAnim() {
        addAction(Actions.moveTo(28f, 181f, 0.5f, Interpolation.swing))
    }

    private fun hideAnim() {
        addAction(Actions.moveTo(28f, -449f, 0.5f, Interpolation.swing))
    }

}