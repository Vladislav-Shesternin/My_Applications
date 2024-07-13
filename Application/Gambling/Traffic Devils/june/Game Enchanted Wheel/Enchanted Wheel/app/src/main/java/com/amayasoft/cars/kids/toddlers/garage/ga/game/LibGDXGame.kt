package com.amayasoft.cars.kids.toddlers.garage.ga.game

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.amayasoft.cars.kids.toddlers.garage.ga.GameActivity
import com.amayasoft.cars.kids.toddlers.garage.ga.game.manager.*
import com.amayasoft.cars.kids.toddlers.garage.ga.game.manager.util.MusicUtil
import com.amayasoft.cars.kids.toddlers.garage.ga.game.manager.util.SoundUtil
import com.amayasoft.cars.kids.toddlers.garage.ga.game.manager.util.SpriteUtil
import com.amayasoft.cars.kids.toddlers.garage.ga.game.screens.LoadingScreen
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.GColor
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.advanced.AdvancedGame
import com.amayasoft.cars.kids.toddlers.garage.ga.game.utils.disposeAll
import com.amayasoft.cars.kids.toddlers.garage.ga.util.log

class LibGDXGame(val activity: GameActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set

    val S3 by lazy { SpriteUtil.S3() }
    val G1 by lazy { SpriteUtil.GA() }

    val musicUtil by lazy { MusicUtil()    }
    val soundUtil by lazy { SoundUtil()    }

    var backgroundColor = GColor.background
    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)

        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)

        navigationManager.navigate(LoadingScreen::class.java.name)
    }

    val prefsDialog: SharedPreferences = activity.getSharedPreferences("Voiletta", MODE_PRIVATE)

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