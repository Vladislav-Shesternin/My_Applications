package com.YovoGames.magicBo.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.YovoGames.magicBo.game.LibGDXGame
import com.YovoGames.magicBo.game.actors.AButton
import com.YovoGames.magicBo.game.utils.TIME_ANIM
import com.YovoGames.magicBo.game.utils.WIDTH_UI
import com.YovoGames.magicBo.game.utils.actor.animHide
import com.YovoGames.magicBo.game.utils.actor.animShow
import com.YovoGames.magicBo.game.utils.actor.setOnClickListener
import com.YovoGames.magicBo.game.utils.advanced.AdvancedScreen
import com.YovoGames.magicBo.game.utils.advanced.AdvancedStage
import com.YovoGames.magicBo.game.utils.region
import com.badlogic.gdx.scenes.scene2d.Actor

class RulesScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val rules = Image(game.jakarta.rlese)
    private val back  = Actor()

    override fun show() {
        stageUI.root.x = -WIDTH_UI
        setBackBackground(game.surgut.Soloha.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(rules)
        rules.setBounds(60f, 135f, 420f, 691f)

        addActor(back)
        back.apply {
            setBounds(159f, 167f, 222f, 68f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

    }

}