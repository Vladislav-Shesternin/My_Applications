package com.cosmo.plinko.game.actors

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import com.cosmo.plinko.game.utils.advanced.AdvancedGroup
import com.cosmo.plinko.game.utils.advanced.AdvancedScreen
import com.cosmo.plinko.game.utils.runGDX

class AKomete(override val screen: AdvancedScreen): AdvancedGroup() {

    private val kometeList = List(5) { Image(screen.game.splashAssets.KOMETA) }
    private val flow       = MutableSharedFlow<Image>(replay = 5)

    private val scaleRR    get() = (50..200).random() / 100f
    private val yRR        get() = (180..2000).random().toFloat()
    private val durationRR get() = (5..20).random() / 10f

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
                    it.setSize(310f, 251f)
                    it.setScale(scaleRR)
                    it.setPosition(-it.width, yRR)

                    it.addAction(Actions.sequence(
                        Actions.moveTo(1080f+it.width, it.y-700, durationRR),
                        Actions.run { flow.tryEmit(it) }
                    ))

                }

                delay((200..1000L).random())
            }
        }

    }

}