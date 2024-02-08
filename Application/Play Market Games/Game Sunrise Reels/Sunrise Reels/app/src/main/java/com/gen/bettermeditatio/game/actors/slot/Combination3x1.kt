package com.gen.bettermeditatio.game.actors.slot

import com.gen.bettermeditatio.game.actors.slot.Matrix3x1.Item.*

interface CombinationMatrix3x1 {
    val matrixList: List<Matrix3x1>
}

object Combination3x1 {

    object Mix : CombinationMatrix3x1 {
        val _1 = Matrix3x1(
            a1 = a1, b1 = a2, c1 = a3,
        )
        val _2 = Matrix3x1(
            a1 = a1, b1 = a1, c1 = a3,
        )
        val _3 = Matrix3x1(
            a1 = a1, b1 = a3, c1 = a3,
        )
        val _4 = Matrix3x1(
            a1 = a1, b1 = a2, c1 = a1,
        )

        override val matrixList = listOf<Matrix3x1>(_1, _2, _3, _4)
    }

    object MixWithWild: CombinationMatrix3x1 {
        val _1 = Matrix3x1(
            scheme = """ 
                    W - -
                         """,
            a1 = W, b1 = a1, c1 = a2,
        )
        val _2 = Matrix3x1(
            scheme = """ 
                    - W -
                         """,
            a1 = a1, b1 = W, c1 = a2,
        )
        val _3 = Matrix3x1(
            scheme = """ 
                    - - W
                         """,
            a1 = a1, b1 = a2, c1 = W,
        )

        override val matrixList = listOf<Matrix3x1>(_1, _2, _3)
    }

    object Win: CombinationMatrix3x1 {
        val _1 = Matrix3x1(
            scheme = """ 
                    1 1 1
                         """,
            winItemList = listOf(a1),
            a1 = a1, b1 = a1, c1 = a1,
        )
        override val matrixList = listOf<Matrix3x1>(_1)
    }

    object WinWithWild: CombinationMatrix3x1 {
        val _1 = Matrix3x1(
            scheme = """ 
                    W 1 1
                         """,
            winItemList = listOf(W, a1),
            a1 = W, b1 = a1, c1 = a1,
        )
        val _2 = Matrix3x1(
            scheme = """ 
                    1 W 1
                         """,
            winItemList = listOf(W, a1),
            a1 = a1, b1 = W, c1 = a1,
        )
        val _3 = Matrix3x1(
            scheme = """ 
                    1 1 W
                         """,
            winItemList = listOf(W, a1),
            a1 = a1, b1 = a1, c1 = W,
        )
        override val matrixList = listOf<Matrix3x1>(_1, _2, _3)
    }

}