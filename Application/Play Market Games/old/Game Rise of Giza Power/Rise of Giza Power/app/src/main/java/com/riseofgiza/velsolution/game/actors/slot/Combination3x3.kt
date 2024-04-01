package com.riseofgiza.velsolution.game.actors.slot

import com.riseofgiza.velsolution.game.actors.slot.Matrix3x3.Item.*

interface CombinationMatrix3x3 {
    val matrixList: List<Matrix3x3>
}

object Combination3x3 {

    object Mix : CombinationMatrix3x3 {
        val _1 = Matrix3x3(
                a1 = a1, b1 = a4, c1 = a1,
                a2 = a2, b2 = a5, c2 = a2,
                a3 = a3, b3 = a6, c3 = a3,
            )
        val _2 = Matrix3x3(
            a1 = a1, b1 = a4, c1 = a1,
            a2 = a2, b2 = a5, c2 = a2,
            a3 = a1, b3 = a6, c3 = a2,
        )
        val _3 = Matrix3x3(
            a1 = a1, b1 = a4, c1 = a1,
            a2 = a2, b2 = a5, c2 = a2,
            a3 = a3, b3 = a6, c3 = a5,
        )
        val _4 = Matrix3x3(
            a1 = a1, b1 = a4, c1 = a1,
            a2 = a6, b2 = a5, c2 = a4,
            a3 = a3, b3 = a6, c3 = a3,
        )
        val _5 = Matrix3x3(
            a1 = a1, b1 = a3, c1 = a1,
            a2 = a2, b2 = a6, c2 = a2,
            a3 = a3, b3 = a1, c3 = a3,
        )

        override val matrixList = listOf<Matrix3x3>(_1, _2, _3, _4, _5)
    }

    object MixWithWild: CombinationMatrix3x3 {
        val _1 = Matrix3x3(
            scheme = """ W - -
                         - - -
                         - - - """,
            a1 = W,  b1 = a4, c1 = a1,
            a2 = a2, b2 = a5, c2 = a2,
            a3 = a3, b3 = a6, c3 = a3,
        )
        val _2 = Matrix3x3(
            scheme = """ - W -
                         - - -
                         - - - """,
            a1 = a4,  b1 = W,  c1 = a1,
            a2 = a2, b2 = a5, c2 = a2,
            a3 = a3, b3 = a6, c3 = a3,
        )
        val _3 = Matrix3x3(
            scheme = """ - - W
                         - - -
                         - - - """,
            a1 = a4, b1 = a1, c1 = W,
            a2 = a2, b2 = a5, c2 = a2,
            a3 = a3, b3 = a6, c3 = a3,
        )
        val _4 = Matrix3x3(
            scheme = """ - - -
                         W - -
                         - - - """,
            a1 = a4, b1 = a1, c1 = a2,
            a2 = W, b2 = a5, c2 = a2,
            a3 = a3, b3 = a6, c3 = a3,
        )
        val _5 = Matrix3x3(
            scheme = """ - - -
                         - W -
                         - - - """,
            a1 = a4, b1 = a1, c1 = a2,
            a2 = a5, b2 = W, c2 = a2,
            a3 = a3, b3 = a6, c3 = a3,
        )
        val _6 = Matrix3x3(
            scheme = """ - - -
                         - - W
                         - - - """,
            a1 = a4, b1 = a1, c1 = a2,
            a2 = a5, b2 = a4, c2 = W,
            a3 = a3, b3 = a6, c3 = a3,
        )
        val _7 = Matrix3x3(
            scheme = """ - - -
                         - - -
                         W - - """,
            a1 = a4, b1 = a1, c1 = a2,
            a2 = a5, b2 = a4, c2 = a3,
            a3 = W, b3 = a6, c3 = a3,
        )
        val _8 = Matrix3x3(
            scheme = """ - - -
                         - - -
                         - W - """,
            a1 = a4, b1 = a1, c1 = a2,
            a2 = a5, b2 = a4, c2 = a6,
            a3 = a3, b3 = W, c3 = a3,
        )
        val _9 = Matrix3x3(
            scheme = """ - - -
                         - - -
                         - - W """,
            a1 = a4, b1 = a1, c1 = a2,
            a2 = a5, b2 = a4, c2 = a6,
            a3 = a3, b3 = a5, c3 = W,
        )
        override val matrixList = listOf<Matrix3x3>(_1, _2, _3, _4, _5, _6, _7, _8, _9)
    }

