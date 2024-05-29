package com.goplaytoday.guildofhero.game.actors.slots.slot6x5

interface ICombinationMatrix6x5 {
    val matrixList: List<Matrix6x5>
}
//WILD index = none
/**
 *
 * ITEM index = 0..8
 *
 * */
object Combination6x5 {

    object Mix : ICombinationMatrix6x5 {
        val _1 = Matrix6x5(
            a1 = 0, b1 = 2, c1 = 3, d1 = 1, e1 = 4, f1 = 1,
            a2 = 0, b2 = 1, c2 = 8, d2 = 7, e2 = 5, f2 = 6,
            a3 = 2, b3 = 2, c3 = 3, d3 = 4, e3 = 6, f3 = 8,
            a4 = 7, b4 = 6, c4 = 5, d4 = 2, e4 = 2, f4 = 1,
            a5 = 8, b5 = 8, c5 = 1, d5 = 0, e5 = 4, f5 = 3,
        )
        val _2 = Matrix6x5(
            a1 = 1, b1 = 2, c1 = 3, d1 = 4, e1 = 5, f1 = 6,
            a2 = 6, b2 = 5, c2 = 4, d2 = 3, e2 = 2, f2 = 1,
            a3 = 8, b3 = 7, c3 = 1, d3 = 1, e3 = 7, f3 = 8,
            a4 = 3, b4 = 4, c4 = 5, d4 = 5, e4 = 4, f4 = 3,
            a5 = 0, b5 = 2, c5 = 0, d5 = 8, e5 = 2, f5 = 0,
        )
        val _3 = Matrix6x5(
            a1 = 1, b1 = 4, c1 = 1, d1 = 3, e1 = 5, f1 = 1,
            a2 = 2, b2 = 0, c2 = 2, d2 = 0, e2 = 4, f2 = 0,
            a3 = 3, b3 = 8, c3 = 3, d3 = 8, e3 = 3, f3 = 8,
            a4 = 4, b4 = 7, c4 = 4, d4 = 7, e4 = 2, f4 = 7,
            a5 = 5, b5 = 6, c5 = 5, d5 = 6, e5 = 1, f5 = 0,
        )

        override val matrixList = listOf<Matrix6x5>(_1,_2,_3)
    }

