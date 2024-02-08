package jogos.tigerfortune.tighrino.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import jogos.tigerfortune.tighrino.game.LibGDXGame
import jogos.tigerfortune.tighrino.game.actors.button.AButton
import jogos.tigerfortune.tighrino.game.actors.checkbox.ACheckBox
import jogos.tigerfortune.tighrino.game.utils.TIME_ANIM_SCREEN_ALPHA
import jogos.tigerfortune.tighrino.game.utils.actor.animHide
import jogos.tigerfortune.tighrino.game.utils.actor.animShow
import jogos.tigerfortune.tighrino.game.utils.actor.setOnClickListener
import jogos.tigerfortune.tighrino.game.utils.advanced.AdvancedScreen
import jogos.tigerfortune.tighrino.game.utils.advanced.AdvancedStage
import jogos.tigerfortune.tighrino.game.utils.region

class TRulesScreen(override val game: LibGDXGame) : AdvancedScreen() {

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.gameAssets.B_MAIN.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addBack()
        addMusic()
        addRules()
        addPlay()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addBack() {
        val menu = AButton(this@TRulesScreen, AButton.Static.Type.EXIT)
        addActor(menu)
        menu.setBounds(44f, 1754f, 114f, 119f)

        menu.setOnClickListener(game.soundUtil) {
            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }
        }
    }

    private fun AdvancedStage.addMusic() {
        val music = ACheckBox(this@TRulesScreen, ACheckBox.Static.Type.MUSIC)
        addActor(music)
        music.setBounds(925f, 1754f, 114f, 119f)

        game.musicUtil.music?.let { mmm ->
            if (mmm.isPlaying.not()) music.check(false)

            music.setOnCheckListener { if (it) mmm.pause() else mmm.play() }
        }
    }

    private fun AdvancedStage.addRules() {
        val rules = Image(game.gameAssets.F_RULES)
        addActor(rules)
        rules.setBounds(116f, 45f, 859f, 1867f)
    }

    private fun AdvancedStage.addPlay() {
        val ok = Actor()
        addActor(ok)
        ok.setBounds(313f, 45f, 454f, 140f)

        ok.setOnClickListener(game.soundUtil) {
            game.navigationManager.navigate(TChooseScreen::class.java.name)
        }
    }

}