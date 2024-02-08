package com.favsport.slots.screens

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.viewport.FitViewport
import com.favsport.slots.HEIGHT
import com.favsport.slots.WIDTH
import com.favsport.slots.actors.ButtonClickable
import com.favsport.slots.advanced.AdvancedScreen
import com.favsport.slots.advanced.AdvancedStage
import com.favsport.slots.assets.SpriteManager
import com.favsport.slots.languageSprite
import com.favsport.slots.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CollectionScreen : AdvancedScreen() {
    override val viewport = FitViewport(WIDTH, HEIGHT)
    
    private val price = Image() 
    private val item = Image()

    private val collectionItemList = listOf<CollectionItem>(
       CollectionItem(price = SpriteManager.CollectionSpriteList.PRICE_LIST.textureDataList[0].texture, item = SpriteManager.CollectionSpriteList.ITEM_LIST.textureDataList[0].texture),
       CollectionItem(price = SpriteManager.CollectionSpriteList.PRICE_LIST.textureDataList[1].texture, item = SpriteManager.CollectionSpriteList.ITEM_LIST.textureDataList[1].texture),
       CollectionItem(price = SpriteManager.CollectionSpriteList.PRICE_LIST.textureDataList[2].texture, item = SpriteManager.CollectionSpriteList.ITEM_LIST.textureDataList[2].texture),
       CollectionItem(price = SpriteManager.CollectionSpriteList.PRICE_LIST.textureDataList[3].texture, item = SpriteManager.CollectionSpriteList.ITEM_LIST.textureDataList[3].texture),
       CollectionItem(price = SpriteManager.CollectionSpriteList.PRICE_LIST.textureDataList[4].texture, item = SpriteManager.CollectionSpriteList.ITEM_LIST.textureDataList[4].texture),
       CollectionItem(price = SpriteManager.CollectionSpriteList.PRICE_LIST.textureDataList[5].texture, item = SpriteManager.CollectionSpriteList.ITEM_LIST.textureDataList[5].texture),
       CollectionItem(price = SpriteManager.CollectionSpriteList.PRICE_LIST.textureDataList[6].texture, item = SpriteManager.CollectionSpriteList.ITEM_LIST.textureDataList[6].texture),
       CollectionItem(price = SpriteManager.CollectionSpriteList.PRICE_LIST.textureDataList[7].texture, item = SpriteManager.CollectionSpriteList.ITEM_LIST.textureDataList[7].texture),
    )

    private val currentCollectionItemIndexFlow = MutableStateFlow(0)

    private val coroutineCollectionItemIndex = CoroutineScope(Dispatchers.Default)



    override fun show() {
        super.show()
        stage.addActorsOnStage()
    }

    override fun dispose() {
        super.dispose()
        cancelCoroutinesAll(coroutineCollectionItemIndex)
    }



    private fun AdvancedStage.addActorsOnStage(){
        addCoin()
        addPrice()
        addStand()
        addItem()
        addLeft()
        addRight()
        addBack()
    }



    private fun AdvancedStage.addCoin(){
        val image = Image(SpriteManager.CollectionSprite.COIN.textureData.texture).apply {
            setBoundsFigmaY(COIN_X, COIN_Y, COIN_W, COIN_H)
        }
        addActor(image)
    }

    private fun AdvancedStage.addPrice(){
        val image = price.apply {
            coroutineCollectionItemIndex.launch {
                currentCollectionItemIndexFlow.collect { drawable = TextureRegionDrawable(collectionItemList[it].price) }
            }
            setBoundsFigmaY(PRICE_X, PRICE_Y, PRICE_W, PRICE_H)
        }
        addActor(image)
    }

    private fun AdvancedStage.addItem(){
        val image = item.apply {
            coroutineCollectionItemIndex.launch {
                currentCollectionItemIndexFlow.collect { drawable = TextureRegionDrawable(collectionItemList[it].item) }
            }
            setBoundsFigmaY(ITEM_X, ITEM_Y, ITEM_W, ITEM_H)
        }
        addActor(image)
    }

    private fun AdvancedStage.addStand(){
        val image = Image(SpriteManager.CollectionSprite.STAND.textureData.texture).apply {
            setBoundsFigmaY(STAND_X, STAND_Y, STAND_W, STAND_H)
        }
        addActor(image)
    }

    private fun AdvancedStage.addLeft(){
        val button = ButtonClickable(ButtonClickable.Style(
            default = SpriteManager.CollectionSprite.LEFT_DEF.textureData.texture,
            pressed = SpriteManager.CollectionSprite.LEFT_PRESS.textureData.texture,
        )).apply {
            setBoundsFigmaY(LEFT_X, LEFT_Y, LEFT_W, LEFT_H)
            setOnClickListener(SoundUtil.CLICK) { leftHandler() }
        }
        addActor(button)
    }

    private fun AdvancedStage.addRight(){
        val button = ButtonClickable(ButtonClickable.Style(
            default = SpriteManager.CollectionSprite.RIGHT_DEF.textureData.texture,
            pressed = SpriteManager.CollectionSprite.RIGHT_PRESS.textureData.texture,
        )).apply {
            setBoundsFigmaY(RIGHT_X, RIGHT_Y, RIGHT_W, RIGHT_H)
            setOnClickListener(SoundUtil.CLICK) { rightHandler() }
        }
        addActor(button)
    }

    private fun AdvancedStage.addBack(){
        val button = ButtonClickable(ButtonClickable.Style(
            default = languageSprite.back_def,
            pressed = languageSprite.back_press,
        )).apply { 
            setBoundsFigmaY(BACK_X, BACK_Y, BACK_W, BACK_H)
            setOnClickListener(SoundUtil.CLICK) { backHandler() }
        }
        addActor(button)
    }



    private fun ButtonClickable.leftHandler() {
        with(currentCollectionItemIndexFlow) {
            if (value.dec() == -1) value = collectionItemList.lastIndex
            else value -= 1
        }
    }

    private fun ButtonClickable.rightHandler() {
        with(currentCollectionItemIndexFlow) {
            if (value.inc() == collectionItemList.size) value = 0
            else value += 1
        }
    }

    private fun ButtonClickable.backHandler() {
        NavigationUtil.back()
    }



    private data class CollectionItem(
        val price: Texture,
        val item: Texture,
    )
    
}