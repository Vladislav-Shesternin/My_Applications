package com.mesga.moolahit.game.actors.slot

import com.mesga.moolahit.game.actors.slot.Matrix4x3.Item.*

interface CombinationMatrix4x3 {
    val matrixList: List<Matrix4x3>
}

object Combination4x3 {

    object Mix : CombinationMatrix4x3 {
        val _1 = Matrix4x3(
            a1 = a1, b1 = a4, c1 = a7, d1 = a10,
            a2 = a2, b2 = a5, c2 = a8, d2 = a1,
            a3 = a3, b3 = a6, c3 = a9, d3 = a2,
        )
        val _2 = Matrix4x3(
            a1 = a1, b1 = a4, c1 = a7, d1 = a10,
            a2 = a2, b2 = a5, c2 = a1, d2 = a1,
            a3 = a1, b3 = a6, c3 = a9, d3 = a2,
        )
        val _3 = Matrix4x3(
            a1 = a1, b1 = a1, c1 = a7, d1 = a10,
            a2 = a2, b2 = a5, c2 = a8, d2 = a1,
            a3 = a3, b3 = a6, c3 = a1, d3 = a2,
        )
        val _4 = Matrix4x3(
            a1 = a1, b1 = a4, c1 = a2, d1 = a10,
            a2 = a2, b2 = a5, c2 = a8, d2 = a1,
            a3 = a3, b3 = a6, c3 = a9, d3 = a2,
        )
        val _5 = Matrix4x3(
            a1 = a2, b1 = a4, c1 = a7, d1 = a10,
            a2 = a2, b2 = a5, c2 = a8, d2 = a1,
            a3 = a10, b3 = a6, c3 = a9, d3 = a2,
        )
        val _6 = Matrix4x3(
            a1 = a1, b1 = a4, c1 = a7, d1 = a10,
            a2 = a5, b2 = a5, c2 = a8, d2 = a5,
            a3 = a3, b3 = a6, c3 = a9, d3 = a2,
        )
        val _7 = Matrix4x3(
            a1 = a1, b1 = a4, c1 = a10, d1 = a10,
            a2 = a2, b2 = a5, c2 = a8, d2 = a1,
            a3 = a6, b3 = a6, c3 = a9, d3 = a2,
        )

        override val matrixList = listOf<Matrix4x3>(_1, _2, _3, _4, _5, _6, _7)
    }

