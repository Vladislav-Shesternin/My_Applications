package com.invt.nard.app.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.invt.nard.app.game.screens.SplashScreen
import com.invt.nard.app.MainActivity
import com.invt.nard.app.game.manager.GameDataStoreManager
import com.invt.nard.app.game.manager.NavigationManager
import com.invt.nard.app.game.utils.GameColor
import com.invt.nard.app.game.utils.advanced.AdvancedGame
import com.invt.nard.app.game.utils.asset.MusicUtil
import com.invt.nard.app.util.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

lateinit var game     : LibGDXGame private set
lateinit var musicUtil: MusicUtil private set

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set

    private val coroutine = CoroutineScope(Dispatchers.Default)

    companion object {
        var recordFlow = MutableStateFlow(0)
            private set
    }


    override fun create() {
        game         = this
        assetManager = AssetManager()
        musicUtil    = MusicUtil()

        coroutine.launch(Dispatchers.IO) {
            GameDataStoreManager.Reco.collect {
                if (recordFlow.value == 0) recordFlow.value = it ?: 0
            }
        }
        coroutine.launch {
            recordFlow.collect { reco ->
                if (reco != 0) GameDataStoreManager.Reco.update { reco }
            }
        }

        NavigationManager.navigate(SplashScreen())
    }

    override fun render() {
        ScreenUtils.clear(Color.BLACK)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        cancelCoroutinesAll(coroutine)
        musicUtil.dispose()
        assetManager.dispose()
    }

}