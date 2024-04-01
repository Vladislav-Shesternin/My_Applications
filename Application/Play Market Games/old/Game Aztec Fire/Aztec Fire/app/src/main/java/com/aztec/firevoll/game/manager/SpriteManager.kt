package com.aztec.firevoll.game.manager

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
        _2(TextureAtlasData("atlas/2.atlas")),
    }



    enum class CommonRegion(override val region: TextureRegion): IRegion {
        BACKGROUND(EnumAtlas._1.data.atlas.findRegion("background"))
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        AUTO_DEF(     EnumAtlas._1.data.atlas.findRegion("auto_def")     ),
        AUTO_PRESS(   EnumAtlas._1.data.atlas.findRegion("auto_press")   ),
        AUTO_DIS(     EnumAtlas._1.data.atlas.findRegion("auto_dis")     ),
        BACKGROUND(   EnumAtlas._1.data.atlas.findRegion("background")   ),
        BALANCE_PANEL(EnumAtlas._1.data.atlas.findRegion("balance_panel")),
        BONUS_DEF(    EnumAtlas._1.data.atlas.findRegion("bonus_def")    ),
        BONUS_PRESS(  EnumAtlas._1.data.atlas.findRegion("bonus_press")  ),
        PANEL(        EnumAtlas._1.data.atlas.findRegion("panel")        ),
        SLOT_GROUP(   EnumAtlas._1.data.atlas.findRegion("slot_group")   ),
        SPIN_DEF(     EnumAtlas._1.data.atlas.findRegion("spin_def")     ),
        SPIN_PRESS(   EnumAtlas._1.data.atlas.findRegion("spin_press")   ),
        WILD(         EnumAtlas._1.data.atlas.findRegion("wild")         ),
        GLOW(         EnumAtlas._1.data.atlas.findRegion("glow")         ),

        BONUS_1(      EnumAtlas._2.data.atlas.findRegion("bonus-1")      ),
        BONUS_2(      EnumAtlas._2.data.atlas.findRegion("bonus-2")      ),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
        ITEMS(List(9) { EnumAtlas._1.data.atlas.findRegion("${it.inc()}") }),
        ANIMS(List(16) { EnumAtlas._2.data.atlas.findRegion("${it.inc()}") }),
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