package com.plugoya.rosgpb.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.plugoya.rosgpb.game.utils.region

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
        CARD(  TextureAtlasData("atlas/card.atlas")  ),
    }
    enum class EnumTexture(override val data: TextureData): ITexture {
        BASGAR(   TextureData("textures/basgar.png")   ),
        LINES(    TextureData("textures/lines.png")    ),
        NEWS(     TextureData("textures/news.png")     ),
        RARIVOCY( TextureData("textures/rarivocy.png") ),
    }


    enum class SplashRegion(override val region: TextureRegion): IRegion {
        BA_KG(EnumTexture.BASGAR.data.texture.region),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        BACK(      EnumAtlas.GAME.data.atlas.findRegion("back")      ),
        BLACK(     EnumAtlas.GAME.data.atlas.findRegion("black")     ),
        CBIX_DID(  EnumAtlas.GAME.data.atlas.findRegion("cbix_did")  ),
        CBIX_PRIS( EnumAtlas.GAME.data.atlas.findRegion("cbix_pris") ),
        CHIKOBOKA( EnumAtlas.GAME.data.atlas.findRegion("chikoboka") ),
        DEFLAKO(   EnumAtlas.GAME.data.atlas.findRegion("deflako")   ),
        GIRL(      EnumAtlas.GAME.data.atlas.findRegion("girl")      ),
        GREEN(     EnumAtlas.GAME.data.atlas.findRegion("green")     ),
        MAN(       EnumAtlas.GAME.data.atlas.findRegion("man")       ),
        NEWSES(    EnumAtlas.GAME.data.atlas.findRegion("newses")    ),
        RECTANGLE( EnumAtlas.GAME.data.atlas.findRegion("rectangle") ),
        UN_BACK(   EnumAtlas.GAME.data.atlas.findRegion("un_back")   ),
        WHITE(     EnumAtlas.GAME.data.atlas.findRegion("white")     ),

        LINES(    EnumTexture.LINES.data.texture.region    ),
        NEWS(     EnumTexture.NEWS.data.texture.region     ),
        RARIVOCY( EnumTexture.RARIVOCY.data.texture.region ),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
        COLORS( List(6) { EnumAtlas.CARD.data.atlas.findRegion("color $it") }),
        IDS(    List(10) { EnumAtlas.CARD.data.atlas.findRegion("id $it") }),
        WIFI(   List(8) { EnumAtlas.CARD.data.atlas.findRegion("wifi $it") }),
        SURNAME(List(8) { EnumAtlas.CARD.data.atlas.findRegion("surname $it") }),
        NAMES(  List(8) { EnumAtlas.CARD.data.atlas.findRegion("name $it") }),
        NUMES(  List(7) { EnumAtlas.CARD.data.atlas.findRegion("nume $it") }),
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