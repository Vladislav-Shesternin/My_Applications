package aviator.original.win.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import aviator.original.win.game.LibGDXGame
import aviator.original.win.game.actors.button.AButton
import aviator.original.win.game.actors.checkbox.ACheckBox
import aviator.original.win.game.utils.TIME_ANIM_SCREEN_ALPHA
import aviator.original.win.game.utils.actor.animHide
import aviator.original.win.game.utils.actor.animShow
import aviator.original.win.game.utils.actor.setOnClickListener
import aviator.original.win.game.utils.advanced.AdvancedScreen
import aviator.original.win.game.utils.advanced.AdvancedStage
import aviator.original.win.game.utils.region

class OriginalRulesScreen(override val game: LibGDXGame) : AdvancedScreen() {

    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.gameAssets.MainBackground.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addBack()
        addMusic()
        addRules()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addBack() {
        val menu = AButton(this@OriginalRulesScreen, AButton.Static.Type.MENU)
        addActor(menu)
        menu.setBounds(22f, 1281f, 99f, 99f)

        menu.setOnClickListener(game.soundUtil) {
            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }
        }
    }

    private fun AdvancedStage.addMusic() {
        val music = ACheckBox(this@OriginalRulesScreen, ACheckBox.Static.Type.MUSIC)
        addActor(music)
        music.setBounds(520f, 1281f, 98f, 98f)

        game.musicUtil.music?.let { mmm ->
            if (mmm.isPlaying.not()) music.check(false)

            music.setOnCheckListener { if (it) mmm.pause() else mmm.play() }
        }
    }

    private fun AdvancedStage.addRules() {
        val rules = Image(game.gameAssets.Rules)
        addActor(rules)
        rules.setBounds(17f, 236f, 614f, 929f)
    }

}