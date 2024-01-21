package com.cosmo.plinko.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.cosmo.plinko.game.utils.advanced.AdvancedGroup
import com.cosmo.plinko.game.utils.advanced.AdvancedScreen
import com.cosmo.plinko.game.utils.font.FontParameter

class AWinScore(override val screen: AdvancedScreen): AdvancedGroup() {

    private val parameter = FontParameter()

    private val score = Label("", Label.LabelStyle(screen.fontGeneratorMachineGunk.generateFont(parameter.setCharacters(FontParameter.CharType.NUMBERS).setSize(105)), Color.WHITE))

    val restart = Actor()
    val next    = Actor()

    override fun addActorsOnGroup() {
        addAndFillActor(Image(screen.game.gameAssets.RESULT_GOOD))
        addActor(score)
        score.apply {
            setBounds(247f, 156f, 369f, 99f)
            setAlignment(Align.center)
        }

        addActors(restart, next)
        restart.setBounds(42f, 0f, 358f, 105f)
        next.setBounds(467f, 0f, 358f, 105f)

    }

    fun updateScore(vl: Int) {
        score.setText(vl.toString())
    }

}