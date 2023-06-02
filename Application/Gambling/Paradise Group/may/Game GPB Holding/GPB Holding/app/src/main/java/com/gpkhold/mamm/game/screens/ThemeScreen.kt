package com.gpkhold.mamm.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.gpkhold.mamm.game.actors.button.AButton
import com.gpkhold.mamm.game.actors.button.AButtonStyle
import com.gpkhold.mamm.game.actors.checkbox.ACheckBox
import com.gpkhold.mamm.game.actors.checkbox.ACheckBoxGroup
import com.gpkhold.mamm.game.actors.checkbox.ACheckBoxStyle
import com.gpkhold.mamm.game.manager.NavigationManager
import com.gpkhold.mamm.game.manager.SpriteManager
import com.gpkhold.mamm.game.utils.ThemeUtil
import com.gpkhold.mamm.game.utils.advanced.AdvancedGroup
import com.gpkhold.mamm.game.utils.advanced.AdvancedScreen

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

