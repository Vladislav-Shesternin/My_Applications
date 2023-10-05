package mange.yourse.finnnance.game.screens

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import mange.yourse.finnnance.game.LibGDXGame
import mange.yourse.finnnance.game.utils.actor.animHide
import mange.yourse.finnnance.game.utils.actor.setOnClickListener
import mange.yourse.finnnance.game.utils.advanced.AdvancedScreen
import mange.yourse.finnnance.game.utils.advanced.AdvancedStage

class TrentingScreen(override val game: LibGDXGame): AdvancedScreen() {

    // Actor
    private val rashodImg = Image(game.spriteUtil.GAME.REZUME)

    override fun show() {
        //stageUI.root.animHide()
//        setUIBackground(game.spriteUtil.SPLASH.BACKGROUND)
        super.show()
        //stageUI.root.animShow(0.3f)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addashodImg()
      addMakiavelly()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addashodImg() {
        addActors(rashodImg)
        rashodImg.apply {
            setBounds(-300f, 75f, 567f, 1226f)
            addAction(Actions.moveTo(32f,75f, 0.5f, Interpolation.bounceOut))
        }
    }

    private fun AdvancedStage.addMakiavelly() {
        val a1 = Actor()
        val a2 = Actor()
        val a3 = Actor()
        val a4 = Actor()
        addActors(a1,a2,a3,a4)
        a1.apply {
            setBounds(19f, 58f, 87f, 87f)
            setOnClickListener { stage.root.animHide(0.5f) { game.navigationManager.navigate(FirstingScreen(game), TrentingScreen(game)) } }
        }
        a2.apply {
            setBounds(145f, 58f, 87f, 87f)
            setOnClickListener { stage.root.animHide(0.5f) { game.navigationManager.navigate(TwontingScreen(game), TrentingScreen(game)) } }
        }
        a3.apply {
            setBounds(385f, 58f, 87f, 87f)
            setOnClickListener { stage.root.animHide(0.5f) { game.navigationManager.navigate(TwontingScreen(game), TrentingScreen(game)) } }
        }
        a4.apply {
            setBounds(505f, 58f, 87f, 87f)
            //setOnClickListener { stage.root.animHide(0.5f) { game.navigationManager.navigate() } }
        }

    }

}