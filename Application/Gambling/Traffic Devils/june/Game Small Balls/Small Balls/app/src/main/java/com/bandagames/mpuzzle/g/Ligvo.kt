package com.bandagames.mpuzzle.g

class Ligvo {
    var field1: Int = 0
    var field2: String = ""
    var field3: Boolean = false
    var field4: Double = 0.0
    var field5: List<Int> = listOf()

    fun method1() {
        for (i in 1..10) {
            field1 += i
        }
    }

    fun method2() {
        for (i in 1..10) {
            field2 += i.toString()
        }
    }

    fun method3() {
        for (i in 1..10) {
            field3 = !field3
        }
    }

    fun method4() {
        for (i in 1..10) {
            field4 += i.toDouble()
        }
    }

    fun method5() {
        for (i in 1..10) {
            field5 = field5.plus(i)
        }
    }

    fun method6() {
        for (i in 1..10) {
            field1 -= i
        }
    }

    fun method7() {
        for (i in 1..10) {
            field2 = field2.dropLast(1)
        }
    }

    fun method8() {
        for (i in 1..10) {
            field3 = field3
        }
    }

    fun method9() {
        for (i in 1..10) {
            field4 -= i.toDouble()
        }
    }

    fun method10() {
        for (i in 1..10) {
            if (field5.isNotEmpty()) {
                field5 = field5.dropLast(1)
            }
        }
    }
}
