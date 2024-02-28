package com.fortunetiger.bigwin.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.fortunetiger.bigwin.game.LibGDXGame
import com.fortunetiger.bigwin.game.actors.button.AButton
import com.fortunetiger.bigwin.game.actors.slots.slot6x5.SlotGroup
import com.fortunetiger.bigwin.game.utils.advanced.AdvancedScreen
import com.fortunetiger.bigwin.game.utils.advanced.AdvancedStage
import com.fortunetiger.bigwin.game.utils.font.FontParameter
import com.fortunetiger.bigwin.game.utils.region
import com.fortunetiger.bigwin.game.utils.runGDX
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FTWSlotScreen(override val game: LibGDXGame): AdvancedScreen() {

    companion object {
        var BALANCE = 500
    }

    private val parameter = FontParameter().setCharacters(FontParameter.CharType.ALL)
    private val font64    = fontGenerator_JuaRegular.generateFont(parameter.setSize(64))

    private val STAKE_STEP = 10
    private val STAKE_MAX  = 100
    private val STAKE_MIN  = 10

    private val balanceFlow = MutableStateFlow(BALANCE)
    private val stakeFlow   = MutableStateFlow(STAKE_MIN)

    // Actor
    private val panelImg   = Image(game.allAssets.slot_group)
    private val menuBtn    = AButton(this, AButton.Static.Type.BACK)
    private val bottomImg  = Image(drawerUtil.getRegion(Color.valueOf("FFC700").apply { a = 0.67f }))
    private val bidImg     = Image(game.allAssets.bid)
    private val creditImg  = Image(game.allAssets.credit)
    private val balanceLbl = Label(BALANCE.toString(), Label.LabelStyle(font64, Color.WHITE))
    private val stakeLbl   = Label("" + stakeFlow.value, Label.LabelStyle(font64, Color.WHITE))
    private val minusBtn   = AButton(this, AButton.Static.Type.MINUS)
    private val plusBtn    = AButton(this, AButton.Static.Type.PLUS)
    private val spinBtn    = AButton(this, AButton.Static.Type.SPIN)
    private val slotGroup  = SlotGroup(this)

    private val listClickable = listOf(spinBtn, minusBtn, plusBtn)


    override fun show() {
        //stageUI.root.animHide()
        setBackBackground(game.loaderAssets.FTWBackground.region)
        super.show()
        //stageUI.root.animShow(TIME_ANIM_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addPanelImg()
        addBackBtn()
        addBottomImg()
        addCreditBidImg()
        addBalanceAndStake()
        addMinusPlusBtn()
        addSpinBtn()
        addSlotGroup()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addPanelImg() {
        addActor(panelImg)
        panelImg. setBounds(180f, 233f, 1515f, 781f)
    }

    private fun AdvancedStage.addBackBtn() {
        addActor(menuBtn)
        menuBtn.setBounds(1750f, 910f, 145f, 145f)
        menuBtn.setOnClickListener { game.navigationManager.back() }
    }

    private fun AdvancedStage.addBottomImg() {
        addActor(bottomImg)
        bottomImg.setBounds(0f, 0f, 1920f, 145f)
    }

    private fun AdvancedStage.addCreditBidImg() {
        addActors(creditImg, bidImg)
        creditImg.setBounds(180f, 27f, 451f, 80f)
        bidImg.setBounds(1351f, 31f, 353f, 80f)
    }

    private fun AdvancedStage.addBalanceAndStake() {
        addActors(balanceLbl, stakeLbl)
        balanceLbl.setBounds(385f, 26f, 172f, 80f)
        stakeLbl.setBounds(1460f, 31f, 172f, 80f)
        balanceLbl.setAlignment(Align.center)
        stakeLbl.setAlignment(Align.center)

        coroutine?.launch {
            launch {
                balanceFlow.collect { balance ->
                    BALANCE = balance
                    runGDX { balanceLbl.setText("$balance") }
                }
            }
            launch {
                stakeFlow.collect { stake -> runGDX { stakeLbl.setText("$stake") } }
            }
        }
    }

    private fun AdvancedStage.addMinusPlusBtn() {
        addActors(minusBtn, plusBtn)
        minusBtn.apply {
            setBounds(1076f, 6f, 130f, 130f)
            setOnClickListener {
                if (stakeFlow.value - STAKE_STEP >= STAKE_MIN) stakeFlow.value -= STAKE_STEP
            }
        }
        plusBtn.apply {
            setBounds(715f, 10f, 130f, 130f)
            setOnClickListener {
                if (stakeFlow.value + STAKE_STEP <= STAKE_MAX) stakeFlow.value += STAKE_STEP
            }
        }
    }

    private fun AdvancedStage.addSlotGroup() {
        addActor(slotGroup)
        slotGroup.setBounds(263f, 269f, 1350f, 706f)
    }

    private fun AdvancedStage.addSpinBtn() {
        addActor(spinBtn)
        spinBtn.apply {
            setBounds(857f, 0f, 207f, 207f)

            setOnClickListener {
                balanceFlow.value -= stakeFlow.value

                listClickable.onEach { it.disable() }

                coroutine?.launch {
                    val isWin = slotGroup.spin()
                    if (isWin) balanceFlow.value += (stakeFlow.value * (1..10).random())

                    listClickable.onEach { it.enable() }
                }
            }
        }
    }

}