    object WinMonochrome: CombinationMatrix3x3 {
        val _1 = Matrix3x3(
            scheme = """ 1 1 1
                         - - -
                         - - - """,
            winItemList = listOf(a1),
            a1 = a1, b1 = a1, c1 = a1,
            a2 = a3, b2 = a6, c2 = a4,
            a3 = a2, b3 = a5, c3 = a2,
        )
        val _2 = Matrix3x3(
            scheme = """ - - -
                         1 1 1
                         - - - """,
            winItemList = listOf(a1),
            a1 = a5, b1 = a6, c1 = a4,
            a2 = a1, b2 = a1, c2 = a1,
            a3 = a2, b3 = a5, c3 = a2,
        )
        val _3 = Matrix3x3(
            scheme = """ - - -
                         - - -
                         1 1 1 """,
            winItemList = listOf(a1),
            a1 = a5, b1 = a6, c1 = a4,
            a2 = a3, b2 = a4, c2 = a2,
            a3 = a1, b3 = a1, c3 = a1,
        )
        override val matrixList = listOf<Matrix3x3>(_1, _2, _3)
    }

    object WinMonochromeWithWild: CombinationMatrix3x3 {
        val _1 = Matrix3x3(
            scheme = """ W 1 1
                         - - -
                         - - - """,
            winItemList = listOf(W, a1),
            a1 = W, b1 = a1, c1 = a1,
            a2 = a3, b2 = a4, c2 = a2,
            a3 = a2, b3 = a3, c3 = a5,
        )
        val _2 = Matrix3x3(
            scheme = """ W - -
                         - 1 -
                         - - 1 """,
            winItemList = listOf(W, a1),
            a1 = W, b1 = a2, c1 = a6,
            a2 = a3, b2 = a1, c2 = a2,
            a3 = a2, b3 = a3, c3 = a1,
        )
        val _3 = Matrix3x3(
            scheme = """ - - 1
                         1 W 1
                         - - 1 """,
            winItemList = listOf(W, a1),
            a1 = a5, b1 = a6, c1 = a1,
            a2 = a1, b2 = W, c2 = a1,
            a3 = a2, b3 = a3, c3 = a1,
        )
        val _4 = Matrix3x3(
            scheme = """ - - -
                         - - -
                         W 1 1 """,
            winItemList = listOf(W, a1),
            a1 = a5, b1 = a6, c1 = a2,
            a2 = a3, b2 = a4, c2 = a2,
            a3 = W, b3 = a1, c3 = a1,
        )
        val _5 = Matrix3x3(
            scheme = """ - - 1
                         - - 1
                         1 1 W """,
            winItemList = listOf(W, a1),
            a1 = a5, b1 = a6, c1 = a1,
            a2 = a3, b2 = a4, c2 = a1,
            a3 = a1, b3 = a1, c3 = W,
        )
        val _6 = Matrix3x3(
            scheme = """ W 1 1
                         - - 1
                         1 1 W """,
            winItemList = listOf(W, a1),
            a1 = W, b1 = a1, c1 = a1,
            a2 = a3, b2 = a4, c2 = a1,
            a3 = a1, b3 = a1, c3 = W,
        )
        val _7 = Matrix3x3(
            scheme = """ W 1 1
                         1 W 1
                         1 1 W """,
            winItemList = listOf(W, a1),
            a1 = W, b1 = a1, c1 = a1,
            a2 = a1, b2 = W, c2 = a1,
            a3 = a1, b3 = a1, c3 = W,
        )
        override val matrixList = listOf<Matrix3x3>(_1, _2, _3, _4, _5, _6, _7)
    }

