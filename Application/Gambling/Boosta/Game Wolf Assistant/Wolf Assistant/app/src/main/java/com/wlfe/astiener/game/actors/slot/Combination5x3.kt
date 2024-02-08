package com.wlfe.astiener.game.actors.slot

import com.wlfe.astiener.game.actors.slot.Matrix5x3.Item.*

interface CombinationMatrix5x3 {
    val matrixList: List<Matrix5x3>
}

object Combination5x3 {

    object Mix : CombinationMatrix5x3 {
        val _1 = Matrix5x3(
            a1 = a1, b1 = a6, c1 = a2, d1 = a7, e1 = a3,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a1, e3 = a5,

        )
        val _2 = Matrix5x3(
            a1 = a4, b1 = a6, c1 = a2, d1 = a7, e1 = a5,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a1, e3 = a5,
        )
        val _3 = Matrix5x3(
            a1 = a1, b1 = a6, c1 = a2, d1 = a7, e1 = a3,
            a2 = a2, b2 = a7, c2 = a1, d2 = a8, e2 = a2,
            a3 = a1, b3 = a8, c3 = a4, d3 = a1, e3 = a5,
        )
        val _4 = Matrix5x3(
            a1 = a1, b1 = a6, c1 = a2, d1 = a7, e1 = a3,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a2, d3 = a1, e3 = a2,
        )
        val _5 = Matrix5x3(
            a1 = a2, b1 = a6, c1 = a2, d1 = a8, e1 = a3,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a1, e3 = a5,
        )

        override val matrixList = listOf<Matrix5x3>(_1, _2, _3, _4, _5)
    }

    object MixWithWild: CombinationMatrix5x3 {
        val _1 = Matrix5x3(
            scheme = """ 
                W - - - -
                - - - - -
                - - - - -
                         """,
            a1 = W, b1 = a6, c1 = a2, d1 = a7, e1 = a3,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a6, e3 = a5,
        )
        val _2 = Matrix5x3(
            scheme = """ 
                - - W - -
                - - - - -
                - - - - -
                         """,
            a1 = a1, b1 = a6, c1 = W, d1 = a7, e1 = a3,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a6, e3 = a5,
        )
        val _3 = Matrix5x3(
            scheme = """ 
                - - - - W
                - - - - -
                - - - - -
                         """,
            a1 = a1, b1 = a6, c1 = a2, d1 = a7, e1 = W,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a6, e3 = a5,
        )
        val _4 = Matrix5x3(
            scheme = """ 
                - - - - -
                - W - - -
                - - - - -
                         """,
            a1 = a1, b1 = a6, c1 = a5, d1 = a7, e1 = a3,
            a2 = a2, b2 = W, c2 = a3, d2 = a8, e2 = a4,
            a3 = a7, b3 = a8, c3 = a4, d3 = a6, e3 = a5,
        )
        val _5 = Matrix5x3(
            scheme = """ 
                - - - - -
                - - - W -
                - - - - -
                         """,
            a1 = a1, b1 = a6, c1 = a1, d1 = a7, e1 = a3,
            a2 = a2, b2 = a7, c2 = a2, d2 = W, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a6, e3 = a5,
        )
        val _6 = Matrix5x3(
            scheme = """ 
                - - - - W
                - - - - -
                W - - - -
                         """,
            a1 = a1, b1 = a6, c1 = a1, d1 = a7, e1 = W,
            a2 = a2, b2 = a7, c2 = a2, d2 = a3, e2 = a4,
            a3 = W, b3 = a8, c3 = a4, d3 = a6, e3 = a5,
        )
        val _7 = Matrix5x3(
            scheme = """ 
                - - - - -
                - - - - -
                - - W - -
                         """,
            a1 = a1, b1 = a6, c1 = a1, d1 = a7, e1 = a3,
            a2 = a2, b2 = a7, c2 = a2, d2 = a8, e2 = a4,
            a3 = a3, b3 = a4, c3 = W, d3 = a6, e3 = a5,
        )
        val _8 = Matrix5x3(
            scheme = """ 
                - W - - -
                - - - - -
                - - - - W
                         """,
            a1 = a1, b1 = W, c1 = a3, d1 = a7, e1 = a3,
            a2 = a5, b2 = a7, c2 = a2, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a6, e3 = W,
        )
        val _9 = Matrix5x3(
            scheme = """ 
                - - - - -
                - - - - -
                - - - - W
                         """,
            a1 = a1, b1 = a3, c1 = a3, d1 = a7, e1 = a3,
            a2 = a5, b2 = a7, c2 = a2, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a6, e3 = W,
        )

