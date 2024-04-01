package com.favsport.slots

import androidx.core.view.isVisible
import com.badlogic.gdx.Game
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.favsport.slots.assets.FontManager
import com.favsport.slots.assets.MusicManager
import com.favsport.slots.assets.SoundManager
import com.favsport.slots.assets.SpriteManager
import com.favsport.slots.screens.MenuScreen
import com.favsport.slots.utils.NavigationUtil
import com.favsport.slots.utils.Once
import com.favsport.slots.utils.cancelCoroutinesAll
import com.favsport.slots.utils.language.LanguageManagerUtil
import com.favsport.slots.utils.language.LanguageSprite
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