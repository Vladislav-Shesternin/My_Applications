package com.tmesfo.frtunes.game.actors.slot.util.combination._3x4

import com.tmesfo.frtunes.game.actors.slot.util.combination._3x4.Matrix3x4.Item.*

interface CombinationMatrixEnum3x4 {
    val matrix: Matrix3x4
}



object Combination3x4 {

    enum class Mix(
        override val matrix: Matrix3x4,
    ) : CombinationMatrixEnum3x4 {
        _1(
            Matrix3x4(
                a0 = A, b0 = D, c0 = G, d0 = A,
                a1 = B, b1 = E, c1 = H, d1 = B,
                a2 = C, b2 = F, c2 = I, d2 = C,
            )
        ),
        _2(
            Matrix3x4(
                a0 = A, b0 = A, c0 = G, d0 = B,
                a1 = B, b1 = E, c1 = H, d1 = B,
                a2 = C, b2 = F, c2 = I, d2 = C,
            )
        ),
        _3(
            Matrix3x4(
                a0 = A, b0 = D, c0 = G, d0 = A,
                a1 = B, b1 = A, c1 = H, d1 = B,
                a2 = C, b2 = C, c2 = I, d2 = C,
            )
        ),
        _4(
            Matrix3x4(
                a0 = A, b0 = A, c0 = G, d0 = A,
                a1 = B, b1 = C, c1 = H, d1 = B,
                a2 = C, b2 = B, c2 = I, d2 = C,
            )
        ),
        _5(
            Matrix3x4(
                a0 = A, b0 = I, c0 = G, d0 = A,
                a1 = B, b1 = C, c1 = C, d1 = B,
                a2 = A, b2 = G, c2 = I, d2 = A,
            )
        ),
    }

    enum class MixWithWild(
        override val matrix: Matrix3x4,
    ) : CombinationMatrixEnum3x4 {
        _1(
            Matrix3x4(
                a0 = W, b0 = D, c0 = G, d0 = A,
                a1 = B, b1 = E, c1 = H, d1 = B,
                a2 = C, b2 = F, c2 = I, d2 = C,
            )
        ),
        _2(
            Matrix3x4(
                a0 = D, b0 = W, c0 = G, d0 = A,
                a1 = B, b1 = E, c1 = H, d1 = B,
                a2 = C, b2 = F, c2 = I, d2 = C,
            )
        ),
        _3(
            Matrix3x4(
                a0 = G, b0 = D, c0 = W, d0 = A,
                a1 = B, b1 = E, c1 = H, d1 = B,
                a2 = C, b2 = F, c2 = I, d2 = C,
            )
        ),
        _4(
            Matrix3x4(
                a0 = A, b0 = D, c0 = G, d0 = W,
                a1 = B, b1 = E, c1 = H, d1 = B,
                a2 = C, b2 = F, c2 = I, d2 = C,
            )
        ),
        _5(
            Matrix3x4(
                a0 = B, b0 = D, c0 = G, d0 = A,
                a1 = W, b1 = E, c1 = H, d1 = B,
                a2 = C, b2 = F, c2 = I, d2 = C,
            )
        ),
        _6(
            Matrix3x4(
                a0 = E, b0 = D, c0 = G, d0 = A,
                a1 = B, b1 = W, c1 = H, d1 = B,
                a2 = C, b2 = F, c2 = I, d2 = C,
            )
        ),
        _7(
            Matrix3x4(
                a0 = H, b0 = D, c0 = G, d0 = A,
                a1 = B, b1 = E, c1 = W, d1 = B,
                a2 = C, b2 = F, c2 = I, d2 = C,
            )
        ),
        _8(
            Matrix3x4(
                a0 = B, b0 = D, c0 = G, d0 = A,
                a1 = B, b1 = E, c1 = H, d1 = W,
                a2 = C, b2 = F, c2 = I, d2 = C,
            )
        ),
        _9(
            Matrix3x4(
                a0 = C, b0 = D, c0 = G, d0 = A,
                a1 = B, b1 = E, c1 = H, d1 = B,
                a2 = W, b2 = F, c2 = I, d2 = C,
            )
        ),
        _10(
            Matrix3x4(
                a0 = F, b0 = D, c0 = G, d0 = A,
                a1 = B, b1 = E, c1 = H, d1 = B,
                a2 = C, b2 = W, c2 = I, d2 = C,
            )
        ),
        _11(
            Matrix3x4(
                a0 = I, b0 = D, c0 = G, d0 = A,
                a1 = B, b1 = E, c1 = H, d1 = B,
                a2 = C, b2 = F, c2 = W, d2 = C,
            )
        ),
        _12(
            Matrix3x4(
                a0 = H, b0 = D, c0 = G, d0 = A,
                a1 = B, b1 = E, c1 = H, d1 = B,
                a2 = C, b2 = F, c2 = I, d2 = W,
            )
        ),
    }

