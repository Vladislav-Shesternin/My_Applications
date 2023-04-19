package com.veldan.spinia.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport
import com.veldan.spinia.HEIGHT
import com.veldan.spinia.WIDTH
import com.veldan.spinia.actors.*
import com.veldan.spinia.advanced.AdvancedScreen
import com.veldan.spinia.assets.FontManager
import com.veldan.spinia.assets.SpriteManager
import com.veldan.spinia.utils.*
import kotlinx.coroutines.*
import kotlin.random.Random

class GameScreen : AdvancedScreen() {
    override val viewport = FitViewport(WIDTH, HEIGHT)

    private val coroutineRainJewels = CoroutineScope(Dispatchers.Default)
    private val coroutineBestResult = CoroutineScope(Dispatchers.IO)

    private val jewelryList = listOf<Jewel>(
        Jewel(0, 1000, SpriteManager.itemList[0]),
        Jewel(1, 10_000, SpriteManager.itemList[1]),
        Jewel(2, 5000, SpriteManager.itemList[2]),
        Jewel(3, 500, SpriteManager.itemList[3]),
        Jewel(4, 7000, SpriteManager.itemList[4]),
        Jewel(5, 15_000, SpriteManager.itemList[5]),
        Jewel(6, 20_000, SpriteManager.itemList[6]),
        Jewel(7, 30_000, SpriteManager.itemList[7]),
    )

    private val jewelryActorList = List(10) { JewelActor() }

    private val hero = Hero()
    private val monkey = Monkey()
    private val bird = Bird()
    private val heart = Heart(bird)
    private val banana = Banana()
    private val gameover = Image(SpriteManager.game_over)
    private val hpText = Label("3", Label.LabelStyle(FontManager.font_50, Color.WHITE))
    private val jewelryText = Label("0", Label.LabelStyle(FontManager.font_26, Color.WHITE))
    private val newResultText = Label("0", Label.LabelStyle(FontManager.font_17, Color.WHITE))
    private val bestResultText = Label("0", Label.LabelStyle(FontManager.font_17, Color.WHITE))

    private var isGameOver = false



    override fun show() {
        super.show()
        background = SpriteManager.backgroundList[1]
        addActors()
        coroutineRainJewels.launch { startRainingJewels() }
        Gdx.input.setCatchKey(Input.Keys.BACK, true)
    }

    override fun render(delta: Float) {
        super.render(delta)
        bird.renderFly(delta)
        updateHero()
        updateGameOver()
    }

    override fun dispose() {
        super.dispose()
        cancelCoroutinesAll(coroutineRainJewels, coroutineBestResult)
    }

    override fun keyDown(keycode: Int): Boolean {
        when (keycode) {
            Input.Keys.BACK -> NavigationUtil.back()
        }
        return super.keyDown(keycode)
    }


    private fun addActors(){
        stage.addActors(getActors().plus(jewelryActorList))
    }



    private fun getActors() = listOf<Actor>(
        setUpHero(),
        setUpHeart(),
        setUpBird(),
        setUpBanana(),
        setUpMonkey(),
        setUpHP(),
        setUpHPText(),
        setUpJewelry(),
        setUpJewelryText(),
        setUpGameOver(),
        setUpNewResultText(),
        setUpBestResultText(),
    )



    private fun setUpHero() = hero.apply {
        setBoundsFigmaY(HERO_X, HERO_Y, HERO_W, HERO_H)
    }

    private fun setUpBird() = bird.apply {
        setBoundsFigmaY(BIRD_MIN_X, BIRD_Y, BIRD_W, BIRD_H)
        fly()
    }

    private fun setUpHeart() = heart.apply {
        setBoundsFigmaY(BIRD_MIN_X, BIRD_Y, HEART_W, HEART_H)
        fly()
    }

    private fun setUpMonkey() = monkey.apply {
        setBoundsFigmaY(MONKEY_X, MONKEY_Y, MONKEY_W, MONKEY_H)
        swing()
    }

    private fun setUpBanana() = banana.apply {
        setBoundsFigmaY(BANANA_X, BANANA_Y, BANANA_W, BANANA_H)
        fly()
    }

