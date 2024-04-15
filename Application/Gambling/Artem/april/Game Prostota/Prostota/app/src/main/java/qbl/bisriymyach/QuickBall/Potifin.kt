package qbl.bisriymyach.QuickBall

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import qbl.bisriymyach.QuickBall.databinding.ActivityMainBinding
import qbl.bisriymyach.QuickBall.tidams.lakki
import qbl.bisriymyach.QuickBall.hotvils.vvavava
import qbl.bisriymyach.QuickBall.tidams.log
import kotlin.system.exitProcess

class Potifin : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val onceExit = vvavava()

    private lateinit var binding: ActivityMainBinding

    lateinit var lottie: lakki

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
        lottie.tyarampa8()
    }

    override fun exit() {
        onceExit.once {

            log("exit")
            coroutine.launch(Dispatchers.Main) {
                finishAndRemoveTask()
                delay(100)
                exitProcess(0)
            }
        }
    }

    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lottie = lakki(binding)
    }

}