package com.tdapps.test.game.actors.slots.slot4x3

/** SlotMatrix4x3
 *
 * 4 slots 3 rows
 * aN..cN - SlitItem index
 *
 * */
data class Matrix4x3(
    val resultMatrix4x3: ResultMatrix4x3? = null,

    val a1: Int,
    val a2: Int,
    val a3: Int,

    val b1: Int,
    val b2: Int,
    val b3: Int,

    val c1: Int,
    val c2: Int,
    val c3: Int,

    val d1: Int,
    val d2: Int,
    val d3: Int,
)

/** ResultMatrix4x3
 *
 * false - fail
 * true  - win
 *
* */
data class ResultMatrix4x3(
    val a1: Boolean,
    val a2: Boolean,
    val a3: Boolean,

    val b1: Boolean,
    val b2: Boolean,
    val b3: Boolean,

    val c1: Boolean,
    val c2: Boolean,
    val c3: Boolean,

    val d1: Boolean,
    val d2: Boolean,
    val d3: Boolean,
)