package com.csmttt.medus.play.game.box2d.bodies

enum class BodyId {
    NONE,
    MEDUZA,
    TARE,
    WEAPON,
    E1, E2, E3, E4, E5, E6, E7, E8;
}

fun getBodyIdAllE() = arrayOf(BodyId.E1, BodyId.E2, BodyId.E3, BodyId.E4, BodyId.E5, BodyId.E6, BodyId.E7, BodyId.E8)