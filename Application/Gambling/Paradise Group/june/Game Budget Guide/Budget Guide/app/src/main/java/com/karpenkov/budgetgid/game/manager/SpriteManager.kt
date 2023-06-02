package com.karpenkov.budgetgid.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.karpenkov.budgetgid.game.utils.region

object SpriteManager {

    var loadableAtlasList   = mutableListOf<IAtlas>()
    var loadableTextureList = mutableListOf<ITexture>()



    fun loadAtlas(assetManager: AssetManager) {
        loadableAtlasList.onEach { assetManager.load(it.data.path, TextureAtlas::class.java) }
    }

    fun loadTexture(assetManager: AssetManager) {
        loadableTextureList.onEach {
            assetManager.load(it.data.path, Texture::class.java, TextureLoader.TextureParameter().apply {
                minFilter = Texture.TextureFilter.Linear
                magFilter = Texture.TextureFilter.Linear
                genMipMaps = true
            })
        }
    }

    fun initAtlas(assetManager: AssetManager) {
        loadableAtlasList.onEach { it.data.atlas = assetManager[it.data.path, TextureAtlas::class.java] }
    }

    fun initTexture(assetManager: AssetManager) {
        loadableTextureList.onEach { it.data.texture = assetManager[it.data.path, Texture::class.java] }
    }

    enum class EnumAtlas(override val data: TextureAtlasData): IAtlas {
        GAME(  TextureAtlasData("atlas/game.atlas")  ),
        FLAG(  TextureAtlasData("atlas/flag.atlas")  ),
    }
    enum class EnumTexture(override val data: TextureData): ITexture {
        BANAR(      TextureData("textures/banar.png")      ),
        CONVERTER(  TextureData("textures/converter.png")  ),
        PIRIVVACTY( TextureData("textures/pirivvacty.png") ),
    }


    enum class SplashRegion(override val region: TextureRegion): IRegion {
        DEFAULT_BACKGROUND(EnumAtlas.GAME.data.atlas.findRegion("1")),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        BACK(       EnumAtlas.GAME.data.atlas.findRegion("back")       ),
        BTY_CLICK(  EnumAtlas.GAME.data.atlas.findRegion("bty_click")  ),
        BTY_WHITER( EnumAtlas.GAME.data.atlas.findRegion("bty_whiter") ),
        CBD(        EnumAtlas.GAME.data.atlas.findRegion("cbd")        ),
        CBP(        EnumAtlas.GAME.data.atlas.findRegion("cbp")        ),
        LIST_PANEL( EnumAtlas.GAME.data.atlas.findRegion("list_panel") ),
        TO(         EnumAtlas.GAME.data.atlas.findRegion("to")         ),

        BANAR(      EnumTexture.BANAR.data.texture.region      ),
        CONVERTER(  EnumTexture.CONVERTER.data.texture.region  ),
        PIRIVVACTY( EnumTexture.PIRIVVACTY.data.texture.region ),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
        BACKGROUND( List(8) {  EnumAtlas.GAME.data.atlas.findRegion("${it.inc()}") }),
        FLAG(       List(12) { EnumAtlas.FLAG.data.atlas.findRegion("${it.inc()}") }),
    }



    interface IAtlas {
        val data: TextureAtlasData
    }

    interface ITexture {
        val data: TextureData
    }

    interface IRegion {
        val region: TextureRegion
    }

    interface IRegionList {
        val regionList: List<TextureRegion>
    }

    data class TextureAtlasData(
        val path: String,
    ) {
        lateinit var atlas: TextureAtlas
    }

    data class TextureData(
        val path: String,
    ) {
        lateinit var texture: Texture
    }

}