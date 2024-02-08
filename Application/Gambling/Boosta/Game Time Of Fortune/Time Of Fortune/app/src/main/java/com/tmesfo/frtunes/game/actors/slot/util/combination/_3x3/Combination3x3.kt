package com.tmesfo.frtunes.game.actors.slot.util.combination._3x3

import com.tmesfo.frtunes.game.actors.slot.util.combination._3x3.Matrix3x3.Item.*

interface CombinationMatrixEnum3x3 {
    val matrix: Matrix3x3
}



object Combination3x3 {

    enum class Mix(
        override val matrix: Matrix3x3,
    ) : CombinationMatrixEnum3x3 {
        _1(
            Matrix3x3(
                a0 = A, b0 = D, c0 = G,
                a1 = B, b1 = E, c1 = H,
                a2 = C, b2 = F, c2 = I,
            )
        ),
        _2(
            Matrix3x3(
                a0 = A, b0 = A, c0 = G,
                a1 = B, b1 = E, c1 = H,
                a2 = C, b2 = F, c2 = I,
            )
        ),
        _3(
            Matrix3x3(
                a0 = B, b0 = C, c0 = G,
                a1 = B, b1 = A, c1 = B,
                a2 = G, b2 = C, c2 = B,
            )
        ),
        _4(
            Matrix3x3(
                a0 = A, b0 = A, c0 = G,
                a1 = B, b1 = C, c1 = H,
                a2 = I, b2 = A, c2 = A,
            )
        ),
        _5(
            Matrix3x3(
                a0 = A, b0 = I, c0 = G,
                a1 = B, b1 = C, c1 = C,
                a2 = A, b2 = G, c2 = I,
            )
        ),
        _6(
            Matrix3x3(
                a0 = A, b0 = G, c0 = G,
                a1 = B, b1 = I, c1 = C,
                a2 = A, b2 = G, c2 = D,
            )
        ),
        _7(
            Matrix3x3(
                a0 = A, b0 = G, c0 = H,
                a1 = E, b1 = A, c1 = B,
                a2 = C, b2 = B, c2 = D,
            )
        ),
        _8(
            Matrix3x3(
                a0 = B, b0 = G, c0 = B,
                a1 = E, b1 = A, c1 = B,
                a2 = C, b2 = A, c2 = D,
            )
        ),
        _9(
            Matrix3x3(
                a0 = A, b0 = G, c0 = A,
                a1 = E, b1 = H, c1 = B,
                a2 = A, b2 = I, c2 = A,
            )
        ),
        _10(
            Matrix3x3(
                a0 = A, b0 = G, c0 = A,
                a1 = E, b1 = H, c1 = B,
                a2 = C, b2 = A, c2 = A,
            )
        ),
    }