    object WinMonochrome: CombinationMatrix4x3 {
        val _1 = Matrix4x3(
            scheme = """ 
                        1 1 1 1
                        - - - -
                        - - - -""",
            winItemList = listOf(a1),
            a1 = a1, b1 = a1, c1 = a1, d1 = a1,
            a2 = a2, b2 = a5, c2 = a8, d2 = a2,
            a3 = a3, b3 = a6, c3 = a9, d3 = a2,
        )
        val _2 = Matrix4x3(
            scheme = """ 
                        - - - -
                        1 1 1 1
                        - - - -""",
            winItemList = listOf(a1),
            a1 = a2, b1 = a5, c1 = a8, d1 = a2,
            a2 = a1, b2 = a1, c2 = a1, d2 = a1,
            a3 = a3, b3 = a6, c3 = a9, d3 = a2,
        )
        val _3 = Matrix4x3(
            scheme = """ 
                        - - - -
                        - - - -
                        1 1 1 1""",
            winItemList = listOf(a1),
            a1 = a2, b1 = a5, c1 = a8, d1 = a2,
            a2 = a3, b2 = a6, c2 = a9, d2 = a2,
            a3 = a1, b3 = a1, c3 = a1, d3 = a1,
        )
        val _4 = Matrix4x3(
            scheme = """ 
                        1 1 1 1
                        - - - -
                        1 1 1 1""",
            winItemList = listOf(a1),
            a1 = a1, b1 = a1, c1 = a1, d1 = a1,
            a2 = a3, b2 = a6, c2 = a9, d2 = a2,
            a3 = a1, b3 = a1, c3 = a1, d3 = a1,
        )
        val _5 = Matrix4x3(
            scheme = """ 
                        - - - -
                        1 1 1 1
                        1 1 1 1""",
            winItemList = listOf(a1),
            a1 = a2, b1 = a5, c1 = a8, d1 = a2,
            a2 = a1, b2 = a1, c2 = a1, d2 = a1,
            a3 = a1, b3 = a1, c3 = a1, d3 = a1,
        )
        val _6 = Matrix4x3(
            scheme = """ 
                        1 1 1 1
                        1 1 1 1
                        - - - -""",
            winItemList = listOf(a1),
            a1 = a1, b1 = a1, c1 = a1, d1 = a1,
            a2 = a1, b2 = a1, c2 = a1, d2 = a1,
            a3 = a3, b3 = a6, c3 = a9, d3 = a2,
        )
        val _7 = Matrix4x3(
            scheme = """ 
                        1 1 1 1
                        1 1 1 1
                        1 1 1 1""",
            winItemList = listOf(a1),
            a1 = a1, b1 = a1, c1 = a1, d1 = a1,
            a2 = a1, b2 = a1, c2 = a1, d2 = a1,
            a3 = a1, b3 = a1, c3 = a1, d3 = a1,
        )
        val _8 = Matrix4x3(
            scheme = """ 
                        1 1 1 1
                        - 1 1 -
                        1 1 1 1""",
            winItemList = listOf(a1),
            a1 = a1, b1 = a1, c1 = a1, d1 = a1,
            a2 = a2, b2 = a1, c2 = a1, d2 = a2,
            a3 = a1, b3 = a1, c3 = a1, d3 = a1,
        )
        override val matrixList = listOf<Matrix4x3>(_1, _2, _3, _4, _5, _6, _7, _8)
    }

    object WinColorful: CombinationMatrix4x3 {
        val _1 = Matrix4x3(
            scheme = """ 
                        1 1 1 1
                        - 2 2 -
                        - 2 2 -""",
            winItemList = listOf(a1, a2),
            a1 = a1, b1 = a1, c1 = a1, d1 = a1,
            a2 = a4, b2 = a2, c2 = a2, d2 = a5,
            a3 = a3, b3 = a2, c3 = a2, d3 = a6,
        )
        val _2 = Matrix4x3(
            scheme = """ 
                        1 1 1 1
                        3 3 2 2
                        3 3 2 2""",
            winItemList = listOf(a1, a2, a3),
            a1 = a1, b1 = a1, c1 = a1, d1 = a1,
            a2 = a3, b2 = a3, c2 = a2, d2 = a2,
            a3 = a3, b3 = a3, c3 = a2, d3 = a2,
        )
        val _3 = Matrix4x3(
            scheme = """ 
                        1 - - 2
                        - 1 2 -
                        1 - - 2""",
            winItemList = listOf(a1, a2),
            a1 = a1, b1 = a3, c1 = a4, d1 = a2,
            a2 = a8, b2 = a1, c2 = a2, d2 = a5,
            a3 = a1, b3 = a7, c3 = a6, d3 = a2,
        )
        val _4 = Matrix4x3(
            scheme = """ 
                        1 1 2 2
                        - 1 2 -
                        1 2 1 2""",
            winItemList = listOf(a1, a2),
            a1 = a1, b1 = a1, c1 = a2, d1 = a2,
            a2 = a8, b2 = a1, c2 = a2, d2 = a5,
            a3 = a1, b3 = a2, c3 = a1, d3 = a2,
        )
        val _5 = Matrix4x3(
            scheme = """ 
                        2 2 3 3
                        3 3 2 2
                        1 1 1 1""",
            winItemList = listOf(a1, a2, a3),
            a1 = a2, b1 = a2, c1 = a3, d1 = a3,
            a2 = a3, b2 = a3, c2 = a2, d2 = a2,
            a3 = a1, b3 = a1, c3 = a1, d3 = a1,
        )


        override val matrixList = listOf<Matrix4x3>(_1, _2, _3, _4, _5)
    }

}