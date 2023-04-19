package com.veldan.pharaohslots

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.veldan.pharaohslots.advanced.AdvancedGame
import com.veldan.pharaohslots.assets.util.MusicUtil
import com.veldan.pharaohslots.manager.NavigationManager
import com.veldan.pharaohslots.screens.SplashScreen
import com.veldan.pharaohslots.utils.language.LanguageSprite

lateinit var game: LibGDXGame private set
lateinit var assetManager: AssetManager private set
lateinit var languageSprite: LanguageSprite

class LibGDXGame : AdvancedGame() {

    private val color = Color.BLACK



    override fun create() {
        game = this
        assetManager = AssetManager()

        NavigationManager.navigate(SplashScreen())
    }

    override fun render() {
        ScreenUtils.clear(color)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
        MusicUtil.dispose()
    }

}