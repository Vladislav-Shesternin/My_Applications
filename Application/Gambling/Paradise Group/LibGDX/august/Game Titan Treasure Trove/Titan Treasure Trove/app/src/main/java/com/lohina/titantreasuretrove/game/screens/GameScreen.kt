package com.lohina.titantreasuretrove.game.screens

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.lohina.titantreasuretrove.game.LibGDXGame
import com.lohina.titantreasuretrove.game.actors.label.SpinningLabel
import com.lohina.titantreasuretrove.game.box2d.BodyId
import com.lohina.titantreasuretrove.game.box2d.WorldUtil
import com.lohina.titantreasuretrove.game.box2d.bodies.BItem
import com.lohina.titantreasuretrove.game.box2d.bodies.BStatic
import com.lohina.titantreasuretrove.game.utils.GameColor
import com.lohina.titantreasuretrove.game.utils.LightningUtil
import com.lohina.titantreasuretrove.game.utils.TIME_ANIM_ALPHA
import com.lohina.titantreasuretrove.game.utils.actor.animHide
import com.lohina.titantreasuretrove.game.utils.actor.animShow
import com.lohina.titantreasuretrove.game.utils.advanced.AdvancedBox2dScreen
import com.lohina.titantreasuretrove.game.utils.advanced.AdvancedStage
import com.lohina.titantreasuretrove.game.utils.runGDX
import com.lohina.titantreasuretrove.game.utils.toB2
import com.lohina.titantreasuretrove.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

class GameScreen(override val game: LibGDXGame): AdvancedBox2dScreen(WorldUtil()) {

    // Actor
    private val aZefsImg  = Image(game.spriteUtil.SPLASH.ZEFS)
    private val aScoreImg = Image(game.spriteUtil.GAME.PANEL)
    private val aScoreLbl = SpinningLabel(this, "0", Label.LabelStyle(game.fontTTFUtil.fontInterBlack.font_30.font, GameColor.pirple))

    // Body
    private val bZefs      = BStatic(this)
    private val bItem_List = List(10) { BItem(this) }

    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.spriteUtil.SPLASH.BACKGROUND)
        super.show()
        stageUI.root.animShow(TIME_ANIM_ALPHA)
    }

    // Fields
    private val lightningUtil = LightningUtil(this)
    private val bItemFlow     = MutableSharedFlow<BItem>(replay = 10)
    private val scoreFlow     = MutableStateFlow(0)

    override fun AdvancedStage.addActorsOnStageUI() {
        addScore()
        addZefs()

        createB_Zeus {
            createB_Items()
            lightningUtil.initialize()
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addScore() {
        addActors(aScoreImg, aScoreLbl)
        aScoreImg.setBounds(857f, 426f, 162f, 69f)
        aScoreLbl.setBounds(861f, 441f, 154f, 38f)

        coroutine?.launch { scoreFlow.collect { score -> runGDX { aScoreLbl.setText(score.toString()) } } }
    }

    private fun AdvancedStage.addZefs() {
        addActor(aZefsImg)
        aZefsImg.apply { setBounds(385f, 0f, 254f, 181f) }
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Zeus(block: () -> Unit = {}) {
        bZefs.apply {
            id = BodyId.Game.ZEUS
            collisionList.addAll(arrayOf(BodyId.Game.ITEM))

            val isContact = AtomicBoolean(true)
            beginContactBlock = { bd -> if (bd.id == BodyId.Game.ITEM) { if (isContact.getAndSet(false)) scoreFlow.value += (bd as? BItem)?.score ?: 0 } }
            endContactBlock = { isContact.set(true) }
        }
        bZefs.requestToCreate(Vector2(420f, 0f), Vector2(183f, 183f), block)
    }

    private fun createB_Items() {
        bItem_List.onEach {
            it.apply {
                id = BodyId.Game.ITEM
                collisionList.addAll(arrayOf(BodyId.Game.ZEUS))
                bodyDef.gravityScale = 0f
            }
            it.requestToCreate(Vector2(0f, 700f), Vector2(70f, 70f)) { bItemFlow.tryEmit(it) }

            var timer = 0f

            it.renderBlock = { time ->
                timer += time
                if (timer >= 0.5) {
                    timer = 0f
                    it.body?.let { body -> if (body.position.y <= 0) { if (it.isTrue.getAndSet(false)) { bItemFlow.tryEmit(it) } } }
                }
            }
        }

        coroutine?.launch {
            bItemFlow.collect { item ->
                runGDX { item.apply {
                    freeze()
                    updateItem()
                    body?.setTransform(Vector2((0..954).random().toFloat(),700f).toB2, 0f)
                    unfreeze()
                } }
                delay(400)
                item.isTrue.set(true)
            }
        }

    }

}