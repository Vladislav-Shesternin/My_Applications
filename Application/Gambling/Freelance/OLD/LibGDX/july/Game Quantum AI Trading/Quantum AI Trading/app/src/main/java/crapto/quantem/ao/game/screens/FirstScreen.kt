package crapto.quantem.ao.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import crapto.quantem.ao.game.manager.NavigationManager
import crapto.quantem.ao.game.manager.SpriteManager
import crapto.quantem.ao.game.utils.actor.setOnClickListener
import crapto.quantem.ao.game.utils.advanced.AdvancedGroup
import crapto.quantem.ao.game.utils.advanced.AdvancedScreen

class FirstScreen: AdvancedScreen() {

    private val koshelImage = Image(SpriteManager.GameRegion.KOSHEL.region)
    private val clik        = Actor()

    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.BARODADA.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addKoshel()
        addButn()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedGroup.addKoshel() {
        val image = Image(SpriteManager.GameRegion.KOSHEL.region)
        addActor(image)
        image.setBounds(16f, 80f, 645f, 1005f)
    }

    private fun AdvancedGroup.addButn() {
        addActor(clik)
        clik.setBounds(16f, 80f, 645f, 111f)
        clik.setOnClickListener { NavigationManager.navigate(MainScreen()) }
    }

}