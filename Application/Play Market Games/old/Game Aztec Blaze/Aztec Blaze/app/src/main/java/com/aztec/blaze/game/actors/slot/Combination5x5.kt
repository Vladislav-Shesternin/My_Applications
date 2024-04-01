package com.aztec.blaze.game.actors.slot

import com.aztec.blaze.game.actors.slot.Matrix5x5.Item.*

interface CombinationMatrix5x5 {
    val matrixList: List<Matrix5x5>
}

object Combination5x5 {

    object Mix : CombinationMatrix5x5 {
        val _1 = Matrix5x5(
            a1 = a1, b1 = a6, c1 = a2, d1 = a7, e1 = a3,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
            a5 = a5, b5 = a1, c5 = a6, d5 = a2, e5 = a7,
        )
        val _2 = Matrix5x5(
            a1 = a2, b1 = a6, c1 = a3, d1 = a7, e1 = a4,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
            a5 = a5, b5 = a1, c5 = a6, d5 = a2, e5 = a7,
        )
        val _3 = Matrix5x5(
            a1 = a1, b1 = a6, c1 = a2, d1 = a7, e1 = a3,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
            a5 = a4, b5 = a1, c5 = a5, d5 = a2, e5 = a6,
        )
        val _4 = Matrix5x5(
            a1 = a1, b1 = a6, c1 = a2, d1 = a7, e1 = a3,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a9, c3 = a4, d3 = a8, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
            a5 = a5, b5 = a1, c5 = a6, d5 = a2, e5 = a3,
        )
        val _5 = Matrix5x5(
            a1 = a1, b1 = a6, c1 = a2, d1 = a7, e1 = a3,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a7, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
            a5 = a3, b5 = a1, c5 = a6, d5 = a2, e5 = a5,
        )

        override val matrixList = listOf<Matrix5x5>(_1, _2, _3, _4, _5)
    }

    object MixWithWild: CombinationMatrix5x5 {
        val _1 = Matrix5x5(
            scheme = """ W - - - -
                         - - - - -
                         - - - - -
                         - - - - -
                         - - - - -""",
            a1 = W, b1 = a6, c1 = a2, d1 = a7, e1 = a3,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
            a5 = a5, b5 = a1, c5 = a6, d5 = a2, e5 = a7,
        )
        val _2 = Matrix5x5(
            scheme = """ - - W - -
                         - - - - -
                         - - - - -
                         - - - - -
                         - - - - -""",
            a1 = a1, b1 = a6, c1 = W, d1 = a7, e1 = a3,
            a2 = a2, b2 = a4, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
            a5 = a5, b5 = a1, c5 = a6, d5 = a2, e5 = a7,
        )
        val _3 = Matrix5x5(
            scheme = """ - - - - W
                         - - - - -
                         - - - - -
                         - - - - -
                         - - - - -""",
            a1 = a1, b1 = a6, c1 = a2, d1 = a7, e1 = W,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
            a5 = a5, b5 = a1, c5 = a6, d5 = a2, e5 = a7,
        )
        val _4 = Matrix5x5(
            scheme = """ - - - - -
                         - W - - -
                         - - - - -
                         - - - - -
                         - - - - -""",
            a1 = a1, b1 = a6, c1 = a5, d1 = a7, e1 = a3,
            a2 = a2, b2 = W, c2 = a3, d2 = a8, e2 = a4,
            a3 = a9, b3 = a8, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
            a5 = a5, b5 = a1, c5 = a6, d5 = a2, e5 = a7,
        )
        val _5 = Matrix5x5(
            scheme = """ - - - - -
                         - - - W -
                         - - - - -
                         - - - - -
                         - - - - -""",
            a1 = a1, b1 = a6, c1 = a2, d1 = a7, e1 = a1,
            a2 = a2, b2 = a7, c2 = a3, d2 = W, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
            a5 = a5, b5 = a1, c5 = a6, d5 = a2, e5 = a7,
        )
        val _6 = Matrix5x5(
            scheme = """ - - - - -
                         - - W - -
                         - - - - -
                         - - - - -
                         - - - - W""",
            a1 = a1, b1 = a6, c1 = a2, d1 = a1, e1 = a3,
            a2 = a2, b2 = a7, c2 = W, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
            a5 = a5, b5 = a1, c5 = a6, d5 = a2, e5 = W,
        )
        val _7 = Matrix5x5(
            scheme = """ - - - - -
                         - - - - -
                         - - W - -
                         - - - - -
                         - - - - -""",
            a1 = a1, b1 = a6, c1 = a2, d1 = a7, e1 = a3,
            a2 = a2, b2 = a7, c2 = a3, d2 = a6, e2 = a4,
            a3 = a3, b3 = a8, c3 = W, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
            a5 = a5, b5 = a1, c5 = a6, d5 = a2, e5 = a7,
        )
        val _8 = Matrix5x5(
            scheme = """ - - - - -
                         - - - - -
                         W - - - W
                         - - - - -
                         - - - - -""",
            a1 = a1, b1 = a6, c1 = a2, d1 = a7, e1 = a3,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = W, b3 = a8, c3 = a5, d3 = a9, e3 = W,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
            a5 = a5, b5 = a1, c5 = a6, d5 = a2, e5 = a7,
        )
        val _9 = Matrix5x5(
            scheme = """ - - - - -
                         - - - - -
                         - - - - -
                         - - - - -
                         - - W - -""",
            a1 = a1, b1 = a6, c1 = a2, d1 = a7, e1 = a3,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a1, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
            a5 = a5, b5 = a7, c5 = W, d5 = a2, e5 = a7,
        )

