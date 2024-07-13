package com.centurygames.idlecouri.game

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.centurygames.idlecouri.GameActivity
import com.centurygames.idlecouri.game.manager.*
import com.centurygames.idlecouri.game.manager.util.MusicUtil
import com.centurygames.idlecouri.game.manager.util.SoundUtil
import com.centurygames.idlecouri.game.manager.util.SpriteUtil
import com.centurygames.idlecouri.game.screens.LoadingScreen
import com.centurygames.idlecouri.game.utils.GColor
import com.centurygames.idlecouri.game.utils.advanced.AdvancedGame
import com.centurygames.idlecouri.game.utils.disposeAll
import com.centurygames.idlecouri.util.log

class LibGDXGame(val activity: GameActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set

    val lobster by lazy { SpriteUtil.Lobster() }
    val alpha   by lazy { SpriteUtil.Alpha()   }

    val musicUtil by lazy { MusicUtil() }
    val soundUtil by lazy { SoundUtil() }

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

    val prefsDialog: SharedPreferences = activity.getSharedPreferences("POPaKAkKim", MODE_PRIVATE)

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