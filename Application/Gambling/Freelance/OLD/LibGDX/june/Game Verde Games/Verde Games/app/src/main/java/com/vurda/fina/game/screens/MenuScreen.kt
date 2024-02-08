package com.vurda.fina.game.screens

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.vurda.fina.game.actors.DGDH
import com.vurda.fina.game.actors.checkbox.ACheckBox
import com.vurda.fina.game.actors.checkbox.ACheckBoxStyle
import com.vurda.fina.game.manager.NavigationManager
import com.vurda.fina.game.manager.SpriteManager
import com.vurda.fina.game.musicUtil
import com.vurda.fina.game.screens.levels.*
import com.vurda.fina.game.utils.advanced.AdvancedGroup
import com.vurda.fina.game.utils.advanced.AdvancedScreen

class MenuScreen: AdvancedScreen() {

    companion object {
        private var isFirstOpen = true
        private var isPauseMusic = false
    }

    private val musicBox = ACheckBox(ACheckBoxStyle.music)
    private val image    = Image(SpriteManager.GameRegion.DEVKA.region)

    private val reiie = listOf(
        SpriteManager.GameRegion.LEV_1.region,
        SpriteManager.GameRegion.LEV_2.region,
        SpriteManager.GameRegion.LEV_3.region,
        SpriteManager.GameRegion.LEV_4.region,
        SpriteManager.GameRegion.LEV_5.region,
    )
    private val urovList = List(5) { Image(reiie[it]) }

    private val hrgd = DGDH(50f, endGap = 600f)
    private val scrd = ScrollPane(hrgd)


    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.BARAGRANDOM.region)
        super.show()

        if (isFirstOpen) {
            isFirstOpen = false
            musicUtil.apply { music = MAIN.apply { isLooping = true } }
        }
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addUrovni()
        addDevka()
        addMusic()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addUrovni() {
        addActor(scrd)
        scrd.setBounds(0f, 243f, 1555f, 443f)

        val uroven = listOf(
            Level_1_Screen(),
            Level_2_Screen(),
            Level_3_Screen(),
            Level_4_Screen(),
            Level_5_Screen(),
        )

        urovList.onEachIndexed { ind, urv ->
            hrgd.addActor(urv.apply {
                setSize(559f, 443f)
                addListener(object : ClickListener() {
                    override fun clicked(event: InputEvent?, x: Float, y: Float) {
                        NavigationManager.navigate(uroven[ind], MenuScreen())
                    }
                })
            })
        }
    }

    private fun AdvancedGroup.addMusic() {
        addActor(musicBox)

        musicBox.apply {
            setBounds(249f, 33f, 193f, 193f)

            if (isPauseMusic) check() else uncheck()

            setOnCheckListener {
                if (it) {
                    isPauseMusic = true
                    musicUtil.music?.pause()
                } else {
                    isPauseMusic = false
                    musicUtil.music?.play()
                }
            }
        }
    }

    private fun AdvancedGroup.addDevka() {
        addActor(image)

        image.apply {
            setBounds(1022f, 0f, 533f, 570f)

            addAction(Actions.forever(Actions.sequence(
                Actions.moveBy(-123f, 0f, 5f),
                Actions.moveBy(123f, 0f, 5f),
            )))
        }
    }

}