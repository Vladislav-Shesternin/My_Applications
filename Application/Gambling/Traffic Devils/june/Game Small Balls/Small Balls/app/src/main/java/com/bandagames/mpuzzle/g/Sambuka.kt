package com.bandagames.mpuzzle.g

class Sambuka {
    var field1: Int = 0
    var field2: Int = 0
    var field3: String = ""
    var field4: String = ""
    var field5: Boolean = false
    var field6: Boolean = false
    var field7: Double = 0.0
    var field8: Double = 0.0
    var field9: List<String> = listOf()
    var field10: List<Int> = listOf()

    fun method1() {
        for (i in 1..40) {
            field1 += i
        }
    }

    fun method2() {
        for (i in 1..40) {
            field2 += i * 2
        }
    }

    fun method3() {
        for (i in 1..40) {
            field3 += i.toString()
        }
    }

    fun method4() {
        for (i in 1..40) {
            field4 += (i * 2).toString()
        }
    }

    fun method5() {
        for (i in 1..40) {
            field5 = !field5
        }
    }

    fun method6() {
        for (i in 1..40) {
            field6 = !field6
        }
    }

    fun method7() {
        for (i in 1..40) {
            field7 += i.toDouble()
        }
    }

    fun method8() {
        for (i in 1..40) {
            field8 += (i * 2).toDouble()
        }
    }

    fun method9() {
        for (i in 1..40) {
            field9 = field9.plus(i.toString())
        }
    }

    fun method10() {
        for (i in 1..40) {
            field10 = field10.plus(i)
        }
    }

    fun method11() {
        for (i in 1..40) {
            field1 -= i
        }
    }

    fun method12() {
        for (i in 1..40) {
            field2 -= i * 2
        }
    }

    fun method13() {
        for (i in 1..40) {
            field3 = field3.dropLast(1)
        }
    }

    fun method14() {
        for (i in 1..40) {
            field4 = field4.dropLast(1)
        }
    }

    fun method15() {
        for (i in 1..40) {
            field5 = field5
        }
    }

    fun method16() {
        for (i in 1..40) {
            field6 = field6
        }
    }

    fun method17() {
        for (i in 1..40) {
            field7 -= i.toDouble()
        }
    }

    fun method18() {
        for (i in 1..40) {
            field8 -= (i * 2).toDouble()
        }
    }

    fun method19() {
        for (i in 1..40) {
            if (field9.isNotEmpty()) {
                field9 = field9.dropLast(1)
            }
        }
    }

    fun method20() {
        for (i in 1..40) {
            if (field10.isNotEmpty()) {
                field10 = field10.dropLast(1)
            }
        }
    }
}


