package com.dogbytegames.offtheroa.util

import androidx.core.view.isVisible
import com.dogbytegames.offtheroa.databinding.ActivityGameBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class LottieUtil(private val binding: ActivityGameBinding) {

    private val coroutineLottie = CoroutineScope(Dispatchers.Main)

    fun show() {
        coroutineLottie.launch {
            binding.chastik.apply {
                if (isVisible.not()) {
                    isVisible = true
                    playAnimation()
                }
            }
        }
    }

    fun hide() {
        coroutineLottie.launch {
            binding.chastik.apply {
                if (isVisible) {
                    isVisible = false
                    coroutineLottie.coroutineContext.cancelChildren()
                }
            }
        }
    }

}