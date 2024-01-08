package rainbowriches.lucky.start.game.screens

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import rainbowriches.lucky.start.game.LibGDXGame
import rainbowriches.lucky.start.game.actors.button.AButton
import rainbowriches.lucky.start.game.actors.checkbox.ACheckBox
import rainbowriches.lucky.start.game.utils.Layout
import rainbowriches.lucky.start.game.utils.TIME_ANIM_SCREEN_ALPHA
import rainbowriches.lucky.start.game.utils.actor.animShow_Suspend
import rainbowriches.lucky.start.game.utils.actor.setBounds
import rainbowriches.lucky.start.game.utils.actor.setOnClickListener
import rainbowriches.lucky.start.game.utils.advanced.AdvancedScreen
import rainbowriches.lucky.start.game.utils.advanced.AdvancedStage
import rainbowriches.lucky.start.game.utils.region
import rainbowriches.lucky.start.game.utils.runGDX

var isMusic = true

class ButtonsScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val musicCB = ACheckBox(this, ACheckBox.Static.Type.MUSIC).apply { color.a = 0f }
    private val playBtn = AButton(this, AButton.Static.Type.PLAY).apply { color.a = 0f }
    private val btns    = listOf(
        AButton(this, AButton.Static.Type.PAPER).apply { color.a = 0f },
        AButton(this, AButton.Static.Type.GEAR).apply { color.a = 0f },
        AButton(this, AButton.Static.Type.X).apply { color.a = 0f },
    )


    override fun show() {
        setBackgrounds(game.gameAssets.PLAYGROUND.region)
        super.show()


        val img = Image(game.gameAssets.test)
        stageUI.addActor(img)
        img.apply {
            setBounds(72f, 1639f, 553f, 226f)
            setOnClickListener {
                game.musicUtil.music?.pause()
                game.activity.openWeb("https://fex.net/")
            }
        }
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        coroutine?.launch {
            runGDX {
                addMusicCB()
                addPlayBtn()
                addBtns()
            }
            delay(500)
            musicCB.animShow_Suspend(TIME_ANIM_SCREEN_ALPHA)
            playBtn.animShow_Suspend(TIME_ANIM_SCREEN_ALPHA)
            btns.onEach {
                it.animShow_Suspend(0.2f)
            }
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addMusicCB() {
        addActor(musicCB)
        musicCB.apply {
            setBounds(828f, 1684f, 182f, 193f)
            if (isMusic) uncheck(false) else check(false)

            setOnCheckListener {
                if (it) {
                    isMusic = false
                    game.musicUtil.music?.pause()
                } else {
                    isMusic = true
                    game.musicUtil.music?.play()
                }
            }

        }
    }

    private fun AdvancedStage.addPlayBtn() {
        addActor(playBtn)
        playBtn.apply {
            setBounds(197f, 916f, 640f, 640f)
            setOnClickListener { navigateGo(LevNorHardScreen::class.java.name) }
        }
    }

    private fun AdvancedStage.addBtns() {
        val selection = listOf(
            SpecificationScreen::class.java.name,
            MusicSoundScreen::class.java.name,
            "EXIT",
        )

        btns.onEachIndexed { index, aButton ->
            addActor(aButton)
            aButton.setBounds(Layout.Buttons.btns[index], Vector2(234f, 234f))
            aButton.setOnClickListener { navigateGo(selection[index]) }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun navigateGo(id: String) {
        animHideScreen { if (id == "EXIT") game.navigationManager.exit() else game.navigationManager.navigate(id, this::class.java.name) }
    }


}