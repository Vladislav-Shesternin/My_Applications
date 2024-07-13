package com.YovoGames.magicBo.util

import androidx.core.view.isVisible
import com.YovoGames.magicBo.databinding.ActivityGameBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class LottieUtil(private val binding: ActivityGameBinding) {

    private val coroutineLottie = CoroutineScope(Dispatchers.Main)

    fun show() {
        coroutineLottie.launch {
            binding.colors.apply {
                if (isVisible.not()) {
                    isVisible = true
                    playAnimation()
                }
            }
        }
    }

    fun hide() {
        coroutineLottie.launch {
            binding.colors.apply {
                if (isVisible) {
                    isVisible = false
                    coroutineLottie.coroutineContext.cancelChildren()
                }
            }
        }
    }

}