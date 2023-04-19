package com.veldan.sportslots

import androidx.core.view.isVisible
import com.badlogic.gdx.Game
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.veldan.sportslots.assets.FontManager
import com.veldan.sportslots.assets.MusicManager
import com.veldan.sportslots.assets.SoundManager
import com.veldan.sportslots.assets.SpriteManager
import com.veldan.sportslots.screens.MenuScreen
import com.veldan.sportslots.utils.NavigationUtil
import com.veldan.sportslots.utils.Once
import com.veldan.sportslots.utils.cancelCoroutinesAll
import com.veldan.sportslots.utils.language.LanguageManagerUtil
import com.veldan.sportslots.utils.language.LanguageSprite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

lateinit var game: LibGDXGame private set
lateinit var assetManager: AssetManager
lateinit var languageSprite: LanguageSprite

class LibGDXGame : Game() {

    private val onceLoadAssets = Once()

    private val coroutineLottie = CoroutineScope(Dispatchers.Main)

    private val color = Color(2f / 255, 0f, 84f / 255, 1f)



    override fun create() {
        game = this
        assetManager = AssetManager()
        loadAssets()
    }

    override fun render() {
        ScreenUtils.clear(color)

        if (assetManager.update()) {
            onceLoadAssets.once {
                initAssets()
                languageSprite = LanguageManagerUtil.languageSprite
                cancelLottieLoader()
                NavigationUtil.navigate(MenuScreen())
            }
            super.render()
        }
    }

    override fun dispose() {
        super.dispose()
        cancelCoroutinesAll(coroutineLottie)
        assetManager.dispose()
    }



    private fun cancelLottieLoader(){
        coroutineLottie.launch {
            binding.lottie.apply {
                isVisible = false
                cancelAnimation()
            }
        }
    }



    private fun loadAssets() {
        SpriteManager.loadAll(assetManager)
        FontManager.loadAll(assetManager)
        MusicManager.loadAll(assetManager)
        SoundManager.loadAll(assetManager)
    }

    private fun initAssets() {
        SpriteManager.init(assetManager)
        FontManager.initAll(assetManager)
        MusicManager.init(assetManager)
        SoundManager.init(assetManager)
    }

}