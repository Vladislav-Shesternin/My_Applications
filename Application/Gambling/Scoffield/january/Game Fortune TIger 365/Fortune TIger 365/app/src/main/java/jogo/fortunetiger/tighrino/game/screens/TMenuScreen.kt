package jogo.fortunetiger.tighrino.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import jogo.fortunetiger.tighrino.game.LibGDXGame
import jogo.fortunetiger.tighrino.game.actors.checkbox.ACheckBox
import jogo.fortunetiger.tighrino.game.utils.TIME_ANIM_SCREEN_ALPHA
import jogo.fortunetiger.tighrino.game.utils.actor.animHide
import jogo.fortunetiger.tighrino.game.utils.actor.animShow
import jogo.fortunetiger.tighrino.game.utils.actor.setOnClickListener
import jogo.fortunetiger.tighrino.game.utils.advanced.AdvancedScreen
import jogo.fortunetiger.tighrino.game.utils.advanced.AdvancedStage
import jogo.fortunetiger.tighrino.game.utils.region

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
            "exit",
        )

        var ny = 948f

        names.onEachIndexed { index, sName ->
            val btn = Actor()
            addActor(btn)
            btn.setBounds(311f, ny, 454f, 139f)
            ny -= (if (index == 2) 147f else 57f) + 139f

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