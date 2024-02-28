package com.fortunetiger.bigwin.game.actors.slots.slot6x5

interface ICombinationMatrix6x5 {
    val matrixList: List<Matrix6x5>
}

/**
 *
 * ITEM index = 0..9
 * WILD index = not
 *
 * */
object Combination6x5 {

    object Mix : ICombinationMatrix6x5 {
        val _1 = Matrix6x5(
            a1 = 1, b1 = 6, c1 = 1, d1 = 6, e1 = 1, f1 = 1,
            a2 = 2, b2 = 7, c2 = 2, d2 = 7, e2 = 2, f2 = 2,
            a3 = 3, b3 = 8, c3 = 3, d3 = 8, e3 = 3, f3 = 3,
            a4 = 4, b4 = 9, c4 = 4, d4 = 9, e4 = 4, f4 = 0,
            a5 = 5, b5 = 0, c5 = 5, d5 = 0, e5 = 2, f5 = 5,
        )
        val _2 = Matrix6x5(
            a1 = 1, b1 = 1, c1 = 0, d1 = 6, e1 = 2, f1 = 0,
            a2 = 3, b2 = 7, c2 = 2, d2 = 7, e2 = 2, f2 = 7,
            a3 = 3, b3 = 8, c3 = 1, d3 = 0, e3 = 3, f3 = 6,
            a4 = 4, b4 = 9, c4 = 4, d4 = 9, e4 = 4, f4 = 5,
            a5 = 5, b5 = 0, c5 = 1, d5 = 0, e5 = 4, f5 = 5,
        )
        val _3 = Matrix6x5(
            a1 = 7, b1 = 6, c1 = 1, d1 = 0, e1 = 2, f1 = 0,
            a2 = 2, b2 = 7, c2 = 4, d2 = 7, e2 = 2, f2 = 2,
            a3 = 3, b3 = 8, c3 = 3, d3 = 1, e3 = 3, f3 = 0,
            a4 = 2, b4 = 9, c4 = 4, d4 = 1, e4 = 2, f4 = 4,
            a5 = 5, b5 = 1, c5 = 5, d5 = 0, e5 = 5, f5 = 1,
        )
        val _4 = Matrix6x5(
            a1 = 0, b1 = 6, c1 = 1, d1 = 6, e1 = 1, f1 = 0,
            a2 = 2, b2 = 7, c2 = 2, d2 = 7, e2 = 2, f2 = 3,
            a3 = 1, b3 = 8, c3 = 0, d3 = 8, e3 = 3, f3 = 6,
            a4 = 4, b4 = 9, c4 = 4, d4 = 1, e4 = 2, f4 = 4,
            a5 = 0, b5 = 1, c5 = 5, d5 = 2, e5 = 0, f5 = 5,
        )
        val _5 = Matrix6x5(
            a1 = 1, b1 = 6, c1 = 5, d1 = 6, e1 = 1, f1 = 1,
            a2 = 2, b2 = 8, c2 = 2, d2 = 7, e2 = 2, f2 = 5,
            a3 = 3, b3 = 8, c3 = 1, d3 = 8, e3 = 4, f3 = 3,
            a4 = 4, b4 = 9, c4 = 4, d4 = 9, e4 = 1, f4 = 4,
            a5 = 5, b5 = 1, c5 = 0, d5 = 2, e5 = 5, f5 = 0,
        )

        override val matrixList = listOf<Matrix6x5>(_1,_2,_3,_4,_5)
    }

