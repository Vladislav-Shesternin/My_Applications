package com.veldan.boost.and.clean.simpleapp.game.actors

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.veldan.boost.and.clean.simpleapp.game.manager.SpriteManager
import com.veldan.boost.and.clean.simpleapp.game.utils.actor.setBounds
import com.veldan.boost.and.clean.simpleapp.game.utils.advanced.AdvancedGroup
import com.veldan.boost.and.clean.simpleapp.game.utils.Layout.Snowflake as LS

class Snowflake: AdvancedGroup() {

    private val image  = Image(SpriteManager.CoolingRegion.SNOWFLAKE.region)
    private val arrows = Image(SpriteManager.CommonRegion.ARROWS.region)


    override fun sizeChanged() {
        super.sizeChanged()
        if(width > 0 && height > 0) addActorsOnGroup()
    }


    private fun addActorsOnGroup() {
        addImages()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun addImages() {
        addAndFillActor(arrows)
        addActor(image)
        image.apply {
            setBounds(LS.snowflake)
            setOrigin(Align.center)
        }
        arrows.setOrigin(Align.center)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    fun startAnim() {
        val scaleValue = 0.1f
        image.addAction(Actions.forever(Actions.sequence(
            Actions.scaleBy(-scaleValue, -scaleValue, 0.3f, Interpolation.fade),
            Actions.scaleBy(scaleValue, scaleValue, 0.3f, Interpolation.fade),
        )))

        arrows.addAction(Actions.forever(Actions.rotateBy(-360f, 2f, Interpolation.fade)))
    }

    fun finishAnim() {
        image.clearActions()
        arrows.clearActions()
        setOrigin(Align.center)
        addAction(Actions.scaleTo(0f, 0f, 0.7f, Interpolation.swing))
    }

}