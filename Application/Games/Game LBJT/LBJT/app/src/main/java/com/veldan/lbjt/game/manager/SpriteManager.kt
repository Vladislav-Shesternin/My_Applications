package com.veldan.lbjt.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas

class SpriteManager(var assetManager: AssetManager) {

    var loadableAtlasList   = mutableListOf<AtlasData>()
    var loadableTextureList = mutableListOf<TextureData>()

    fun loadAtlas() {
        loadableAtlasList.onEach { assetManager.load(it.path, TextureAtlas::class.java) }
    }

    fun loadTexture() {
        loadableTextureList.onEach {
            assetManager.load(it.path, Texture::class.java, TextureLoader.TextureParameter().apply {
                minFilter = Texture.TextureFilter.Linear
                magFilter = Texture.TextureFilter.Linear
                genMipMaps = true
            })
        }
    }

    fun initAtlas() {
        loadableAtlasList.onEach { it.atlas = assetManager[it.path, TextureAtlas::class.java] }
        loadableAtlasList.clear()
    }

    fun initTexture() {
        loadableTextureList.onEach { it.texture = assetManager[it.path, Texture::class.java] }
        loadableTextureList.clear()
    }

    fun initAtlasAndTexture() {
        initAtlas()
        initTexture()
    }


    enum class EnumAtlas(val data: AtlasData) {
        GAME(AtlasData("atlas/game.atlas")),
    }

    enum class EnumTexture(val data: TextureData) {
        YAN_BACKGROUND(TextureData("textures/yan_background.png")),
        YIN_BACKGROUND(TextureData("textures/yin_background.png")),
        MASK_ICON(     TextureData("textures/mask_icon.png")     ),
        VELDAN_ICON(   TextureData("textures/veldan_icon.png")   ),
    }

    enum class EnumTexture_GeneralInformation(val data: TextureData) {
        I1( TextureData("textures/general_information/i1.png") ),
        I2( TextureData("textures/general_information/i2.png") ),
        I3( TextureData("textures/general_information/i3.png") ),
        I4( TextureData("textures/general_information/i4.png") ),
        I5( TextureData("textures/general_information/i5.png") ),
        I6( TextureData("textures/general_information/i6.png") ),
        I7( TextureData("textures/general_information/i7.png") ),
        I8( TextureData("textures/general_information/i8.png") ),
        I9( TextureData("textures/general_information/i9.png") ),
        I10(TextureData("textures/general_information/i10.png")),
        I11(TextureData("textures/general_information/i11.png")),
        I12(TextureData("textures/general_information/i12.png")),
    }


    data class AtlasData(val path: String) {
        lateinit var atlas: TextureAtlas
    }

    data class TextureData(val path: String) {
        lateinit var texture: Texture
    }

}