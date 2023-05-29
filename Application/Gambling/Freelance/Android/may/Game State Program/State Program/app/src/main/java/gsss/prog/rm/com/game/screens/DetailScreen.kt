package gsss.prog.rm.com.game.screens

import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.fragment.app.FragmentActivity
import gsss.prog.rm.com.game.util.Screen

abstract class DetailScreen(val actyvity: FragmentActivity, @DrawableRes val img: Int): Screen(actyvity) {

    private val icon = ImageView(actyvity).apply { setImageResource(img) }

    final override fun ViewGroup.addActorsOnStage() {
        addView(icon)
        sizeConverter.setBounds(icon, 72f, 88f, 661f, 315f)

        addActorsOnStageDetail()
    }

    abstract fun ViewGroup.addActorsOnStageDetail()
}