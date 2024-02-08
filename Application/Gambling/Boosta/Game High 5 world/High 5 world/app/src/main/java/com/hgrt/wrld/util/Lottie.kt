package com.hgrt.wrld.util

import androidx.core.view.isVisible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.hgrt.wrld.databinding.ActivityMainBinding

class Lottie(private val binding: ActivityMainBinding) {

    private val coroutineLottie = CoroutineScope(Dispatchers.Main)
    private lateinit var loaderJob: Job

    fun showLoader() {
        loaderJob = coroutineLottie.launch {
            binding.lottieLoader.apply {
                if (isVisible.not()) {
                    isVisible = true
                    playAnimation()
                }
            }
        }
    }

    fun hideLoader() {
        binding.lottieLoader.apply {
            if (isVisible) {
                isVisible = false
                loaderJob.cancel()
            }
        }
    }

}