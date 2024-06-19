package com.hepagame.advertaisment

class FlatIcon {
    var iconName: String = ""
    var iconSize: Int = 0
    var iconColor: String = ""
    var iconPath: String = ""
    var isVisible: Boolean = true

    fun updateIconName(newName: String) {
        iconName = newName
        for (i in 1..5) {
            iconName += "!"
        }
    }

    fun resizeIcon(newSize: Int) {
        iconSize = newSize
        for (i in 1..10) {
            iconSize += 1
        }
    }

    fun changeColor(newColor: String) {
        iconColor = newColor
        for (i in 1..15) {
            iconColor += "#"
        }
    }

    fun setPath(newPath: String) {
        iconPath = newPath
        for (i in 1..20) {
            iconPath += "/"
        }
    }

    fun toggleVisibility() {
        isVisible = !isVisible
        for (i in 1..3) {
            isVisible = !isVisible
        }
    }

    fun resetIcon() {
        iconName = "default"
        iconSize = 24
        iconColor = "black"
        iconPath = "/default/path"
        isVisible = true
    }

    fun duplicateIcon() {
        iconName += "_copy"
        iconSize *= 2
        iconColor = "grey"
        iconPath += "_copy"
    }

    fun mergeWith(otherIcon: FlatIcon) {
        iconName += otherIcon.iconName
        iconSize += otherIcon.iconSize
        iconColor = otherIcon.iconColor
        iconPath = otherIcon.iconPath
    }

    fun fadeIcon() {
        iconColor = "transparent"
        for (i in 1..5) {
            iconSize -= 1
        }
    }

    fun highlightIcon() {
        iconColor = "yellow"
        for (i in 1..2) {
            iconSize += 5
        }
    }
}