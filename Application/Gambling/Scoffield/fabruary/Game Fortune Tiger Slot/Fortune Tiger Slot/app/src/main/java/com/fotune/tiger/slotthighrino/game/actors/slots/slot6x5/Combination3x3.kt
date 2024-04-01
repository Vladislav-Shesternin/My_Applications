package com.fotune.tiger.slotthighrino.game.actors.slots.slot6x5

interface ICombinationMatrix3x3 {
    val matrixList: List<Matrix3x3>
}

/**
 *
 * ITEM index = 0..8
 * WILD index = not
 *
 * */
object Combination3x3 {

    object Mix : ICombinationMatrix3x3 {
        val _1 = Matrix3x3(
            a1 = 1, b1 = 6, c1 = 1,
            a2 = 2, b2 = 7, c2 = 2,
            a3 = 3, b3 = 8, c3 = 3,
        )
        val _2 = Matrix3x3(
            a1 = 1, b1 = 1, c1 = 0,
            a2 = 3, b2 = 7, c2 = 2,
            a3 = 3, b3 = 8, c3 = 1,
        )
        val _3 = Matrix3x3(
            a1 = 7, b1 = 6, c1 = 1,
            a2 = 2, b2 = 7, c2 = 4,
            a3 = 3, b3 = 8, c3 = 3,
        )
        val _4 = Matrix3x3(
            a1 = 0, b1 = 6, c1 = 1,
            a2 = 2, b2 = 7, c2 = 2,
            a3 = 1, b3 = 8, c3 = 0,
        )
        val _5 = Matrix3x3(
            a1 = 1, b1 = 6, c1 = 5,
            a2 = 2, b2 = 8, c2 = 2,
            a3 = 3, b3 = 8, c3 = 1,
        )

        override val matrixList = listOf<Matrix3x3>(_1,_2,_3,_4,_5)
    }

    object Win : ICombinationMatrix3x3 {
        val _1 = Matrix3x3(
            a1 = 1, b1 = 6, c1 = 5,
            a2 = 1, b2 = 8, c2 = 2,
            a3 = 1, b3 = 8, c3 = 1,

            resultMatrix6x5 = ResultMatrix3x3(
                a1 = true, b1 = false, c1 = false,
                a2 = true, b2 = false, c2 = false,
                a3 = true, b3 = false, c3 = false,
            )
        )
        val _2 = Matrix3x3(
            a1 = 1, b1 = 2, c1 = 3,
            a2 = 0, b2 = 2, c2 = 5,
            a3 = 3, b3 = 2, c3 = 0,

            resultMatrix6x5 = ResultMatrix3x3(
                a1 = false, b1 = true, c1 = false,
                a2 = false, b2 = true, c2 = false,
                a3 = false, b3 = true, c3 = false,
            )
        )
        val _3 = Matrix3x3(
            a1 = 1, b1 = 2, c1 = 0,
            a2 = 0, b2 = 5, c2 = 0,
            a3 = 3, b3 = 4, c3 = 0,

            resultMatrix6x5 = ResultMatrix3x3(
                a1 = false, b1 = false, c1 = true,
                a2 = false, b2 = false, c2 = true,
                a3 = false, b3 = false, c3 = true,
            )
        )

        override val matrixList = listOf<Matrix3x3>(_1,_2,_3)
    }

}