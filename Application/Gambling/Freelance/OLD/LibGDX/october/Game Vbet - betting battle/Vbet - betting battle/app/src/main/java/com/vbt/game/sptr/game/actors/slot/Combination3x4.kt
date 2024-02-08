package com.vbt.game.sptr.game.actors.slot

import com.vbt.game.sptr.game.actors.slot.Matrix3x4.Item.*

interface CombinationMatrix3x4 {
    val matrixList: List<Matrix3x4>
}

object Combination3x4 {

    object Mix : CombinationMatrix3x4 {
        val _1 = Matrix3x4(
            a1 = a1, b1 = a6, c1 = a2,
            a2 = a2, b2 = a7, c2 = a3,
            a3 = a3, b3 = a8, c3 = a4,
            a4 = a4, b4 = a1, c4 = a5,
        )
        val _2 = Matrix3x4(
            a1 = a1, b1 = a6, c1 = a2,
            a2 = a2, b2 = a3, c2 = a3,
            a3 = a1, b3 = a8, c3 = a4,
            a4 = a4, b4 = a2, c4 = a5,
        )
        val _3 = Matrix3x4(
            a1 = a1, b1 = a6, c1 = a1,
            a2 = a2, b2 = a7, c2 = a3,
            a3 = a3, b3 = a8, c3 = a4,
            a4 = a4, b4 = a1, c4 = a6,
        )
        val _4 = Matrix3x4(
            a1 = a1, b1 = a6, c1 = a1,
            a2 = a2, b2 = a7, c2 = a2,
            a3 = a4, b3 = a1, c3 = a4,
            a4 = a5, b4 = a1, c4 = a5,
        )
        val _5 = Matrix3x4(
            a1 = a1, b1 = a6, c1 = a4,
            a2 = a2, b2 = a7, c2 = a3,
            a3 = a3, b3 = a8, c3 = a4,
            a4 = a4, b4 = a6, c4 = a1,
        )

        override val matrixList = listOf<Matrix3x4>(_1, _2, _3, _4, _5)
    }

    object MixWithWild: CombinationMatrix3x4 {
        val _1 = Matrix3x4(
            scheme = """ W - -
                         - - -
                         - - -
                         - - -""",
            a1 = W, b1 = a6, c1 = a2,
            a2 = a2, b2 = a7, c2 = a3,
            a3 = a3, b3 = a8, c3 = a4,
            a4 = a4, b4 = a1, c4 = a5,
        )
        val _2 = Matrix3x4(
            scheme = """ - W -
                         - - -
                         - - -
                         - - -""",
            a1 = a1, b1 = W, c1 = a2,
            a2 = a4, b2 = a7, c2 = a3,
            a3 = a3, b3 = a8, c3 = a4,
            a4 = a4, b4 = a1, c4 = a5,
        )
        val _3 = Matrix3x4(
            scheme = """ - - W
                         - - -
                         - - -
                         - - -""",
            a1 = a1, b1 = a5, c1 = W,
            a2 = a4, b2 = a7, c2 = a3,
            a3 = a3, b3 = a8, c3 = a4,
            a4 = a4, b4 = a1, c4 = a5,
        )
        val _4 = Matrix3x4(
            scheme = """ - - -
                         W - -
                         - - -
                         - - -""",
            a1 = a1, b1 = a5, c1 = a4,
            a2 = W, b2 = a7, c2 = a3,
            a3 = a3, b3 = a8, c3 = a4,
            a4 = a4, b4 = a1, c4 = a5,
        )
        val _5 = Matrix3x4(
            scheme = """ - - -
                         - W -
                         - - -
                         - - -""",
            a1 = a1, b1 = a5, c1 = a4,
            a2 = a6, b2 = W, c2 = a3,
            a3 = a2, b3 = a8, c3 = a4,
            a4 = a4, b4 = a1, c4 = a5,
        )
        val _6 = Matrix3x4(
            scheme = """ - - -
                         - - W
                         - - -
                         - - -""",
            a1 = a1, b1 = a5, c1 = a4,
            a2 = a6, b2 = a3, c2 = W,
            a3 = a2, b3 = a8, c3 = a6,
            a4 = a4, b4 = a1, c4 = a5,
        )
        val _7 = Matrix3x4(
            scheme = """ - - -
                         - - -
                         - W -
                         - - -""",
            a1 = a1, b1 = a5, c1 = a4,
            a2 = a6, b2 = a7, c2 = a3,
            a3 = a2, b3 = W, c3 = a6,
            a4 = a4, b4 = a1, c4 = a5,
        )
        val _8 = Matrix3x4(
            scheme = """ - - -
                         - - -
                         - - -
                         W - -""",
            a1 = a1, b1 = a5, c1 = a4,
            a2 = a6, b2 = a7, c2 = a3,
            a3 = a2, b3 = a4, c3 = a6,
            a4 = W, b4 = a1, c4 = a5,
        )
        val _9 = Matrix3x4(
            scheme = """ - - -
                         - - -
                         - - -
                         - - W""",
            a1 = a1, b1 = a5, c1 = a4,
            a2 = a6, b2 = a7, c2 = a3,
            a3 = a2, b3 = a4, c3 = a6,
            a4 = a3, b4 = a1, c4 = W,
        )

