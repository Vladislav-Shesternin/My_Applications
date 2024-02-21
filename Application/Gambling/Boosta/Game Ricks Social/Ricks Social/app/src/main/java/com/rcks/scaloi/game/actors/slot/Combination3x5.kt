package com.rcks.scaloi.game.actors.slot

import com.rcks.scaloi.game.actors.slot.Matrix3x5.Item.*

interface CombinationMatrix3x5 {
    val matrixList: List<Matrix3x5>
}

object Combination3x5 {

    object Mix : CombinationMatrix3x5 {
        val _1 = Matrix3x5(
            a1 = a1, b1 = a6, c1 = a11,
            a2 = a2, b2 = a7, c2 = a12,
            a3 = a3, b3 = a8, c3 = a13,
            a4 = a4, b4 = a9, c4 = a14,
            a5 = a5, b5 = a10, c5 = a1,
            )
        val _2 = Matrix3x5(
            a1 = a1, b1 = a6, c1 = a11,
            a2 = a1, b2 = a7, c2 = a11,
            a3 = a3, b3 = a8, c3 = a13,
            a4 = a4, b4 = a9, c4 = a14,
            a5 = a5, b5 = a10, c5 = a14,
        )
        val _3 = Matrix3x5(
            a1 = a1, b1 = a1, c1 = a7,
            a2 = a2, b2 = a7, c2 = a12,
            a3 = a3, b3 = a5, c3 = a13,
            a4 = a4, b4 = a9, c4 = a14,
            a5 = a5, b5 = a5, c5 = a1,
        )
        val _4 = Matrix3x5(
            a1 = a10, b1 = a6, c1 = a11,
            a2 = a2, b2 = a7, c2 = a12,
            a3 = a3, b3 = a4, c3 = a13,
            a4 = a4, b4 = a9, c4 = a14,
            a5 = a9, b5 = a10, c5 = a4,
        )
        val _5 = Matrix3x5(
            a1 = a1, b1 = a6, c1 = a1,
            a2 = a2, b2 = a7, c2 = a2,
            a3 = a3, b3 = a8, c3 = a3,
            a4 = a4, b4 = a9, c4 = a4,
            a5 = a5, b5 = a10, c5 = a5,
        )

        override val matrixList = listOf<Matrix3x5>(_1, _2, _3, _4, _5)
    }

    object MixWithWild: CombinationMatrix3x5 {
        val _1 = Matrix3x5(
            scheme = """ W - -
                         - - -
                         - - -
                         - - -
                         - - -""",
            a1 = W, b1 = a6, c1 = a11,
            a2 = a2, b2 = a7, c2 = a12,
            a3 = a3, b3 = a8, c3 = a13,
            a4 = a4, b4 = a9, c4 = a14,
            a5 = a5, b5 = a10, c5 = a1,
        )
        val _2 = Matrix3x5(
            scheme = """ - - W
                         - - -
                         - - -
                         - - -
                         - - -""",
            a1 = a1, b1 = a6, c1 = W,
            a2 = a2, b2 = a7, c2 = a12,
            a3 = a3, b3 = a8, c3 = a13,
            a4 = a4, b4 = a9, c4 = a14,
            a5 = a5, b5 = a10, c5 = a1,
        )
        val _3 = Matrix3x5(
            scheme = """ - - -
                         - W -
                         - - -
                         - - -
                         - - -""",
            a1 = a1, b1 = a6, c1 = a11,
            a2 = a2, b2 = W, c2 = a12,
            a3 = a3, b3 = a8, c3 = a13,
            a4 = a4, b4 = a9, c4 = a14,
            a5 = a5, b5 = a10, c5 = a1,
        )
        val _4 = Matrix3x5(
            scheme = """ - - -
                         - - -
                         - W -
                         - - -
                         - - -""",
            a1 = a1, b1 = a6, c1 = a11,
            a2 = a2, b2 = a7, c2 = a12,
            a3 = a3, b3 = W, c3 = a13,
            a4 = a4, b4 = a9, c4 = a14,
            a5 = a5, b5 = a10, c5 = a1,
        )
        val _5 = Matrix3x5(
            scheme = """ - - -
                         - - -
                         - - W
                         - - -
                         - - -""",
            a1 = a1, b1 = a6, c1 = a11,
            a2 = a2, b2 = a7, c2 = a12,
            a3 = a3, b3 = a8, c3 = W,
            a4 = a4, b4 = a9, c4 = a14,
            a5 = a5, b5 = a10, c5 = a1,
        )
        val _6 = Matrix3x5(
            scheme = """ - - -
                         - - -
                         - - -
                         W - -
                         - - -""",
            a1 = a1, b1 = a6, c1 = a11,
            a2 = a2, b2 = a7, c2 = a12,
            a3 = a3, b3 = a8, c3 = a13,
            a4 = W, b4 = a9, c4 = a14,
            a5 = a5, b5 = a10, c5 = a1,
        )
        val _7 = Matrix3x5(
            scheme = """ - - -
                         - - -
                         - - -
                         - W -
                         - - -""",
            a1 = a1, b1 = a6, c1 = a11,
            a2 = a2, b2 = a7, c2 = a12,
            a3 = a3, b3 = a8, c3 = a13,
            a4 = a4, b4 = W, c4 = a14,
            a5 = a5, b5 = a10, c5 = a1,
        )
        val _8 = Matrix3x5(
            scheme = """ - - -
                         - - -
                         - - -
                         - - -
                         W - -""",
            a1 = a1, b1 = a6, c1 = a11,
            a2 = a2, b2 = a7, c2 = a12,
            a3 = a3, b3 = a8, c3 = a13,
            a4 = a4, b4 = a9, c4 = a14,
            a5 = W, b5 = a10, c5 = a1,
        )
        val _9 = Matrix3x5(
            scheme = """ - - -
                         - - -
                         - - -
                         - - -
                         - - W """,
            a1 = a1, b1 = a6, c1 = a11,
            a2 = a2, b2 = a7, c2 = a12,
            a3 = a3, b3 = a8, c3 = a13,
            a4 = a4, b4 = a9, c4 = a14,
            a5 = a5, b5 = a10, c5 = W,
        )

