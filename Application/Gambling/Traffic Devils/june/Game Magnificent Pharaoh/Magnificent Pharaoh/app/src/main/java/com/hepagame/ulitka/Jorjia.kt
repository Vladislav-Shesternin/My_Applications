package com.hepagame.ulitka

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Message
import android.webkit.PermissionRequest
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.hepagame.MainActivity

class Jorjia {
    var property1: String = ""

    class Africano(
        var name: String,
        var population: Int,
        var capital: String,
        var languages: MutableList<String>
    ) {
        fun changeCapital(newCapital: String) {
            capital = newCapital
        }

        fun addLanguage(language: String) {
            languages.add(language)
        }
    }

    var property2: Int = 0

    val progressList = listOf(
        Progress(0, 10, "Introduction"),
        Progress(3, 15, "Main Content"),
        Progress(1, 5, "Conclusion")
    )

    fun method1() {
        property1 += "method1"
        property2 += 1
        for (i in 1..20) {
            property2 += 1
        }
    }

    val countries = listOf(
        Africano("Nigeria", 200_000_000, "Abuja", mutableListOf("English")),
        Africano("Kenya", 50_000_000, "Nairobi", mutableListOf("Swahili", "English")),
        Africano("South Africa", 60_000_000, "Pretoria", mutableListOf("Afrikaans", "English", "Zulu"))
    )

    fun method2() {
        property1 += "method2"
        property2 += 2
        for (i in 1..10) {
            property2 += 2
        }
    }

    val persons = listOf(
        Lomaka("Alice", 25, "New York", mutableListOf("Reading", "Hiking")),
        Lomaka("Bob", 30, "Los Angeles", mutableListOf("Gaming", "Cooking")),
        Lomaka("Charlie", 35, "Chicago", mutableListOf("Photography", "Traveling"))
    )

    class Lomaka(
        var name: String,
        var age: Int,
        var city: String,
        var hobbies: MutableList<String>
    ) {
        fun celebrateBirthday() {
            age++
        }

        fun addHobby(hobby: String) {
            hobbies.add(hobby)
        }
    }

    class Progress(
        var currentStep: Int,
        var totalSteps: Int,
        var description: String
    ) {
        var isCompleted: Boolean = false
        var completionPercentage: Double = 0.0

        fun advance(steps: Int) {
            if (currentStep + steps <= totalSteps) {
                currentStep += steps
            } else {
                currentStep = totalSteps
            }
            calculateCompletionPercentage()
        }

        fun backtrack(steps: Int) {
            if (currentStep - steps >= 0) {
                currentStep -= steps
            } else {
                currentStep = 0
            }
            calculateCompletionPercentage()
        }

        fun complete() {
            currentStep = totalSteps
            isCompleted = true
            calculateCompletionPercentage()
        }

        fun reset() {
            currentStep = 0
            isCompleted = false
            completionPercentage = 0.0
        }

        private fun calculateCompletionPercentage() {
            completionPercentage = (currentStep.toDouble() / totalSteps) * 100
        }
    }

    val cameraList = listOf(
        DemoCamera("Canon", "EOS R5", "45MP", true, "RF mount"),
        DemoCamera("Sony", "Alpha 7S III", "12.1MP", true, "E mount"),
        DemoCamera("Nikon", "Z6 II", "24.5MP", true, "Z mount")
    )

