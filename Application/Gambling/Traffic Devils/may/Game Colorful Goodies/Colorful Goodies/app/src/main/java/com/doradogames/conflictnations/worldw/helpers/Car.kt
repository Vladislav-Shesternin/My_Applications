package com.doradogames.conflictnations.worldw.helpers

import android.net.Uri

class Car(
    val brand: String,
    val model: String,
    val year: Int,
    val color: String,
    val fuelType: String,
    val mileage: Double,
    val price: Double
)

val sendvich = Uri.parse("market://details?id=jp.naver.line.android")