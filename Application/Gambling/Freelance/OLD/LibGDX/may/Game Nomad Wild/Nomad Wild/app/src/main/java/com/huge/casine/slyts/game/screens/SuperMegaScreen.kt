package com.huge.casine.slyts.game.screens

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.huge.casine.slyts.game.actors.baraban.Baraban
import com.huge.casine.slyts.game.actors.button.AButton
import com.huge.casine.slyts.game.actors.button.AButtonStyle
import com.huge.casine.slyts.game.actors.checkbox.ACheckBox
import com.huge.casine.slyts.game.actors.checkbox.ACheckBoxGroup
import com.huge.casine.slyts.game.actors.checkbox.ACheckBoxStyle
import com.huge.casine.slyts.game.manager.FontTTFManager
import com.huge.casine.slyts.game.manager.GameDataStoreManager
import com.huge.casine.slyts.game.manager.SpriteManager
import com.huge.casine.slyts.game.utils.GameColor
import com.huge.casine.slyts.game.utils.advanced.AdvancedGroup
import com.huge.casine.slyts.game.utils.advanced.AdvancedScreen
import com.huge.casine.slyts.game.utils.runGDX
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.cos

class SuperMegaScreen: AdvancedScreen() {

    private val letwinImage = Image(SpriteManager.GameRegion.LOS_WIN.region)
    private val tenkaImage  = Image(SpriteManager.GameRegion.TEN_KA.region)
    private val start       = AButton(AButtonStyle.start)
    private val bLabel      = Label("", Label.LabelStyle(FontTTFManager.InriaSerifFont.font_68.font, GameColor.ylow))
    private val cLos        = ACheckBox(ACheckBoxStyle.stg)
    private val cWin        = ACheckBox(ACheckBoxStyle.stg)
    private val baraban     = Baraban()
    private val redImage    = Image(SpriteManager.GameRegion.RED.region)
    private val greenImage  = Image(SpriteManager.GameRegion.GREEN.region)


    private val cbGroup = ACheckBoxGroup()
    private var isWin = true

    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.BURAKSA.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addTenKa()
        addGreenRed()
        addLosWin()
        addBlank()
        addBoxesC()
        addBaraban()
        addStart()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addGreenRed() {
        addActors(greenImage, redImage)

        greenImage.apply {
            addAction(Actions.alpha(0f))
            setBounds(210f, 228f, 395f, 287f)
        }
        redImage.apply {
            addAction(Actions.alpha(0f))
            setBounds(-74f, 228f, 395f, 287f)
        }
    }

    private fun AdvancedGroup.addLosWin() {
        addActor(letwinImage)

        letwinImage.setBounds(64f, 238f, 411f, 174f)
    }

    private fun AdvancedGroup.addTenKa() {
        addActor(tenkaImage)

        tenkaImage.setBounds(96f, 926f, 347f, 92f)
    }

    private fun AdvancedGroup.addBlank() {
        addActor(bLabel)

        bLabel.apply {
            setBounds(101f, 931f, 336f, 82f)
            setAlignment(Align.center)

            coroutine.launch(Dispatchers.IO) {
                GameDataStoreManager.Balance.collect { balance ->
                    runGDX { setText((balance ?: 10_000).toString()) }
                }
            }
        }
    }
    private fun AdvancedGroup.addBoxesC() {
        addActors(cLos, cWin)

        cLos.apply {
            checkBoxGroup = cbGroup
            setBounds(64f, 249f, 60f, 60f)
            setOnCheckListener { if (it) isWin = false }
        }
        cWin.apply {
            checkBoxGroup = cbGroup
            check()
            setBounds(415f, 249f, 60f, 60f)
            setOnCheckListener { if (it) isWin = true }
        }
    }
    private fun AdvancedGroup.addBaraban() {
        addActor(baraban)
        baraban.apply {
            setBounds(64f, 456f, 411f, 411f)
        }
    }

    private fun AdvancedGroup.addStart() {
        addActor(start)

        start.apply {
            setBounds(152f, 57f, 235f, 117f)
            setOnClickListener {
                coroutine.launch {
                    runGDX { start.disable() }
                    val result = baraban.start()
                    val color  = if (result) greenImage else redImage
                    color.addAction(Actions.fadeIn(0.2f))
                    delay(400)
                    color.addAction(Actions.fadeOut(0.2f))

                    val cost = if (result && isWin || !result && !isWin) 1000 else -1000

                    withContext(Dispatchers.IO) {
                        GameDataStoreManager.Balance.update { (it ?: 10_000L) + cost }
                    }

                    runGDX { start.enable() }
                }
            }
        }
    }

}
