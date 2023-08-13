package com.uchimenkoe.financecounter.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.uchimenkoe.financecounter.game.utils.region

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
        GAME(       TextureAtlasData("atlas/game.atlas")       ),
    }
    enum class EnumTexture(override val data: TextureData): ITexture {
        BUDGER( TextureData("textures/budger.png") ),
        REBRO(  TextureData("textures/rebro.png")  ),
        ELIK(   TextureData("textures/elik.png")   ),
        MENU(   TextureData("textures/menu.png")   ),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        ASKAR(    EnumAtlas.GAME.data.atlas.findRegion("askar")    ),
        BACKA(    EnumAtlas.GAME.data.atlas.findRegion("backa")    ),
        BERDAS(   EnumAtlas.GAME.data.atlas.findRegion("berdas")   ),
        DELETE(   EnumAtlas.GAME.data.atlas.findRegion("delete")   ),
        DORIVNUE( EnumAtlas.GAME.data.atlas.findRegion("dorivnue") ),
        DSA(      EnumAtlas.GAME.data.atlas.findRegion("dsa")      ),
        FLOMENKO( EnumAtlas.GAME.data.atlas.findRegion("flomenko") ),
        PRADSIK(  EnumAtlas.GAME.data.atlas.findRegion("pradsik")  ),
        REDF(     EnumAtlas.GAME.data.atlas.findRegion("redf")     ),

        BUDGER( EnumTexture.BUDGER.data.texture.region ),
        REBRO(  EnumTexture.REBRO.data.texture.region  ),
        ELIK(   EnumTexture.ELIK.data.texture.region   ),
        MENU(   EnumTexture.MENU.data.texture.region   ),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
        //CARTOCHKI( List(9) {  EnumAtlas.CARTOCHKI.data.atlas.findRegion("${it.inc()}") }),
        //COINS( List(18) {     EnumAtlas.COINS.data.atlas.findRegion("${it.inc()}")     }),
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