package com.book.of.dead.deluxe.game.manager

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
        _1(TextureAtlasData("sprites/atlas/1.atlas")),
        _2(TextureAtlasData("sprites/atlas/2.atlas")),
    }



    enum class CommonRegion(override val region: TextureRegion): IRegion {
        BACKGROUND_1(EnumAtlas._2.data.atlas.findRegion("background_1")),
        BACKGROUND_2(EnumAtlas._2.data.atlas.findRegion("background_2")),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        INFO(            EnumAtlas._1.data.atlas.findRegion("info")           ),
        INFO_DEF(        EnumAtlas._1.data.atlas.findRegion("info_def")       ),
        INFO_PRESS(      EnumAtlas._1.data.atlas.findRegion("info_press")     ),
        INFO_TEXT_1(     EnumAtlas._1.data.atlas.findRegion("info_text-1")    ),
        INFO_TEXT_2(     EnumAtlas._1.data.atlas.findRegion("info_text-2")    ),
        MINI_BONUS_WILD( EnumAtlas._1.data.atlas.findRegion("mini_bonus_wild")),
        MINUS_DEF(       EnumAtlas._1.data.atlas.findRegion("minus_def")      ),
        MINUS_DIS(       EnumAtlas._1.data.atlas.findRegion("minus_dis")      ),
        MINUS_PRESS(     EnumAtlas._1.data.atlas.findRegion("minus_press")    ),
        PLUS_DEF(        EnumAtlas._1.data.atlas.findRegion("plus_def")       ),
        PLUS_DIS(        EnumAtlas._1.data.atlas.findRegion("plus_dis")       ),
        PLUS_PRESS(      EnumAtlas._1.data.atlas.findRegion("plus_press")     ),
        SPIN_DEF(        EnumAtlas._1.data.atlas.findRegion("spin_def")       ),
        SPIN_DIS(        EnumAtlas._1.data.atlas.findRegion("spin_dis")       ),
        SPIN_PRESS(      EnumAtlas._1.data.atlas.findRegion("spin_press")     ),
        WILD(            EnumAtlas._1.data.atlas.findRegion("wild")           ),
        BALANCE(         EnumAtlas._1.data.atlas.findRegion("balance")        ),
        BET(             EnumAtlas._1.data.atlas.findRegion("bet")            ),
        BONUS_WILD(      EnumAtlas._1.data.atlas.findRegion("bonus_wild")     ),
        GLOW(            EnumAtlas._1.data.atlas.findRegion("glow")           ),

    }

    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
        ITEMS(      List(8) { EnumAtlas._1.data.atlas.findRegion("${it.inc()}") }),
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