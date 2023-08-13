package com.veldan.lbjt.game.actors.checkbox

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.veldan.lbjt.game.utils.advanced.AdvancedGroup
import com.veldan.lbjt.game.utils.advanced.AdvancedScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.math.round

class ACheckBoxTouch(
    override val screen: AdvancedScreen,
    type: Type? = null,
) : ACheckBox(screen, type) {

    override fun getListener() = object : InputListener() {
        val touchPointDown = Vector2()
        val touchPointUp   = Vector2()

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            touchPointDown.set(round(x), round(y))
            return true
        }

        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            touchPointUp.set(round(x), round(y))

            if (
                touchPointDown.x in (touchPointUp.x - 1..touchPointUp.x + 1) &&
                touchPointDown.y in (touchPointUp.y - 1..touchPointUp.y + 1)
            ) if (checkBoxGroup != null) {
                if (checkFlow.value.not()) check()
            } else {
                if (checkFlow.value) uncheck() else check()
            }
        }
    }

}