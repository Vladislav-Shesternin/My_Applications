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

class TMenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.gameAssets.B_MAIN.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addMusic()
        addMenu()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addMusic() {
        val music = ACheckBox(this@TMenuScreen, ACheckBox.Static.Type.MUSIC)
        addActor(music)
        music.setBounds(925f, 1754f, 114f, 119f)

        game.musicUtil.music?.let { mmm ->
            if (mmm.isPlaying.not()) music.check(false)

            music.setOnCheckListener { if (it) mmm.pause() else mmm.play() }
        }
    }

    private fun AdvancedStage.addMenu() {
        val menuBar = Image(game.gameAssets.F_MENU)
        addActor(menuBar)
        menuBar.setBounds(161f, 268f, 759f, 1541f)

        val names = listOf(
            TChooseScreen::class.java.name,
            TRulesScreen::class.java.name,
            TSettingsScreen::class.java.name,
            TLevelScreen::class.java.name,
            "exit",
        )

        var ny = 948f

        names.onEachIndexed { index, sName ->
            val btn = Actor()
            addActor(btn)
            btn.setBounds(311f, ny, 454f, 139f)
            ny -= (if (index == 3) 55f else 22f) + 139f

            btn.setOnClickListener(game.soundUtil) { navigateGo(sName) }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun navigateGo(sName: String) {
        stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
            if (sName=="exit") game.navigationManager.exit()
            else game.navigationManager.navigate(sName, TMenuScreen::class.java.name)
        }
    }


}