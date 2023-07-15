package com.danila.cryptotracker.util

import androidx.core.view.isVisible
import com.danila.cryptotracker.databinding.PomelaAndersonMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Lottie(private val binding: PomelaAndersonMainBinding) {

    fun showLoader() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.broder.apply {
                if (isVisible.not()) {
                    isVisible = true
                    playAnimation()
                }
            }
        }
    }

    fun hideLoader() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.broder.apply {
                if (isVisible) {
                    isVisible = false
                    cancelAnimation()
                }
            }
        }
    }

    fun showNotInternet() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.netr.apply {
                if (isVisible.not()) {
                    isVisible = true
                    playAnimation()
                }
            }
        }
    }

    fun hideNotInternet() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.netr.apply {
                if (isVisible) {
                    isVisible = false
                    cancelAnimation()
                }
            }
        }
    }

}