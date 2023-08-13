package com.prochenkoa.businessplanner.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.prochenkoa.businessplanner.game.utils.region

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
        empte(TextureData("textures/empte.png")),
        poco(TextureData("textures/poco.png")),
        katka_v_dotku(TextureData("textures/katka_v_dotku.png")),

        i1(TextureData("textures/itemkis/1.png")),
        i2(TextureData("textures/itemkis/2.png")),
        i3(TextureData("textures/itemkis/3.png")),
        i4(TextureData("textures/itemkis/4.png")),
        i5(TextureData("textures/itemkis/5.png")),
        i6(TextureData("textures/itemkis/6.png")),
        i7(TextureData("textures/itemkis/7.png")),
        i8(TextureData("textures/itemkis/8.png")),

        p1(TextureData("textures/pipki/1.png")),
        p2(TextureData("textures/pipki/2.png")),
        p3(TextureData("textures/pipki/3.png")),
        p4(TextureData("textures/pipki/4.png")),
        p5(TextureData("textures/pipki/5.png")),
        p6(TextureData("textures/pipki/6.png")),
        p7(TextureData("textures/pipki/7.png")),
        p8(TextureData("textures/pipki/8.png"));
    }



    enum class Sepe(override val region: TextureRegion): IRegion {
        POCO(EnumTexture.poco.data.texture.region),
    }

    enum class Kedr(override val region: TextureRegion): IRegion {
        EMPTE(EnumTexture.empte.data.texture.region),
        katka_v_dotku(EnumTexture.katka_v_dotku.data.texture.region),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        CICI(      EnumAtlas.GAME.data.atlas.findRegion("cici")     ),
        DERTO(     EnumAtlas.GAME.data.atlas.findRegion("derto")    ),
        FLT_CHK(   EnumAtlas.GAME.data.atlas.findRegion("flt_chk")  ),
        INT_CHK(   EnumAtlas.GAME.data.atlas.findRegion("int_chk")  ),
        LONG_CHK(  EnumAtlas.GAME.data.atlas.findRegion("long_chk") ),
        POCO(      EnumAtlas.GAME.data.atlas.findRegion("poco")     ),
        PRODO(     EnumAtlas.GAME.data.atlas.findRegion("prodo")    ),
        RETIG(     EnumAtlas.GAME.data.atlas.findRegion("retig")    ),
        SAR(       EnumAtlas.GAME.data.atlas.findRegion("sar")      ),
        SOLS(      EnumAtlas.GAME.data.atlas.findRegion("sols")     ),
        XIMES(     EnumAtlas.GAME.data.atlas.findRegion("ximes")    ),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>) : IRegionList {
        //ITEMS(List(6) { EnumAtlas.ITEMS.data.atlas.findRegion("${it.inc()}")}),
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