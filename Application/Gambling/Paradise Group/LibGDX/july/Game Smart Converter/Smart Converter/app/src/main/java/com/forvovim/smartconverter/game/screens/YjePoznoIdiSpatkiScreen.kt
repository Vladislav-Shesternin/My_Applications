package com.forvovim.smartconverter.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.forvovim.smartconverter.game.actors.button.AButton
import com.forvovim.smartconverter.game.actors.button.AButtonStyle
import com.forvovim.smartconverter.game.manager.FontTTFManager
import com.forvovim.smartconverter.game.manager.NavigationManager
import com.forvovim.smartconverter.game.manager.SpriteManager
import com.forvovim.smartconverter.game.utils.GameColor
import com.forvovim.smartconverter.game.utils.actor.animHide
import com.forvovim.smartconverter.game.utils.actor.animShow
import com.forvovim.smartconverter.game.utils.actor.setOnClickListener
import com.forvovim.smartconverter.game.utils.advanced.AdvancedGroup
import com.forvovim.smartconverter.game.utils.advanced.AdvancedScreen
import com.forvovim.smartconverter.game.utils.namekased
import com.forvovim.smartconverter.game.utils.numStr
import com.forvovim.smartconverter.game.utils.runGDX
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

var fromIndex = 0
var toooIndex = 1

var isFrom = true

class YjePoznoIdiSpatkiScreen: AdvancedScreen() {

    private val groupSuper = AdvancedGroup()
    private val gradik     = Image(SpriteManager.GameRegion.GRADIENTALLIKA.region)
    private val viborVal   = Image(SpriteManager.GameRegion.VIBOR_VALUTE.region)
    private val klava      = Image(SpriteManager.GameRegion.KLAVEATURE.region)
    private val convert    = AButton(AButtonStyle.convet)
    private val panel = AdvancedGroup()
    private val kurs  = Label("1 ${namekased[fromIndex]} = ${numStr(1,300, 1)} ${namekased[toooIndex]}", Label.LabelStyle(FontTTFManager.GilMedium.font_30.font, Color.BLACK))

    private val flag1 = Image(SpriteManager.ListRegion.FLAGES.regionList[fromIndex])
    private val flag2 = Image(SpriteManager.ListRegion.FLAGES.regionList[toooIndex])
    private val nam11 = Label(namekased[fromIndex], Label.LabelStyle(FontTTFManager.GilSemBold.font_32.font, Color.BLACK))
    private val nam22 = Label(namekased[toooIndex], Label.LabelStyle(FontTTFManager.GilSemBold.font_32.font, Color.BLACK))
    private val sum11 = Label("1", Label.LabelStyle(FontTTFManager.GilReg.font_24.font, GameColor.gadaBlaCKAaa))
    private val sum22 = Label("${numStr(1, 120, 1)}", Label.LabelStyle(FontTTFManager.GilReg.font_24.font, GameColor.gadaBlaCKAaa))

    private val revert = Actor()

    private var changedLBL = sum11
    private var changed222LBL = sum22

    private val textFlow = MutableStateFlow("1")


    override fun show() {
        super.show()
        mainGroup.addActor(groupSuper)
        groupSuper.apply {
            setBounds(615f, 615f, WIDTH, HEIGHT)
            addAction(Actions.alpha(0f))

            addAcatarasa()
            addActor(convert)
            convert.apply {
                setBounds(29f, 46f, 559f, 99f)
                setOnClickListener { hideKlava() }
            }
            addActor(revert)
            revert.apply {
                setBounds(270f, 957f, 75f, 75f)

                setOnClickListener {
                    flag1.drawable = TextureRegionDrawable(SpriteManager.ListRegion.FLAGES.regionList[toooIndex])
                    flag2.drawable = TextureRegionDrawable(SpriteManager.ListRegion.FLAGES.regionList[fromIndex])
                    nam11.setText(namekased[toooIndex])
                    nam22.setText(namekased[fromIndex])
                    sum11.setText("${numStr(1, 333, 1)}")
                    sum22.setText("${numStr(1, 333, 1)}")

                    val v1 = fromIndex
                    val v2 = toooIndex
                    fromIndex = v2
                    toooIndex = v1
                }
            }

            addKLAVISHI()

        }.animShow(0.5f) {
            coroutine.launch {
                textFlow.collect {
                    runGDX {
                        changedLBL.setText(it)
                        changed222LBL.setText("${numStr(1, 457, 1)}")
                    }
                }
            }
        }
    }

