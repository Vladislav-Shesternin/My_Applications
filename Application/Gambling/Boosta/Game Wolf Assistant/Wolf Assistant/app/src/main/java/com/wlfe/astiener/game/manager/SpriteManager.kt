package com.wlfe.astiener.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.wlfe.astiener.game.util.region

object SpriteManager {

    private val parameter = TextureLoader.TextureParameter().apply {
        minFilter = Texture.TextureFilter.Linear
        magFilter = Texture.TextureFilter.Linear
    }

    var loadableAtlasList = mutableListOf<IAtlas>()
    var loadableTextureList = mutableListOf<ITexture>()



    fun load(assetManager: AssetManager) {
        loadableAtlasList.onEach { assetManager.load(it.data.path, TextureAtlas::class.java) }
        loadableTextureList.onEach { assetManager.load(it.data.path, Texture::class.java, parameter) }

    }

    fun init(assetManager: AssetManager) {
        loadableAtlasList.onEach { it.data.atlas = assetManager[it.data.path, TextureAtlas::class.java] }
        loadableTextureList.onEach { it.data.texture = assetManager[it.data.path, Texture::class.java] }

    }



    enum class EnumAtlas(override val data: TextureAtlasData): IAtlas {
        _1(TextureAtlasData("atlas/1.atlas")),
        _2(TextureAtlasData("atlas/2.atlas")),
        _3(TextureAtlasData("atlas/3.atlas")),
    }

    enum class CommonRegion(override val region: TextureRegion): IRegion {
        BACKGROUND(EnumAtlas._1.data.atlas.findRegion("background")),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        BAL(        EnumAtlas._1.data.atlas.findRegion("bal")       ),
        BET(        EnumAtlas._1.data.atlas.findRegion("bet")       ),
        GLOW(       EnumAtlas._1.data.atlas.findRegion("glow")      ),
        MINUS_DEF(  EnumAtlas._1.data.atlas.findRegion("minus_def") ),
        MINUS_DIS(  EnumAtlas._1.data.atlas.findRegion("minus_dis") ),
        PLUS_DEF(   EnumAtlas._1.data.atlas.findRegion("plus_def")  ),
        PLUS_DIS(   EnumAtlas._1.data.atlas.findRegion("plus_dis")  ),
        SPIN_DEF(   EnumAtlas._1.data.atlas.findRegion("spin_def")  ),
        SPIN_PRESS( EnumAtlas._1.data.atlas.findRegion("spin_press")),
        SPIN_DIS(   EnumAtlas._1.data.atlas.findRegion("spin_dis")  ),

        WILD(EnumAtlas._3.data.atlas.findRegion("wild")),

        BACKGROUND(EnumTexture.BACKGROUND.data.texture.region),
        BTNS(EnumTexture.BTNS.data.texture.region),
        off(EnumTexture.off.data.texture.region),
        on(EnumTexture.on.data.texture.region),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
        GREATS(List(4) { EnumAtlas._2.data.atlas.findRegion("${it.inc()}") }),

        ITEMS(List(8) { EnumAtlas._3.data.atlas.findRegion("${it.inc()}") }),
    }

    enum class EnumTexture(override val data: TextureData): ITexture {
        BACKGROUND(TextureData("background.png")),
        BTNS(TextureData("btns.png")),
        off(TextureData("off.png")),
        on(TextureData("on.png")),
    }



    interface IAtlas {
        val data: TextureAtlasData
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

    interface ITexture {
        val data: TextureData
    }

}