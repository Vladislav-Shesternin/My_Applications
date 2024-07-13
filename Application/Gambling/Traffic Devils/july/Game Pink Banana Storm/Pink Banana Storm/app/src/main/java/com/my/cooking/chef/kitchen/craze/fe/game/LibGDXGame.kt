package com.my.cooking.chef.kitchen.craze.fe.game

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.my.cooking.chef.kitchen.craze.fe.GameActivity
import com.my.cooking.chef.kitchen.craze.fe.game.manager.*
import com.my.cooking.chef.kitchen.craze.fe.game.manager.util.MusicUtil
import com.my.cooking.chef.kitchen.craze.fe.game.manager.util.SoundUtil
import com.my.cooking.chef.kitchen.craze.fe.game.manager.util.SpriteUtil
import com.my.cooking.chef.kitchen.craze.fe.game.screens.LoadingScreen
import com.my.cooking.chef.kitchen.craze.fe.game.utils.GColor
import com.my.cooking.chef.kitchen.craze.fe.game.utils.advanced.AdvancedGame
import com.my.cooking.chef.kitchen.craze.fe.game.utils.disposeAll
import com.my.cooking.chef.kitchen.craze.fe.util.log
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils

class LibGDXGame(val activity: GameActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set

    val loal  by lazy { SpriteUtil.yyhAAGS() }
    val allOl by lazy { SpriteUtil.AoplKA() }

    val musicUtil by lazy { MusicUtil()    }
    val soundUtil by lazy { SoundUtil()    }

    var backgroundColor = GColor.bckg
    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)

        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)

        navigationManager.navigate(LoadingScreen::class.java.name)
    }

    val prefsDialog: SharedPreferences = activity.getSharedPreferences("AStoN", MODE_PRIVATE)

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