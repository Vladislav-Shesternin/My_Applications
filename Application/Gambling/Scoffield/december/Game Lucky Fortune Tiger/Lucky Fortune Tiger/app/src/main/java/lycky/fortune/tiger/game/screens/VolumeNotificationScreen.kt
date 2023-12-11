package lycky.fortune.tiger.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import lycky.fortune.tiger.game.LibGDXGame
import lycky.fortune.tiger.game.actors.button.AButton
import lycky.fortune.tiger.game.actors.checkbox.ACheckBox
import lycky.fortune.tiger.game.actors.progress.AVolumeBar
import lycky.fortune.tiger.game.utils.actor.animShow
import lycky.fortune.tiger.game.utils.actor.animShow_Suspend
import lycky.fortune.tiger.game.utils.actor.setOnClickListener
import lycky.fortune.tiger.game.utils.advanced.AdvancedScreen
import lycky.fortune.tiger.game.utils.advanced.AdvancedStage
import lycky.fortune.tiger.game.utils.region
import lycky.fortune.tiger.game.utils.runGDX
import plinko.games.mega.game.actors.checkbox.ACheckBoxGroup

class VolumeNotificationScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private companion object {
        var isNotification = false
        var valueVolume: Float? = null
    }

    private val set = Image(game.gameAssets.SET).apply { color.a = 0f }
    private val menu     = AButton(this, AButton.Static.Type.MENU)
    private val notificationON     = ACheckBox(this, ACheckBox.Static.Type.CB)
    private val notificationOFF    = ACheckBox(this, ACheckBox.Static.Type.CB)
    private val musicON     = ACheckBox(this, ACheckBox.Static.Type.CB)
    private val musicOFF    = ACheckBox(this, ACheckBox.Static.Type.CB)
    private val regulator   = AVolumeBar(this)

    override fun show() {
        setBackgrounds(game.splashAssets.FIRST_BACKGROUND.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        coroutine?.launch {
            runGDX {
                addAndFillActor(Image(game.splashAssets.VARIOUS_LUXURY_ITEMS).apply {
                    color.a = 0f
                    animShow(0.3f)
                })
                addActor(menu)
                menu.setBounds(16f, 1693f, 227f, 227f)
                menu.setOnClickListener { game.navigationManager.back() }

                addActor(set)
                set.setBounds(69f, 135f, 942f, 1431f)
                addBtn()

                addNotificationCB()
                addMusicCB()
                addBar()
            }
            delay(400)
            set.animShow_Suspend(0.3f)
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addBtn() {
        Actor().also { actor ->
            addActor(actor)
            actor.setBounds(372f, 135f, 344f, 344f)
            actor.setOnClickListener(game.soundUtil) { navigateGo() }
        }
    }

    private fun AdvancedStage.addNotificationCB() {
        val cbg = ACheckBoxGroup()
        addActors(notificationON, notificationOFF)
        notificationON.apply {
            setBounds(309f, 1065f, 106f, 110f)
            checkBoxGroup = cbg
            setOnCheckListener { if (it) isNotification = true }
        }
        notificationOFF.apply {
            setBounds(672f, 1065f, 106f, 110f)
            checkBoxGroup = cbg
            setOnCheckListener { if (it) isNotification = false }
        }

        (if (isNotification) notificationON else notificationOFF).check(false)
    }

    private fun AdvancedStage.addMusicCB() {
        val cbg = ACheckBoxGroup()
        addActors(musicON, musicOFF)
        musicON.apply {
            setBounds(309f, 793f, 106f, 110f)
            checkBoxGroup = cbg
            setOnCheckListener { if (it) game.musicUtil.music?.play() }
        }
        musicOFF.apply {
            setBounds(672f, 793f, 106f, 110f)
            checkBoxGroup = cbg
            setOnCheckListener { if (it) game.musicUtil.music?.pause() }
        }

        game.musicUtil.music?.apply { (if (isPlaying) musicON else musicOFF).check(false) }
    }

    private fun AdvancedStage.addBar() {
        addActor(regulator)
        regulator.setBounds(266f, 563f, 563f, 71f)

        regulator.apply {
            setProgressPercent(valueVolume ?: (game.musicUtil.volumeLevelFlow.value * 100f))

            coroutine?.launch {
                progressPercentFlow.collect {
                    valueVolume = it
                    game.musicUtil.volumeLevelFlow.value = it / 100f
                }
            }
        }
    }


    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun navigateGo() {
        // to GAME
        game.navigationManager.navigate(ManyToysScreen::class.java.name)
    }


}