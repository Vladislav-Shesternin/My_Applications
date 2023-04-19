package com.gs.vest.ask.game.util

object Layout {

    object Profile {
        val gosInvest = LayoutData(46f,19f,408f,477f)
        val timer     = LayoutData(250f,18f,103f,50f)
        val notOblig  = LayoutData(92f,677f,315f,160f)
        val balance   = LayoutData(46f,229f,408f,50f)
        val profit    = LayoutData(50f,328f,401f,50f)
        val button    = LayoutData(96f,1073f,310f,92f)
        val scroll    = LayoutData(11f,534f,489f,502f)
    }

    object Bonds {
        val panel  = LayoutData(46f, 33f, 408f, 1147f)
        val scroll = LayoutData(38f, 225f, 423f, 919f)
    }

    object Bond {
        val panel     = LayoutData(15f, 390f, 470f, 810f)
        val name      = LayoutData(15f, 293f, 470f, 82f)
        val logo      = LayoutData(123f, 39f, 254f, 254f)
        val cost      = LayoutData(15f, 789f, 470f, 50f)
        val percent   = LayoutData(15f, 940f, 470f, 50f)
        val amount    = LayoutData(15f, 1021f, 470f, 82f)
        val profit    = LayoutData(15f, 1103f, 470f, 82f)
        val buyCost    = LayoutData(22f, 515f, 220f, 38f)
        val buyProfit  = LayoutData(258f, 515f, 220f, 38f)
        val amountEdit = LayoutData(15f, 417f, 470f, 67f)
        val sell = LayoutData(29f, 589f, 207f, 61f)
        val buy  = LayoutData(264f, 589f, 207f, 61f)
    }

    object BondItem {
        val name    = LayoutData(9f, 7f, 404f, 40f)
        val logo    = LayoutData(88f, 53f, 254f, 254f)
        val cost    = LayoutData(18f, 316f, 192f, 50f)
        val percent = LayoutData(227f, 316f, 177f, 50f)
        val amount  = LayoutData(215f, 379f, 189f, 40f)
        val profit  = LayoutData(215f, 428f, 189f, 40f)
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












