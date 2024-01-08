package com.tigerfortune.jogo.game.box2d

object BodyId {
    const val NONE = "none"

    object Game {
        const val TIGER = "game.tiger"
        const val BOMB  = "game.bomb"

        val items = List(7) { "game.$it" }
    }
}