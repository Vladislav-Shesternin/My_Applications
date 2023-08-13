package com.foot.ball.quiz.game.manager

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
        ANS_DEF(   EnumAtlas._1.data.atlas.findRegion("ans_def")   ),
        ANS_FALSE( EnumAtlas._1.data.atlas.findRegion("ans_false") ),
        ANS_TRUE(  EnumAtlas._1.data.atlas.findRegion("ans_true")  ),
        CHECK(     EnumAtlas._1.data.atlas.findRegion("check")     ),
        DEF(       EnumAtlas._1.data.atlas.findRegion("def")       ),
        PANEL(     EnumAtlas._1.data.atlas.findRegion("panel")     ),
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