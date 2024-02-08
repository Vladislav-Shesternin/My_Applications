package com.hgrt.wrld.game.actors.slot

import com.hgrt.wrld.game.actors.slot.Matrix6x3.Item.*

interface CombinationMatrix6x3 {
    val matrixList: List<Matrix6x3>
}

object Combination6x3 {

    object Mix : CombinationMatrix6x3 {
        val _1 = Matrix6x3(
                a1 = a1, b1 = a4, c1 = a7, d1 = a1, e1 = a4, f1 = a7,
                a2 = a2, b2 = a5, c2 = a8, d2 = a2, e2 = a5, f2 = a8,
                a3 = a3, b3 = a6, c3 = a9, d3 = a3, e3 = a6, f3 = a9,
            )
        val _2 = Matrix6x3(
            a1 = a1, b1 = a4, c1 = a7, d1 = a1, e1 = a7, f1 = a1,
            a2 = a2, b2 = a5, c2 = a8, d2 = a2, e2 = a8, f2 = a4,
            a3 = a3, b3 = a6, c3 = a9, d3 = a3, e3 = a9, f3 = a9,
        )
        val _3 = Matrix6x3(
            a1 = a1, b1 = a3, c1 = a7, d1 = a1, e1 = a3, f1 = a7,
            a2 = a2, b2 = a5, c2 = a8, d2 = a2, e2 = a8, f2 = a8,
            a3 = a3, b3 = a6, c3 = a9, d3 = a3, e3 = a9, f3 = a3,
        )
        val _4 = Matrix6x3(
            a1 = a1, b1 = a3, c1 = a7, d1 = a1, e1 = a9, f1 = a1,
            a2 = a1, b2 = a5, c2 = a8, d2 = a2, e2 = a8, f2 = a1,
            a3 = a3, b3 = a6, c3 = a8, d3 = a3, e3 = a9, f3 = a5,
        )
        val _5 = Matrix6x3(
            a1 = a2, b1 = a2, c1 = a7, d1 = a1, e1 = a9, f1 = a4,
            a2 = a1, b2 = a5, c2 = a8, d2 = a5, e2 = a8, f2 = a4,
            a3 = a3, b3 = a6, c3 = a3, d3 = a3, e3 = a6, f3 = a8,
        )

        override val matrixList = listOf<Matrix6x3>(_1, _2, _3, _4, _5)
    }

