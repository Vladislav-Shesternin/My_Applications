package com.hello.piramidka.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.hello.piramidka.game.actors.button.AButton
import com.hello.piramidka.game.actors.button.AButtonStyle
import com.hello.piramidka.game.actors.checkbox.ACheckBox
import com.hello.piramidka.game.actors.checkbox.ACheckBoxGroup
import com.hello.piramidka.game.actors.checkbox.ACheckBoxStyle
import com.hello.piramidka.game.manager.NavigationManager
import com.hello.piramidka.game.manager.SpriteManager
import com.hello.piramidka.game.utils.ThemeUtil
import com.hello.piramidka.game.utils.actor.setOnClickListener
import com.hello.piramidka.game.utils.advanced.AdvancedGroup
import com.hello.piramidka.game.utils.advanced.AdvancedScreen

class ThemeScreen: AdvancedScreen() {

    companion object {
        var darkCheck = true
    }

    private val panelImage  = Image(SpriteManager.GameRegion.THEME.region)
    private val cbDark      = ACheckBox(ACheckBoxStyle.darkCB)
    private val cbLight     = ACheckBox(ACheckBoxStyle.lightCB)
    private val backBtn     = AButton(AButtonStyle.back)

    override fun AdvancedGroup.addActorsOnGroup() {
        addPanel()
        addCB()
        addBack()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addPanel() {
        addActor(panelImage)
        panelImage.setBounds(56f, 414f, 688f, 1110f)
    }

    private fun AdvancedGroup.addCB() {
        addActors(cbDark, cbLight)

        if (darkCheck) cbDark.toCheck() else cbLight.toCheck()

        val groupppp = ACheckBoxGroup()

        cbDark.apply {
            checkBoxGroup = groupppp
            setBounds(172f, 1141f, 55f, 55f)

            setOnCheckListener {
                if (it) {
                    darkCheck = true
                    themeUtil.setTheme(ThemeUtil.ThemeEnum.DARK)
                    NavigationManager.navigate(ThemeScreen())
                }
            }
        }

        cbLight.apply {
            checkBoxGroup = groupppp
            setBounds(576f, 1141f, 55f, 55f)

            setOnCheckListener {
                if (it) {
                    darkCheck = false
                    themeUtil.setTheme(ThemeUtil.ThemeEnum.LIGHT)
                    NavigationManager.navigate(ThemeScreen())
                }
            }
        }
    }

    private fun AdvancedGroup.addBack() {
        addActor(backBtn)
        backBtn.setBounds(240f, 154f, 320f, 202f)
        backBtn.setOnClickListener { NavigationManager.back() }
    }

}

