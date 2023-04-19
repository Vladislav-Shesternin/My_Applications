package com.veldan.pharaohslots

import android.app.Activity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.veldan.pharaohslots.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

lateinit var activityContext: Activity private set
lateinit var navController: NavController private set
lateinit var binding: ActivityMainBinding private set

class AndroidLauncher : FragmentActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        private val coroutineLoader = CoroutineScope(Dispatchers.Main)

        fun showLoader() {
            coroutineLoader.launch {
                binding.lottie.apply {
                    isVisible = true
                    playAnimation()
                }
            }
        }

        fun hideLoader() {
            coroutineLoader.launch {
                binding.lottie.apply {
                    isVisible = false
                    cancelAnimation()
                }
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_JungleGo)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activityContext = this
        navController = findNavController(R.id.nav_host_fragment)
    }

    override fun exit() {
        finish()
        exitProcess(0)
    }

}