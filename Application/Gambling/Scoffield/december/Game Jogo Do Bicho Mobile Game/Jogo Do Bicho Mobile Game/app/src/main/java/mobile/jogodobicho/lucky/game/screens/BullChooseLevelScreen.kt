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

class BullChooseLevelScreen(override val game: LibGDXGame) : AdvancedScreen() {

    companion object {
        var LEVEL_STATIC = LEVEL.EASY
            private set
    }

    private val babImg   = Image(game.gameAssets.MENU_BABOCHKE)
    private val musicCB  = ACheckBox(this, ACheckBox.Static.Type.MUSIC)
    private val levelImg = Image(game.gameAssets.BULL_LEVEL)

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.splashAssets.SUPER_PUPER_BACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addBab()
        addMusicCB()
        addLevelImg()
        addExitPlay()
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

    private fun AdvancedStage.addLevelImg() {
        addActor(levelImg)
        levelImg.setBounds(45f, 295f, 998f, 1231f)

        var ny = 1025f
        arrayOf(
            LEVEL.EASY, LEVEL.MEDIUM, LEVEL.HARD
        ).onEach { level ->
            Actor().also { actor ->
                addActor(actor)
                actor.apply {
                    setBounds(188f, ny, 656f, 197f)
                    ny -= 196f

                    setOnClickListener(game.soundUtil) {
                        LEVEL_STATIC = level
                        navToScrNam(BullGameScreen::class.java.name)
                    }
                }
            }
        }
    }

    private fun AdvancedStage.addExitPlay() {
        val exit = Actor()
        val play = Actor()

        addActors(exit, play)
        exit.apply {
            setBounds(45f, 368f, 449f, 133f)
            setOnClickListener(game.soundUtil) { navToScrNam("back") }
        }
        play.apply {
            setBounds(594f, 368f, 449f, 133f)
            setOnClickListener(game.soundUtil) { navToScrNam(BullGameScreen::class.java.name) }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun navToScrNam(scrNam: String) {
        stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
            if (scrNam == "back") game.navigationManager.back()
            else game.navigationManager.navigate(scrNam)
        }
    }

    enum class LEVEL {
        EASY, MEDIUM, HARD;

        fun getName() = when (LEVEL_STATIC) {
            EASY   -> "Easy"
            MEDIUM -> "Medium"
            HARD   -> "Hard"
        }
    }


}