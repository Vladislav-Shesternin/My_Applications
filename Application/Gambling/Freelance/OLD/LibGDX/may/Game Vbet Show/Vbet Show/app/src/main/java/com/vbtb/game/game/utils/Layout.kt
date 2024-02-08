package com.vbtb.game.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {
    object Splash {
        val progress = LayoutData(455f,55f,290f,109f)
    }

    object Settings {
        val panel = LayoutData(64f,453f,616f,900f)
        val play  = LayoutData(358f,0f,387f,235f)
        val music = LayoutData(134f,70f,109f,131f)
        val weightText         = LayoutData(447f,942f,233f,89f)
        val weightProgress     = LayoutData(64f,869f,616f,29f)
        val elasticityText     = LayoutData(447f,734f,233f,89f)
        val elasticityProgress = LayoutData(64f,661f,616f,29f)
        val frictionText       = LayoutData(447f,526f,233f,89f)
        val frictionProgress   = LayoutData(64f,453f,616f,29f)
    }

    object Game {
        val bowl = LayoutData(325f,75f,95f,95f)
        val gold = LayoutData(311f,1011f,123f,123f)
    }

    object Progress {
        val progressLength = 587f
        val size = Size(616f, 16f)
        val back = Vector2(0f, 6f)
        val leverSize = Size(29f, 29f)
    }

    data class LayoutData(
        val x: Float = 0f,
        val y: Float = 0f,
        val w: Float = 0f,
        val h: Float = 0f,
        // horizontal space
        val hs: Float = 0f,
        // vertical space
        val vs: Float = 0f,
    ) {

        fun position() = Vector2(x, y)
        fun size() = Size(w, h)

    }

}












