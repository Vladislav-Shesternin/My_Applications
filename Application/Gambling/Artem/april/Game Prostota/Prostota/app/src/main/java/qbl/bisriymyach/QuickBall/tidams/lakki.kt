package qbl.bisriymyach.QuickBall.tidams

import androidx.core.view.isVisible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import qbl.bisriymyach.QuickBall.databinding.ActivityMainBinding

class lakki(private val binding: ActivityMainBinding) {

    private val coroutineLottie = CoroutineScope(Dispatchers.Main)

    fun tyarampa8() {





            coroutineLottie.launch {
                                                     binding.pompa.apply {
                if (isVisible.not()) {
                                     isVisible = true
                    playAnimation()
                }
            }
        }
    }

    fun yuaA() {
                             coroutineLottie.launch {
                    binding.pompa.apply {
                if (isVisible) {
                    isVisible = false
                    coroutineLottie.coroutineContext.cancelChildren()
                }
            }
        }
    }

}