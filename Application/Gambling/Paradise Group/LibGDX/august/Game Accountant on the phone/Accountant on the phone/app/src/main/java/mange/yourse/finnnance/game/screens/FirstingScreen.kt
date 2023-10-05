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

class FirstingScreen(override val game: LibGDXGame): AdvancedScreen() {

    // Actor
    private val rashodImg = Image(game.spriteUtil.GAME.RASHOD)
    private val vertep = VerticalGroup(this, 51f)
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
            setBounds(-300f, 0f, 615f, 1289f)
            addAction(Actions.moveTo(0f,0f, 0.5f, Interpolation.bounceOut))
        }
    }

    private fun AdvancedStage.addScrolio() {
        addActors(scrulio)
        scrulio.setBounds(41f, 189f, 523f, 454f)
        scrulio.animHide()

        listOf(
            game.spriteUtil.GAME.ITEM_LIST,
            game.spriteUtil.GAME.ITEM_LIST,
            game.spriteUtil.GAME.ITEM_LIST,
            game.spriteUtil.GAME.ITEM_LIST,
            game.spriteUtil.GAME.ITEM_LIST,
        ).flatten().shuffled().onEach {
            val img = Image(it).apply { setSize(521f, 75f) }
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
           // setOnClickListener { stage.root.animHide(0.5f) { game.navigationManager.navigate() } }
        }
        a2.apply {
            setBounds(145f, 58f, 87f, 87f)
            setOnClickListener { stage.root.animHide(0.5f) { game.navigationManager.navigate(TwontingScreen(game), FirstingScreen(game)) } }
        }
        a3.apply {
            setBounds(385f, 58f, 87f, 87f)
            setOnClickListener { stage.root.animHide(0.5f) { game.navigationManager.navigate(TwontingScreen(game), FirstingScreen(game)) } }
        }
        a4.apply {
            setBounds(505f, 58f, 87f, 87f)
            setOnClickListener { stage.root.animHide(0.5f) { game.navigationManager.navigate(TrentingScreen(game), FirstingScreen(game)) } }
        }

    }

}