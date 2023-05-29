package com.polovinka.gurieva.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.polovinka.gurieva.MainActivity
import com.polovinka.gurieva.game.manager.NavigationManager
import com.polovinka.gurieva.game.screens.SplashScreen
import com.polovinka.gurieva.game.utils.GameColor
import com.polovinka.gurieva.game.utils.advanced.AdvancedGame
import com.polovinka.gurieva.game.utils.asset.MusicUtil
import com.polovinka.gurieva.game.utils.asset.SoundUtil

lateinit var game: LibGDXGame private set
lateinit var musicUtil: MusicUtil private set
lateinit var soundUtil: SoundUtil private set
class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set

    override fun create() {
        game         = this
        assetManager = AssetManager()
        musicUtil    = MusicUtil()
        soundUtil    = SoundUtil()

        NavigationManager.navigate(SplashScreen())
    }

    override fun render() {
        ScreenUtils.clear(GameColor.purpil)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
        musicUtil.dispose()
    }

}