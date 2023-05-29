//package com.kurs.mon.fin.game.util
//
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.isActive
//import kotlinx.coroutines.launch
//
//class TimerUtil {
//
//    private var time = 60
//
//    val blockList = mutableListOf<(Int) -> Unit>()
//
//
//
//    fun initialize(coroutineScope: CoroutineScope) {
//        coroutineScope.launch {
//            while (isActive) {
//                delay(1000)
//                time -= 1
//                blockList.onEach { it.invoke(time) }
//                if (time == 0) time = 60
//            }
//        }
//    }
//
//}