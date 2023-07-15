package com.forvovim.smartconverter.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.forvovim.smartconverter.game.utils.region

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
        GAME(TextureAtlasData("atlas/game.atlas")),
    }
    enum class EnumTexture(override val data: TextureData): ITexture {
        GRADIENTALLIKA(TextureData("textures/gradientallika.png")),
    }



    enum class GameRegion(override val region: TextureRegion): IRegion {
        KLAVEATURE(           EnumAtlas.GAME.data.atlas.findRegion("klaveature")           ),
        KONE_PRESE(           EnumAtlas.GAME.data.atlas.findRegion("kone_prese")           ),
        KONVERTE_DEFE(        EnumAtlas.GAME.data.atlas.findRegion("konverte_defe")        ),
        KOSHECHKA(            EnumAtlas.GAME.data.atlas.findRegion("koshechka")            ),
        KULINAR(              EnumAtlas.GAME.data.atlas.findRegion("kulinar")              ),
        KURS(                 EnumAtlas.GAME.data.atlas.findRegion("kurs")                 ),
        SHTUCHKE(             EnumAtlas.GAME.data.atlas.findRegion("shtuchke")             ),
        SPISOCHEK_NA_ZEMELKE( EnumAtlas.GAME.data.atlas.findRegion("spisochek_na_zemelke") ),
        VIBOR_VALUTE(         EnumAtlas.GAME.data.atlas.findRegion("vibor_valute")         ),

        GRADIENTALLIKA(EnumTexture.GRADIENTALLIKA.data.texture.region),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>) : IRegionList {
        FLAGES(List(21) { EnumAtlas.GAME.data.atlas.findRegion("${it.inc()}")}),
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