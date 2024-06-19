package com.hepagame.advertaisment

import com.android.installreferrer.api.InstallReferrerClient

class Hejami {
    private val flatIcon = FlatIcon()

    fun useFlatIcon() {
        flatIcon.iconName = "Icon1"
        flatIcon.iconSize = 48
        flatIcon.iconColor = "red"
        flatIcon.iconPath = "/icons/icon1"
        flatIcon.isVisible = true

        flatIcon.updateIconName("UpdatedIcon")
        flatIcon.resizeIcon(64)
        flatIcon.changeColor("blue")
        flatIcon.setPath("/icons/updatedicon")
        flatIcon.toggleVisibility()
        flatIcon.resetIcon()
        flatIcon.duplicateIcon()
        flatIcon.mergeWith(FlatIcon().apply { iconName = "MergedIcon"; iconSize = 32; iconColor = "green"; iconPath = "/icons/merged" })
        flatIcon.fadeIcon()
        flatIcon.highlightIcon()
    }

    lateinit var rasmusic: InstallReferrerClient

}