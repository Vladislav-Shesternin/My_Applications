package com.duckduckmoosedesign.cpk.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.duckduckmoosedesign.cpk.game.LibGDXGame
import com.duckduckmoosedesign.cpk.game.actors.AButton
import com.duckduckmoosedesign.cpk.game.utils.TIME_ANIM
import com.duckduckmoosedesign.cpk.game.utils.actor.animHide
import com.duckduckmoosedesign.cpk.game.utils.actor.animShow
import com.duckduckmoosedesign.cpk.game.utils.advanced.AdvancedScreen
import com.duckduckmoosedesign.cpk.game.utils.advanced.AdvancedStage
import com.duckduckmoosedesign.cpk.game.utils.region

class ManualScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val manual = Image(game.Ser.MNL)
    private val back   = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.Mis.Firster.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(manual.apply { setBounds(150f, 141f, 981f, 676f) })

        addActor(back)
        back.apply {
            setBounds(1188f, 43f, 185f, 141f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }
    }

}