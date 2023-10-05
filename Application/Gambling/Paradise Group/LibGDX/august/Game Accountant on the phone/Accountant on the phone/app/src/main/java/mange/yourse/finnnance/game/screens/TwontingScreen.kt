package mange.yourse.finnnance.game.screens

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import mange.yourse.finnnance.game.LibGDXGame
import mange.yourse.finnnance.game.scroll.VerticalGroup
import mange.yourse.finnnance.game.utils.actor.animHide
import mange.yourse.finnnance.game.utils.actor.setOnClickListener
import mange.yourse.finnnance.game.utils.advanced.AdvancedScreen
import mange.yourse.finnnance.game.utils.advanced.AdvancedStage

class TwontingScreen(override val game: LibGDXGame): AdvancedScreen() {

    // Actor
    private val rashodImg = Image(game.spriteUtil.GAME.LIMITQWQ)
    private val vertep = VerticalGroup(this, 38f, endGap = 0f)
    private val scrulio = ScrollPane(vertep)

    override fun show() {
        //stageUI.root.animHide()
//        setUIBackground(game.spriteUtil.SPLASH.BACKGROUND)
        super.show()
        //stageUI.root.animShow(0.3f)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addashodImg()
        addScrolio()
        addMakiavelly()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addashodImg() {
        addActors(rashodImg)
        rashodImg.apply {
            setBounds(-300f, 72f, 616f, 1296f)
            addAction(Actions.moveTo(0f,72f, 0.5f, Interpolation.bounceOut))
        }
    }

    private fun AdvancedStage.addScrolio() {
        addActors(scrulio)
        scrulio.setBounds(42f, 179f, 521f, 814f)
        scrulio.animHide()

        listOf(
            game.spriteUtil.GAME.BIGI_LIST,
            game.spriteUtil.GAME.BIGI_LIST,
            game.spriteUtil.GAME.BIGI_LIST,
            game.spriteUtil.GAME.BIGI_LIST,
            game.spriteUtil.GAME.BIGI_LIST,
        ).flatten().shuffled().onEach {
            val img = Image(it).apply { setSize(521f, 388f) }
            vertep.addActor(img)
        }

        scrulio.addAction(Actions.fadeIn(0.5f, Interpolation.bounce))

    }

    private fun AdvancedStage.addMakiavelly() {
        val a1 = Actor()
        val a2 = Actor()
        val a3 = Actor()
        val a4 = Actor()
        addActors(a1,a2,a3,a4)
        a1.apply {
            setBounds(19f, 58f, 87f, 87f)
            setOnClickListener { stage.root.animHide(0.5f) { game.navigationManager.navigate(FirstingScreen(game), TwontingScreen(game)) } }
        }
        a2.apply {
            setBounds(145f, 58f, 87f, 87f)
//            setOnClickListener { stage.root.animHide(0.5f) { game.navigationManager.navigate() } }
        }
        a3.apply {
            setBounds(385f, 58f, 87f, 87f)
            setOnClickListener { stage.root.animHide(0.5f) { game.navigationManager.navigate(FirstingScreen(game), TwontingScreen(game)) } }
        }
        a4.apply {
            setBounds(505f, 58f, 87f, 87f)
            setOnClickListener { stage.root.animHide(0.5f) { game.navigationManager.navigate(TrentingScreen(game), TwontingScreen(game)) } }
        }

    }

}