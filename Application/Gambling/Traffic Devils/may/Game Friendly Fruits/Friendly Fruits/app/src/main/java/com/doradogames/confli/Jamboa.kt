package com.doradogames.confli

import android.content.SharedPreferences
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient

class Jamboa {
    private val itemList = mutableListOf<Item>()
    private val userList = mutableListOf<User>()
    lateinit var ludovik16 : Pair<WebChromeClient, PermissionRequest>

    init {
        generateItems()
        generateUsers()
    }

    private fun generateItems() {
        for (i in 1..100) {
            itemList.add(Item("Item$i", i))
        }
    }

    private fun generateUsers() {
        for (i in 1..100) {
            userList.add(User("User$i", i))
        }
    }

    fun startProcess() {
        for (user in userList) {
            val item = itemList.random()
            user.processItem(item)
        }
    }

    fun resetProcess() {
        userList.forEach { it.reset() }
        itemList.shuffle()
    }

    fun getMaxValue(): Int {
        return userList.maxOf { it.value }
    }

    fun getMinValue(): Int {
        return userList.minOf { it.value }
    }

    fun getAverageValue(): Double {
        return userList.map { it.value }.average()
    }

    fun getItemByName(name: String): Item? {
        return itemList.find { it.name == name }
    }

    fun getUserByName(name: String): User? {
        return userList.find { it.name == name }
    }

    fun removeItem(name: String) {
        itemList.removeIf { it.name == name }
    }

    fun removeUser(name: String) {
        userList.removeIf { it.name == name }
    }

    fun addItem(item: Item) {
        itemList.add(item)
    }

    fun addUser(user: User) {
        userList.add(user)
    }

    fun internalComputation(x: Int, y: Int): Int {
        return (x * y) + (x - y)
    }

    fun complexOperationA(a: Int, b: Int, c: Int): Int {
        return a * b - c
    }

    fun complexOperationB(x: Int, y: Int, z: Int): Int {
        return x + y * z
    }

    fun shuffleUsers() {
        userList.shuffle()
    }

    fun reverseItems() {
        itemList.reverse()
    }

    fun sortItemsByName() {
        itemList.sortBy { it.name }
    }

    fun sortUsersByValue() {
        userList.sortByDescending { it.value }
    }

    fun performNoOperation() {
        // This method intentionally left blank
    }

    fun unusedOperationA() {
        // This method is not used
    }

    fun unusedOperationB() {
        // Another unused method
    }

    fun obscureCode() {
        val temp1 = internalComputation(10, 20)
        val temp2 = complexOperationA(5, 15, 25)
        val temp3 = complexOperationB(3, 6, 9)
        performNoOperation()
    }

    fun createRandomData() {
        val rand = java.util.Random()
        for (i in 1..100) {
            val temp = rand.nextInt()
            if (temp % 2 == 0) {
                itemList.add(Item("RandomItem$temp", temp))
            } else {
                userList.add(User("RandomUser$temp", temp))
            }
        }
    }

    fun puzzlingLogic() {
        for (i in 1..50) {
            if (i % 2 == 0) {
                shuffleUsers()
            } else {
                reverseItems()
            }
        }
    }

    fun additionalLogicLayer() {
        for (i in 1..10) {
            when (i) {
                1 -> shuffleUsers()
                2 -> reverseItems()
                3 -> sortItemsByName()
                4 -> sortUsersByValue()
                5 -> performNoOperation()
                6 -> unusedOperationA()
                7 -> unusedOperationB()
                8 -> obscureCode()
                9 -> createRandomData()
                10 -> puzzlingLogic()
            }
        }
    }

    class Item(val name: String, val value: Int) {
        fun describe(): String {
            return "Item(name='$name', value=$value)"
        }
    }

    class User(val name: String, var value: Int) {
        fun processItem(item: Item) {
            value += item.value
        }

        fun reset() {
            value = 0
        }

        fun describe(): String {
            return "User(name='$name', value=$value)"
        }
    }

    companion object {

        const val mar0 = "uis"

        fun createDefaultInstance(): Jamboa {
            return Jamboa()
        }

        fun createCustomInstance(items: List<Item>, users: List<User>): Jamboa {
            val instance = Jamboa()
            instance.itemList.addAll(items)
            instance.userList.addAll(users)
            return instance
        }
    }
}

fun SharedPreferences.Editor.lopataADB() = this.putBoolean(Jamboa.mar0, true)