    enum class MixWithWild(
        override val matrix: Matrix3x3,
    ) : CombinationMatrixEnum3x3 {
        _1(
            Matrix3x3(
                scheme = """
                    W - -
                    - - -
                    - - -
                """,
                a0 = W, b0 = D, c0 = G,
                a1 = B, b1 = E, c1 = H,
                a2 = C, b2 = F, c2 = I,
            )
        ),
        _2(
            Matrix3x3(
                scheme = """
                    - W -
                    - - -
                    - - -
                """,
                a0 = A, b0 = W, c0 = G,
                a1 = B, b1 = E, c1 = H,
                a2 = C, b2 = F, c2 = I,
            )
        ),
        _3(
            Matrix3x3(
                scheme = """
                    - - W
                    - - -
                    - - -
                """,
                a0 = A, b0 = D, c0 = W,
                a1 = B, b1 = E, c1 = H,
                a2 = C, b2 = F, c2 = I,
            )
        ),
        _4(
            Matrix3x3(
                scheme = """
                    - - -
                    W - -
                    - - -
                """,
                a0 = A, b0 = D, c0 = G,
                a1 = W, b1 = E, c1 = H,
                a2 = C, b2 = F, c2 = I,
            )
        ),
        _5(
            Matrix3x3(
                scheme = """
                    - - -
                    - W -
                    - - -
                """,
                a0 = A, b0 = D, c0 = G,
                a1 = B, b1 = W, c1 = H,
                a2 = C, b2 = F, c2 = I,
            )
        ),
        _6(
            Matrix3x3(
                scheme = """
                    - - -
                    - - W
                    - - -
                """,
                a0 = A, b0 = D, c0 = G,
                a1 = B, b1 = E, c1 = W,
                a2 = C, b2 = F, c2 = I,
            )
        ),
        _7(
            Matrix3x3(
                scheme = """
                    - - -
                    - - -
                    W - -
                """,
                a0 = A, b0 = D, c0 = G,
                a1 = B, b1 = E, c1 = H,
                a2 = W, b2 = F, c2 = I,
            )
        ),
        _8(
            Matrix3x3(
                scheme = """
                    - - -
                    - - -
                    - W -
                """,
                a0 = A, b0 = D, c0 = G,
                a1 = B, b1 = E, c1 = H,
                a2 = C, b2 = W, c2 = I,
            )
        ),
        _9(
            Matrix3x3(
                scheme = """
                    - - -
                    - - -
                    - - W
                """,
                a0 = A, b0 = D, c0 = G,
                a1 = B, b1 = E, c1 = H,
                a2 = C, b2 = F, c2 = W,
            )
        ),
    }

