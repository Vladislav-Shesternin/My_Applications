package com.veldan.fantasticslots.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Widget
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.Disposable
import com.veldan.fantasticslots.R
import com.veldan.fantasticslots.activityResources
import com.veldan.fantasticslots.actors.button.ButtonClickable
import com.veldan.fantasticslots.actors.label.LabelStyleUtil
import com.veldan.fantasticslots.actors.label.RollingLabel
import com.veldan.fantasticslots.advanced.AdvancedStage
import com.veldan.fantasticslots.assets.SpriteManager
import com.veldan.fantasticslots.screens.options.OptionsScreen
import com.veldan.fantasticslots.utils.*
import com.veldan.fantasticslots.utils.Tutorial
import com.veldan.fantasticslots.utils.layout.setBoundsFigmaY
import com.veldan.fantasticslots.utils.listeners.toClickable
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicInteger

class Tutorial(val controller: GameScreenController): Disposable {

    companion object {
        const val TIME_HIDE = 0.5f
        const val TIME_SHOW = 0.5f
    }

    private val coroutineAnimations = CoroutineScope(Dispatchers.Default)

    private val onceFinish = Once()

    private val screenBounds = Widget().apply { debug() }
    private val dialog       = Image()
    private val frame        = Image()
    private val label        = Label("", LabelStyleUtil.robotoMono30).apply {
        wrap = true
        setAlignment(Align.center, Align.center)
    }
    private val next         = ButtonClickable(ButtonClickable.Style.next)
    private val skipImage    = Image(SpriteManager.GameSprite.TUTORIAL_SKIP.data.texture).apply {
        setBoundsFigmaY(1282f, 0f, 118f, 121f)
        toClickable().setOnClickListener {
            OptionsScreen.isCheckedTraining = false
            finish()
        }
    }
    private val skipLabel    = RollingLabel(activityResources.getString(R.string.skip_tutorial), LabelStyleUtil.robotoMono30).apply {
        disable()
        setBoundsFigmaY(1282f, 41f, 118f, 40f)
    }

    private val actorList = listOf<Actor>(dialog, frame, label, next, skipImage, skipLabel)

    override fun dispose() {
        cancelCoroutinesAll(coroutineAnimations)
    }



    private fun AdvancedStage.addActorsOnStage() {
        addAndFillActor(screenBounds)
        addActors(actorList)
    }

    private fun handlerNext() {

        val mutex = Mutex()
        val counter = AtomicInteger(1)

        fun next() {
            log("c = ${counter.get()}")
            coroutineAnimations.launch {
                mutex.withLock {
                    log("work - ${counter.get()}")
                    if (counter.get() < TAG.values().size) {
                        hideActors()
                        handleTag(TAG.values()[counter.get()])
                        updateActors(TAG.values()[counter.get()].data)
                        showActors()

                        counter.incrementAndGet()
                    } else onceFinish.once { finish() }
                }
            }
        }

        screenBounds.toClickable().setOnClickListener { next() }
        next.setOnClickListener { next() }
    }

    private fun handleTag(tag: TAG) {
        Gdx.app.postRunnable {
            when (tag) {
                TAG.SCATTER -> {
                    controller.screen.slotGroup.showPanelTutorial()
                }
                TAG.GLOW    -> {
                    controller.screen.slotGroup.showGlowTutorial()
                }
                else        -> {}
            }
        }
    }

    private fun updateActors(data: TutorialData) {
        with(data.layout) {
            dialog.apply { 
                drawable = TextureRegionDrawable(data.dialogTexture)
                setBoundsFigmaY(DIALOG_X, DIALOG_Y, DIALOG_W, DIALOG_H)
            }
            frame.apply {
                drawable = TextureRegionDrawable(data.frameTexture)
                setBoundsFigmaY(FRAME_X, FRAME_Y, FRAME_W, FRAME_H)
            }
            label.apply { 
                setText(data.text)
                setBoundsFigmaY(TEXT_X, TEXT_Y, TEXT_W, TEXT_H)
            }
            next.apply {
                setBoundsFigmaY(NEXT_X, NEXT_Y, NEXT_W, NEXT_H)
            }
        }
    }

    private suspend fun hideActors() = CompletableDeferred<Boolean>().also { continuation ->
        Gdx.app.postRunnable {
            actorList.onEach { actor ->
                actor.addAction(Actions.sequence(
                    Actions.fadeOut(TIME_HIDE),
                    Actions.run { continuation.complete(true) }
                ))
            }
        }
    }.await()

    private suspend fun showActors() = CompletableDeferred<Boolean>().also { continuation ->
        Gdx.app.postRunnable {
        actorList.onEach { actor ->
                actor.addAction(Actions.sequence(
                    Actions.fadeIn(TIME_SHOW),
                    Actions.run { continuation.complete(true) }
                ))
            }
        }
    }.await()

