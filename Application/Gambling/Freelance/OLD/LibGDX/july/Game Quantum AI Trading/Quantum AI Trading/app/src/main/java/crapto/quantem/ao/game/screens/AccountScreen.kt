package crapto.quantem.ao.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import crapto.quantem.ao.game.manager.FontTTFManager
import crapto.quantem.ao.game.manager.NavigationManager
import crapto.quantem.ao.game.manager.SpriteManager
import crapto.quantem.ao.game.utils.actor.setOnClickListener
import crapto.quantem.ao.game.utils.advanced.AdvancedGroup
import crapto.quantem.ao.game.utils.advanced.AdvancedScreen


class AccountScreen: AdvancedScreen() {

    private val listIm = List(6) { Image(SpriteManager.ListRegion.ACCOUNT.regionList[it]) }
    private val back   = Image(SpriteManager.GameRegion.BACK.region)


    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.BARODADA.region)
        super.show()
    }
    override fun AdvancedGroup.addActorsOnGroup() {

        var ny = 1127f
        listIm.onEachIndexed { index, image ->
            addActor(image)
            image.setBounds(118f, ny, 440f, 98f)
            ny -= 50f + 98f
            image.setOnClickListener { NavigationManager.back(index) }
        }

        addActor(back)
        back.apply {
            setBounds(277f, 61f, 156f, 60f)
            setOnClickListener { NavigationManager.back() }
        }
    }

}