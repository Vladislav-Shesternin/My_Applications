package com.csmttt.medus.play.game.manager

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

    enum class SplashRegion(override val region: TextureRegion): IRegion {
        LOGO_LEDY(EnumAtlas._1.data.atlas.findRegion("logo_ledy"))
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        INFO_DEF(    EnumAtlas._2.data.atlas.findRegion("info_def")    ),
        INFO_PANEL(  EnumAtlas._2.data.atlas.findRegion("info_panel")  ),
        INFO_PRESS(  EnumAtlas._2.data.atlas.findRegion("info_press")  ),
        LEFT_DEF(    EnumAtlas._2.data.atlas.findRegion("left_def")    ),
        LEFT_PRESS(  EnumAtlas._2.data.atlas.findRegion("left_press")  ),
        MECH_L(      EnumAtlas._2.data.atlas.findRegion("mech_l")      ),
        MECH_R(      EnumAtlas._2.data.atlas.findRegion("mech_r")      ),
        MEDUZA_BIG(  EnumAtlas._2.data.atlas.findRegion("meduza_big")  ),
        MEDUZA_MINI( EnumAtlas._2.data.atlas.findRegion("meduza_mini") ),
        RIGHT_DEF(   EnumAtlas._2.data.atlas.findRegion("right_def")   ),
        RIGHT_PRESS( EnumAtlas._2.data.atlas.findRegion("right_press") ),
        SIKIRA(      EnumAtlas._2.data.atlas.findRegion("sikira")      ),
        TARELKA(     EnumAtlas._2.data.atlas.findRegion("tarelka")     ),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
        ELEMENTS(List(8) { EnumAtlas._2.data.atlas.findRegion("${it.inc()}") }),
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