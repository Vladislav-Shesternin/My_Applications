package italodisco.fernando.lucherano.iopartew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import italodisco.fernando.lucherano.JopaStarTue

class YtrA : AndroidFragmentApplication() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val conf = AndroidApplicationConfiguration().apply {
            a = 8
                         useAccelerometer = false
            useCompass = false
        }

        return initializeForView(opOPa(requireActivity() as JopaStarTue), conf)
    }
}