    object Win : ICombinationMatrix6x5 {
        val _1 = Matrix6x5(
            a1 = 1, b1 = 6, c1 = 5, d1 = 6, e1 = 2, f1 = 0,
            a2 = 1, b2 = 8, c2 = 2, d2 = 7, e2 = 2, f2 = 5,
            a3 = 1, b3 = 8, c3 = 1, d3 = 8, e3 = 3, f3 = 3,
            a4 = 1, b4 = 9, c4 = 4, d4 = 9, e4 = 4, f4 = 4,
            a5 = 1, b5 = 0, c5 = 0, d5 = 2, e5 = 5, f5 = 5,

            resultMatrix6x5 = ResultMatrix6x5(
                a1 = true, b1 = false, c1 = false, d1 = false, e1 = false, f1 = false,
                a2 = true, b2 = false, c2 = false, d2 = false, e2 = false, f2 = false,
                a3 = true, b3 = false, c3 = false, d3 = false, e3 = false, f3 = false,
                a4 = true, b4 = false, c4 = false, d4 = false, e4 = false, f4 = false,
                a5 = true, b5 = false, c5 = false, d5 = false, e5 = false, f5 = false,
            )
        )
        val _2 = Matrix6x5(
            a1 = 1, b1 = 2, c1 = 3, d1 = 6, e1 = 0, f1 = 1,
            a2 = 0, b2 = 2, c2 = 5, d2 = 7, e2 = 2, f2 = 5,
            a3 = 3, b3 = 2, c3 = 0, d3 = 8, e3 = 3, f3 = 3,
            a4 = 4, b4 = 2, c4 = 4, d4 = 9, e4 = 4, f4 = 2,
            a5 = 5, b5 = 2, c5 = 5, d5 = 1, e5 = 5, f5 = 5,

            resultMatrix6x5 = ResultMatrix6x5(
                a1 = false, b1 = true, c1 = false, d1 = false, e1 = false, f1 = false,
                a2 = false, b2 = true, c2 = false, d2 = false, e2 = false, f2 = false,
                a3 = false, b3 = true, c3 = false, d3 = false, e3 = false, f3 = false,
                a4 = false, b4 = true, c4 = false, d4 = false, e4 = false, f4 = false,
                a5 = false, b5 = true, c5 = false, d5 = false, e5 = false, f5 = false,
            )
        )
        val _3 = Matrix6x5(
            a1 = 1, b1 = 2, c1 = 0, d1 = 6, e1 = 0, f1 = 1,
            a2 = 0, b2 = 5, c2 = 0, d2 = 7, e2 = 2, f2 = 5,
            a3 = 3, b3 = 4, c3 = 0, d3 = 6, e3 = 9, f3 = 6,
            a4 = 4, b4 = 3, c4 = 0, d4 = 9, e4 = 4, f4 = 2,
            a5 = 5, b5 = 2, c5 = 0, d5 = 1, e5 = 5, f5 = 1,

            resultMatrix6x5 = ResultMatrix6x5(
                a1 = false, b1 = false, c1 = true, d1 = false, e1 = false, f1 = false,
                a2 = false, b2 = false, c2 = true, d2 = false, e2 = false, f2 = false,
                a3 = false, b3 = false, c3 = true, d3 = false, e3 = false, f3 = false,
                a4 = false, b4 = false, c4 = true, d4 = false, e4 = false, f4 = false,
                a5 = false, b5 = false, c5 = true, d5 = false, e5 = false, f5 = false,
            )
        )
        val _4 = Matrix6x5(
            a1 = 0, b1 = 2, c1 = 1, d1 = 0, e1 = 2, f1 = 1,
            a2 = 0, b2 = 5, c2 = 2, d2 = 0, e2 = 2, f2 = 5,
            a3 = 3, b3 = 4, c3 = 1, d3 = 0, e3 = 3, f3 = 6,
            a4 = 4, b4 = 3, c4 = 5, d4 = 0, e4 = 4, f4 = 0,
            a5 = 5, b5 = 2, c5 = 1, d5 = 0, e5 = 5, f5 = 1,

            resultMatrix6x5 = ResultMatrix6x5(
                a1 = false, b1 = false, c1 = false, d1 = true, e1 = false, f1 = false,
                a2 = false, b2 = false, c2 = false, d2 = true, e2 = false, f2 = false,
                a3 = false, b3 = false, c3 = false, d3 = true, e3 = false, f3 = false,
                a4 = false, b4 = false, c4 = false, d4 = true, e4 = false, f4 = false,
                a5 = false, b5 = false, c5 = false, d5 = true, e5 = false, f5 = false,
            )
        )
        val _5 = Matrix6x5(
            a1 = 0, b1 = 4, c1 = 2, d1 = 7, e1 = 0, f1 = 2,
            a2 = 3, b2 = 5, c2 = 2, d2 = 8, e2 = 0, f2 = 5,
            a3 = 1, b3 = 4, c3 = 1, d3 = 9, e3 = 0, f3 = 6,
            a4 = 5, b4 = 3, c4 = 5, d4 = 2, e4 = 0, f4 = 2,
            a5 = 1, b5 = 2, c5 = 6, d5 = 1, e5 = 0, f5 = 5,

            resultMatrix6x5 = ResultMatrix6x5(
                a1 = false, b1 = false, c1 = false, d1 = false, e1 = true, f1 = false,
                a2 = false, b2 = false, c2 = false, d2 = false, e2 = true, f2 = false,
                a3 = false, b3 = false, c3 = false, d3 = false, e3 = true, f3 = false,
                a4 = false, b4 = false, c4 = false, d4 = false, e4 = true, f4 = false,
                a5 = false, b5 = false, c5 = false, d5 = false, e5 = true, f5 = false,
            )
        )
        val _6 = Matrix6x5(
            a1 = 0, b1 = 4, c1 = 2, d1 = 7, e1 = 1, f1 = 0,
            a2 = 3, b2 = 5, c2 = 2, d2 = 8, e2 = 2, f2 = 0,
            a3 = 1, b3 = 4, c3 = 1, d3 = 9, e3 = 3, f3 = 0,
            a4 = 5, b4 = 3, c4 = 5, d4 = 9, e4 = 4, f4 = 0,
            a5 = 1, b5 = 2, c5 = 6, d5 = 1, e5 = 5, f5 = 0,

            resultMatrix6x5 = ResultMatrix6x5(
                a1 = false, b1 = false, c1 = false, d1 = false, e1 = false, f1 = true,
                a2 = false, b2 = false, c2 = false, d2 = false, e2 = false, f2 = true,
                a3 = false, b3 = false, c3 = false, d3 = false, e3 = false, f3 = true,
                a4 = false, b4 = false, c4 = false, d4 = false, e4 = false, f4 = true,
                a5 = false, b5 = false, c5 = false, d5 = false, e5 = false, f5 = true,
            )
        )

