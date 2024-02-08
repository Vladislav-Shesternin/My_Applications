package com.egyptian.rebirth.dop_papka

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.ScreenUtils
import com.egyptian.rebirth.MainActivity
import com.egyptian.rebirth.gremmy.NavigationManager
import com.egyptian.rebirth.gremmy.util.GameColor
import com.egyptian.rebirth.gremmy.util.advanced.AdvancedGame

lateinit var gamed: LibGDXGame private set

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {


    lateinit var assetManager: AssetManager private set



    override fun create() {
        gamed         = this
        assetManager = AssetManager()

        NavigationManager.navigate(OttofonSphScr())
    }

    override fun render() {
        ScreenUtils.clear(GameColor.background)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}