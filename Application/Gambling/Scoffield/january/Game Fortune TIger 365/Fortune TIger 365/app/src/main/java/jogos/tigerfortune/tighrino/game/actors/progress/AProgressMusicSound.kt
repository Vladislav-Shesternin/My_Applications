package jogos.tigerfortune.tighrino.game.actors.progress

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import jogos.tigerfortune.tighrino.game.utils.advanced.AdvancedGroup
import jogos.tigerfortune.tighrino.game.utils.advanced.AdvancedScreen
import jogos.tigerfortune.tighrino.game.utils.runGDX

class AProgressMusicSound(override val screen: AdvancedScreen): AdvancedGroup() {

    private val LENGTH = 315f

    private val progressImage = Image(screen.game.gameAssets.cursor)

    private val onePercentX = LENGTH / 100f

    // 0 .. 100 %
    val progressPercentFlow = MutableStateFlow(0f)


    override fun addActorsOnGroup() {
        addProgress()

        coroutine?.launch {
            progressPercentFlow.collect { percent ->
                runGDX { progressImage.x = percent * onePercentX }
            }
        }

        addListener(inputListener())
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addProgress() {
        addActor(progressImage)
        progressImage.setBounds(0f, 0f, 90f, 72f)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun inputListener() = object : InputListener() {
        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            touchDragged(event, x, y, pointer)
            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            progressPercentFlow.value = when {
                x <= 0 -> 0f
                x >= LENGTH -> 100f
                else -> x / onePercentX
            }
        }
    }

    fun setProgressPercent(percent: Float) {
        progressPercentFlow.value = percent
    }


}