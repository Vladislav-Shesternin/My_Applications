package com.bottleber.lebeler.game.screens

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.bottleber.lebeler.game.LibGDXGame
import com.bottleber.lebeler.game.actors.checkbox.ACheckBox
import com.bottleber.lebeler.game.actors.checkbox.ACheckBoxGroup
import com.bottleber.lebeler.game.screens.bottler._10_BottlerScreen
import com.bottleber.lebeler.game.screens.bottler._11_BottlerScreen
import com.bottleber.lebeler.game.screens.bottler._12_BottlerScreen
import com.bottleber.lebeler.game.screens.bottler._1_BottlerScreen
import com.bottleber.lebeler.game.screens.bottler._2_BottlerScreen
import com.bottleber.lebeler.game.screens.bottler._3_BottlerScreen
import com.bottleber.lebeler.game.screens.bottler._4_BottlerScreen
import com.bottleber.lebeler.game.screens.bottler._5_BottlerScreen
import com.bottleber.lebeler.game.screens.bottler._6_BottlerScreen
import com.bottleber.lebeler.game.screens.bottler._7_BottlerScreen
import com.bottleber.lebeler.game.screens.bottler._8_BottlerScreen
import com.bottleber.lebeler.game.screens.bottler._9_BottlerScreen
import com.bottleber.lebeler.game.utils.TIME_ANIM
import com.bottleber.lebeler.game.utils.actor.animHide
import com.bottleber.lebeler.game.utils.actor.animShow
import com.bottleber.lebeler.game.utils.actor.setOnClickListener
import com.bottleber.lebeler.game.utils.advanced.AdvancedScreen
import com.bottleber.lebeler.game.utils.advanced.AdvancedStage
import com.bottleber.lebeler.game.utils.region
import com.bottleber.lebeler.util.log

class BotlerMenuScreen(override val game: LibGDXGame): AdvancedScreen() {

    companion object {
        var BOTTLE_ID = 0
            private set
    }

    private val menu  = Image(game.allAssets.miui)
    private val arrow = Image(game.allAssets.arrow)

    // Field
    private val bottleDataList = listOf(
        Static.BottleData(7, Vector2(584f, 893f)),
        Static.BottleData(8, Vector2(529f, 697f)),
        Static.BottleData(9, Vector2(474f, 513f)),
        Static.BottleData(10, Vector2(419f, 331f)),
    )

    private val levelList = listOf(
        _1_BottlerScreen::class.java.name,
        _2_BottlerScreen::class.java.name,
        _3_BottlerScreen::class.java.name,
        _4_BottlerScreen::class.java.name,
        _5_BottlerScreen::class.java.name,
        _6_BottlerScreen::class.java.name,
        _7_BottlerScreen::class.java.name,
        _8_BottlerScreen::class.java.name,
        _9_BottlerScreen::class.java.name,
        _10_BottlerScreen::class.java.name,
        _11_BottlerScreen::class.java.name,
        _12_BottlerScreen::class.java.name,
    )

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.loaderAssets.fonit.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addMiUi()
        addArrow()
        addCBoxList()
        addPlayBtn()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedStage.addMiUi() {
        addActor(menu)
        menu.setBounds(60f, 0f, 1424f, 1026f)
    }

    private fun AdvancedStage.addArrow() {
        addActor(arrow)
        arrow.setBounds(177f, 737f, 209f, 104f)
        arrow.setOrigin(21f, 64f)
        arrow.addAction(Actions.forever(Actions.sequence(
            Actions.delay(1f),
            Actions.rotateBy(35f, 1f, Interpolation.sineOut),
            Actions.rotateBy(-100f, 3f, Interpolation.sine),
            Actions.rotateBy(65f, 1.3f, Interpolation.sineIn)
        )))
    }

    private fun AdvancedStage.addCBoxList() {
        val cbg        = ACheckBoxGroup()
        var isFirst    = true
        var counterId = 0
        bottleDataList.onEach { data ->
            var nx = data.pos.x
            repeat(data.count) {
                addActor(ACheckBox(this@BotlerMenuScreen, ACheckBox.Static.Type.SHOT).apply {
                    checkBoxGroup = cbg
                    setBounds(nx, data.pos.y, 92f, 92f)
                    nx += 18+92

                    val id = counterId++

                    setOnCheckListener { isCheck ->
                        if (isCheck) BOTTLE_ID = id
                    }

                    if (isFirst) {
                        isFirst = false
                        check()
                    }
                })
            }
        }
    }

    private fun AdvancedStage.addPlayBtn() {
        val btn = Actor()
        addActor(btn)
        btn.apply {
            setBounds(810f, 49f, 302f, 121f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(if (BOTTLE_ID > 11) levelList.random() else levelList[BOTTLE_ID], BotlerMenuScreen::class.java.name)
                }
            }
        }
    }

    // ---------------------------------------------------
    // Class
    // ---------------------------------------------------

    object Static {
        data class BottleData(
            val count: Int,
            val pos  : Vector2,
        )
    }

}