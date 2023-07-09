package com.supertest.card.util

import androidx.core.view.isVisible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.supertest.card.databinding.ActivityMainBinding

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
        coroutineLottie.launch {
            binding.lottieLoader.apply {
                if (isVisible) {
                    isVisible = false
                    loaderJob.cancel()
                }
            }
        }
    }

    fun showNotNetwork() {
        coroutineLottie.launch {
            binding.lottieNetwork.apply {
                if (isVisible.not()) {
                    hideLoader()
                    isVisible = true
                    playAnimation()
                }
            }
        }
    }

}