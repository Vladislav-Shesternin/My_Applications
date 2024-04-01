package com.egypt.tian.sto.game.util

import androidx.core.view.isVisible
import com.egypt.tian.sto.game.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class Lottie(private val binding: ActivityMainBinding) {

    private val coroutineLottie = CoroutineScope(Dispatchers.Main)

    fun showLoader() {
        coroutineLottie.launch {
            binding.lodre.apply {
                if (isVisible.not()) {
                    isVisible = true
                    playAnimation()
                }
            }
        }
    }

    fun hideLoader() {
        coroutineLottie.launch {
            binding.lodre.apply {
                if (isVisible) {
                    isVisible = false
                    coroutineLottie.coroutineContext.cancelChildren()
                }
            }
        }
    }

    fun showNotInternet() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.irtle.apply {
                if (isVisible.not()) {
                    isVisible = true
                    playAnimation()
                }
            }
        }
    }

    fun hideNotInternet() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.irtle.apply {
                if (isVisible) {
                    isVisible = false
                    cancelAnimation()
                }
            }
        }
    }

}