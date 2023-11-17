package aiebu.kakono.tutokazalos.parmengo

import androidx.core.view.isVisible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import aiebu.kakono.tutokazalos.databinding.ActivityMainBinding

class Lottie(private val binding: ActivityMainBinding) {

    private val coroutineLottie = CoroutineScope(Dispatchers.Main)

    fun showLoader() {
        coroutineLottie.launch {
            binding.lord.apply {
                if (isVisible.not()) {
                    isVisible = true
                    playAnimation()
                }
            }
        }
    }

    fun hideLoader() {
        coroutineLottie.launch {
            binding.lord.apply {
                if (isVisible) {
                    isVisible = false
                    coroutineLottie.coroutineContext.cancelChildren()
                }
            }
        }
    }

    fun showNotInternet() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.pisd.apply {
                if (isVisible.not()) {
                    isVisible = true
                    playAnimation()
                }
            }
        }
    }

    fun hideNotInternet() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.pisd.apply {
                if (isVisible) {
                    isVisible = false
                    cancelAnimation()
                }
            }
        }
    }

}