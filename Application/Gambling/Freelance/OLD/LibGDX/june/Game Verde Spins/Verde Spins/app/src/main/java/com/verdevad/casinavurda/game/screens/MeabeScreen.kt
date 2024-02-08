package com.verdevad.casinavurda.game.screens

import com.verdevad.casinavurda.game.actors.button.AButton
import com.verdevad.casinavurda.game.actors.button.AButtonStyle
import com.verdevad.casinavurda.game.manager.NavigationManager
import com.verdevad.casinavurda.game.manager.SpriteManager
import com.verdevad.casinavurda.game.utils.advanced.AdvancedGroup
import com.verdevad.casinavurda.game.utils.advanced.AdvancedScreen

class MeabeScreen: AdvancedScreen() {

    private val pImage = AButton(AButtonStyle.play)
    private val eImage = AButton(AButtonStyle.exit)


    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.BKGRNDFK.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
       addPlayerska()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addPlayerska() {
        addActors(pImage, eImage)
        pImage.apply {
            setBounds(123f, 674f, 433f, 182f)
            setOnClickListener {
                NavigationManager.navigate(DranduletScreen(), MeabeScreen())
            }
        }
        eImage.apply {
            setBounds(123f, 465f, 433f, 182f)
            setOnClickListener { NavigationManager.exit() }
        }
    }

}