package com.veldan.bigwinslots777.actors.slot.util.combination._3x4

import com.veldan.bigwinslots777.actors.slot.util.combination._3x4.Matrix3x4.Item.*

interface CombinationMatrixEnum {
    val matrix: Matrix3x4
}

interface CombinationSlot {
    val combinationList: List<CombinationMatrixEnum>
}

object Combination {

    enum class Mix(
        override val matrix: Matrix3x4,
    ) : CombinationMatrixEnum {
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
    ) : CombinationMatrixEnum {
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
    ) : CombinationMatrixEnum {
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
    ) : CombinationMatrixEnum {
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
    ) : CombinationMatrixEnum {
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
    ) : CombinationMatrixEnum {
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

    enum class SuperWinWild1(
        override val matrix: Matrix3x4,
    ) : CombinationMatrixEnum {
        _1(
            Matrix3x4(
                scheme = """
                    W A A -
                    - - - -
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = W, b0 = A, c0 = A, d0 = G,
                a1 = F, b1 = E, c1 = H, d1 = D,
                a2 = C, b2 = B, c2 = G, d2 = B,
            )
        ),
        _2(
            Matrix3x4(
                scheme = """
                    W - - -
                    - A - -
                    - - A -
                """,
                winItemList = listOf(A),
                a0 = W, b0 = D, c0 = B, d0 = G,
                a1 = F, b1 = A, c1 = H, d1 = D,
                a2 = C, b2 = B, c2 = A, d2 = B,
            )
        ),
        _3(
            Matrix3x4(
                scheme = """
                    W - - -
                    A - - -
                    A - - -
                """,
                winItemList = listOf(A),
                a0 = W, b0 = D, c0 = B, d0 = G,
                a1 = A, b1 = C, c1 = H, d1 = D,
                a2 = A, b2 = B, c2 = F, d2 = E,
            )
        ),
        _4(
            Matrix3x4(
                scheme = """
                    W A A -
                    A - - -
                    A - - -
                """,
                winItemList = listOf(A),
                a0 = W, b0 = A, c0 = A, d0 = G,
                a1 = A, b1 = C, c1 = H, d1 = D,
                a2 = A, b2 = B, c2 = F, d2 = E,
            )
        ),
        _5(
            Matrix3x4(
                scheme = """
                    W A A -
                    A B - -
                    A - B -
                """,
                winItemList = listOf(A, B),
                a0 = W, b0 = A, c0 = A, d0 = G,
                a1 = A, b1 = B, c1 = H, d1 = D,
                a2 = A, b2 = E, c2 = B, d2 = E,
            )
        ),
        _6(
            Matrix3x4(
                scheme = """
                    - W A A
                    - - - -
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = C, b0 = W, c0 = A, d0 = A,
                a1 = F, b1 = I, c1 = H, d1 = D,
                a2 = C, b2 = E, c2 = B, d2 = E,
            )
        ),
        _7(
            Matrix3x4(
                scheme = """
                    - W A A
                    - A - -
                    - A - -
                """,
                winItemList = listOf(A),
                a0 = C, b0 = W, c0 = A, d0 = A,
                a1 = F, b1 = A, c1 = H, d1 = D,
                a2 = C, b2 = A, c2 = B, d2 = E,
            )
        ),
        _8(
            Matrix3x4(
                scheme = """
                    - W A A
                    - B - -
                    - B - -
                """,
                winItemList = listOf(A, B),
                a0 = C, b0 = W, c0 = A, d0 = A,
                a1 = F, b1 = B, c1 = H, d1 = D,
                a2 = C, b2 = B, c2 = C, d2 = E,
            )
        ),
        _9(
            Matrix3x4(
                scheme = """
                    - W A A
                    - B C -
                    - B - C
                """,
                winItemList = listOf(A, B, C),
                a0 = I, b0 = W, c0 = A, d0 = A,
                a1 = F, b1 = B, c1 = C, d1 = D,
                a2 = H, b2 = B, c2 = E, d2 = C,
            )
        ),
        _10(
            Matrix3x4(
                scheme = """
                    - A W A
                    - - - -
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = I, b0 = A, c0 = W, d0 = A,
                a1 = F, b1 = B, c1 = C, d1 = D,
                a2 = H, b2 = G, c2 = E, d2 = B,
            )
        ),
        _11(
            Matrix3x4(
                scheme = """
                    - A W A
                    - - B -
                    - - B -
                """,
                winItemList = listOf(A, B),
                a0 = I, b0 = A, c0 = W, d0 = A,
                a1 = F, b1 = E, c1 = B, d1 = D,
                a2 = H, b2 = G, c2 = B, d2 = F,
            )
        ),
        _12(
            Matrix3x4(
                scheme = """
                    - A A W
                    - - A -
                    - A - -
                """,
                winItemList = listOf(A),
                a0 = I, b0 = A, c0 = A, d0 = W,
                a1 = F, b1 = E, c1 = A, d1 = D,
                a2 = H, b2 = A, c2 = B, d2 = F,
            )
        ),
        _13(
            Matrix3x4(
                scheme = """
                    - - - -
                    W A A -
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = I, b0 = H, c0 = I, d0 = C,
                a1 = W, b1 = A, c1 = A, d1 = D,
                a2 = H, b2 = B, c2 = E, d2 = F,
            )
        ),
        _14(
            Matrix3x4(
                scheme = """
                    - B B -
                    W A A B
                    - - - A
                """,
                winItemList = listOf(A, B),
                a0 = I, b0 = B, c0 = B, d0 = C,
                a1 = W, b1 = A, c1 = A, d1 = B,
                a2 = H, b2 = C, c2 = E, d2 = A,
            )
        ),
        _15(
            Matrix3x4(
                scheme = """
                    B - C -
                    A W A A
                    C - B A
                """,
                winItemList = listOf(A, B, C),
                a0 = B, b0 = E, c0 = C, d0 = F,
                a1 = A, b1 = W, c1 = A, d1 = A,
                a2 = C, b2 = D, c2 = B, d2 = A,
            )
        ),
        _16(
            Matrix3x4(
                scheme = """
                    - - - -
                    A A W -
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = B, b0 = E, c0 = C, d0 = F,
                a1 = A, b1 = A, c1 = W, d1 = G,
                a2 = C, b2 = D, c2 = B, d2 = I,
            )
        ),
        _17(
            Matrix3x4(
                scheme = """
                    - - - -
                    - A W A
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = B, b0 = E, c0 = C, d0 = F,
                a1 = D, b1 = A, c1 = W, d1 = A,
                a2 = C, b2 = D, c2 = B, d2 = I,
            )
        ),
        _18(
            Matrix3x4(
                scheme = """
                    - A - -
                    - A W A
                    - - - A
                """,
                winItemList = listOf(A),
                a0 = B, b0 = A, c0 = C, d0 = F,
                a1 = D, b1 = A, c1 = W, d1 = A,
                a2 = C, b2 = D, c2 = B, d2 = A,
            )
        ),
        _19(
            Matrix3x4(
                scheme = """
                    - A - A
                    - A W A
                    - A - A
                """,
                winItemList = listOf(A),
                a0 = B, b0 = A, c0 = C, d0 = A,
                a1 = D, b1 = A, c1 = W, d1 = A,
                a2 = C, b2 = A, c2 = B, d2 = A,
            )
        ),
        _20(
            Matrix3x4(
                scheme = """
                    - A - A
                    B B W B
                    - A - A
                """,
                winItemList = listOf(A, B),
                a0 = D, b0 = A, c0 = F, d0 = A,
                a1 = B, b1 = B, c1 = W, d1 = B,
                a2 = C, b2 = A, c2 = I, d2 = A,
            )
        ),
        _21(
            Matrix3x4(
                scheme = """
                    - - - -
                    - A A W
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = D, b0 = E, c0 = F, d0 = D,
                a1 = B, b1 = A, c1 = A, d1 = W,
                a2 = C, b2 = H, c2 = G, d2 = I,
            )
        ),
        _22(
            Matrix3x4(
                scheme = """
                    - - - A
                    - A A W
                    - - - A
                """,
                winItemList = listOf(A),
                a0 = D, b0 = E, c0 = F, d0 = A,
                a1 = B, b1 = A, c1 = A, d1 = W,
                a2 = C, b2 = H, c2 = G, d2 = A,
            )
        ),
        _23(
            Matrix3x4(
                scheme = """
                    B B B A
                    - A A W
                    C C C A
                """,
                winItemList = listOf(A, B, C),
                a0 = B, b0 = B, c0 = B, d0 = A,
                a1 = D, b1 = A, c1 = A, d1 = W,
                a2 = C, b2 = C, c2 = C, d2 = A,
            )
        ),
        _24(
            Matrix3x4(
                scheme = """
                    - A A -
                    - - - W
                    - B B -
                """,
                winItemList = listOf(A, B),
                a0 = D, b0 = A, c0 = A, d0 = D,
                a1 = F, b1 = F, c1 = E, d1 = W,
                a2 = C, b2 = B, c2 = B, d2 = I,
            )
        ),
        _25(
            Matrix3x4(
                scheme = """
                    - A A C
                    - C C W
                    - B B C
                """,
                winItemList = listOf(A, B, C),
                a0 = D, b0 = A, c0 = A, d0 = C,
                a1 = F, b1 = C, c1 = C, d1 = W,
                a2 = E, b2 = B, c2 = B, d2 = C,
            )
        ),
        _26(
            Matrix3x4(
                scheme = """
                    A A A -
                    - - - W
                    - A A -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = A, c0 = A, d0 = C,
                a1 = F, b1 = C, c1 = D, d1 = W,
                a2 = E, b2 = A, c2 = A, d2 = B,
            )
        ),
        _27(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - - -
                    W A A -
                """,
                winItemList = listOf(A),
                a0 = E, b0 = H, c0 = H, d0 = C,
                a1 = F, b1 = C, c1 = D, d1 = G,
                a2 = W, b2 = A, c2 = A, d2 = B,
            )
        ),
        _28(
            Matrix3x4(
                scheme = """
                    - - A -
                    - A - -
                    W - - -
                """,
                winItemList = listOf(A),
                a0 = E, b0 = H, c0 = A, d0 = C,
                a1 = F, b1 = A, c1 = D, d1 = G,
                a2 = W, b2 = B, c2 = I, d2 = B,
            )
        ),
        _29(
            Matrix3x4(
                scheme = """
                    - - A -
                    - A - -
                    W A A -
                """,
                winItemList = listOf(A),
                a0 = E, b0 = E, c0 = A, d0 = C,
                a1 = F, b1 = A, c1 = D, d1 = G,
                a2 = W, b2 = A, c2 = A, d2 = B,
            )
        ),
        _30(
            Matrix3x4(
                scheme = """
                    - - A A
                    - A - -
                    W A A A
                """,
                winItemList = listOf(A),
                a0 = E, b0 = I, c0 = A, d0 = A,
                a1 = F, b1 = A, c1 = D, d1 = G,
                a2 = W, b2 = A, c2 = A, d2 = A,
            )
        ),
        _31(
            Matrix3x4(
                scheme = """
                    - - B -
                    - B - -
                    W A A A
                """,
                winItemList = listOf(A, B),
                a0 = E, b0 = G, c0 = B, d0 = C,
                a1 = F, b1 = B, c1 = D, d1 = G,
                a2 = W, b2 = A, c2 = A, d2 = A,
            )
        ),
        _32(
            Matrix3x4(
                scheme = """
                    - A A A
                    - - - B
                    W B B -
                """,
                winItemList = listOf(A, B),
                a0 = E, b0 = A, c0 = A, d0 = A,
                a1 = F, b1 = I, c1 = D, d1 = B,
                a2 = W, b2 = B, c2 = B, d2 = C,
            )
        ),
        _33(
            Matrix3x4(
                scheme = """
                    - - - A
                    - A A A
                    W - - A
                """,
                winItemList = listOf(A),
                a0 = E, b0 = I, c0 = B, d0 = A,
                a1 = F, b1 = A, c1 = A, d1 = A,
                a2 = W, b2 = D, c2 = B, d2 = A,
            )
        ),
        _34(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - - -
                    A W A -
                """,
                winItemList = listOf(A),
                a0 = E, b0 = I, c0 = B, d0 = E,
                a1 = F, b1 = C, c1 = D, d1 = C,
                a2 = A, b2 = W, c2 = A, d2 = B,
            )
        ),
        _35(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - - -
                    A W A A
                """,
                winItemList = listOf(A),
                a0 = E, b0 = I, c0 = B, d0 = E,
                a1 = F, b1 = C, c1 = D, d1 = C,
                a2 = A, b2 = W, c2 = A, d2 = A,
            )
        ),
        _36(
            Matrix3x4(
                scheme = """
                    - A A A
                    - - - -
                    B W B -
                """,
                winItemList = listOf(A, B),
                a0 = E, b0 = A, c0 = A, d0 = A,
                a1 = F, b1 = C, c1 = D, d1 = C,
                a2 = B, b2 = W, c2 = B, d2 = D,
            )
        ),
        _37(
            Matrix3x4(
                scheme = """
                    - A A A
                    - C C C
                    B W B B
                """,
                winItemList = listOf(A, B, C),
                a0 = E, b0 = A, c0 = A, d0 = A,
                a1 = F, b1 = C, c1 = C, d1 = C,
                a2 = B, b2 = W, c2 = B, d2 = B,
            )
        ),
        _38(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - - -
                    - W A A
                """,
                winItemList = listOf(A),
                a0 = E, b0 = D, c0 = I, d0 = H,
                a1 = F, b1 = C, c1 = F, d1 = E,
                a2 = B, b2 = W, c2 = A, d2 = A,
            )
        ),
        _39(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - A A
                    - W - -
                """,
                winItemList = listOf(A),
                a0 = E, b0 = D, c0 = I, d0 = H,
                a1 = F, b1 = C, c1 = A, d1 = A,
                a2 = B, b2 = W, c2 = F, d2 = H,
            )
        ),
        _40(
            Matrix3x4(
                scheme = """
                    C C C C
                    C C A A
                    C W B B
                """,
                winItemList = listOf(A, B, C),
                a0 = C, b0 = C, c0 = C, d0 = C,
                a1 = C, b1 = C, c1 = A, d1 = A,
                a2 = C, b2 = W, c2 = B, d2 = B,
            )
        ),
        _41(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - - -
                    A A W -
                """,
                winItemList = listOf(A),
                a0 = C, b0 = E, c0 = H, d0 = E,
                a1 = F, b1 = G, c1 = I, d1 = D,
                a2 = A, b2 = A, c2 = W, d2 = B,
            )
        ),
        _42(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - - -
                    A A W A
                """,
                winItemList = listOf(A),
                a0 = C, b0 = E, c0 = H, d0 = E,
                a1 = F, b1 = G, c1 = I, d1 = D,
                a2 = A, b2 = A, c2 = W, d2 = A,
            )
        ),
        _43(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - - -
                    - A W A
                """,
                winItemList = listOf(A),
                a0 = C, b0 = E, c0 = H, d0 = E,
                a1 = F, b1 = G, c1 = I, d1 = D,
                a2 = E, b2 = A, c2 = W, d2 = A,
            )
        ),
        _44(
            Matrix3x4(
                scheme = """
                    - - - -
                    A A - -
                    - A W A
                """,
                winItemList = listOf(A),
                a0 = C, b0 = E, c0 = H, d0 = E,
                a1 = A, b1 = A, c1 = I, d1 = D,
                a2 = E, b2 = A, c2 = W, d2 = A,
            )
        ),
        _45(
            Matrix3x4(
                scheme = """
                    A A A A
                    - - - -
                    - B W B
                """,
                winItemList = listOf(A, B),
                a0 = A, b0 = A, c0 = A, d0 = A,
                a1 = E, b1 = F, c1 = I, d1 = D,
                a2 = E, b2 = B, c2 = W, d2 = B,
            )
        ),
        _46(
            Matrix3x4(
                scheme = """
                    A A A C
                    - A C C
                    - B W B
                """,
                winItemList = listOf(A, B, C),
                a0 = A, b0 = A, c0 = A, d0 = C,
                a1 = F, b1 = A, c1 = C, d1 = C,
                a2 = E, b2 = B, c2 = W, d2 = B,
            )
        ),
        _47(
            Matrix3x4(
                scheme = """
                    A A A A
                    C C C C
                    B B W B
                """,
                winItemList = listOf(A, B, C),
                a0 = A, b0 = A, c0 = A, d0 = A,
                a1 = C, b1 = C, c1 = C, d1 = C,
                a2 = B, b2 = B, c2 = W, d2 = B,
            )
        ),
        _48(
            Matrix3x4(
                scheme = """
                    - A - -
                    - - A -
                    - - - W
                """,
                winItemList = listOf(A),
                a0 = E, b0 = A, c0 = B, d0 = I,
                a1 = F, b1 = C, c1 = A, d1 = C,
                a2 = B, b2 = F, c2 = D, d2 = W,
            )
        ),
        _49(
            Matrix3x4(
                scheme = """
                    - A - -
                    - - A -
                    - B B W
                """,
                winItemList = listOf(A, B),
                a0 = E, b0 = A, c0 = G, d0 = I,
                a1 = F, b1 = C, c1 = A, d1 = C,
                a2 = G, b2 = B, c2 = B, d2 = W,
            )
        ),
        _50(
            Matrix3x4(
                scheme = """
                    - C C C
                    A A A C
                    - B B W
                """,
                winItemList = listOf(A, B, C),
                a0 = E, b0 = C, c0 = C, d0 = C,
                a1 = A, b1 = A, c1 = A, d1 = C,
                a2 = G, b2 = B, c2 = B, d2 = W,
            )
        ),
    }

    enum class SuperWinWild2(
        override val matrix: Matrix3x4,
    ) : CombinationMatrixEnum {
        _1(
            Matrix3x4(
                scheme = """
                    W W A -
                    - - A -
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = W, b0 = W, c0 = A, d0 = G,
                a1 = F, b1 = E, c1 = A, d1 = D,
                a2 = C, b2 = B, c2 = G, d2 = B,
            )
        ),
        _2(
            Matrix3x4(
                scheme = """
                    A W W A
                    - - - A
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = W, c0 = W, d0 = A,
                a1 = F, b1 = E, c1 = H, d1 = A,
                a2 = C, b2 = B, c2 = G, d2 = B,
            )
        ),
        _3(
            Matrix3x4(
                scheme = """
                    - A W W
                    - A - -
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = B, b0 = A, c0 = W, d0 = W,
                a1 = F, b1 = A, c1 = H, d1 = D,
                a2 = C, b2 = B, c2 = G, d2 = B,
            )
        ),
        _4(
            Matrix3x4(
                scheme = """
                    - A A W
                    W - - -
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = B, b0 = A, c0 = A, d0 = W,
                a1 = W, b1 = E, c1 = H, d1 = D,
                a2 = C, b2 = D, c2 = G, d2 = B,
            )
        ),
        _5(
            Matrix3x4(
                scheme = """
                    - - A -
                    W W A -
                    - - A -
                """,
                winItemList = listOf(A),
                a0 = B, b0 = I,       c0 = A, d0 = H,
                a1 = W, b1 = W, c1 = A, d1 = D,
                a2 = C, b2 = D,       c2 = A, d2 = B,
            )
        ),
        _6(
            Matrix3x4(
                scheme = """
                    A - - B
                    A W W C
                    A B - C
                """,
                winItemList = listOf(A, B, C),
                a0 = A, b0 = I,    c0 = E,    d0 = B,
                a1 = A, b1 = W, c1 = W, d1 = C,
                a2 = A, b2 = B,    c2 = G,    d2 = C,
            )
        ),
        _7(
            Matrix3x4(
                scheme = """
                    B - - A
                    C W W A
                    A - B -
                """,
                winItemList = listOf(A, B, C),
                a0 = B, b0 = I,    c0 = E,    d0 = A,
                a1 = C, b1 = W, c1 = W, d1 = A,
                a2 = A, b2 = F,    c2 = B,    d2 = D,
            )
        ),
        _8(
            Matrix3x4(
                scheme = """
                    - A - -
                    - A W W
                    - A - -
                """,
                winItemList = listOf(A),
                a0 = B, b0 = A, c0 = E, d0 = H,
                a1 = D, b1 = A, c1 = W, d1 = W,
                a2 = C, b2 = A, c2 = G, d2 = B,
            )
        ),
        _9(
            Matrix3x4(
                scheme = """
                    - A - -
                    A A W W
                    - B - -
                """,
                winItemList = listOf(A, B),
                a0 = E, b0 = A, c0 = E, d0 = H,
                a1 = A, b1 = A, c1 = W, d1 = W,
                a2 = C, b2 = B, c2 = G, d2 = C,
            )
        ),
        _10(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - W -
                    W A - -
                """,
                winItemList = listOf(A),
                a0 = B,    b0 = I, c0 = E,    d0 = H,
                a1 = F,    b1 = D, c1 = W, d1 = F,
                a2 = W, b2 = A, c2 = G,    d2 = B,
            )
        ),
        _11(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - W A
                    W A - -
                """,
                winItemList = listOf(A),
                a0 = B, b0 = I,    c0 = E, d0 = H,
                a1 = F, b1 = D,    c1 = W, d1 = A,
                a2 = W, b2 = A, c2 = G, d2 = B,
            )
        ),
        _12(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - A W
                    W A - -
                """,
                winItemList = listOf(A),
                a0 = B, b0 = I, c0 = E, d0 = H,
                a1 = F, b1 = D, c1 = A, d1 = W,
                a2 = W, b2 = A, c2 = G, d2 = B,
            )
        ),
        _13(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - A -
                    W W A -
                """,
                winItemList = listOf(A),
                a0 = B, b0 = I, c0 = E, d0 = H,
                a1 = F, b1 = D, c1 = A, d1 = I,
                a2 = W, b2 = W, c2 = A, d2 = F,
            )
        ),
        _14(
            Matrix3x4(
                scheme = """
                    - - - -
                    A - - B
                    A W W C
                """,
                winItemList = listOf(A, B, C),
                a0 = G, b0 = I, c0 = E, d0 = H,
                a1 = A, b1 = D, c1 = F, d1 = B,
                a2 = A, b2 = W, c2 = W, d2 = C,
            )
        ),
        _15(
            Matrix3x4(
                scheme = """
                    - - - -
                    B - - A
                    B W W A
                """,
                winItemList = listOf(A, B),
                a0 = C, b0 = I, c0 = E, d0 = H,
                a1 = B, b1 = D, c1 = G, d1 = A,
                a2 = B, b2 = W, c2 = W, d2 = A,
            )
        ),
        _16(
            Matrix3x4(
                scheme = """
                    - - - -
                    - B - -
                    - A W W
                """,
                winItemList = listOf(A, B),
                a0 = F, b0 = I, c0 = E, d0 = H,
                a1 = F, b1 = B, c1 = H, d1 = I,
                a2 = H, b2 = A, c2 = W, d2 = W,
            )
        ),
        _17(
            Matrix3x4(
                scheme = """
                    W - - -
                    - A A -
                    - - - W
                """,
                winItemList = listOf(A),
                a0 = W, b0 = I, c0 = E, d0 = H,
                a1 = F, b1 = A, c1 = A, d1 = I,
                a2 = H, b2 = B, c2 = D, d2 = W,
            )
        ),
        _18(
            Matrix3x4(
                scheme = """
                    W A A -
                    - - - -
                    - B B W
                """,
                winItemList = listOf(A, B),
                a0 = W, b0 = A, c0 = A, d0 = H,
                a1 = F, b1 = E, c1 = G, d1 = I,
                a2 = H, b2 = B, c2 = B, d2 = W,
            )
        ),
        _19(
            Matrix3x4(
                scheme = """
                    W A A -
                    - - - -
                    W B B -
                """,
                winItemList = listOf(A, B),
                a0 = W, b0 = A, c0 = A, d0 = H,
                a1 = F, b1 = E, c1 = G, d1 = I,
                a2 = W, b2 = B, c2 = B, d2 = G,
            )
        ),
        _20(
            Matrix3x4(
                scheme = """
                    W A A -
                    W B B -
                    - - - -
                """,
                winItemList = listOf(A, B),
                a0 = W, b0 = A, c0 = A, d0 = H,
                a1 = W, b1 = B, c1 = B, d1 = I,
                a2 = H, b2 = I, c2 = G, d2 = G,
            )
        ),
        _21(
            Matrix3x4(
                scheme = """
                    W A A -
                    W B B A
                    - - - B
                """,
                winItemList = listOf(A, B),
                a0 = W, b0 = A, c0 = A, d0 = H,
                a1 = W, b1 = B, c1 = B, d1 = A,
                a2 = H, b2 = I, c2 = G, d2 = B,
            )
        ),
        _22(
            Matrix3x4(
                scheme = """
                    W A A W
                    - - - -
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = W, b0 = A, c0 = A, d0 = W,
                a1 = D, b1 = G, c1 = B, d1 = C,
                a2 = H, b2 = I, c2 = I, d2 = E,
            )
        ),
        _23(
            Matrix3x4(
                scheme = """
                    W A A W
                    - - B -
                    B B - -
                """,
                winItemList = listOf(A, B),
                a0 = W, b0 = A, c0 = A, d0 = W,
                a1 = D, b1 = G, c1 = B, d1 = C,
                a2 = B, b2 = B, c2 = I, d2 = E,
            )
        ),
        _24(
            Matrix3x4(
                scheme = """
                    W A A W
                    - B - -
                    - - B B
                """,
                winItemList = listOf(A, B),
                a0 = W, b0 = A, c0 = A, d0 = W,
                a1 = D, b1 = B, c1 = E, d1 = C,
                a2 = H, b2 = F, c2 = B, d2 = B,
            )
        ),
        _25(
            Matrix3x4(
                scheme = """
                    W A A W
                    - B C -
                    C C B B
                """,
                winItemList = listOf(A, B),
                a0 = W, b0 = A, c0 = A, d0 = W,
                a1 = I,    b1 = B, c1 = C, d1 = I,
                a2 = C,    b2 = C, c2 = B, d2 = B,
            )
        ),
        _26(
            Matrix3x4(
                scheme = """
                    W - - -
                    - A A W
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = W, b0 = D, c0 = F, d0 = G,
                a1 = D, b1 = A, c1 = A, d1 = W,
                a2 = H, b2 = F, c2 = I, d2 = B,
            )
        ),
        _27(
            Matrix3x4(
                scheme = """
                    W - - -
                    B A A W
                    B B B -
                """,
                winItemList = listOf(A, B),
                a0 = W, b0 = D, c0 = F, d0 = G,
                a1 = B, b1 = A, c1 = A, d1 = W,
                a2 = B, b2 = B, c2 = B, d2 = I,
            )
        ),
        _28(
            Matrix3x4(
                scheme = """
                    W A A A
                    B - - W
                    B B B A
                """,
                winItemList = listOf(A, B),
                a0 = W, b0 = A, c0 = A, d0 = A,
                a1 = B, b1 = G, c1 = D, d1 = W,
                a2 = B, b2 = B, c2 = B, d2 = A,
            )
        ),
        _29(
            Matrix3x4(
                scheme = """
                    W - - -
                    A A A A
                    - - - W
                """,
                winItemList = listOf(A),
                a0 = W, b0 = D, c0 = C, d0 = B,
                a1 = A, b1 = A, c1 = A, d1 = A,
                a2 = B, b2 = C, c2 = D, d2 = W,
            )
        ),
        _30(
            Matrix3x4(
                scheme = """
                    W A A B
                    C B A B
                    C C W B
                """,
                winItemList = listOf(A, B, C),
                a0 = W, b0 = A, c0 = A, d0 = B,
                a1 = C, b1 = B, c1 = A, d1 = B,
                a2 = C, b2 = C, c2 = W, d2 = B,
            )
        ),
    }

    enum class SuperWinWild3(
        override val matrix: Matrix3x4,
    ) : CombinationMatrixEnum {
        _1(
            Matrix3x4(
                scheme = """
                    W W W A
                    A - A A
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = W, b0 = W, c0 = W, d0 = A,
                a1 = A, b1 = E, c1 = A, d1 = A,
                a2 = C, b2 = B, c2 = G, d2 = B,
            )
        ),
        _2(
            Matrix3x4(
                scheme = """
                    A W W W
                    A A - A
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = A, b0 = W, c0 = W, d0 = W,
                a1 = A, b1 = A, c1 = B, d1 = A,
                a2 = C, b2 = I, c2 = G, d2 = E,
            )
        ),
        _3(
            Matrix3x4(
                scheme = """
                    - A W W
                    W A - -
                    - - - -
                """,
                winItemList = listOf(A),
                a0 = C, b0 = A, c0 = W, d0 = W,
                a1 = W, b1 = A, c1 = B, d1 = C,
                a2 = F, b2 = I, c2 = G, d2 = E,
            )
        ),
        _4(
            Matrix3x4(
                scheme = """
                    - - A W
                    W W A -
                    - - A -
                """,
                winItemList = listOf(A),
                a0 = C, b0 = D, c0 = A, d0 = W,
                a1 = W, b1 = W, c1 = A, d1 = C,
                a2 = F, b2 = I, c2 = A, d2 = E,
            )
        ),
        _5(
            Matrix3x4(
                scheme = """
                    B - A A
                    W W W A
                    B - A A
                """,
                winItemList = listOf(A, B),
                a0 = B, b0 = D, c0 = A, d0 = A,
                a1 = W, b1 = W, c1 = W, d1 = A,
                a2 = B, b2 = I, c2 = A, d2 = A,
            )
        ),
        _6(
            Matrix3x4(
                scheme = """
                    B C - A
                    A W W W
                    B C - A
                """,
                winItemList = listOf(A, B, C),
                a0 = B, b0 = C, c0 = E, d0 = A,
                a1 = A, b1 = W, c1 = W, d1 = W,
                a2 = B, b2 = C, c2 = G, d2 = A,
            )
        ),
        _7(
            Matrix3x4(
                scheme = """
                    - A - -
                    - A W W
                    W A - -
                """,
                winItemList = listOf(A),
                a0 = B,    b0 = A, c0 = E,    d0 = D,
                a1 = C,    b1 = A, c1 = W, d1 = W,
                a2 = W, b2 = A, c2 = G,    d2 = F,
            )
        ),
        _8(
            Matrix3x4(
                scheme = """
                    - - A -
                    - - A W
                    W W A -
                """,
                winItemList = listOf(A),
                a0 = B,    b0 = I,    c0 = A,    d0 = D,
                a1 = C,    b1 = G,    c1 = A, d1 = W,
                a2 = W, b2 = W, c2 = A,    d2 = F,
            )
        ),
        _9(
            Matrix3x4(
                scheme = """
                    - - - -
                    A - C B
                    W W W A
                """,
                winItemList = listOf(A, B, C),
                a0 = D,    b0 = I,    c0 = H,    d0 = D,
                a1 = A,    b1 = G,    c1 = C,    d1 = B,
                a2 = W, b2 = W, c2 = W, d2 = A,
            )
        ),
        _10(
            Matrix3x4(
                scheme = """
                    - - - -
                    A A A A
                    A W W W
                """,
                winItemList = listOf(A),
                a0 = D,    b0 = I,    c0 = H,    d0 = D,
                a1 = A,    b1 = A,    c1 = A,    d1 = A,
                a2 = A, b2 = W, c2 = W, d2 = W,
            )
        ),
        _11(
            Matrix3x4(
                scheme = """
                    B B B -
                    A A A A
                    A W W W
                """,
                winItemList = listOf(A, B),
                a0 = B,    b0 = B,    c0 = B,    d0 = D,
                a1 = A,    b1 = A,    c1 = A,    d1 = A,
                a2 = A, b2 = W, c2 = W, d2 = W,
            )
        ),
        _12(
            Matrix3x4(
                scheme = """
                    W - W -
                    - W - -
                    A - A -
                """,
                winItemList = listOf(A),
                a0 = W, b0 = B, c0 = W, d0 = D,
                a1 = C, b1 = W, c1 = H, d1 = G,
                a2 = A, b2 = F, c2 = A, d2 = I,
            )
        ),
        _13(
            Matrix3x4(
                scheme = """
                    W - - W
                    - W A -
                    - - A -
                """,
                winItemList = listOf(A),
                a0 = W, b0 = B, c0 = D, d0 = W,
                a1 = C, b1 = W, c1 = A, d1 = G,
                a2 = I, b2 = F, c2 = A, d2 = I,
            )
        ),
        _14(
            Matrix3x4(
                scheme = """
                    W - - W
                    B B A A
                    - W A -
                """,
                winItemList = listOf(A),
                a0 = W, b0 = F, c0 = D, d0 = W,
                a1 = B, b1 = B, c1 = A, d1 = A,
                a2 = I, b2 = W, c2 = A, d2 = I,
            )
        ),
        _15(
            Matrix3x4(
                scheme = """
                    W W A -
                    - - A -
                    - W B B
                """,
                winItemList = listOf(A, B),
                a0 = W, b0 = W, c0 = A, d0 = C,
                a1 = G, b1 = H, c1 = A, d1 = D,
                a2 = I, b2 = W, c2 = B, d2 = B,
            )
        ),
        _16(
            Matrix3x4(
                scheme = """
                    W C -  -
                    - A W W
                    - B - -
                """,
                winItemList = listOf(A, B, C),
                a0 = W, b0 = C, c0 = F, d0 = E,
                a1 = G, b1 = A, c1 = W, d1 = W,
                a2 = I, b2 = B, c2 = G, d2 = D,
            )
        ),
        _17(
            Matrix3x4(
                scheme = """
                    W - -  -
                    A A A W
                    W - - -
                """,
                winItemList = listOf(A),
                a0 = W, b0 = C, c0 = F, d0 = E,
                a1 = A, b1 = A, c1 = A, d1 = W,
                a2 = W, b2 = B, c2 = G, d2 = D,
            )
        ),
        _18(
            Matrix3x4(
                scheme = """
                    W B B W
                    A A A W
                    - - - -
                """,
                winItemList = listOf(A, B),
                a0 = W, b0 = B, c0 = B, d0 = W,
                a1 = A, b1 = A, c1 = A, d1 = W,
                a2 = H, b2 = C, c2 = G, d2 = D,
            )
        ),
        _19(
            Matrix3x4(
                scheme = """
                    B B B W
                    A A A A
                    C W W C
                """,
                winItemList = listOf(A, B, C),
                a0 = B, b0 = B, c0 = B, d0 = W,
                a1 = A, b1 = A, c1 = A, d1 = A,
                a2 = C, b2 = W, c2 = W, d2 = C,
            )
        ),
        _20(
            Matrix3x4(
                scheme = """
                    B W B -
                    - - C W
                    W A A C
                """,
                winItemList = listOf(A, B, C),
                a0 = B, b0 = W, c0 = B, d0 = D,
                a1 = F, b1 = I, c1 = C, d1 = W,
                a2 = W, b2 = A, c2 = A, d2 = C,
            )
        ),
    }

    enum class SuperFailWild1(
        override val matrix: Matrix3x4,
    ) : CombinationMatrixEnum {
        _1(
            Matrix3x4(
                scheme = """
                    W - - -
                    - - - -
                    - - - -
                """,
                a0 = W, b0 = B, c0 = A, d0 = G,
                a1 = F, b1 = E, c1 = H, d1 = D,
                a2 = C, b2 = B, c2 = G, d2 = B,
            )
        ),
        _2(
            Matrix3x4(
                scheme = """
                    - W - -
                    - - - -
                    - - - -
                """,
                a0 = B, b0 = W, c0 = A, d0 = G,
                a1 = F, b1 = E, c1 = H, d1 = D,
                a2 = C, b2 = B, c2 = G, d2 = B,
            )
        ),
        _3(
            Matrix3x4(
                scheme = """
                    - - W -
                    - - - -
                    - - - -
                """,
                a0 = B, b0 = A, c0 = W, d0 = G,
                a1 = F, b1 = E, c1 = H, d1 = D,
                a2 = C, b2 = A, c2 = G, d2 = B,
            )
        ),
        _4(
            Matrix3x4(
                scheme = """
                    - - - W
                    - - - -
                    - - - -
                """,
                a0 = B, b0 = A, c0 = C, d0 = W,
                a1 = F, b1 = E, c1 = H, d1 = D,
                a2 = C, b2 = A, c2 = G, d2 = B,
            )
        ),
        _5(
            Matrix3x4(
                scheme = """
                    - - - -
                    W - - -
                    - - - -
                """,
                a0 = H, b0 = A, c0 = C, d0 = D,
                a1 = W, b1 = E, c1 = H, d1 = D,
                a2 = C, b2 = B, c2 = G, d2 = B,
            )
        ),
        _6(
            Matrix3x4(
                scheme = """
                    - - - -
                    - W - -
                    - - - -
                """,
                a0 = H, b0 = A, c0 = B, d0 = D,
                a1 = E, b1 = W, c1 = H, d1 = D,
                a2 = C, b2 = D, c2 = G, d2 = B,
            )
        ),
        _7(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - W -
                    - - - -
                """,
                a0 = H, b0 = A, c0 = C, d0 = I,
                a1 = E, b1 = G, c1 = W, d1 = D,
                a2 = C, b2 = B, c2 = G, d2 = B,
            )
        ),
        _8(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - - W
                    - - - -
                """,
                a0 = H, b0 = A, c0 = C, d0 = I,
                a1 = E, b1 = G, c1 = D, d1 = W,
                a2 = C, b2 = B, c2 = G, d2 = B,
            )
        ),
        _9(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - - -
                    W - - -
                """,
                a0 = H, b0 = A, c0 = C, d0 = I,
                a1 = E, b1 = G, c1 = D, d1 = I,
                a2 = W, b2 = B, c2 = G, d2 = B,
            )
        ),
        _10(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - - -
                    - W - -
                """,
                a0 = H, b0 = A, c0 = C, d0 = C,
                a1 = E, b1 = I, c1 = D, d1 = B,
                a2 = B, b2 = W, c2 = G, d2 = B,
            )
        ),
        _11(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - - -
                    - - W -
                """,
                a0 = H, b0 = A, c0 = A, d0 = C,
                a1 = E, b1 = I, c1 = D, d1 = B,
                a2 = B, b2 = H, c2 = W, d2 = I,
            )
        ),
        _12(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - - -
                    - - - W
                """,
                a0 = H, b0 = H, c0 = A, d0 = C,
                a1 = E, b1 = A, c1 = D, d1 = B,
                a2 = B, b2 = H, c2 = I, d2 = W,
            )
        ),
    }

    enum class SuperFailWild2(
        override val matrix: Matrix3x4,
    ) : CombinationMatrixEnum {
        _1(
            Matrix3x4(
                scheme = """
                    W - - W
                    - - - -
                    - - - -
                """,
                a0 = W, b0 = B, c0 = A, d0 = W,
                a1 = F, b1 = E, c1 = H, d1 = D,
                a2 = C, b2 = B, c2 = G, d2 = B,
            )
        ),
        _2(
            Matrix3x4(
                scheme = """
                    W - - -
                    - - - W
                    - - - -
                """,
                a0 = W, b0 = B, c0 = A, d0 = C,
                a1 = F, b1 = E, c1 = H, d1 = W,
                a2 = C, b2 = B, c2 = G, d2 = B,
            )
        ),
        _3(
            Matrix3x4(
                scheme = """
                    W - - -
                    - - - -
                    - - - W
                """,
                a0 = W, b0 = B, c0 = A, d0 = C,
                a1 = F, b1 = E, c1 = H, d1 = G,
                a2 = C, b2 = B, c2 = G, d2 = W,
            )
        ),
        _4(
            Matrix3x4(
                scheme = """
                    - - - -
                    W - - -
                    - - - W
                """,
                a0 = A, b0 = B, c0 = A, d0 = C,
                a1 = W, b1 = E, c1 = H, d1 = G,
                a2 = C, b2 = B, c2 = G, d2 = W,
            )
        ),
        _5(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - - -
                    W - - W
                """,
                a0 = A, b0 = B, c0 = A, d0 = C,
                a1 = I, b1 = E, c1 = H, d1 = G,
                a2 = W, b2 = B, c2 = G, d2 = W,
            )
        ),
        _6(
            Matrix3x4(
                scheme = """
                    - - - -
                    - - - W
                    W - - -
                """,
                a0 = A, b0 = B, c0 = A, d0 = C,
                a1 = I, b1 = E, c1 = H, d1 = W,
                a2 = W, b2 = B, c2 = G, d2 = B,
            )
        ),
        _7(
            Matrix3x4(
                scheme = """
                    - - - W
                    - - - -
                    W - - -
                """,
                a0 = A, b0 = B, c0 = A, d0 = W,
                a1 = I, b1 = E, c1 = H, d1 = C,
                a2 = W, b2 = B, c2 = G, d2 = B,
            )
        ),
        _8(
            Matrix3x4(
                scheme = """
                    - - - -
                    W - - W
                    - - - -
                """,
                a0 = A, b0 = B, c0 = A, d0 = I,
                a1 = W, b1 = E, c1 = H, d1 = W,
                a2 = D, b2 = B, c2 = G, d2 = B,
            )
        ),
    }

    enum class SuperFailWild3(
        override val matrix: Matrix3x4,
    ) : CombinationMatrixEnum {
        _1(
            Matrix3x4(
                scheme = """
                    W - - W
                    - - - -
                    - - - W
                """,
                a0 = W, b0 = B, c0 = A, d0 = W,
                a1 = F, b1 = E, c1 = H, d1 = D,
                a2 = C, b2 = B, c2 = A, d2 = W,
            )
        ),
        _2(
            Matrix3x4(
                scheme = """
                    W - - W
                    - - - -
                    W - - -
                """,
                a0 = W, b0 = B, c0 = A, d0 = W,
                a1 = F, b1 = E, c1 = H, d1 = D,
                a2 = W, b2 = B, c2 = I, d2 = B,
            )
        ),
        _3(
            Matrix3x4(
                scheme = """
                    W - - -
                    - - - -
                    W - - W
                """,
                a0 = W, b0 = B, c0 = I, d0 = F,
                a1 = F, b1 = E, c1 = H, d1 = B,
                a2 = W, b2 = D, c2 = I, d2 = W,
            )
        ),
        _4(
            Matrix3x4(
                scheme = """
                    - - - W
                    - - - -
                    W - - W
                """,
                a0 = G, b0 = B, c0 = I, d0 = W,
                a1 = F, b1 = E, c1 = H, d1 = B,
                a2 = W, b2 = D, c2 = I, d2 = W,
            )
        ),
    }



    enum class MiniWinWildSlots(
        val rowList: List<CombinationSlot>
    ) {
        _0(MiniWinWildSlot0.values().toList()),
        _1(MiniWinWildSlot1.values().toList()),
        _2(MiniWinWildSlot2.values().toList()),
        _3(MiniWinWildSlot3.values().toList());


        enum class MiniWinWildSlot0(
            override val combinationList: List<CombinationMatrixEnum>
        ) : CombinationSlot {
            _0(MiniWinWildRow0.values().toList()),
            _1(MiniWinWildRow1.values().toList()),
            _2(MiniWinWildRow2.values().toList());


            enum class MiniWinWildRow0(
                override val matrix: Matrix3x4,
            ) : CombinationMatrixEnum {
                _1(
                    Matrix3x4(
                        scheme = """
                    W A A -
                    - - - -
                    - - - -
                """,
                        winItemList = listOf(A),
                        a0 = W, b0 = A, c0 = A, d0 = B,
                        a1 = F, b1 = E, c1 = H, d1 = D,
                        a2 = C, b2 = B, c2 = G, d2 = I,
                    )
                ),
                _2(
                    Matrix3x4(
                        scheme = """
                    W - - -
                    - A - -
                    - - A -
                """,
                        winItemList = listOf(A),
                        a0 = W, b0 = E, c0 = G, d0 = B,
                        a1 = F, b1 = A, c1 = H, d1 = D,
                        a2 = C, b2 = B, c2 = A, d2 = I,
                    )
                ),
                _3(
                    Matrix3x4(
                        scheme = """
                    W - - -
                    A - - -
                    A - - -
                """,
                        winItemList = listOf(A),
                        a0 = W, b0 = E, c0 = G, d0 = B,
                        a1 = A, b1 = D, c1 = H, d1 = D,
                        a2 = A, b2 = B, c2 = C, d2 = I,
                    )
                ),
                _4(
                    Matrix3x4(
                        scheme = """
                    W A A -
                    A - - -
                    A - - -
                """,
                        winItemList = listOf(A),
                        a0 = W, b0 = A, c0 = A, d0 = B,
                        a1 = A, b1 = D, c1 = H, d1 = D,
                        a2 = A, b2 = B, c2 = C, d2 = I,
                    )
                ),
                _5(
                    Matrix3x4(
                        scheme = """
                    W C C -
                    A B - -
                    A - B -
                """,
                        winItemList = listOf(A, B, C),
                        a0 = W, b0 = C, c0 = C, d0 = I,
                        a1 = A, b1 = B, c1 = H, d1 = D,
                        a2 = A, b2 = D, c2 = B, d2 = I,
                    )
                ),
            }

            enum class MiniWinWildRow1(
                override val matrix: Matrix3x4,
            ) : CombinationMatrixEnum {
                _1(
                    Matrix3x4(
                        scheme = """
                            - - - -
                            W A A -
                            - - - -
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = D, c0 = B, d0 = B,
                        a1 = W, b1 = A, c1 = A, d1 = D,
                        a2 = C, b2 = B, c2 = G, d2 = I,
                    )
                ),
                _2(
                    Matrix3x4(
                        scheme = """
                            - - - -
                            W - - -
                            - A A -
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = D, c0 = B, d0 = B,
                        a1 = W, b1 = I, c1 = H, d1 = D,
                        a2 = C, b2 = A, c2 = A, d2 = I,
                    )
                ),
                _3(
                    Matrix3x4(
                        scheme = """
                            - A A -
                            W - - -
                            - - - -
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = A, c0 = A, d0 = B,
                        a1 = W, b1 = I, c1 = H, d1 = D,
                        a2 = C, b2 = E, c2 = B, d2 = I,
                    )
                ),
                _4(
                    Matrix3x4(
                        scheme = """
                            - A A -
                            W - - -
                            - B B -
                        """,
                        winItemList = listOf(A, B),
                        a0 = F, b0 = A, c0 = A, d0 = F,
                        a1 = W, b1 = I, c1 = H, d1 = D,
                        a2 = C, b2 = B, c2 = B, d2 = I,
                    )
                ),
                _5(
                    Matrix3x4(
                        scheme = """
                            - A A -
                            W C C -
                            - B B -
                        """,
                        winItemList = listOf(A, B, C),
                        a0 = F, b0 = A, c0 = A, d0 = F,
                        a1 = W, b1 = C, c1 = C, d1 = D,
                        a2 = G, b2 = B, c2 = B, d2 = I,
                    )
                ),
            }

            enum class MiniWinWildRow2(
                override val matrix: Matrix3x4,
            ) : CombinationMatrixEnum {
                _1(
                    Matrix3x4(
                        scheme = """
                            - - - -
                            - - - -
                            W A A -
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = D, c0 = B, d0 = B,
                        a1 = E, b1 = E, c1 = G, d1 = D,
                        a2 = W, b2 = A, c2 = A, d2 = I,
                    )
                ),
                _2(
                    Matrix3x4(
                        scheme = """
                            - - A -
                            - A - -
                            W - - -
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = D, c0 = A, d0 = B,
                        a1 = E, b1 = A, c1 = G, d1 = D,
                        a2 = W, b2 = D, c2 = G, d2 = I,
                    )
                ),
                _3(
                    Matrix3x4(
                        scheme = """
                            - - A -
                            - A - -
                            W A A -
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = D, c0 = A, d0 = B,
                        a1 = E, b1 = A, c1 = G, d1 = D,
                        a2 = W, b2 = A, c2 = A, d2 = I,
                    )
                ),
                _4(
                    Matrix3x4(
                        scheme = """
                            - - A -
                            - A A A
                            W A A -
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = D, c0 = A, d0 = B,
                        a1 = E, b1 = A, c1 = A, d1 = A,
                        a2 = W, b2 = A, c2 = A, d2 = I,
                    )
                ),
                _5(
                    Matrix3x4(
                        scheme = """
                            - - A A
                            - A A B
                            W B B B
                        """,
                        winItemList = listOf(A, B),
                        a0 = F, b0 = D, c0 = A, d0 = A,
                        a1 = E, b1 = A, c1 = A, d1 = B,
                        a2 = W, b2 = B, c2 = B, d2 = B,
                    )
                ),
            }

        }

        enum class MiniWinWildSlot1(
            override val combinationList: List<CombinationMatrixEnum>
        ) : CombinationSlot {
            _0(MiniWinWildRow0.values().toList()),
            _1(MiniWinWildRow1.values().toList()),
            _2(MiniWinWildRow2.values().toList());

            enum class MiniWinWildRow0(
                override val matrix: Matrix3x4,
            ) : CombinationMatrixEnum {
                _1(
                    Matrix3x4(
                        scheme = """
                            - W A A
                            - - - -
                            - - - -
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = W, c0 = A, d0 = A,
                        a1 = E, b1 = E, c1 = G, d1 = D,
                        a2 = G, b2 = D, c2 = B, d2 = I,
                    )
                ),
                _2(
                    Matrix3x4(
                        scheme = """
                            - W A -
                            - A A -
                            A - - A
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = W, c0 = A, d0 = B,
                        a1 = E, b1 = A, c1 = A, d1 = D,
                        a2 = A, b2 = D, c2 = G, d2 = A,
                    )
                ),
                _3(
                    Matrix3x4(
                        scheme = """
                            - W A A
                            - A B -
                            A - - B
                        """,
                        winItemList = listOf(A, B),
                        a0 = F, b0 = W, c0 = A, d0 = A,
                        a1 = H, b1 = A, c1 = B, d1 = D,
                        a2 = A, b2 = E, c2 = I, d2 = B,
                    )
                ),
                _4(
                    Matrix3x4(
                        scheme = """
                            - W A -
                            C C B A
                            C C B A
                        """,
                        winItemList = listOf(A, B, C),
                        a0 = F, b0 = W, c0 = A, d0 = D,
                        a1 = C, b1 = C, c1 = B, d1 = A,
                        a2 = C, b2 = C, c2 = B, d2 = A,
                    )
                ),
                _5(
                    Matrix3x4(
                        scheme = """
                            A W A A
                            B B B B
                            C C C C
                        """,
                        winItemList = listOf(A, B),
                        a0 = A, b0 = W, c0 = A, d0 = A,
                        a1 = B, b1 = B, c1 = B, d1 = B,
                        a2 = C, b2 = C, c2 = C, d2 = C,
                    )
                ),
            }

            enum class MiniWinWildRow1(
                override val matrix: Matrix3x4,
            ) : CombinationMatrixEnum {
                _1(
                    Matrix3x4(
                        scheme = """
                            - - - -
                            - W A A
                            - - - -
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = I, c0 = C, d0 = B,
                        a1 = E, b1 = W, c1 = A, d1 = A,
                        a2 = G, b2 = D, c2 = B, d2 = I,
                    )
                ),
                _2(
                    Matrix3x4(
                        scheme = """
                            - - - -
                            - W - -
                            - - A A
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = I, c0 = C, d0 = B,
                        a1 = E, b1 = W, c1 = H, d1 = B,
                        a2 = G, b2 = D, c2 = A, d2 = A,
                    )
                ),
                _3(
                    Matrix3x4(
                        scheme = """
                            - - B B
                            - W - -
                            - - A A
                        """,
                        winItemList = listOf(A, B),
                        a0 = F, b0 = I, c0 = B, d0 = B,
                        a1 = E, b1 = W, c1 = H, d1 = D,
                        a2 = G, b2 = D, c2 = A, d2 = A,
                    )
                ),
                _4(
                    Matrix3x4(
                        scheme = """
                            A - B B
                            C W C C
                            B - A A
                        """,
                        winItemList = listOf(A, B, C),
                        a0 = A, b0 = I, c0 = B, d0 = B,
                        a1 = C, b1 = W, c1 = C, d1 = C,
                        a2 = B, b2 = D, c2 = A, d2 = A,
                    )
                ),
                _5(
                    Matrix3x4(
                        scheme = """
                            A - - -
                            - W C C
                            - - A A
                        """,
                        winItemList = listOf(A, C),
                        a0 = A, b0 = I, c0 = D, d0 = F,
                        a1 = F, b1 = W, c1 = C, d1 = C,
                        a2 = H, b2 = G, c2 = A, d2 = A,
                    )
                ),
            }

            enum class MiniWinWildRow2(
                override val matrix: Matrix3x4,
            ) : CombinationMatrixEnum {
                _1(
                    Matrix3x4(
                        scheme = """
                            - - - -
                            - - - -
                            A W A -
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = I, c0 = E, d0 = B,
                        a1 = E, b1 = B, c1 = C, d1 = D,
                        a2 = A, b2 = W, c2 = A, d2 = I,
                    )
                ),
                _2(
                    Matrix3x4(
                        scheme = """
                            - - - -
                            - - A -
                            A W A A
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = I, c0 = E, d0 = B,
                        a1 = E, b1 = B, c1 = A, d1 = D,
                        a2 = A, b2 = W, c2 = A, d2 = A,
                    )
                ),
                _3(
                    Matrix3x4(
                        scheme = """
                            - - - B
                            - - B -
                            A W A A
                        """,
                        winItemList = listOf(A, B),
                        a0 = F, b0 = I, c0 = E, d0 = B,
                        a1 = E, b1 = D, c1 = B, d1 = D,
                        a2 = A, b2 = W, c2 = A, d2 = A,
                    )
                ),
                _4(
                    Matrix3x4(
                        scheme = """
                            C - - B
                            C C B -
                            A W A A
                        """,
                        winItemList = listOf(A, B, C),
                        a0 = C, b0 = I, c0 = E, d0 = B,
                        a1 = C, b1 = C, c1 = B, d1 = D,
                        a2 = A, b2 = W, c2 = A, d2 = A,
                    )
                ),
                _5(
                    Matrix3x4(
                        scheme = """
                            A - - B
                            A A B B
                            - W A -
                        """,
                        winItemList = listOf(A, B),
                        a0 = A, b0 = I, c0 = E, d0 = B,
                        a1 = A, b1 = A, c1 = B, d1 = D,
                        a2 = C, b2 = W, c2 = A, d2 = F,
                    )
                ),
            }
        }

        enum class MiniWinWildSlot2(
            override val combinationList: List<CombinationMatrixEnum>
        ) : CombinationSlot {
            _0(MiniWinWildRow0.values().toList()),
            _1(MiniWinWildRow1.values().toList()),
            _2(MiniWinWildRow2.values().toList());

            enum class MiniWinWildRow0(
                override val matrix: Matrix3x4,
            ) : CombinationMatrixEnum {
                _1(
                    Matrix3x4(
                        scheme = """
                            - A W A
                            - - - -
                            - - - -
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = A, c0 = W, d0 = A,
                        a1 = E, b1 = E, c1 = G, d1 = D,
                        a2 = G, b2 = D, c2 = B, d2 = I,
                    )
                ),
                _2(
                    Matrix3x4(
                        scheme = """
                            - A W A
                            - A - -
                            A - - -
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = A, c0 = W, d0 = A,
                        a1 = E, b1 = A, c1 = G, d1 = D,
                        a2 = A, b2 = D, c2 = B, d2 = I,
                    )
                ),
                _3(
                    Matrix3x4(
                        scheme = """
                            - A W A
                            - A - -
                            B B B -
                        """,
                        winItemList = listOf(A, B),
                        a0 = F, b0 = A, c0 = W, d0 = A,
                        a1 = E, b1 = A, c1 = G, d1 = D,
                        a2 = B, b2 = B, c2 = B, d2 = I,
                    )
                ),
                _4(
                    Matrix3x4(
                        scheme = """
                            A A W A
                            C C C A
                            B B B A
                        """,
                        winItemList = listOf(A, B, C),
                        a0 = A, b0 = A, c0 = W, d0 = A,
                        a1 = C, b1 = C, c1 = C, d1 = A,
                        a2 = B, b2 = B, c2 = B, d2 = A,
                    )
                ),
                _5(
                    Matrix3x4(
                        scheme = """
                            - - W -
                            - - - -
                            - A A A
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = D, c0 = W, d0 = G,
                        a1 = E, b1 = C, c1 = E, d1 = F,
                        a2 = B, b2 = A, c2 = A, d2 = A,
                    )
                ),
            }

            enum class MiniWinWildRow1(
                override val matrix: Matrix3x4,
            ) : CombinationMatrixEnum {
                _1(
                    Matrix3x4(
                        scheme = """
                            - - - A
                            - - W -
                            - A - -
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = I, c0 = B, d0 = A,
                        a1 = E, b1 = C, c1 = W, d1 = D,
                        a2 = G, b2 = A, c2 = D, d2 = F,
                    )
                ),
                _2(
                    Matrix3x4(
                        scheme = """
                            - B - A
                            - - W -
                            - A - B
                        """,
                        winItemList = listOf(A, B),
                        a0 = F, b0 = B, c0 = C, d0 = A,
                        a1 = E, b1 = D, c1 = W, d1 = I,
                        a2 = G, b2 = A, c2 = D, d2 = B,
                    )
                ),
                _3(
                    Matrix3x4(
                        scheme = """
                            - A - -
                            - - W -
                            - - - A
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = A, c0 = C, d0 = G,
                        a1 = E, b1 = H, c1 = W, d1 = I,
                        a2 = G, b2 = B, c2 = D, d2 = A,
                    )
                ),
                _4(
                    Matrix3x4(
                        scheme = """
                            - A - -
                            - A W A
                            - - - A
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = A, c0 = C, d0 = G,
                        a1 = E, b1 = A, c1 = W, d1 = A,
                        a2 = G, b2 = B, c2 = D, d2 = A,
                    )
                ),
                _5(
                    Matrix3x4(
                        scheme = """
                            A A A -
                            - B W B
                            C C C C
                        """,
                        winItemList = listOf(A, B, C),
                        a0 = A, b0 = A, c0 = A, d0 = G,
                        a1 = E, b1 = B, c1 = W, d1 = B,
                        a2 = G, b2 = C, c2 = C, d2 = C,
                    )
                ),
            }

            enum class MiniWinWildRow2(
                override val matrix: Matrix3x4,
            ) : CombinationMatrixEnum {
                _1(
                    Matrix3x4(
                        scheme = """
                            - - - -
                            - - - -
                            - A W A
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = I, c0 = B, d0 = I,
                        a1 = E, b1 = H, c1 = C, d1 = D,
                        a2 = G, b2 = A, c2 = W, d2 = A,
                    )
                ),
                _2(
                    Matrix3x4(
                        scheme = """
                            B - - -
                            - B - -
                            - A W A
                        """,
                        winItemList = listOf(A, B),
                        a0 = B, b0 = I, c0 = H, d0 = I,
                        a1 = E, b1 = B, c1 = C, d1 = D,
                        a2 = G, b2 = A, c2 = W, d2 = A,
                    )
                ),
                _3(
                    Matrix3x4(
                        scheme = """
                            A B - -
                            A - B B
                            A A W A
                        """,
                        winItemList = listOf(A, B),
                        a0 = A, b0 = B, c0 = D, d0 = I,
                        a1 = A, b1 = H, c1 = B, d1 = B,
                        a2 = A, b2 = A, c2 = W, d2 = A,
                    )
                ),
                _4(
                    Matrix3x4(
                        scheme = """
                            - - - -
                            A A - -
                            - - W -
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = I, c0 = B, d0 = I,
                        a1 = A, b1 = A, c1 = C, d1 = D,
                        a2 = G, b2 = I, c2 = W, d2 = E,
                    )
                ),
                _5(
                    Matrix3x4(
                        scheme = """
                            A - - A
                            - A - A
                            - A W A
                        """,
                        winItemList = listOf(A),
                        a0 = A, b0 = I, c0 = B, d0 = A,
                        a1 = E, b1 = A, c1 = C, d1 = A,
                        a2 = G, b2 = A, c2 = W, d2 = A,
                    )
                ),

            }

        }

        enum class MiniWinWildSlot3(
            override val combinationList: List<CombinationMatrixEnum>
        ) : CombinationSlot {
            _0(MiniWinWildRow0.values().toList()),
            _1(MiniWinWildRow1.values().toList()),
            _2(MiniWinWildRow2.values().toList());

            enum class MiniWinWildRow0(
                override val matrix: Matrix3x4,
            ) : CombinationMatrixEnum {
                _1(
                    Matrix3x4(
                        scheme = """
                            - A A W
                            - - - -
                            - - - -
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = A, c0 = A, d0 = W,
                        a1 = E, b1 = H, c1 = C, d1 = D,
                        a2 = G, b2 = I, c2 = G, d2 = B,
                    )
                ),
                _2(
                    Matrix3x4(
                        scheme = """
                            - A A W
                            - - A -
                            - A - -
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = A, c0 = A, d0 = W,
                        a1 = E, b1 = H, c1 = A, d1 = D,
                        a2 = G, b2 = A, c2 = G, d2 = B,
                    )
                ),
                _3(
                    Matrix3x4(
                        scheme = """
                            - - - W
                            - - A -
                            - A - -
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = C, c0 = B, d0 = W,
                        a1 = E, b1 = H, c1 = A, d1 = D,
                        a2 = G, b2 = A, c2 = G, d2 = B,
                    )
                ),
                _4(
                    Matrix3x4(
                        scheme = """
                            - B B W
                            B - A -
                            - A - -
                        """,
                        winItemList = listOf(A, B),
                        a0 = F, b0 = B, c0 = B, d0 = W,
                        a1 = B, b1 = H, c1 = A, d1 = D,
                        a2 = G, b2 = A, c2 = G, d2 = I,
                    )
                ),
                _5(
                    Matrix3x4(
                        scheme = """
                            A A A W
                            A A A -
                            A A - -
                        """,
                        winItemList = listOf(A),
                        a0 = A, b0 = A, c0 = A, d0 = W,
                        a1 = A, b1 = A, c1 = A, d1 = D,
                        a2 = A, b2 = A, c2 = G, d2 = B,
                    )
                ),

            }

            enum class MiniWinWildRow1(
                override val matrix: Matrix3x4,
            ) : CombinationMatrixEnum {
                _1(
                    Matrix3x4(
                        scheme = """
                            - - - -
                            - A A W
                            - - - -
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = H, c0 = C, d0 = D,
                        a1 = E, b1 = A, c1 = A, d1 = W,
                        a2 = G, b2 = I, c2 = G, d2 = B,
                    )
                ),
                _2(
                    Matrix3x4(
                        scheme = """
                            - B B -
                            - A A W
                            - - - -
                        """,
                        winItemList = listOf(A, B),
                        a0 = F, b0 = B, c0 = B, d0 = D,
                        a1 = E, b1 = A, c1 = A, d1 = W,
                        a2 = G, b2 = I, c2 = G, d2 = C,
                    )
                ),
                _3(
                    Matrix3x4(
                        scheme = """
                            - B B -
                            - A A W
                            - C C -
                        """,
                        winItemList = listOf(A, B, C),
                        a0 = F, b0 = B, c0 = B, d0 = D,
                        a1 = E, b1 = A, c1 = A, d1 = W,
                        a2 = G, b2 = C, c2 = C, d2 = I,
                    )
                ),
                _4(
                    Matrix3x4(
                        scheme = """
                            - B B -
                            - - - W
                            - A A -
                        """,
                        winItemList = listOf(A, B),
                        a0 = F, b0 = B, c0 = B, d0 = D,
                        a1 = E, b1 = H, c1 = F, d1 = W,
                        a2 = G, b2 = A, c2 = A, d2 = I,
                    )
                ),
                _5(
                    Matrix3x4(
                        scheme = """
                            - - - -
                            A - - W
                            - A A -
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = B, c0 = E, d0 = D,
                        a1 = A, b1 = H, c1 = F, d1 = W,
                        a2 = G, b2 = A, c2 = A, d2 = I,
                    )
                ),
            }

            enum class MiniWinWildRow2(
                override val matrix: Matrix3x4,
            ) : CombinationMatrixEnum {
                _1(
                    Matrix3x4(
                        scheme = """
                            - - - -
                            - A A -
                            - - - W
                        """,
                        winItemList = listOf(A),
                        a0 = F, b0 = H, c0 = C, d0 = D,
                        a1 = E, b1 = A, c1 = A, d1 = B,
                        a2 = G, b2 = I, c2 = G, d2 = W,
                    )
                ),
                _2(
                    Matrix3x4(
                        scheme = """
                            A - - -
                            - A A -
                            - - - W
                        """,
                        winItemList = listOf(A),
                        a0 = A, b0 = H, c0 = C, d0 = D,
                        a1 = E, b1 = A, c1 = A, d1 = B,
                        a2 = G, b2 = I, c2 = G, d2 = W,
                    )
                ),
                _3(
                    Matrix3x4(
                        scheme = """
                            B B B B
                            - A A B
                            A A A W
                        """,
                        winItemList = listOf(A, B),
                        a0 = B, b0 = B, c0 = B, d0 = B,
                        a1 = E, b1 = A, c1 = A, d1 = B,
                        a2 = A, b2 = A, c2 = A, d2 = W,
                    )
                ),
                _4(
                    Matrix3x4(
                        scheme = """
                            A - - A
                            - A A -
                            B B B W
                        """,
                        winItemList = listOf(A, B),
                        a0 = A, b0 = H, c0 = C, d0 = A,
                        a1 = E, b1 = A, c1 = A, d1 = D,
                        a2 = B, b2 = B, c2 = B, d2 = W,
                    )
                ),
                _5(
                    Matrix3x4(
                        scheme = """
                            A B B B
                            - A A -
                            C C C W
                        """,
                        winItemList = listOf(A, B, C),
                        a0 = A, b0 = B, c0 = B, d0 = B,
                        a1 = E, b1 = A, c1 = A, d1 = D,
                        a2 = C, b2 = C, c2 = C, d2 = W,
                    )
                ),
            }
        }

    }

    enum class MiniFailWildSlots(
        val rowList: List<CombinationMatrixEnum>
    ) {
        _0(MiniFailWildSlot0.values().toList()),
        _1(MiniFailWildSlot1.values().toList()),
        _2(MiniFailWildSlot2.values().toList()),
        _3(MiniFailWildSlot3.values().toList());



        enum class MiniFailWildSlot0(
            override val matrix: Matrix3x4
        ): CombinationMatrixEnum {
            _0(
                Matrix3x4(
                    scheme = """
                        W - - -
                        - - - -
                        - - - -
                    """,
                    a0 = W, b0 = B, c0 = A, d0 = B,
                    a1 = F, b1 = E, c1 = H, d1 = D,
                    a2 = C, b2 = B, c2 = G, d2 = I,
                )
            ),
            _1(
                Matrix3x4(
                    scheme = """
                        - - - -
                        W - - -
                        - - - -
                    """,
                    a0 = D, b0 = B, c0 = A, d0 = B,
                    a1 = W, b1 = E, c1 = H, d1 = D,
                    a2 = C, b2 = B, c2 = G, d2 = I,
                )
            ),
            _2(
                Matrix3x4(
                    scheme = """
                        - - - -
                        - - - -
                        W - - -
                    """,
                    a0 = D, b0 = B, c0 = A, d0 = B,
                    a1 = H, b1 = E, c1 = H, d1 = D,
                    a2 = W, b2 = B, c2 = G, d2 = I,
                )
            ),
        }

        enum class MiniFailWildSlot1(
            override val matrix: Matrix3x4
        ): CombinationMatrixEnum {
            _0(
                Matrix3x4(
                    scheme = """
                        - W - -
                        - - - -
                        - - - -
                    """,
                    a0 = B, b0 = W, c0 = A, d0 = B,
                    a1 = F, b1 = E, c1 = H, d1 = D,
                    a2 = C, b2 = B, c2 = G, d2 = I,
                )
            ),
            _1(
                Matrix3x4(
                    scheme = """
                        - - - -
                        - W - -
                        - - - -
                    """,
                    a0 = D, b0 = B, c0 = A, d0 = B,
                    a1 = E, b1 = W, c1 = H, d1 = D,
                    a2 = C, b2 = I, c2 = G, d2 = I,
                )
            ),
            _2(
                Matrix3x4(
                    scheme = """
                        - - - -
                        - - - -
                        - W - -
                    """,
                    a0 = D, b0 = B, c0 = A, d0 = B,
                    a1 = H, b1 = E, c1 = H, d1 = D,
                    a2 = B, b2 = W, c2 = G, d2 = I,
                )
            ),
        }

        enum class MiniFailWildSlot2(
            override val matrix: Matrix3x4
        ): CombinationMatrixEnum {
            _0(
                Matrix3x4(
                    scheme = """
                        - - W -
                        - - - -
                        - - - -
                    """,
                    a0 = B, b0 = A, c0 = W, d0 = F,
                    a1 = F, b1 = E, c1 = H, d1 = D,
                    a2 = C, b2 = B, c2 = G, d2 = I,
                )
            ),
            _1(
                Matrix3x4(
                    scheme = """
                        - - - -
                        - - W -
                        - - - -
                    """,
                    a0 = D, b0 = B, c0 = A, d0 = F,
                    a1 = E, b1 = H, c1 = W, d1 = D,
                    a2 = C, b2 = I, c2 = G, d2 = I,
                )
            ),
            _2(
                Matrix3x4(
                    scheme = """
                        - - - -
                        - - - -
                        - - W -
                    """,
                    a0 = D, b0 = B, c0 = A, d0 = F,
                    a1 = H, b1 = E, c1 = H, d1 = D,
                    a2 = B, b2 = G, c2 = W, d2 = I,
                )
            ),
        }

        enum class MiniFailWildSlot3(
            override val matrix: Matrix3x4
        ): CombinationMatrixEnum {
            _0(
                Matrix3x4(
                    scheme = """
                        - - - W
                        - - - -
                        - - - -
                    """,
                    a0 = B, b0 = A, c0 = F, d0 = W,
                    a1 = F, b1 = E, c1 = H, d1 = D,
                    a2 = C, b2 = B, c2 = G, d2 = I,
                )
            ),
            _1(
                Matrix3x4(
                    scheme = """
                        - - - -
                        - - - W
                        - - - -
                    """,
                    a0 = D, b0 = B, c0 = A, d0 = F,
                    a1 = E, b1 = H, c1 = D, d1 = W,
                    a2 = C, b2 = I, c2 = G, d2 = I,
                )
            ),
            _2(
                Matrix3x4(
                    scheme = """
                        - - - -
                        - - - -
                        - - - W
                    """,
                    a0 = D, b0 = B, c0 = A, d0 = F,
                    a1 = H, b1 = E, c1 = H, d1 = D,
                    a2 = B, b2 = G, c2 = I, d2 = W,
                )
            ),
        }
    }

}