        override val matrixList = listOf<Matrix3x4>(_1, _2, _3, _4, _5, _6, _7, _8, _9)
    }

    object WinMonochrome: CombinationMatrix3x4 {
        val _1 = Matrix3x4(
            scheme = """ 1 1 1
                         - - -
                         - - -
                         - - -""",
            winItemList = listOf(a1),
            a1 = a1, b1 = a1, c1 = a1,
            a2 = a2, b2 = a5, c2 = a8,
            a3 = a3, b3 = a6, c3 = a2,
            a4 = a4, b4 = a7, c4 = a3,
        )
        val _2 = Matrix3x4(
            scheme = """ - - -
                         1 1 1
                         - - -
                         - - -""",
            winItemList = listOf(a1),
            a1 = a2, b1 = a5, c1 = a8,
            a2 = a1, b2 = a1, c2 = a1,
            a3 = a3, b3 = a6, c3 = a2,
            a4 = a4, b4 = a7, c4 = a3,
        )
        val _3 = Matrix3x4(
            scheme = """ - - -
                         - - -
                         1 1 1
                         - - -""",
            winItemList = listOf(a1),
            a1 = a2, b1 = a5, c1 = a8,
            a2 = a3, b2 = a6, c2 = a2,
            a3 = a1, b3 = a1, c3 = a1,
            a4 = a4, b4 = a7, c4 = a3,
        )
        val _4 = Matrix3x4(
            scheme = """ - - -
                         - - -
                         - - -
                         1 1 1""",
            winItemList = listOf(a1),
            a1 = a2, b1 = a5, c1 = a8,
            a2 = a3, b2 = a6, c2 = a2,
            a3 = a4, b3 = a7, c3 = a3,
            a4 = a1, b4 = a1, c4 = a1,
        )
        val _5 = Matrix3x4(
            scheme = """ 1 - -
                         - 1 -
                         - - 1
                         - - -""",
            winItemList = listOf(a1),
            a1 = a1, b1 = a5, c1 = a8,
            a2 = a3, b2 = a1, c2 = a2,
            a3 = a4, b3 = a7, c3 = a1,
            a4 = a3, b4 = a8, c4 = a2,
        )
        val _6 = Matrix3x4(
            scheme = """ - - 1
                         - 1 -
                         1 - -
                         - - -""",
            winItemList = listOf(a1),
            a1 = a2, b1 = a5, c1 = a1,
            a2 = a3, b2 = a1, c2 = a2,
            a3 = a1, b3 = a7, c3 = a3,
            a4 = a2, b4 = a8, c4 = a5,
        )
        val _7 = Matrix3x4(
            scheme = """ 1 - 1
                         - 1 -
                         - 1 -
                         1 1 1""",
            winItemList = listOf(a1),
            a1 = a1, b1 = a5, c1 = a1,
            a2 = a3, b2 = a1, c2 = a2,
            a3 = a4, b3 = a1, c3 = a3,
            a4 = a1, b4 = a1, c4 = a1,
        )
        override val matrixList = listOf<Matrix3x4>(_1, _2, _3, _4, _5, _6, _7)
    }

