package lycky.fortune.tiger.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import lycky.fortune.tiger.game.LibGDXGame
import lycky.fortune.tiger.game.actors.button.AButton
import lycky.fortune.tiger.game.actors.checkbox.ACheckBox
import lycky.fortune.tiger.game.utils.actor.animShow
import lycky.fortune.tiger.game.utils.actor.animShow_Suspend
import lycky.fortune.tiger.game.utils.actor.setOnClickListener
import lycky.fortune.tiger.game.utils.advanced.AdvancedScreen
import lycky.fortune.tiger.game.utils.advanced.AdvancedStage
import lycky.fortune.tiger.game.utils.region
import lycky.fortune.tiger.game.utils.runGDX

class GoodLuckRulesScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val rul = Image(game.gameAssets.RUL).apply { color.a = 0f }
    private val sound     = ACheckBox(this, ACheckBox.Static.Type.SOUND)
    private val menu     = AButton(this, AButton.Static.Type.MENU)


    override fun show() {
        setBackgrounds(game.splashAssets.FIRST_BACKGROUND.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        coroutine?.launch {
            runGDX {
                addAndFillActor(Image(game.splashAssets.VARIOUS_LUXURY_ITEMS).apply {
                    color.a = 0f
                    animShow(0.3f)
                })
                addActor(sound)
                sound.setBounds(836f, 1693f, 227f, 227f)
                if (game.soundUtil.isPause) sound.check(false)
                sound.setOnCheckListener { game.soundUtil.isPause = it }

                addActor(menu)
                menu.setBounds(16f, 1693f, 227f, 227f)
                menu.setOnClickListener { game.navigationManager.back() }

                addActor(rul)
                rul.setBounds(69f, 135f, 942f, 1431f)
                addBtn()
            }
            delay(400)
            rul.animShow_Suspend(0.3f)
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addBtn() {
        Actor().also { actor ->
            addActor(actor)
            actor.setBounds(372f, 135f, 344f, 344f)
            actor.setOnClickListener(game.soundUtil) { navigateGo() }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun navigateGo() {
        // to GAME
        game.navigationManager.navigate(ManyToysScreen::class.java.name)
    }


}