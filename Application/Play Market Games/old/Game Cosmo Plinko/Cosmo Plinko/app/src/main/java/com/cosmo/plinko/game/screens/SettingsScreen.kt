package com.cosmo.plinko.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.cosmo.plinko.game.LibGDXGame
import com.cosmo.plinko.game.actors.AKomete
import com.cosmo.plinko.game.actors.AMusor
import com.cosmo.plinko.game.actors.button.AButton
import com.cosmo.plinko.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.cosmo.plinko.game.utils.TextureEmpty
import com.cosmo.plinko.game.utils.actor.animHide
import com.cosmo.plinko.game.utils.actor.animShow
import com.cosmo.plinko.game.utils.actor.setBounds
import com.cosmo.plinko.game.utils.actor.setOnClickListener
import com.cosmo.plinko.game.utils.advanced.AdvancedScreen
import com.cosmo.plinko.game.utils.advanced.AdvancedStage
import com.cosmo.plinko.game.utils.region
import com.cosmo.plinko.game.utils.runGDX
import com.cosmo.plinko.util.log
import com.cosmo.plinko.game.utils.Layout.Splash as LS

class SettingsScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val musicFlow = MutableStateFlow((game.musicUtil.music!!.volume * 100).toInt())
    private val soundFlow = MutableStateFlow((game.soundUtil.volumeLevel * 100).toInt())

    private val musicI = Image()
    private val soundI = Image()

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.splashAssets.BACGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addPalanet()
        addKomete()
        addMusor()
        addMenu()
        addBack()
        addMP_Music()
        addMP_Sound()

        coroutine?.launch {
            musicFlow.collect {
                runGDX {
                    log("music = $it")

                    when {
                        it <= 0 -> musicI.drawable = TextureRegionDrawable(TextureEmpty)
                        it > 81 -> musicI.drawable = TextureRegionDrawable(game.gameAssets.S_LIST.last())

                        it in 1..10  -> musicI.drawable = TextureRegionDrawable(game.gameAssets.S_LIST[0])
                        it in 11..20 -> musicI.drawable = TextureRegionDrawable(game.gameAssets.S_LIST[1])
                        it in 21..30 -> musicI.drawable = TextureRegionDrawable(game.gameAssets.S_LIST[2])
                        it in 31..40 -> musicI.drawable = TextureRegionDrawable(game.gameAssets.S_LIST[3])
                        it in 41..50 -> musicI.drawable = TextureRegionDrawable(game.gameAssets.S_LIST[4])
                        it in 51..60 -> musicI.drawable = TextureRegionDrawable(game.gameAssets.S_LIST[5])
                        it in 61..70 -> musicI.drawable = TextureRegionDrawable(game.gameAssets.S_LIST[6])
                        it in 71..80 -> musicI.drawable = TextureRegionDrawable(game.gameAssets.S_LIST[7])
                    }

                    game.musicUtil.music?.volume = if (it <= 0) 0f else it / 100f

                }
            }
        }
        coroutine?.launch {
            soundFlow.collect {
                runGDX {
                    log("sound = $it")

                    when {
                        it <= 0 -> soundI.drawable = TextureRegionDrawable(TextureEmpty)
                        it > 81 -> soundI.drawable = TextureRegionDrawable(game.gameAssets.S_LIST.last())

                        it in 1..10  -> soundI.drawable = TextureRegionDrawable(game.gameAssets.S_LIST[0])
                        it in 11..20 -> soundI.drawable = TextureRegionDrawable(game.gameAssets.S_LIST[1])
                        it in 21..30 -> soundI.drawable = TextureRegionDrawable(game.gameAssets.S_LIST[2])
                        it in 31..40 -> soundI.drawable = TextureRegionDrawable(game.gameAssets.S_LIST[3])
                        it in 41..50 -> soundI.drawable = TextureRegionDrawable(game.gameAssets.S_LIST[4])
                        it in 51..60 -> soundI.drawable = TextureRegionDrawable(game.gameAssets.S_LIST[5])
                        it in 61..70 -> soundI.drawable = TextureRegionDrawable(game.gameAssets.S_LIST[6])
                        it in 71..80 -> soundI.drawable = TextureRegionDrawable(game.gameAssets.S_LIST[7])
                    }

                    game.soundUtil.volumeLevel = if (it <= 0) 0f else it / 100f

                }
            }
        }

    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addPalanet() {
        val palanet = Image(game.splashAssets.PALANET)
        addActor(palanet)
        palanet.setBounds(LS.palanet)
    }

    private fun AdvancedStage.addKomete() {
        val palanet = AKomete(this@SettingsScreen)
        addActor(palanet)
        palanet.setBounds(0f, 0f, WIDTH, HEIGHT)
    }

    private fun AdvancedStage.addMusor() {
        val palanet = AMusor(this@SettingsScreen)
        addActor(palanet)
        palanet.setBounds(0f, 0f, WIDTH, HEIGHT)
    }

    private fun AdvancedStage.addMenu() {
        val menu = Image(game.gameAssets.SETTINGS)
        addActor(menu)
        menu.setBounds(114f, 549f, 851f, 885f)
    }

    private fun AdvancedStage.addBack() {
        val palanet = AButton(this@SettingsScreen, AButton.Static.Type.MENU)
        addActor(palanet)
        palanet.setBounds(57f, 1780f, 124f, 106f)
        palanet.setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() } }
    }

    private fun AdvancedStage.addMP_Music() {
        addActor(musicI)
        musicI.setBounds(351f, 1027f, 380f, 56f)

        val m = Actor()
        val p = Actor()
        addActors(m, p)
        m.apply {
            setBounds(266f, 1017f, 70f, 63f)
            setOnClickListener(game.soundUtil) { if (musicFlow.value.checkMinus()) musicFlow.value -= 10 }
        }
        p.apply {
            setBounds(745f, 1017f, 70f, 63f)
            setOnClickListener(game.soundUtil) { if (musicFlow.value.checkPlus()) musicFlow.value += 10 }
        }
    }

    private fun AdvancedStage.addMP_Sound() {
        addActor(soundI)
        soundI.setBounds(351f, 782f, 380f, 56f)

        val m = Actor()
        val p = Actor()
        addActors(m, p)
        m.apply {
            setBounds(266f, 772f, 70f, 63f)
            setOnClickListener(game.soundUtil) { if (soundFlow.value.checkMinus()) soundFlow.value -= 10 }
        }
        p.apply {
            setBounds(745f, 772f, 70f, 63f)
            setOnClickListener(game.soundUtil) { if (soundFlow.value.checkPlus()) soundFlow.value += 10 }
        }
    }

    private fun Int.checkMinus() = (this-10) >= -10
    private fun Int.checkPlus() = (this+10) <= 90


}