        override val matrixList = listOf<Matrix6x5>(_1,_2,_3,_4,_5, _6)
    }

    /*object MixWithWild: CombinationMatrix5x3 {
        val _1 = Matrix5x3(
            scheme = """
                W - - - - -
                - - - - - -
                - - - - - -
                - - - - - -""",
            a1 = W, b1 = a6, c1 = a2, d1 = a6, e1 = a2,
            a2 = a2, b2 = a7, c2 = a3, d2 = a7, e2 = a3,
            a3 = a3, b3 = a8, c3 = a4, d3 = a8, e3 = a4,
        )
        val _2 = Matrix5x3(
            scheme = """
                - - - W - -
                - - - - - -
                - - - - - -
                - - - - - -""",
            a1 = a1, b1 = a6, c1 = a2, d1 = W, e1 = a2,
            a2 = a2, b2 = a7, c2 = a3, d2 = a7, e2 = a3,
            a3 = a3, b3 = a8, c3 = a4, d3 = a8, e3 = a4,
        )
        val _3 = Matrix5x3(
            scheme = """
                - - - - - -
                - W - - - -
                - - - - - -
                - - - - - -""",
            a1 = a7, b1 = a6, c1 = a2, d1 = a6, e1 = a2,
            a2 = a1, b2 = W, c2 = a3, d2 = a7, e2 = a3,
            a3 = a3, b3 = a8, c3 = a4, d3 = a8, e3 = a4,
        )
        val _4 = Matrix5x3(
            scheme = """
                W - - - - -
                - - - - - -
                - - - - W -
                - - - - - -""",
            a1 = W, b1 = a6, c1 = a2, d1 = a6, e1 = a2,
            a2 = a2, b2 = a7, c2 = a3, d2 = a4, e2 = a3,
            a3 = a3, b3 = a8, c3 = a3, d3 = a8, e3 = W,
        )
        val _5 = Matrix5x3(
            scheme = """
                - W - - - -
                - - - - - -
                - - - - W -
                W - - - - -""",
            a1 = a1, b1 = W, c1 = a2, d1 = a6, e1 = a2,
            a2 = a2, b2 = a7, c2 = a3, d2 = a7, e2 = a3,
            a3 = a3, b3 = a8, c3 = a4, d3 = a8, e3 = W,
        )
        val _6 = Matrix5x3(
            scheme = """
                W - - - - W
                - - - - - -
                - - - - - -
                W - - - - W""",
            a1 = W, b1 = a6, c1 = a2, d1 = a6, e1 = a2,
            a2 = a2, b2 = a7, c2 = a3, d2 = a7, e2 = a3,
            a3 = a3, b3 = a8, c3 = a4, d3 = a8, e3 = a4,
        )

        override val matrixList = listOf<Matrix5x3>(_1, _2, _3, _4, _5, _6)
    }

    object WinMonochrome: CombinationMatrix5x3 {
        val _1 = Matrix5x3(
            scheme = """
                1 1 1 - - -
                - - - - - -
                - - - - - -
                - - - - - -""",
            winItemList = listOf(a1),
            a1 = a1, b1 = a1, c1 = a1, d1 = a6, e1 = a2,
            a2 = a2, b2 = a7, c2 = a3, d2 = a7, e2 = a3,
            a3 = a3, b3 = a8, c3 = a4, d3 = a8, e3 = a4,
        )
        val _2 = Matrix5x3(
            scheme = """
                1 1 1 - - -
                - - - 1 - -
                - - - - 1 -
                - - - - - 1""",
            winItemList = listOf(a1),
            a1 = a1, b1 = a1, c1 = a1, d1 = a6, e1 = a2,
            a2 = a2, b2 = a7, c2 = a3, d2 = a1, e2 = a3,
            a3 = a3, b3 = a8, c3 = a4, d3 = a8, e3 = a1,
        )
        val _3 = Matrix5x3(
            scheme = """
                1 1 1 1 1 1
                - - - 1 - -
                - - 1 1 1 1
                1 1 - - - -""",
            winItemList = listOf(a1),
            a1 = a1, b1 = a1, c1 = a1, d1 = a1, e1 = a1,
            a2 = a2, b2 = a7, c2 = a3, d2 = a1, e2 = a3,
            a3 = a3, b3 = a8, c3 = a1, d3 = a1, e3 = a1,
        )
        val _4 = Matrix5x3(
            scheme = """
                - - - - - -
                - - - - - -
                - - - - - -
                - 1 1 1 - -""",
            winItemList = listOf(a1),
            a1 = a2, b1 = a3, c1 = a4, d1 = a6, e1 = a2,
            a2 = a2, b2 = a7, c2 = a3, d2 = a7, e2 = a3,
            a3 = a3, b3 = a8, c3 = a4, d3 = a8, e3 = a4,
        )
        val _5 = Matrix5x3(
            scheme = """
                - - - - - -
                - - - - 1 1
                - - 1 1 1 1
                - - - - - -""",
            winItemList = listOf(a1),
            a1 = a2, b1 = a7, c1 = a4, d1 = a6, e1 = a2,
            a2 = a2, b2 = a7, c2 = a3, d2 = a7, e2 = a1,
            a3 = a3, b3 = a8, c3 = a1, d3 = a1, e3 = a1,
        )
        val _6 = Matrix5x3(
            scheme = """
                1 - - - - 1
                - 1 - - 1 -
                - 1 1 1 - 1
                - - - - - 1""",
            winItemList = listOf(a1),
            a1 = a1, b1 = a2, c1 = a3, d1 = a6, e1 = a2,
            a2 = a2, b2 = a1, c2 = a3, d2 = a7, e2 = a1,
            a3 = a3, b3 = a1, c3 = a1, d3 = a1, e3 = a4,
        )
        val _7 = Matrix5x3(
            scheme = """
                1 1 - - - -
                1 - - 1 1 1
                - - - - - -
                - 1 1 1 - -""",
            winItemList = listOf(a1),
            a1 = a1, b1 = a1, c1 = a3, d1 = a6, e1 = a2,
            a2 = a1, b2 = a7, c2 = a3, d2 = a1, e2 = a1,
            a3 = a3, b3 = a8, c3 = a4, d3 = a8, e3 = a4,
        )
        override val matrixList = listOf<Matrix5x3>(_1, _2, _3, _4, _5, _6, _7)
    }

    object WinMonochromeWithWild: CombinationMatrix5x3 {
        val _1 = Matrix5x3(
            scheme = """
                W - - - 1 -
                - 1 - 1 - -
                - - 1 - - -
                - - - - - -""",
            winItemList = listOf(a1, W),
            a1 = W, b1 = a5, c1 = a4, d1 = a6, e1 = a1,
            a2 = a2, b2 = a1, c2 = a3, d2 = a1, e2 = a3,
            a3 = a3, b3 = a8, c3 = a1, d3 = a8, e3 = a4,
        )
        val _2 = Matrix5x3(
            scheme = """
                W - - - 1 1
                - 1 - W - -
                - - 1 - 1 -
                - - 1 - - 1""",
            winItemList = listOf(a1, W),
            a1 = W, b1 = a5, c1 = a4, d1 = a6, e1 = a1,
            a2 = a2, b2 = a1, c2 = a3, d2 = W, e2 = a3,
            a3 = a3, b3 = a8, c3 = a1, d3 = a8, e3 = a1,
        )
        val _3 = Matrix5x3(
            scheme = """
                - - - - 1 -
                - - - 1 - -
                - - 1 - - -
                - W - - - -""",
            winItemList = listOf(a1, W),
            a1 = a2, b1 = a5, c1 = a4, d1 = a6, e1 = a1,
            a2 = a2, b2 = a4, c2 = a2, d2 = a1, e2 = a3,
            a3 = a3, b3 = a8, c3 = a1, d3 = a8, e3 = a4,
        )
        val _4 = Matrix5x3(
            scheme = """
                - - - - 1 1
                - - - 1 1 -
                1 1 1 1 - -
                1 W W 1 - -""",
            winItemList = listOf(a1, W),
            a1 = a2, b1 = a5, c1 = a4, d1 = a6, e1 = a1,
            a2 = a2, b2 = a4, c2 = a2, d2 = a1, e2 = a1,
            a3 = a1, b3 = a1, c3 = a1, d3 = a1, e3 = a2,
        )
        val _5 = Matrix5x3(
            scheme = """
                - 1 - - 1 -
                - - 1 1 - -
                W 1 1 1 1 W
                - - - - - -""",
            winItemList = listOf(a1, W),
            a1 = a5, b1 = a1, c1 = a4, d1 = a6, e1 = a1,
            a2 = a2, b2 = a4, c2 = a1, d2 = a1, e2 = a3,
            a3 = W, b3 = a1, c3 = a1, d3 = a1, e3 = a1,
        )

        override val matrixList = listOf<Matrix5x3>(_1, _2, _3, _4, _5)
    }

    object WinColorful: CombinationMatrix5x3 {
        val _1 = Matrix5x3(
            scheme = """
                1 1 - - - -
                1 1 - - - -
                - - - - 2 2
                - - - - 2 2""",
            winItemList = listOf(a1, a2),
            a1 = a1, b1 = a1, c1 = a4, d1 = a6, e1 = a2,
            a2 = a1, b2 = a1, c2 = a3, d2 = a7, e2 = a2,
            a3 = a3, b3 = a8, c3 = a6, d3 = a8, e3 = a2,
        )
        val _2 = Matrix5x3(
            scheme = """
                - - 1 1 1 -
                - - 1 - 1 -
                - - 2 - 2 -
                - - 2 2 2 -""",
            winItemList = listOf(a1, a2),
            a1 = a4, b1 = a7, c1 = a1, d1 = a1, e1 = a1,
            a2 = a5, b2 = a6, c2 = a1, d2 = a7, e2 = a1,
            a3 = a3, b3 = a8, c3 = a2, d3 = a2, e3 = a2,
        )

        override val matrixList = listOf<Matrix5x3>(_1, _2)
    }

    object WinColorfulWithWild: CombinationMatrix5x3 {
        val _1 = Matrix5x3(
            scheme = """
                1 1 6 5 4 4
                1 1 W W 4 4
                3 3 W W 2 2
                3 3 6 5 2 2""",
            winItemList = listOf(a1, a2, a3, a4, a5, a6, W),
            a1 = a1, b1 = a1, c1 = a6, d1 = a5, e1 = a4,
            a2 = a1, b2 = a1, c2 = W, d2 = W, e2 = a4,
            a3 = a3, b3 = a3, c3 = W, d3 = W, e3 = a2,
        )
        val _2 = Matrix5x3(
            scheme = """
                W 2 W 2 8 8
                1 W 3 3 8 6
                1 7 4 4 W 6
                7 7 5 W 5 W""",
            winItemList = listOf(a1, a2, a3, a4, a5, a6, a7, a8, W),
            a1 = W, b1 = a2, c1 = W, d1 = a2, e1 = a8,
            a2 = a1, b2 = W, c2 = a3, d2 = a3, e2 = a8,
            a3 = a1, b3 = a7, c3 = a4, d3 = a4, e3 = W,
        )

        override val matrixList = listOf<Matrix5x3>(_1, _2)
    }*/

}