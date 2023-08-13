package com.veldan.lbjt.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.lbjt.game.LibGDXGame
import com.veldan.lbjt.game.box2d.bodiesGroup.BGBorders
import com.veldan.lbjt.game.box2d.disposeAll
import com.veldan.lbjt.game.utils.TIME_ANIM_ALPHA
import com.veldan.lbjt.game.utils.actor.animHide
import com.veldan.lbjt.game.utils.actor.animShow
import com.veldan.lbjt.game.utils.advanced.AdvancedMouseScreen
import com.veldan.lbjt.game.utils.advanced.AdvancedStage

class AboutAuthorScreen(override val game: LibGDXGame): AdvancedMouseScreen() {

    // Actor
    private val aImg = Image(drawerUtil.getRegion(Color.valueOf("16ABBF")))

    // BodyGroup
    private val bgBorders     = BGBorders(this )

    // Fields
//    private val fileHandlerMusic       = Gdx.files.local(LOCAL_MUSIC_DIR)

    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.themeUtil.trc.BACKGROUND)
        super.show()
        stageUI.root.animShow(TIME_ANIM_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createBG_Borders {
            addImg()
        }
    }


    override fun dispose() {
        listOf(bgBorders).disposeAll()
        super.dispose()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addImg() {
        addActor(aImg)
        aImg.apply {
            setBounds(207f, 632f, 286f, 135f)
        }
    }

    // ------------------------------------------------------------------------
    // Create Body Group
    // ------------------------------------------------------------------------
    private fun createBG_Borders(block: () -> Unit = {}) {
        bgBorders.requestToCreate(Vector2(0f, 0f), Vector2(700f, 1400f), block)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

}