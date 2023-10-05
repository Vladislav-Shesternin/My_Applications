package notconvert.notvalue.notvista.game.screens

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import notconvert.notvalue.notvista.game.LibGDXGame
import notconvert.notvalue.notvista.game.utils.actor.animHide
import notconvert.notvalue.notvista.game.utils.actor.setOnClickListener
import notconvert.notvalue.notvista.game.utils.advanced.AdvancedScreen
import notconvert.notvalue.notvista.game.utils.advanced.AdvancedStage

class ProdoljateScreen(override val game: LibGDXGame): AdvancedScreen() {

    // Actor
    private val rashodImg = Image(game.spriteUtil.GAME.DELAY)
//    private val vertep = VerticalGroup(this, 51f)
//    private val scrulio = ScrollPane(vertep)

    override fun show() {
        //stageUI.root.animHide()
//        setUIBackground(game.spriteUtil.SPLASH.BACKGROUND)
        super.show()
        //stageUI.root.animShow(0.3f)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addashodImg()
//        addScrolio()
//        addMakiavelly()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addashodImg() {
        addActors(rashodImg)
        rashodImg.apply {
            setBounds(0f, -1124f, 598f, 1124f)
            addAction(Actions.moveTo(0f,170f, 0.7f, Interpolation.elasticOut))
        }

        val dale = Actor()
        addActor(dale)
        dale.apply {
            setBounds(53f, 170f, 530f, 82f)
            setOnClickListener {
                stageUI.root.animHide(0.3f) { game.navigationManager.navigate(PrivetNikolaScreen(game), ProdoljateScreen(game)) }
            }
        }
    }

//    private fun AdvancedStage.addScrolio() {
//        addActors(scrulio)
//        scrulio.setBounds(41f, 189f, 523f, 454f)
//        scrulio.animHide()
//
//        listOf(
//            game.spriteUtil.GAME.i_LIST,
//            game.spriteUtil.GAME.i_LIST,
//            game.spriteUtil.GAME.i_LIST,
//            game.spriteUtil.GAME.i_LIST,
//            game.spriteUtil.GAME.i_LIST,
//        ).flatten().shuffled().onEach {
//            val img = Image(it).apply { setSize(521f, 75f) }
//            vertep.addActor(img)
//        }
//
//        scrulio.addAction(Actions.fadeIn(0.5f, Interpolation.bounce))
//
//    }
//
//    private fun AdvancedStage.addMakiavelly() {
//        val a1 = Actor()
//        val a2 = Actor()
//        val a3 = Actor()
//        val a4 = Actor()
//        addActors(a1,a2,a3,a4)
//        a1.apply {
//            setBounds(19f, 58f, 87f, 87f)
//           // setOnClickListener { stage.root.animHide(0.5f) { game.navigationManager.navigate() } }
//        }
//        a2.apply {
//            setBounds(145f, 58f, 87f, 87f)
//            setOnClickListener { stage.root.animHide(0.5f) { game.navigationManager.navigate(TwontingScreen(game), ProdoljateScreen(game)) } }
//        }
//        a3.apply {
//            setBounds(385f, 58f, 87f, 87f)
//            setOnClickListener { stage.root.animHide(0.5f) { game.navigationManager.navigate(TwontingScreen(game), ProdoljateScreen(game)) } }
//        }
//        a4.apply {
//            setBounds(505f, 58f, 87f, 87f)
//            setOnClickListener { stage.root.animHide(0.5f) { game.navigationManager.navigate(TrentingScreen(game), ProdoljateScreen(game)) } }
//        }
//
//    }

}