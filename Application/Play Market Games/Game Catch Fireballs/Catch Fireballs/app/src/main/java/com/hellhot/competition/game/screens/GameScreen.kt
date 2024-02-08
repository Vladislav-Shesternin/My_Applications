package com.hellhot.competition.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.hellhot.competition.adAppOpen
import com.hellhot.competition.game.actors.label.ALabelStyle
import com.hellhot.competition.game.box2d.bodies.BodyId
import com.hellhot.competition.game.box2d.bodies.bonus.Bonus
import com.hellhot.competition.game.box2d.bodies.duplo.Duplo
import com.hellhot.competition.game.box2d.bodies.leftButton.LeftButton
import com.hellhot.competition.game.box2d.bodies.rightButton.RightButton
import com.hellhot.competition.game.box2d.bodies.scull.Scull
import com.hellhot.competition.game.box2d.bodies.tare.Tare
import com.hellhot.competition.game.game
import com.hellhot.competition.game.manager.SpriteManager
import com.hellhot.competition.game.util.Size
import com.hellhot.competition.game.util.advanced.AdvancedBox2dScreen
import com.hellhot.competition.game.util.advanced.AdvancedStage
import com.hellhot.competition.game.util.disable
import com.hellhot.competition.game.util.enable
import com.hellhot.competition.log
import com.hellhot.competition.toMS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import com.hellhot.competition.game.util.Layout.Game as LG

class GameScreen: AdvancedBox2dScreen(1800f, 808f, 50f, 22.44f) {

    private val rightButton = RightButton()
    private val leftButton  = LeftButton()
    private val tare        = Tare()
    private val scullRight  = Scull().apply { angleValue = -0.03f }
    private val scullLeft   = Scull().apply { angleValue = 0.03f }

    private val bonus1Image = Image(SpriteManager.GameRegion.BONUS_1.region)
    private val bonus2Image = Image(SpriteManager.GameRegion.BONUS_2.region)
    private val bonus1Label = Label("", ALabelStyle.fontRed_25)
    private val bonus2Label = Label("", ALabelStyle.fontRed_25)

    private val bonus1Flow = MutableStateFlow(0)
    private val bonus2Flow = MutableStateFlow(0)
    private val bonusOutFlow = MutableSharedFlow<Bonus>(replay = 20)

    private val bonusList = (MutableList(10) { Bonus().apply {
        id = BodyId.BONUS_1
        originId = BodyId.BONUS_1
        actor.drawable = TextureRegionDrawable(SpriteManager.GameRegion.BONUS_1.region)
    } } + MutableList(10) { Bonus().apply {
        id = BodyId.BONUS_2
        originId = BodyId.BONUS_2
        actor.drawable = TextureRegionDrawable(SpriteManager.GameRegion.BONUS_2.region)
    } }).shuffled()


    override fun show() {
        adAppOpen?.showAd(game.activity)

        super.show()
        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)

