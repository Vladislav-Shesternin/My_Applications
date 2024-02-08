package com.csmttt.medus.play.game.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.csmttt.medus.play.game.actors.label.ALabelStyle
import com.csmttt.medus.play.game.manager.SpriteManager
import com.csmttt.medus.play.game.util.advanced.AdvancedGroup
import com.csmttt.medus.play.game.util.disable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.csmttt.medus.play.game.util.Layout.InfoPanel as LIP

class InfoPanel: AdvancedGroup() {

    private val textList = List(8) { Label("0", ALabelStyle.fontBlack_38) }
    private val textListFlow = List(8) { MutableStateFlow(0) }



    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            disable()
            addAction(Actions.alpha(0f))
            addAndFillActor(Image(SpriteManager.GameRegion.INFO_PANEL.region))
            addActorsOnGroup()
            collectText()
        }
    }


    private fun addActorsOnGroup() {

        var newX = LIP.text.x
        var newY = LIP.text.y

        textList.onEachIndexed { index, label ->
            addActor(label)
            label.setAlignment(Align.center)
            with(LIP.text) {
                label.setBounds(newX, newY, w, h)

                newX += w + hs

                if (index == 3) {
                    newX = x
                    newY = (y - vs - h)
                }
            }
        }
    }

    private fun collectText() {
        textList.onEachIndexed { index, label ->
            coroutine.launch {
                textListFlow[index].collect {
                    Gdx.app.postRunnable { label.setText(it) }
                }
            }
        }
    }

    fun updateText(index: Int) {
        textListFlow[index].value += 1
    }



}