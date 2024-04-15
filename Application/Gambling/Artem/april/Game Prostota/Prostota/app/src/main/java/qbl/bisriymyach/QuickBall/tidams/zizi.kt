package qbl.bisriymyach.QuickBall.tidams

import com.badlogic.gdx.math.Vector2
import qbl.bisriymyach.QuickBall.fastergan.divOr0

class zizi {

    private var standart = 1f

    val Vector2.toStandart get() = this.divOr0(standart)

    fun standardize(standartW: Float, newW: Float) {
        standart = standartW / newW
    }

    inline fun <T> scope(block: zizi.() -> T) = block(this)
}