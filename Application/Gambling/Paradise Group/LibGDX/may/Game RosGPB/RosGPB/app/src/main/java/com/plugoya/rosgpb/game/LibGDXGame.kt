package com.plugoya.rosgpb.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.ScreenUtils
import com.plugoya.rosgpb.MainActivity
import com.plugoya.rosgpb.game.manager.NavigationManager
import com.plugoya.rosgpb.game.screens.SplashScreen
import com.plugoya.rosgpb.game.utils.GameColor
import com.plugoya.rosgpb.game.utils.advanced.AdvancedGame
import com.plugoya.rosgpb.game.utils.asset.MusicUtil
import com.plugoya.rosgpb.game.utils.asset.SoundUtil

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