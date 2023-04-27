package com.invt.nard.app.game.utils

import com.badlogic.gdx.math.Vector2
object Layout {
    object Splash {
        val progress = LayoutData(1193f,0f,258f,118f)
    }
    object Profile {
        val balls = LayoutData(40f,123f,1362f,581f)
        val box   = LayoutData(303f,397f,132f,171f, hs = 100f, vs = 58f)
        val reco  = LayoutData(253f,615f,258f,64f)
        val play  = LayoutData(1267f,259f,135f,135f)
        val music = LayoutData(40f,44f,79f,79f)
    }

    object Game {
        val record = LayoutData(40f,597f,201f,82f)
        val right  = LayoutData(1137f,295f,157f,199f)
        val left   = LayoutData(176f,295f,157f,199f)
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