    object MixWithWild: CombinationMatrix6x3 {
        val _1 = Matrix6x3(
            scheme = """ W - - - - -
                         - - - - - -
                         - - - - - - """,
            a1 = W, b1 = a4, c1 = a7, d1 = a1, e1 = a4, f1 = a7,
            a2 = a2, b2 = a5, c2 = a8, d2 = a2, e2 = a5, f2 = a8,
            a3 = a3, b3 = a6, c3 = a9, d3 = a3, e3 = a6, f3 = a9,
        )
        val _2 = Matrix6x3(
            scheme = """ - W - - - -
                         - - - - - -
                         - - - - - - """,
            a1 = a9, b1 = W, c1 = a7, d1 = a1, e1 = a4, f1 = a7,
            a2 = a2, b2 = a5, c2 = a8, d2 = a2, e2 = a5, f2 = a8,
            a3 = a3, b3 = a6, c3 = a9, d3 = a3, e3 = a6, f3 = a9,
        )
        val _3 = Matrix6x3(
            scheme = """ - - W - - -
                         - - - - - -
                         - - - - - - """,
            a1 = a7, b1 = a4, c1 = W, d1 = a1, e1 = a4, f1 = a7,
            a2 = a2, b2 = a5, c2 = a8, d2 = a2, e2 = a5, f2 = a8,
            a3 = a3, b3 = a6, c3 = a9, d3 = a3, e3 = a6, f3 = a9,
        )
        val _4 = Matrix6x3(
            scheme = """ - - - W - -
                         - - - - - -
                         - - - - - - """,
            a1 = a7, b1 = a4, c1 = a1, d1 = W, e1 = a4, f1 = a7,
            a2 = a2, b2 = a5, c2 = a8, d2 = a2, e2 = a5, f2 = a8,
            a3 = a3, b3 = a6, c3 = a9, d3 = a3, e3 = a6, f3 = a9,
        )
        val _5 = Matrix6x3(
            scheme = """ - - - - W -
                         - - - - - -
                         - - - - - - """,
            a1 = a7, b1 = a4, c1 = a1, d1 = a7, e1 = W, f1 = a9,
            a2 = a2, b2 = a5, c2 = a8, d2 = a2, e2 = a5, f2 = a8,
            a3 = a3, b3 = a6, c3 = a9, d3 = a3, e3 = a6, f3 = a9,
        )
        val _6 = Matrix6x3(
            scheme = """ - - - - - -
                         W - - - - -
                         - - - - - - """,
            a1 = a7, b1 = a4, c1 = a1, d1 = a7, e1 = a9, f1 = a7,
            a2 = W, b2 = a5, c2 = a8, d2 = a2, e2 = a5, f2 = a8,
            a3 = a3, b3 = a6, c3 = a9, d3 = a3, e3 = a6, f3 = a9,
        )
        val _7 = Matrix6x3(
            scheme = """ - - - - - -
                         - - - W - -
                         - - - - - - """,
            a1 = a7, b1 = a4, c1 = a1, d1 = a7, e1 = a6, f1 = a7,
            a2 = a9, b2 = a5, c2 = a8, d2 = W, e2 = a5, f2 = a8,
            a3 = a3, b3 = a6, c3 = a9, d3 = a3, e3 = a6, f3 = a9,
        )
        val _8 = Matrix6x3(
            scheme = """ - - - - - -
                         - - - - - -
                         - W - - - - """,
            a1 = a7, b1 = a4, c1 = a1, d1 = a7, e1 = a6, f1 = a7,
            a2 = a9, b2 = a5, c2 = a8, d2 = a2, e2 = a5, f2 = a8,
            a3 = a3, b3 = W, c3 = a9, d3 = a3, e3 = a6, f3 = a9,
        )
        val _9 = Matrix6x3(
            scheme = """ - - - - - -
                         - - - - - -
                         - - - - W - """,
            a1 = a7, b1 = a4, c1 = a1, d1 = a7, e1 = a6, f1 = a7,
            a2 = a9, b2 = a5, c2 = a8, d2 = a2, e2 = a5, f2 = a8,
            a3 = a3, b3 = a1, c3 = a9, d3 = a3, e3 = W, f3 = a9,
        )

        override val matrixList = listOf<Matrix6x3>(_1, _2, _3, _4, _5, _6, _7, _8, _9)
    }

    object WinMonochrome: CombinationMatrix6x3 {
        val _1 = Matrix6x3(
            scheme = """ 1 1 1 1 1 1
                         - - - - - -
                         - - - - - - """,
            winItemList = listOf(a1),
            a1 = a1, b1 = a1, c1 = a1, d1 = a1, e1 = a1, f1 = a1,
            a2 = a4, b2 = a5, c2 = a8, d2 = a5, e2 = a8, f2 = a8,
            a3 = a3, b3 = a6, c3 = a3, d3 = a3, e3 = a6, f3 = a9,
        )
        val _2 = Matrix6x3(
            scheme = """ - - - - - -
                         1 1 1 1 1 1
                         - - - - - - """,
            winItemList = listOf(a1),
            a1 = a4, b1 = a5, c1 = a9, d1 = a5, e1 = a8, f1 = a7,
            a2 = a1, b2 = a1, c2 = a1, d2 = a1, e2 = a1, f2 = a1,
            a3 = a3, b3 = a6, c3 = a7, d3 = a3, e3 = a6, f3 = a9,
        )
        val _3 = Matrix6x3(
            scheme = """ - - - - - -
                         - - - - - -
                         1 1 1 1 1 1 """,
            winItemList = listOf(a1),
            a1 = a4, b1 = a5, c1 = a9, d1 = a5, e1 = a8, f1 = a7,
            a2 = a3, b2 = a6, c2 = a7, d2 = a4, e2 = a7, f2 = a8,
            a3 = a1, b3 = a1, c3 = a1, d3 = a1, e3 = a1, f3 = a9,
        )
        val _4 = Matrix6x3(
            scheme = """ 1 1 - 1 1 -
                         - - 1 - - 1
                         1 1 1 1 1 - """,
            winItemList = listOf(a1),
            a1 = a1, b1 = a1, c1 = a9, d1 = a1, e1 = a1, f1 = a3,
            a2 = a3, b2 = a6, c2 = a1, d2 = a4, e2 = a7, f2 = a1,
            a3 = a1, b3 = a1, c3 = a1, d3 = a1, e3 = a1, f3 = a9,
        )
        val _5 = Matrix6x3(
            scheme = """ 1 1 - 1 1 -
                         - 1 - 1 - 1
                         1 1 - 1 1 - """,
            winItemList = listOf(a1),
            a1 = a1, b1 = a1, c1 = a9, d1 = a1, e1 = a1, f1 = a7,
            a2 = a3, b2 = a1, c2 = a2, d2 = a1, e2 = a4, f2 = a1,
            a3 = a1, b3 = a1, c3 = a5, d3 = a1, e3 = a1, f3 = a9,
        )
        val _6 = Matrix6x3(
            scheme = """ 1 1 - 1 1 1
                         1 1 - 1 1 -
                         1 1 - 1 1 1 """,
            winItemList = listOf(a1),
            a1 = a1, b1 = a1, c1 = a9, d1 = a1, e1 = a1, f1 = a1,
            a2 = a1, b2 = a1, c2 = a2, d2 = a1, e2 = a1, f2 = a8,
            a3 = a1, b3 = a1, c3 = a5, d3 = a1, e3 = a1, f3 = a1,
        )
        val _7 = Matrix6x3(
            scheme = """ 1 1 1 1 1 1
                         1 1 - - 1 1
                         1 1 1 1 1 1 """,
            winItemList = listOf(a1),
            a1 = a1, b1 = a1, c1 = a1, d1 = a1, e1 = a1, f1 = a1,
            a2 = a1, b2 = a1, c2 = a3, d2 = a4, e2 = a1, f2 = a1,
            a3 = a1, b3 = a1, c3 = a1, d3 = a1, e3 = a1, f3 = a1,
        )
        override val matrixList = listOf<Matrix6x3>(_1, _2, _3, _4, _5, _6, _7)
    }

