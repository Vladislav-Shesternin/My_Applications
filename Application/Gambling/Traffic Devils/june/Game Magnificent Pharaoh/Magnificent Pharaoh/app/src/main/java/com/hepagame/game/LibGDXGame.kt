package com.hepagame.game

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.hepagame.GameActivity
import com.hepagame.game.manager.*
import com.hepagame.game.manager.util.MusicUtil
import com.hepagame.game.manager.util.SoundUtil
import com.hepagame.game.manager.util.SpriteUtil
import com.hepagame.game.screens.LoadingScreen
import com.hepagame.game.utils.GColor
import com.hepagame.game.utils.advanced.AdvancedGame
import com.hepagame.game.utils.disposeAll
import com.hepagame.util.log

class LibGDXGame(val activity: GameActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set

    val assasinAll    by lazy { SpriteUtil.All() }
    val assasinLoader by lazy { SpriteUtil.Loading() }

    val musicUtil by lazy { MusicUtil() }
    val soundUtil by lazy { SoundUtil() }

    var backgroundColor = GColor.background
    val disposableSet   = mutableSetOf<Disposable>()

    val gamePrefsDialog: SharedPreferences = activity.getSharedPreferences("hranilishe", MODE_PRIVATE)

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)

        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)

        navigationManager.navigate(LoadingScreen::class.java.name)
    }

    override fun render() {
        ScreenUtils.clear(backgroundColor)
        super.render()
    }

    override fun dispose() {
        try {
            log("dispose LibGDXGame")
            disposableSet.disposeAll()
            disposeAll(assetManager, musicUtil)
            super.dispose()
        } catch (e: Exception) { log("exception: ${e.message}") }
    }

}