    private fun setUpHP() = Image(SpriteManager.hp).apply {
        setBoundsFigmaY(HP_X, HP_Y, HP_W, HP_H)
    }

    private fun setUpHPText() = hpText.apply {
        setAlignment(Align.center)
        setBoundsFigmaY(HP_TEXT_X, HP_TEXT_Y, HP_TEXT_W, HP_TEXT_H)
    }

    private fun setUpJewelry() = Image(SpriteManager.jewelry).apply {
        setBoundsFigmaY(JEWELRY_X, JEWELRY_Y, JEWELRY_W, JEWELRY_H)
    }
    
    private fun setUpJewelryText() = jewelryText.apply {
        setAlignment(Align.center)
        setBoundsFigmaY(JEWELRY_TEXT_X, JEWELRY_TEXT_Y, JEWELRY_TEXT_W, JEWELRY_TEXT_H)
    }

    private fun setUpGameOver() = gameover.apply {
        isVisible = false
        setBoundsFigmaY(GAME_OVER_X, GAME_OVER_Y, GAME_OVER_W, GAME_OVER_H)
    }

    private fun setUpNewResultText() = newResultText.apply {
        isVisible = false
        setAlignment(Align.center)
        setBoundsFigmaY(NEW_RESULT_X, NEW_RESULT_Y, NEW_RESULT_W, NEW_RESULT_H)
    }

    private fun setUpBestResultText() = bestResultText.apply {
        isVisible = false
        setAlignment(Align.center)
        setBoundsFigmaY(BEST_RESULT_X, BEST_RESULT_Y, BEST_RESULT_W, BEST_RESULT_H)
    }
    


    private fun updateHero() {
        Gdx.input.accelerometerY.also { y ->
            if (y < -1) if (hero.x - 7 < HERO_MIN_X) hero.x = HERO_MIN_X else hero.x -= 7
            if (y > +1) if (hero.x + 7 > HERO_MAX_X) hero.x = HERO_MAX_X else hero.x += 7
        }

        jewelryActorList.onEach {
            if (
                it.x in hero.basketX..(hero.basketX + BASKET_W) ||
                (it.x + it.width) in hero.basketX..(hero.basketX + BASKET_W) &&
                it.isFree.not()
            ) if (
                it.y > BASKET_Y &&
                it.y < (BASKET_Y + BASKET_H)
            ) {
                jewelryText.apply { setText(text.int + (it.jewel?.price ?: 0)) }
                it.finishGo()
            }
        }


        with(hero) {
            // Banana
            if (
                banana.x in (frameX..frameX + HERO_FRAME_W) &&
                banana.y in (0f..HERO_FRAME_H)
            ) {
                hpText.apply {
                    setText(text.int.dec())
                    if (text.int == 0) showGameOver()
                }
                with(banana){
                    reset()
                    fly()
                }
            }
            // Heart
            if (
                heart.x in (frameX..frameX + HERO_FRAME_W) &&
                heart.y in (0f..HERO_FRAME_H)
            ) {
                hpText.setText(hpText.text.int.inc())
                with(heart){
                    reset()
                    fly()
                }
            }
        }


    }



    private suspend fun startRainingJewels() {
        var time = 0L
        while (true) {
            delay(time)
            jewelryActorList.random().apply {
                if (isFree) {
                    time = Random.nextLong(500, 1000)
                    jewel = jewelryList.random()
                    go()
                } else time = 0L
            }
        }
    }



    private fun showGameOver() {
        isGameOver = true
        coroutineRainJewels.cancel()
        banana.finishFly()
        heart.finishFly()
        gameover.isVisible = true
        setResult()
    }

    private fun updateGameOver(){
        if (isGameOver.not()) return
        if (Gdx.input.justTouched()) NavigationUtil.navigate(GameScreen())
    }



    private fun setResult() {
        newResultText.apply {
            setText(jewelryText.text)
            isVisible = true
        }
        bestResultText.apply {
            coroutineBestResult.launch {
                with(DataStoreUtil) {
                    getBestResult {
                        if (it < newResultText.text.int) {
                            bestResultText.setText(newResultText.text)
                            updateBestResult { newResultText.text.int }
                        } else bestResultText.setText(it)
                    }
                    isVisible = true
                }
            }
        }
    }

}