    enum class WinMonochrome(
        override val matrix: Matrix3x3,
    ) : CombinationMatrixEnum3x3 {
        _1(
            Matrix3x3(
                scheme = """
                    A A A
                    - - -
                    - - -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A,
                a1 = B, b1 = E, c1 = H,
                a2 = C, b2 = F, c2 = I,
            )
        ),
        _2(
            Matrix3x3(
                scheme = """
                    - - -
                    A A A
                    - - -
                """,
                winItemList = listOf(A),
                a0 = B, b0 = D, c0 = E,
                a1 = A, b1 = A, c1 = A,
                a2 = C, b2 = F, c2 = I,
            )
        ),
        _3(
            Matrix3x3(
                scheme = """
                    - - -
                    - - -
                    A A A
                """,
                winItemList = listOf(A),
                a0 = C, b0 = D, c0 = E,
                a1 = B, b1 = E, c1 = H,
                a2 = A, b2 = A, c2 = A,
            )
        ),
        _4(
            Matrix3x3(
                scheme = """
                    A - -
                    - A A
                    - - -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = D, c0 = E,
                a1 = B, b1 = A, c1 = A,
                a2 = C, b2 = F, c2 = I,
            )
        ),
        _5(
            Matrix3x3(
                scheme = """
                    - - -
                    - A A
                    A - -
                """,
                winItemList = listOf(A),
                a0 = C, b0 = D, c0 = E,
                a1 = B, b1 = A, c1 = A,
                a2 = A, b2 = F, c2 = I,
            )
        ),
        _6(
            Matrix3x3(
                scheme = """
                    A - -
                    - A -
                    - - A
                """,
                winItemList = listOf(A),
                a0 = A, b0 = D, c0 = E,
                a1 = B, b1 = A, c1 = H,
                a2 = C, b2 = F, c2 = A,
            )
        ),
        _7(
            Matrix3x3(
                scheme = """
                    - - A
                    - A -
                    A - -
                """,
                winItemList = listOf(A),
                a0 = C, b0 = D, c0 = A,
                a1 = B, b1 = A, c1 = H,
                a2 = A, b2 = F, c2 = I,
            )
        ),
        _8(
            Matrix3x3(
                scheme = """
                    - - -
                    - A -
                    A - A
                """,
                winItemList = listOf(A),
                a0 = C, b0 = D, c0 = E,
                a1 = B, b1 = A, c1 = H,
                a2 = A, b2 = F, c2 = A,
            )
        ),
        _9(
            Matrix3x3(
                scheme = """
                    A - A
                    - A -
                    - - -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = D, c0 = A,
                a1 = B, b1 = A, c1 = H,
                a2 = C, b2 = F, c2 = I,
            )
        ),
        _10(
            Matrix3x3(
                scheme = """
                    A A -
                    A A A
                    - - -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = E,
                a1 = A, b1 = A, c1 = A,
                a2 = C, b2 = F, c2 = I,
            )
        ),
        _11(
            Matrix3x3(
                scheme = """
                    A A -
                    A A A
                    A A -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = E,
                a1 = A, b1 = A, c1 = A,
                a2 = A, b2 = A, c2 = I,
            )
        ),
        _12(
            Matrix3x3(
                scheme = """
                    - - -
                    A A -
                    A A A
                """,
                winItemList = listOf(A),
                a0 = C, b0 = D, c0 = E,
                a1 = A, b1 = A, c1 = H,
                a2 = A, b2 = A, c2 = A,
            )
        ),
        _13(
            Matrix3x3(
                scheme = """
                    A - -
                    - A -
                    A A A
                """,
                winItemList = listOf(A),
                a0 = A, b0 = D, c0 = E,
                a1 = B, b1 = A, c1 = H,
                a2 = A, b2 = A, c2 = A,
            )
        ),
        _14(
            Matrix3x3(
                scheme = """
                    A A A
                    - A -
                    - - A
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A,
                a1 = B, b1 = A, c1 = H,
                a2 = C, b2 = F, c2 = A,
            )
        ),
        _15(
            Matrix3x3(
                scheme = """
                    A A A
                    - A -
                    A A A
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A,
                a1 = B, b1 = A, c1 = H,
                a2 = A, b2 = A, c2 = A,
            )
        ),
        _16(
            Matrix3x3(
                scheme = """
                    A - A
                    - A -
                    A - A
                """,
                winItemList = listOf(A),
                a0 = A, b0 = D, c0 = A,
                a1 = B, b1 = A, c1 = H,
                a2 = A, b2 = F, c2 = A,
            )
        ),
        _17(
            Matrix3x3(
                scheme = """
                    A A A
                    - - -
                    A A A
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A,
                a1 = B, b1 = E, c1 = H,
                a2 = A, b2 = A, c2 = A,
            )
        ),
        _18(
            Matrix3x3(
                scheme = """
                    A A A
                    A A A
                    - - -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A,
                a1 = A, b1 = A, c1 = A,
                a2 = C, b2 = F, c2 = I,
            )
        ),
        _19(
            Matrix3x3(
                scheme = """
                    - - -
                    A A A
                    A A A
                """,
                winItemList = listOf(A),
                a0 = C, b0 = D, c0 = E,
                a1 = A, b1 = A, c1 = A,
                a2 = A, b2 = A, c2 = A,
            )
        ),
        _20(
            Matrix3x3(
                scheme = """
                    A - A
                    A A A
                    A - A
                """,
                winItemList = listOf(A),
                a0 = A, b0 = D, c0 = A,
                a1 = A, b1 = A, c1 = A,
                a2 = A, b2 = F, c2 = A,
            )
        ),
        _21(
            Matrix3x3(
                scheme = """
                    A A A
                    A A A
                    A A A
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A,
                a1 = A, b1 = A, c1 = A,
                a2 = A, b2 = A, c2 = A,
            )
        ),
    }

