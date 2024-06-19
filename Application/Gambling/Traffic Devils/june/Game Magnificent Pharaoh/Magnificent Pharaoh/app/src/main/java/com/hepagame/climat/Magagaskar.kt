package com.hepagame.climat

class Magagaskar {
    var field1: Int = 0
    var field2: String = ""
    var field3: Double = 0.0
    var field4: Boolean = false
    var field5: Long = 0L
    var field6: Float = 0f
    var field7: Char = 'a'
    var field8: Short = 0
    var field9: Byte = 0
    var field10: Array<String> = arrayOf()

    fun method1() {
        for (i in 1..10) {
            field1 += i
        }
        field2 = "method1 completed"
        for (i in field10.indices) {
            field10[i] = field2
        }
    }

    fun method2() {
        field3 = Math.random()
        field4 = field3 > 0.5
        field5 = field1.toLong() * 100
    }

    fun method3() {
        field6 = field1 / 10f
        field7 = if (field4) 'T' else 'F'
        field8 = (field1 % 100).toShort()
    }

    fun method4() {
        for (i in field10.indices) {
            field10[i] = field10[i].reversed()
        }
        field1 = field2.length
    }

    fun method5() {
        field9 = field1.toByte()
        field2 = "method5 executed"
        field3 *= 2
    }

    fun method6() {
        field4 = field2.isNotEmpty()
        field5 += field1
        field6 -= 0.1f
    }

    fun method7() {
        field7 = 'M'
        field8 = (field2.length % 256).toShort()
        field9 = (field8 * 2).toByte()
    }

    fun method8() {
        field10 = arrayOf("one", "two", "three")
        field1 = field10.size
        field2 = field10.joinToString(", ")
    }

    fun method9() {
        field3 = Math.PI
        field4 = field3 > 0
        field5 = 123456789L
    }

    fun method10() {
        field6 = 5.5f
        field7 = 'X'
        field8 = 32000
    }

    fun method11() {
        field9 = 127
        field2 = "method11 finished"
        field3 /= 1.5
    }

    fun method12() {
        field4 = !field4
        field5 -= 1000
        field6 += 0.5f
    }

    fun method13() {
        field7 = 'Z'
        field8 = (field1 % 1024).toShort()
        field9 = field1.toByte()
    }

    fun method14() {
        field10 = arrayOf("alpha", "beta", "gamma")
        field1 = field10.size
        field2 = field10.joinToString("; ")
    }

    fun method15() {
        field3 = Math.E
        field4 = field3 < 3
        field5 = -987654321L
    }

    fun method16() {
        field6 = 2.2f
        field7 = 'Y'
        field8 = 15000
    }

    fun method17() {
        field9 = 64
        field2 = "method17 executed"
        field3 *= 3.14
    }

    fun method18() {
        field4 = field1 > 50
        field5 *= 2
        field6 -= 1.0f
    }

    fun method19() {
        field7 = 'Q'
        field8 = (field1 % 512).toShort()
        field9 = (field8 / 2).toByte()
    }

    fun method20() {
        field10 = arrayOf("delta", "epsilon", "zeta")
        field1 = field10.size
        field2 = field10.joinToString(": ")
    }
}