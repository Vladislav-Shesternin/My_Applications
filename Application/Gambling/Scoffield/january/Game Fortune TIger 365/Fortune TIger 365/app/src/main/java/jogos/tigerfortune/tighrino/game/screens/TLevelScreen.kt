package jogos.tigerfortune.tighrino.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import jogos.tigerfortune.tighrino.game.LibGDXGame
import jogos.tigerfortune.tighrino.game.actors.checkbox.ACheckBox
import jogos.tigerfortune.tighrino.game.utils.TIME_ANIM_SCREEN_ALPHA
import jogos.tigerfortune.tighrino.game.utils.actor.animHide
import jogos.tigerfortune.tighrino.game.utils.actor.animShow
import jogos.tigerfortune.tighrino.game.utils.actor.setOnClickListener
import jogos.tigerfortune.tighrino.game.utils.advanced.AdvancedScreen
import jogos.tigerfortune.tighrino.game.utils.advanced.AdvancedStage
import jogos.tigerfortune.tighrino.game.utils.region

class TLevelScreen(override val game: LibGDXGame) : AdvancedScreen() {

    companion object {
        var LEVEL = Level.EASY
            private set
    }

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.gameAssets.B_MAIN.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addMusic()
        addLevels()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addMusic() {
        val music = ACheckBox(this@TLevelScreen, ACheckBox.Static.Type.MUSIC)
        addActor(music)
        music.setBounds(925f, 1754f, 114f, 119f)

        game.musicUtil.music?.let { mmm ->
            if (mmm.isPlaying.not()) music.check(false)

            music.setOnCheckListener { if (it) mmm.pause() else mmm.play() }
        }
    }

    private fun AdvancedStage.addLevels() {
        val levelBar = Image(game.gameAssets.F_LEVEL)
        addActor(levelBar)
        levelBar.setBounds(161f, 268f, 759f, 1541f)

        var ny = 944f

        Level.values().onEach { lvl ->
            val btn = Actor()
            addActor(btn)
            btn.setBounds(311f, ny, 454f, 139f)
            ny -= 76f + 139f

            btn.setOnClickListener(game.soundUtil) {
                LEVEL = lvl
                navigateToGame()
            }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun navigateToGame() {
        stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
            game.navigationManager.navigate(TGameScreen::class.java.name)
        }
    }

    // ---------------------------------------------------
    // enum
    // ---------------------------------------------------

    enum class Level {
        EASY, NORMAL, HARD
    }


}