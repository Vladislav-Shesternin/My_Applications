package com.egypt.tian.sto.game.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.egypt.tian.sto.game.game.LibGDXGame
import com.egypt.tian.sto.game.game.utils.actor.setOnClickListener
import com.egypt.tian.sto.game.game.utils.advanced.AdvancedScreen
import com.egypt.tian.sto.game.game.utils.advanced.AdvancedStage
import com.egypt.tian.sto.game.game.utils.region

class LevelScreen(override val game: LibGDXGame) : AdvancedScreen() {

    companion object {
        var STATIC_LEVEL = 0
    }

    private val panelImg = Image(game.allAssets.level)

    override fun show() {
        setBackBackground(game.allAssets.bubinka.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addPanelImg()
        addMenu()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addPanelImg() {
        addActor(panelImg)
        panelImg.setBounds(21f, 408f, 1051f, 1380f)

        val back = Actor()
        addActor(back)
        back.apply {
            setBounds(866f, 1363f, 206f, 206f)
            setOnClickListener(game.soundUtil) { animHideScreen { game.navigationManager.back() } }
        }
    }

    private fun AdvancedStage.addMenu() {
        val selections = listOf(
            LevelScreen   ::class.java.name,
            SettingsScreen::class.java.name,
            RulesScreen   ::class.java.name,
        )

        var ny = 1173f
        selections.onEachIndexed { index, screenName ->
            addActor(Actor().apply {
                setBounds(250f, ny, 593f, 206f)
                ny -= 59f+206f
                setOnClickListener(game.soundUtil) {
                    STATIC_LEVEL = index

                    animHideScreen {
                        game.navigationManager.navigate(GameScreen::class.java.name, LevelScreen::class.java.name)
                    }
                }
            })
        }
    }

}