package com.fellinger.yeasman.game.utils

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Disposable
import com.fellinger.yeasman.game.box2d.WorldUtil
import com.fellinger.yeasman.game.box2d.bodiesGroup.BG_Balk
import com.fellinger.yeasman.game.utils.advanced.AdvancedBox2dScreen
import com.fellinger.yeasman.utils.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.fellinger.yeasman.game.utils.Layout.BalkGenerator as LBG

class BalkGenerator(val worldUtil: WorldUtil, val screen: AdvancedBox2dScreen): Disposable {

    private val coroutine = CoroutineScope(Dispatchers.Default)

    private val listBalk = List(5) { BG_Balk(worldUtil) }
    private val renderList = mutableListOf<BG_Balk>()

    private var isInitialize = false


    override fun dispose() {
        listBalk.onEach { it.destroy() }
        cancelCoroutinesAll(coroutine)
    }


    fun initialize() {
        with(screen) {
            listBalk.onEach { balk ->
                balk.initialize(
                    stageUI,
                    sizeConverterUIToBox,
                    sizeConverterBoxToUI,
                    generateStartPosition(),
                    LBG.balkSize,
                )
            }
            isInitialize = true
        }

        coroutine.launch {
            listBalk.onEach { balk ->
                renderList.add(balk)
                delay(3000)
            }
        }

    }

    fun render(time: Float) {
       renderList.onEachIndexed { index, balk ->
           balk.changePosition(balk.position.x - 3f, balk.position.y)
           if (balk.position.x <= -LBG.balkSize.width) {
               if (renderList.size == listBalk.size) {
                   if (index == 0) {
                       if (renderList.last().position.x.toInt() in 0..1450) generateStartPosition().also { pos -> balk.changePosition(pos.x, pos.y) }
                   } else {
                       if (renderList[index - 1].position.x.toInt() in 0..1450) generateStartPosition().also { pos -> balk.changePosition(pos.x, pos.y) }
                   }
               }
           }
       }
    }

    private fun generateStartPosition() = Vector2(
        LBG.startX,
        (LBG.minY.toInt()..LBG.maxY.toInt()).shuffled().first().toFloat()
    )

}