    enum class WinMonochromeWithWild(
        override val matrix: Matrix3x3,
    ) : CombinationMatrixEnum3x3 {
        _1(
            Matrix3x3(
                scheme = """
                    W A A
                    - - -
                    - - -
                """,
                winItemList = listOf(A),
                a0 = W, b0 = A, c0 = A,
                a1 = B, b1 = E, c1 = H,
                a2 = C, b2 = F, c2 = I,
            )
        ),
        _2(
            Matrix3x3(
                scheme = """
                    - - -
                    A W A
                    - - -
                """,
                winItemList = listOf(A),
                a0 = B, b0 = D, c0 = E,
                a1 = A, b1 = W, c1 = A,
                a2 = C, b2 = F, c2 = I,
            )
        ),
        _3(
            Matrix3x3(
                scheme = """
                    - - -
                    - - -
                    A A A
                """,
                winItemList = listOf(A),
                a0 = C, b0 = D, c0 = E,
                a1 = B, b1 = E, c1 = H,
                a2 = A, b2 = A, c2 = W,
            )
        ),
        _4(
            Matrix3x3(
                scheme = """
                    A - -
                    - W A
                    - - -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = D, c0 = E,
                a1 = B, b1 = A, c1 = A,
                a2 = C, b2 = F, c2 = I,
            )
        ),
        _5(
            Matrix3x3(
                scheme = """
                    - - -
                    - A A
                    W - -
                """,
                winItemList = listOf(A),
                a0 = C, b0 = D, c0 = E,
                a1 = B, b1 = A, c1 = A,
                a2 = A, b2 = F, c2 = I,
            )
        ),
        _6(
            Matrix3x3(
                scheme = """
                    A - -
                    - W -
                    - - A
                """,
                winItemList = listOf(A),
                a0 = A, b0 = D, c0 = E,
                a1 = B, b1 = A, c1 = H,
                a2 = C, b2 = F, c2 = A,
            )
        ),
        _7(
            Matrix3x3(
                scheme = """
                    - - W
                    - A -
                    A - -
                """,
                winItemList = listOf(A),
                a0 = C, b0 = D, c0 = A,
                a1 = B, b1 = A, c1 = H,
                a2 = A, b2 = F, c2 = I,
            )
        ),
        _8(
            Matrix3x3(
                scheme = """
                    - - -
                    - A -
                    W - A
                """,
                winItemList = listOf(A),
                a0 = C, b0 = D, c0 = E,
                a1 = B, b1 = A, c1 = H,
                a2 = A, b2 = F, c2 = A,
            )
        ),
        _9(
            Matrix3x3(
                scheme = """
                    A - W
                    - A -
                    - - -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = D, c0 = A,
                a1 = B, b1 = A, c1 = H,
                a2 = C, b2 = F, c2 = I,
            )
        ),
        _10(
            Matrix3x3(
                scheme = """
                    A A -
                    A W A
                    - - -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = E,
                a1 = A, b1 = A, c1 = A,
                a2 = C, b2 = F, c2 = I,
            )
        ),
        _11(
            Matrix3x3(
                scheme = """
                    A A -
                    A W A
                    A A -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = E,
                a1 = A, b1 = A, c1 = A,
                a2 = A, b2 = A, c2 = I,
            )
        ),
        _12(
            Matrix3x3(
                scheme = """
                    - - -
                    W A -
                    A A A
                """,
                winItemList = listOf(A),
                a0 = C, b0 = D, c0 = E,
                a1 = A, b1 = A, c1 = H,
                a2 = A, b2 = A, c2 = A,
            )
        ),
        _13(
            Matrix3x3(
                scheme = """
                    A - -
                    - A -
                    A W A
                """,
                winItemList = listOf(A),
                a0 = A, b0 = D, c0 = E,
                a1 = B, b1 = A, c1 = H,
                a2 = A, b2 = A, c2 = A,
            )
        ),
        _14(
            Matrix3x3(
                scheme = """
                    A W A
                    - A -
                    - - A
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A,
                a1 = B, b1 = A, c1 = H,
                a2 = C, b2 = F, c2 = A,
            )
        ),
        _15(
            Matrix3x3(
                scheme = """
                    A A A
                    - W -
                    A A A
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A,
                a1 = B, b1 = A, c1 = H,
                a2 = A, b2 = A, c2 = A,
            )
        ),
        _16(
            Matrix3x3(
                scheme = """
                    A - A
                    - W -
                    A - A
                """,
                winItemList = listOf(A),
                a0 = A, b0 = D, c0 = A,
                a1 = B, b1 = A, c1 = H,
                a2 = A, b2 = F, c2 = A,
            )
        ),
        _17(
            Matrix3x3(
                scheme = """
                    A A W
                    - - -
                    W A A
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A,
                a1 = B, b1 = E, c1 = H,
                a2 = A, b2 = A, c2 = A,
            )
        ),
        _18(
            Matrix3x3(
                scheme = """
                    A A W
                    W A A
                    - - -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A,
                a1 = A, b1 = A, c1 = A,
                a2 = C, b2 = F, c2 = I,
            )
        ),
        _19(
            Matrix3x3(
                scheme = """
                    - - -
                    A A A
                    A A W
                """,
                winItemList = listOf(A),
                a0 = C, b0 = D, c0 = E,
                a1 = A, b1 = A, c1 = A,
                a2 = A, b2 = A, c2 = A,
            )
        ),
        _20(
            Matrix3x3(
                scheme = """
                    A - A
                    A W A
                    A - A
                """,
                winItemList = listOf(A),
                a0 = A, b0 = D, c0 = A,
                a1 = A, b1 = A, c1 = A,
                a2 = A, b2 = F, c2 = A,
            )
        ),
        _21(
            Matrix3x3(
                scheme = """
                    W A W
                    A A A
                    W A W
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A,
                a1 = A, b1 = A, c1 = A,
                a2 = A, b2 = A, c2 = A,
            )
        ),
    }

    enum class WinColorful(
        override val matrix: Matrix3x3,
    ) : CombinationMatrixEnum3x3 {
        _1(
            Matrix3x3(
                scheme = """
                    A A A
                    - - -
                    B B B
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = A,
                a1 = F, b1 = E, c1 = H,
                a2 = B, b2 = B, c2 = B,
            )
        ),
        _2(
            Matrix3x3(
                scheme = """
                    A A A
                    B B B
                    - - -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = A,
                a1 = B, b1 = B, c1 = B,
                a2 = C, b2 = F, c2 = I,
            )
        ),
        _3(
            Matrix3x3(
                scheme = """
                    - - -
                    A A A
                    B B B
                """,
                winItemList = listOf(A, B),
                a0 = B, b0 = D, c0 = G,
                a1 = A, b1 = A, c1 = A,
                a2 = B, b2 = B, c2 = B,
            )
        ),
        _4(
            Matrix3x3(
                scheme = """
                    A - -
                    - A A
                    B B B
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = D, c0 = G,
                a1 = C, b1 = A, c1 = A,
                a2 = B, b2 = B, c2 = B,
            )
        ),
        _5(
            Matrix3x3(
                scheme = """
                    B B B
                    - A A
                    A - -
                """,
                winItemList = listOf(A, B),
                a0 = B, b0 = B, c0 = B,
                a1 = C, b1 = A, c1 = A,
                a2 = A, b2 = F, c2 = I,
            )
        ),
        _6(
            Matrix3x3(
                scheme = """
                    - A A
                    A B B
                    B - -
                """,
                winItemList = listOf(A, B),
                a0 = C, b0 = A, c0 = A,
                a1 = A, b1 = B, c1 = B,
                a2 = B, b2 = F, c2 = I,
            )
        ),
        _7(
            Matrix3x3(
                scheme = """
                    A - -
                    B A A
                    - B B
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = D, c0 = G,
                a1 = B, b1 = A, c1 = A,
                a2 = C, b2 = B, c2 = B,
            )
        ),
        _8(
            Matrix3x3(
                scheme = """
                    - A -
                    A B A
                    B - B
                """,
                winItemList = listOf(A, B),
                a0 = C, b0 = A, c0 = G,
                a1 = A, b1 = B, c1 = A,
                a2 = B, b2 = F, c2 = B,
            )
        ),
        _9(
            Matrix3x3(
                scheme = """
                    A - A
                    B A B
                    - B -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = D, c0 = A,
                a1 = B, b1 = A, c1 = B,
                a2 = C, b2 = B, c2 = I,
            )
        ),
        _10(
            Matrix3x3(
                scheme = """
                    A A A
                    B B B
                    C C C
                """,
                winItemList = listOf(A, B, C),
                a0 = A, b0 = A, c0 = A,
                a1 = B, b1 = B, c1 = B,
                a2 = C, b2 = C, c2 = C,
            )
        ),
    }

