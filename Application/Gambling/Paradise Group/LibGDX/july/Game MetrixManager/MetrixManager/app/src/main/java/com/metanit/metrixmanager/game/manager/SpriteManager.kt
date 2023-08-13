package com.metanit.metrixmanager.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.metanit.metrixmanager.game.utils.region

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
        GAME(   TextureAtlasData("atlas/game.atlas")   ),
        FLOWERS(TextureAtlasData("atlas/flowers.atlas")),

    }
    enum class EnumTexture(override val data: TextureData): ITexture {
        mazda_x_sixik(TextureData("textures/mazda_x_sixik.png")),

        beliy_papirus( TextureData("textures/beliy papirus.png") ),
        privetik_vlad( TextureData("textures/privetik vlad.png") ),
        pravo_na_jizn( TextureData("textures/pravo na jizn.png") ),
        mnogonojka(    TextureData("textures/mnogonojka.png")    ),
        may(           TextureData("textures/may.png")           ),
    }



    enum class Saprano(override val region: TextureRegion): IRegion {
        MAZDA(EnumTexture.mazda_x_sixik.data.texture.region),
    }

    enum class GamePlay(override val region: TextureRegion): IRegion {
        A_ETO_U_NAS_MENU( EnumAtlas.GAME.data.atlas.findRegion("a_eto_u_nas_menu") ),
        LORD_GABRIIL(     EnumAtlas.GAME.data.atlas.findRegion("lord_gabriil")     ),
        MONAKO(           EnumAtlas.GAME.data.atlas.findRegion("monako")           ),
        SHO_SKAZAV_ALLO(  EnumAtlas.GAME.data.atlas.findRegion("sho_skazav_allo")  ),
        SUNTAFE(          EnumAtlas.GAME.data.atlas.findRegion("suntafe")          ),

        beliy_papirus(EnumTexture.beliy_papirus.data.texture.region),
        privetik_vlad(EnumTexture.privetik_vlad.data.texture.region),
        pravo_na_jizn(EnumTexture.pravo_na_jizn.data.texture.region),
        mnogonojka(EnumTexture.mnogonojka.data.texture.region),
        may(EnumTexture.may.data.texture.region),
    }

    enum class Bazuki(override val regionList: List<TextureRegion>) : IRegionList {
        GAGI(List(7) { EnumAtlas.FLOWERS.data.atlas.findRegion("$it")}),
        DEFI(List(7) { EnumAtlas.FLOWERS.data.atlas.findRegion("def${it.inc()}")}),
        CHKI(List(7) { EnumAtlas.FLOWERS.data.atlas.findRegion("chk${it.inc()}")}),
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