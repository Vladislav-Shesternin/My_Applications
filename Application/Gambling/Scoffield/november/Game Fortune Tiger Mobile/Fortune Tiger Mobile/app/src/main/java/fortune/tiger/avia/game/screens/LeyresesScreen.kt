package fortune.tiger.avia.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import fortune.tiger.avia.game.LibGDXGame
import fortune.tiger.avia.game.utils.Layout
import fortune.tiger.avia.game.utils.TIME_ANIM_SCREEN_ALPHA
import fortune.tiger.avia.game.utils.actor.animHide
import fortune.tiger.avia.game.utils.actor.setBounds
import fortune.tiger.avia.game.utils.actor.setOnClickListener
import fortune.tiger.avia.game.utils.advanced.AdvancedScreen
import fortune.tiger.avia.game.utils.advanced.AdvancedStage
import fortune.tiger.avia.game.utils.region
import java.util.Random

class LeyresesScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val domImg = Image(game.gameAssets.DOMIK)

    override fun show() {
        setBackgrounds(game.gameAssets.LEVELSIMG.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addDom()
        val l = listOf(
            Layout.Leyers.lvl1,
            Layout.Leyers.lvl2,
            Layout.Leyers.lvl3,
            Layout.Leyers.lvl4,
            Layout.Leyers.lvl5,
            Layout.Leyers.lvl6,
            Layout.Leyers.lvl7,
            Layout.Leyers.lvl8,
        )
        List(8) { Actor() }.onEachIndexed { index, a ->
            addActor(a)
            a.setBounds(l[index])
            a.setOnClickListener { navTo() }
        }
    }

    private fun navTo() {
        stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
            game.navigationManager.navigate(if (Random().nextBoolean()) IgrabelnaGraScreen::class.java.name else IgrabelnaGraScreen2::class.java.name)
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addDom() {
        addActor(domImg)
        domImg.apply {
            setBounds(60f, 1725f, 156f, 164f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                    game.navigationManager.back()
                }
            }
        }
    }

}