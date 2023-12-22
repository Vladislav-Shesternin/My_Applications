package mobile.jogodobicho.lucky.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import mobile.jogodobicho.lucky.game.LibGDXGame
import mobile.jogodobicho.lucky.game.actors.checkbox.ACheckBox
import mobile.jogodobicho.lucky.game.utils.TIME_ANIM_SCREEN_ALPHA
import mobile.jogodobicho.lucky.game.utils.actor.animHide
import mobile.jogodobicho.lucky.game.utils.actor.animShow
import mobile.jogodobicho.lucky.game.utils.actor.setOnClickListener
import mobile.jogodobicho.lucky.game.utils.advanced.AdvancedScreen
import mobile.jogodobicho.lucky.game.utils.advanced.AdvancedStage
import mobile.jogodobicho.lucky.game.utils.region

class BullMenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val babImg  = Image(game.gameAssets.MENU_BABOCHKE)
    private val musicCB = ACheckBox(this, ACheckBox.Static.Type.MUSIC)
    private val menuImg = Image(game.gameAssets.BULL_MENU)

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.splashAssets.SUPER_PUPER_BACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addBab()
        addMusicCB()
        addMenuImg()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addBab() {
        addActor(babImg)
        babImg.setBounds(-33f, 104f, 1087f, 1708f)
    }

    private fun AdvancedStage.addMusicCB() {
        addActor(musicCB)
        musicCB.apply {
            setBounds(873f, 1740f, 121f, 130f)
            if (LibGDXGame.isMusic.not()) check(false)

            setOnCheckListener {
                if (it) {
                    LibGDXGame.isMusic = false
                    game.musicUtil.music?.pause()
                } else {
                    LibGDXGame.isMusic = true
                    game.musicUtil.music?.play()
                }
            }

        }
    }

    private fun AdvancedStage.addMenuImg() {
        addActor(menuImg)
        menuImg.setBounds(90f, 295f, 853f, 1212f)

        var ny = 1025f
        listOf(
            BullGameScreen       ::class.java.name,
            BullChooseLevelScreen::class.java.name,
            BullSettingsScreen   ::class.java.name,
            "backend",
        ).onEach { screenName ->
            Actor().also { actor ->
                addActor(actor)
                actor.apply {
                    setBounds(188f, ny, 656f, 197f)
                    ny -= 196f

                    setOnClickListener(game.soundUtil) { navToScrNam(screenName) }
                }
            }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun navToScrNam(scrNam: String) {
        stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
            if (scrNam == "backend") game.navigationManager.exit()
            else game.navigationManager.navigate(scrNam, BullMenuScreen::class.java.name) }
    }


}