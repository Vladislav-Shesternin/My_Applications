package com.golovkoe.cryptosafe.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.golovkoe.cryptosafe.game.manager.FontTTFManager
import com.golovkoe.cryptosafe.game.manager.NavigationManager
import com.golovkoe.cryptosafe.game.manager.SpriteManager
import com.golovkoe.cryptosafe.game.utils.actor.setOnClickListener
import com.golovkoe.cryptosafe.game.utils.advanced.AdvancedGroup
import com.golovkoe.cryptosafe.game.utils.advanced.AdvancedScreen


class AccountScreen: AdvancedScreen() {

    private val listIm = List(6) { Image(SpriteManager.ListRegion.ACCOUNT.regionList[it]) }
    private val back   = Image(SpriteManager.GameRegion.BACK.region)


    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.BARODADA.region)
        super.show()
    }
    override fun AdvancedGroup.addActorsOnGroup() {

        var ny = 1127f
        listIm.onEachIndexed { index, image ->
            addActor(image)
            image.setBounds(118f, ny, 440f, 98f)
            ny -= 50f + 98f
            image.setOnClickListener { NavigationManager.back(index) }
        }

        addActor(back)
        back.apply {
            setBounds(277f, 61f, 156f, 60f)
            setOnClickListener { NavigationManager.back() }
        }
    }

}