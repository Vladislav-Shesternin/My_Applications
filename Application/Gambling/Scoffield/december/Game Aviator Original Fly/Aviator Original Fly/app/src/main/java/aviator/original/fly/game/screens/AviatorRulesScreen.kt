package aviator.original.fly.game.screens

import aviator.original.fly.game.LibGDXGame
import aviator.original.fly.game.actors.button.AButton
import aviator.original.fly.game.actors.checkbox.ACheckBox
import aviator.original.fly.game.utils.TIME_ANIM_SCREEN_ALPHA
import aviator.original.fly.game.utils.actor.animHide
import aviator.original.fly.game.utils.actor.setOnClickListener
import aviator.original.fly.game.utils.advanced.AdvancedScreen
import aviator.original.fly.game.utils.advanced.AdvancedStage
import aviator.original.fly.game.utils.region
import com.badlogic.gdx.scenes.scene2d.ui.Image

class AviatorRulesScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val musicCB  = ACheckBox(this, ACheckBox.Static.Type.MUSIC)
    private val menuBtn  = AButton(this, AButton.Static.Type.MENU)
    private val rulesImg = Image(game.gameAssets.rules)
    private val exitBtn  = Image(game.gameAssets.exit)


    override fun show() {
        setUIBackground(game.splashAssets.AviatorLoading.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addMusicCB()
        addMenuBtn()
        addExitBtn()
        addRules()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addMusicCB() {
        addActor(musicCB)
        musicCB.apply {
            setBounds(505f, 1211f, 76f, 75f)
            if (isMusic.not()) check(false)

            setOnCheckListener { isCheck ->
                if (isCheck) {
                    isMusic = false
                    game.musicUtil.music?.pause()
                } else {
                    isMusic = true
                    game.musicUtil.music?.play()
                }
            }

        }
    }

    private fun AdvancedStage.addMenuBtn() {
        addActor(menuBtn)
        menuBtn.apply {
            setBounds(19f, 1211f, 76f, 75f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() } }
        }
    }

    private fun AdvancedStage.addExitBtn() {
        addActor(exitBtn)
        exitBtn.apply {
            setBounds(182f, 78f, 236f, 90f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() } }
        }
    }

    private fun AdvancedStage.addRules() {
        addActor(rulesImg)
        rulesImg.setBounds(27f, 353f, 547f, 594f)
    }


}