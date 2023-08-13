package com.danila.cryptotracker.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.danila.cryptotracker.game.utils.region

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
        GAME(     TextureAtlasData("atlas/game.atlas")     ),
        KADGAT(   TextureAtlasData("atlas/kadgat.atlas")   ),
        PAMOGUGON(TextureAtlasData("atlas/pamogugon.atlas")),
    }
    enum class EnumTexture(override val data: TextureData): ITexture {
        GOMEOFAG(         TextureData("textures/gomeofag.png")         ),
        KOSHELOK_SINENKI( TextureData("textures/koshelok_sinenki.png") ),
        KUPIT_SECGAS(     TextureData("textures/kupit_secgas.png")     ),
        PATRON(           TextureData("textures/patron.png")           ),
    }



    enum class GameRegion(override val region: TextureRegion): IRegion {
        CINI(                     EnumAtlas.GAME.data.atlas.findRegion("cini")                     ),
        DATA_I_TORGOVLA(          EnumAtlas.GAME.data.atlas.findRegion("data_i_torgovla")          ),
        GOLOVNA(                  EnumAtlas.GAME.data.atlas.findRegion("golovna")                  ),
        MOYO_BALLA(               EnumAtlas.GAME.data.atlas.findRegion("moyo_balla")               ),
        PORTFOLIO(                EnumAtlas.GAME.data.atlas.findRegion("portfolio")                ),
        RINOK_KALA_PODOROJAL(     EnumAtlas.GAME.data.atlas.findRegion("rinok_kala_podorojal")     ),
        UGU(                      EnumAtlas.GAME.data.atlas.findRegion("ugu")                      ),
        VIBERI_AKTIV_DLA_POKUPKI( EnumAtlas.GAME.data.atlas.findRegion("viberi_aktiv_dla_pokupki") ),
        Y_VAS_NET_TRANZA(         EnumAtlas.GAME.data.atlas.findRegion("y_vas_net_tranza")         ),
        ZVEZDA_I_BACK(            EnumAtlas.GAME.data.atlas.findRegion("zvezda_i_back")            ),

        GOMEOFAG(         EnumTexture.GOMEOFAG.data.texture.region         ),
        KOSHELOK_SINENKI( EnumTexture.KOSHELOK_SINENKI.data.texture.region ),
        KUPIT_SECGAS(     EnumTexture.KUPIT_SECGAS.data.texture.region     ),
        PATRON(           EnumTexture.PATRON.data.texture.region           ),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>) : IRegionList {
        KADGAT(    List(10) { EnumAtlas.KADGAT.data.atlas.findRegion("${it.inc()}")}   ),
        PAMOGUGON( List(23) { EnumAtlas.PAMOGUGON.data.atlas.findRegion("${it.inc()}")}),
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