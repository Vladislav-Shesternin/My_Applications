package com.uchimenkoe.financecounter.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.uchimenkoe.financecounter.game.actors.Bugar
import com.uchimenkoe.financecounter.game.actors.button.AButton
import com.uchimenkoe.financecounter.game.actors.button.AButtonStyle
import com.uchimenkoe.financecounter.game.manager.FontTTFManager
import com.uchimenkoe.financecounter.game.manager.NavigationManager
import com.uchimenkoe.financecounter.game.manager.SpriteManager
import com.uchimenkoe.financecounter.game.utils.GameColor
import com.uchimenkoe.financecounter.game.utils.advanced.AdvancedGroup
import com.uchimenkoe.financecounter.game.utils.advanced.AdvancedScreen
import com.uchimenkoe.financecounter.game.utils.runGDX
import com.uchimenkoe.financecounter.util.log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CalculatorScreen: AdvancedScreen() {

    private val flamenkoImage = Image(SpriteManager.GameRegion.FLOMENKO.region)
    private val AC = Label("AC", Label.LabelStyle(FontTTFManager.DaysOneRegular.font_80.font, Color.WHITE)).apply { setAlignment(Align.center) }
    private val AA = Label("( )", Label.LabelStyle(FontTTFManager.DaysOneRegular.font_80.font, Color.WHITE)).apply { setAlignment(Align.center) }
    private val PE = Label("%", Label.LabelStyle(FontTTFManager.DaysOneRegular.font_80.font, Color.WHITE)).apply { setAlignment(Align.center) }
    private val _1  = Label("1", Label.LabelStyle(FontTTFManager.DaysOneRegular.font_80.font, Color.WHITE)).apply { setAlignment(Align.center) }
    private val _2  = Label("2", Label.LabelStyle(FontTTFManager.DaysOneRegular.font_80.font, Color.WHITE)).apply { setAlignment(Align.center) }
    private val _3  = Label("3", Label.LabelStyle(FontTTFManager.DaysOneRegular.font_80.font, Color.WHITE)).apply { setAlignment(Align.center) }
    private val _4  = Label("4", Label.LabelStyle(FontTTFManager.DaysOneRegular.font_80.font, Color.WHITE)).apply { setAlignment(Align.center) }
    private val _5  = Label("5", Label.LabelStyle(FontTTFManager.DaysOneRegular.font_80.font, Color.WHITE)).apply { setAlignment(Align.center) }
    private val _6  = Label("6", Label.LabelStyle(FontTTFManager.DaysOneRegular.font_80.font, Color.WHITE)).apply { setAlignment(Align.center) }
    private val _7  = Label("7", Label.LabelStyle(FontTTFManager.DaysOneRegular.font_80.font, Color.WHITE)).apply { setAlignment(Align.center) }
    private val _8  = Label("8", Label.LabelStyle(FontTTFManager.DaysOneRegular.font_80.font, Color.WHITE)).apply { setAlignment(Align.center) }
    private val _9  = Label("9", Label.LabelStyle(FontTTFManager.DaysOneRegular.font_80.font, Color.WHITE)).apply { setAlignment(Align.center) }
    private val _0  = Label("0", Label.LabelStyle(FontTTFManager.DaysOneRegular.font_80.font, Color.WHITE)).apply { setAlignment(Align.center) }
    private val deleteImage = Image(SpriteManager.GameRegion.DELETE.region)
    private val menuImage   = Image(SpriteManager.GameRegion.MENU.region)
    private val PL = Label("+", Label.LabelStyle(FontTTFManager.DaysOneRegular.font_100.font, GameColor.fugu)).apply { setAlignment(Align.center) }
    private val MI = Label("-", Label.LabelStyle(FontTTFManager.DaysOneRegular.font_100.font, GameColor.fugu)).apply { setAlignment(Align.center) }
    private val MN = Label("*", Label.LabelStyle(FontTTFManager.DaysOneRegular.font_100.font, GameColor.fugu)).apply { setAlignment(Align.center) }
    private val DL = Label("/", Label.LabelStyle(FontTTFManager.DaysOneRegular.font_80.font, GameColor.fugu)).apply { setAlignment(Align.center) }
    private val resultLabel  = Label("0", Label.LabelStyle(FontTTFManager.DaysOneRegular.font_100.font, Color.WHITE)).apply { setAlignment(Align.right) }
    private val previewLabel = Label("", Label.LabelStyle(FontTTFManager.DaysOneRegular.font_60.font, GameColor.fugu)).apply { setAlignment(Align.right) }

    private val burdek = Bugar()

    private val resultFlow  = MutableStateFlow(0L)
    private val previewFlow = MutableStateFlow("")

    private var cifra = ""
    private val massivCifr = mutableListOf<Long>()
    private val massivDeis = mutableListOf<String>()


    override fun AdvancedGroup.addActorsOnGroup() {
        addFlamenko()
        addCnopochki()
        addDeleteMenu()
        addDeistvia()
        addResult()
        addPreview()
        addBugred()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addFlamenko() {
        addActor(flamenkoImage)
        flamenkoImage.setBounds(0f, 0f, 845f, 813f)
    }

    private fun AdvancedGroup.addCnopochki() {
        var nx = 0f
        var ny = 649f

        var bx = 30f
        var by = 649f

        listOf(AC, AA, PE, _1, _2, _3, _4, _5, _6, _7, _8, _9).onEachIndexed { index, label ->
            addActor(label)
            label.setBounds(nx, ny, 222f, 162f)

            nx += 222f
            if (index.inc() % 3 == 0) {
                nx = 0f
                ny -= 162f
            }

            AButton(AButtonStyle.elik).also { btn ->
                addActor(btn)
                btn.apply {
                    setBounds(bx, by, 162f, 162f)

                    bx += 162f + 60f
                    if (index.inc() % 3 == 0) {
                        bx = 30f
                        by -= 162f
                    }

                    if (index > 2) {
                        setOnClickListener {
                            cifra += (index-2)
                            previewFlow.value += (index-2)
                        }
                    }

                    if (index == 0) setOnClickListener { NavigationManager.navigate(CalculatorScreen()) }
                }
            }
        }

        addActor(_0)
        _0.setBounds(222f, 1f, 222f, 162f)

        addActor(AButton(AButtonStyle.elik).apply {
            setBounds(252f, 1f, 162f, 162f)
            setOnClickListener {
                cifra += 0
                previewFlow.value += 0
            }
        })
    }

    private fun AdvancedGroup.addDeleteMenu() {
        addActors(deleteImage, menuImage)
        deleteImage.apply {
            setBounds(0f, 1f, 222f, 162f)
        }
        menuImage.apply {
            setBounds(444f, 1f, 222f, 162f)
        }

        List(2) { AButton(AButtonStyle.elik) }.onEachIndexed { index, btn ->
            addActor(btn)
            if (index == 0) btn.setBounds(30f, 1f, 162f, 162f)
            else {
                btn.setBounds(474f, 1f, 162f, 162f)
                btn.setOnClickListener { burdek.showAct() }
            }
        }
    }

    private fun AdvancedGroup.addDeistvia() {
        var ny = 649f
        var by = 649f

        listOf(PL, MI, MN, DL).onEachIndexed { index, label ->
            addActor(label)
            label.setBounds(669f, ny, 176f, 162f)

            ny -= 162f

            AButton(AButtonStyle.elik).also { btn ->
                addActor(btn)
                btn.apply {
                    setBounds(676f, by, 162f, 162f)

                    by -= 162f

                    setOnClickListener {
                        try { massivCifr.add(cifra.toLong()) } catch (e: Exception) { massivCifr.add(0) }
                        cifra = ""

                        when (index) {
                            0 -> "+"
                            1 -> "-"
                            2 -> "*"
                            3 -> "/"
                            else -> "+"
                        }.also { znak ->
                            if (previewFlow.value.isNotEmpty()) {
                                listOf('+', '-', '*', '/').onEach {
                                    if (previewFlow.value.last() == it) previewFlow.apply { value = value.substring(0, value.lastIndex) }
                                }
                                previewFlow.value += znak
                                massivDeis.add(znak)
                            }
                        }
                    }
                }
            }
        }

        addActor(Image(SpriteManager.GameRegion.DORIVNUE.region).apply {
            setBounds(669f, 1f, 176f, 162f)
        })

        addActor(AButton(AButtonStyle.elik).apply {
            setBounds(676f, 1f, 162f, 162f)
            setOnClickListener {
                try {
                    if (massivDeis.isNotEmpty()) {

                        listOf('+', '-', '*', '/').onEach {
                            if (previewFlow.value.last() == it) previewFlow.apply {
                                value = value.substring(0, value.lastIndex)
                                massivDeis.removeLast()
                                massivCifr.removeLast()
                            }
                        }

                        massivCifr.add(cifra.toLong())

                        var result = massivCifr.first()

                        log(massivCifr.joinToString())
                        log(massivDeis.joinToString())

                        massivDeis.onEachIndexed { index, dei ->
                            result = try {
                                result.dia(massivCifr[index + 1], dei)
                            } catch (e: Exception) {
                                log("dd ${e.message}")
                                0
                            }
                        }

                        massivCifr.clear()
                        massivDeis.clear()

                        previewFlow.value = ""
                        resultFlow.value = result

                        cifra = ""
                    }
                } catch (e: Exception) {
                    massivCifr.clear()
                    massivDeis.clear()

                    previewFlow.value = ""
                    resultFlow.value = 0

                    cifra = ""
                }
            }
        })
    }

    private fun AdvancedGroup.addResult() {
        addActor(resultLabel)
        resultLabel.setBounds(44f, 895f, 757f, 119f)

        coroutine.launch {
            resultFlow.collect { result ->
                runGDX { resultLabel.setText(result.toString()) }
            }
        }
    }

    private fun AdvancedGroup.addPreview() {
        addActor(previewLabel)
        previewLabel.setBounds(44f, 1014f, 757f, 119f)

        coroutine.launch {
            previewFlow.collect { text ->
                runGDX { previewLabel.setText(text) }
            }
        }
    }

    private fun AdvancedGroup.addBugred() {
        addActor(burdek)
        burdek.setBounds(-845f, 0f, 845f, 1625f)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun Long.dia(b: Long, dia: String): Long = when (dia) {
        "+" -> this + b
        "-" -> this - b
        "*" -> this * b
        "/" -> this / b
        else -> 0L
    }

}