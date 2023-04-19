package com.zet.vest.app.util

import androidx.core.view.isVisible
import com.zet.vest.app.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class Lottie(private val binding: ActivityMainBinding) {

    private val coroutineLottie = CoroutineScope(Dispatchers.Main)
    private lateinit var loaderJob: Job

    fun showLoader() {
        loaderJob = coroutineLottie.launch {
            binding.loader.apply {
                if (isVisible.not()) {
                    isVisible = true
                    playAnimation()
                }
            }
        }
    }

    fun hideLoader() {
        coroutineLottie.launch {
            binding.loader.apply {
                if (isVisible) {
                    isVisible = false
                    loaderJob.cancel()
                }
            }
        }
    }

    fun showNotInternet() {
        coroutineLottie.launch {
            binding.internet.apply {
                if (isVisible.not()) {
                    hideLoader()
                    isVisible = true
                    playAnimation()
                }
            }
        }
    }

}