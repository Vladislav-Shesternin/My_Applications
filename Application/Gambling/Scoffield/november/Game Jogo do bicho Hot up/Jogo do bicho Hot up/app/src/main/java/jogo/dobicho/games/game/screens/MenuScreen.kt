package jogo.dobicho.games.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import jogo.dobicho.games.game.LibGDXGame
import jogo.dobicho.games.game.actors.ASplashAnimal
import jogo.dobicho.games.game.actors.checkbox.ACheckBox
import jogo.dobicho.games.game.utils.TIME_ANIM_SCREEN_ALPHA
import jogo.dobicho.games.game.utils.actor.animHide
import jogo.dobicho.games.game.utils.actor.animShow
import jogo.dobicho.games.game.utils.actor.disable
import jogo.dobicho.games.game.utils.actor.enable
import jogo.dobicho.games.game.utils.actor.setOnClickListener
import jogo.dobicho.games.game.utils.advanced.AdvancedScreen
import jogo.dobicho.games.game.utils.advanced.AdvancedStage
import jogo.dobicho.games.game.utils.region

private var isPause = false

class MenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val animal  = ASplashAnimal(this, true)
    private val menu    = Image(game.gameAssets.MENU).apply { color.a = 0f }
    private val cbMusic = ACheckBox(this, ACheckBox.Static.Type.MUSIC).apply { color.a = 0f }

    override fun show() {
        setBackgrounds(game.splashAssets.BACGROUND.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addAnimal()
        addMenu()
        addMusic()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addAnimal() {
       addAndFillActor(animal)
       animal.animAnimateAnimal()
    }

    private fun AdvancedStage.addMenu() {
        animal.addBeetween { addActor(menu) }
        menu.setBounds(177f, 396f, 725f, 1200f)
        menu.animShow(TIME_ANIM_SCREEN_ALPHA)

        val classes = listOf(
            TileScreen::class.java.name,
            RulesScreen::class.java.name,
            SettingsScreen::class.java.name,
            "EXIT",
        )

        var ny = 1238f

        classes.onEach { cId ->
            val btn = Actor()
            addActor(btn)
            btn.setBounds(324f, ny, 418f, 155f)
            ny -= 58+155f

            btn.setOnClickListener(game.soundUtil) { navigateGo(cId) }
        }
    }

    private fun AdvancedStage.addMusic() {
        addActor(cbMusic)
        cbMusic.apply {
            setBounds(890f, 1765f, 117f, 117f)
            animShow(TIME_ANIM_SCREEN_ALPHA)

            if (isPause) check(false) else uncheck(false)

            setOnCheckListener {
                isPause = it
                if (it) game.musicUtil.music?.pause() else game.musicUtil.music?.play()
            }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun navigateGo(id: String) {
        animal.animToStart {}
        cbMusic.animHide(TIME_ANIM_SCREEN_ALPHA)
        menu.animHide(TIME_ANIM_SCREEN_ALPHA) {
            if (id == "EXIT") game.navigationManager.exit()
            else game.navigationManager.navigate(id, this::class.java.name)
        }
    }


}