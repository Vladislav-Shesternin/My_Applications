package com.veldan.boost.and.clean.simpleapp.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.ScreenUtils
import com.veldan.boost.and.clean.simpleapp.MainActivity
import com.veldan.boost.and.clean.simpleapp.game.manager.NavigationManager
import com.veldan.boost.and.clean.simpleapp.game.screens.SplashScreen
import com.veldan.boost.and.clean.simpleapp.game.utils.GameColor
import com.veldan.boost.and.clean.simpleapp.game.utils.advanced.AdvancedGame

lateinit var game: LibGDXGame private set

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {


    lateinit var assetManager: AssetManager private set



    override fun create() {
        game         = this
        assetManager = AssetManager()



        NavigationManager.navigate(SplashScreen())
    }

    override fun render() {
        ScreenUtils.clear(Color.WHITE)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}