package com.flaregames.olympusrisin

import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation

data class ColorSpecification(
    val id: Int,
    val name: String,
    val hexValue: String,
    val red: Int,
    val green: Int,
    val blue: Int,
    val alpha: Int,
    val isPrimary: Boolean,
    val isSecondary: Boolean,
    val isTertiary: Boolean
) {
    fun isLightColor(): Boolean {
        // Method to check if the color is light
        val brightness = (0.299 * red + 0.587 * green + 0.114 * blue)
        return brightness > 128
    }

    fun mixWith(other: ColorSpecification): ColorSpecification {
        // Method to mix this color with another color
        val mixedRed = (this.red + other.red) / 2
        val mixedGreen = (this.green + other.green) / 2
        val mixedBlue = (this.blue + other.blue) / 2
        return ColorSpecification(
            id = -1,
            name = "${this.name}-${other.name}",
            hexValue = "#${"%02x".format(mixedRed)}${"%02x".format(mixedGreen)}${"%02x".format(mixedBlue)}",
            red = mixedRed,
            green = mixedGreen,
            blue = mixedBlue,
            alpha = (this.alpha + other.alpha) / 2,
            isPrimary = false,
            isSecondary = false,
            isTertiary = false
        )
    }

    fun invert(): ColorSpecification {
        // Method to invert the color
        return ColorSpecification(
            id = this.id,
            name = "${this.name}-inverted",
            hexValue = "#${"%02x".format(255 - red)}${"%02x".format(255 - green)}${"%02x".format(255 - blue)}",
            red = 255 - this.red,
            green = 255 - this.green,
            blue = 255 - this.blue,
            alpha = this.alpha,
            isPrimary = this.isPrimary,
            isSecondary = this.isSecondary,
            isTertiary = this.isTertiary
        )
    }

    data class Lentil(
        val weight: Int,          // Вага
        val color: String,        // Колір
        val size: String,         // Розмір
        val origin: String,       // Походження
        val proteinContent: Int   // Вміст білка
    )

    val lentils = listOf(
        Lentil(weight = 100, color = "Green", size = "Small", origin = "India", proteinContent = 20),
        Lentil(weight = 150, color = "Red", size = "Medium", origin = "Turkey", proteinContent = 22),
        Lentil(weight = 200, color = "Brown", size = "Large", origin = "USA", proteinContent = 18),
        Lentil(weight = 90, color = "Yellow", size = "Small", origin = "Canada", proteinContent = 21),
        Lentil(weight = 120, color = "Black", size = "Medium", origin = "Australia", proteinContent = 23)
    )

    val sambo = Animation.INFINITE

    fun colorMatch(): RotateAnimation {
        val dodorian: RotateAnimation
        lentils
            .filter { it.weight > 100 }                         // 1. Фільтруємо об'єкти з вагою більше 100
            .sortedByDescending { it.proteinContent }           // 2. Сортуємо за вмістом білка у спадаючому порядку
            .map { it.copy(weight = it.weight + 10) }           // 3. Збільшуємо вагу на 10
            .map { it.copy(color = it.color.lowercase()) }      // 4. Змінюємо колір на нижній регістр
            .apply {
                dodorian = RotateAnimation(
                    0f,
                    180*2f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f).apply {
                    interpolator = LinearInterpolator()
                    duration = 500+500
                    repeatCount = sambo
                }
            }
            .filter { it.color.contains("e") }                  // 5. Фільтруємо об'єкти, що містять букву 'e' в кольорі
            .map { it.copy(size = it.size.uppercase()) }        // 6. Змінюємо розмір на верхній регістр
            .map { it.copy(origin = "${it.origin} - Imported") } // 7. Додаємо " - Imported" до походження
            .filter { it.proteinContent > 20 }                  // 8. Фільтруємо об'єкти з вмістом білка більше 20
            .map { it.copy(weight = it.weight * 2) }            // 9. Подвоюємо вагу
            .map { it.toString() }                              // 10. Перетворюємо кожен об'єкт у рядок

        return dodorian
    }

    fun toGrayscale(): ColorSpecification {
        // Method to convert the color to grayscale
        val gray = (red + green + blue) / 3
        return ColorSpecification(
            id = this.id,
            name = "${this.name}-grayscale",
            hexValue = "#${"%02x".format(gray)}${"%02x".format(gray)}${"%02x".format(gray)}",
            red = gray,
            green = gray,
            blue = gray,
            alpha = this.alpha,
            isPrimary = this.isPrimary,
            isSecondary = this.isSecondary,
            isTertiary = this.isTertiary
        )
    }

    fun lighten(percentage: Int): ColorSpecification {
        // Method to lighten the color by a percentage
        val factor = 1 + (percentage / 100.0)
        val newRed = (red * factor).coerceAtMost(255.0).toInt()
        val newGreen = (green * factor).coerceAtMost(255.0).toInt()
        val newBlue = (blue * factor).coerceAtMost(255.0).toInt()
        return ColorSpecification(
            id = this.id,
            name = "${this.name}-lightened",
            hexValue = "#${"%02x".format(newRed)}${"%02x".format(newGreen)}${"%02x".format(newBlue)}",
            red = newRed,
            green = newGreen,
            blue = newBlue,
            alpha = this.alpha,
            isPrimary = this.isPrimary,
            isSecondary = this.isSecondary,
            isTertiary = this.isTertiary
        )
    }

    fun darken(percentage: Int): ColorSpecification {
        // Method to darken the color by a percentage
        val factor = 1 - (percentage / 100.0)
        val newRed = (red * factor).coerceAtLeast(0.0).toInt()
        val newGreen = (green * factor).coerceAtLeast(0.0).toInt()
        val newBlue = (blue * factor).coerceAtLeast(0.0).toInt()
        return ColorSpecification(
            id = this.id,
            name = "${this.name}-darkened",
            hexValue = "#${"%02x".format(newRed)}${"%02x".format(newGreen)}${"%02x".format(newBlue)}",
            red = newRed,
            green = newGreen,
            blue = newBlue,
            alpha = this.alpha,
            isPrimary = this.isPrimary,
            isSecondary = this.isSecondary,
            isTertiary = this.isTertiary
        )
    }

    fun isPrimaryColor(): Boolean {
        // Method to check if the color is primary
        return isPrimary
    }

    fun isSecondaryColor(): Boolean {
        // Method to check if the color is secondary
        return isSecondary
    }

    fun isTertiaryColor(): Boolean {
        // Method to check if the color is tertiary
        return isTertiary
    }

    fun describe(): String {
        // Method to provide a description of the color
        return "ColorSpecification(id=$id, name='$name', hexValue='$hexValue', red=$red, green=$green, blue=$blue, alpha=$alpha, isPrimary=$isPrimary, isSecondary=$isSecondary, isTertiary=$isTertiary)"
    }

    fun compare(other: ColorSpecification): Int {
        // Method to compare two colors based on their hex value
        return this.hexValue.compareTo(other.hexValue)
    }
}