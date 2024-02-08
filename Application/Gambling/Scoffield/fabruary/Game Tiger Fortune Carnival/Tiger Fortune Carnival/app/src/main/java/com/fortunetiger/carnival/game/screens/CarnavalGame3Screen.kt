package com.fortunetiger.carnival.game.screens

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.fortunetiger.carnival.game.LibGDXGame
import com.fortunetiger.carnival.game.actors.ASharPanel
import com.fortunetiger.carnival.game.actors.AResultGroup
import com.fortunetiger.carnival.game.actors.AShar
import com.fortunetiger.carnival.game.actors.ATimerGroup
import com.fortunetiger.carnival.game.actors.button.AButton
import com.fortunetiger.carnival.game.actors.checkbox.ACheckBox
import com.fortunetiger.carnival.game.box2d.AbstractBody
import com.fortunetiger.carnival.game.box2d.BodyId
import com.fortunetiger.carnival.game.box2d.WorldUtil
import com.fortunetiger.carnival.game.box2d.bodies.BShar
import com.fortunetiger.carnival.game.utils.HEIGHT_BOX2D
import com.fortunetiger.carnival.game.utils.TIME_ANIM
import com.fortunetiger.carnival.game.utils.actor.animHide
import com.fortunetiger.carnival.game.utils.actor.animShow
import com.fortunetiger.carnival.game.utils.actor.setOnClickListener
import com.fortunetiger.carnival.game.utils.advanced.AdvancedBox2dScreen
import com.fortunetiger.carnival.game.utils.advanced.AdvancedStage
import com.fortunetiger.carnival.game.utils.region
import com.fortunetiger.carnival.game.utils.runGDX
import com.fortunetiger.carnival.game.utils.toB2
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class CarnavalGame3Screen(override val game: LibGDXGame) : AdvancedBox2dScreen(WorldUtil()) {

    companion object {
        const val COUNT_SHAR = 10
    }

    private val assets = game.allAssets

    // Actor
    private val aTimer         = ATimerGroup(this)
    private val aItemPanelList = List(3) { ASharPanel(this) }
    private val aResult        = AResultGroup(this).apply { color.a = 0f }
    private val aPnl           = Image(assets.pnl)

    // Field
    private val randomIndexList  = (0..assets.shars.lastIndex).shuffled().take(3)
    private val sharFlow = MutableSharedFlow<BShar>(replay = COUNT_SHAR)

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.allAssets.GAMNAVAL.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(aPnl)
        aPnl.setBounds(382f, 1683f, 353f, 132f)

        addBack()
        addPause()
        addItemPanelList()
        addTimer()

        createB_Shars()

        addAndFillActor(aResult)

        aTimer.startTimer {
            aTimer.isPause = true
            isWorldPause   = true

            aResult.update(false)
            aResult.animShow(TIME_ANIM)
            aResult.touchable = Touchable.enabled
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addBack() {
        val menu = AButton(this@CarnavalGame3Screen, AButton.Static.Type.HOME)
        addActor(menu)
        menu.setBounds(56f, 1677f, 159f, 159f)

        menu.setOnClickListener(game.soundUtil) {
            stageUI.root.animHide(TIME_ANIM) { game.navigationManager.back() }
        }
    }

    private fun AdvancedStage.addPause() {
        val pause = ACheckBox(this@CarnavalGame3Screen, ACheckBox.Static.Type.PAUSE)
        addActor(pause)
        pause.setBounds(880f, 1677f, 159f, 159f)

        pause.setOnCheckListener { isCheck ->
            aTimer.isPause = isCheck
            isWorldPause   = isCheck
        }
    }

    private fun AdvancedStage.addTimer() {
        addActor(aTimer)
        aTimer.setBounds(389f, 1687f, 338f, 120f)
    }

    private fun AdvancedStage.addItemPanelList() {
        val panel = Image(game.allAssets.i3)
        addActor(panel)
        panel.setBounds(262f, 1574f, 562f, 87f)

        var nx = 288f

        val randomCountList = List<Int>(3) { (2..3).random() }

        aItemPanelList.onEachIndexed { index, aItemPanel ->
            addActor(aItemPanel)
            aItemPanel.setBounds(nx, 1587f, 122f, 60f)
            nx += 82f + 122f

            aItemPanel.update(randomCountList[index], assets.shars[randomIndexList[index]])
        }
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Shars() {
        repeat(COUNT_SHAR) {
            BShar(this).also { bItem ->

                bItem.renderBlockArray.add(AbstractBody.RenderBlock {
                    bItem.body?.let {
                        if (it.position.y >= HEIGHT_BOX2D) {
                            if (bItem.atomicBoolean.getAndSet(false)) {
                                sharFlow.tryEmit(bItem)
                            }
                        }
                    }
                })

                val itemId1 = BodyId.Game.items[randomIndexList[0]]
                val itemId2 = BodyId.Game.items[randomIndexList[1]]
                val itemId3 = BodyId.Game.items[randomIndexList[2]]

                (bItem.actor as AShar).apply {
                    boomBlock = {
                        game.soundUtil.apply { play(touch) }

                        when(bItem.id) {
                            itemId1 -> {
                                if (bItem.atomicBoolean.getAndSet(false)) {
                                    sharFlow.tryEmit(bItem)
                                    aItemPanelList[0].boom()
                                    checkAllSharIsBoom()

                                    game.soundUtil.apply { play(bass_drop) }
                                }
                            }
                            itemId2 -> {
                                if (bItem.atomicBoolean.getAndSet(false)) {
                                    sharFlow.tryEmit(bItem)
                                    aItemPanelList[1].boom()
                                    checkAllSharIsBoom()

                                    game.soundUtil.apply { play(bass_drop) }
                                }
                            }
                            itemId3 -> {
                                if (bItem.atomicBoolean.getAndSet(false)) {
                                    sharFlow.tryEmit(bItem)
                                    aItemPanelList[2].boom()
                                    checkAllSharIsBoom()

                                    game.soundUtil.apply { play(bass_drop) }
                                }
                            }
                        }
                    }
                }

                bItem.bodyDef.fixedRotation = true
                bItem.bodyDef.gravityScale  = 0f
                bItem.create(-300f, -400f, 218f, 313f)

                sharFlow.tryEmit(bItem)
            }
        }

        coroutine?.launch {
            sharFlow.collect { bItem ->
                delay(300)
                runGDX {
                    bItem.body?.apply {
                        bItem.setNoneId()

                        setLinearVelocity(0f, 0f)
                        gravityScale = 0f
                        isAwake = false

                        setTransform(Vector2(-300f, -400f).toB2, 0f)
                    }
                }
            }
        }
        coroutine?.launch {
            sharFlow.collect { bItem ->
                delay((500L..1000L).random())
                runGDX {
                    bItem.updateImage()
                    bItem.body?.apply {
                        setTransform((0..808).random().toFloat().toB2, (-400f).toB2, 0f)

                        gravityScale = 1f
                        isAwake      = true
                    }
                }
                delay(100)
                runGDX {
                    bItem.updateId()
                    bItem.atomicBoolean.set(true)
                }
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun checkAllSharIsBoom() {
        if (aItemPanelList.all { it.counter == 0 }) {
            aTimer.isPause = true
            isWorldPause   = true

            aResult.update(true)
            aResult.animShow(TIME_ANIM)
            aResult.touchable = Touchable.enabled
        }
    }

}