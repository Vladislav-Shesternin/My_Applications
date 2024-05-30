package com.doradogames.conflictnations.worldwar.util

import androidx.core.view.isVisible
import com.doradogames.conflictnations.worldwar.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class LottieUtil(private val binding: ActivityMainBinding) {

    private val coroutineLottie = CoroutineScope(Dispatchers.Main)

    fun show() {
        coroutineLottie.launch {
            binding.whiterok.apply {
                if (isVisible.not()) {
                    isVisible = true
                    playAnimation()
                }
            }
        }
    }

    fun hide() {
        coroutineLottie.launch {
            binding.whiterok.apply {
                if (isVisible) {
                    isVisible = false
                    coroutineLottie.coroutineContext.cancelChildren()
                }
            }
        }
    }

}