    object WinMonochromeWithWild: CombinationMatrix6x3 {
        val _1 = Matrix6x3(
            scheme = """ W 1 1 1 1 1
                         - - - - - -
                         - - - - - - """,
            winItemList = listOf(W, a1),
            a1 = W, b1 = a1, c1 = a1, d1 = a1, e1 = a1, f1 = a1,
            a2 = a1, b2 = a5, c2 = a8, d2 = a5, e2 = a8, f2 = a8,
            a3 = a3, b3 = a6, c3 = a3, d3 = a3, e3 = a6, f3 = a9,
        )
        val _2 = Matrix6x3(
            scheme = """ - - - - - -
                         W 1 1 1 1 1
                         - - - - - - """,
            winItemList = listOf(W, a1),
            a1 = a1, b1 = a5, c1 = a8, d1 = a5, e1 = a9, f1 = a7,
            a2 = W, b2 = a1, c2 = a1, d2 = a1, e2 = a1, f2 = a1,
            a3 = a3, b3 = a6, c3 = a7, d3 = a3, e3 = a7, f3 = a9,
        )
        val _3 = Matrix6x3(
            scheme = """ - - - - - -
                         - - - - - -
                         1 1 W 1 1 1 """,
            winItemList = listOf(W, a1),
            a1 = a4, b1 = a5, c1 = a8, d1 = a5, e1 = a9, f1 = a7,
            a2 = a2, b2 = a3, c2 = a6, d2 = a7, e2 = a6, f2 = a8,
            a3 = a1, b3 = a1, c3 = W, d3 = a1, e3 = a1, f3 = a1,
        )
        val _4 = Matrix6x3(
            scheme = """ 1 - - - - -
                         - 1 - - - 1
                         1 1 W 1 1 - """,
            winItemList = listOf(W, a1),
            a1 = a1, b1 = a5, c1 = a8, d1 = a5, e1 = a9, f1 = a7,
            a2 = a2, b2 = a1, c2 = a6, d2 = a7, e2 = a6, f2 = a1,
            a3 = a1, b3 = a1, c3 = W, d3 = a1, e3 = a1, f3 = a9,
        )
        val _5 = Matrix6x3(
            scheme = """ 1 - - - 1 -
                         - 1 - 1 - 1
                         1 1 W 1 1 - """,
            winItemList = listOf(W, a1),
            a1 = a1, b1 = a5, c1 = a8, d1 = a5, e1 = a1, f1 = a7,
            a2 = a2, b2 = a1, c2 = a6, d2 = a1, e2 = a6, f2 = a1,
            a3 = a1, b3 = a1, c3 = W, d3 = a1, e3 = a1, f3 = a9,
        )
        val _6 = Matrix6x3(
            scheme = """ 1 - W - 1 -
                         - 1 - 1 - 1
                         - 1 W 1 - 1 """,
            winItemList = listOf(W, a1),
            a1 = a1, b1 = a5, c1 = W, d1 = a9, e1 = a1, f1 = a7,
            a2 = a2, b2 = a1, c2 = a6, d2 = a1, e2 = a3, f2 = a1,
            a3 = a7, b3 = a1, c3 = W, d3 = a1, e3 = a4, f3 = a1,
        )
        val _7 = Matrix6x3(
            scheme = """ W - W - W -
                         - 1 - 1 - 1
                         W - W - W - """,
            winItemList = listOf(W, a1),
            a1 = W, b1 = a5, c1 = W, d1 = a9, e1 = W, f1 = a7,
            a2 = a2, b2 = a1, c2 = a6, d2 = a1, e2 = a3, f2 = a1,
            a3 = W, b3 = a2, c3 = W, d3 = a7, e3 = W, f3 = a9,
        )