    private fun AdvancedGroup.addAcatarasa() {
        addAndFillActor(gradik)
        addActors(viborVal, klava)

        viborVal.setBounds(27f, 750f, 562f, 536f)
        klava.setBounds(34f, 225f, 550f, 507f)
        klava.addAction(Actions.alpha(0f))

        addActor(panel)
        panel.apply {
            setBounds(34f, 664f, 327f, 77f)
            val kk = Image(SpriteManager.GameRegion.KURS.region)
            addActors(kk, kurs)
            kk.setBounds(0f, 43f, 196f, 33f)
            kurs.setBounds(0f, 0f, 327f, 37f)
        }

        addActors(flag1, flag2)
        flag1.setBounds(68f, 1065f, 102f, 66f)
        flag2.setBounds(68f, 814f, 102f, 66f)

        flag1.setOnClickListener {
            mainGroup.animHide(0.5f) {
                isFrom = true
                NavigationManager.navigate(
                    Andrey_Zdarova_Bandit_Screen(),
                    YjePoznoIdiSpatkiScreen()
                )
            }
        }
        flag2.setOnClickListener {
            mainGroup.animHide(0.5f) {
                isFrom = false
                NavigationManager.navigate(
                    Andrey_Zdarova_Bandit_Screen(),
                    YjePoznoIdiSpatkiScreen()
                )
            }
        }

        addActors(nam11, nam22)
        nam11.setBounds(188f, 1076f, 64f, 40f)
        nam22.setBounds(188f, 825f, 64f, 40f)
        addActors(sum11, sum22)
        sum11.setBounds(377f, 1084f, 159f, 29f)
        sum22.setBounds(377f, 833f, 159f, 29f)
        sum11.setAlignment(Align.right)
        sum22.setAlignment(Align.right)

        val sumAct1 = Actor()
        val sumAct2 = Actor()
        addActors(sumAct1, sumAct2)
        sumAct1.apply {
            setBounds(353f, 1065f, 193f, 66f)
            setOnClickListener {
                changedLBL = sum11
                changed222LBL = sum22
                showKlava()
            }
        }
        sumAct2.apply {
            setBounds(353f, 814f, 193f, 66f)
            setOnClickListener {
                changedLBL = sum22
                changed222LBL = sum11
                showKlava()
            }
        }
    }

    private fun AdvancedGroup.addKLAVISHI() {
        val _1 = Actor()
        val _2 = Actor()
        val _3 = Actor()
        val _4 = Actor()
        val _5 = Actor()
        val _6 = Actor()
        val _7 = Actor()
        val _8 = Actor()
        val _9 = Actor()
        val _0 = Actor()
        val _t = Actor()
        val _r = Actor()
        addActors(_1, _2, _3, _4, _5, _6, _7, _8, _9, _t, _0, _r,)

        var nx = 93f
        var ny = 621f
        listOf(_1, _2, _3, _4, _5, _6, _7, _8, _9, _t, _0, _r,).onEachIndexed { index, actor ->
            actor.setBounds(nx, ny, 97f, 97f)
            nx += 97f+68f

            if (index.inc() % 3 == 0) {
                nx = 93f
                ny -= 29f+97f
            }

        }


        _1.setOnClickListener { textFlow.value += "1" }
        _2.setOnClickListener { textFlow.value += "2" }
        _3.setOnClickListener { textFlow.value += "3" }
        _4.setOnClickListener { textFlow.value += "4" }
        _5.setOnClickListener { textFlow.value += "5" }
        _6.setOnClickListener { textFlow.value += "6" }
        _7.setOnClickListener { textFlow.value += "7" }
        _8.setOnClickListener { textFlow.value += "8" }
        _9.setOnClickListener { textFlow.value += "9" }
        _t.setOnClickListener { textFlow.value += "." }
        _0.setOnClickListener { textFlow.value += "0" }
        _r.setOnClickListener { textFlow.apply { value = value.dropLast(1) } }
    }



    private fun showKlava() {
        panel.addAction(Actions.sequence(
            Actions.fadeOut(0.4f),
            Actions.run {
                textFlow.value = ""
                klava.addAction(Actions.fadeIn(0.4f))
            }
        ))
    }

    private fun hideKlava() {
        klava.addAction(Actions.sequence(
            Actions.fadeOut(0.4f),
            Actions.run {
                panel.addAction(Actions.fadeIn(0.4f))
            }
        ))
    }

}