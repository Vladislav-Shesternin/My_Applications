package com.fotune.tiger.slotthighrino.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.fotune.tiger.slotthighrino.game.LibGDXGame
import com.fotune.tiger.slotthighrino.game.actors.slots.slot6x5.SlotGroup
import com.fotune.tiger.slotthighrino.game.utils.TIME_ANIM_ALPHA
import com.fotune.tiger.slotthighrino.game.utils.actor.animHide
import com.fotune.tiger.slotthighrino.game.utils.actor.animShow
import com.fotune.tiger.slotthighrino.game.utils.actor.setOnClickListener
import com.fotune.tiger.slotthighrino.game.utils.advanced.AdvancedScreen
import com.fotune.tiger.slotthighrino.game.utils.advanced.AdvancedStage
import com.fotune.tiger.slotthighrino.game.utils.font.FontParameter
import com.fotune.tiger.slotthighrino.game.utils.region
import com.fotune.tiger.slotthighrino.game.utils.runGDX
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GameScreen(override val game: LibGDXGame): AdvancedScreen() {

    companion object {
        var BALANCE = 100
    }

    private val parameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars + "$")
    private val font26    = fontGenerator_InterBlack.generateFont(parameter.setSize(26))
    private val font39    = fontGenerator_InterBlack.generateFont(parameter.setSize(39))

    private val STAKE_STEP = 5
    private val STAKE_MAX  = 20
    private val STAKE_MIN  = 5

    private val balanceFlow = MutableStateFlow(BALANCE)

    // Actor
    private val tigersImg  = Image(game.allAssets.tigers)
    private val sloyGImg   = Image(game.allAssets.slotG)
    private val balanceLbl = Label(BALANCE.toString(), Label.LabelStyle(font26, Color.valueOf("FECF2F")))
    private val winLbl     = Label("0", Label.LabelStyle(font39, Color.WHITE))
    private val slotGroup  = SlotGroup(this)
    private val rechagImg  = Image(game.allAssets.def)


    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.loaderAssets.main.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addNumImg()
        addSlotG()
        addBalanceAndStake()
        addRechagImg()
        addSlotGroup()
        addPanelImg()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addPanelImg() {
        addActor(tigersImg)
        tigersImg.setBounds(568f, 0f, 905f, 236f)
    }

    private fun AdvancedStage.addNumImg() {
        val num = Image(game.allAssets.numbers)
        addActor(num)
        num.setBounds(13f, 14f, 327f, 722f)
    }

    private fun AdvancedStage.addSlotG() {
        addActor(sloyGImg)
        sloyGImg.setBounds(284f, 72f, 1027f, 730f)
    }

    private fun AdvancedStage.addBalanceAndStake() {
        addActors(balanceLbl, winLbl)
        balanceLbl.setBounds(729f, 692f, 175f, 32f)
        winLbl.setBounds(150f, 39f, 178f, 48f)
        winLbl.setAlignment(Align.center)

        coroutine?.launch {
            launch {
                balanceFlow.collect { balance ->
                    BALANCE = balance
                    runGDX { balanceLbl.setText("$balance") }
                }
            }
        }
    }

    private fun AdvancedStage.addSlotGroup() {
        addActor(slotGroup)
        slotGroup.setBounds(411f, 140f, 781f, 517f)
    }

    private fun AdvancedStage.addRechagImg() {
        addActor(rechagImg)
        rechagImg.setBounds(1234f, 291f, 101f, 340f)

        var isDef = true
        rechagImg.setOnClickListener {
            if (isDef) {
                isDef = false
                rechagImg.drawable = TextureRegionDrawable(game.allAssets.check)
                game.soundUtil.apply { play(spin) }

                coroutine?.launch {
                    balanceFlow.value -= 10
                    val isWin = slotGroup.spin()
                    var win   = 0
                    if (isWin) {
                        win = (5 * (1..50).random())
                        balanceFlow.value += win
                    }

                    runGDX {
                        winLbl.setText(win)
                        isDef = true
                        rechagImg.drawable = TextureRegionDrawable(game.allAssets.def)
                    }
                }
            }
        }
    }

}