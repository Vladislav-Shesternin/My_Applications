package com.fellinger.yeasman.game.box2d.bodiesGroup

import com.badlogic.gdx.math.Vector2
import com.fellinger.yeasman.game.box2d.AbstractBody
import com.fellinger.yeasman.game.box2d.AbstractBodyGroup
import com.fellinger.yeasman.game.box2d.WorldUtil
import com.fellinger.yeasman.game.box2d.bodies.Balk
import com.fellinger.yeasman.game.box2d.bodies.Door
import com.fellinger.yeasman.game.utils.Size
import com.fellinger.yeasman.game.utils.SizeConverter
import com.fellinger.yeasman.game.utils.advanced.AdvancedStage
import com.fellinger.yeasman.game.utils.Layout.BG_Balk as LB

class BG_Balk(override val worldUtil: WorldUtil): AbstractBodyGroup() {

    private val balkTop    = Balk(worldUtil, Balk.Type.TOP)
    private val balkBottom = Balk(worldUtil, Balk.Type.BOTTOM)
    private val door       = Door(worldUtil)


    override fun initialize(
        stageUI: AdvancedStage,
        sizeConverterUIToBox: SizeConverter,
        sizeConverterBoxToUI: SizeConverter,
        position: Vector2,
        size: Size
    ) {
        super.initialize(stageUI, sizeConverterUIToBox, sizeConverterBoxToUI, position, size)

        createBalkTop()
        createBalkBottom()
        createDoor()
    }

    override fun destroy() {
        super.destroy()
        listOf<AbstractBody>(balkTop, balkBottom).onEach { it.destroy() }
    }

    var bx = 0f
    var by = 0f
    fun changePosition(x: Float, y: Float) {
        position.set(x, y)

        bx = sizeConverterUIToBox.getSizeX(x)
        by = sizeConverterUIToBox.getSizeY(y)
        balkTop.body.setTransform(bx, by + sizeConverterUIToBox.getSizeY(LB.balkTopY), 0f)
        door.body.setTransform(bx, by + sizeConverterUIToBox.getSizeY(LB.doorY), 0f)
        balkBottom.body.setTransform(bx, by, 0f)
    }

    // ------------------------------------------------------------------------
    // Create Bodies
    // ------------------------------------------------------------------------
    private fun createBalkTop() {
        balkTop.initialize(
            stageUI,
            sizeConverterUIToBox,
            sizeConverterBoxToUI,
            Vector2(position.x, position.y + LB.balkTopY),
            LB.balkSize,
        )
    }

    private fun createBalkBottom() {
        balkBottom.initialize(
            stageUI,
            sizeConverterUIToBox,
            sizeConverterBoxToUI,
            position,
            LB.balkSize,
        )
    }

    private fun createDoor() {
        door.initialize(
            stageUI,
            sizeConverterUIToBox,
            sizeConverterBoxToUI,
            Vector2(position.x, position.y + LB.doorY),
            LB.doorSize,
        )
    }

}