    object Win : ICombinationMatrix6x5 {
        val _1 = Matrix6x5(
            a1 = 1, b1 = 4, c1 = 1, d1 = 3, e1 = 5, f1 = 1,
            a2 = 1, b2 = 0, c2 = 2, d2 = 0, e2 = 4, f2 = 0,
            a3 = 1, b3 = 8, c3 = 3, d3 = 8, e3 = 3, f3 = 8,
            a4 = 1, b4 = 7, c4 = 4, d4 = 7, e4 = 2, f4 = 7,
            a5 = 1, b5 = 6, c5 = 5, d5 = 6, e5 = 1, f5 = 0,

            resultMatrix6x5 = ResultMatrix6x5(
                a1 = true, b1 = false, c1 = false, d1 = false, e1 = false, f1 = false,
                a2 = true, b2 = false, c2 = false, d2 = false, e2 = false, f2 = false,
                a3 = true, b3 = false, c3 = false, d3 = false, e3 = false, f3 = false,
                a4 = true, b4 = false, c4 = false, d4 = false, e4 = false, f4 = false,
                a5 = true, b5 = false, c5 = false, d5 = false, e5 = false, f5 = false,
            )
        )
        val _2 = Matrix6x5(
            a1 = 4, b1 = 1, c1 = 8, d1 = 3, e1 = 5, f1 = 1,
            a2 = 0, b2 = 1, c2 = 2, d2 = 0, e2 = 4, f2 = 0,
            a3 = 8, b3 = 1, c3 = 3, d3 = 8, e3 = 3, f3 = 8,
            a4 = 7, b4 = 1, c4 = 4, d4 = 7, e4 = 2, f4 = 7,
            a5 = 6, b5 = 1, c5 = 5, d5 = 6, e5 = 1, f5 = 0,

            resultMatrix6x5 = ResultMatrix6x5(
                a1 = false, b1 = true, c1 = false, d1 = false, e1 = false, f1 = false,
                a2 = false, b2 = true, c2 = false, d2 = false, e2 = false, f2 = false,
                a3 = false, b3 = true, c3 = false, d3 = false, e3 = false, f3 = false,
                a4 = false, b4 = true, c4 = false, d4 = false, e4 = false, f4 = false,
                a5 = false, b5 = true, c5 = false, d5 = false, e5 = false, f5 = false,
            )
        )
        val _3 = Matrix6x5(
            a1 = 4, b1 = 8, c1 = 1, d1 = 3, e1 = 5, f1 = 1,
            a2 = 0, b2 = 2, c2 = 1, d2 = 0, e2 = 4, f2 = 0,
            a3 = 8, b3 = 3, c3 = 1, d3 = 8, e3 = 3, f3 = 8,
            a4 = 7, b4 = 4, c4 = 1, d4 = 7, e4 = 2, f4 = 7,
            a5 = 6, b5 = 5, c5 = 1, d5 = 6, e5 = 1, f5 = 0,

            resultMatrix6x5 = ResultMatrix6x5(
                a1 = false, b1 = false, c1 = true, d1 = false, e1 = false, f1 = false,
                a2 = false, b2 = false, c2 = true, d2 = false, e2 = false, f2 = false,
                a3 = false, b3 = false, c3 = true, d3 = false, e3 = false, f3 = false,
                a4 = false, b4 = false, c4 = true, d4 = false, e4 = false, f4 = false,
                a5 = false, b5 = false, c5 = true, d5 = false, e5 = false, f5 = false,
            )
        )
        val _4 = Matrix6x5(
            a1 = 4, b1 = 8, c1 = 3, d1 = 1, e1 = 5, f1 = 1,
            a2 = 0, b2 = 2, c2 = 0, d2 = 1, e2 = 4, f2 = 0,
            a3 = 8, b3 = 3, c3 = 8, d3 = 1, e3 = 3, f3 = 8,
            a4 = 7, b4 = 4, c4 = 7, d4 = 1, e4 = 2, f4 = 7,
            a5 = 6, b5 = 5, c5 = 6, d5 = 1, e5 = 8, f5 = 0,

            resultMatrix6x5 = ResultMatrix6x5(
                a1 = false, b1 = false, c1 = false, d1 = true, e1 = false, f1 = false,
                a2 = false, b2 = false, c2 = false, d2 = true, e2 = false, f2 = false,
                a3 = false, b3 = false, c3 = false, d3 = true, e3 = false, f3 = false,
                a4 = false, b4 = false, c4 = false, d4 = true, e4 = false, f4 = false,
                a5 = false, b5 = false, c5 = false, d5 = true, e5 = false, f5 = false,
            )
        )
        val _5 = Matrix6x5(
            a1 = 4, b1 = 8, c1 = 3, d1 = 5, e1 = 1, f1 = 7,
            a2 = 0, b2 = 2, c2 = 0, d2 = 4, e2 = 1, f2 = 0,
            a3 = 8, b3 = 3, c3 = 8, d3 = 3, e3 = 1, f3 = 8,
            a4 = 7, b4 = 4, c4 = 7, d4 = 2, e4 = 1, f4 = 7,
            a5 = 6, b5 = 5, c5 = 6, d5 = 5, e5 = 1, f5 = 0,

            resultMatrix6x5 = ResultMatrix6x5(
                a1 = false, b1 = false, c1 = false, d1 = false, e1 = true, f1 = false,
                a2 = false, b2 = false, c2 = false, d2 = false, e2 = true, f2 = false,
                a3 = false, b3 = false, c3 = false, d3 = false, e3 = true, f3 = false,
                a4 = false, b4 = false, c4 = false, d4 = false, e4 = true, f4 = false,
                a5 = false, b5 = false, c5 = false, d5 = false, e5 = true, f5 = false,
            )
        )
        val _6 = Matrix6x5(
            a1 = 4, b1 = 8, c1 = 3, d1 = 5, e1 = 2, f1 = 1,
            a2 = 0, b2 = 2, c2 = 0, d2 = 4, e2 = 0, f2 = 1,
            a3 = 8, b3 = 3, c3 = 8, d3 = 3, e3 = 8, f3 = 1,
            a4 = 7, b4 = 4, c4 = 7, d4 = 2, e4 = 7, f4 = 1,
            a5 = 6, b5 = 5, c5 = 6, d5 = 1, e5 = 0, f5 = 1,

            resultMatrix6x5 = ResultMatrix6x5(
                a1 = false, b1 = false, c1 = false, d1 = false, e1 = false, f1 = true,
                a2 = false, b2 = false, c2 = false, d2 = false, e2 = false, f2 = true,
                a3 = false, b3 = false, c3 = false, d3 = false, e3 = false, f3 = true,
                a4 = false, b4 = false, c4 = false, d4 = false, e4 = false, f4 = true,
                a5 = false, b5 = false, c5 = false, d5 = false, e5 = false, f5 = true,
            )
        )

        override val matrixList = listOf<Matrix6x5>(_1,_2,_3,_4,_5,_6)
    }

}