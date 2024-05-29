package com.centurygames.idlecourie.game.actors

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.centurygames.idlecourie.game.utils.actor.animHide
import com.centurygames.idlecourie.game.utils.actor.animShow
import com.centurygames.idlecourie.game.utils.advanced.AdvancedGroup
import com.centurygames.idlecourie.game.utils.advanced.AdvancedScreen

class ATile(
    override val screen: AdvancedScreen,
    val id: Int,
    val region: TextureRegion
): AdvancedGroup() {

    // Actor
    private val imgBackground = Image(screen.game.valhalla.pitannie)
    private val img = Image(region).apply { color.a = 0f }

    // Field
    private val timeAnim = 0.223f

    override fun addActorsOnGroup() {
        addAndFillActor(imgBackground)
        addAndFillActor(img)
    }

    fun animOpen() {
        disable()
        imgBackground.animHide(timeAnim)
        img.animShow(timeAnim)
    }

    fun animClose() {
        img.animHide(timeAnim) { enable() }
        imgBackground.animShow(timeAnim)
    }

}

fun Actor.disable() {
    touchable = Touchable.disabled
}

fun Actor.enable() {
    touchable = Touchable.enabled
}