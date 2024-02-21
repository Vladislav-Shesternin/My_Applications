package com.jungle.jumping.bird.game.box2d

import com.badlogic.gdx.math.Vector2
import com.jungle.jumping.bird.game.utils.Size
import com.jungle.jumping.bird.game.utils.SizeConverter
import com.jungle.jumping.bird.game.utils.advanced.AdvancedStage
import com.jungle.jumping.bird.utils.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class AbstractBodyGroup {
    abstract val worldUtil: WorldUtil

    lateinit var stageUI             : AdvancedStage private set
    lateinit var sizeConverterUIToBox: SizeConverter private set
    lateinit var sizeConverterBoxToUI: SizeConverter private set

    val size      = Size()
    val position  = Vector2()
    val coroutine = CoroutineScope(Dispatchers.Default)



    open fun destroy() {
        cancelCoroutinesAll(coroutine)
    }

    open fun initialize(
        stageUI: AdvancedStage,
        sizeConverterUIToBox: SizeConverter,
        sizeConverterBoxToUI: SizeConverter,
        position: Vector2,
        size: Size,
    ) {
        this.stageUI              = stageUI
        this.sizeConverterUIToBox = sizeConverterUIToBox
        this.sizeConverterBoxToUI = sizeConverterBoxToUI
        this.position.set(position)
        this.size.set(size)
    }

}