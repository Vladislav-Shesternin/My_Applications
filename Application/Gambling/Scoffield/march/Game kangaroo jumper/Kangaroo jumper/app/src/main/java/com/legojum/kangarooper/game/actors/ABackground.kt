package com.legojum.kangarooper.game.actors

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.legojum.kangarooper.game.utils.advanced.AdvancedGroup
import com.legojum.kangarooper.game.utils.advanced.AdvancedScreen

class ABackground constructor(override val screen: AdvancedScreen): AdvancedGroup() {

    private val bot = Image(screen.game.loaderAssets.BOT)
    private val top = Image(screen.game.loaderAssets.TOP)

    override fun addActorsOnGroup() {
        setSize(1080f, 3553f)
        addActors(bot, top)

        bot.setSize(1080f, 1633f)
        top.setBounds(0f, 1633f, 1080f, 1920f)
    }

}