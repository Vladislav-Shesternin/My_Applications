package com.pharhaslo.slo7.game.actors.slot

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.pharhaslo.slo7.game.advanced.AdvancedGroup
import com.pharhaslo.slo7.game.assets.SpriteManager
import com.pharhaslo.slo7.game.assets.util.SoundUtil
import com.pharhaslo.slo7.game.utils.setBoundsFigmaY
import com.pharhaslo.slo7.game.utils.GlowSlot3 as GS3

class GlowSlot3: AdvancedGroup() {

    companion object{
        const val GLOW_COUNT = 3

        // seconds
        const val TIME_BLOW_IN = 1f
    }

    private val glowImageList = List(GLOW_COUNT) {
        Image(SpriteManager.GameSprite.GLOW.data.texture).apply { addAction(Actions.alpha(0f)) }
    }


    init {
        setSize(GS3.W, GS3.H)
        addActorsOnGroup()
    }



    private fun addActorsOnGroup(){
        addSlotItemList()
    }


    private fun addSlotItemList() {
        glowImageList.onEachIndexed { index, image ->
            val newY = (GS3.GLOW_H + GS3.GLOW_SPACE_VERTICAL) * index
            image.setBoundsFigmaY(0f, newY, GS3.GLOW_W, GS3.GLOW_H, GS3.H)
            addActor(image)
        }
    }



    fun blowIn(rowIndex: Int) {
        SoundUtil.apply { if (isPause.not()) WIN.play(volumeLevel.value) }
        Gdx.app.postRunnable { glowImageList[rowIndex].addAction(Actions.addAction(Actions.fadeIn(
            TIME_BLOW_IN
        ))) }
    }

    fun blowOut(rowIndex: Int) {
        glowImageList[rowIndex].addAction(Actions.addAction(Actions.fadeOut(TIME_BLOW_IN)))
    }

    fun blowInAll() {
        glowImageList.onEach { image -> image.addAction(Actions.addAction(Actions.fadeIn(
            TIME_BLOW_IN
        ))) }
    }

    fun blowOutAll() {
        Gdx.app.postRunnable { glowImageList.onEach { image -> image.addAction(Actions.addAction(Actions.fadeOut(
            TIME_BLOW_IN
        ))) } }
    }

}