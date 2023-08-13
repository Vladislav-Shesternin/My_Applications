package com.veldan.lbjt.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.veldan.lbjt.game.LibGDXGame
import com.veldan.lbjt.game.box2d.bodiesGroup.BGBorders
import com.veldan.lbjt.game.box2d.bodiesGroup.BGMenu
import com.veldan.lbjt.game.box2d.bodiesGroup.BGYanYinTheme
import com.veldan.lbjt.game.box2d.disposeAll
import com.veldan.lbjt.game.utils.FIREBASE_STORAGE_MUSIC
import com.veldan.lbjt.game.utils.LOCAL_MUSIC_DIR
import com.veldan.lbjt.game.utils.TIME_ANIM_ALPHA
import com.veldan.lbjt.game.utils.actor.animHide
import com.veldan.lbjt.game.utils.actor.animShow
import com.veldan.lbjt.game.utils.actor.setOnTouchListener
import com.veldan.lbjt.game.utils.advanced.AdvancedMouseScreen
import com.veldan.lbjt.game.utils.advanced.AdvancedScreen
import com.veldan.lbjt.game.utils.advanced.AdvancedStage
import com.veldan.lbjt.game.utils.runGDX
import com.veldan.lbjt.util.Once
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MenuScreen(override val game: LibGDXGame): AdvancedMouseScreen() {

    companion object {
        private val oncePlayMusic = Once()
    }

    // Actor
    private val aHandHelloImg = Image(game.themeUtil.trc.HAND_HELLO)
    private val aHandHintImg  = Image(game.themeUtil.trc.HAND_HINT)

    // BodyGroup
    private val bgBorders     = BGBorders(this )
    private val bgMenu        = BGMenu(this )
    private val bgYanYinTheme = BGYanYinTheme(this)

    // Fields
    private val fileHandlerMusic       = Gdx.files.local(LOCAL_MUSIC_DIR)
    private val isLoadStorageMusicFlow = MutableSharedFlow<Boolean>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private var jobAnimHand: Job? = null

    override fun show() {
        game.apply { backgroundColor = themeUtil.backgroundColor }

        stageUI.root.animHide()
        setUIBackground(game.themeUtil.trc.BACKGROUND)
        super.show()
        stageUI.root.animShow(TIME_ANIM_ALPHA)

        game.activity.apply {
            setNavigationBarColor(game.themeUtil.navBarColor)
            bannerUtil.show()
        }

        //oncePlayMusic.once { playMusic() }
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createBG_Borders()
        createBG_Menu()
        createBG_YanYinTheme {
            addHandHelloImg()
            addHandHintImg()

            initialize {
                asyncCollectIsUserFirstTouchFlow()

                jobAnimHand = coroutine?.launch {
                    animHandHello()
                    animHandHint()
                }
            }
        }
    }


    override fun dispose() {
        listOf(bgBorders, bgMenu, bgYanYinTheme).disposeAll()
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
    private fun createBG_Borders(block: () -> Unit = {}) {
        bgBorders.requestToCreate(Vector2(0f, 0f), Vector2(700f, 1400f), block)
    }

    private fun createBG_Menu(block: () -> Unit = {}) {
        bgMenu.requestToCreate(Vector2(117f, 444f), Vector2(466f, 1012f), block)

        bgMenu.apply {
            bRegularBtnTutorial   .getActor()?.setOnTouchListener {/*navigateTo(SettingsScreen())*/ }
            bRegularBtnSettings   .getActor()?.setOnTouchListener { navigateTo(SettingsScreen(game))    }
            bRegularBtnAboutAuthor.getActor()?.setOnTouchListener { navigateTo(AboutAuthorScreen(game)) }
            bRegularBtnComment    .getActor()?.setOnTouchListener { /*navigateTo(SettingsScreen())*/ }

        }
    }

    private fun createBG_YanYinTheme(block: () -> Unit = {}) {
        bgYanYinTheme.requestToCreate(Vector2(285f, 231f), Vector2(131f, 131f), block)
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

    private fun asyncLoadStorageMusic() {
        if (fileHandlerMusic.exists()) isLoadStorageMusicFlow.tryEmit(true)
        else coroutine?.launch(Dispatchers.IO) {
            val musicRef = Firebase.storage.getReferenceFromUrl(FIREBASE_STORAGE_MUSIC)

            musicRef.listAll().addOnSuccessListener { results ->
                results.items.also { items ->
                    items.onEachIndexed { index, ref ->
                        ref.getBytes(Long.MAX_VALUE).addOnSuccessListener { bytes -> runGDX {
                            fileHandlerMusic.child("song$index.ogg").writeBytes(bytes, false)
                            if (index == items.lastIndex) isLoadStorageMusicFlow.tryEmit(true)
                        } }
                    }
                }
            }
        }
    }

    private fun asyncLoadLocalMusic() {
        coroutine?.launch(Dispatchers.Default) {
            isLoadStorageMusicFlow.collect { isLoad ->
                if (isLoad) runGDX { fileHandlerMusic.list().onEach { file ->
                    AssetDescriptor(file, Music::class.java).also { musicDescriptor ->
                        game.assetManagerLocal.apply {
                            load(musicDescriptor)
                            finishLoading()
                            game.musicUtil.firebaseStorageMusicList.add(get(musicDescriptor))
                        }
                    }
                } }
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
        asyncLoadLocalMusic()
        asyncLoadStorageMusic()

        var indexMusic = 0
        var musicList: List<Music> = game.musicUtil.firebaseStorageMusicList

        fun playRandomMusic() {
            game.musicUtil.apply {
                if(indexMusic >= firebaseStorageMusicList.size) {
                    indexMusic = 0
                    musicList = firebaseStorageMusicList.shuffled()
                }

                music = musicList[indexMusic].apply {
                    indexMusic++
                    setOnCompletionListener { playRandomMusic() }
                }
            }
        }

        playRandomMusic()
    }

    private fun navigateTo(screen: AdvancedScreen) {
        stageUI.root.animHide(TIME_ANIM_ALPHA) { game.navigationManager.navigate(screen, MenuScreen(game)) }
    }

}