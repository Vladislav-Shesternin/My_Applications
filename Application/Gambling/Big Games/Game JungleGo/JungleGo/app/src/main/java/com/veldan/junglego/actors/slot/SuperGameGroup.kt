package com.veldan.junglego.actors.slot

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.junglego.actors.Roulette
import com.veldan.junglego.advanced.AdvancedGroup
import com.veldan.junglego.assets.SpriteManager
import com.veldan.junglego.assets.util.SoundUtil
import com.veldan.junglego.listeners.toClickable
import com.veldan.junglego.utils.cancelCoroutinesAll
import com.veldan.junglego.utils.disable
import com.veldan.junglego.utils.setBoundsFigmaY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.veldan.junglego.utils.SuperGameGroup as SGG

class SuperGameGroup: AdvancedGroup() {

    companion object{
        private const val SEGMENT = 36
        private const val FIRST_SEGMENT_END = (SEGMENT / 2f)
        private const val LAST_SEGMENT_END = (FIRST_SEGMENT_END + (SEGMENT * 9f))
    }

    private val rouletteItemList = listOf<Roulette.RouletteItem>(
        // First SEGMENT
        Roulette.RouletteItem(price = 10_000, segment = 1f to FIRST_SEGMENT_END),
        Roulette.RouletteItem(price = 10_000, segment = LAST_SEGMENT_END to 360f),

        Roulette.RouletteItem(price = 25_000, segment = (FIRST_SEGMENT_END + (SEGMENT * 0f)) + 1f to (FIRST_SEGMENT_END + (SEGMENT * 1f))),
        Roulette.RouletteItem(price = 33_000, segment = (FIRST_SEGMENT_END + (SEGMENT * 1f)) + 1f to (FIRST_SEGMENT_END + (SEGMENT * 2f))),
        Roulette.RouletteItem(price = 50_000, segment = (FIRST_SEGMENT_END + (SEGMENT * 2f)) + 1f to (FIRST_SEGMENT_END + (SEGMENT * 3f))),
        Roulette.RouletteItem(price = 70_000, segment = (FIRST_SEGMENT_END + (SEGMENT * 3f)) + 1f to (FIRST_SEGMENT_END + (SEGMENT * 4f))),
        Roulette.RouletteItem(price = 80_000, segment = (FIRST_SEGMENT_END + (SEGMENT * 4f)) + 1f to (FIRST_SEGMENT_END + (SEGMENT * 5f))),
        Roulette.RouletteItem(price = 75_000, segment = (FIRST_SEGMENT_END + (SEGMENT * 5f)) + 1f to (FIRST_SEGMENT_END + (SEGMENT * 6f))),
        Roulette.RouletteItem(price = 90_000, segment = (FIRST_SEGMENT_END + (SEGMENT * 6f)) + 1f to (FIRST_SEGMENT_END + (SEGMENT * 7f))),
        Roulette.RouletteItem(price = 95_000, segment = (FIRST_SEGMENT_END + (SEGMENT * 7f)) + 1f to (FIRST_SEGMENT_END + (SEGMENT * 8f))),
        Roulette.RouletteItem(price = 100_000, segment = (FIRST_SEGMENT_END + (SEGMENT * 8f)) + 1f to (FIRST_SEGMENT_END + (SEGMENT * 9f))),
    )


    private val coroutineSpin = CoroutineScope(Dispatchers.Default)

    private val roulette = Roulette(rouletteItemList)

    var resultBlock: (price: Long) -> Unit = {}



    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }

    override fun dispose() {
        super.dispose()
        cancelCoroutinesAll(coroutineSpin)
    }



    private fun addActorsOnGroup() {
        addBackground()
        addRoulette()
        addIndicator()
        addGo()
    }



    private fun addBackground() {
        val image = Image(SpriteManager.GameSprite.BACKGROUND.textureData.texture)
        addAndFillActor(image)
    }

    private fun addRoulette() {
        roulette.apply {
            setBoundsFigmaY(SGG.ROULETTE_X, SGG.ROULETTE_Y, SGG.ROULETTE_W, SGG.ROULETTE_H)
        }
        addActor(roulette)
    }

    private fun addIndicator() {
        val image = Image(SpriteManager.GameSprite.ROULETTE_INDICATOR.textureData.texture).apply {
            setBoundsFigmaY(SGG.INDICATOR_X, SGG.INDICATOR_Y, SGG.INDICATOR_W, SGG.INDICATOR_H)
        }
        addActor(image)
    }

    private fun addGo() {
        val image = Image(SpriteManager.GameSprite.ROULETTE_GO.textureData.texture).apply {
            setBoundsFigmaY(SGG.GO_X, SGG.GO_Y, SGG.GO_W, SGG.GO_H)
            toClickable().setOnClickListener { goHandler() }
        }
        addActor(image)
    }



    private fun Image.goHandler() {
        isVisible = false
        disable()
        coroutineSpin.launch {
            roulette.spin().also { resultBlock(it.price) }
            SoundUtil.apply { if (isPause.not()) WIN.play(volumeLevel.value) }
        }
    }

}