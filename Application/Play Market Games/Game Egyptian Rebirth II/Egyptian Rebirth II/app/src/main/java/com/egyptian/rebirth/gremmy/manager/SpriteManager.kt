package com.egyptian.rebirth.gremmy.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion

object SpriteManager {

    var loadableAtlasList   = mutableListOf<IAtlas>()



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
        BACKGROUND(EnumAtlas._1.data.atlas.findRegion("background"))
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        MINUS_DEF(  EnumAtlas._1.data.atlas.findRegion("minus_def")  ),
        MINUS_DIS(  EnumAtlas._1.data.atlas.findRegion("minus_dis")  ),
        PLUS_DEF(   EnumAtlas._1.data.atlas.findRegion("plus_def")   ),
        PLUS_DIS(   EnumAtlas._1.data.atlas.findRegion("plus_dis")   ),
        SLOT_GROUP( EnumAtlas._1.data.atlas.findRegion("slot")       ),
        SPIN_DEF(   EnumAtlas._1.data.atlas.findRegion("spin_def")   ),
        SPIN_PRESS( EnumAtlas._1.data.atlas.findRegion("spin_press") ),
        SPIN_DIS(   EnumAtlas._1.data.atlas.findRegion("spin_dis")   ),
        WILD(       EnumAtlas._1.data.atlas.findRegion("wild")       ),
        GLOW(       EnumAtlas._1.data.atlas.findRegion("glow")       ),
        BALANCE(    EnumAtlas._1.data.atlas.findRegion("balance")    ),
        BET(        EnumAtlas._1.data.atlas.findRegion("bet")        ),

    }

    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
        ITEMS(List(8) { EnumAtlas._1.data.atlas.findRegion("${it.inc()}") })
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