        override val matrixList = listOf<Matrix5x5>(_1, _2, _3, _4, _5, _6, _7, _8, _9)
    }

    object WinMonochrome: CombinationMatrix5x5 {
        val _1 = Matrix5x5(
            scheme = """ 1 1 1 1 1
                         - - - - -
                         - - - - -
                         - - - - -
                         - - - - -""",
            winItemList = listOf(a1),
            a1 = a1, b1 = a1, c1 = a1, d1 = a1, e1 = a1,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a7, e4 = a6,
            a5 = a5, b5 = a3, c5 = a6, d5 = a2, e5 = a7,
        )
        val _2 = Matrix5x5(
            scheme = """ - - - - -
                         1 1 1 1 1
                         - - - - -
                         - - - - -
                         - - - - -""",
            winItemList = listOf(a1),
            a1 = a2, b1 = a7, c1 = a3, d1 = a8, e1 = a4,
            a2 = a1, b2 = a1, c2 = a1, d2 = a1, e2 = a1,
            a3 = a3, b3 = a8, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a7, e4 = a6,
            a5 = a5, b5 = a3, c5 = a6, d5 = a2, e5 = a7,
        )
        val _3 = Matrix5x5(
            scheme = """ - - - - -
                         - - - - -
                         1 1 1 1 1
                         - - - - -
                         - - - - -""",
            winItemList = listOf(a1),
            a1 = a2, b1 = a7, c1 = a3, d1 = a8, e1 = a4,
            a2 = a3, b2 = a8, c2 = a4, d2 = a9, e2 = a5,
            a3 = a1, b3 = a1, c3 = a1, d3 = a1, e3 = a1,
            a4 = a4, b4 = a9, c4 = a5, d4 = a7, e4 = a6,
            a5 = a5, b5 = a3, c5 = a6, d5 = a2, e5 = a7,
        )
        val _4 = Matrix5x5(
            scheme = """ - - - - -
                         - - - - -
                         - - - - -
                         1 1 1 1 1
                         - - - - -""",
            winItemList = listOf(a1),
            a1 = a2, b1 = a7, c1 = a3, d1 = a8, e1 = a4,
            a2 = a3, b2 = a8, c2 = a4, d2 = a9, e2 = a5,
            a3 = a4, b3 = a9, c3 = a5, d3 = a7, e3 = a6,
            a4 = a1, b4 = a1, c4 = a1, d4 = a1, e4 = a1,
            a5 = a5, b5 = a3, c5 = a6, d5 = a2, e5 = a7,
        )
        val _5 = Matrix5x5(
            scheme = """ - - - - -
                         - - - - -
                         - - - - -
                         - - - - -
                         1 1 1 1 1""",
            winItemList = listOf(a1),
            a1 = a2, b1 = a7, c1 = a3, d1 = a8, e1 = a4,
            a2 = a3, b2 = a8, c2 = a4, d2 = a9, e2 = a5,
            a3 = a4, b3 = a9, c3 = a5, d3 = a7, e3 = a6,
            a4 = a5, b4 = a3, c4 = a6, d4 = a2, e4 = a7,
            a5 = a1, b5 = a1, c5 = a1, d5 = a1, e5 = a1,
        )
        val _6 = Matrix5x5(
            scheme = """ - - - - 1
                         - - 1 1 -
                         - 1 - - -
                         1 - - - -
                         1 1 1 1 1""",
            winItemList = listOf(a1),
            a1 = a2, b1 = a7, c1 = a3, d1 = a8, e1 = a1,
            a2 = a3, b2 = a8, c2 = a1, d2 = a1, e2 = a5,
            a3 = a4, b3 = a1, c3 = a5, d3 = a7, e3 = a6,
            a4 = a1, b4 = a3, c4 = a6, d4 = a2, e4 = a7,
            a5 = a1, b5 = a1, c5 = a1, d5 = a1, e5 = a1,
        )
        val _7 = Matrix5x5(
            scheme = """ 1 1 1 1 1
                         - 1 1 1 -
                         - 1 - 1 -
                         1 - - - 1
                         1 1 1 1 1""",
            winItemList = listOf(a1),
            a1 = a1, b1 = a1, c1 = a1, d1 = a1, e1 = a1,
            a2 = a3, b2 = a1, c2 = a1, d2 = a1, e2 = a5,
            a3 = a4, b3 = a1, c3 = a5, d3 = a1, e3 = a6,
            a4 = a1, b4 = a3, c4 = a6, d4 = a2, e4 = a1,
            a5 = a1, b5 = a1, c5 = a1, d5 = a1, e5 = a1,
        )
        override val matrixList = listOf<Matrix5x5>(_1, _2, _3, _4, _5, _6, _7)
    }

