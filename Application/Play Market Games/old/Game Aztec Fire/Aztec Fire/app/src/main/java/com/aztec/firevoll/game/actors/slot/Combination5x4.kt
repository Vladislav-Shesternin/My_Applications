package com.aztec.firevoll.game.actors.slot

import com.aztec.firevoll.game.actors.slot.Matrix5x4.Item.*

interface CombinationMatrix5x4 {
    val matrixList: List<Matrix5x4>
}

object Combination5x4 {

    object Mix : CombinationMatrix5x4 {
        val _1 = Matrix5x4(
            a1 = a1, b1 = a6, c1 = a2, d1 = a7, e1 = a3,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
        )
        val _2 = Matrix5x4(
            a1 = a2, b1 = a6, c1 = a3, d1 = a7, e1 = a4,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
        )
        val _3 = Matrix5x4(
            a1 = a1, b1 = a6, c1 = a2, d1 = a7, e1 = a3,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
        )
        val _4 = Matrix5x4(
            a1 = a1, b1 = a6, c1 = a2, d1 = a7, e1 = a3,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a9, c3 = a4, d3 = a8, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
        )
        val _5 = Matrix5x4(
            a1 = a1, b1 = a6, c1 = a2, d1 = a7, e1 = a3,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a7, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
        )

        override val matrixList = listOf<Matrix5x4>(_1, _2, _3, _4, _5)
    }

    object MixWithWild: CombinationMatrix5x4 {
        val _1 = Matrix5x4(
            scheme = """ 
                W - - - -
                - - - - -
                - - - - -
                - - - - -""",
            a1 = W, b1 = a6, c1 = a2, d1 = a7, e1 = a3,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
        )
        val _2 = Matrix5x4(
            scheme = """ 
                - - W - -
                - - - - -
                - - - - -
                - - - - -""",
            a1 = a1, b1 = a6, c1 = W, d1 = a7, e1 = a3,
            a2 = a2, b2 = a4, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
        )
        val _3 = Matrix5x4(
            scheme = """
                 - - - - W
                 - - - - -
                 - - - - -
                 - - - - -""",
            a1 = a1, b1 = a6, c1 = a2, d1 = a7, e1 = W,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
        )
        val _4 = Matrix5x4(
            scheme = """ 
                - - - - -
                - W - - -
                - - - - -
                - - - - -""",
            a1 = a1, b1 = a6, c1 = a5, d1 = a7, e1 = a3,
            a2 = a2, b2 = W, c2 = a3, d2 = a8, e2 = a4,
            a3 = a9, b3 = a8, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
        )
        val _5 = Matrix5x4(
            scheme = """ 
                - - - - -
                - - - W -
                - - - - -
                - - - - -""",
            a1 = a1, b1 = a6, c1 = a2, d1 = a7, e1 = a1,
            a2 = a2, b2 = a7, c2 = a3, d2 = W, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
        )
        val _6 = Matrix5x4(
            scheme = """ 
                - - - - -
                - - W - -
                - - - - -
                - - - - -""",
            a1 = a1, b1 = a6, c1 = a2, d1 = a1, e1 = a3,
            a2 = a2, b2 = a7, c2 = W, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
        )
        val _7 = Matrix5x4(
            scheme = """
                - - - - -
                - - - - -
                - - W - -
                - - - - -""",
            a1 = a1, b1 = a6, c1 = a2, d1 = a7, e1 = a3,
            a2 = a2, b2 = a7, c2 = a3, d2 = a6, e2 = a4,
            a3 = a3, b3 = a8, c3 = W, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
        )
        val _8 = Matrix5x4(
            scheme = """ 
                - - - - -
                - - - - -
                W - - - W
                - - - - -""",
            a1 = a1, b1 = a6, c1 = a2, d1 = a7, e1 = a3,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = W, b3 = a8, c3 = a5, d3 = a9, e3 = W,
            a4 = a4, b4 = a9, c4 = a5, d4 = a1, e4 = a6,
        )
        val _9 = Matrix5x4(
            scheme = """ 
                - - - - -
                - - - - -
                - - - - -
                - - W - -""",
            a1 = a1, b1 = a6, c1 = a2, d1 = a7, e1 = a3,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a2, d3 = a7, e3 = a5,
            a4 = a4, b4 = a9, c4 = W, d4 = a1, e4 = a6,
        )

        override val matrixList = listOf<Matrix5x4>(_1, _2, _3, _4, _5, _6, _7, _8, _9)
    }

