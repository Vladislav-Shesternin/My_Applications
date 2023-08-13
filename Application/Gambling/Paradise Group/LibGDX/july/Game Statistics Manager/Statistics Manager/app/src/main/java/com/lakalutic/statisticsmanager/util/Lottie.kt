package com.lakalutic.statisticsmanager.util

import androidx.core.view.isVisible
import com.lakalutic.statisticsmanager.databinding.PilotkaBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Lottie(private val binding: PilotkaBinding) {

    fun showLoader() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.luda.apply {
                if (isVisible.not()) {
                    isVisible = true
                    playAnimation()
                }
            }
        }
    }

    fun hideLoader() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.luda.apply {
                if (isVisible) {
                    isVisible = false
                    cancelAnimation()
                }
            }
        }
    }

    fun showNotInternet() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.koxa.apply {
                if (isVisible.not()) {
                    isVisible = true
                    playAnimation()
                }
            }
        }
    }

    fun hideNotInternet() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.koxa.apply {
                if (isVisible) {
                    isVisible = false
                    cancelAnimation()
                }
            }
        }
    }

}