    object WinMonochromeWithWild: CombinationMatrix5x5 {
        val _1 = Matrix5x5(
            scheme = """ W 1 1 1 1
                         - 1 - - -
                         - - 1 - -
                         - - 1 - -
                         - - 1 1 1""",
            winItemList = listOf(W, a1),
            a1 = W, b1 = a1, c1 = a1, d1 = a1, e1 = a1,
            a2 = a3, b2 = a1, c2 = a5, d2 = a7, e2 = a5,
            a3 = a4, b3 = a2, c3 = a1, d3 = a4, e3 = a6,
            a4 = a3, b4 = a3, c4 = a1, d4 = a2, e4 = a9,
            a5 = a5, b5 = a6, c5 = a1, d5 = a1, e5 = a1,
        )
        val _2 = Matrix5x5(
            scheme = """ W 1 1 - -
                         - 1 1 - -
                         - - - - -
                         - - - - -
                         - W 1 1 1""",
            winItemList = listOf(W, a1),
            a1 = W, b1 = a1, c1 = a1, d1 = a4, e1 = a8,
            a2 = a3, b2 = a1, c2 = a1, d2 = a7, e2 = a5,
            a3 = a4, b3 = a2, c3 = a6, d3 = a4, e3 = a6,
            a4 = a3, b4 = a3, c4 = a6, d4 = a2, e4 = a9,
            a5 = a5, b5 = W, c5 = a1, d5 = a1, e5 = a1,
        )
        val _3 = Matrix5x5(
            scheme = """ - - - - W
                         - 1 - 1 -
                         - - 1 - -
                         W 1 - - -
                         1 1 1 - -""",
            winItemList = listOf(W, a1),
            a1 = a2, b1 = a4, c1 = a5, d1 = a9, e1 = W,
            a2 = a3, b2 = a1, c2 = a5, d2 = a1, e2 = a5,
            a3 = a4, b3 = a2, c3 = a1, d3 = a4, e3 = a6,
            a4 = W, b4 = a1, c4 = a6, d4 = a2, e4 = a9,
            a5 = a1, b5 = a1, c5 = a1, d5 = a7, e5 = a8,
        )
        val _4 = Matrix5x5(
            scheme = """ - - - - -
                         - W - - -
                         - - 1 - -
                         - - 1 - -
                         - - 1 W 1""",
            winItemList = listOf(W, a1),
            a1 = a2, b1 = a3, c1 = a5, d1 = a8, e1 = a9,
            a2 = a3, b2 = W, c2 = a5, d2 = a7, e2 = a5,
            a3 = a4, b3 = a2, c3 = a1, d3 = a4, e3 = a6,
            a4 = a3, b4 = a3, c4 = a1, d4 = a2, e4 = a9,
            a5 = a5, b5 = a6, c5 = a1, d5 = W, e5 = a1,
        )
        val _5 = Matrix5x5(
            scheme = """ - - - - -
                         - - - - -
                         - - 1 1 1
                         - - 1 W W
                         - - 1 W W""",
            winItemList = listOf(W, a1),
            a1 = a2, b1 = a7, c1 = a8, d1 = a9, e1 = a3,
            a2 = a3, b2 = a4, c2 = a5, d2 = a7, e2 = a5,
            a3 = a4, b3 = a2, c3 = a1, d3 = a1, e3 = a1,
            a4 = a3, b4 = a3, c4 = a1, d4 = W, e4 = W,
            a5 = a5, b5 = a6, c5 = a1, d5 = W, e5 = W,
        )
        val _6 = Matrix5x5(
            scheme = """ - 1 - - -
                         1 1 - - -
                         - 1 1 1 1
                         W 1 1 W W
                         - - 1 W W""",
            winItemList = listOf(W, a1),
            a1 = a2, b1 = a1, c1 = a8, d1 = a9, e1 = a3,
            a2 = a1, b2 = a1, c2 = a5, d2 = a7, e2 = a5,
            a3 = a4, b3 = a1, c3 = a1, d3 = a1, e3 = a1,
            a4 = W, b4 = a1, c4 = a1, d4 = W, e4 = W,
            a5 = a5, b5 = a6, c5 = a1, d5 = W, e5 = W,
        )
        val _7 = Matrix5x5(
            scheme = """ W 1 - 1 W
                         1 1 - 1 1
                         - - - - -
                         1 1 - 1 1
                         w 1 - 1 W""",
            winItemList = listOf(W, a1),
            a1 = W, b1 = a1, c1 = a8, d1 = a1, e1 = W,
            a2 = a1, b2 = a1, c2 = a5, d2 = a1, e2 = a1,
            a3 = a4, b3 = a5, c3 = a9, d3 = a4, e3 = a4,
            a4 = a1, b4 = a1, c4 = a7, d4 = a1, e4 = a1,
            a5 = W, b5 = a1, c5 = a6, d5 = a1, e5 = W,
        )

