package mobile.jogodobicho.lucky.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import mobile.jogodobicho.lucky.game.LibGDXGame
import mobile.jogodobicho.lucky.game.actors.button.AButton
import mobile.jogodobicho.lucky.game.actors.panel.APanelOptions
import mobile.jogodobicho.lucky.game.utils.TIME_ANIM_SCREEN_ALPHA
import mobile.jogodobicho.lucky.game.utils.actor.animHide
import mobile.jogodobicho.lucky.game.utils.actor.animShow
import mobile.jogodobicho.lucky.game.utils.actor.setOnClickListener
import mobile.jogodobicho.lucky.game.utils.advanced.AdvancedScreen
import mobile.jogodobicho.lucky.game.utils.advanced.AdvancedStage
import mobile.jogodobicho.lucky.game.utils.region

class BullSettingsScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val babImg  = Image(game.gameAssets.MENU_BABOCHKE)
    private val menuBtn = AButton(this, AButton.Static.Type.MENU)
    private val panel   = APanelOptions(this)

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.splashAssets.SUPER_PUPER_BACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addBab()
        addMenuBtn()
        addPanel()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addBab() {
        addActor(babImg)
        babImg.setBounds(-33f, 104f, 1087f, 1708f)
    }

    private fun AdvancedStage.addMenuBtn() {
        addActor(menuBtn)
        menuBtn.apply {
            setBounds(95f, 1740f, 121f, 130f)
            setOnClickListener { backich() }
        }
    }

    private fun AdvancedStage.addPanel() {
        addActor(panel)
        panel.setBounds(-2f, 588f, 1084f, 743f)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun backich() {
        stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }
    }


}