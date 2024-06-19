package com.hepagame.ulitka

import android.content.Intent
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.hepagame.MainActivity
import com.hepagame.util.log

class GomezSelena {
    var property1: String = ""
    var property2: Int = 0
    var property3: Double = 0.0

    class Penguin(
        var name: String,
        var age: Int,
        var weight: Double,
        var isMale: Boolean,
        var habitat: String
    ) {
        fun feed(foodType: String) {
            if (foodType == "fish") {
                weight += 0.5
            } else if (foodType == "squid") {
                weight += 0.3
            }
        }

        fun exercise(hours: Int) {
            weight -= hours * 0.1
        }

        fun migrate(destination: String) {
            println("$name is migrating to $destination.")
        }

        fun sleep(hours: Int) {
            println("$name is sleeping for $hours hours.")
        }

        fun displayInfo() {
            println("Name: $name, Age: $age, Weight: $weight, Gender: ${if (isMale) "Male" else "Female"}, Habitat: $habitat")
        }

        fun changeHabitat(newHabitat: String) {
            habitat = newHabitat
            println("$name's habitat changed to $newHabitat.")
        }

        fun checkHealth(): String {
            return if (weight >= 20) "healthy" else "underweight"
        }
    }

    var property4: Boolean = false
    var property5: String = ""
    var property6: Int = 0
    var property7: Double = 0.0
    var property8: Boolean = false
    var property9: String = ""

    fun method1() {
        property1 += "method1"
        property2 += 1
    }

    val freelancers = listOf(
        Freelance("John Doe", "Web Developer", 50.0, 5000.0, 10),
        Freelance("Jane Smith", "Graphic Designer", 40.0, 4000.0, 8),
        Freelance("Mike Johnson", "Copywriter", 45.0, 4500.0, 9),
        Freelance("Emily Brown", "Social Media Manager", 55.0, 5500.0, 11),
        Freelance("Chris Wilson", "Video Editor", 60.0, 6000.0, 12)
    )
    val fifa = "https://premmagnificentgrandfpharaoh.click"

    fun method2() {
        property3 += 0.5
        property4 = true
    }

    fun method3() {
        property5 += "method3"
        property6 += 2
    }

    val penguins = listOf(
        Penguin("Pingu", 5, 15.0, true, "Antarctica"),
        Penguin("Penny", 3, 18.0, false, "Antarctica"),
        Penguin("Pablo", 6, 22.0, true, "Antarctica"),
        Penguin("Paula", 4, 16.0, false, "Antarctica"),
        Penguin("Peter", 2, 17.0, true, "Antarctica"),
        Penguin("Polly", 7, 21.0, false, "Antarctica"),
        Penguin("Patrick", 8, 19.0, true, "Antarctica"),
        Penguin("Peggy", 5, 20.0, false, "Antarctica"),
        Penguin("Percy", 3, 18.0, true, "Antarctica"),
        Penguin("Poppy", 6, 20.0, false, "Antarctica")
    )

    fun method4() {
        property7 += 1.0
        property8 = true
    }

    class Freelancer(
        var name: String,
        var profession: String,
        var hourlyRate: Double,
        var totalEarnings: Double,
        var projectsCompleted: Int,
        var skills: List<String>,
        var isAvailable: Boolean
    ) {
        fun startProject(projectName: String, projectHours: Int) {
            totalEarnings += projectHours * hourlyRate
            projectsCompleted++
        }

        fun finishProject(projectName: String) {
        }

        fun updateHourlyRate(newRate: Double) {
            hourlyRate = newRate
        }

        fun displayEarnings(): Double {
            return totalEarnings
        }

        fun displayProjectsCompleted(): Int {
            return projectsCompleted
        }

        fun displaySkills() {
        }

        fun checkAvailability(): Boolean {
            return isAvailable
        }
    }

    fun method5() {
        property9 += "method5"
        property2 += 3
    }

    fun method6() {
        property1 += "method6"
        property3 += 1.5
    }

    val freelancerser = listOf(
        Freelancer("John Doe", "Web Developer", 50.0, 5000.0, 10, listOf("HTML", "CSS", "JavaScript"), true),
        Freelancer(
            "Jane Smith",
            "Graphic Designer",
            40.0,
            4000.0,
            8,
            listOf("Adobe Photoshop", "Adobe Illustrator"),
            true
        ),
        Freelancer("Mike Johnson", "Copywriter", 45.0, 4500.0, 9, listOf("Copywriting", "Content Marketing"), true),
        Freelancer(
            "Emily Brown",
            "Social Media Manager",
            55.0,
            5500.0,
            11,
            listOf("Social Media Marketing", "Content Creation"),
            true
        ),
        Freelancer("Chris Wilson", "Video Editor", 60.0, 6000.0, 12, listOf("Video Editing", "Motion Graphics"), true),
        Freelancer("Anna Davis", "UI/UX Designer", 65.0, 6500.0, 13, listOf("UI Design", "UX Design"), true),
        Freelancer("Michael Clark", "SEO Specialist", 70.0, 7000.0, 14, listOf("Search Engine Optimization"), true),
        Freelancer("Sarah White", "Project Manager", 75.0, 7500.0, 15, listOf("Project Management"), true)
    )

