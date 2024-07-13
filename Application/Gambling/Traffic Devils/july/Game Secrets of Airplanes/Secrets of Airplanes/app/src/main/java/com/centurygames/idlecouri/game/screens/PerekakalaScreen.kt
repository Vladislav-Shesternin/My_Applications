package com.centurygames.idlecouri.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.centurygames.idlecouri.game.LibGDXGame
import com.centurygames.idlecouri.game.actors.AButton
import com.centurygames.idlecouri.game.utils.TIME_ANIM
import com.centurygames.idlecouri.game.utils.actor.animHide
import com.centurygames.idlecouri.game.utils.actor.animShow
import com.centurygames.idlecouri.game.utils.actor.setBounds
import com.centurygames.idlecouri.game.utils.actor.setOnClickListener
import com.centurygames.idlecouri.game.utils.advanced.AdvancedScreen
import com.centurygames.idlecouri.game.utils.advanced.AdvancedStage
import com.centurygames.idlecouri.game.utils.region

class PerekakalaScreen(override val game: LibGDXGame) : AdvancedScreen() {

    companion object {
        var PLANE_INDEX = 0
            private set
    }

    private val planes = Image(game.alpha.vvv)
    private val back   = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.lobster.lister.random().region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(planes)
        planes.setBounds(71f, 341f, 486f, 696f)

        addActor(back)
        back.apply {
            setBounds(156f, 82f, 315f, 92f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

        val p1 = Actor()
        val p2 = Actor()
        val p3 = Actor()
        val p4 = Actor()
        addActors(p1,p2,p3,p4)
        p1.apply {
            setBounds(73f, 597f, 219f, 159f)
            setOnClickListener(game.soundUtil) {
                PLANE_INDEX = 0
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(GameScreen::class.java.name, PerekakalaScreen::class.java.name)
                }
            }
        }
        p2.apply {
            setBounds(335f, 597f, 219f, 159f)
            setOnClickListener(game.soundUtil) {
                PLANE_INDEX = 1
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(GameScreen::class.java.name, PerekakalaScreen::class.java.name)
                }
            }
        }
        p3.apply {
            setBounds(73f, 341f, 219f, 159f)
            setOnClickListener(game.soundUtil) {
                PLANE_INDEX = 2
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(GameScreen::class.java.name, PerekakalaScreen::class.java.name)
                }
            }
        }
        p4.apply {
            setBounds(335f, 341f, 219f, 159f)
            setOnClickListener(game.soundUtil) {
                PLANE_INDEX = 3
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(GameScreen::class.java.name, PerekakalaScreen::class.java.name)
                }
            }
        }

    }

}