    private fun finish() {
        log("finish")
        controller.screen.slotGroup.hideTutorial()

        coroutineAnimations.launch {
            hideActors()

            Gdx.app.postRunnable {
                with(controller.screen) {
                    with(stage.root) {
                        actorList.onEach { removeActor(it) }
                        removeActor(screenBounds)
                    }

                    listOf<Actor>(
                        betPlusButton, betMinusButton, autospinButton, menuButton, spinButton
                    ).onEach { it.enable() }

                    this@Tutorial.dispose()
                }
            }
        }
    }

    fun start() {
        Gdx.app.postRunnable {
            with(controller.screen) {
               listOf<Actor>(
                   betPlusButton, betMinusButton, autospinButton, menuButton, spinButton
               ).onEach { it.disable() }

                actorList.onEach { it.addAction(Actions.alpha(0f)) }
                stage.addActorsOnStage()
            }

            updateActors(TAG.values().first().data)
            coroutineAnimations.launch { showActors() }
            handlerNext()
        }
    }


    private enum class TAG(val data: TutorialData) {
        BALANCE(TutorialData(
            dialogTexture = SpriteManager.GameSprite.DIALOG_1.data.texture,
            frameTexture  = SpriteManager.GameSprite.FRAME_BALANCE.data.texture,
            text          = activityResources.getString(R.string.tutorial_balance),
            layout        = Tutorial.Balance
        )),
        BET(TutorialData(
            dialogTexture = SpriteManager.GameSprite.DIALOG_1.data.texture,
            frameTexture  = SpriteManager.GameSprite.FRAME_BET.data.texture,
            text          = activityResources.getString(R.string.tutorial_bet),
            layout        = Tutorial.Bet
        )),
        PLUS_MINUS(TutorialData(
            dialogTexture = SpriteManager.GameSprite.DIALOG_1.data.texture,
            frameTexture  = SpriteManager.GameSprite.FRAME_PLUS_MINUS.data.texture,
            text          = activityResources.getString(R.string.tutorial_plus_minus),
            layout        = Tutorial.PlusMinus
        )),
        SPIN(TutorialData(
            dialogTexture = SpriteManager.GameSprite.DIALOG_1.data.texture,
            frameTexture  = SpriteManager.GameSprite.FRAME_SPIN.data.texture,
            text          = activityResources.getString(R.string.tutorial_spin),
            layout        = Tutorial.Spin
        )),
        AUTOSPIN(TutorialData(
            dialogTexture = SpriteManager.GameSprite.DIALOG_1.data.texture,
            frameTexture  = SpriteManager.GameSprite.FRAME_AUTOSPIN.data.texture,
            text          = activityResources.getString(R.string.tutorial_autospin),
            layout        = Tutorial.Autospin
        )),
        MENU(TutorialData(
            dialogTexture = SpriteManager.GameSprite.DIALOG_1.data.texture,
            frameTexture  = SpriteManager.GameSprite.FRAME_MENU.data.texture,
            text          = activityResources.getString(R.string.tutorial_menu),
            layout        = Tutorial.Menu
        )),
        SLOT(TutorialData(
            dialogTexture = SpriteManager.GameSprite.DIALOG_2.data.texture,
            frameTexture  = SpriteManager.GameSprite.FRAME_SLOT.data.texture,
            text          = activityResources.getString(R.string.tutorial_slot),
            layout        = Tutorial.Slot
        )),
        SCATTER(TutorialData(
            dialogTexture = SpriteManager.GameSprite.DIALOG_2.data.texture,
            frameTexture  = SpriteManager.GameSprite.FRAME_SLOT_ITEM.data.texture,
            text          = activityResources.getString(R.string.tutorial_scatter),
            layout        = Tutorial.Scatter
        )),
        WILD(TutorialData(
            dialogTexture = SpriteManager.GameSprite.DIALOG_2.data.texture,
            frameTexture  = SpriteManager.GameSprite.FRAME_SLOT_ITEM.data.texture,
            text          = activityResources.getString(R.string.tutorial_wild),
            layout        = Tutorial.Wild
        )),
        WIN(TutorialData(
            dialogTexture = SpriteManager.GameSprite.DIALOG_2.data.texture,
            frameTexture  = SpriteManager.GameSprite.FRAME_WIN.data.texture,
            text          = activityResources.getString(R.string.tutorial_win),
            layout        = Tutorial.Win
        )),
        GLOW(TutorialData(
            dialogTexture = SpriteManager.GameSprite.DIALOG_2.data.texture,
            frameTexture  = SpriteManager.GameSprite.FRAME_SLOT.data.texture,
            text          = activityResources.getString(R.string.tutorial_glow),
            layout        = Tutorial.Glow
        )),
        COMPLETED(TutorialData(
            dialogTexture = SpriteManager.GameSprite.DIALOG_1.data.texture,
            frameTexture  = SpriteManager.GameSprite.FRAME_BALANCE.data.texture,
            text          = activityResources.getString(R.string.tutorial_completed),
            layout        = Tutorial.Completed
        )),
    }

    private data class TutorialData(
        val dialogTexture: Texture,
        val frameTexture : Texture,
        val text         : String,
        val layout       : Tutorial.Template,
    )

}