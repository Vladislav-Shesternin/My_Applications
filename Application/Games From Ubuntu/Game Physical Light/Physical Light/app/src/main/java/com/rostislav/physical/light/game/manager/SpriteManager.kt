package com.rostislav.physical.light.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas

class SpriteManager(var assetManager: AssetManager) {

    var loadableAtlasList   = mutableListOf<AtlasData>()

    fun loadAtlas() {
        loadableAtlasList.onEach { assetManager.load(it.path, TextureAtlas::class.java) }
    }

    fun initAtlas() {
        loadableAtlasList.onEach { it.atlas = assetManager[it.path, TextureAtlas::class.java] }
        loadableAtlasList.clear()
    }


    enum class EnumAtlas(val data: AtlasData) {
        ALL   (AtlasData("atlas/all.atlas")   ),
        DETAIL(AtlasData("atlas/detail.atlas")),
    }

    data class AtlasData(val path: String) {
        lateinit var atlas: TextureAtlas
    }

}