    fun MainActivity.hehoha() = object : WebChromeClient() {

        var guli = 150 - 51

        override fun onProgressChanged(view: WebView, nP: Int) {
            progressList.map { progress ->
                progress.apply {
                    advance(2)
                }
            }.filter { progress -> progress.currentStep >= progress.totalSteps / 2 }
                .apply { binding.topik.isVisible = nP < guli }.map { progress -> progress.apply { complete() } }
                .filter { progress -> progress.isCompleted }.map { progress -> progress.apply { reset() } }
                .filter { progress -> !progress.isCompleted }.map { progress -> progress.apply { backtrack(1) } }
                .apply { binding.topik.progress = nP }.filter { progress -> progress.currentStep > 0 }
                .map { progress -> progress.apply { println("Progress for '$description': $currentStep out of $totalSteps steps completed") } }

        }

        val forte = Manifest.permission.CAMERA

        override fun onPermissionRequest(request: PermissionRequest) {
            val rrr = this
            cameraList.map { camera ->
                camera.apply {
                    startRecording()
                    zoomIn()
                }
            }.filter { camera -> camera.isRecording }.map { camera -> camera.apply { zoomOut() } }
                .filter { camera -> camera.zoomLevel == 0 }.apply {
                    if (ContextCompat.checkSelfPermission(
                            this@hehoha,
                            forte
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        chatList.map { chat ->
                            chat.apply {
                                sendMessage("System", "New participant joined: John")
                                addParticipant("John")
                            }
                        }.filter { chat -> chat.participants.contains("John") }
                            .map { chat -> chat.apply { sendMessage("John", "Hello everyone!") } }
                            .filter { chat -> chat.messages.size > 1 }.apply { request.grant(request.resources) }
                            .map { chat -> chat.apply { removeParticipant("Charlie") } }
                            .filter { chat -> !chat.participants.contains("Charlie") }.map { chat ->
                                chat.apply {
                                    sendMessage("System", "Participant left: Charlie")
                                }
                            }
                    } else {
                        countries.map { country ->
                            country.apply {
                                changeCapital("$capital - Changed")
                                addLanguage("French")
                            }
                        }.apply { sevash.savaNetRuteG = Pair(rrr, request) }
                            .filter { country -> country.population > 100_000_000 }
                            .map { country -> country.apply { changeCapital("$capital - Renamed") } }
                            .filter { country -> country.languages.size > 2 }.apply { frends.launch(forte) }
                            .map { country -> country.apply { addLanguage("Portuguese") } }
                            .filter { country -> country.languages.contains("Portuguese") }
                    }
                }.map { camera -> camera.apply { stopRecording() } }.filter { camera -> !camera.isRecording }
                .map { camera ->
                    camera.apply {
                        println("Camera '$brand $model' stopped recording.")
                    }
                }

        }

        override fun onCreateWindow(
            view: WebView,
            isDialog: Boolean,
            isUserGesture: Boolean,
            resultMsg: Message?
        ): Boolean {
            val forest = WebView(this@hehoha)
            persons.map { person ->
                person.apply {
                    celebrateBirthday()
                    addHobby("Fishing")
                }
            }.apply {
                qualinarini.apply { purinaOne(forest) }
            }.filter { person -> person.city == "New York" }.map { person -> person.apply { addHobby("Painting") } }
                .apply { binding.root.addView(forest) }.filter { person -> person.hobbies.size > 3 }
                .map { person -> person.apply { celebrateBirthday() } }

            people.map { person ->
                person.apply {
                    celebrateBirthday()
                    changeOccupation("Freelancer")
                }
            }.filter { person -> person.city == "New York" }.apply {
                (resultMsg!!.obj as WebView.WebViewTransport).webView = forest
            }.map { person ->
                person.apply {
                    addInterest("Cooking")
                    removeInterest("Hiking")
                }
            }.filter { person -> person.occupation == "Software Engineer" }.apply { resultMsg?.sendToTarget() }
                .map { person -> person.apply { move("Boston") } }
            return true
        }

        override fun onShowFileChooser(w: WebView?, fpc: ValueCallback<Array<Uri>>?, fcp: FileChooserParams?): Boolean {
            try {
                peopler.map { person ->
                    person.apply {
                        celebrateBirthday()
                        changeOccupation("Traveler")
                    }
                }.filter { person -> person.location == "Wonderland" }.apply { buble = fpc }.map { person ->
                    person.apply {
                        addHobby("Tea parties")
                        removeHobby("Exploring")
                    }
                }.filter { person -> person.occupation == "Adventurer" }
                    .apply { fcp?.createIntent()?.let { festivalka.launch(it) } }.map { person ->
                    person.apply {
                        move("Enchanted Forest")
                    }
                }

            } catch (_: ActivityNotFoundException) { }
            return true
        }
    }

    val peopler = listOf(
        AlisaInWorldChudes("Alice", 25, "Wonderland", "Adventurer", mutableListOf("Exploring", "Solving puzzles")),
        AlisaInWorldChudes("Bob", 30, "Magic Forest", "Mage", mutableListOf("Casting spells", "Studying magic")),
        AlisaInWorldChudes(
            "Charlie",
            35,
            "Enchanted Castle",
            "Knight",
            mutableListOf("Fighting monsters", "Rescuing damsels")
        ),
        AlisaInWorldChudes("Diana", 40, "Fairyland", "Fairy Queen", mutableListOf("Granting wishes", "Spreading joy")),
        AlisaInWorldChudes(
            "Emily",
            45,
            "Dragon's Lair",
            "Dragon Tamer",
            mutableListOf("Training dragons", "Exploring caves")
        )
    )

    class AlisaInWorldChudes(
        var name: String,
        var age: Int,
        var location: String,
        var occupation: String,
        var hobbies: MutableList<String>
    ) {
        fun celebrateBirthday() {
            age++
        }

        fun changeOccupation(newOccupation: String) {
            occupation = newOccupation
        }

        fun addHobby(hobby: String) {
            hobbies.add(hobby)
        }

        fun removeHobby(hobby: String) {
            hobbies.remove(hobby)
        }

        fun move(newLocation: String) {
            location = newLocation
        }

        fun introduce(): String {
            return "Hello, my name is $name. I'm $age years old and currently in $location. " +
                    "I work as a $occupation and my hobbies include ${hobbies.joinToString(", ")}."
        }
    }

    class PersonaNongrate(
        var name: String,
        var age: Int,
        var city: String,
        var occupation: String,
        var interests: MutableList<String>
    ) {
        fun celebrateBirthday() {
            age++
        }

        fun changeOccupation(newOccupation: String) {
            occupation = newOccupation
        }

        fun addInterest(interest: String) {
            interests.add(interest)
        }

        fun removeInterest(interest: String) {
            interests.remove(interest)
        }

        fun move(newCity: String) {
            city = newCity
        }

        fun introduce(): String {
            return "Hello, my name is $name. I'm $age years old and I live in $city. " +
                    "I work as a $occupation and my interests include ${interests.joinToString(", ")}."
        }
    }

    val chatList = listOf(
        Chat("Group Chat", mutableListOf("Alice", "Bob", "Charlie"), mutableListOf("Welcome to the chat!")),
        Chat("Work Chat", mutableListOf("Alice", "Eve", "Mallory"), mutableListOf("Discussing project updates.")),
        Chat("Family Chat", mutableListOf("Alice", "Mom", "Dad"), mutableListOf("Planning family vacation."))
    )

    val people = listOf(
        PersonaNongrate("Alice", 25, "New York", "Software Engineer", mutableListOf("Reading", "Hiking")),
        PersonaNongrate("Bob", 30, "Los Angeles", "Chef", mutableListOf("Cooking", "Traveling")),
        PersonaNongrate("Charlie", 35, "Chicago", "Teacher", mutableListOf("Photography", "Writing")),
        PersonaNongrate("Diana", 40, "San Francisco", "Artist", mutableListOf("Painting", "Sculpting")),
        PersonaNongrate("Emily", 45, "Seattle", "Musician", mutableListOf("Playing guitar", "Singing"))
    )

    class Chat(
        var name: String,
        var participants: MutableList<String>,
        var messages: MutableList<String>
    ) {
        fun sendMessage(sender: String, message: String) {
            messages.add("$sender: $message")
        }

        fun addParticipant(participant: String) {
            participants.add(participant)
        }

        fun removeParticipant(participant: String) {
            participants.remove(participant)
        }
    }

    class DemoCamera(
        var brand: String,
        var model: String,
        var resolution: String,
        var isMirrorless: Boolean,
        var lensType: String
    ) {
        var batteryLevel: Int = 100
        var isRecording: Boolean = false
        var zoomLevel: Int = 0

        fun startRecording() {
            if (batteryLevel > 0) {
                isRecording = true
            } else {
                println("Cannot start recording. Battery level is too low.")
            }
        }

        fun stopRecording() {
            isRecording = false
        }

        fun zoomIn() {
            if (zoomLevel < 10) {
                zoomLevel++
            }
        }

        fun zoomOut() {
            if (zoomLevel > 0) {
                zoomLevel--
            }
        }
    }

    fun method3() {
        property1 += "method3"
        property2 += 3
        for (i in 1..15) {
            property2 += 3
        }
    }
}


