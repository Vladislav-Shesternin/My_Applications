package com.gen.bettermeditatio.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.gen.bettermeditatio.game.util.region

object SpriteManager {

    var loadableAtlasList = mutableListOf<IAtlas>()



    fun load(assetManager: AssetManager) {
        loadableAtlasList.onEach { assetManager.load(it.data.path, TextureAtlas::class.java) }
    }

    fun init(assetManager: AssetManager) {
        loadableAtlasList.onEach { it.data.atlas = assetManager[it.data.path, TextureAtlas::class.java] }
    }



    enum class EnumAtlas(override val data: TextureAtlasData): IAtlas {
        _1(TextureAtlasData("atlas/1.atlas")),
    }

    enum class CommonRegion(override val region: TextureRegion): IRegion {
        BACKGROUND(EnumAtlas._1.data.atlas.findRegion("background")),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        BALANCE( EnumAtlas._1.data.atlas.findRegion("balance") ),
        MASK(    EnumAtlas._1.data.atlas.findRegion("mask")    ),
        SLOT(    EnumAtlas._1.data.atlas.findRegion("slot")    ),
        SPIN_DEF(EnumAtlas._1.data.atlas.findRegion("spin_def")),
        SPIN_DIS(EnumAtlas._1.data.atlas.findRegion("spin_dis")),
        WILD(    EnumAtlas._1.data.atlas.findRegion("wild")    ),
        GLOW(    EnumAtlas._1.data.atlas.findRegion("glow")    ),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
        ITEMS(List(8) { EnumAtlas._1.data.atlas.findRegion("${it.inc()}") }),
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

}