package com.veldan.junglego.actors.slot

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.viewport.Viewport
import com.veldan.junglego.HEIGHT
import com.veldan.junglego.WIDTH
import com.veldan.junglego.actors.ScissorGroup
import com.veldan.junglego.advanced.AdvancedGroup
import com.veldan.junglego.assets.SpriteManager
import com.veldan.junglego.assets.util.MusicUtil
import com.veldan.junglego.assets.util.SoundUtil
import com.veldan.junglego.manager.SlotManager
import com.veldan.junglego.utils.SlotGroup
import com.veldan.junglego.utils.setBoundsFigmaY
import com.veldan.junglego.utils.toDelay
import kotlinx.coroutines.*
import java.util.*

import com.veldan.junglego.utils.Slot as S

class SlotGroup(val viewport: Viewport) : AdvancedGroup() {

    companion object {
        // SECONDS
        const val SHOW_WIN_TIME = 3f
        const val HIDE_WIN_TIME = 1f
        const val TRANSITION_LEFT_TIME = 2f
        const val TRANSITION_RIGHT_TIME = 2f
    }

    private val slotList = List(4) { Slot() }
    private val lineList = List(4) { Image() }

    private val slotManager = SlotManager(this, slotList)

    private val scissorGroup = ScissorGroup(viewport)

    lateinit var miniGameGroup: MiniGameGroup private set
    lateinit var superGameGroup: SuperGameGroup private set

    private var isTranslateLeft = false
    private var isTranslateRight = false

    private var time = 0f



    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }


    override fun act(delta: Float) {
        super.act(delta)
        translateLeft(delta)
        translateRight(delta)
    }


    private fun addActorsOnGroup() {
        addPanel()
        addSeparator()
        addScissorGroup()
        addSlots()
        addLines()
    }



    private fun addPanel() {
        val image = Image(SpriteManager.GameSprite.SLOT_GROUP_PANEL.textureData.texture).apply {
            setBoundsFigmaY(SlotGroup.PANEL_X, SlotGroup.PANEL_Y, SlotGroup.PANEL_W, SlotGroup.PANEL_H, this@SlotGroup.height)
        }
        addActor(image)
    }

    private fun addSeparator() {
        val image = Image(SpriteManager.GameSprite.SEPARATOR.textureData.texture).apply {
            setBoundsFigmaY(SlotGroup.SEPARATOR_X, SlotGroup.SEPARATOR_Y, SlotGroup.SEPARATOR_W, SlotGroup.SEPARATOR_H, this@SlotGroup.height)
        }
        addActor(image)
    }

    private fun addScissorGroup() {
        scissorGroup.apply {
            setBoundsScissor(SlotGroup.SCISSOR_X, SlotGroup.SCISSOR_Y, SlotGroup.SCISSOR_W, SlotGroup.SCISSOR_H)
        }
        addAndFillActor(scissorGroup)
    }

    private fun addSlots() {
        var newX = S.FIRST_X
        slotList.onEach {
            it.setBoundsFigmaY(newX, S.START_Y, S.W, S.H, height)
            newX += it.width + S.SPACE_HORIZONTAL
            scissorGroup.addActor(it)
        }
    }

    private fun addLines() {
        var newY = SlotGroup.LINE_FIRST_Y
        lineList.onEach { image ->
            image.drawable = TextureRegionDrawable(SpriteManager.GameSprite.LINE.textureData.texture)
            image.setBoundsFigmaY(SlotGroup.LINE_START_X, newY, SlotGroup.LINE_W, SlotGroup.LINE_H, height)
            newY += SlotGroup.LINE_H + SlotGroup.LINE_SPACE_VERTICAL
            scissorGroup.addActor(image)
        }
    }



    suspend fun spin() = coroutineScope<SpinResult> {
        slotManager.spin().apply {
            if (slotItemList.isNotEmpty()) {
                //// PLAY MUSIC WIN
                slotManager.rowWinIndexList.onEach { row -> launch { showWin(row) } }
                delay(SHOW_WIN_TIME.toDelay)
                delay(1f.toDelay)
                slotManager.rowWinIndexList.onEach { row -> launch { hideWin(row) } }
                delay(HIDE_WIN_TIME.toDelay)
            }
        }
    }



    private suspend fun showWin(row: Int) {
        MusicUtil.apply { currentMusic = MAIN }
        SoundUtil.apply { if (isPause.not()) WIN.play(volumeLevel.value) }

        Gdx.app.postRunnable { lineList[row].apply { addAction(Actions.moveTo(SlotGroup.LINE_END_X, y, SHOW_WIN_TIME)) } }
        delay(SHOW_WIN_TIME.toDelay)
    }

    private suspend fun hideWin(row: Int) {
        Gdx.app.postRunnable {
            lineList[row].apply {
                addAction(Actions.moveTo(SlotGroup.LINE_START_X, y, HIDE_WIN_TIME))
            }
        }
        delay(HIDE_WIN_TIME.toDelay)
    }



    private fun translateLeft(deltaTime: Float) {
        if (isTranslateLeft.not()) return

        time += deltaTime
        viewport.camera.position.x -= (700 / (TRANSITION_LEFT_TIME * 60))

        if (time >= TRANSITION_LEFT_TIME) {
            time = 0f
            isTranslateLeft = false
            viewport.camera.position.x = -350f
        }
    }

    private fun translateRight(deltaTime: Float) {
        if (isTranslateRight.not()) return

        time += deltaTime
        viewport.camera.position.x += (700 / (TRANSITION_RIGHT_TIME * 60))

        if (time >= TRANSITION_RIGHT_TIME) {
            time = 0f
            isTranslateRight = false
            viewport.camera.position.x = 350f
        }
    }



    suspend fun addMiniGameGroup() {
        MusicUtil.apply { currentMusic = MINI_GAME.apply { isLooping = true } }

        miniGameGroup = MiniGameGroup().apply {
            setBoundsFigmaY(SlotGroup.BONUS_GAME_GROUP_X, SlotGroup.BONUS_GAME_GROUP_Y, WIDTH, HEIGHT, this@SlotGroup.height)
        }
        addActor(miniGameGroup)

        isTranslateLeft = true
        delay(TRANSITION_LEFT_TIME.toDelay)
    }

    suspend fun removeMiniGameGroup() {
        isTranslateRight = true
        delay(TRANSITION_RIGHT_TIME.toDelay)
        removeActor(miniGameGroup)
    }



    suspend fun addSuperGameGroup() {
        MusicUtil.apply { currentMusic = SUPER_GAME.apply { isLooping = true } }

        superGameGroup = SuperGameGroup().apply {
            setBoundsFigmaY(SlotGroup.BONUS_GAME_GROUP_X, SlotGroup.BONUS_GAME_GROUP_Y, WIDTH, HEIGHT, this@SlotGroup.height)
        }
        addActor(superGameGroup)

        isTranslateLeft = true
        delay(TRANSITION_LEFT_TIME.toDelay)
    }

    suspend fun removeSuperGameGroup() {
        isTranslateRight = true
        delay(TRANSITION_RIGHT_TIME.toDelay)
        removeActor(superGameGroup)
    }

}