    enum class WinMonochrome(
        override val matrix: Matrix3x4,
    ) : CombinationMatrixEnum3x4 {
        _1(
            Matrix3x4(
                scheme = """
                    + + + -
                    - - - -
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A, d0 = D,
                a1 = B, b1 = E, c1 = H, d1 = B,
                a2 = C, b2 = F, c2 = I, d2 = C,
            )
        ),
        _2(
            Matrix3x4(
                scheme = """
                    - - - -
                    + + + -
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = B, b0 = E, c0 = H, d0 = D,
                a1 = A, b1 = A, c1 = A, d1 = B,
                a2 = C, b2 = F, c2 = I, d2 = C,
            )
        ),
        _3(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - - -
                    + + + -
                """,
                winItemList = listOf(A),
                a0 = B, b0 = E, c0 = H, d0 = D,
                a1 = C, b1 = F, c1 = I, d1 = B,
                a2 = A, b2 = A, c2 = A, d2 = C,
            )
        ),
        _4(
            Matrix3x4(
                scheme = """
                    - + + +
                    - - - -
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = B, b0 = A, c0 = A, d0 = A,
                a1 = D, b1 = E, c1 = H, d1 = B,
                a2 = C, b2 = F, c2 = I, d2 = C,
            )
        ),
        _5(
            Matrix3x4(
                scheme = """
                    - - - -
                    - + + +
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = B, b0 = E, c0 = H, d0 = D,
                a1 = B, b1 = A, c1 = A, d1 = A,
                a2 = C, b2 = F, c2 = I, d2 = C,
            )
        ),
        _6(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - - -
                    - + + +
                """,
                winItemList = listOf(A),
                a0 = B, b0 = E, c0 = H, d0 = D,
                a1 = F, b1 = I, c1 = G, d1 = B,
                a2 = C, b2 = A, c2 = A, d2 = A,
            )
        ),
        _7(
            Matrix3x4(
                scheme = """
                    + - - -
                    - + - -
                    - - + -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = E, c0 = H, d0 = D,
                a1 = F, b1 = A, c1 = G, d1 = B,
                a2 = C, b2 = F, c2 = A, d2 = C,
            )
        ),
        _8(
            Matrix3x4(
                scheme = """
                    - + - -
                    - - + -
                    - - - +
                """,
                winItemList = listOf(A),
                a0 = E, b0 = A, c0 = H, d0 = D,
                a1 = F, b1 = G, c1 = A, d1 = B,
                a2 = C, b2 = F, c2 = C, d2 = A,
            )
        ),
        _9(
            Matrix3x4(
                scheme = """
                    - - + -
                    - + - -
                    + - - -
                """,
                winItemList = listOf(A),
                a0 = E, b0 = G, c0 = A, d0 = D,
                a1 = F, b1 = A, c1 = H, d1 = B,
                a2 = A, b2 = F, c2 = C, d2 = I,
            )
        ),
        _10(
            Matrix3x4(
                scheme = """
                    - - - +
                    - - + -
                    - + - -
                """,
                winItemList = listOf(A),
                a0 = E, b0 = G, c0 = D, d0 = A,
                a1 = F, b1 = H, c1 = A, d1 = B,
                a2 = F, b2 = A, c2 = C, d2 = I,
            )
        ),
        _11(
            Matrix3x4(
                scheme = """
                    + + + +
                    - - - -
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A, d0 = A,
                a1 = F, b1 = H, c1 = D, d1 = B,
                a2 = G, b2 = E, c2 = C, d2 = I,
            )
        ),
        _12(
            Matrix3x4(
                scheme = """
                    - - - -
                    + + + +
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = F, b0 = H, c0 = D, d0 = B,
                a1 = A, b1 = A, c1 = A, d1 = A,
                a2 = G, b2 = E, c2 = C, d2 = I,
            )
        ),
        _13(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - - -
                    + + + +
                """,
                winItemList = listOf(A),
                a0 = F, b0 = H, c0 = D, d0 = B,
                a1 = G, b1 = E, c1 = C, d1 = I,
                a2 = A, b2 = A, c2 = A, d2 = A,
            )
        ),
        _14(
            Matrix3x4(
                scheme = """
                    + - - -
                    + + - -
                    + + + -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = H, c0 = D, d0 = B,
                a1 = A, b1 = A, c1 = C, d1 = I,
                a2 = A, b2 = A, c2 = A, d2 = G,
            )
        ),
        _15(
            Matrix3x4(
                scheme = """
                    + + + -
                    + + + -
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A, d0 = B,
                a1 = A, b1 = A, c1 = A, d1 = I,
                a2 = D, b2 = E, c2 = F, d2 = G,
            )
        ),
        _16(
            Matrix3x4(
                scheme = """
                    - - - -
                    + + + -
                    + + + -
                """,
                winItemList = listOf(A),
                a0 = C, b0 = E, c0 = F, d0 = B,
                a1 = A, b1 = A, c1 = A, d1 = I,
                a2 = A, b2 = A, c2 = A, d2 = G,
            )
        ),
        _17(
            Matrix3x4(
                scheme = """
                    + + + -
                    - - - -
                    + + + -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A, d0 = B,
                a1 = F, b1 = C, c1 = D, d1 = I,
                a2 = A, b2 = A, c2 = A, d2 = G,
            )
        ),
        _18(
            Matrix3x4(
                scheme = """
                    + + + +
                    - - + -
                    + + - -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A, d0 = A,
                a1 = F, b1 = C, c1 = A, d1 = I,
                a2 = A, b2 = A, c2 = E, d2 = G,
            )
        ),
        _19(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - + +
                    + + - -
                """,
                winItemList = listOf(A),
                a0 = D, b0 = B, c0 = E, d0 = I,
                a1 = F, b1 = C, c1 = A, d1 = A,
                a2 = A, b2 = A, c2 = E, d2 = G,
            )
        ),
        _20(
            Matrix3x4(
                scheme = """
                    + + - -
                    - - + +
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = E, d0 = I,
                a1 = F, b1 = C, c1 = A, d1 = A,
                a2 = H, b2 = B, c2 = E, d2 = G,
            )
        ),
        _21(
            Matrix3x4(
                scheme = """
                    + - - +
                    - + + -
                    - - + -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = D, c0 = E, d0 = A,
                a1 = F, b1 = A, c1 = A, d1 = B,
                a2 = H, b2 = B, c2 = A, d2 = G,
            )
        ),
        _22(
            Matrix3x4(
                scheme = """
                    + + + +
                    - + + +
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A, d0 = A,
                a1 = F, b1 = A, c1 = A, d1 = A,
                a2 = H, b2 = B, c2 = C, d2 = G,
            )
        ),
        _23(
            Matrix3x4(
                scheme = """
                    - + + +
                    - - + -
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = E, b0 = A, c0 = A, d0 = A,
                a1 = F, b1 = D, c1 = A, d1 = H,
                a2 = H, b2 = B, c2 = C, d2 = G,
            )
        ),
        _24(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - + -
                    - + + +
                """,
                winItemList = listOf(A),
                a0 = E, b0 = I, c0 = E, d0 = G,
                a1 = F, b1 = D, c1 = A, d1 = H,
                a2 = H, b2 = A, c2 = A, d2 = A,
            )
        ),
        _25(
            Matrix3x4(
                scheme = """
                    - - - -
                    - + + +
                    - + + -
                """,
                winItemList = listOf(A),
                a0 = E, b0 = I, c0 = E, d0 = G,
                a1 = F, b1 = A, c1 = A, d1 = A,
                a2 = H, b2 = A, c2 = A, d2 = D,
            )
        ),
        _26(
            Matrix3x4(
                scheme = """
                    - + - -
                    - + + -
                    - - + +
                """,
                winItemList = listOf(A),
                a0 = E, b0 = A, c0 = E, d0 = G,
                a1 = F, b1 = A, c1 = A, d1 = B,
                a2 = H, b2 = D, c2 = A, d2 = A,
            )
        ),
        _27(
            Matrix3x4(
                scheme = """
                    - - - -
                    - + - -
                    - - + +
                """,
                winItemList = listOf(A),
                a0 = E, b0 = C, c0 = E, d0 = G,
                a1 = F, b1 = A, c1 = D, d1 = B,
                a2 = H, b2 = D, c2 = A, d2 = A,
            )
        ),
        _28(
            Matrix3x4(
                scheme = """
                    - - + +
                    - + - +
                    - - - +
                """,
                winItemList = listOf(A),
                a0 = E, b0 = C, c0 = A, d0 = A,
                a1 = F, b1 = A, c1 = D, d1 = A,
                a2 = H, b2 = D, c2 = G, d2 = A,
            )
        ),
        _29(
            Matrix3x4(
                scheme = """
                    - - - +
                    - + + +
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = E, b0 = C, c0 = I, d0 = A,
                a1 = F, b1 = A, c1 = A, d1 = A,
                a2 = H, b2 = D, c2 = G, d2 = B,
            )
        ),
        _30(
            Matrix3x4(
                scheme = """
                    + - + -
                    - + - +
                    + - + -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = C, c0 = A, d0 = H,
                a1 = F, b1 = A, c1 = I, d1 = A,
                a2 = A, b2 = D, c2 = A, d2 = B,
            )
        ),
    }

    enum class WinMonochromeWithWild(
        override val matrix: Matrix3x4,
    ) : CombinationMatrixEnum3x4 {
        _1(
            Matrix3x4(
                scheme = """
                    + W + -
                    - - - -
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = W, c0 = A, d0 = D,
                a1 = B, b1 = E, c1 = H, d1 = B,
                a2 = C, b2 = F, c2 = I, d2 = C,
            )
        ),
        _2(
            Matrix3x4(
                scheme = """
                    - - - -
                    W + + -
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = B, b0 = E, c0 = H, d0 = D,
                a1 = W, b1 = A, c1 = A, d1 = B,
                a2 = C, b2 = F, c2 = I, d2 = C,
            )
        ),
        _3(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - - -
                    + + W -
                """,
                winItemList = listOf(A),
                a0 = B, b0 = E, c0 = H, d0 = D,
                a1 = C, b1 = F, c1 = I, d1 = B,
                a2 = A, b2 = A, c2 = W, d2 = C,
            )
        ),
        _4(
            Matrix3x4(
                scheme = """
                    - + + +
                    W - - -
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = B, b0 = A, c0 = A, d0 = A,
                a1 = W, b1 = E, c1 = H, d1 = B,
                a2 = C, b2 = F, c2 = I, d2 = C,
            )
        ),
        _5(
            Matrix3x4(
                scheme = """
                    - - - -
                    - + + +
                    - W - -
                """,
                winItemList = listOf(A),
                a0 = B, b0 = E, c0 = H, d0 = D,
                a1 = B, b1 = A, c1 = A, d1 = A,
                a2 = C, b2 = W, c2 = I, d2 = C,
            )
        ),
        _6(
            Matrix3x4(
                scheme = """
                    - - - -
                    - W - -
                    - + + +
                """,
                winItemList = listOf(A),
                a0 = B, b0 = E, c0 = H, d0 = D,
                a1 = F, b1 = W, c1 = G, d1 = B,
                a2 = C, b2 = A, c2 = A, d2 = A,
            )
        ),
        _7(
            Matrix3x4(
                scheme = """
                    + - - -
                    W + - -
                    - - + -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = E, c0 = H, d0 = D,
                a1 = W, b1 = A, c1 = G, d1 = B,
                a2 = C, b2 = F, c2 = A, d2 = C,
            )
        ),
        _8(
            Matrix3x4(
                scheme = """
                    - + W -
                    - - + -
                    - - - +
                """,
                winItemList = listOf(A),
                a0 = E, b0 = A, c0 = W, d0 = D,
                a1 = F, b1 = G, c1 = A, d1 = B,
                a2 = C, b2 = F, c2 = C, d2 = A,
            )
        ),
        _9(
            Matrix3x4(
                scheme = """
                    - - + -
                    - W - -
                    + - - -
                """,
                winItemList = listOf(A),
                a0 = E, b0 = G, c0 = A, d0 = D,
                a1 = F, b1 = W, c1 = H, d1 = B,
                a2 = A, b2 = D, c2 = C, d2 = I,
            )
        ),
        _10(
            Matrix3x4(
                scheme = """
                    - - - +
                    - - + -
                    - + - W
                """,
                winItemList = listOf(A),
                a0 = E, b0 = G, c0 = D, d0 = A,
                a1 = F, b1 = H, c1 = A, d1 = B,
                a2 = F, b2 = A, c2 = C, d2 = W,
            )
        ),
        _11(
            Matrix3x4(
                scheme = """
                    + W + +
                    - - - -
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = W, c0 = A, d0 = A,
                a1 = F, b1 = H, c1 = D, d1 = B,
                a2 = G, b2 = E, c2 = C, d2 = I,
            )
        ),
        _12(
            Matrix3x4(
                scheme = """
                    W - - -
                    + + + +
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = W, b0 = H, c0 = D, d0 = B,
                a1 = A, b1 = A, c1 = A, d1 = A,
                a2 = G, b2 = E, c2 = C, d2 = I,
            )
        ),
        _13(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - - W
                    + + + +
                """,
                winItemList = listOf(A),
                a0 = F, b0 = H, c0 = D, d0 = B,
                a1 = G, b1 = E, c1 = C, d1 = W,
                a2 = A, b2 = A, c2 = A, d2 = A,
            )
        ),
        _14(
            Matrix3x4(
                scheme = """
                    + - - -
                    + + - -
                    W + + -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = H, c0 = D, d0 = B,
                a1 = A, b1 = A, c1 = C, d1 = I,
                a2 = W, b2 = A, c2 = A, d2 = G,
            )
        ),
        _15(
            Matrix3x4(
                scheme = """
                    + + + -
                    + + + -
                    - - - W
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A, d0 = B,
                a1 = A, b1 = A, c1 = A, d1 = I,
                a2 = D, b2 = E, c2 = F, d2 = W,
            )
        ),
        _16(
            Matrix3x4(
                scheme = """
                    - - - W
                    + + + -
                    + + + -
                """,
                winItemList = listOf(A),
                a0 = C, b0 = E, c0 = F, d0 = W,
                a1 = A, b1 = A, c1 = A, d1 = I,
                a2 = A, b2 = A, c2 = A, d2 = G,
            )
        ),
        _17(
            Matrix3x4(
                scheme = """
                    + + + -
                    - - - W
                    + + + -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A, d0 = B,
                a1 = F, b1 = C, c1 = D, d1 = W,
                a2 = A, b2 = A, c2 = A, d2 = G,
            )
        ),
        _18(
            Matrix3x4(
                scheme = """
                    + + + +
                    - - + -
                    + + - W
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A, d0 = A,
                a1 = F, b1 = C, c1 = A, d1 = I,
                a2 = A, b2 = A, c2 = E, d2 = W,
            )
        ),
        _19(
            Matrix3x4(
                scheme = """
                    - W - -
                    - - + +
                    + + - -
                """,
                winItemList = listOf(A),
                a0 = D, b0 = W, c0 = E, d0 = I,
                a1 = F, b1 = C, c1 = A, d1 = A,
                a2 = A, b2 = A, c2 = E, d2 = G,
            )
        ),
        _20(
            Matrix3x4(
                scheme = """
                    + + - -
                    - - + +
                    - W - -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = E, d0 = I,
                a1 = F, b1 = C, c1 = A, d1 = A,
                a2 = H, b2 = W, c2 = E, d2 = G,
            )
        ),
        _21(
            Matrix3x4(
                scheme = """
                    + - - +
                    - + + -
                    W - + W
                """,
                winItemList = listOf(A),
                a0 = A, b0 = D, c0 = E, d0 = A,
                a1 = F, b1 = A, c1 = A, d1 = B,
                a2 = W, b2 = B, c2 = A, d2 = W,
            )
        ),
        _22(
            Matrix3x4(
                scheme = """
                    + W + W
                    - + + +
                    W - - -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = W, c0 = A, d0 = W,
                a1 = F, b1 = A, c1 = A, d1 = A,
                a2 = W, b2 = B, c2 = C, d2 = G,
            )
        ),
        _23(
            Matrix3x4(
                scheme = """
                    - + + +
                    - - + -
                    - - - W
                """,
                winItemList = listOf(A),
                a0 = E, b0 = A, c0 = A, d0 = A,
                a1 = F, b1 = D, c1 = A, d1 = H,
                a2 = H, b2 = B, c2 = C, d2 = W,
            )
        ),
        _24(
            Matrix3x4(
                scheme = """
                    - - - -
                    - W + -
                    - + + +
                """,
                winItemList = listOf(A),
                a0 = E, b0 = I, c0 = E, d0 = G,
                a1 = F, b1 = W, c1 = A, d1 = H,
                a2 = H, b2 = A, c2 = A, d2 = A,
            )
        ),
        _25(
            Matrix3x4(
                scheme = """
                    - - - W
                    - + + +
                    - + + -
                """,
                winItemList = listOf(A),
                a0 = E, b0 = I, c0 = E, d0 = W,
                a1 = F, b1 = A, c1 = A, d1 = A,
                a2 = H, b2 = A, c2 = A, d2 = D,
            )
        ),
        _26(
            Matrix3x4(
                scheme = """
                    - + - -
                    - + W -
                    - - + +
                """,
                winItemList = listOf(A),
                a0 = E, b0 = A, c0 = E, d0 = G,
                a1 = F, b1 = A, c1 = W, d1 = B,
                a2 = H, b2 = D, c2 = A, d2 = A,
            )
        ),
        _27(
            Matrix3x4(
                scheme = """
                    W - - -
                    - + - -
                    - - + +
                """,
                winItemList = listOf(A),
                a0 = W, b0 = C, c0 = E, d0 = G,
                a1 = F, b1 = A, c1 = D, d1 = B,
                a2 = H, b2 = D, c2 = A, d2 = A,
            )
        ),
        _28(
            Matrix3x4(
                scheme = """
                    - - + +
                    - + W +
                    - - - +
                """,
                winItemList = listOf(A),
                a0 = E, b0 = C, c0 = A, d0 = A,
                a1 = F, b1 = A, c1 = W, d1 = A,
                a2 = H, b2 = D, c2 = G, d2 = A,
            )
        ),
        _29(
            Matrix3x4(
                scheme = """
                    - W - +
                    - + + +
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = E, b0 = W, c0 = I, d0 = A,
                a1 = F, b1 = A, c1 = A, d1 = A,
                a2 = H, b2 = D, c2 = G, d2 = B,
            )
        ),
        _30(
            Matrix3x4(
                scheme = """
                    + - + -
                    - + - W
                    + - + -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = C, c0 = A, d0 = H,
                a1 = F, b1 = A, c1 = I, d1 = W,
                a2 = A, b2 = D, c2 = A, d2 = B,
            )
        ),
    }

    enum class WinColorful(
        override val matrix: Matrix3x4,
    ) : CombinationMatrixEnum3x4 {
        _1(
            Matrix3x4(
                scheme = """
                    A A A -
                    - - - -
                    - B B B
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = A, d0 = G,
                a1 = F, b1 = E, c1 = H, d1 = D,
                a2 = C, b2 = B, c2 = B, d2 = B,
            )
        ),
        _2(
            Matrix3x4(
                scheme = """
                    - A A A
                    - - - -
                    B B B -
                """,
                winItemList = listOf(A, B),
                a0 = G, b0 = A, c0 = A, d0 = A,
                a1 = F, b1 = E, c1 = H, d1 = D,
                a2 = B, b2 = B, c2 = B, d2 = C,
            )
        ),
        _3(
            Matrix3x4(
                scheme = """
                    - - - -
                    A A A -
                    B B B -
                """,
                winItemList = listOf(A, B),
                a0 = G, b0 = F, c0 = G, d0 = E,
                a1 = A, b1 = A, c1 = A, d1 = D,
                a2 = B, b2 = B, c2 = B, d2 = C,
            )
        ),
        _4(
            Matrix3x4(
                scheme = """
                    C C C -
                    A A A -
                    B B B -
                """,
                winItemList = listOf(A, B, C),
                a0 = C, b0 = C, c0 = C, d0 = E,
                a1 = A, b1 = A, c1 = A, d1 = D,
                a2 = B, b2 = B, c2 = B, d2 = H,
            )
        ),
        _5(
            Matrix3x4(
                scheme = """
                    B B B -
                    A A A -
                    - - - -
                """,
                winItemList = listOf(A, B),
                a0 = B, b0 = B, c0 = B, d0 = E,
                a1 = A, b1 = A, c1 = A, d1 = D,
                a2 = D, b2 = F, c2 = H, d2 = C,
            )
        ),
        _6(
            Matrix3x4(
                scheme = """
                    - B B B
                    - A A A
                    - C C C
                """,
                winItemList = listOf(A, B, C),
                a0 = D, b0 = B, c0 = B, d0 = B,
                a1 = H, b1 = A, c1 = A, d1 = A,
                a2 = F, b2 = C, c2 = C, d2 = C,
            )
        ),
        _7(
            Matrix3x4(
                scheme = """
                    A C C C
                    C A B B
                    B B A A
                """,
                winItemList = listOf(A, B, C),
                a0 = A, b0 = C, c0 = C, d0 = C,
                a1 = C, b1 = A, c1 = B, d1 = B,
                a2 = B, b2 = B, c2 = A, d2 = A,
            )
        ),
        _8(
            Matrix3x4(
                scheme = """
                    A A A A
                    C C C A
                    B B B A
                """,
                winItemList = listOf(A, B, C),
                a0 = A, b0 = A, c0 = A, d0 = A,
                a1 = C, b1 = C, c1 = C, d1 = A,
                a2 = B, b2 = B, c2 = B, d2 = A,
            )
        ),
        _9(
            Matrix3x4(
                scheme = """
                    A A - -
                    - - A B
                    B B B -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = E, d0 = D,
                a1 = C, b1 = F, c1 = A, d1 = B,
                a2 = B, b2 = B, c2 = B, d2 = F,
            )
        ),
        _10(
            Matrix3x4(
                scheme = """
                    A A A -
                    - B B B
                    B C C C
                """,
                winItemList = listOf(A, B, C),
                a0 = A, b0 = A, c0 = A, d0 = D,
                a1 = F, b1 = B, c1 = B, d1 = B,
                a2 = B, b2 = C, c2 = C, d2 = C,
            )
        ),
        _11(
            Matrix3x4(
                scheme = """
                    - A A A
                    - - - -
                    - B B B
                """,
                winItemList = listOf(A, B),
                a0 = H, b0 = A, c0 = A, d0 = A,
                a1 = G, b1 = F, c1 = E, d1 = D,
                a2 = I, b2 = B, c2 = B, d2 = B,
            )
        ),
        _12(
            Matrix3x4(
                scheme = """
                    - - - -
                    - A A A
                    - B B B
                """,
                winItemList = listOf(A, B),
                a0 = H, b0 = E, c0 = F, d0 = D,
                a1 = G, b1 = A, c1 = A, d1 = A,
                a2 = I, b2 = B, c2 = B, d2 = B,
            )
        ),
        _13(
            Matrix3x4(
                scheme = """
                    A A A -
                    B B B -
                    - C C C
                """,
                winItemList = listOf(A, B, C),
                a0 = A, b0 = A, c0 = A, d0 = D,
                a1 = B, b1 = B, c1 = B, d1 = G,
                a2 = I, b2 = C, c2 = C, d2 = C,
            )
        ),
        _14(
            Matrix3x4(
                scheme = """
                    A - - A
                    - A A -
                    B B B -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = D, c0 = C, d0 = A,
                a1 = E, b1 = A, c1 = A, d1 = G,
                a2 = B, b2 = B, c2 = B, d2 = I,
            )
        ),
        _15(
            Matrix3x4(
                scheme = """
                    - - A -
                    A A A B
                    - B B B
                """,
                winItemList = listOf(A, B),
                a0 = E, b0 = D, c0 = A, d0 = C,
                a1 = A, b1 = A, c1 = A, d1 = B,
                a2 = H, b2 = B, c2 = B, d2 = B,
            )
        ),
        _16(
            Matrix3x4(
                scheme = """
                    A A - B
                    A A B -
                    - B A -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = I, d0 = B,
                a1 = A, b1 = A, c1 = B, d1 = D,
                a2 = H, b2 = B, c2 = A, d2 = C,
            )
        ),
        _17(
            Matrix3x4(
                scheme = """
                    B A A -
                    A B B -
                    - - - -
                """,
                winItemList = listOf(A, B),
                a0 = B, b0 = A, c0 = A, d0 = I,
                a1 = A, b1 = B, c1 = B, d1 = D,
                a2 = H, b2 = G, c2 = H, d2 = C,
            )
        ),
        _18(
            Matrix3x4(
                scheme = """
                    - A B B
                    - B A A
                    - - A A
                """,
                winItemList = listOf(A, B),
                a0 = I, b0 = A, c0 = B, d0 = B,
                a1 = E, b1 = B, c1 = A, d1 = A,
                a2 = H, b2 = G, c2 = A, d2 = A,
            )
        ),
        _19(
            Matrix3x4(
                scheme = """
                    - - A A
                    - A B A
                    B B A A
                """,
                winItemList = listOf(A, B),
                a0 = I, b0 = D, c0 = A, d0 = A,
                a1 = E, b1 = A, c1 = B, d1 = A,
                a2 = B, b2 = B, c2 = A, d2 = A,
            )
        ),
        _20(
            Matrix3x4(
                scheme = """
                    A B B -
                    B A B -
                    B B A -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = B, c0 = B, d0 = C,
                a1 = B, b1 = A, c1 = B, d1 = D,
                a2 = B, b2 = B, c2 = A, d2 = E,
            )
        ),
        _21(
            Matrix3x4(
                scheme = """
                    A A C -
                    B C A A
                    C B B -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = C, d0 = D,
                a1 = B, b1 = C, c1 = A, d1 = A,
                a2 = C, b2 = B, c2 = B, d2 = E,
            )
        ),
    }

    enum class WinColorfulWithWild(
        override val matrix: Matrix3x4,
    ) : CombinationMatrixEnum3x4 {
        _1(
            Matrix3x4(
                scheme = """
                    A A W -
                    - - - -
                    - B B B
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = W, d0 = G,
                a1 = F, b1 = E, c1 = H, d1 = D,
                a2 = C, b2 = B, c2 = B, d2 = B,
            )
        ),
        _2(
            Matrix3x4(
                scheme = """
                    - A A A
                    - - - -
                    B B B W
                """,
                winItemList = listOf(A, B),
                a0 = G, b0 = A, c0 = A, d0 = A,
                a1 = F, b1 = E, c1 = H, d1 = D,
                a2 = B, b2 = B, c2 = B, d2 = W,
            )
        ),
        _3(
            Matrix3x4(
                scheme = """
                    - - - W
                    A A A -
                    B B B -
                """,
                winItemList = listOf(A, B),
                a0 = G, b0 = F, c0 = G, d0 = W,
                a1 = A, b1 = A, c1 = A, d1 = D,
                a2 = B, b2 = B, c2 = B, d2 = C,
            )
        ),
        _4(
            Matrix3x4(
                scheme = """
                    C C C -
                    A W A -
                    B B B -
                """,
                winItemList = listOf(A, B, C),
                a0 = C, b0 = C, c0 = C, d0 = E,
                a1 = A, b1 = W, c1 = A, d1 = D,
                a2 = B, b2 = B, c2 = B, d2 = H,
            )
        ),
        _5(
            Matrix3x4(
                scheme = """
                    B B B -
                    A A A -
                    - - - W
                """,
                winItemList = listOf(A, B),
                a0 = B, b0 = B, c0 = B, d0 = E,
                a1 = A, b1 = A, c1 = A, d1 = D,
                a2 = D, b2 = F, c2 = H, d2 = W,
            )
        ),
        _6(
            Matrix3x4(
                scheme = """
                    - B B B
                    W A A A
                    - C C C
                """,
                winItemList = listOf(A, B, C),
                a0 = D, b0 = B, c0 = B, d0 = B,
                a1 = W, b1 = A, c1 = A, d1 = A,
                a2 = F, b2 = C, c2 = C, d2 = C,
            )
        ),
        _7(
            Matrix3x4(
                scheme = """
                    W C C C
                    C A W B
                    W B A A
                """,
                winItemList = listOf(A, B, C),
                a0 = W, b0 = C, c0 = C, d0 = C,
                a1 = C, b1 = A, c1 = W, d1 = B,
                a2 = W, b2 = B, c2 = A, d2 = A,
            )
        ),
        _8(
            Matrix3x4(
                scheme = """
                    A W A A
                    C C C A
                    W B B W
                """,
                winItemList = listOf(A, B, C),
                a0 = A, b0 = W, c0 = A, d0 = A,
                a1 = C, b1 = C, c1 = C, d1 = A,
                a2 = W, b2 = B, c2 = B, d2 = W,
            )
        ),
        _9(
            Matrix3x4(
                scheme = """
                    A A - -
                    - - A B
                    B B B W
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = E, d0 = D,
                a1 = C, b1 = F, c1 = A, d1 = B,
                a2 = B, b2 = B, c2 = B, d2 = W,
            )
        ),
        _10(
            Matrix3x4(
                scheme = """
                    A A A -
                    - B W B
                    B C C C
                """,
                winItemList = listOf(A, B, C),
                a0 = A, b0 = A, c0 = A, d0 = D,
                a1 = F, b1 = B, c1 = W, d1 = B,
                a2 = B, b2 = C, c2 = C, d2 = C,
            )
        ),
        _11(
            Matrix3x4(
                scheme = """
                    - A A A
                    - W - -
                    - B B B
                """,
                winItemList = listOf(A, B),
                a0 = H, b0 = A, c0 = A, d0 = A,
                a1 = G, b1 = W, c1 = E, d1 = D,
                a2 = I, b2 = B, c2 = B, d2 = B,
            )
        ),
        _12(
            Matrix3x4(
                scheme = """
                    W - - -
                    - A A A
                    - B B B
                """,
                winItemList = listOf(A, B),
                a0 = W, b0 = E, c0 = F, d0 = D,
                a1 = G, b1 = A, c1 = A, d1 = A,
                a2 = I, b2 = B, c2 = B, d2 = B,
            )
        ),
        _13(
            Matrix3x4(
                scheme = """
                    W A A -
                    B W B -
                    - C C C
                """,
                winItemList = listOf(A, B, C),
                a0 = W, b0 = A, c0 = A, d0 = D,
                a1 = B, b1 = W, c1 = B, d1 = G,
                a2 = I, b2 = C, c2 = C, d2 = C,
            )
        ),
        _14(
            Matrix3x4(
                scheme = """
                    A - - A
                    - W A -
                    B W B -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = D, c0 = C, d0 = A,
                a1 = E, b1 = W, c1 = A, d1 = G,
                a2 = B, b2 = W, c2 = B, d2 = I,
            )
        ),
        _15(
            Matrix3x4(
                scheme = """
                    - - A -
                    A A W B
                    - B B -
                """,
                winItemList = listOf(A, B),
                a0 = E, b0 = D, c0 = A, d0 = C,
                a1 = A, b1 = A, c1 = W, d1 = B,
                a2 = H, b2 = B, c2 = B, d2 = C,
            )
        ),
        _16(
            Matrix3x4(
                scheme = """
                    A A - B
                    A W B -
                    - B A -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = I, d0 = B,
                a1 = A, b1 = W, c1 = B, d1 = D,
                a2 = H, b2 = B, c2 = A, d2 = C,
            )
        ),
        _17(
            Matrix3x4(
                scheme = """
                    B A A -
                    A B B W
                    - - - -
                """,
                winItemList = listOf(A, B),
                a0 = B, b0 = A, c0 = A, d0 = I,
                a1 = A, b1 = B, c1 = B, d1 = W,
                a2 = H, b2 = G, c2 = H, d2 = C,
            )
        ),
        _18(
            Matrix3x4(
                scheme = """
                    - A B B
                    - B A W
                    - W A A
                """,
                winItemList = listOf(A, B),
                a0 = I, b0 = A, c0 = B, d0 = B,
                a1 = E, b1 = B, c1 = A, d1 = W,
                a2 = H, b2 = W, c2 = A, d2 = A,
            )
        ),
        _19(
            Matrix3x4(
                scheme = """
                    - - A A
                    - A B A
                    W B A W
                """,
                winItemList = listOf(A, B),
                a0 = I, b0 = D, c0 = A, d0 = A,
                a1 = E, b1 = A, c1 = B, d1 = A,
                a2 = W, b2 = B, c2 = A, d2 = W,
            )
        ),
        _20(
            Matrix3x4(
                scheme = """
                    A B B -
                    B W B -
                    B B A -
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = B, c0 = B, d0 = C,
                a1 = B, b1 = W, c1 = B, d1 = D,
                a2 = B, b2 = B, c2 = A, d2 = E,
            )
        ),
        _21(
            Matrix3x4(
                scheme = """
                    A A C -
                    B W A A
                    C B B -
                """,
                winItemList = listOf(A, B, C),
                a0 = A, b0 = A, c0 = C, d0 = W,
                a1 = B, b1 = W, c1 = A, d1 = A,
                a2 = C, b2 = B, c2 = B, d2 = W,
            )
        ),
    }

}