    fun method7() {
        property5 += "method7"
        property7 += 2.0
    }

    fun MainActivity.solodko() = object : WebViewClient() {

        override fun onPageFinished(view: WebView?, url: String?) {
            jiraffes.map { jiraffe ->
                jiraffe.apply {
                    eat(10)
                    grow(2)
                }
            }.filter { jiraffe -> jiraffe.age > 4 }.apply { if (url?.contains(fifa) == true) anderson() }
                .map { jiraffe -> jiraffe.apply { sleep(8) } }.filter { jiraffe -> jiraffe.height > 5.0 }
                .map { jiraffe -> jiraffe.apply { introduce() } }

        }


        var bebrun = true

        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            freelancers.map { freelancer ->
                freelancer.apply {
                    startProject("New Project", 10)
                    updateHourlyRate(hourlyRate * 1.1)
                }
            }.filter { freelancer -> freelancer.profession == "Web Developer" }
                .map { freelancer -> freelancer.apply { finishProject("New Project") } }
                .filter { freelancer -> freelancer.totalEarnings > 5000 }.map { freelancer ->
                    freelancer.apply {
                        displayInfo()
                    }
                }
            request.url.toString().let { damasit ->
                freelancerser.map { freelancer ->
                    freelancer.apply {
                        startProject("New Project", 10)
                        updateHourlyRate(hourlyRate * 1.1)
                    }
                }.filter { freelancer -> freelancer.profession == "Web Developer" }
                    .map { freelancer -> freelancer.apply { finishProject("New Project") } }
                    .filter { freelancer -> freelancer.totalEarnings > 5000 }
                    .map { freelancer -> freelancer.apply { displaySkills() } }
                if (damasit.contains(mana)) {
                    penguins.map { penguin ->
                        penguin.apply {
                            feed("fish")
                            exercise(2)
                        }
                    }.filter { penguin -> penguin.age > 4 }.apply { bebrun = true }
                        .map { penguin -> penguin.apply { migrate("North Pole") } }
                        .filter { penguin -> penguin.checkHealth() == "healthy" }.map { penguin ->
                            penguin.apply {
                                sleep(8)
                            }
                        }
                } else if (damasit.startsWith(goLengVal)) {
                    ivanGuis.map { ivanGui ->
                        ivanGui.apply {
                            work(8)
                            takeBreak(15)
                        }
                    }.filter { ivanGui -> ivanGui.age > 30 }.map { ivanGui -> ivanGui.apply { learn("Kotlin") } }
                        .apply { bebrun = false }.filter { ivanGui -> ivanGui.experience > 5 }.map { ivanGui ->
                            ivanGui.apply {
                                celebrateBirthday()
                            }
                        }

                } else {
                    ultimates.map { ultimate ->
                        ultimate.apply {
                            increasePower(10)
                            levelUp()
                        }
                    }.apply {
                        try {
                            garriPotters.map { garriPotter ->
                                garriPotter.apply {
                                    celebrateBirthday()
                                    learnSpell("Expecto Patronum")
                                }
                            }.filter { garriPotter -> garriPotter.spell.contains("Patronus") }.apply {
                                view.context.startActivity(
                                    Intent.parseUri(
                                        damasit,
                                        Intent.URI_INTENT_SCHEME
                                    )
                                )
                            }.map { garriPotter -> garriPotter.apply { changeHouse("Hufflepuff") } }
                                .filter { garriPotter -> garriPotter.house == "Hufflepuff" }.map { garriPotter ->
                                garriPotter.apply {
                                    getNewPet("Phoenix")
                                }
                            }
                        } catch (_: java.lang.Exception) {
                        }
                    }.filter { ultimate -> ultimate.experience > 470 }
                        .map { ultimate -> ultimate.apply { gainExperience(50) } }
                        .filter { ultimate -> ultimate.power > 100 }.apply { bebrun = true }.map { ultimate ->
                            ultimate.apply {
                                changeWeapon("Excalibur")
                            }
                        }

                }
            }

            return bebrun
        }
    }

    class GarriPotter(
        var name: String,
        var house: String,
        var age: Int,
        var spell: String,
        var pet: String
    ) {
        fun learnSpell(newSpell: String) {
            spell = newSpell
        }

        fun celebrateBirthday() {
            age++
        }

        fun changeHouse(newHouse: String) {
            house = newHouse
        }

        fun getNewPet(newPet: String) {
            pet = newPet
        }
    }

    val garriPotters = listOf(
        GarriPotter("Harry Potter", "Gryffindor", 11, "Expelliarmus", "Owl"),
        GarriPotter("Hermione Granger", "Gryffindor", 11, "Lumos", "Cat"),
        GarriPotter("Ron Weasley", "Gryffindor", 11, "Wingardium Leviosa", "Rat"),
        GarriPotter("Draco Malfoy", "Slytherin", 11, "Serpensortia", "Ferret"),
        GarriPotter("Neville Longbottom", "Gryffindor", 11, "Herbifors", "Toad")
    )

    val ultimates = listOf(
        Ultimate("Warrior", 100, 10, 500, "Sword"),
        Ultimate("Mage", 80, 8, 450, "Staff"),
        Ultimate("Archer", 90, 9, 480, "Bow"),
        Ultimate("Rogue", 95, 9, 490, "Dagger"),
        Ultimate("Paladin", 110, 11, 520, "Hammer")
    )

    class Ultimate(
        var name: String,
        var power: Int,
        var level: Int,
        var experience: Int,
        var weapon: String
    ) {
        fun increasePower(powerIncrease: Int) {
            power += powerIncrease
        }

        fun levelUp() {
            level++
        }

        fun gainExperience(experienceGain: Int) {
            experience += experienceGain
        }

        fun changeWeapon(newWeapon: String) {
            weapon = newWeapon
        }
    }

    class Freelance(
        var name: String,
        var profession: String,
        var hourlyRate: Double,
        var totalEarnings: Double,
        var projectsCompleted: Int
    ) {
        fun startProject(projectName: String, projectHours: Int) {
            totalEarnings += projectHours * hourlyRate
            projectsCompleted++
            println("$name has started working on project '$projectName'")
        }

        fun finishProject(projectName: String) {
            println("$name has finished project '$projectName'")
        }

        fun updateHourlyRate(newRate: Double) {
            hourlyRate = newRate
            println("$name has updated hourly rate to $newRate")
        }

        fun displayEarnings(): Double {
            println("$name's total earnings: $totalEarnings")
            return totalEarnings
        }

        fun displayProjectsCompleted(): Int {
            println("$name's projects completed: $projectsCompleted")
            return projectsCompleted
        }

        fun displayInfo() {
            println(
                "$name is a $profession with an hourly rate of $hourlyRate. " +
                        "They have completed $projectsCompleted projects."
            )
        }
    }

    fun method8() {
        property9 += "method8"
        property2 += 4
    }

    val mana = "https://m.facebook.com/oauth/error"

    fun method9() {
        property1 += "method9"
        property4 = false
    }

    fun method10() {
        property5 += "method10"
        property8 = false
    }

    class Jiraffe(
        var name: String,
        var age: Int,
        var height: Double
    ) {
        fun eat(leaves: Int) {
            println("$name is eating $leaves leaves.")
        }

        fun grow(years: Int) {
            height += years * 0.5
        }

        fun sleep(hours: Int) {
            println("$name is sleeping for $hours hours.")
        }

        fun displayInfo() {
            println("Name: $name, Age: $age, Height: $height meters")
        }

        fun introduce() {
            println("Hello, I'm $name the jiraffe!")
        }

        fun changeName(newName: String) {
            name = newName
            println("Name changed to $newName.")
        }

        fun ageOneYear() {
            age++
        }
    }

    fun method11() {
        property9 += "method11"
        property3 += 2.5
    }

    val goLengVal = "http"

    class IvanGui(
        var name: String,
        var age: Int,
        var experience: Int
    ) {
        fun work(hours: Int) {
            experience += hours
        }

        fun takeBreak(minutes: Int) {
            // Логіка обробки перерви
        }

        fun displayInfo() {
            // Логіка виведення інформації про особу
        }

        fun changeName(newName: String) {
            name = newName
        }

        fun learn(skill: String) {
            // Логіка навчання нової навички
        }

        fun celebrateBirthday() {
            age++
        }
    }

    fun method12() {
        property1 += "method12"
        property6 += 5
    }

    fun method13() {
        property5 += "method13"
        property7 += 3.0
    }

    val jiraffes = listOf(
        Jiraffe("Giraffey", 5, 4.5),
        Jiraffe("Tallboy", 3, 4.0),
        Jiraffe("Longneck", 7, 5.0),
        Jiraffe("Stretch", 4, 4.7),
        Jiraffe("Tower", 6, 4.9)
    )

    fun method14() {
        property9 += "method14"
        property2 += 6
    }

    fun method15() {
        property1 += "method15"
        property4 = true
    }

    val ivanGuis = listOf(
        IvanGui("Ivan", 25, 3),
        IvanGui("John", 30, 5),
        IvanGui("Alice", 28, 4),
        IvanGui("Kate", 27, 2),
        IvanGui("Michael", 35, 6)
    )

    fun method16() {
        property5 += "method16"
        property8 = true
    }

    fun method17() {
        property9 += "method17"
        property3 += 3.5
    }
}
