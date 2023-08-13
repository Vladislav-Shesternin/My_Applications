package com.mariam.cleverfinancier.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.mariam.cleverfinancier.game.utils.region

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
        GAME(    TextureAtlasData("atlas/game.atlas")     ),
        ITEMS(   TextureAtlasData("atlas/items.atlas")    ),
        KVADRAT( TextureAtlasData("atlas/kvadrat.atlas")  ),
    }
    enum class EnumTexture(override val data: TextureData): ITexture {
        GRAPHIK(TextureData("textures/graphik.png")),
        PARDASE(TextureData("textures/pardase.png")),
    }

//    enum class SplashRegion(override val region: TextureRegion): IRegion {
//       // BARODADA(EnumTexture.BARODADA.data.texture.region),
//       // LODERKAD(EnumTexture.LODERKAD.data.texture.region),
//    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        GLAVNA(         EnumAtlas.GAME.data.atlas.findRegion("glavna")         ),
        M1(             EnumAtlas.GAME.data.atlas.findRegion("m1")             ),
        M2(             EnumAtlas.GAME.data.atlas.findRegion("m2")             ),
        M3(             EnumAtlas.GAME.data.atlas.findRegion("m3")             ),
        OLL_OPERATSIA(  EnumAtlas.GAME.data.atlas.findRegion("oll_operatsia")  ),
        STATAS(         EnumAtlas.GAME.data.atlas.findRegion("statas")         ),
        STATKAMA(       EnumAtlas.GAME.data.atlas.findRegion("statkama")       ),
        VSE_MOI_DOHODI( EnumAtlas.GAME.data.atlas.findRegion("vse_moi_dohodi") ),
        CKK(            EnumAtlas.GAME.data.atlas.findRegion("ckk")            ),
        DFF(            EnumAtlas.GAME.data.atlas.findRegion("dff")            ),
        OTKL(           EnumAtlas.GAME.data.atlas.findRegion("otkl")           ),
        UVIMKNENO(      EnumAtlas.GAME.data.atlas.findRegion("uvimkneno")      ),
        BLACK(          EnumAtlas.GAME.data.atlas.findRegion("black")          ),
        BLUCK(          EnumAtlas.GAME.data.atlas.findRegion("bluck")          ),
        PROGRESSOCHKO(  EnumAtlas.GAME.data.atlas.findRegion("progressochko")  ),

        GRAPHIK(EnumTexture.GRAPHIK.data.texture.region),
        PARDASE(EnumTexture.PARDASE.data.texture.region),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>) : IRegionList {
        S(List(8) { EnumAtlas.ITEMS.data.atlas.findRegion("s${it.inc()}") }),
        PAN(List(3) { EnumAtlas.ITEMS.data.atlas.findRegion("pan-${it.inc()}") }),
        STK(List(3) { EnumAtlas.ITEMS.data.atlas.findRegion("stk-${it.inc()}") }),
        GAF(List(10) { EnumAtlas.KVADRAT.data.atlas.findRegion("gaf-${it.inc()}") }),
        STSTSTST(List(3) { EnumAtlas.ITEMS.data.atlas.findRegion("stststst-${it.inc()}") }),
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