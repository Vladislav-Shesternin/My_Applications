package com.boo.koftre.sure.game.game.actors

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.boo.koftre.sure.game.game.utils.actor.animHide
import com.boo.koftre.sure.game.game.utils.actor.animShow
import com.boo.koftre.sure.game.game.utils.actor.disable
import com.boo.koftre.sure.game.game.utils.actor.enable
import com.boo.koftre.sure.game.game.utils.advanced.AdvancedGroup
import com.boo.koftre.sure.game.game.utils.advanced.AdvancedScreen

class ATile(
    override val screen: AdvancedScreen,
    val id: Int,
    val region: TextureRegion
): AdvancedGroup() {

    // Actor
    private val img = Image(region)

    // Field
    private val timeAnim = 0.27f

    override fun addActorsOnGroup() {
        color.a = 0f
        addAndFillActor(img)
    }

    fun animOpen() {
        disable()
        animShow(timeAnim)
    }

    fun animClose() {
        animHide(timeAnim) { enable() }
    }

}