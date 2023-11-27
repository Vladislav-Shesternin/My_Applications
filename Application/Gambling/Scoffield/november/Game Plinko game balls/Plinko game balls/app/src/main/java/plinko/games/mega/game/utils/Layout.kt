package plinko.games.mega.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Splash {
        val prolog   = LayoutData(223f,314f,634f,75f)
        val progress = LayoutData(369f,325f,343f,52f)
        val palanet  = LayoutData(0f,1469f,641f,451f)
        val luna     = LayoutData(108f,527f,864f,864f)
        val listI    = listOf(
            LayoutData(510f,458f,69f,69f),
            LayoutData(0f,0f,0f,0f),
            LayoutData(671f,529f,78f,78f),
            LayoutData(318f,528f,78f,78f),
            LayoutData(829f,622f,98f,98f),
            LayoutData(153f,623f,98f,98f),
        )
    }

    object Game {
        val game1 = listOf(
            listOf(
                LayoutData(455f, 1381f, 217f, 134f),
                LayoutData(101f, 1179f, 217f, 134f),
                LayoutData(821f, 1202f, 217f, 134f),
                LayoutData(517f, 964f, 217f, 134f),
                LayoutData(23f, 771f, 217f, 134f),
                LayoutData(819f, 749f, 217f, 134f),
                LayoutData(433f, 547f, 217f, 134f),
                LayoutData(132f, 353f, 217f, 134f),
            ),
            listOf(
                LayoutData(541f, 1381f, 217f, 134f),
                LayoutData(318f, 1104f, 217f, 134f),
                LayoutData(821f, 1093f, 217f, 134f),
                LayoutData(585f, 864f, 217f, 134f),
                LayoutData(9f, 859f, 217f, 134f),
                LayoutData(222f, 606f, 217f, 134f),
                LayoutData(855f, 597f, 217f, 134f),
                LayoutData(482f, 399f, 217f, 134f),
            ),
            listOf(
                LayoutData(317f, 1340f, 217f, 134f),
                LayoutData(564f, 1104f, 217f, 134f),
                LayoutData(9f, 967f, 217f, 134f),
                LayoutData(859f, 873f, 217f, 134f),
                LayoutData(585f, 687f, 217f, 134f),
                LayoutData(140f, 525f, 217f, 134f),
                LayoutData(855f, 405f, 217f, 134f),
                LayoutData(520f, 233f, 217f, 134f),
            ),
        )

        val panelStars = listOf(421f, 505f, 589f)
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
        fun size() = Vector2(w, h)

    }

}