    object WinColorful: CombinationMatrix3x3 {
        val _1 = Matrix3x3(
            scheme = """ 1 1 1
                         2 2 2
                         - - - """,
            winItemList = listOf(a1, a2),
            a1 = a1, b1 = a1, c1 = a1,
            a2 = a2, b2 = a2, c2 = a2,
            a3 = a6, b3 = a5, c3 = a4,
        )
        val _2 = Matrix3x3(
            scheme = """ 1 2 1
                         2 1 2
                         - - - """,
            winItemList = listOf(a1, a2),
            a1 = a1, b1 = a2, c1 = a1,
            a2 = a2, b2 = a1, c2 = a2,
            a3 = a6, b3 = a5, c3 = a4,
        )
        val _3 = Matrix3x3(
            scheme = """ - 2 -
                         2 1 2
                         1 - 1 """,
            winItemList = listOf(a1, a2),
            a1 = a3, b1 = a2, c1 = a4,
            a2 = a2, b2 = a1, c2 = a2,
            a3 = a1, b3 = a4, c3 = a1,
        )
        val _4 = Matrix3x3(
            scheme = """ - 2 2
                         2 - -
                         1 1 1 """,
            winItemList = listOf(a1, a2),
            a1 = a3, b1 = a2, c1 = a2,
            a2 = a2, b2 = a5, c2 = a3,
            a3 = a1, b3 = a1, c3 = a1,
        )
        val _5 = Matrix3x3(
            scheme = """ 2 2 2
                         1 1 1
                         3 3 3 """,
            winItemList = listOf(a1, a2, a3),
            a1 = a3, b1 = a2, c1 = a2,
            a2 = a1, b2 = a1, c2 = a1,
            a3 = a3, b3 = a3, c3 = a3,
        )
        val _6 = Matrix3x3(
            scheme = """ 1 2 1
                         2 1 2
                         3 3 3 """,
            winItemList = listOf(a1, a2, a3),
            a1 = a1, b1 = a2, c1 = a1,
            a2 = a2, b2 = a1, c2 = a2,
            a3 = a3, b3 = a3, c3 = a3,
        )
        val _7 = Matrix3x3(
            scheme = """ 2 2 1
                         3 1 2
                         1 3 3 """,
            winItemList = listOf(a1, a2, a3),
            a1 = a2, b1 = a2, c1 = a1,
            a2 = a3, b2 = a1, c2 = a2,
            a3 = a1, b3 = a3, c3 = a3,
        )

        override val matrixList = listOf<Matrix3x3>(_1, _2, _3, _4, _5,_6,_7)
    }

    object WinColorfulWithWild: CombinationMatrix3x3 {
        val _1 = Matrix3x3(
            scheme = """ 1 1 1
                         2 W 2
                         - - - """,
            winItemList = listOf(W, a1, a2),
            a1 = a1, b1 = a1, c1 = a1,
            a2 = a2, b2 = W, c2 = a2,
            a3 = a6, b3 = a5, c3 = a4,
        )
        val _2 = Matrix3x3(
            scheme = """ 1 2 W
                         W 1 2
                         - - - """,
            winItemList = listOf(W, a1, a2),
            a1 = a1, b1 = a2, c1 = W,
            a2 = W, b2 = a1, c2 = a2,
            a3 = a6, b3 = a5, c3 = a4,
        )
        val _3 = Matrix3x3(
            scheme = """ - W -
                         2 W 2
                         1 - 1 """,
            winItemList = listOf(W, a1, a2),
            a1 = a3, b1 = W, c1 = a6,
            a2 = a2, b2 = W, c2 = a2,
            a3 = a1, b3 = a4, c3 = a1,
        )
        val _4 = Matrix3x3(
            scheme = """ - 2 W
                         2 - -
                         W 1 1 """,
            winItemList = listOf(W, a1, a2),
            a1 = a3, b1 = a2, c1 = W,
            a2 = a2, b2 = a5, c2 = a3,
            a3 = W, b3 = a1, c3 = a1,
        )
        val _5 = Matrix3x3(
            scheme = """ 2 2 W
                         W 1 1
                         3 3 W """,
            winItemList = listOf(W, a1, a2, a3),
            a1 = a3, b1 = a2, c1 = W,
            a2 = W, b2 = a1, c2 = a1,
            a3 = a3, b3 = a3, c3 = W,
        )
        val _6 = Matrix3x3(
            scheme = """ 1 2 W
                         2 W 2
                         W 3 3 """,
            winItemList = listOf(W, a1, a2, a3),
            a1 = a1, b1 = a2, c1 = W,
            a2 = a2, b2 = W, c2 = a2,
            a3 = W, b3 = a3, c3 = a3,
        )
        val _7 = Matrix3x3(
            scheme = """ W 2 1
                         3 W 2
                         1 3 W """,
            winItemList = listOf(W, a1, a2, a3),
            a1 = W, b1 = a2, c1 = a1,
            a2 = a3, b2 = W, c2 = a2,
            a3 = a1, b3 = a3, c3 = W,
        )
        override val matrixList = listOf<Matrix3x3>(_1, _2, _3, _4, _5, _6, _7)
    }

}