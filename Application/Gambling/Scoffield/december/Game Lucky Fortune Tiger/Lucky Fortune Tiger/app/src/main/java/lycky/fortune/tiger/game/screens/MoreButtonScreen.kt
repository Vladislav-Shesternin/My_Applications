package lycky.fortune.tiger.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import lycky.fortune.tiger.game.LibGDXGame
import lycky.fortune.tiger.game.actors.checkbox.ACheckBox
import lycky.fortune.tiger.game.utils.actor.animShow
import lycky.fortune.tiger.game.utils.actor.animShow_Suspend
import lycky.fortune.tiger.game.utils.actor.setBounds
import lycky.fortune.tiger.game.utils.actor.setOnClickListener
import lycky.fortune.tiger.game.utils.advanced.AdvancedScreen
import lycky.fortune.tiger.game.utils.advanced.AdvancedStage
import lycky.fortune.tiger.game.utils.region
import lycky.fortune.tiger.game.utils.runGDX

class MoreButtonScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val tigerMenu = Image(game.gameAssets.TIGER_MENU).apply { color.a = 0f }
    private val sound     = ACheckBox(this, ACheckBox.Static.Type.SOUND)

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
                addActor(tigerMenu)
                tigerMenu.setBounds(69f, 355f, 942f, 1211f)
                addActor(sound)
                sound.setBounds(836f, 1693f, 227f, 227f)
                if (game.soundUtil.isPause) sound.check(false)
                sound.setOnCheckListener { game.soundUtil.isPause = it }
                addMenuItem()
            }
            delay(400)
            tigerMenu.animShow_Suspend(0.3f)
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addMenuItem() {
        val item = listOf(
            ManyToysScreen::class.java.name,
            GoodLuckRulesScreen::class.java.name,
            VolumeNotificationScreen::class.java.name,
            "TIGER",
        )

        var ny = 1092f
        item.onEach { cName ->
            Actor().also { actor ->
                addActor(actor)
                actor.setBounds(179f, ny, 721f, 192f)
                actor.setOnClickListener(game.soundUtil) { navigateGo(cName) }

                ny -= 29+192f
            }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun navigateGo(id: String) {
        if (id == "TIGER") game.navigationManager.exit() else game.navigationManager.navigate(id, this::class.java.name)
    }


}