    object WinMonochrome: CombinationMatrix5x4 {
        val _1 = Matrix5x4(
            scheme = """ 
                1 1 1 1 1
                - - - - -
                - - - - -
                - - - - -""",
            winItemList = listOf(a1),
            a1 = a1, b1 = a1, c1 = a1, d1 = a1, e1 = a1,
            a2 = a2, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a7, e4 = a6,
        )
        val _2 = Matrix5x4(
            scheme = """ 
                - - - - -
                1 1 1 1 1
                - - - - -
                - - - - -""",
            winItemList = listOf(a1),
            a1 = a2, b1 = a7, c1 = a3, d1 = a8, e1 = a4,
            a2 = a1, b2 = a1, c2 = a1, d2 = a1, e2 = a1,
            a3 = a3, b3 = a8, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a5, d4 = a7, e4 = a6,
        )
        val _3 = Matrix5x4(
            scheme = """ 
                - - - - -
                - - - - -
                1 1 1 1 1
                - - - - -""",
            winItemList = listOf(a1),
            a1 = a2, b1 = a7, c1 = a3, d1 = a8, e1 = a4,
            a2 = a3, b2 = a8, c2 = a4, d2 = a9, e2 = a5,
            a3 = a1, b3 = a1, c3 = a1, d3 = a1, e3 = a1,
            a4 = a4, b4 = a9, c4 = a5, d4 = a7, e4 = a6,
        )
        val _4 = Matrix5x4(
            scheme = """ 
                - - - - -
                - - - - -
                - - - - -
                1 1 1 1 1""",
            winItemList = listOf(a1),
            a1 = a2, b1 = a7, c1 = a3, d1 = a8, e1 = a4,
            a2 = a3, b2 = a8, c2 = a4, d2 = a9, e2 = a5,
            a3 = a4, b3 = a9, c3 = a5, d3 = a7, e3 = a6,
            a4 = a1, b4 = a1, c4 = a1, d4 = a1, e4 = a1,
        )
        override val matrixList = listOf<Matrix5x4>(_1, _2, _3, _4)
    }

    object WinMonochromeWithWild: CombinationMatrix5x4 {
        val _1 = Matrix5x4(
            scheme = """
                 W 1 1 1 1
                 - 1 - - -
                 - - 1 - -
                 - - 1 - -""",
            winItemList = listOf(W, a1),
            a1 = W, b1 = a1, c1 = a1, d1 = a1, e1 = a1,
            a2 = a3, b2 = a1, c2 = a5, d2 = a7, e2 = a5,
            a3 = a4, b3 = a2, c3 = a1, d3 = a4, e3 = a6,
            a4 = a3, b4 = a3, c4 = a1, d4 = a2, e4 = a9,
        )
        val _2 = Matrix5x4(
            scheme = """
                 W 1 1 - -
                 - 1 1 - -
                 - - - - -
                 - - - - -""",
            winItemList = listOf(W, a1),
            a1 = W, b1 = a1, c1 = a1, d1 = a4, e1 = a8,
            a2 = a3, b2 = a1, c2 = a1, d2 = a7, e2 = a5,
            a3 = a4, b3 = a2, c3 = a6, d3 = a4, e3 = a6,
            a4 = a3, b4 = a3, c4 = a6, d4 = a2, e4 = a9,
        )
        val _3 = Matrix5x4(
            scheme = """ 
                - - - - W
                - 1 - 1 -
                - - 1 - -
                W 1 - - -""",
            winItemList = listOf(W, a1),
            a1 = a2, b1 = a4, c1 = a5, d1 = a9, e1 = W,
            a2 = a3, b2 = a1, c2 = a5, d2 = a1, e2 = a5,
            a3 = a4, b3 = a2, c3 = a1, d3 = a4, e3 = a6,
            a4 = W, b4 = a1, c4 = a6, d4 = a2, e4 = a9,
        )
        val _4 = Matrix5x4(
            scheme = """ 
                - - - - -
                - W - - -
                - - 1 - -
                - - 1 - -""",
            winItemList = listOf(W, a1),
            a1 = a2, b1 = a3, c1 = a5, d1 = a8, e1 = a9,
            a2 = a3, b2 = W, c2 = a5, d2 = a7, e2 = a5,
            a3 = a4, b3 = a2, c3 = a1, d3 = a4, e3 = a6,
            a4 = a3, b4 = a3, c4 = a1, d4 = a2, e4 = a9,
        )
        val _5 = Matrix5x4(
            scheme = """
                 - - - - -
                 - - - - -
                 - - 1 1 1
                 - - 1 W W""",
            winItemList = listOf(W, a1),
            a1 = a2, b1 = a7, c1 = a8, d1 = a9, e1 = a3,
            a2 = a3, b2 = a4, c2 = a5, d2 = a7, e2 = a5,
            a3 = a4, b3 = a2, c3 = a1, d3 = a1, e3 = a1,
            a4 = a3, b4 = a3, c4 = a1, d4 = W, e4 = W,
        )
        val _6 = Matrix5x4(
            scheme = """
                 - 1 - - -
                 1 1 - - -
                 - 1 1 1 1
                 W 1 1 W W""",
            winItemList = listOf(W, a1),
            a1 = a2, b1 = a1, c1 = a8, d1 = a9, e1 = a3,
            a2 = a1, b2 = a1, c2 = a5, d2 = a7, e2 = a5,
            a3 = a4, b3 = a1, c3 = a1, d3 = a1, e3 = a1,
            a4 = W, b4 = a1, c4 = a1, d4 = W, e4 = W,
        )
        val _7 = Matrix5x4(
            scheme = """ 
                W 1 - 1 W
                1 1 - 1 1
                - - 1 - -
                1 1 - 1 1""",
            winItemList = listOf(W, a1),
            a1 = W, b1 = a1, c1 = a8, d1 = a1, e1 = W,
            a2 = a1, b2 = a1, c2 = a5, d2 = a1, e2 = a1,
            a3 = a4, b3 = a5, c3 = a1, d3 = a4, e3 = a4,
            a4 = a1, b4 = a1, c4 = a9, d4 = a1, e4 = a1,
        )

