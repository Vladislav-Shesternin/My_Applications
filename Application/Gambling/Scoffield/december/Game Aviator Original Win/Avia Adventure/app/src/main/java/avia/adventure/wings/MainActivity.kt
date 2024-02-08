package avia.adventure.wings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import avia.adventure.wings.databinding.ActivityMainBinding
import avia.adventure.wings.util.Lottie
import avia.adventure.wings.util.Once
import avia.adventure.wings.util.log
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val onceExit  = Once()

    private lateinit var binding : ActivityMainBinding

    lateinit var lottie: Lottie
    private lateinit var banner: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
        lottie.showLoader()
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
        lottie = Lottie(binding)
        banner = binding.banner.apply { loadAd(AdRequest.Builder().build()) }
    }

}