package com.orange.magic.board.doodle.color.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.orange.magic.board.doodle.color.game.LidaGame
import com.orange.magic.board.doodle.color.game.actors.button.AButton
import com.orange.magic.board.doodle.color.game.utils.TIMI_TERNER
import com.orange.magic.board.doodle.color.game.utils.actor.animHide
import com.orange.magic.board.doodle.color.game.utils.actor.animShow
import com.orange.magic.board.doodle.color.game.utils.advanced.AdvancedScreen
import com.orange.magic.board.doodle.color.game.utils.advanced.AdvancedStage
import com.orange.magic.board.doodle.color.game.utils.font.FontParameter
import com.orange.magic.board.doodle.color.game.utils.region

class WeRcordeScreen(override val game: LidaGame): AdvancedScreen() {

    companion object {
        val listItemCounter = MutableList<Int>(6) { 0 }
    }

    private val par = FontParameter().setCharacters(FontParameter.CharType.NUMBERS).setSize(76)
    private val fonito = fontGenerator_K2D.generateFont(par)

    // Actor
    private val aMenu = AButton(
        this,
        AButton.Static.Type.MNU
    )
    private val listLbl = List(6) { Label(listItemCounter[it].toString(), Label.LabelStyle(fonito, Color.WHITE)) }

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.dgop.meduna.region)
        super.show()
        stageUI.root.animShow(TIMI_TERNER)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addMenu()

        var nyImg = 1365f
        game.simagoche.irems.onEachIndexed { index, it ->
            addActor(Image(it).apply { setBounds(146f, nyImg, 192f, 192f) })

            addActor(listLbl[index].apply {
                setBounds(392f, nyImg+42f, 395f, 99f)
                setAlignment(Align.center)
            })

            nyImg -= 63+192

        }
    }

    // ------------------------------------------------------------------------
    // Create Add Actor
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addMenu() {
        addActors(aMenu)

        aMenu.apply {
            setBounds(34f, 1773f, 112f, 112f)
            setOnClickListener {
                stageUI.root.animHide(TIMI_TERNER) { game.navigationManager.back() }
            }
        }
    }

}