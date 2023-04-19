package com.veldan.bigwinslots777.main.controller

import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.veldan.bigwinslots777.R
import com.veldan.bigwinslots777.databinding.ActivityMainBinding
import com.veldan.bigwinslots777.main.AndroidLauncher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class AndroidLauncherControllerPublic(val activity: AndroidLauncher) {

    private lateinit var coroutineLoader: CoroutineScope

    fun initialize() {
        with(activity) {
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            navController = findNavController(R.id.nav_host_fragment)
        }
    }

    fun showLoader() {
        coroutineLoader = CoroutineScope(Dispatchers.Main)
        coroutineLoader.launch { activity.binding.lottie.apply {
            isVisible = true
            playAnimation()
        } }
    }

    fun hideLoader() {
        coroutineLoader.launch { activity.binding.lottie.apply {
            isVisible = false
            cancelAnimation()
            cancel()
        } }
    }

}