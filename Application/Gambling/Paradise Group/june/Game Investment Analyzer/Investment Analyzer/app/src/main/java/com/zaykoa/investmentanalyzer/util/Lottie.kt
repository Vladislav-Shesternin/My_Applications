package com.zaykoa.investmentanalyzer.util

import androidx.core.view.isVisible
import com.zaykoa.investmentanalyzer.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Lottie(private val binding: ActivityMainBinding) {

    fun showLoader() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.leadegro.apply {
                if (isVisible.not()) {
                    isVisible = true
                    playAnimation()
                }
            }
        }
    }

    fun hideLoader() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.leadegro.apply {
                if (isVisible) {
                    isVisible = false
                    cancelAnimation()
                }
            }
        }
    }

    fun showNotInternet() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.inernationale.apply {
                if (isVisible.not()) {
                    hideLoader()
                    isVisible = true
                    playAnimation()
                }
            }
        }
    }

}