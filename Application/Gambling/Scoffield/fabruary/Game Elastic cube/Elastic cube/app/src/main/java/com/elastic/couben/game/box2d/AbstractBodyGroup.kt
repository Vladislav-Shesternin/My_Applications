package com.elastic.couben.game.box2d

import com.badlogic.gdx.math.Vector2
import com.elastic.couben.game.utils.advanced.AdvancedBox2dScreen

abstract class AbstractBodyGroup {
    abstract val screenBox2d: AdvancedBox2dScreen

    val position = Vector2()
    val world    by lazy { screenBox2d.worldUtil.world }



    open fun create(position: Vector2) {
        this.position.set(position)
    }

    open fun destroy() {}

}