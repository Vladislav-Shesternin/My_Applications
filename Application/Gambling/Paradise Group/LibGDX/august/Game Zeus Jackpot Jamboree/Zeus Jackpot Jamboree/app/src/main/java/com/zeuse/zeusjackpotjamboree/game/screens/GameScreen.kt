package com.zeuse.zeusjackpotjamboree.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.zeuse.zeusjackpotjamboree.game.LibGDXGame
import com.zeuse.zeusjackpotjamboree.game.actors.label.SpinningLabel
import com.zeuse.zeusjackpotjamboree.game.box2d.BodyId
import com.zeuse.zeusjackpotjamboree.game.box2d.WorldUtil
import com.zeuse.zeusjackpotjamboree.game.box2d.bodies.BDynamic
import com.zeuse.zeusjackpotjamboree.game.box2d.bodies.BItem
import com.zeuse.zeusjackpotjamboree.game.box2d.bodies.BZeus
import com.zeuse.zeusjackpotjamboree.game.box2d.bodiesGroup.BGBorders
import com.zeuse.zeusjackpotjamboree.game.box2d.disposeAll
import com.zeuse.zeusjackpotjamboree.game.utils.TIME_ANIM_ALPHA
import com.zeuse.zeusjackpotjamboree.game.utils.actor.animHide
import com.zeuse.zeusjackpotjamboree.game.utils.actor.animShow
import com.zeuse.zeusjackpotjamboree.game.utils.ControllerMouseUtil
import com.zeuse.zeusjackpotjamboree.game.utils.advanced.AdvancedBox2dScreen
import com.zeuse.zeusjackpotjamboree.game.utils.advanced.AdvancedScreen
import com.zeuse.zeusjackpotjamboree.game.utils.advanced.AdvancedStage
import com.zeuse.zeusjackpotjamboree.game.utils.runGDX
import com.zeuse.zeusjackpotjamboree.game.utils.toB2
import com.zeuse.zeusjackpotjamboree.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

class GameScreen(override val game: LibGDXGame): AdvancedBox2dScreen(WorldUtil()) {

    // Actor
    private val aScoreImg = Image(game.themeUtil.trc.SCORE)
    private val aScoreLbl = SpinningLabel(this, "0", Label.LabelStyle(game.fontTTFUtil.fontInterBlack.font_68.font, Color.WHITE))

    // Body
    private val bZeus: BZeus = BZeus(this)
    private val bItem_List   = List(10) { BItem(this) }

    // BodyGroup
    private val bgBorders = BGBorders(this)

    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.themeUtil.trc.BACKGROUND)
        super.show()
        stageUI.root.animShow(TIME_ANIM_ALPHA)
    }

    // Fields
    private val controllerMouseUtil = ControllerMouseUtil(this, bZeus)
    private val bItemFlow           = MutableSharedFlow<BItem>(replay = 10)
    private val scoreFlow           = MutableStateFlow(0)

    override fun AdvancedStage.addActorsOnStageUI() {
        createBG_Borders()
        createB_Zeus {
            createB_Items()
            controllerMouseUtil.initialize { addScore() }
        }
    }


    override fun dispose() {
        listOf(bgBorders).disposeAll()
        super.dispose()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addScore() {
        addActors(aScoreImg, aScoreLbl)
        aScoreImg.setBounds(1450f, 441f, 321f, 346f)
        aScoreLbl.setBounds(1524f, 599f, 188f, 78f)

        coroutine?.launch { scoreFlow.collect { score -> runGDX { aScoreLbl.setText(score.toString()) } } }
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Zeus(block: () -> Unit = {}) {
        bZeus.apply {
            id = BodyId.Game.ZEUS
            collisionList.addAll(arrayOf(BodyId.BORDERS, BodyId.Game.ITEM))

            val isContact = AtomicBoolean(true)
            beginContactBlock = { bd -> if (bd.id == BodyId.Game.ITEM) { if (isContact.getAndSet(false)) scoreFlow.value += (bd as? BItem)?.score ?: 0 } }
            endContactBlock = { isContact.set(true) }
        }
        bZeus.requestToCreate(Vector2(756f, 227f), Vector2(259f, 332f), block)
    }

    private fun createB_Items() {
        bItem_List.onEach {
            it.apply {
                id = BodyId.Game.ITEM
                collisionList.addAll(arrayOf(BodyId.Game.ZEUS, BodyId.Game.ITEM))
            }
            it.requestToCreate(Vector2(0f, 800f), Vector2(110f, 110f)) { bItemFlow.tryEmit(it) }

            var timer = 0f

            it.renderBlock = { time ->
                timer += time
                if (timer >= 0.5) {
                    timer = 0f
                    it.body?.let { body -> if (body.position.y <= 0) { if (it.isTrue.getAndSet(false)) {
                        log("restart")
                        bItemFlow.tryEmit(it)
                    } } }
                }
            }
        }

        coroutine?.launch {
            bItemFlow.collect { item ->
                runGDX { item.apply {
                    freeze()
                    updateItem()
                    body?.setTransform(Vector2((55..1662).random().toFloat(),800f).toB2, 0f)
                    unfreeze()
                } }
                delay(400)
                item.isTrue.set(true)
            }
        }

    }

    // ------------------------------------------------------------------------
    // Create Body Group
    // ------------------------------------------------------------------------
    private fun createBG_Borders(block: () -> Unit = {}) {
        bgBorders.requestToCreate(Vector2(0f, 0f), Vector2(1772f, 787f), block)
    }

    // ---------------------------------------------------
    // Anim
    // ---------------------------------------------------

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun navigateTo(screen: AdvancedScreen) {
        stageUI.root.animHide(TIME_ANIM_ALPHA) { game.navigationManager.navigate(screen, GameScreen(game)) }
    }

}