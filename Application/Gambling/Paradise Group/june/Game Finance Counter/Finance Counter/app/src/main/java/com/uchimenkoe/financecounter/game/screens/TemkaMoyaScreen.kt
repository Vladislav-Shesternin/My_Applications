package com.uchimenkoe.financecounter.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.uchimenkoe.financecounter.game.calaraka
import com.uchimenkoe.financecounter.game.manager.NavigationManager
import com.uchimenkoe.financecounter.game.manager.SpriteManager
import com.uchimenkoe.financecounter.game.utils.GameColor
import com.uchimenkoe.financecounter.game.utils.actor.setOnClickListener
import com.uchimenkoe.financecounter.game.utils.advanced.AdvancedGroup
import com.uchimenkoe.financecounter.game.utils.advanced.AdvancedScreen

class TemkaMoyaScreen: AdvancedScreen() {

    private val palette = Image(SpriteManager.GameRegion.REBRO.region)
    private val back    = Image(SpriteManager.GameRegion.BACKA.region)
    private val _1      = Actor().apply { userObject = GameColor._1 }
    private val _2      = Actor().apply { userObject = GameColor._2 }
    private val _3      = Actor().apply { userObject = GameColor._3 }
    private val _4      = Actor().apply { userObject = GameColor._4 }
    private val _5      = Actor().apply { userObject = GameColor._5 }
    private val _6      = Actor().apply { userObject = GameColor._6 }

    override fun AdvancedGroup.addActorsOnGroup() {
        addActors(palette, back)
        palette.setBounds(102f, 472f, 641f, 962f)
        back.setBounds(275f, 120f, 295f, 295f)
        back.setOnClickListener { NavigationManager.back() }

        addActors(_1, _2, _3, _4, _5, _6)

        _1.apply {
            setBounds(102f, 1152f, 200f, 200f)
            setOnClickListener { calaraka = userObject as Color }
        }
        _2.apply {
            setBounds(461f, 1152f, 200f, 200f)
            setOnClickListener { calaraka = userObject as Color }
        }
        _3.apply {
            setBounds(102f, 812f, 200f, 200f)
            setOnClickListener { calaraka = userObject as Color }
        }
        _4.apply {
            setBounds(461f, 812f, 200f, 200f)
            setOnClickListener { calaraka = userObject as Color }
        }
        _5.apply {
            setBounds(102f, 472f, 200f, 200f)
            setOnClickListener { calaraka = userObject as Color }
        }
        _6.apply {
            setBounds(461f, 472f, 200f, 200f)
            setOnClickListener { calaraka = userObject as Color }
        }

    }

}