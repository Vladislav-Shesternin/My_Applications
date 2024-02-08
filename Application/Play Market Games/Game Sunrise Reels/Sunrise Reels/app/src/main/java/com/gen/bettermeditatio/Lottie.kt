package com.gen.bettermeditatio

import androidx.core.view.isVisible
import com.gen.bettermeditatio.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

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

}