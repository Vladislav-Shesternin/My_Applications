package com.verdevad.casinavurda.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.verdevad.casinavurda.game.utils.region

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
    }

    enum class EnumTexture(override val data: TextureData): ITexture {
        BKGRNDFK( TextureData("textures/bkgrndfk.png") ),
        GRB(      TextureData("textures/grb.png")      ),
        WDRH(     TextureData("textures/wdrh.png")     ),
    }


    enum class SplashRegion(override val region: TextureRegion): IRegion {
        BKGRNDFK( EnumTexture.BKGRNDFK.data.texture.region ),
        GRB(      EnumTexture.GRB.data.texture.region      ),
        WDRH(     EnumTexture.WDRH.data.texture.region     ),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        BABKA(        EnumAtlas.GAME.data.atlas.findRegion("babka")        ),
        JJJKSJ(       EnumAtlas.GAME.data.atlas.findRegion("jjjksj")       ),
        KISTKA(       EnumAtlas.GAME.data.atlas.findRegion("kistka")       ),
        LILI_PISI(    EnumAtlas.GAME.data.atlas.findRegion("lili_pisi")    ),
        LILYA_KAKA(   EnumAtlas.GAME.data.atlas.findRegion("lilya_kaka")   ),
        PFG(          EnumAtlas.GAME.data.atlas.findRegion("pfg")          ),
        QWE(          EnumAtlas.GAME.data.atlas.findRegion("qwe")          ),
        SERUHA(       EnumAtlas.GAME.data.atlas.findRegion("seruha")       ),
        SOSALKA_MALA( EnumAtlas.GAME.data.atlas.findRegion("sosalka_mala") ),
        WER(          EnumAtlas.GAME.data.atlas.findRegion("wer")          ),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
        ITEMS( List(6) { EnumAtlas.GAME.data.atlas.findRegion("${it.inc()}") }),
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