    object WinMonochromeWithWild: CombinationMatrix3x4 {
        val _1 = Matrix3x4(
            scheme = """ W 1 1
                         - - -
                         - - -
                         - - -""",
            winItemList = listOf(W, a1),
            a1 = W, b1 = a1, c1 = a1,
            a2 = a2, b2 = a5, c2 = a8,
            a3 = a3, b3 = a6, c3 = a2,
            a4 = a4, b4 = a7, c4 = a3,
        )
        val _2 = Matrix3x4(
            scheme = """ 1 W 1
                         - - -
                         - - -
                         - - -""",
            winItemList = listOf(W, a1),
            a1 = a1, b1 = W, c1 = a1,
            a2 = a2, b2 = a5, c2 = a8,
            a3 = a3, b3 = a6, c3 = a2,
            a4 = a4, b4 = a7, c4 = a3,
        )
        val _3 = Matrix3x4(
            scheme = """ - 1 -
                         1 - W
                         - - -
                         - - -""",
            winItemList = listOf(W, a1),
            a1 = a2, b1 = a1, c1 = a3,
            a2 = a1, b2 = a5, c2 = W,
            a3 = a3, b3 = a6, c3 = a2,
            a4 = a4, b4 = a7, c4 = a3,
        )
        val _4 = Matrix3x4(
            scheme = """ - - 1
                         - 1 1
                         W - -
                         - - -""",
            winItemList = listOf(W, a1),
            a1 = a3, b1 = a4, c1 = a1,
            a2 = a2, b2 = a1, c2 = a1,
            a3 = W, b3 = a6, c3 = a2,
            a4 = a4, b4 = a7, c4 = a3,
        )
        val _5 = Matrix3x4(
            scheme = """ - - -
                         1 - 1
                         - W -
                         1 - 1""",
            winItemList = listOf(W, a1),
            a1 = a5, b1 = a6, c1 = a7,
            a2 = a1, b2 = a8, c2 = a1,
            a3 = a3, b3 = W, c3 = a2,
            a4 = a1, b4 = a7, c4 = a1,
        )
        val _6 = Matrix3x4(
            scheme = """ - 1 1
                         - - 1
                         W 1 -
                         - - 1""",
            winItemList = listOf(W, a1),
            a1 = a3, b1 = a1, c1 = a1,
            a2 = a2, b2 = a5, c2 = a1,
            a3 = W, b3 = a1, c3 = a2,
            a4 = a4, b4 = a7, c4 = a1,
        )
        val _7 = Matrix3x4(
            scheme = """ - 1 -
                         W - 1
                         - 1 -
                         1 - W""",
            winItemList = listOf(W, a1),
            a1 = a3, b1 = a1, c1 = a8,
            a2 = W, b2 = a5, c2 = a1,
            a3 = a3, b3 = a1, c3 = a2,
            a4 = a1, b4 = a7, c4 = W,
        )

        override val matrixList = listOf<Matrix3x4>(_1, _2, _3, _4, _5, _6, _7)
    }

    object WinColorful: CombinationMatrix3x4 {
        val _1 = Matrix3x4(
            scheme = """ 1 1 1
                         2 2 2
                         - - -
                         - - -""",
            winItemList = listOf(a1, a2),
            a1 = a1, b1 = a1, c1 = a1,
            a2 = a2, b2 = a2, c2 = a2,
            a3 = a3, b3 = a6, c3 = a4,
            a4 = a4, b4 = a7, c4 = a3,
        )
        val _2 = Matrix3x4(
            scheme = """ 1 1 1
                         2 2 2
                         3 3 3
                         - - -""",
            winItemList = listOf(a1, a2, a3),
            a1 = a1, b1 = a1, c1 = a1,
            a2 = a2, b2 = a2, c2 = a2,
            a3 = a3, b3 = a3, c3 = a3,
            a4 = a4, b4 = a5, c4 = a6,
        )
        val _3 = Matrix3x4(
            scheme = """ 1 1 1
                         2 2 2
                         3 3 3
                         4 4 4""",
            winItemList = listOf(a1, a2, a3, a4),
            a1 = a1, b1 = a1, c1 = a1,
            a2 = a2, b2 = a2, c2 = a2,
            a3 = a3, b3 = a3, c3 = a3,
            a4 = a4, b4 = a4, c4 = a4,
        )


        override val matrixList = listOf<Matrix3x4>(_1, _2, _3)
    }

    object WinColorfulWithWild: CombinationMatrix3x4 {
        val _1 = Matrix3x4(
            scheme = """ 1 1 1
                         2 W 2
                         3 3 3
                         W 4 4""",
            winItemList = listOf(W, a1, a2, a3, a4),
            a1 = a1, b1 = a1, c1 = a1,
            a2 = a2, b2 = W, c2 = a2,
            a3 = a3, b3 = a3, c3 = a3,
            a4 = W, b4 = a4, c4 = a4,
        )
        val _2 = Matrix3x4(
            scheme = """ 1 1 1
                         2 W 2
                         - - -
                         - - -""",
            winItemList = listOf(W, a1, a2),
            a1 = a1, b1 = a1, c1 = a1,
            a2 = a2, b2 = W, c2 = a2,
            a3 = a8, b3 = a7, c3 = a6,
            a4 = a4, b4 = a3, c4 = a5,
        )
        val _3 = Matrix3x4(
            scheme = """ W 1 1
                         3 2 3
                         3 3 2
                         - - -""",
            winItemList = listOf(W, a1, a2, a3),
            a1 = W, b1 = a1, c1 = a1,
            a2 = a3, b2 = a2, c2 = a3,
            a3 = a3, b3 = a3, c3 = a2,
            a4 = a4, b4 = a3, c4 = a5,
        )

        override val matrixList = listOf<Matrix3x4>(_1, _2, _3)
    }

}