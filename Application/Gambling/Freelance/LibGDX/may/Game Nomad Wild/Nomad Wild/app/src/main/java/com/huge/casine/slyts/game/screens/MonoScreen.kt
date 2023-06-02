package com.huge.casine.slyts.game.screens

import com.huge.casine.slyts.game.actors.button.AButton
import com.huge.casine.slyts.game.actors.button.AButtonStyle
import com.huge.casine.slyts.game.manager.NavigationManager
import com.huge.casine.slyts.game.manager.SpriteManager
import com.huge.casine.slyts.game.utils.Layout
import com.huge.casine.slyts.game.utils.advanced.AdvancedGroup
import com.huge.casine.slyts.game.utils.advanced.AdvancedScreen

class MonoScreen: AdvancedScreen() {

    private val gImage = AButton(AButtonStyle.game)
    private val eImage = AButton(AButtonStyle.exit)


    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.BURAKSA.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
       addIconDuplicator()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addIconDuplicator() {
        addActors(gImage, eImage)
        gImage.apply {
            setBounds(75f, 578f, 389f, 128f)
            setOnClickListener {
                NavigationManager.navigate(SuperMegaScreen(), MonoScreen())
            }
        }
        eImage.apply {
            setBounds(75f, 371f, 389f, 128f)
            setOnClickListener { NavigationManager.exit() }
        }
    }

}