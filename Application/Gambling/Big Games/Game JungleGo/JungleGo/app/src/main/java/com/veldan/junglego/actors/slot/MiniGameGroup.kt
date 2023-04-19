package com.veldan.junglego.actors.slot

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.veldan.junglego.actors.ButtonClickable
import com.veldan.junglego.actors.ButtonClickable.Style.Companion.style_6
import com.veldan.junglego.advanced.AdvancedGroup
import com.veldan.junglego.assets.SpriteManager
import com.veldan.junglego.assets.util.SoundUtil
import com.veldan.junglego.listeners.toClickable
import com.veldan.junglego.utils.disable
import com.veldan.junglego.utils.enable
import com.veldan.junglego.utils.log
import com.veldan.junglego.utils.setBoundsFigmaY
import com.veldan.junglego.utils.MiniGameGroup as MGG

class MiniGameGroup: AdvancedGroup() {

    companion object {
        const val BONUS_BOX_COUNT = 3
    }

    private val bonusPriceList = listOf<BonusPrice>(
        BonusPrice(10_000, SpriteManager.PriceSpriteList.PRICE_LIST.textureDataList[1].texture),
        BonusPrice(15_000, SpriteManager.PriceSpriteList.PRICE_LIST.textureDataList[2].texture),
        BonusPrice(20_000, SpriteManager.PriceSpriteList.PRICE_LIST.textureDataList[3].texture),
        BonusPrice(25_000, SpriteManager.PriceSpriteList.PRICE_LIST.textureDataList[4].texture),
        BonusPrice(30_000, SpriteManager.PriceSpriteList.PRICE_LIST.textureDataList[5].texture),
        BonusPrice(35_000, SpriteManager.PriceSpriteList.PRICE_LIST.textureDataList[6].texture),
        BonusPrice(40_000, SpriteManager.PriceSpriteList.PRICE_LIST.textureDataList[7].texture),
        BonusPrice(45_000, SpriteManager.PriceSpriteList.PRICE_LIST.textureDataList[8].texture),
        BonusPrice(50_000, SpriteManager.PriceSpriteList.PRICE_LIST.textureDataList[9].texture),
    )

    private val boxesGroup = AdvancedGroup()
    private val resultGroup = AdvancedGroup().apply {
        addAction(Actions.alpha(0f))
        disable()
    }

    private val bonusImage = Image()
    private val bonusPriceImage = Image()

    private var bonusPrice = 0L

    var resultBlock: (price: Long) -> Unit = {}



    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }



    private fun addActorsOnGroup() {
        addBackground()
        addActors(boxesGroup, resultGroup)
        addBonusBoxGroup()
        addBonusDone()
        addBonusImage()
        addBonusPriceImage()
    }



    private fun addBackground() {
        val image = Image(SpriteManager.GameSprite.BACKGROUND.textureData.texture)
        addAndFillActor(image)
    }

    private fun addBonusBoxGroup() {
        val bonusWinIndexList = List(BONUS_BOX_COUNT) { it }.shuffled().take(2)

        log("MINI GAME BONUS LIST: ${bonusWinIndexList.map { it.inc() }.sorted()}")

        var newY = MGG.BONUS_BOX_FIRST_Y
        List(BONUS_BOX_COUNT) { Image() }.onEachIndexed { index, image ->
            image.drawable = TextureRegionDrawable(SpriteManager.GameSprite.BONUS_BOX.textureData.texture)
            image.setBoundsFigmaY(MGG.BONUS_BOX_X, newY, MGG.BONUS_BOX_W, MGG.BONUS_BOX_H)
            newY += MGG.BONUS_BOX_H + MGG.BONUS_BOX_SPACE_VERTICAL

            image.toClickable().setOnClickListener {
                if (bonusWinIndexList.contains(index)) showBonusWin() else showBonusFail()
            }

            boxesGroup.addActor(image)
        }
    }

    private fun addBonusDone() {
        val button = ButtonClickable(style_6).apply {
            setBoundsFigmaY(MGG.BONUS_DONE_X, MGG.BONUS_DONE_Y, MGG.BONUS_DONE_W, MGG.BONUS_DONE_H)
            setOnClickListener { resultBlock(bonusPrice) }
        }
        resultGroup.addActor(button)
    }

    private fun addBonusImage() {
        bonusImage.setBoundsFigmaY(MGG.BONUS_IMAGE_X, MGG.BONUS_IMAGE_Y, MGG.BONUS_IMAGE_W, MGG.BONUS_IMAGE_H)
        resultGroup.addActor(bonusImage)
    }

    private fun addBonusPriceImage() {
        bonusPriceImage.setBoundsFigmaY(MGG.BONUS_PRICE_IMAGE_X, MGG.BONUS_PRICE_IMAGE_Y, MGG.BONUS_PRICE_IMAGE_W, MGG.BONUS_PRICE_IMAGE_H)
        resultGroup.addActor(bonusPriceImage)
    }



    private fun showBonusWin() {
        log("MINI GAME BONUS LIST: WIN")
        SoundUtil.apply { if (isPause.not()) WIN.play(volumeLevel.value) }

        bonusPriceList.random().also {
            bonusPrice = it.price
            bonusPriceImage.drawable = TextureRegionDrawable(it.texture)
        }

        bonusImage.drawable = TextureRegionDrawable(SpriteManager.GameSprite.BONUS_WIN.textureData.texture)
        showResultGroup()
    }

    private fun showBonusFail() {
        log("MINI GAME BONUS LIST: FAIL")
        SoundUtil.apply { if (isPause.not()) FAIL.play(volumeLevel.value) }

        bonusPrice = 0
        bonusPriceImage.drawable = TextureRegionDrawable(SpriteManager.PriceSpriteList.PRICE_LIST.textureDataList[0].texture)

        bonusImage.drawable = TextureRegionDrawable(SpriteManager.GameSprite.BONUS_FAIL.textureData.texture)
        showResultGroup()
    }



    private fun showResultGroup() {
        resultGroup.apply {
            addAction(Actions.fadeIn(2f))
            enable()
        }
        boxesGroup.apply {
            addAction(Actions.fadeOut(2f))
            disable()
        }
    }



    private data class BonusPrice(
        val price: Long,
        val texture: Texture,
    )

}