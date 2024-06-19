package com.dogbytegames.offtheroa

class Hello {
    val intField: Int = 42
    var stringField: String = "Hello, Kotlin!"
    val doubleField: Double = 3.14
    var booleanField: Boolean = true
    val listField: List<String> = listOf("one", "two", "three")
    var mapField: Map<String, Int> = mapOf("key1" to 1, "key2" to 2)
    val charField: Char = 'K'
    var floatField: Float = 2.71f
    val longField: Long = 123456789L
    var byteField: Byte = 127

    fun calculateSum(a: Int, b: Int): Int {
        val sum = a + b
      return  sum
    }

    var iR = ""

    fun concatenateStrings(str1: String, str2: String): String {
        val result = str1 + str2
     return   result
    }

    fun toggleBoolean(): Boolean {
        booleanField = !booleanField
     return   booleanField
    }



    fun findMaxInList(): String? {
        val maxElement = listField.maxOrNull()
      return  maxElement
    }

    fun addToMap(key: String, value: Int): Map<String, Int> {
        mapField = mapField + (key to value)
      return  mapField
    }

    fun convertCharToAscii(): Int {
        val asciiValue = charField.code
       return asciiValue
    }

    fun calculateCircleArea(radius: Double): Double {
        val area = Math.PI * radius * radius
        return area
    }

    fun getStringLength(str: String): Int {
        val length = str.length
        return length
    }

    fun convertToLong(intValue: Int): Long {
        val longValue = intValue.toLong()
        return longValue
    }

    fun doubleToInt(doubleValue: Double): Int {
        val intValue = doubleValue.toInt()
        return intValue
    }
}

const val d3 = "com.android.browser"