        override val matrixList = listOf<Matrix6x3>(_1, _2, _3, _4, _5, _6, _7)
    }

    object WinColorful: CombinationMatrix6x3 {
        val _1 = Matrix6x3(
            scheme = """ 1 1 1 1 1 2
                         2 2 2 2 2 1
                         - - - - - - """,
            winItemList = listOf(a1, a2),
            a1 = a1, b1 = a1, c1 = a1, d1 = a1, e1 = a1, f1 = a2,
            a2 = a2, b2 = a2, c2 = a2, d2 = a2, e2 = a2, f2 = a1,
            a3 = a4, b3 = a5, c3 = a6, d3 = a7, e3 = a9, f3 = a9,
        )
        val _2 = Matrix6x3(
            scheme = """ 1 1 1 1 1 2
                         2 2 3 2 2 1
                         3 3 2 3 3 3 """,
            winItemList = listOf(a1, a2, a3),
            a1 = a1, b1 = a1, c1 = a1, d1 = a1, e1 = a1, f1 = a2,
            a2 = a2, b2 = a2, c2 = a3, d2 = a2, e2 = a2, f2 = a1,
            a3 = a3, b3 = a3, c3 = a2, d3 = a3, e3 = a3, f3 = a3,
        )

        override val matrixList = listOf<Matrix6x3>(_1, _2)
    }

    object WinColorfulWithWild: CombinationMatrix6x3 {
        val _1 = Matrix6x3(
            scheme = """ 1 1 1 2 1 W
                         2 W 2 W 2 1
                         - - - - - - """,
            winItemList = listOf(W, a1, a2),
            a1 = a1, b1 = a1, c1 = a1, d1 = a2, e1 = a1, f1 = W,
            a2 = a2, b2 = W, c2 = a2, d2 = W, e2 = a2, f2 = a1,
            a3 = a3, b3 = a4, c3 = a5, d3 = a6, e3 = a7, f3 = a9,
        )
        val _2 = Matrix6x3(
            scheme = """ 1 1 1 2 1 1
                         2 W 2 W 2 2
                         3 3 W 3 3 3 """,
            winItemList = listOf(W, a1, a2, a3),
            a1 = a1, b1 = a1, c1 = a1, d1 = a2, e1 = a1, f1 = a1,
            a2 = a2, b2 = W, c2 = a2, d2 = W, e2 = a2, f2 = a2,
            a3 = a3, b3 = a3, c3 = W, d3 = a3, e3 = a3, f3 = a3,
        )
        val _3 = Matrix6x3(
            scheme = """ W 1 - 3 - 3
                         2 W W W 3 2
                         - 1 - 3 W 3 """,
            winItemList = listOf(W, a1, a2, a3),
            a1 = W, b1 = a1, c1 = a5, d1 = a3, e1 = a5, f1 = a3,
            a2 = a2, b2 = W, c2 = W, d2 = W, e2 = a3, f2 = a2,
            a3 = a4, b3 = a1, c3 = a8, d3 = a3, e3 = W, f3 = a3,
        )

        override val matrixList = listOf<Matrix6x3>(_1, _2, _3)
    }

}