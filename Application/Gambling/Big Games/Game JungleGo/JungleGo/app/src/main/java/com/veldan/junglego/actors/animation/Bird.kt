package com.veldan.junglego.actors.animation

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.veldan.junglego.advanced.AdvancedInputProcessor
import com.veldan.junglego.assets.SpriteManager
import com.veldan.junglego.listeners.toClickable
import com.veldan.junglego.utils.log

class Bird : Image() {

    private val keyFrames = SpriteManager.AnimationSpriteList.BIRD_LIST.textureDataList.map { it.texture }
    private val frameDuration = 0.166f
    private val timeLastFrame = ((keyFrames.size * 2) - 2) * frameDuration

    private val animation = Animation<Texture>(frameDuration, *keyFrames.toTypedArray()).apply { playMode = Animation.PlayMode.LOOP_PINGPONG }

    private var time = 0f



    override fun act(delta: Float) {
        super.act(delta)

        time += delta
        if (time >= timeLastFrame) time = 0f

        drawable = TextureRegionDrawable(animation.getKeyFrame(time))
    }

}