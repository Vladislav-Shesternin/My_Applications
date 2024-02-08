package com.csmttt.medus.play.util

import androidx.core.view.isVisible
import com.csmttt.medus.play.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class Lottie(private val binding: ActivityMainBinding) {

    private val coroutineLottie = CoroutineScope(Dispatchers.Main)

    fun showLoader() {
        coroutineLottie.launch {
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
                    coroutineLottie.coroutineContext.cancelChildren()
                }
            }
        }
    }

    fun showNotInternet() {
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