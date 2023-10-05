package notconvert.notvalue.notvista.game.screens

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import notconvert.notvalue.notvista.game.LibGDXGame
import notconvert.notvalue.notvista.game.scroll.VerticalGroup
import notconvert.notvalue.notvista.game.utils.actor.animHide
import notconvert.notvalue.notvista.game.utils.actor.setOnClickListener
import notconvert.notvalue.notvista.game.utils.advanced.AdvancedScreen
import notconvert.notvalue.notvista.game.utils.advanced.AdvancedStage

class PrivetNikolaScreen(override val game: LibGDXGame): AdvancedScreen() {

    // Actor
    private val lodImg = Image(game.spriteUtil.GAME.PIRIVETKA)
    private val vertep = VerticalGroup(this, 14f)
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
        addActors(lodImg)
        lodImg.apply {
            setBounds(0f, -1232f, 598f, 1232f)
            addAction(Actions.moveTo(0f,0f, 0.7f, Interpolation.elasticOut))
        }
    }

    private fun AdvancedStage.addScrolio() {
        addActors(scrulio)
        scrulio.setBounds(53f, 223f, 508f, 491f)
        scrulio.animHide()

        listOf(
            game.spriteUtil.GAME.i_LIST,
            game.spriteUtil.GAME.i_LIST,
            game.spriteUtil.GAME.i_LIST,
            game.spriteUtil.GAME.i_LIST,
            game.spriteUtil.GAME.i_LIST,
        ).flatten().shuffled().onEach {
            val img = Image(it).apply { setSize(508f, 87f) }
            vertep.addActor(img)
        }

        scrulio.addAction(Actions.fadeIn(1f, Interpolation.circle))

    }

    private fun AdvancedStage.addMakiavelly() {
        val a1 = Actor()
        addActor(a1)
        a1.apply {
            setBounds(0f, 0f, 598f, 183f)
            setOnClickListener { stage.root.animHide(0.5f) { game.navigationManager.navigate(KurskScreen(game), PrivetNikolaScreen(game)) } }
        }

    }

}