        override val matrixList = listOf<Matrix5x3>(_1, _2, _3, _4, _5, _6, _7, _8, _9)
    }

    object WinMonochrome: CombinationMatrix5x3 {
        val _1 = Matrix5x3(
            scheme = """ 
                1 1 1 1 1
                - - - - -
                - - - - -
                         """,
            winItemList = listOf(a1),
            a1 = a1, b1 = a1, c1 = a1, d1 = a1, e1 = a1,
            a2 = a5, b2 = a7, c2 = a2, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a6, e3 = a7,
        )
        val _2 = Matrix5x3(
            scheme = """ 
                1 - - - -
                - 1 - - -
                - - 1 - -
                         """,
            winItemList = listOf(a1),
            a1 = a1, b1 = a4, c1 = a3, d1 = a5, e1 = a7,
            a2 = a5, b2 = a1, c2 = a2, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a1, d3 = a6, e3 = a7,
        )
        val _3 = Matrix5x3(
            scheme = """ 
                - 1 - - -
                - - 1 - -
                - - - 1 -
                         """,
            winItemList = listOf(a1),
            a1 = a2, b1 = a1, c1 = a3, d1 = a5, e1 = a7,
            a2 = a5, b2 = a4, c2 = a1, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a8, d3 = a1, e3 = a7,
        )
        val _4 = Matrix5x3(
            scheme = """ 
                - - 1 - -
                - - - 1 -
                - - - - 1
                         """,
            winItemList = listOf(a1),
            a1 = a2, b1 = a5, c1 = a1, d1 = a5, e1 = a7,
            a2 = a5, b2 = a4, c2 = a6, d2 = a1, e2 = a4,
            a3 = a3, b3 = a8, c3 = a8, d3 = a7, e3 = a1,
        )
        val _5 = Matrix5x3(
            scheme = """ 
                - - 1 - -
                - 1 - 1 -
                1 - - - 1
                         """,
            winItemList = listOf(a1),
            a1 = a2, b1 = a5, c1 = a1, d1 = a5, e1 = a7,
            a2 = a5, b2 = a1, c2 = a6, d2 = a1, e2 = a4,
            a3 = a1, b3 = a8, c3 = a8, d3 = a7, e3 = a1,
        )
        val _6 = Matrix5x3(
            scheme = """ 
                - - 1 - -
                - 1 - - -
                1 - - - -
                         """,
            winItemList = listOf(a1),
            a1 = a2, b1 = a5, c1 = a1, d1 = a5, e1 = a7,
            a2 = a5, b2 = a1, c2 = a6, d2 = a2, e2 = a4,
            a3 = a1, b3 = a8, c3 = a8, d3 = a7, e3 = a5,
        )
        val _7 = Matrix5x3(
            scheme = """ 
                - - 1 - 1
                - 1 - 1 -
                1 - 1 - -
                         """,
            winItemList = listOf(a1),
            a1 = a2, b1 = a5, c1 = a1, d1 = a5, e1 = a1,
            a2 = a5, b2 = a1, c2 = a6, d2 = a1, e2 = a4,
            a3 = a1, b3 = a8, c3 = a1, d3 = a7, e3 = a5,
        )
        override val matrixList = listOf<Matrix5x3>(_1, _2, _3, _4, _5, _6, _7)
    }

