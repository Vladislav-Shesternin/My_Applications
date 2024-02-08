package com.sca.rab.que.stgame.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.sca.rab.que.stgame.game.utils.actor.disable
import com.sca.rab.que.stgame.game.utils.actor.setOnClickListener
import com.sca.rab.que.stgame.game.utils.advanced.AdvancedGroup
import com.sca.rab.que.stgame.game.utils.advanced.AdvancedScreen

class AResultGroup(override val screen: AdvancedScreen): AdvancedGroup() {

    private val result = Image()

    override fun addActorsOnGroup() {
        disable()
        color.a = 0f

        addAndFillActor(Image(screen.drawerUtil.getRegion(Color.valueOf("653600").apply { a = 0.56f })))
        addResult()
        addMenuBtn()
        addBtns()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun addMenuBtn() {
        val back = Actor()
        addActor(back)
        back.apply {
            setBounds(853f, 1153f, 171f, 175f)
            setOnClickListener(screen.game.soundUtil) { screen.animHideScreen { screen.game.navigationManager.back() } }
        }
    }

    private fun addResult() {
        addActor(result)
        result.setBounds(67f, 306f, 957f, 1274f)
    }

    private fun addBtns() {
        var ny = 951f
//        LoaLevelScreen.LEVEL.values().onEach { level ->
//            val actor = Actor()
//            addActor(actor)
//            actor.setBounds(183f, ny, 714f, 120f)
//            ny -= 87f + 120f
//            actor.setOnClickListener(screen.game.soundUtil) {
//                LoaLevelScreen.Level = level
//                screen.animHideScreen { screen.game.navigationManager.navigate(LoaPuzzleScreen::class.java.name) }
//            }
//        }
    }

    fun showResult(isWin: Boolean) {
        //result.drawable = TextureRegionDrawable(if (isWin) screen.game.gameAssets.LOA_WIN else screen.game.gameAssets.LOA_LOSE)
    }

}