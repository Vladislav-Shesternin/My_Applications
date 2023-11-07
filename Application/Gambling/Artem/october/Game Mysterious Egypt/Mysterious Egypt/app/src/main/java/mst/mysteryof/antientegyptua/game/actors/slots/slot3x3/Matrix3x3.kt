package mst.mysteryof.antientegyptua.game.actors.slots.slot3x3

/** SlotMatrix3x3
 *
 * 3 slots 3 rows
 * aN..cN - SlitItem index
 *
 * */
data class Matrix3x3(
    val resultMatrix3x3: ResultMatrix3x3? = null,

    val a1: Int,
    val a2: Int,
    val a3: Int,

    val b1: Int,
    val b2: Int,
    val b3: Int,

    val c1: Int,
    val c2: Int,
    val c3: Int,
)

/** ResultMatrix3x3
 *
 * false - fail
 * true  - win
 *
* */
data class ResultMatrix3x3(
    val a1: Boolean,
    val a2: Boolean,
    val a3: Boolean,

    val b1: Boolean,
    val b2: Boolean,
    val b3: Boolean,

    val c1: Boolean,
    val c2: Boolean,
    val c3: Boolean,
)