        override val matrixList = listOf<Matrix5x5>(_1, _2, _3, _4, _5, _6, _7)
    }

    object WinColorful: CombinationMatrix5x5 {
        val _1 = Matrix5x5(
            scheme = """ 1 1 1 1 1
                         - - - - -
                         2 2 2 2 2
                         - - - - -
                         - - - - -""",
            winItemList = listOf(a1, a2),
            a1 = a1, b1 = a1, c1 = a1, d1 = a1, e1 = a1,
            a2 = a6, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a2, b3 = a2, c3 = a2, d3 = a2, e3 = a2,
            a4 = a4, b4 = a9, c4 = a5, d4 = a7, e4 = a6,
            a5 = a5, b5 = a3, c5 = a6, d5 = a9, e5 = a7,
        )
        val _2 = Matrix5x5(
            scheme = """ - - 1 1 1
                         1 1 1 - -
                         - - - - -
                         - - 2 2 2
                         2 2 2 - -""",
            winItemList = listOf(a1, a2),
            a1 = a3, b1 = a4, c1 = a1, d1 = a1, e1 = a1,
            a2 = a1, b2 = a1, c2 = a1, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a2, d4 = a2, e4 = a2,
            a5 = a2, b5 = a2, c5 = a2, d5 = a6, e5 = a7,
        )

        override val matrixList = listOf<Matrix5x5>(_1, _2)
    }

    object WinColorfulWithWild: CombinationMatrix5x5 {
        val _1 = Matrix5x5(
            scheme = """ W W 1 1 1
                         1 1 1 - -
                         - - - - -
                         - - 2 2 2
                         2 2 2 W W""",
            winItemList = listOf(W, a1, a2),
            a1 = W, b1 = W, c1 = a1, d1 = a1, e1 = a1,
            a2 = a1, b2 = a1, c2 = a1, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a2, d4 = a2, e4 = a2,
            a5 = a2, b5 = a2, c5 = a2, d5 = W, e5 = W,
        )
        val _2 = Matrix5x5(
            scheme = """ 1 1 - - -
                         W 1 - - -
                         1 1 - 2 2
                         - - - 2 2
                         - - - 2 W""",
            winItemList = listOf(W, a1, a2),
            a1 = a1, b1 = a1, c1 = a7, d1 = a9, e1 = a7,
            a2 = W, b2 = a1, c2 = a3, d2 = a8, e2 = a4,
            a3 = a1, b3 = a1, c3 = a4, d3 = a2, e3 = a2,
            a4 = a4, b4 = a9, c4 = a5, d4 = a2, e4 = a2,
            a5 = a3, b5 = a8, c5 = a4, d5 = a2, e5 = W,
        )
        val _3 = Matrix5x5(
            scheme = """ - 1 W 1 -
                         - 1 1 1 -
                         - - - - -
                         - 2 2 2 -
                         - 2 W 2 -""",
            winItemList = listOf(W, a1, a2),
            a1 = a3, b1 = a1, c1 = W, d1 = a1, e1 = a7,
            a2 = a5, b2 = a1, c2 = a1, d2 = a1, e2 = a4,
            a3 = a6, b3 = a7, c3 = a4, d3 = a7, e3 = a8,
            a4 = a4, b4 = a2, c4 = a2, d4 = a2, e4 = a7,
            a5 = a3, b5 = a2, c5 = W, d5 = a2, e5 = a4,
        )

        override val matrixList = listOf<Matrix5x5>(_1, _2, _3)
    }

}