package nsl.radead.egyptlegacy.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.badlogic.gdx.backends.android.AndroidFragmentApplication

class LibGDXFragment : AndroidFragmentApplication() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val conf = AndroidApplicationConfiguration().apply {
            a = 8
            useAccelerometer = false
            useCompass = false
        }

        return initializeForView(nsl.radead.egyptlegacy.game.LibGDXGame(requireActivity() as nsl.radead.egyptlegacy.MainActivity), conf)
    }
}