    enum class WinColorfulWithWild(
        override val matrix: Matrix3x3,
    ) : CombinationMatrixEnum3x3 {
        _1(
            Matrix3x3(
                scheme = """
                    A A A
                    - W -
                    B B B
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = A,
                a1 = F, b1 = W, c1 = H,
                a2 = B, b2 = B, c2 = B,
            )
        ),
        _2(
            Matrix3x3(
                scheme = """
                    W A A
                    B B B
                    - - -
                """,
                winItemList = listOf(A, B),
                a0 = W, b0 = A, c0 = A,
                a1 = B, b1 = B, c1 = B,
                a2 = C, b2 = F, c2 = I,
            )
        ),
        _3(
            Matrix3x3(
                scheme = """
                    - - -
                    A A W
                    B B B
                """,
                winItemList = listOf(A, B),
                a0 = B, b0 = D, c0 = G,
                a1 = A, b1 = A, c1 = W,
                a2 = B, b2 = B, c2 = B,
            )
        ),
        _4(
            Matrix3x3(
                scheme = """
                    A - -
                    - A A
                    B W B
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = D, c0 = G,
                a1 = C, b1 = A, c1 = A,
                a2 = B, b2 = W, c2 = B,
            )
        ),
        _5(
            Matrix3x3(
                scheme = """
                    B B B
                    - W A
                    A - -
                """,
                winItemList = listOf(A, B),
                a0 = B, b0 = B, c0 = B,
                a1 = C, b1 = W, c1 = A,
                a2 = A, b2 = F, c2 = I,
            )
        ),
        _6(
            Matrix3x3(
                scheme = """
                    - A A
                    A B B
                    W - -
                """,
                winItemList = listOf(A, B),
                a0 = C, b0 = A, c0 = A,
                a1 = A, b1 = B, c1 = B,
                a2 = W, b2 = F, c2 = I,
            )
        ),
        _7(
            Matrix3x3(
                scheme = """
                    W - -
                    B A A
                    - B B
                """,
                winItemList = listOf(A, B),
                a0 = W, b0 = D, c0 = G,
                a1 = B, b1 = A, c1 = A,
                a2 = C, b2 = B, c2 = B,
            )
        ),
        _8(
            Matrix3x3(
                scheme = """
                    - A -
                    A W A
                    B - B
                """,
                winItemList = listOf(A, B),
                a0 = C, b0 = A, c0 = G,
                a1 = A, b1 = W, c1 = A,
                a2 = B, b2 = F, c2 = B,
            )
        ),
        _9(
            Matrix3x3(
                scheme = """
                    A - A
                    B A B
                    - W -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = D, c0 = A,
                a1 = B, b1 = A, c1 = B,
                a2 = C, b2 = W, c2 = I,
            )
        ),
        _10(
            Matrix3x3(
                scheme = """
                    W A A
                    B W B
                    C C W
                """,
                winItemList = listOf(A, B, C),
                a0 = W, b0 = A, c0 = A,
                a1 = B, b1 = W, c1 = B,
                a2 = C, b2 = C, c2 = W,
            )
        ),
    }

}