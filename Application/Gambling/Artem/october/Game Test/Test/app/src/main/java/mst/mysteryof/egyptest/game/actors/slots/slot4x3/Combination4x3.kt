package mst.mysteryof.egyptest.game.actors.slots.slot4x3

interface ICombinationMatrix4x3 {
    val matrixList: List<Matrix4x3>
}
//WILD index = 13
/**
 *
 * ITEM index = 0..2
 *
 * */
object Combination4x3 {

    object Mix : ICombinationMatrix4x3 {
        val _1 = Matrix4x3(
            a1 = 0, b1 = 2, c1 = 0, d1 = 0,
            a2 = 2, b2 = 1, c2 = 0, d2 = 1,
            a3 = 0, b3 = 1, c3 = 1, d3 = 2,
        )
        val _2 = Matrix4x3(
            a1 = 1, b1 = 0, c1 = 2, d1 = 2,
            a2 = 2, b2 = 1, c2 = 2, d2 = 1,
            a3 = 0, b3 = 2, c3 = 1, d3 = 0,
        )
        val _3 = Matrix4x3(
            a1 = 1, b1 = 0, c1 = 1, d1 = 2,
            a2 = 2, b2 = 1, c2 = 2, d2 = 0,
            a3 = 0, b3 = 1, c3 = 0, d3 = 2,
        )

        override val matrixList = listOf<Matrix4x3>(_1,_2,_3)
    }

    object Win : ICombinationMatrix4x3 {
        val _1 = Matrix4x3(
            a1 = 0, b1 = 0, c1 = 0, d1 = 0,
            a2 = 2, b2 = 1, c2 = 2, d2 = 2,
            a3 = 0, b3 = 1, c3 = 0, d3 = 0,

            resultMatrix4x3 = ResultMatrix4x3(
                a1 = true,  b1 = true,  c1 = true, d1 = true,
                a2 = false, b2 = false, c2 = false, d2 = false,
                a3 = false, b3 = false, c3 = false, d3 = false,
            )
        )
        val _2 = Matrix4x3(
            a1 = 0, b1 = 0, c1 = 0, d1 = 1,
            a2 = 0, b2 = 1, c2 = 0, d2 = 2,
            a3 = 0, b3 = 1, c3 = 0, d3 = 1,

            resultMatrix4x3 = ResultMatrix4x3(
                a1 = true,  b1 = true,  c1 = true, d1 = false,
                a2 = true, b2 = false, c2 = true, d2 = false,
                a3 = true, b3 = false, c3 = true, d3 = false,
            )
        )
        val _3 = Matrix4x3(
            a1 = 0, b1 = 2, c1 = 0, d1 = 2,
            a2 = 0, b2 = 1, c2 = 1, d2 = 0,
            a3 = 0, b3 = 1, c3 = 0, d3 = 2,

            resultMatrix4x3 = ResultMatrix4x3(
                a1 = true,  b1 = false,  c1 = false, d1 = false,
                a2 = true, b2 = false, c2 = false, d2 = false,
                a3 = true, b3 = false, c3 = false, d3 = false,
            )
        )
        val _4 = Matrix4x3(
            a1 = 0, b1 = 2, c1 = 2, d1 = 2,
            a2 = 0, b2 = 1, c2 = 1, d2 = 1,
            a3 = 0, b3 = 2, c3 = 2, d3 = 2,

            resultMatrix4x3 = ResultMatrix4x3(
                a1 = true,  b1 = true,  c1 = true, d1 = true,
                a2 = true, b2 = true, c2 = true, d2 = true,
                a3 = true, b3 = true, c3 = true, d3 = true,
            )
        )
        val _5 = Matrix4x3(
            a1 = 0, b1 = 2, c1 = 2, d1 = 0,
            a2 = 0, b2 = 1, c2 = 1, d2 = 0,
            a3 = 0, b3 = 2, c3 = 2, d3 = 0,

            resultMatrix4x3 = ResultMatrix4x3(
                a1 = true,  b1 = false,  c1 = false, d1 = true,
                a2 = true, b2 = false, c2 = false, d2 = true,
                a3 = true, b3 = false, c3 = false, d3 = true,
            )
        )
        val _6 = Matrix4x3(
            a1 = 2, b1 = 1, c1 = 2, d1 = 0,
            a2 = 0, b2 = 1, c2 = 0, d2 = 0,
            a3 = 2, b3 = 1, c3 = 2, d3 = 0,

            resultMatrix4x3 = ResultMatrix4x3(
                a1 = false,  b1 = true,  c1 = false, d1 = true,
                a2 = false, b2 = true, c2 = true, d2 = true,
                a3 = false, b3 = true, c3 = false, d3 = true,
            )
        )
        val _7 = Matrix4x3(
            a1 = 2, b1 = 2, c1 = 2, d1 = 1,
            a2 = 1, b2 = 1, c2 = 2, d2 = 0,
            a3 = 1, b3 = 0, c3 = 0, d3 = 0,

            resultMatrix4x3 = ResultMatrix4x3(
                a1 = true,  b1 = true,  c1 = true, d1 = false,
                a2 = false, b2 = false, c2 = true, d2 = true,
                a3 = false, b3 = true, c3 = true, d3 = true,
            )
        )

        override val matrixList = listOf<Matrix4x3>(_1,_2,_3,_4,_5,_6,_7)
    }

}