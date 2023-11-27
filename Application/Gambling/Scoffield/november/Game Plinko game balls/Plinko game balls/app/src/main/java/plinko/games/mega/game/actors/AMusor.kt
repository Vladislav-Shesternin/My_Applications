package plinko.games.mega.game.actors

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import plinko.games.mega.game.utils.advanced.AdvancedGroup
import plinko.games.mega.game.utils.advanced.AdvancedScreen
import plinko.games.mega.game.utils.runGDX
import java.util.Random

class AMusor(override val screen: AdvancedScreen): AdvancedGroup() {

    private val kometeList = List(5) { Image(screen.game.splashAssets.MUSOR_LIST[it]) }
    private val flow       = MutableSharedFlow<Image>(replay = 5)

    private val scaleRR    get() = (50..200).random() / 100f
    private val yRR        get() = (180..2000).random().toFloat()
    private val durationRR get() = (10..40).random() / 10f
    private val durationRR2 get() = (40..60).random() / 10f

    override fun addActorsOnGroup() {
        kometeList.onEach {
            addActor(it)
            it.setBounds(-40f, 0f, 10f, 10f)
            flow.tryEmit(it)
        }

        coroutine?.launch {
            flow.collect {
                runGDX {
                    it.clearActions()
                    it.setSize(108f, 108f)
                    it.setOrigin(Align.center)
                    it.setScale(scaleRR)
                    it.setPosition(-it.width, yRR)

                    it.addAction(Actions.sequence(
                        Actions.parallel(
                            Actions.rotateBy(if (Random().nextBoolean()) 360f else -360f, durationRR2),
                            Actions.moveTo(1080f+it.width, it.y-(300..1000).random(), durationRR),
                        ),
                        Actions.run { flow.tryEmit(it) }
                    ))

                }

                delay((700..1000L).random())
            }
        }

    }

}