        override val matrixList = listOf<Matrix5x4>(_1, _2, _3, _4, _5, _6, _7)
    }

    object WinColorful: CombinationMatrix5x4 {
        val _1 = Matrix5x4(
            scheme = """ 
                1 1 1 1 1
                - - - - -
                2 2 2 2 2
                - - - - -""",
            winItemList = listOf(a1, a2),
            a1 = a1, b1 = a1, c1 = a1, d1 = a1, e1 = a1,
            a2 = a6, b2 = a7, c2 = a3, d2 = a8, e2 = a4,
            a3 = a2, b3 = a2, c3 = a2, d3 = a2, e3 = a2,
            a4 = a4, b4 = a9, c4 = a5, d4 = a7, e4 = a6,
        )
        val _2 = Matrix5x4(
            scheme = """ 
                - - 1 1 1
                1 1 1 - -
                - - - - -
                - - 2 2 2""",
            winItemList = listOf(a1, a2),
            a1 = a3, b1 = a4, c1 = a1, d1 = a1, e1 = a1,
            a2 = a1, b2 = a1, c2 = a1, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a2, d4 = a2, e4 = a2,
        )

        override val matrixList = listOf<Matrix5x4>(_1, _2)
    }

    object WinColorfulWithWild: CombinationMatrix5x4 {
        val _1 = Matrix5x4(
            scheme = """ 
                W W 1 1 1
                1 1 1 - -
                - - - - -
                - - 2 2 2""",
            winItemList = listOf(W, a1, a2),
            a1 = W, b1 = W, c1 = a1, d1 = a1, e1 = a1,
            a2 = a1, b2 = a1, c2 = a1, d2 = a8, e2 = a4,
            a3 = a3, b3 = a8, c3 = a4, d3 = a9, e3 = a5,
            a4 = a4, b4 = a9, c4 = a2, d4 = a2, e4 = a2,
        )
        val _2 = Matrix5x4(
            scheme = """ 
                1 1 - - -
                W 1 - - -
                1 1 - 2 2
                - - - 2 2""",
            winItemList = listOf(W, a1, a2),
            a1 = a1, b1 = a1, c1 = a7, d1 = a9, e1 = a7,
            a2 = W, b2 = a1, c2 = a3, d2 = a8, e2 = a4,
            a3 = a1, b3 = a1, c3 = a4, d3 = a2, e3 = a2,
            a4 = a4, b4 = a9, c4 = a5, d4 = a2, e4 = a2,
        )
        val _3 = Matrix5x4(
            scheme = """ 
                - 1 W 1 -
                - 1 1 1 -
                - - - - -
                - 2 2 2 -""",
            winItemList = listOf(W, a1, a2),
            a1 = a3, b1 = a1, c1 = W, d1 = a1, e1 = a7,
            a2 = a5, b2 = a1, c2 = a1, d2 = a1, e2 = a4,
            a3 = a6, b3 = a7, c3 = a4, d3 = a7, e3 = a8,
            a4 = a4, b4 = a2, c4 = a2, d4 = a2, e4 = a7,
        )

        override val matrixList = listOf<Matrix5x4>(_1, _2, _3)
    }

}