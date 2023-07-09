package com.shapovalovd.financecomitet.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.shapovalovd.financecomitet.game.calara
import com.shapovalovd.financecomitet.game.manager.NavigationManager
import com.shapovalovd.financecomitet.game.manager.SpriteManager
import com.shapovalovd.financecomitet.game.utils.GameColor
import com.shapovalovd.financecomitet.game.utils.actor.setOnClickListener
import com.shapovalovd.financecomitet.game.utils.advanced.AdvancedGroup
import com.shapovalovd.financecomitet.game.utils.advanced.AdvancedScreen
import com.shapovalovd.financecomitet.util.log

class TemaScreen: AdvancedScreen() {

    private val palette = Image(SpriteManager.GameRegion.COLORITUS.region)
    private val back    = Image(SpriteManager.GameRegion.BACKCKCKACH.region)
    private val _1      = Actor().apply { userObject = GameColor._1 }
    private val _2      = Actor().apply { userObject = GameColor._2 }
    private val _3      = Actor().apply { userObject = GameColor._3 }
    private val _4      = Actor().apply { userObject = GameColor._4 }
    private val _5      = Actor().apply { userObject = GameColor._5 }
    private val _6      = Actor().apply { userObject = GameColor._6 }

    override fun AdvancedGroup.addActorsOnGroup() {
        addActors(palette, back)
        palette.setBounds(77f, 485f, 524f, 790f)
        back.setBounds(472f, 85f, 164f, 166f)
        back.setOnClickListener { NavigationManager.back() }

        addActors(_1, _2, _3, _4, _5, _6)

        _1.apply {
            setBounds(77f, 1049f, 226f, 226f)
            setOnClickListener { calara = userObject as Color }
        }
        _2.apply {
            setBounds(77f, 767f, 226f, 226f)
            setOnClickListener { calara = userObject as Color }
        }
        _3.apply {
            setBounds(77f, 485f, 226f, 226f)
            setOnClickListener { calara = userObject as Color }
        }
        _4.apply {
            setBounds(375f, 1049f, 226f, 226f)
            setOnClickListener { calara = userObject as Color }
        }
        _5.apply {
            setBounds(375f, 767f, 226f, 226f)
            setOnClickListener { calara = userObject as Color }
        }
        _6.apply {
            setBounds(375f, 485f, 226f, 226f)
            setOnClickListener { calara = userObject as Color }
        }

    }

}