        var isShowAppOpenAd = false
        adAppOpen?.let { ad ->
            coroutine.launch(Dispatchers.Default) {
                launch {
                    while (isShowAppOpenAd.not()) {
                        delay(3_000L)

                        log("show after 3s")

                        if (ad.isLoaded) {
                            isShowAppOpenAd = true
                            ad.showAd(game.activity)
                        }
                    }
                }
                launch {
                    while (isActive) {
                        delay((50..60).random().toFloat().toMS)
                        game.activity.apply { adInterstitial.showAd(this) }
                        delay(30f.toMS)
                    }
                }
            }
        }
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        coroutine.launch {
            createButtons()
            createSculls()
            createTare()
            createDuploList()
            launch { createBonusList() }

            addBonuses()
        }
    }

    // ------------------------------------------------------------------------
    // Create Bodies
    // ------------------------------------------------------------------------
    private fun createButtons() {
        rightButton.initialize(
            stageUI,
            sizeConverterUIToBox,
            sizeConverterBoxToUI,
            Vector2(LG.right.x, LG.right.y),
            Size(LG.right.w, LG.right.h)
        )

        leftButton.initialize(
            stageUI,
            sizeConverterUIToBox,
            sizeConverterBoxToUI,
            Vector2(LG.left.x, LG.left.y),
            Size(LG.left.w, LG.left.h)
        )

        val rightArea = Actor()
        val leftArea  = Actor()

        stageUI.addActors(rightArea, leftArea)

        with(LG.rightArea) { rightArea.setBounds(x, y, w, h) }
        with(LG.leftArea) { leftArea.setBounds(x, y, w, h) }

        rightButton.actor.addArea(rightArea)
        leftButton.actor.addArea(leftArea)

        var longJob: Job? = null
        var newTareX = sizeConverterUIToBox.getSizeX(LG.tare.x)

        fun touchDownBlock(x: Float) {
            longJob = coroutine.launch {
                while (isActive) {
                    newTareX += x
                    Gdx.app.postRunnable {
                        newTareX = when {
                            newTareX > 44.4166f -> 44.4166f
                            newTareX < 0f -> 0f
                            else -> newTareX
                        }
                        tare.body.setTransform(newTareX, tare.body.position.y, 0f)
                    }
                    delay(10)
                }
            }
        }

        leftButton.actor.apply {
            touchDownBlock = { _, _ ->
                rightButton.actor.disable(false)
                rightArea.disable()
                touchDownBlock(-0.25f)
            }
            touchUpBlock = { _, _ ->
                rightButton.actor.enable()
                rightArea.enable()
                longJob?.cancel()
            }
        }

        rightButton.actor.apply {
            touchDownBlock = { _, _ ->
                leftButton.actor.disable(false)
                leftArea.disable()
                touchDownBlock(0.25f)
            }
            touchUpBlock = { _, _ ->
                leftButton.actor.enable()
                leftArea.enable()
                longJob?.cancel()
            }
        }
    }

    private fun createSculls() {
        scullLeft.initialize(
            stageUI,
            sizeConverterUIToBox,
            sizeConverterBoxToUI,
            Vector2(LG.scullLeft.x, LG.scullLeft.y),
            Size(LG.scullLeft.w, LG.scullLeft.h)
        )
        scullRight.initialize(
            stageUI,
            sizeConverterUIToBox,
            sizeConverterBoxToUI,
            Vector2(LG.scullRight.x, LG.scullRight.y),
            Size(LG.scullRight.w, LG.scullRight.h)
        )
    }

    private fun createTare() {
        tare.initialize(
            stageUI,
            sizeConverterUIToBox,
            sizeConverterBoxToUI,
            Vector2(LG.tare.x, LG.tare.y),
            Size(LG.tare.w, LG.tare.h)
        )
        tare.setBeginContactBlock {
            when (it) {
                is Bonus -> {
                    it.also { bonus ->

                        soundTake?.play(0.67f)

                        when (it.id) {
                            BodyId.BONUS_1 -> bonus1Flow.value += 1
                            BodyId.BONUS_2 -> bonus2Flow.value += 1
                            else           -> {}
                        }

                        bonus.bodyOut()
                        bonusOutFlow.tryEmit(it)
                    }
                }
            }
        }
    }

    private fun createDuploList() {
        val counts = listOf(7, 6, 5, 4, 4)

        LG.duploList.onEachIndexed { index, layoutData ->
            var newX = layoutData.x
            List(counts[index]) { Duplo() }.onEach { duplo ->
                duplo.fixtureDef.restitution = (10..100).shuffled().first() / 100f
                duplo.initialize(
                    stageUI,
                    sizeConverterUIToBox,
                    sizeConverterBoxToUI,
                    Vector2(newX, layoutData.y),
                    Size(layoutData.w, layoutData.h),
                )
                newX += layoutData.w + layoutData.hs

                coroutine.launch {
                    delay((100..3000L).shuffled().first())
                    Gdx.app.postRunnable { duplo.startAnim = true }
                }
            }
        }
    }

    private suspend fun createBonusList() {
        fun getPosition() = Vector2().apply {
            x = (LG.bonusStart.toInt()..LG.bonusEnd.toInt()).shuffled().first().toFloat()
            y = LG.bonusY
        }

        bonusList.onEach { bonus ->
            delay(1f.toMS)

            bonus.initialize(
                stageUI,
                sizeConverterUIToBox,
                sizeConverterBoxToUI,
                getPosition(),
                LG.bonusSize
            )

            Gdx.app.postRunnable {
                bonus.setBeginContactBlock { if(it.id == BodyId.DUPLO) soundUdar?.play(1f) }
                bonus.setRenderBlock {
                    if (bonus.actor.x + bonus.actor.width < 0f || bonus.actor.y + bonus.actor.height < 0f) {
                        bonus.bodyOut()
                        bonusOutFlow.tryEmit(bonus)
                    }
                }
            }
        }

        coroutine.launch {
            bonusOutFlow.collect { bonus ->
                delay((500..1000L).shuffled().first())

                Gdx.app.postRunnable {
                    getPosition().also {
                        bonus.body.setTransform(
                            sizeConverterUIToBox.getSizeX(it.x),
                            sizeConverterUIToBox.getSizeY(it.y),
                            0f
                        )
                    }
                    bonus.id = bonus.originId
                    bonus.body.gravityScale = 1f
                    bonus.body.isAwake = true
                }
            }
        }

    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addBonuses() {
        addActors(bonus1Image, bonus2Image, bonus1Label, bonus2Label)
        bonus1Image.apply {
            with(LG.bonus1) { setBounds(x, y, w, h) }
        }
        bonus2Image.apply {
            with(LG.bonus2) { setBounds(x, y, w, h) }
        }
        bonus1Label.apply {
            with(LG.bonus1Label) { setBounds(x, y, w, h) }
            this@GameScreen.coroutine.launch {
                bonus1Flow.collect { count -> Gdx.app.postRunnable { setText(count) } }
            }
        }
        bonus2Label.apply {
            with(LG.bonus2Label) { setBounds(x, y, w, h) }
            this@GameScreen.coroutine.launch {
                bonus2Flow.collect { count -> Gdx.app.postRunnable { setText(count) } }
            }
        }

    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun Bonus.bodyOut() {
        Gdx.app.postRunnable {
            id = BodyId.NONE
            body.apply {
                linearVelocity = Vector2(0f, 0f)
                setTransform(25f, 25f, 0f)
                isAwake = false
                gravityScale = 0f
            }
        }
    }

}