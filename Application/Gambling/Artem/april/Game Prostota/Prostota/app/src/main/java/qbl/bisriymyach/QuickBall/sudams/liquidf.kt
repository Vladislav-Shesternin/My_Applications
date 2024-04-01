package qbl.bisriymyach.QuickBall.sudams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import qbl.bisriymyach.QuickBall.Potifin
import qbl.bisriymyach.QuickBall.LibGDXGame

class liquidf : AndroidFragmentApplication() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val conf = AndroidApplicationConfiguration().apply {
            a = 8
                                   useAccelerometer = false
            useCompass = false
        }

        return initializeForView(LibGDXGame(requireActivity() as Potifin), conf)
    }
}