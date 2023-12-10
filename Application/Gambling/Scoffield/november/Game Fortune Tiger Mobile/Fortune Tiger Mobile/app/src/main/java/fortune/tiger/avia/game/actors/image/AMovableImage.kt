package fortune.tiger.avia.game.actors.image

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import fortune.tiger.avia.game.actors.Pipka
import fortune.tiger.avia.game.utils.Layout
import fortune.tiger.avia.game.utils.advanced.AdvancedScreen
import fortune.tiger.avia.game.utils.runGDX
import fortune.tiger.avia.util.log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

open class AMovableImage constructor(
    override val screen: AdvancedScreen,
    val winDon: MutableStateFlow<Int>,
    val filDon: MutableStateFlow<Int>,
): AImage(screen) {

    var id = -1

    var pipkaList = mutableListOf<Pipka>()


    private var pipkaIndex = -1

    override fun addActorsOnGroup() {
        super.addActorsOnGroup()
        val startX = x
        val startY = y

        addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                touchDragged(event, x, y, pointer)
                return true
            }

            override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
                this@AMovableImage.x += x - (width/2)
                this@AMovableImage.y += y - (height/2)


                this@AMovableImage.also { i ->
                    when {
                        ((i.x+(width/2)).toInt() in 76..320) && ((i.y+(width/2)).toInt() in 801..1128) -> showPipka(0)
                        ((i.x+(width/2)).toInt() in 418..662) && ((i.y+(width/2)).toInt() in 801..1128) -> showPipka(1)
                        ((i.x+(width/2)).toInt() in 760..1004) && ((i.y+(width/2)).toInt() in 801..1128) -> showPipka(2)
                        else -> { hidePipka() }
                    }
                }

            }

            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                if (pipkaIndex != -1) {
                    val pos = Layout.Igrushes.yesimg[pipkaIndex]
                    addAction(Actions.moveTo(pos.x, pos.y, 0.2f))
                    screen.game.soundUtil.apply { play(PRILIP) }

                    if (pipkaList[pipkaIndex].id == id) {
                        winDon.value++
                        log("win = ${winDon.value}")
                        pipkaList[pipkaIndex].hide()
                    }
                    else {
                        screen.game.soundUtil.apply { play(FUILE) }
                        filDon.value++
                        log("fail = ${filDon.value}")
                        pipkaList[pipkaIndex].showNot {
                            clearActions()
                            addAction(Actions.moveTo(startX, startY, 1f, Interpolation.bounceOut))
                        }
                    }

                    pipkaIndex = -1

                } else {
                    clearActions()
                    addAction(Actions.moveTo(startX, startY, 1f, Interpolation.bounceOut))
                }
            }
        })
    }

    private fun showPipka(index: Int) {
        pipkaIndex = index
        pipkaList[index].showYes()
    }

    private fun hidePipka() {
        pipkaIndex = -1
        pipkaList.onEach { it.hide() }
    }

}