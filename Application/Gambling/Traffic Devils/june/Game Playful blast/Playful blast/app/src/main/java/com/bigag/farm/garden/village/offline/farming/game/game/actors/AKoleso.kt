package com.bigag.farm.garden.village.offline.farming.game.game.actors

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.bigag.farm.garden.village.offline.farming.game.game.utils.advanced.AdvancedGroup
import com.bigag.farm.garden.village.offline.farming.game.game.utils.advanced.AdvancedScreen

class AKoleso(override val screen: AdvancedScreen): com.bigag.farm.garden.village.offline.farming.game.game.utils.advanced.AdvancedGroup() {

    private val image = Image(screen.game.assets.kolo)

    override fun addActorsOnGroup() {
        addAndFillActor(image)
        image.apply {
            setOrigin(Align.center)
            addAction(Actions.forever(Actions.rotateBy(-360f, 5f)))
        }
    }

}