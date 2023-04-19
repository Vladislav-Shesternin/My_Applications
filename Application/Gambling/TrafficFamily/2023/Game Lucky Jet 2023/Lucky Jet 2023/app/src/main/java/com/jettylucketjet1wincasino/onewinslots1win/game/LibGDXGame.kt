package com.jettylucketjet1wincasino.onewinslots1win.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.jettylucketjet1wincasino.onewinslots1win.MainActivity
import com.jettylucketjet1wincasino.onewinslots1win.game.manager.NavigationManager
import com.jettylucketjet1wincasino.onewinslots1win.game.screens.SplashScreen
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.GameColor
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.advanced.AdvancedGame

lateinit var game: LibGDXGame private set

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {


    lateinit var assetManager: AssetManager private set



    override fun create() {
        game         = this
        assetManager = AssetManager()



        NavigationManager.navigate(SplashScreen())
    }

    override fun render() {
        ScreenUtils.clear(Color.BLACK)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}