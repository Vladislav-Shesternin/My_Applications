package com.verdevad.casinavurda.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.verdevad.casinavurda.game.actors.button.AButton
import com.verdevad.casinavurda.game.actors.button.AButtonStyle
import com.verdevad.casinavurda.game.actors.soloka.Soloka
import com.verdevad.casinavurda.game.manager.FontTTFManager
import com.verdevad.casinavurda.game.manager.GameDataStoreManager
import com.verdevad.casinavurda.game.manager.NavigationManager
import com.verdevad.casinavurda.game.manager.SpriteManager
import com.verdevad.casinavurda.game.utils.advanced.AdvancedGroup
import com.verdevad.casinavurda.game.utils.advanced.AdvancedScreen
import com.verdevad.casinavurda.game.utils.runGDX
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DranduletScreen: AdvancedScreen() {

    private val kistkaImage = Image(SpriteManager.GameRegion.KISTKA.region)
    private val napravImage = Image(SpriteManager.GameRegion.LILYA_KAKA.region)
    private val gogo        = AButton(AButtonStyle.gogo)
    private val back        = AButton(AButtonStyle.bebka)
    private val bLabel      = Label("1000", Label.LabelStyle(FontTTFManager.Indie.font_120.font, Color.WHITE))
    private val soloka      = Soloka()
    private val resultImage = Image()

    private val bFlow = MutableStateFlow(-1L)


    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.BKGRNDFK.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addBarbariska()
        addNaprav()
        addBabka()
        addGogo()
        addSoloka()

        addActor(resultImage)
        resultImage.apply {
            addAction(Actions.alpha(0f))
            setBounds(32f, 321f, 615f, 677f)
        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addBarbariska() {
        addActors(kistkaImage, bLabel)
        kistkaImage.apply {
            setBounds(63f, 1074f, 227f, 227f)
        }
        bLabel.apply {
            setBounds(359f, 1100f, 298f, 175f)
        }

        coroutine.launch(Dispatchers.IO) {
            launch { GameDataStoreManager.Balance.collect { balance -> bFlow.value = balance ?: 1_000L } }

            launch {
                bFlow.collect { bebka ->
                    if (bebka != -1L) {
                        runGDX { bLabel.setText(bebka.toString()) }
                        GameDataStoreManager.Balance.update { bebka }
                    }
                }
            }
        }
    }

    private fun AdvancedGroup.addNaprav() {
        addActor(napravImage)
        napravImage.setBounds(103f, 76f, 126f, 113f)
    }

    private fun AdvancedGroup.addBabka() {
        addActor(back)
        back.setBounds(527f, 60f, 121f, 52f)
        back.setOnClickListener { NavigationManager.back() }
    }

    private fun AdvancedGroup.addGogo() {
        addActor(gogo)
        gogo.apply {
            setBounds(255f, 48f, 170f, 170f)
            setOnClickListener {
                coroutine.launch {
                    runGDX { gogo.disable() }

                    bFlow.value -= 100
                    val result = soloka.start()

                    if (result) {
                        bFlow.value += 125

                        resultImage.apply {
                            drawable = TextureRegionDrawable(SpriteManager.SplashRegion.GRB.region)
                            addAction(Actions.sequence(
                                Actions.fadeIn(0.7f),
                                Actions.fadeOut(0.4f)
                            ))
                        }
                    } else {
                        resultImage.apply {
                            drawable = TextureRegionDrawable(SpriteManager.SplashRegion.WDRH.region)
                            addAction(Actions.sequence(
                                Actions.fadeIn(0.7f),
                                Actions.fadeOut(0.4f)
                            ))
                        }
                    }

                    runGDX { gogo.enable() }
                }
            }
        }
    }

    private fun AdvancedGroup.addSoloka() {
        addActor(soloka)
        soloka.setBounds(103f, 304f, 473f, 712f)
    }

}
