package com.jjjj.ooo.kkk.eer.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion

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
        _2(TextureAtlasData("atlas/2.atlas")),
    }

    enum class CommonRegion(override val region: TextureRegion): IRegion {
        BACKGROUND(EnumAtlas._2.data.atlas.findRegion("background")),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        NOT_DEF(   EnumAtlas._1.data.atlas.findRegion("not_def")   ),
        NOT_PRESS( EnumAtlas._1.data.atlas.findRegion("not_press") ),
        PAN_GREEN( EnumAtlas._1.data.atlas.findRegion("pan_green") ),
        PAN_RED(   EnumAtlas._1.data.atlas.findRegion("pan_red")   ),
        YES_DEF(   EnumAtlas._1.data.atlas.findRegion("yes_def")   ),
        YES_PRESS( EnumAtlas._1.data.atlas.findRegion("yes_press") ),
        PAN_DEF(   EnumAtlas._2.data.atlas.findRegion("pan_def")   ),
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