package com.vachykm.investmentmanager.game.actors

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.vachykm.investmentmanager.game.manager.SpriteManager
import com.vachykm.investmentmanager.game.utils.actor.setOnClickListener
import com.vachykm.investmentmanager.game.utils.advanced.AdvancedGroup

class ListikGroup: AdvancedGroup() {

    private val luchImg = Image(SpriteManager.GameRegion.LUCH_AKC.region)
    private val btnImg  = Image(SpriteManager.GameRegion.POK.region)
    private val spComp  = ScrollPaneCompany()

    private var isSHow = true

    var showBlock = {}
    var hideBlock = {}

    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addActors(spComp, luchImg, btnImg)
            spComp.setBounds(0f, 0f, 606f, 1203f)
            luchImg.setBounds(0f, 1231f, 189f, 30f)
            btnImg.apply {
                setBounds(468f, 1233f, 135f, 23f)
                setOnClickListener {
                    if (isSHow) {
                        isSHow = false
                        btnImg.drawable = TextureRegionDrawable(SpriteManager.GameRegion.SKR.region)
                        showBlock()
                        showAnim()
                    } else {
                        isSHow = true
                        btnImg.drawable = TextureRegionDrawable(SpriteManager.GameRegion.POK.region)
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
        addAction(Actions.moveTo(22f, 113f, 0.4f))
    }

    private fun hideAnim() {
        addAction(Actions.moveTo(22f, -460f, 0.4f))
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun update() {
        spComp.update()
    }

}