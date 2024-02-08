package com.favsport.slots.actors

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.favsport.slots.SOUND_VOLUME
import com.favsport.slots.advanced.AdvancedGroup
import com.favsport.slots.assets.SpriteManager
import com.favsport.slots.listeners.toClickable
import com.favsport.slots.utils.*
import com.favsport.slots.utils.slot.SlotItem
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlin.random.Random

class BonusGroup: AdvancedGroup() {

    private val winItemList = listOf<SlotItem>(
        SlotItem(0, 1500, SpriteManager.CollectionSpriteList.PRICE_LIST.textureDataList[0].texture),
        SlotItem(1, 2000, SpriteManager.CollectionSpriteList.PRICE_LIST.textureDataList[1].texture),
        SlotItem(2, 5000, SpriteManager.CollectionSpriteList.PRICE_LIST.textureDataList[2].texture),
        SlotItem(3, 7000, SpriteManager.CollectionSpriteList.PRICE_LIST.textureDataList[3].texture),
        SlotItem(4, 9500, SpriteManager.CollectionSpriteList.PRICE_LIST.textureDataList[4].texture),
        SlotItem(5, 12000, SpriteManager.CollectionSpriteList.PRICE_LIST.textureDataList[5].texture),
        SlotItem(6, 15000, SpriteManager.CollectionSpriteList.PRICE_LIST.textureDataList[6].texture),
        SlotItem(7, 17000, SpriteManager.CollectionSpriteList.PRICE_LIST.textureDataList[7].texture),
    )

    private val bonusBackground = Image()
    private val close = Image()
    private val listBonusBox = List(3) { Image(SpriteManager.GameSprite.BONUS_BOX.textureData.texture) }
    private val listBonusWin = List(3) { Image() }

    private var openedBonusBox: Image? = null
    private var listWin = MutableList<SlotItem?>(3) { null }

    private val choiceBoxIndexFlow = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val hideFlow = MutableSharedFlow<Boolean>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)


    init {
        setBoundsFigmaY(BONUS_FAKE_START_X, BONUS_FAKE_Y, BONUS_FAKE_W, BONUS_FAKE_H)
        addActorsOnGroup()
    }



    private fun addActorsOnGroup() {
        addBonusBackground()
        addBonusClose()
        addBonusWinList()
        addBonusBoxList()
    }



    private fun addBonusBackground() {
        val image = bonusBackground.apply {
            drawable = TextureRegionDrawable(SpriteManager.GameSprite.BONUS_BACKGROUND.textureData.texture)
        }
        addAndFillActor(image)
    }

    private fun addBonusWinList() {
        val group = AdvancedGroup().apply { setBoundsFigmaY(BONUS_WIN_GROUP_X, BONUS_WIN_GROUP_Y, BONUS_WIN_GROUP_W, BONUS_WIN_GROUP_H) }
        listBonusWin.onEach { win ->
            win.setSize(BONUS_WIN_W, BONUS_WIN_H)
            group.addActorChain(win, ChainStyle.StartTop_EndBottom, 1, 0f, BONUS_WIN_SPACE_VERTICAL)
        }
        addActor(group)
    }

    private fun addBonusBoxList() {
        val group = AdvancedGroup().apply { setBoundsFigmaY(BONUS_BOX_GROUP_X, BONUS_BOX_GROUP_Y, BONUS_BOX_GROUP_W, BONUS_BOX_GROUP_H) }
        listBonusBox.onEachIndexed { index, box ->
            box.toClickable().setOnClickListener {
                listBonusBox.onEach { it.touchable = Touchable.disabled }
                openedBonusBox = box
                box.addAction(Actions.fadeOut(1f))
                choiceBoxIndexFlow.tryEmit(index)
            }
            box.setSize(BONUS_BOX_W, BONUS_BOX_H)
            group.addActorChain(box, ChainStyle.StartTop_EndBottom, 1, 0f, BONUS_BOX_SPACE_VERTICAL)
        }
        addActor(group)
    }

    private fun addBonusClose() {
        val image = close.apply {
            isVisible = false
            drawable = TextureRegionDrawable(SpriteManager.GameSprite.BONUS_CLOSE.textureData.texture)
            setBoundsFigmaY(BONUS_CLOSE_X, BONUS_CLOSE_Y, BONUS_CLOSE_W, BONUS_CLOSE_H)
            toClickable().setOnClickListener(SoundUtil.CLICK) { bonusCloseHandler() }
        }
        addActor(image)
    }



    private fun Image.bonusCloseHandler() {
        hideBonusMiniGame()
    }



    suspend fun showBonusMiniGame() = CompletableDeferred<Int>().also { continuation ->
        SoundUtil.SHOW_BONUS.play(1f)
        parent.touchable = Touchable.enabled
        generateWin()
        addAction(Actions.moveTo(BONUS_FAKE_END_X, BONUS_FAKE_Y, 2f, Interpolation.pow2Out))
        choiceBoxIndexFlow.take(1).collect { boxIndex ->
            close.isVisible = true
            MusicUtil.currentMusic.pause()
            if (listWin[boxIndex] == null) SoundUtil.FAIL_BONUS.play(1f) else SoundUtil.WIN_BONUS.play(1f)
            hideFlow.take(1).collect { isHide ->
                MusicUtil.currentMusic.play()
                continuation.complete(listWin[boxIndex]?.price ?: 0)
                choiceBoxIndexFlow.resetReplayCache()
                hideFlow.resetReplayCache()
            }
        }
    }.await()

    fun hideBonusMiniGame() {
        close.isVisible = false
        parent.touchable = Touchable.disabled
        addAction(Actions.sequence(
            Actions.moveTo(BONUS_FAKE_START_X, BONUS_FAKE_Y, 2f, Interpolation.pow2Out),
            Actions.delay(1f),
            Actions.run {
                openedBonusBox?.addAction(Actions.fadeIn(0f))
                listBonusBox.onEach { it.touchable = Touchable.enabled }
                hideFlow.tryEmit(true)
            }
        ))

    }



    private fun generateWin() {
        repeat(3) {
            val randomWin = if (Random.nextBoolean()) winItemList.random() else null
            listWin[it] = randomWin
            log("Box: $it = $randomWin")
            listBonusWin[it].drawable = TextureRegionDrawable(randomWin?.item ?: SpriteManager.GameSprite.NONE.textureData.texture)
        }
    }

}