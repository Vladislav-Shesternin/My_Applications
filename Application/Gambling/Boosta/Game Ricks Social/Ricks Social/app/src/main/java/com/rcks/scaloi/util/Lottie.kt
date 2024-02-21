package com.rcks.scaloi.util

import androidx.core.view.isVisible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.rcks.scaloi.databinding.ActivityMainBinding

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
                }
            }
        }
    }

}