package com.levitation.plates.game.actors

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.levitation.plates.game.utils.advanced.AdvancedGroup
import com.levitation.plates.game.utils.advanced.AdvancedScreen

class ATarelkaBlue(override val screen: AdvancedScreen): AdvancedGroup() {

    private val someImg   = Image(screen.game.allAssets.blue_tarelka)
    private val circleImg = Image(screen.game.allAssets.blue)

    override fun addActorsOnGroup() {
        addActor(circleImg)
        circleImg.apply {
            setBounds(-36f, -36f, 276f, 276f)
            setOrigin(Align.center)
            addAction(Actions.forever(Actions.rotateBy(360f, 2.3f, Interpolation.sine)))
        }

        addAndFillActor(someImg)

    }

}