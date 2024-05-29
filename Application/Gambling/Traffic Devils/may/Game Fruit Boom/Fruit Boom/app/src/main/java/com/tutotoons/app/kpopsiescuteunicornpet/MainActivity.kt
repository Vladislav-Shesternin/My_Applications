package com.tutotoons.app.kpopsiescuteunicornpet

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.tutotoons.app.kpopsiescuteunicornpet.databinding.ActivityMainBinding
import com.tutotoons.app.kpopsiescuteunicornpet.util.LottieUtil
import com.tutotoons.app.kpopsiescuteunicornpet.util.OneTime
import com.tutotoons.app.kpopsiescuteunicornpet.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        private var createCount = 0
    }

    private val coroutine  = CoroutineScope(Dispatchers.Default)
    private val onceExit   = OneTime()

    private lateinit var navController: NavController
    private lateinit var binding : ActivityMainBinding
    lateinit var lottie          : LottieUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
        lottie.show()

        ConstraintSet().run {
            this.clone(binding.root)
            this.constrainPercentWidth(binding.gradient.id, 0.2f)
            this.applyTo(binding.root)
        }
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        if (++createCount == 2) setStartDestination(R.id.libGDXFragment)
    }

    override fun exit() {
        onceExit.use {
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
        navController = findNavController(R.id.nav_host_fragment)
        lottie        = LottieUtil(binding)
    }

    private fun setStartDestination(@IdRes destinationId: Int) {
        navController.run { navInflater.inflate(R.navigation.nav_graph).apply { setStartDestination(destinationId) }.also { setGraph(it, null) } }
    }

}