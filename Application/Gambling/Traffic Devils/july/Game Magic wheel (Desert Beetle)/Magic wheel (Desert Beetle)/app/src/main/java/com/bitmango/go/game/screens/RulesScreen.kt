package com.bitmango.go.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.bitmango.go.game.LibGDXGame
import com.bitmango.go.game.actors.AButton
import com.bitmango.go.game.utils.TIME_ANIM
import com.bitmango.go.game.utils.actor.animHide
import com.bitmango.go.game.utils.actor.animShow
import com.bitmango.go.game.utils.advanced.AdvancedScreen
import com.bitmango.go.game.utils.advanced.AdvancedStage
import com.bitmango.go.game.utils.region

class RulesScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val rules = Image(game.bbb.qsde)
    private val back  = AButton(this, AButton.Static.Type.Back)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.aaa.back.random().region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(rules.apply { setBounds(30f, 256f, 479f, 568f) })

        addActor(back)
        back.apply {
            setBounds(176f, 90f, 188f, 78f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM) { game.navigationManager.back() } }
        }

    }

}