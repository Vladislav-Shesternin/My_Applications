package mst.mysteryof.antientegyptua.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import mst.mysteryof.antientegyptua.game.LibGDXGame
import mst.mysteryof.antientegyptua.game.actors.button.AButton
import mst.mysteryof.antientegyptua.game.actors.slots.slot3x3.SlotGroup
import mst.mysteryof.antientegyptua.game.utils.GameColor
import mst.mysteryof.antientegyptua.game.utils.TIME_ANIM_SCREEN_ALPHA
import mst.mysteryof.antientegyptua.game.utils.actor.animHide
import mst.mysteryof.antientegyptua.game.utils.actor.animShow
import mst.mysteryof.antientegyptua.game.utils.advanced.AdvancedScreen
import mst.mysteryof.antientegyptua.game.utils.advanced.AdvancedStage
import mst.mysteryof.antientegyptua.game.utils.font.FontParameter
import mst.mysteryof.antientegyptua.game.utils.region
import mst.mysteryof.antientegyptua.game.utils.runGDX

class GameScreen(override val game: LibGDXGame): AdvancedScreen() {

    companion object {
        val secretKey = "MySecretKey12345"
        val naulekMazurbek = "gx0o+ODfb5q8V71fbJmvuA=="
    }

    private val parameter = FontParameter()
    private val font66    = fontGeneratorKaushanScript_Regular.generateFont(parameter.setCharacters(FontParameter.CharType.NUMBERS).setSize(66))

    // Actor
    private val btnSpin    = AButton(this, AButton.Type.SN)
    private val btnMenu    = AButton(this, AButton.Type.MN)
    private val slotGroup  = SlotGroup(this)
    private val imgCounter = Image(game.spriteUtil.COUNTER)
    private val label      = Label("", Label.LabelStyle(font66, GameColor.textLight))

    // Field
    private val numFlow = MutableStateFlow(1000)

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtilSplash.BACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)

    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addCounter()
        addSpin()
        addMenu()
        addSlotGroup()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addCounter() {
        addActors(imgCounter, label)
        imgCounter.setBounds(999f, 682f, 406f, 97f)
        label.apply {
            setBounds(999f, 682f, 406f, 97f)
            setAlignment(Align.center)
        }

        coroutine?.launch {
            numFlow.collect { num ->
                runGDX { label.setText(num) }
            }
        }

    }

    private fun AdvancedStage.addSpin() {
        addActor(btnSpin)
        btnSpin.apply {
            setBounds(1098f, 349f, 208f, 208f)
            setOnClickListener { spinHandler() }
        }
    }

    private fun AdvancedStage.addMenu() {
        addActor(btnMenu)
        btnMenu.apply {
            setBounds(1066f, 141f, 272f, 68f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() } }
        }
    }

    private fun AdvancedStage.addSlotGroup() {
        addActor(slotGroup)
        slotGroup.setBounds(28f, 51f, 919f, 803f)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun AButton.spinHandler() {
        disable()

        coroutine?.launch {
            numFlow.value -= 100
            numFlow.value += if (slotGroup.spin()) (100..300).random() else 0
            runGDX { enable() }
        }
    }

}