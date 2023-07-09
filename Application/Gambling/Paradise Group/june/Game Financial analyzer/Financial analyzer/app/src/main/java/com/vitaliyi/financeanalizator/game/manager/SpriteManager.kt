package com.vitaliyi.financeanalizator.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.vitaliyi.financeanalizator.game.utils.region

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
        LOADER(TextureAtlasData("atlas/loader.atlas")),
        AB(    TextureAtlasData("atlas/ab.atlas")    ),
    }
    enum class EnumTexture(override val data: TextureData): ITexture {
        PARDON(TextureData("textures/pardon.png")),
        STS(TextureData("textures/sts.png")),
    }


    enum class SplashRegion(override val region: TextureRegion): IRegion {
       // BARODADA(EnumTexture.BARODADA.data.texture.region),
       // LODERKAD(EnumTexture.LODERKAD.data.texture.region),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        BACKGROUNDING_CIRCLE( EnumAtlas.GAME.data.atlas.findRegion("backgrounding_circle") ),
        BUKOVKI(              EnumAtlas.GAME.data.atlas.findRegion("bukovki")              ),
        CHEEKED(              EnumAtlas.GAME.data.atlas.findRegion("cheeked")              ),
        CONTINUE(             EnumAtlas.GAME.data.atlas.findRegion("continue")             ),
        DEF(                  EnumAtlas.GAME.data.atlas.findRegion("def")                  ),
        DEFFAULT(             EnumAtlas.GAME.data.atlas.findRegion("deffault")             ),
        GRADIK(               EnumAtlas.GAME.data.atlas.findRegion("gradik")               ),
        KARTA(                EnumAtlas.GAME.data.atlas.findRegion("karta")                ),
        MENKUHA(              EnumAtlas.GAME.data.atlas.findRegion("menkuha")              ),
        MENUHA(               EnumAtlas.GAME.data.atlas.findRegion("menuha")               ),
        POLITIK_TERMS(        EnumAtlas.GAME.data.atlas.findRegion("politik_terms")        ),
        PRS(                  EnumAtlas.GAME.data.atlas.findRegion("prs")                  ),
        R(                    EnumAtlas.GAME.data.atlas.findRegion("r")                    ),
        STATA(                EnumAtlas.GAME.data.atlas.findRegion("stata")                ),
        TEXT(                 EnumAtlas.GAME.data.atlas.findRegion("text")                 ),
        TEXTUSHKA(            EnumAtlas.GAME.data.atlas.findRegion("textushka")            ),
        UVERH(                EnumAtlas.GAME.data.atlas.findRegion("uverh")                ),
        VIBOR(                EnumAtlas.GAME.data.atlas.findRegion("vibor")                ),
        WHITE(                EnumAtlas.GAME.data.atlas.findRegion("white")                ),

        PARDON(EnumTexture.PARDON.data.texture.region),
        STS(EnumTexture.STS.data.texture.region),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
       LOADER( List(101) {  EnumAtlas.LOADER.data.atlas.findRegion("c$it") }),
       A( List(4) {  EnumAtlas.AB.data.atlas.findRegion("a${it.inc()}") }),
       B( List(4) {  EnumAtlas.AB.data.atlas.findRegion("b${it.inc()}") }),
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