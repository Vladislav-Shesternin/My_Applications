package com.legojum.kangarooper.game.actors

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.legojum.kangarooper.game.utils.advanced.AdvancedGroup
import com.legojum.kangarooper.game.utils.advanced.AdvancedScreen

class ABackground2 constructor(override val screen: AdvancedScreen): AdvancedGroup() {

    private val top = ABackground(screen)
    private val bot = ABackground(screen)

    override fun addActorsOnGroup() {
        setSize(1080f, 7106f)
        addActors(bot, top)
        bot.setBounds(0f,0f, 1080f, 3553f)
        top.setBounds(0f,3553f, 1080f, 3553f)
    }

    fun startAnim() {
        addAction(Actions.forever(Actions.sequence(
            Actions.moveBy(0f, -3553f, 15f),
            Actions.moveBy(0f, 3553f),
        )))
    }

}