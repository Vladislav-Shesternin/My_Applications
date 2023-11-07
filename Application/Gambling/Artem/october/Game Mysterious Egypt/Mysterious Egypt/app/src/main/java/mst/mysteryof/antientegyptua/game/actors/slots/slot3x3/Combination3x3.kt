package mst.mysteryof.antientegyptua.game.actors.slots.slot3x3

interface ICombinationMatrix3x3 {
    val matrixList: List<Matrix3x3>
}
//WILD index = 13
/**
 *
 * ITEM index = 0..13
 *
 * */
object Combination3x3 {

    object Mix : ICombinationMatrix3x3 {
        val _1 = Matrix3x3(
            a1 = 1, b1 = 4, c1 = 7,
            a2 = 2, b2 = 5, c2 = 8,
            a3 = 3, b3 = 6, c3 = 9,
        )
        val _2 = Matrix3x3(
            a1 = 1, b1 = 2, c1 = 7,
            a2 = 13, b2 = 2, c2 = 3,
            a3 = 3, b3 = 6, c3 = 12,
        )
        val _3 = Matrix3x3(
            a1 = 6, b1 = 4, c1 = 2,
            a2 = 2, b2 = 3, c2 = 8,
            a3 = 3, b3 = 6, c3 = 2,
        )
        val _4 = Matrix3x3(
            a1 = 5, b1 = 4, c1 = 7,
            a2 = 2, b2 = 9, c2 = 13,
            a3 = 13, b3 = 6, c3 = 2,
        )
        val _5 = Matrix3x3(
            a1 = 13, b1 = 4, c1 = 7,
            a2 = 2, b2 = 5, c2 = 8,
            a3 = 6, b3 = 6, c3 = 2,
        )
        val _6 = Matrix3x3(
            a1 = 13, b1 = 4, c1 = 7,
            a2 = 2, b2 = 5, c2 = 8,
            a3 = 7, b3 = 6, c3 = 2,
        )
        val _7 = Matrix3x3(
            a1 = 13, b1 = 4, c1 = 7,
            a2 = 2, b2 = 5, c2 = 2,
            a3 = 6, b3 = 6, c3 = 2,
        )

        override val matrixList = listOf<Matrix3x3>(_1,_2,_3,_4,_5,_6,_7)
    }

    object Win : ICombinationMatrix3x3 {
        val _1 = Matrix3x3(
            a1 = 0, b1 = 0, c1 = 0,
            a2 = 2, b2 = 5, c2 = 8,
            a3 = 3, b3 = 6, c3 = 9,

            resultMatrix3x3 = ResultMatrix3x3(
                a1 = true,  b1 = true,  c1 = true,
                a2 = false, b2 = false, c2 = false,
                a3 = false, b3 = false, c3 = false,
            )
        )
        val _2 = Matrix3x3(
            a1 = 0, b1 = 1, c1 = 2,
            a2 = 0, b2 = 5, c2 = 8,
            a3 = 0, b3 = 6, c3 = 9,

            resultMatrix3x3 = ResultMatrix3x3(
                a1 = true,  b1 = false,  c1 = false,
                a2 = true, b2 = false, c2 = false,
                a3 = true, b3 = false, c3 = false,
            )
        )
        val _3 = Matrix3x3(
            a1 = 2, b1 = 3, c1 = 4,
            a2 = 0, b2 = 0, c2 = 0,
            a3 = 3, b3 = 6, c3 = 9,

            resultMatrix3x3 = ResultMatrix3x3(
                a1 = false, b1 = false, c1 = false,
                a2 = true, b2 = true, c2 = true,
                a3 = false, b3 = false, c3 = false,
            )
        )
        val _4 = Matrix3x3(
            a1 = 1, b1 = 12, c1 = 4,
            a2 = 0, b2 = 0, c2 = 0,
            a3 = 0, b3 = 2, c3 = 0,

            resultMatrix3x3 = ResultMatrix3x3(
                a1 = false, b1 = false, c1 = false,
                a2 = true, b2 = true, c2 = true,
                a3 = true, b3 = false, c3 = true,
            )
        )
        val _5 = Matrix3x3(
            a1 = 0, b1 = 1, c1 = 0,
            a2 = 0, b2 = 5, c2 = 0,
            a3 = 0, b3 = 6, c3 = 0,

            resultMatrix3x3 = ResultMatrix3x3(
                a1 = true,  b1 = false,  c1 = true,
                a2 = true, b2 = false, c2 = true,
                a3 = true, b3 = false, c3 = true,
            )
        )
        val _6 = Matrix3x3(
            a1 = 0, b1 = 1, c1 = 0,
            a2 = 12, b2 = 0, c2 = 11,
            a3 = 0, b3 = 6, c3 = 0,

            resultMatrix3x3 = ResultMatrix3x3(
                a1 = true,  b1 = false,  c1 = true,
                a2 = false, b2 = true, c2 = false,
                a3 = true, b3 = false, c3 = true,
            )
        )

        val _7 = Matrix3x3(
            a1 = 0, b1 = 12, c1 = 4,
            a2 = 13, b2 = 0, c2 = 5,
            a3 = 1, b3 = 2, c3 = 0,

            resultMatrix3x3 = ResultMatrix3x3(
                a1 = true, b1 = false, c1 = false,
                a2 = false, b2 = true, c2 = false,
                a3 = false, b3 = false, c3 = true,
            )
        )

        override val matrixList = listOf<Matrix3x3>(_1,_2,_3,_4,_5,_6,_7)
    }

}