package com.dankom.financialtracker.game.actors

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.dankom.financialtracker.game.manager.SpriteManager
import com.dankom.financialtracker.game.utils.actor.setOnClickListener
import com.dankom.financialtracker.game.utils.advanced.AdvancedGroup

class PanelGroup: AdvancedGroup() {

    private val seeImg = Image(SpriteManager.GameRegion.SEE.region)

    private var isSee = true


    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(Image(SpriteManager.GameRegion.MEGABUMKA.region))
            addSee()

            val listGroup = ListGroup()
            addActor(listGroup)
            listGroup.setBounds(47f, 0f, 563f, 1250f)
        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun addSee() {
        addActor(seeImg)
        seeImg.apply {
            setBounds(466f,1289f, 162f, 37f)
            setOnClickListener {
                if (isSee) {
                    isSee = false
                    seeImg.drawable = TextureRegionDrawable(SpriteManager.GameRegion.SVERNUT.region)
                    animShow()
                } else {
                    isSee = true
                    seeImg.drawable = TextureRegionDrawable(SpriteManager.GameRegion.SEE.region)
                    animHide()
                }
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun animShow() {
        addAction(Actions.moveTo(0f, 0f, 0.7f))
    }

    private fun animHide() {
        addAction(Actions.moveTo(0f, -629f, 0.7f))
    }

}