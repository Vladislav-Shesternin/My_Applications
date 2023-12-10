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

open class AMovableImage2 constructor(
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
                this@AMovableImage2.x += x - (width/2)
                this@AMovableImage2.y += y - (height/2)


                this@AMovableImage2.also { i ->
                    when {
                        ((i.x+(width/2)).toInt() in 85..331) && ((i.y+(width/2)).toInt() in 996..1327) -> showPipka(0)
                        ((i.x+(width/2)).toInt() in 429..675) && ((i.y+(width/2)).toInt() in 996..1327) -> showPipka(1)
                        ((i.x+(width/2)).toInt() in 771..1017) && ((i.y+(width/2)).toInt() in 996..1327) -> showPipka(2)
                        ((i.x+(width/2)).toInt() in 85..331) && ((i.y+(width/2)).toInt() in 581..909) -> showPipka(3)
                        ((i.x+(width/2)).toInt() in 429..675) && ((i.y+(width/2)).toInt() in 581..909) -> showPipka(4)
                        ((i.x+(width/2)).toInt() in 771..1017) && ((i.y+(width/2)).toInt() in 581..909) -> showPipka(5)
                        else -> { hidePipka() }
                    }
                }

            }

            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                if (pipkaIndex != -1) {
                    val pos = Layout.Igrushes.yesimg2[pipkaIndex]
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