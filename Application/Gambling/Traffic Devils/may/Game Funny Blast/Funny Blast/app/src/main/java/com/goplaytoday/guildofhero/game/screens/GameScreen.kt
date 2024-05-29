package com.goplaytoday.guildofhero.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.goplaytoday.guildofhero.game.LGDXGame
import com.goplaytoday.guildofhero.game.actors.button.AButton
import com.goplaytoday.guildofhero.game.actors.slots.slot6x5.SlotGroup
import com.goplaytoday.guildofhero.game.utils.TIME_ANIM_ALPHA
import com.goplaytoday.guildofhero.game.utils.actor.animHide
import com.goplaytoday.guildofhero.game.utils.actor.animShow
import com.goplaytoday.guildofhero.game.utils.advanced.AdvancedScreen
import com.goplaytoday.guildofhero.game.utils.advanced.AdvancedStage
import com.goplaytoday.guildofhero.game.utils.font.FontParameter
import com.goplaytoday.guildofhero.game.utils.region
import com.goplaytoday.guildofhero.game.utils.runGDX
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GameScreen(override val game: LGDXGame): AdvancedScreen() {

    private val parameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS).setSize(80)
    private val font80    = fontGeneratorJomhuria.generateFont(parameter)

    // Actor
    private val btnSpak    = AButton(this, AButton.Static.Type.Sparkle)
    private val solodko    = SlotGroup(this)
    private val itemImg    = Image(game.commonAssets.items.last())
    private val balanceLbl = Label("", Label.LabelStyle(font80, Color.WHITE))
    private val savkaLbl   = Label("", Label.LabelStyle(font80, Color.WHITE))
    private val btnPlus    = AButton(this, AButton.Static.Type.Pl)
    private val btnMnus    = AButton(this, AButton.Static.Type.Ms)
    private val btnMenu    = AButton(this, AButton.Static.Type.Menu)


    // Field
    private val numList     = listOf(100, 250, 500, 750, 1000)
    private val numFlow     = MutableStateFlow(numList.first())
    private val balanceFlow = MutableStateFlow(5000)

    private var numIndex = 0

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.loaderAssets.BACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(Image(game.commonAssets.barabans).apply {
            setBounds(47f, 226f, 1235f, 701f)
        })
        addSavka()
        addSparkle()
        addMegaGruba()
        addBalance()
        addPlMn()
        addMenu()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addSavka() {
        addActor(savkaLbl)
        savkaLbl.apply {
            setBounds(1045f, 70f, 130f, 80f)
            setAlignment(Align.center)
        }
        coroutine?.launch { numFlow.collect { num -> runGDX { savkaLbl.setText(num) } } }
    }

    private fun AdvancedStage.addBalance() {
        addActor(itemImg)
        itemImg.setBounds(93f, 41f, 110f, 110f)

        addActor(balanceLbl)
        balanceLbl.setBounds(238f, 55f, 130f, 60f)
        coroutine?.launch { balanceFlow.collect { runGDX { balanceLbl.setText(it) } } }
    }

    private fun AdvancedStage.addSparkle() {
        addActor(btnSpak)
        btnSpak.apply {
            setBounds(1383f, 469f, 216f, 216f)
            setOnClickListener { spakHandler() }
        }
    }

    private fun AdvancedStage.addPlMn() {
        addActors(btnPlus, btnMnus)
        btnPlus.apply {
            setBounds(938f, 64f, 72f, 95f)
            setOnClickListener { if ((numIndex+1) <= numList.lastIndex) numHandler(++numIndex) }
        }
        btnMnus.apply {
            setBounds(1210f, 64f, 72f, 95f)
            setOnClickListener { if ((numIndex-1) >= 0) numHandler(--numIndex) }
        }
    }

    private fun AdvancedStage.addMegaGruba() {
        addActor(solodko)
        solodko.setBounds(67f,249f,1195f,656f)
    }

    private fun AdvancedStage.addMenu() {
        addActor(btnMenu)
        btnMenu.apply {
            setBounds(1392f, 291f, 197f, 91f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_ALPHA) { game.navigationManager.back() } }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun numHandler(numIndex: Int) {
        numFlow.value = numList[numIndex]
    }

    private fun AButton.spakHandler() {
        this.disable()
        btnPlus.disable()
        btnMnus.disable()

        coroutine?.launch {
            balanceFlow.value -= numFlow.value
            if (solodko.spin()) balanceFlow.value += (numFlow.value * 2)
            runGDX {
                this@spakHandler.enable()
                btnPlus.enable()
                btnMnus.enable()
            }
        }
    }

}