package com.obezana.playtocrypto.util

import androidx.core.view.isVisible
import com.obezana.playtocrypto.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Lottie(private val binding: ActivityMainBinding) {

    fun showLoader() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.loader.apply {
                if (isVisible.not()) {
                    isVisible = true
                    playAnimation()
                }
            }
        }
    }

    fun hideLoader() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.loader.apply {
                if (isVisible) {
                    isVisible = false
                    cancelAnimation()
                }
            }
        }
    }

    fun showNotInternet() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.internet.apply {
                if (isVisible.not()) {
                    isVisible = true
                    playAnimation()
                }
            }
        }
    }

    fun hideNotInternet() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.internet.apply {
                if (isVisible) {
                    isVisible = false
                    cancelAnimation()
                }
            }
        }
    }

}