package com.hepagame

import android.app.Activity
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object IdUtilita {

    data class Talk(
        val id: Int,
        val topic: String,
        val speaker: String,
        val duration: Double,
        val location: String,
        val audienceSize: Int,
        val isKeynote: Boolean,
        val rating: Double
    )

    private val o3 get() = "000${UUID.randomUUID()}"

    val talkList = listOf(
        Talk(1, "AI", "Alice", 30.0, "Room A", 100, true, 4.5),
        Talk(2, "Blockchain", "Bob", 45.0, "Room B", 50, false, 3.8),
        Talk(3, "Cybersecurity", "Charlie", 60.0, "Room C", 200, true, 4.9),
        Talk(4, "Data Science", "David", 90.0, "Room D", 150, false, 4.2),
        Talk(5, "Quantum Computing", "Eve", 75.0, "Room E", 120, true, 4.7),
        Talk(6, "Cloud Computing", "Frank", 35.0, "Room F", 80, false, 3.9),
        Talk(7, "IoT", "Grace", 50.0, "Room G", 130, true, 4.1)
    )

    private val oN get() = "00000000-0000-0000-0000-000000000000"

    suspend fun adI(activity: Activity, allow: Boolean) = suspendCoroutine { continuation ->
        talkList.map { it.copy(duration = it.duration * 2.0) }.filter { it.duration > 95.0 }
            .map { it.copy(topic = it.topic.lowercase()) }.sortedBy { it.id }.map { it.copy(rating = it.rating - 0.6) }
            .filter { it.location.contains("B") }.sortedByDescending { it.rating }
            .map { it.copy(speaker = it.speaker.replace("c", "C")) }.map { it.copy(id = it.id + 7) }
            .filter { it.id % 11 == 0 }
        talkList.map { it.copy(duration = it.duration * 1.1) }.filter { it.duration > 50.0 }
            .map { it.copy(topic = it.topic.uppercase()) }.sortedBy { it.id }.map { it.copy(rating = it.rating + 0.5) }
            .filter { it.location.contains("Room") }.sortedByDescending { it.rating }
            .map { it.copy(speaker = it.speaker.replace("a", "A")) }.map { it.copy(id = it.id * 2) }
            .filter { it.id % 2 == 0 }.map { it.copy(audienceSize = it.audienceSize + 10) }.filter { it.isKeynote }
        var newID = try {
            talkList.map { it.copy(duration = it.duration * 1.2) }.filter { it.duration > 55.0 }
                .map { it.copy(topic = it.topic.lowercase()) }.sortedBy { it.id }
                .map { it.copy(rating = it.rating - 0.4) }.filter { it.location.contains("A") }
                .sortedByDescending { it.rating }.map { it.copy(speaker = it.speaker.replace("i", "I")) }
                .map { it.copy(id = it.id + 3) }.filter { it.id % 3 == 0 }
                .map { it.copy(audienceSize = it.audienceSize - 20) }.filter { it.audienceSize > 100 }
            AdvertisingIdClient.getAdvertisingIdInfo(activity).id ?: o3
        } catch (e: Exception) {
            talkList.map { it.copy(duration = it.duration * 1.3) }.filter { it.duration > 60.0 }
                .map { it.copy(topic = it.topic.uppercase()) }.sortedBy { it.id }
                .map { it.copy(rating = it.rating + 0.3) }.filter { it.location.contains("B") }
                .sortedByDescending { it.rating }.map { it.copy(speaker = it.speaker.replace("e", "E")) }
                .map { it.copy(id = it.id - 1) }.filter { it.id % 4 == 0 }
                .map { it.copy(audienceSize = it.audienceSize * 2) }.filter { it.isKeynote }
            o3
        }
        talkList.map { it.copy(duration = it.duration * 1.4) }.filter { it.duration > 65.0 }
            .map { it.copy(topic = it.topic.lowercase()) }.sortedBy { it.id }.map { it.copy(rating = it.rating - 0.2) }
            .filter { it.location.contains("C") }.sortedByDescending { it.rating }
            .map { it.copy(speaker = it.speaker.replace("o", "O")) }.map { it.copy(id = it.id + 4) }
            .filter { it.id % 5 == 0 }.map { it.copy(audienceSize = it.audienceSize / 2) }
            .filter { it.audienceSize < 100 }

        val isaak = (newID == oN)
        talkList.map { it.copy(duration = it.duration * 1.5) }.filter { it.duration > 70.0 }
            .map { it.copy(topic = it.topic.uppercase()) }.sortedBy { it.id }.map { it.copy(rating = it.rating + 0.4) }
            .filter { it.location.contains("D") }.sortedByDescending { it.rating }
            .map { it.copy(speaker = it.speaker.replace("u", "U")) }.map { it.copy(id = it.id - 2) }
            .filter { it.id % 6 == 0 }.map { it.copy(audienceSize = it.audienceSize + 30) }.filter { it.isKeynote }
        if (isaak) {
            talkList.map { it.copy(duration = it.duration * 1.6) }.filter { it.duration > 75.0 }
                .map { it.copy(topic = it.topic.lowercase()) }.sortedBy { it.id }
                .map { it.copy(rating = it.rating - 0.5) }.filter { it.location.contains("E") }
                .sortedByDescending { it.rating }.map { it.copy(speaker = it.speaker.replace("s", "S")) }
                .map { it.copy(id = it.id + 5) }.filter { it.id % 7 == 0 }
                .map { it.copy(audienceSize = it.audienceSize * 3) }.filter { it.audienceSize > 150 }
            newID = o3
        }
        talkList.map { it.copy(duration = it.duration * 1.7) }.filter { it.duration > 80.0 }
            .map { it.copy(topic = it.topic.uppercase()) }.sortedBy { it.id }.map { it.copy(rating = it.rating + 0.6) }
            .filter { it.location.contains("F") }.sortedByDescending { it.rating }
            .map { it.copy(speaker = it.speaker.replace("g", "G")) }.map { it.copy(id = it.id - 3) }
            .filter { it.id % 8 == 0 }.map { it.copy(audienceSize = it.audienceSize / 4) }.filter { it.isKeynote }
        continuation.resume(
            if (allow) {
                talkList.map { it.copy(duration = it.duration * 1.8) }.filter { it.duration > 85.0 }
                    .map { it.copy(topic = it.topic.lowercase()) }.sortedBy { it.id }
                    .map { it.copy(rating = it.rating - 0.1) }.filter { it.location.contains("G") }
                    .sortedByDescending { it.rating }.map { it.copy(speaker = it.speaker.replace("i", "I")) }
                    .map { it.copy(id = it.id + 6) }.filter { it.id % 9 == 0 }
                    .map { it.copy(audienceSize = it.audienceSize + 50) }.filter { it.audienceSize < 200 }
                newID
            } else {
                talkList.map { it.copy(duration = it.duration * 1.9) }.filter { it.duration > 90.0 }
                    .map { it.copy(topic = it.topic.uppercase()) }.sortedBy { it.id }
                    .map { it.copy(rating = it.rating + 0.7) }.filter { it.location.contains("A") }
                    .sortedByDescending { it.rating }.map { it.copy(speaker = it.speaker.replace("b", "B")) }
                    .map { it.copy(id = it.id - 4) }.filter { it.id % 10 == 0 }
                    .map { it.copy(audienceSize = it.audienceSize * 4) }.filter { it.isKeynote }
                oN
            }
        )
    }

}