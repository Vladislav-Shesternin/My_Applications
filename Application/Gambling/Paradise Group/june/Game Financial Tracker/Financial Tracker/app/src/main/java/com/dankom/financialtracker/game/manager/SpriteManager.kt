package com.dankom.financialtracker.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.dankom.financialtracker.game.utils.region

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
        GAME(       TextureAtlasData("atlas/game.atlas")        ),
        LOGOS(      TextureAtlasData("atlas/logos.atlas")       ),
        STATISTICA( TextureAtlasData("atlas/statistica.atlas")  ),
    }
    enum class EnumTexture(override val data: TextureData): ITexture {
        LOGO(      TextureData("textures/logo.png")      ),
        MEGABUMKA( TextureData("textures/megabumka.png") ),
    }
    enum class GameRegion(override val region: TextureRegion): IRegion {
        CHOTIRY(        EnumAtlas.GAME.data.atlas.findRegion("chotiry")        ),
        DATIK(          EnumAtlas.GAME.data.atlas.findRegion("datik")          ),
        DEATELNOST(     EnumAtlas.GAME.data.atlas.findRegion("deatelnost")     ),
        DEFOLT(         EnumAtlas.GAME.data.atlas.findRegion("defolt")         ),
        DETALIZACIA(    EnumAtlas.GAME.data.atlas.findRegion("detalizacia")    ),
        DOMIK(          EnumAtlas.GAME.data.atlas.findRegion("domik")          ),
        INFOLT(         EnumAtlas.GAME.data.atlas.findRegion("infolt")         ),
        K1(             EnumAtlas.GAME.data.atlas.findRegion("k1")             ),
        K2(             EnumAtlas.GAME.data.atlas.findRegion("k2")             ),
        LEFT(           EnumAtlas.GAME.data.atlas.findRegion("left")           ),
        LR(             EnumAtlas.GAME.data.atlas.findRegion("lr")             ),
        NACHAT(         EnumAtlas.GAME.data.atlas.findRegion("nachat")         ),
        NASTROIKI(      EnumAtlas.GAME.data.atlas.findRegion("nastroiki")      ),
        PEOPLE(         EnumAtlas.GAME.data.atlas.findRegion("people")         ),
        PRIVET(         EnumAtlas.GAME.data.atlas.findRegion("privet")         ),
        RIGHT(          EnumAtlas.GAME.data.atlas.findRegion("right")          ),
        SEE(            EnumAtlas.GAME.data.atlas.findRegion("see")            ),
        SERI(           EnumAtlas.GAME.data.atlas.findRegion("seri")           ),
        SINI(           EnumAtlas.GAME.data.atlas.findRegion("sini")           ),
        SVERNUT(        EnumAtlas.GAME.data.atlas.findRegion("svernut")        ),
        USLOVIA(        EnumAtlas.GAME.data.atlas.findRegion("uslovia")        ),
        WHITI_CHOTIRKI( EnumAtlas.GAME.data.atlas.findRegion("whiti_chotirki") ),

        LOGO(     EnumTexture.LOGO.data.texture.region      ),
        MEGABUMKA(EnumTexture.MEGABUMKA.data.texture.region ),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>) : IRegionList {
        LOGOS(     List(28) { EnumAtlas.LOGOS.data.atlas.findRegion("${it.inc()}")      }),
        STATISTICA(List(10) { EnumAtlas.STATISTICA.data.atlas.findRegion("${it.inc()}") }),
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