    object WinMonochromeWithWild: CombinationMatrix5x3 {
        val _1 = Matrix5x3(
            scheme = """ 
                W - - - W
                - 1 - 1 -
                - - W - -
                         """,
            winItemList = listOf(W, a1),
            a1 = W, b1 = a5, c1 = a3, d1 = a5, e1 = W,
            a2 = a4, b2 = a1, c2 = a6, d2 = a1, e2 = a4,
            a3 = a2, b3 = a8, c3 = W, d3 = a7, e3 = a5,
        )
        val _2 = Matrix5x3(
            scheme = """ 
                1 - W - 1
                - 1 - 1 -
                - - - - -
                         """,
            winItemList = listOf(W, a1),
            a1 = a1, b1 = a5, c1 = W, d1 = a3, e1 = a1,
            a2 = a3, b2 = a1, c2 = a6, d2 = a1, e2 = a4,
            a3 = a2, b3 = a8, c3 = a2, d3 = a7, e3 = a5,
        )
        val _3 = Matrix5x3(
            scheme = """ 
                1 - - - -
                - 1 W 1 -
                - - - - 1
                         """,
            winItemList = listOf(W, a1),
            a1 = a1, b1 = a5, c1 = a6, d1 = a3, e1 = a4,
            a2 = a3, b2 = a1, c2 = W, d2 = a1, e2 = a4,
            a3 = a2, b3 = a8, c3 = a2, d3 = a7, e3 = a1,
        )
        val _4 = Matrix5x3(
            scheme = """ 
                - - - - 1
                - 1 W 1 -
                W 1 - - 1
                         """,
            winItemList = listOf(W, a1),
            a1 = a2, b1 = a5, c1 = a6, d1 = a3, e1 = a1,
            a2 = a3, b2 = a1, c2 = W, d2 = a1, e2 = a4,
            a3 = W, b3 = a1, c3 = a2, d3 = a7, e3 = a1,
        )
        val _5 = Matrix5x3(
            scheme = """ 
                W 1 1 1 W
                1 1 W 1 1
                W 1 1 1 W
                         """,
            winItemList = listOf(W, a1),
            a1 = W, b1 = a1, c1 = a1, d1 = a1, e1 = W,
            a2 = a1, b2 = a1, c2 = W, d2 = a1, e2 = a1,
            a3 = W, b3 = a1, c3 = a1, d3 = a1, e3 = W,
        )

        override val matrixList = listOf<Matrix5x3>(_1, _2, _3, _4, _5)
    }

    object WinColorful: CombinationMatrix5x3 {
        val _1 = Matrix5x3(
            scheme = """ 
                1 - 2 - -
                1 - 2 - -
                1 - 2 - -
                         """,
            winItemList = listOf(a1, a2),
            a1 = a1, b1 = a5, c1 = a2, d1 = a7, e1 = a3,
            a2 = a1, b2 = a4, c2 = a2, d2 = a6, e2 = a4,
            a3 = a1, b3 = a3, c3 = a2, d3 = a5, e3 = a8,
        )
        val _2 = Matrix5x3(
            scheme = """ 
                1 - 2 - 3
                1 - 2 - 3
                1 - 2 - 3
                         """,
            winItemList = listOf(a1, a2, a3),
            a1 = a1, b1 = a5, c1 = a2, d1 = a7, e1 = a3,
            a2 = a1, b2 = a4, c2 = a2, d2 = a6, e2 = a3,
            a3 = a1, b3 = a8, c3 = a2, d3 = a5, e3 = a3,
        )

        override val matrixList = listOf<Matrix5x3>(_1, _2)
    }

    object WinColorfulWithWild: CombinationMatrix5x3 {
        val _1 = Matrix5x3(
            scheme = """ 
                1 2 2 - -
                1 W 2 - -
                1 1 2 - -
                         """,
            winItemList = listOf(a1, a2, W),
            a1 = a1, b1 = a2, c1 = a2, d1 = a7, e1 = a3,
            a2 = a1, b2 = W, c2 = a2, d2 = a6, e2 = a4,
            a3 = a1, b3 = a1, c3 = a2, d3 = a5, e3 = a8,
        )
        val _2 = Matrix5x3(
            scheme = """ 
                - 1 - 2 -
                - 2 W 1 -
                - 1 - 2 -
                         """,
            winItemList = listOf(a1, a2, W),
            a1 = a3, b1 = a1, c1 = a6, d1 = a2, e1 = a6,
            a2 = a4, b2 = a2, c2 = W, d2 = a1, e2 = a7,
            a3 = a5, b3 = a1, c3 = a5, d3 = a2, e3 = a8,
        )
        val _3 = Matrix5x3(
            scheme = """ 
                1 2 3 4 5
                W 6 W 7 W
                1 2 3 4 5
                         """,
            winItemList = listOf(a1, a2, a3, a4, a5, a6, a7, W),
            a1 = a1, b1 = a2, c1 = a3, d1 = a4, e1 = a5,
            a2 = W, b2 = a6, c2 = W, d2 = a7, e2 = W,
            a3 = a1, b3 = a2, c3 = a3, d3 = a4, e3 = a5,
        )

        override val matrixList = listOf<Matrix5x3>(_1, _2, _3)
    }

}