        override val matrixList = listOf<Matrix3x5>(_1, _2, _3, _4, _5, _6, _7, _8, _9)
    }

    object WinMonochrome: CombinationMatrix3x5 {
        val _1 = Matrix3x5(
            scheme = """ 1 1 1
                         - - -
                         - - -
                         - - -
                         - - -""",
            winItemList = listOf(a1),
            a1 = a1, b1 = a1, c1 = a1,
            a2 = a2, b2 = a7, c2 = a12,
            a3 = a3, b3 = a8, c3 = a13,
            a4 = a4, b4 = a9, c4 = a14,
            a5 = a5, b5 = a10, c5 = a6,
        )
        val _2 = Matrix3x5(
            scheme = """ - - -
                         1 1 1
                         - - -
                         - - -
                         - - -""",
            winItemList = listOf(a1),
            a1 = a2, b1 = a7, c1 = a12,
            a2 = a1, b2 = a1, c2 = a1,
            a3 = a3, b3 = a8, c3 = a13,
            a4 = a4, b4 = a9, c4 = a14,
            a5 = a5, b5 = a10, c5 = a6,
        )
        val _3 = Matrix3x5(
            scheme = """ - - -
                         1 1 1
                         - - -
                         1 1 1
                         - - -""",
            winItemList = listOf(a1),
            a1 = a2, b1 = a7, c1 = a12,
            a2 = a1, b2 = a1, c2 = a1,
            a3 = a3, b3 = a8, c3 = a13,
            a4 = a1, b4 = a1, c4 = a1,
            a5 = a5, b5 = a10, c5 = a6,
        )
        val _4 = Matrix3x5(
            scheme = """ - 1 -
                         1 1 1
                         - - -
                         - 1 -
                         1 1 1""",
            winItemList = listOf(a1),
            a1 = a2, b1 = a1, c1 = a12,
            a2 = a1, b2 = a1, c2 = a1,
            a3 = a3, b3 = a8, c3 = a13,
            a4 = a4, b4 = a1, c4 = a14,
            a5 = a1, b5 = a1, c5 = a1,
        )
        val _5 = Matrix3x5(
            scheme = """ - - -
                         1 1 1
                         - 1 -
                         1 1 1
                         - - -""",
            winItemList = listOf(a1),
            a1 = a2, b1 = a7, c1 = a12,
            a2 = a1, b2 = a1, c2 = a1,
            a3 = a3, b3 = a1, c3 = a13,
            a4 = a1, b4 = a1, c4 = a1,
            a5 = a5, b5 = a10, c5 = a6,
        )
        val _6 = Matrix3x5(
            scheme = """ - - 1
                         - 1 -
                         - - 1
                         - 1 -
                         - - 1""",
            winItemList = listOf(a1),
            a1 = a2, b1 = a7, c1 = a1,
            a2 = a4, b2 = a1, c2 = a4,
            a3 = a3, b3 = a8, c3 = a1,
            a4 = a4, b4 = a1, c4 = a14,
            a5 = a5, b5 = a10, c5 = a1,
        )
        val _7 = Matrix3x5(
            scheme = """ 1 - -
                         1 - -
                         1 - -
                         1 - -
                         1 - -""",
            winItemList = listOf(a1),
            a1 = a1, b1 = a7, c1 = a12,
            a2 = a1, b2 = a2, c2 = a3,
            a3 = a1, b3 = a8, c3 = a13,
            a4 = a1, b4 = a9, c4 = a14,
            a5 = a1, b5 = a10, c5 = a6,
        )
        val _8 = Matrix3x5(
            scheme = """ - 1 -
                         - 1 -
                         - 1 -
                         - 1 -
                         - 1 -""",
            winItemList = listOf(a1),
            a1 = a7, b1 = a1, c1 = a12,
            a2 = a2, b2 = a1, c2 = a3,
            a3 = a8, b3 = a1, c3 = a13,
            a4 = a9, b4 = a1, c4 = a14,
            a5 = a10, b5 = a1, c5 = a6,
        )
        val _9 = Matrix3x5(
            scheme = """ - - 1
                         - - 1
                         - - 1
                         - - 1
                         - - 1""",
            winItemList = listOf(a1),
            a1 = a2, b1 = a7, c1 = a1,
            a2 = a3, b2 = a8, c2 = a1,
            a3 = a4, b3 = a9, c3 = a1,
            a4 = a5, b4 = a10, c4 = a1,
            a5 = a6, b5 = a11, c5 = a1,
        )
        val _10 = Matrix3x5(
            scheme = """ 1 1 1
                         1 1 1
                         1 1 1
                         1 1 1
                         1 1 1""",
            winItemList = listOf(a1),
            a1 = a1, b1 = a1, c1 = a1,
            a2 = a1, b2 = a1, c2 = a1,
            a3 = a1, b3 = a1, c3 = a1,
            a4 = a1, b4 = a1, c4 = a1,
            a5 = a1, b5 = a1, c5 = a1,
        )
        override val matrixList = listOf<Matrix3x5>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10)
    }

    object WinMonochromeWithWild: CombinationMatrix3x5 {
        val _1 = Matrix3x5(
            scheme = """ - - W
                         - - 1
                         - - 1
                         - - 1
                         - - 1""",
            winItemList = listOf(W, a1),
            a1 = a2, b1 = a7, c1 = W,
            a2 = a3, b2 = a8, c2 = a1,
            a3 = a4, b3 = a9, c3 = a1,
            a4 = a5, b4 = a10, c4 = a1,
            a5 = a6, b5 = a11, c5 = a1,
        )
        val _2 = Matrix3x5(
            scheme = """ - W -
                         - 1 -
                         - 1 -
                         - 1 -
                         - 1 -""",
            winItemList = listOf(W, a1),
            a1 = a2, b1 = W, c1 = a7,
            a2 = a3, b2 = a1, c2 = a8,
            a3 = a4, b3 = a1, c3 = a9,
            a4 = a5, b4 = a1, c4 = a10,
            a5 = a6, b5 = a1, c5 = a11,
        )
        val _3 = Matrix3x5(
            scheme = """ W - -
                         1 - -
                         1 - -
                         1 - -
                         1 - -""",
            winItemList = listOf(W, a1),
            a1 = W, b1 = a2, c1 = a7,
            a2 = a1, b2 = a3, c2 = a8,
            a3 = a1, b3 = a4, c3 = a9,
            a4 = a1, b4 = a5, c4 = a10,
            a5 = a1, b5 = a6, c5 = a11,
        )

        override val matrixList = listOf<Matrix3x5>(_1, _2, _3)
    }

    object WinColorful: CombinationMatrix3x5 {
        val _1 = Matrix3x5(
            scheme = """ 1 2 -
                         1 2 -
                         1 2 -
                         1 2 -
                         1 2 -""",
            winItemList = listOf(a1, a2),
            a1 = a1, b1 = a2, c1 = a7,
            a2 = a1, b2 = a2, c2 = a8,
            a3 = a1, b3 = a2, c3 = a9,
            a4 = a1, b4 = a2, c4 = a10,
            a5 = a1, b5 = a2, c5 = a11,
        )
        val _2 = Matrix3x5(
            scheme = """ 1 2 1
                         1 2 1
                         1 2 1
                         1 2 1
                         1 2 1""",
            winItemList = listOf(a1, a2),
            a1 = a1, b1 = a2, c1 = a1,
            a2 = a1, b2 = a2, c2 = a1,
            a3 = a1, b3 = a2, c3 = a1,
            a4 = a1, b4 = a2, c4 = a1,
            a5 = a1, b5 = a2, c5 = a1,
        )
        val _3 = Matrix3x5(
            scheme = """ 1 2 3
                         1 2 3
                         1 2 3
                         1 2 3
                         1 2 3""",
            winItemList = listOf(a1, a2, a3),
            a1 = a1, b1 = a2, c1 = a3,
            a2 = a1, b2 = a2, c2 = a3,
            a3 = a1, b3 = a2, c3 = a3,
            a4 = a1, b4 = a2, c4 = a3,
            a5 = a1, b5 = a2, c5 = a3,
        )
        val _4 = Matrix3x5(
            scheme = """ 1 1 1
                         2 2 2
                         3 3 3
                         4 4 4
                         4 4 4""",
            winItemList = listOf(a1, a2, a3, a4),
            a1 = a1, b1 = a1, c1 = a1,
            a2 = a2, b2 = a2, c2 = a2,
            a3 = a3, b3 = a3, c3 = a3,
            a4 = a4, b4 = a4, c4 = a4,
            a5 = a4, b5 = a4, c5 = a4,
        )
        val _5 = Matrix3x5(
            scheme = """ 1 1 1
                         2 2 2
                         3 3 3
                         4 4 4
                         5 5 5""",
            winItemList = listOf(a1, a2, a3, a4, a5),
            a1 = a1, b1 = a1, c1 = a1,
            a2 = a2, b2 = a2, c2 = a2,
            a3 = a3, b3 = a3, c3 = a3,
            a4 = a4, b4 = a4, c4 = a4,
            a5 = a5, b5 = a5, c5 = a5,
        )

        override val matrixList = listOf<Matrix3x5>(_1, _2)
    }

    object WinColorfulWithWild: CombinationMatrix3x5 {
        val _1 = Matrix3x5(
            scheme = """ W 1 1
                         2 2 2
                         - - -
                         2 1 1
                         2 2 W""",
            winItemList = listOf(W, a1, a2),
            a1 = W, b1 = a1, c1 = a1,
            a2 = a2, b2 = a2, c2 = a2,
            a3 = a3, b3 = a4, c3 = a5,
            a4 = a2, b4 = a1, c4 = a1,
            a5 = a2, b5 = a2, c5 = W,
        )
        val _2 = Matrix3x5(
            scheme = """ W 2 2
                         2 2 2
                         3 3 3
                         1 1 1
                         1 1 W""",
            winItemList = listOf(W, a1, a2, a3),
            a1 = W, b1 = a2, c1 = a2,
            a2 = a2, b2 = a2, c2 = a2,
            a3 = a3, b3 = a3, c3 = a3,
            a4 = a1, b4 = a1, c4 = a1,
            a5 = a1, b5 = a1, c5 = W,
        )
        val _3 = Matrix3x5(
            scheme = """ W 1 W
                         2 1 2
                         1 W 1
                         2 1 1
                         W 2 W""",
            winItemList = listOf(W, a1, a2),
            a1 = W, b1 = a1, c1 = W,
            a2 = a2, b2 = a1, c2 = a2,
            a3 = a1, b3 = W, c3 = a1,
            a4 = a2, b4 = a1, c4 = a2,
            a5 = W, b5 = a1, c5 = W,
        )

        override val matrixList = listOf<Matrix3x5>(_1, _2, _3)
    }

}