package fortune.tiger.avia.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import fortune.tiger.avia.game.LibGDXGame
import fortune.tiger.avia.game.actors.ASetering
import fortune.tiger.avia.game.utils.TIME_ANIM_SCREEN_ALPHA
import fortune.tiger.avia.game.utils.actor.animHide
import fortune.tiger.avia.game.utils.actor.setOnClickListener
import fortune.tiger.avia.game.utils.advanced.AdvancedScreen
import fortune.tiger.avia.game.utils.advanced.AdvancedStage
import fortune.tiger.avia.game.utils.region

class TessingsScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val setering = ASetering(this)
    private val domImg   = Image(game.gameAssets.DOMIK)

    override fun show() {
        setBackgrounds(game.gameAssets.TESSINGSIMG.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addSetering()
        addDom()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addSetering() {
        addActor(setering)
        setering.setBounds(567f, 959f, 250f, 278f)
    }

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