package mst.mysteryof.egyptest.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import mst.mysteryof.egyptest.game.LibGDXGame
import mst.mysteryof.egyptest.game.actors.button.AButton
import mst.mysteryof.egyptest.game.actors.slots.slot4x3.SlotGroup
import mst.mysteryof.egyptest.game.utils.TIME_ANIM_SCREEN_ALPHA
import mst.mysteryof.egyptest.game.utils.actor.animHide
import mst.mysteryof.egyptest.game.utils.actor.animShow
import mst.mysteryof.egyptest.game.utils.advanced.AdvancedScreen
import mst.mysteryof.egyptest.game.utils.advanced.AdvancedStage
import mst.mysteryof.egyptest.game.utils.font.FontParameter
import mst.mysteryof.egyptest.game.utils.region
import mst.mysteryof.egyptest.game.utils.runGDX

class GameScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val parameter = FontParameter()
    private val font55    = fontGeneratorKaushanScript_Regular.generateFont(parameter.setCharacters(FontParameter.CharType.NUMBERS.chars+"-").setSize(50))

    // Actor
    private val btnSpin    = AButton(this, AButton.Type.SN)
    private val slotGroup  = SlotGroup(this)
    private val numImg     = Image(game.spriteUtil.BALACA)
    private val balanceLbl = Label("", Label.LabelStyle(font55, Color.WHITE))
    private val numLbl     = Label("", Label.LabelStyle(font55, Color.WHITE))
    private val btnPlus    = AButton(this, AButton.Type.PLUS)
    private val btnMnus    = AButton(this, AButton.Type.MNUS)


    // Field
    private val numList     = listOf(100, 250, 500, 750, 1000)
    private val numFlow     = MutableStateFlow(numList.first())
    private val balanceFlow = MutableStateFlow(1000)

    private var numIndex = 0

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtilSplash.BACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)

    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addNum()
        addSpin()
        addSlotGroup()
        addBalance()
        addPlMn()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addNum() {
        addActors(numImg, numLbl)
        numImg.setBounds(307f, 570f, 466f, 216f)
        numLbl.apply {
            setBounds(338f, 645f, 403f, 66f)
            setAlignment(Align.center)
        }
        coroutine?.launch { numFlow.collect { num -> runGDX { numLbl.setText(num) } } }
    }

    private fun AdvancedStage.addBalance() {
        addActor(balanceLbl)
        balanceLbl.apply {
            setBounds(338f, 1448f, 403f, 66f)
            setAlignment(Align.center)
        }
        coroutine?.launch { balanceFlow.collect { runGDX { balanceLbl.setText(it) } } }
    }

    private fun AdvancedStage.addSpin() {
        addActor(btnSpin)
        btnSpin.apply {
            setBounds(245f, 267f, 637f, 176f)
            setOnClickListener { spinHandler() }
        }
    }

    private fun AdvancedStage.addPlMn() {
        addActors(btnPlus, btnMnus)
        btnPlus.apply {
            setBounds(734f, 586f, 185f, 185f)
            setOnClickListener { if ((numIndex+1) <= numList.lastIndex) numHandler(++numIndex) }
        }
        btnMnus.apply {
            setBounds(158f, 586f, 185f, 185f)
            setOnClickListener { if ((numIndex-1) >= 0) numHandler(--numIndex) }
        }
    }

    private fun AdvancedStage.addSlotGroup() {
        addActor(slotGroup)
        slotGroup.setBounds(85f,874f,910f,646f)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun numHandler(numIndex: Int) {
        numFlow.value = numList[numIndex]
    }

    private fun AButton.spinHandler() {
        disable()
        btnPlus.disable()
        btnMnus.disable()

        coroutine?.launch {
            balanceFlow.value -= numFlow.value
            if (slotGroup.spin()) balanceFlow.value += (numFlow.value * 2)
            runGDX {
                enable()
                btnPlus.enable()
                btnMnus.enable()
            }
        }
    }

}