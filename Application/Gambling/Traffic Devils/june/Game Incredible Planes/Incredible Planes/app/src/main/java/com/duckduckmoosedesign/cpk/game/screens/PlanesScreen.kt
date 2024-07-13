package com.duckduckmoosedesign.cpk.game.screens

import com.duckduckmoosedesign.cpk.game.LibGDXGame
import com.duckduckmoosedesign.cpk.game.actors.AButton
import com.duckduckmoosedesign.cpk.game.utils.TIME_ANIM
import com.duckduckmoosedesign.cpk.game.utils.actor.animHide
import com.duckduckmoosedesign.cpk.game.utils.actor.animShow
import com.duckduckmoosedesign.cpk.game.utils.actor.setOnClickListener
import com.duckduckmoosedesign.cpk.game.utils.advanced.AdvancedScreen
import com.duckduckmoosedesign.cpk.game.utils.advanced.AdvancedStage
import com.duckduckmoosedesign.cpk.game.utils.region
import com.badlogic.gdx.scenes.scene2d.Actor
import com.duckduckmoosedesign.cpk.game.actors.AImage

var indexPLANE = 0
    private set

class PlanesScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val planes = AImage(this, game.Ser.PNS)
    private val back   = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.Mis.Firster.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(planes.also { it.setBounds(3f, 194f, 1410f, 538f) })

        var nx = 20f
        var ny = 519f
        repeat(6) { index ->
            val actor = Actor()
            addActor(actor)
            actor.setBounds(nx,ny,392f,212f)
            nx+=99+392
            if (index.inc() % 3 == 0) {
                nx = 20f
                ny -= 99+212
            }

            actor.setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    indexPLANE = index
                    game.navigationManager.navigate(GameresScreen::class.java.name, PlanesScreen::class.java.name)
                }
            }
        }

        addActor(back)
        back.apply {
            setBounds(1188f, 0f, 185f, 141f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }
    }

}