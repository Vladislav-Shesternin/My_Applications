package com.veldan.bigwinslots777.actors.super_game

import com.veldan.bigwinslots777.actors.roulette.superGameRoulette.SuperGameRouletteItem
import com.veldan.bigwinslots777.manager.assets.util.SoundUtil
import com.veldan.bigwinslots777.manager.assets.util.playAdvanced
import com.veldan.bigwinslots777.utils.*
import com.veldan.bigwinslots777.utils.controller.GroupController
import kotlinx.coroutines.*

class SuperGameGroupController(override val group: SuperGameGroup) : GroupController {

    companion object {
        const val TIME_SHOW_GROUP   = 1f
        const val TIME_HIDE_GROUP   = 1f
        const val TIME_SHOW_ELEMENT = 0.5f
    }

    private val coroutineMain = CoroutineScope(Dispatchers.Default)

    val numberList = mutableListOf<Int>()

    var currentRouletteIndex = 0

    var doAfterFinish: (numbers: List<Int>?) -> Unit = {}



    private suspend fun fail() = CompletableDeferred<Boolean>().also {
        group.addDialogFail()
        group.dialogGroup.showAnim(TIME_SHOW_GROUP)
        SoundUtil.FAIL.playAdvanced()
    }.await()

    private suspend fun win() = CompletableDeferred<Boolean>().also {
        group.addDialogWin()
        group.dialogGroup.showAnim(TIME_SHOW_GROUP)
        SoundUtil.WIN.playAdvanced()
    }.await()

    private suspend fun changeRoulette() {
        group.rouletteList[currentRouletteIndex].hideAnim(TIME_HIDE_GROUP)
        currentRouletteIndex++
        group.rouletteList[currentRouletteIndex].showAnim(TIME_SHOW_GROUP)
    }


    fun spin() {
        group.spinButton.controller.disable()
        coroutineMain.launch {
            val number = (group.rouletteList[currentRouletteIndex].controller.spin() as SuperGameRouletteItem).number
            log("NUMBER - $number")

            if (currentRouletteIndex == 0 && number == 0) fail()
            else {
                numberList.add(number)
                group.elementGroup.apply {
                    elementLabelList[currentRouletteIndex].setText(number)
                    elementImageList[currentRouletteIndex].showAnim(TIME_SHOW_ELEMENT)
                    elementLabelList[currentRouletteIndex].showAnim(TIME_SHOW_ELEMENT)
                }
            }

            if (currentRouletteIndex == 2) win()

            changeRoulette()
            group.spinButton.controller.enable()

        }
    }

}