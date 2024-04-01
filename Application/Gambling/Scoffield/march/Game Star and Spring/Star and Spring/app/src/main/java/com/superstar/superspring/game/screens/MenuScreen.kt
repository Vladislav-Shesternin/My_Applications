package com.superstar.superspring.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.superstar.superspring.game.LibGDXGame
import com.superstar.superspring.game.box2d.WorldUtil
import com.superstar.superspring.game.utils.TIME_ANIM
import com.superstar.superspring.game.utils.actor.animHide
import com.superstar.superspring.game.utils.actor.animShow
import com.superstar.superspring.game.utils.actor.setOnClickListener
import com.superstar.superspring.game.utils.advanced.AdvancedBox2dScreen
import com.superstar.superspring.game.utils.advanced.AdvancedStage
import com.superstar.superspring.game.utils.font.FontParameter
import com.superstar.superspring.game.utils.region

class MenuScreen(override val game: LibGDXGame): AdvancedBox2dScreen(WorldUtil()) {

    companion object {
        var MAXIMUM = 0
    }

    private val params = FontParameter().setCharacters(FontParameter.CharType.ALL).setSize(87)
    private val font   = fontGenerator_Baloo.generateFont(params)

    // Actor
    private val label = Label("Maximum: $MAXIMUM", Label.LabelStyle(font, Color.valueOf("FCC715")))

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.loaderAssets.BACKGROUND.region, game.allAssets.MENU_B.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addLabel()

        val play = Actor()
        addActor(play)
        play.apply {
            setBounds(342f, 391f, 395f, 395f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(GameScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
        }
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedStage.addLabel() {
        addActor(label)
        label.setBounds(276f, 992f, 524f, 91f)
        label.setAlignment(Align.center)
    }

}