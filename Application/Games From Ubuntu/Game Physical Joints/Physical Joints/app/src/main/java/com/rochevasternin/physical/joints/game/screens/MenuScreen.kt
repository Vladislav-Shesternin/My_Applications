package com.rochevasternin.physical.joints.game.screens

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.rochevasternin.physical.joints.game.LibGDXGame
import com.rochevasternin.physical.joints.game.box2d.bodiesGroup.BGBorders
import com.rochevasternin.physical.joints.game.box2d.bodiesGroup.BGMenu
import com.rochevasternin.physical.joints.game.box2d.bodiesGroup.BGYanYinTheme
import com.rochevasternin.physical.joints.game.box2d.destroyAll
import com.rochevasternin.physical.joints.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.rochevasternin.physical.joints.game.utils.actor.animHide
import com.rochevasternin.physical.joints.game.utils.actor.animShow
import com.rochevasternin.physical.joints.game.utils.actor.setOnTouchListener
import com.rochevasternin.physical.joints.game.utils.advanced.AdvancedMouseScreen
import com.rochevasternin.physical.joints.game.utils.advanced.AdvancedStage
import com.rochevasternin.physical.joints.game.utils.region
import com.rochevasternin.physical.joints.game.utils.runGDX
import com.rochevasternin.physical.joints.util.Once
import com.rochevasternin.physical.joints.util.log
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MenuScreen(override val game: LibGDXGame): AdvancedMouseScreen(game) {

    companion object {
        private val oncePlayMusic = Once()
    }

    // Actor
    private val aHandHelloImg = Image(game.themeUtil.assets.HAND_HELLO)
    private val aHandHintImg  = Image(game.themeUtil.assets.HAND_HINT)
    
    // BodyGroup
    private val bgBorders     = BGBorders(this )
    private val bgMenu        = BGMenu(this )
    private val bgYanYinTheme = BGYanYinTheme(this)

    // Fields
    private var jobAnimHand: Job? = null


    override fun show() {
        game.apply { backgroundColor = themeUtil.backgroundColor }

        stageUI.root.animHide()
        setUIBackground(game.themeUtil.assets.BACKGROUND.region)
        super.show()

        game.activity.apply { setNavigationBarColor(game.themeUtil.navBarColorId) }

        oncePlayMusic.once { playMusic() }
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createBG_Borders()
        createBG_Menu()
        createBG_YanYinTheme()

        addHandHelloImg()
        addHandHintImg()

        asyncCollectIsUserFirstTouchFlow()

        jobAnimHand = coroutine?.launch {
            animHandHello()
            animHandHint()
        }

        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun dispose() {
        listOf(bgBorders, bgMenu, bgYanYinTheme).destroyAll()
        super.dispose()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addHandHelloImg() {
        addActor(aHandHelloImg)
        aHandHelloImg.apply {
            setBounds(476f, -289f, 206f, 289f)
            setOrigin(50f, 1f)
            addAction(Actions.alpha(0f))
        }
    }

    private fun AdvancedStage.addHandHintImg() {
        addActor(aHandHintImg)
        aHandHintImg.apply {
            setBounds(-246f, 210f, 246f, 284f)
            setOrigin(10f, 85f)
            addAction(Actions.alpha(0f))
        }
    }

    // ------------------------------------------------------------------------
    // Create Body Group
    // ------------------------------------------------------------------------
    private fun createBG_Borders() {
        bgBorders.create(0f,0f,700f,1400f)
    }

    private fun createBG_Menu() {
        bgMenu.create(117f,444f,466f,1012f)

        bgMenu.apply {
            bRegularBtnTutorial   .getActor()?.setOnTouchListener { navigateTo(TutorialIntroductionScreen::class.java.name) }
            bRegularBtnSettings   .getActor()?.setOnTouchListener { navigateTo(SettingsScreen::class.java.name)    }
            bRegularBtnAboutAuthor.getActor()?.setOnTouchListener { navigateTo(AboutAuthorScreen::class.java.name) }
            bRegularBtnComment    .getActor()?.setOnTouchListener { game.navigationManager.exit() }

        }
    }

    private fun createBG_YanYinTheme() {
        bgYanYinTheme.create(285f,231f,131f,131f)
    }

    // ---------------------------------------------------
    // Anim
    // ---------------------------------------------------

    private suspend fun animHandHello() = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            aHandHelloImg.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.fadeIn(2f),
                    Actions.moveTo(476f, 146f, 2f, Interpolation.swingOut)
                ),
                Actions.sequence(
                    Actions.rotateBy(20f, 0.22f, Interpolation.sine),
                    Actions.rotateBy(-25f, 0.22f, Interpolation.sine),
                    Actions.rotateBy(20f, 0.2f, Interpolation.sine),
                    Actions.rotateBy(-20f, 0.2f, Interpolation.sine),
                    Actions.rotateBy(15f, 0.17f, Interpolation.sine),
                    Actions.rotateBy(-10f, 0.17f, Interpolation.sine),
                ),
                Actions.run { continuation.complete(Unit) },
                actionHideHandHello(1f)
            ))
        }
    }.await()

    private suspend fun animHandHint() = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            aHandHintImg.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.fadeIn(1.5f),
                    Actions.moveTo(-6f, 231f, 1.5f, Interpolation.swingOut)
                ),
                Actions.parallel(
                    Actions.run { imitationPullDown() },
                    Actions.moveTo(-4f, 190f, 0.8f, Interpolation.sineOut),
                    Actions.rotateBy(-35f, 0.8f, Interpolation.swingOut),
                ),
                Actions.parallel(
                    Actions.run { imitationPullLeft() },
                    actionHideHandHint(0.7f)
                ),
                Actions.run { continuation.complete(Unit) }
            ))
        }
    }.await()

    private fun actionHideHandHello(time: Float) = Actions.parallel(
        Actions.moveTo(476f, -289f, time, Interpolation.swingIn),
        Actions.fadeOut(time),
    )

    private fun actionHideHandHint(time: Float) = Actions.parallel(
        Actions.moveTo(-300f, 170f, time, Interpolation.swingIn),
        Actions.fadeOut(time)
    )

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun imitationPullDown() {
        val event = InputEvent()

        listenerForMouseJoint.also { listener ->
            listener.touchDown(event, 235f, 480f, 0, 1)

            coroutine?.launch {
                var ny = 480f
                while (ny >= 400f) {
                    delay(17)
                    ny -= (3..9).random()
                    runGDX { listener.touchDragged(event, 235f, ny, 0) }
                }

                listener.touchUp(event, 235f, ny, 0, 1)
            }
        }
    }

    private fun imitationPullLeft() {
        val event = InputEvent()

        listenerForMouseJoint.also { listener ->
            listener.touchDown(event, 295f, 287f, 0, 1)

            coroutine?.launch {
                delay(300)
                var nx = 295f
                while (nx >= 150f) {
                    delay(17)
                    nx -= (10..15).random()
                    runGDX { listener.touchDragged(event, nx, 287f, 0) }
                }

                listener.touchUp(event, nx, 287f, 0, 1)
            }
        }
    }

    private fun asyncCollectIsUserFirstTouchFlow() {
        coroutine?.launch(Dispatchers.Default) {
            isUserFirstTouchFlow.collect {
                jobAnimHand?.cancel()
                aHandHelloImg.apply {
                    clearActions()
                    addAction(actionHideHandHello(0.3f))
                }
                aHandHintImg.apply {
                    clearActions()
                    addAction(actionHideHandHint(0.3f))
                }
            }
        }
    }

    private fun playMusic() {
        var indexMusic = 0
        val musicList: MutableList<Music> = game.musicUtil.musicList.toMutableList()

        fun playRandomMusic() {
            game.musicUtil.apply {
                if(indexMusic >= musicList.size) {
                    indexMusic = 0
                    musicList.shuffle()
                }

                music = musicList[indexMusic].apply {
                    indexMusic++
                    setOnCompletionListener { playRandomMusic() }
                }
            }
        }

        playRandomMusic()
    }

    private fun navigateTo(screenName: String) {
        stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(screenName, MenuScreen::class.java.name) }
    }

}