package com.bandagames.mpuzzle.g.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.bandagames.mpuzzle.g.game.LibGDXGame
import com.bandagames.mpuzzle.g.game.actors.AButton
import com.bandagames.mpuzzle.g.game.utils.TIME_ANIM
import com.bandagames.mpuzzle.g.game.utils.actor.animHide
import com.bandagames.mpuzzle.g.game.utils.actor.setOnClickListener
import com.bandagames.mpuzzle.g.game.utils.advanced.AdvancedScreen
import com.bandagames.mpuzzle.g.game.utils.advanced.AdvancedStage
import com.bandagames.mpuzzle.g.game.utils.region

class StallScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val rules = Image(game.Aoll.stall)
    private val back  = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        setBackBackground(game.Aoll.begi.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(rules)
        rules.setBounds(160f, 107f, 797f, 1737f)

        addActor(back)
        back.apply {
            setBounds(429f, 1474f, 224f, 129f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }


        val a1 = Actor()
        val a2 = Actor()
        val a3 = Actor()
        val a4 = Actor()

        addActors(a1, a2, a3, a4)

        a1.apply {
            setBounds(160f, 1150f, 241f, 237f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(GameScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        a2.apply {
            setBounds(716f, 905f, 241f, 237f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(GameScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        a3.apply {
            setBounds(182f, 550f, 241f, 237f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(GameScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
        a4.apply {
            setBounds(692f, 270f, 241f, 237f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(GameScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }

    }

}