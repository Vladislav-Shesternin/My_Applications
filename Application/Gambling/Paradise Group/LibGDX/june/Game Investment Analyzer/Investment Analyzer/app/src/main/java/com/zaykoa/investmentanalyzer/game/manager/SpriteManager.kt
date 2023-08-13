package com.zaykoa.investmentanalyzer.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.zaykoa.investmentanalyzer.game.utils.region

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
        GAME(            TextureAtlasData("atlas/game.atlas")          ),
        COMPANYS(        TextureAtlasData("atlas/companys.atlas")      ),
        COMPANYS_MINI(   TextureAtlasData("atlas/companys_mini.atlas") ),
    }
    enum class EnumTexture(override val data: TextureData): ITexture {
        LOGIN_BACKGROUND( TextureData("textures/login background.png")  ),
        TOPIK_NA_MOTIK(   TextureData("textures/topik_na_motik.png")    ),
        _1(   TextureData("textures/1.png")    ),
        _2(   TextureData("textures/2.png")    ),
        _3(   TextureData("textures/3.png")    ),
        _4(   TextureData("textures/4.png")    ),
    }
    enum class GameRegion(override val region: TextureRegion): IRegion {
        AVT(                EnumAtlas.GAME.data.atlas.findRegion("avt")               ),
        BEGIN(              EnumAtlas.GAME.data.atlas.findRegion("begin")             ),
        BEGINED(            EnumAtlas.GAME.data.atlas.findRegion("begined")           ),
        BEKICH_WITH_GREEN(  EnumAtlas.GAME.data.atlas.findRegion("bekich_with_green") ),
        BLUE(               EnumAtlas.GAME.data.atlas.findRegion("blue")              ),
        CHECK_CIRCLE(       EnumAtlas.GAME.data.atlas.findRegion("check_circle")      ),
        CHECK_DONE(         EnumAtlas.GAME.data.atlas.findRegion("check_done")        ),
        CIRCLE_COLOROVA(    EnumAtlas.GAME.data.atlas.findRegion("circle_colorova")   ),
        FILTER(             EnumAtlas.GAME.data.atlas.findRegion("filter")            ),
        GLOB(               EnumAtlas.GAME.data.atlas.findRegion("glob")              ),
        GRAY(               EnumAtlas.GAME.data.atlas.findRegion("gray")              ),
        INVEST_ANALIZATOR(  EnumAtlas.GAME.data.atlas.findRegion("invest_analizator") ),
        INVEST_MENU(        EnumAtlas.GAME.data.atlas.findRegion("invest_menu")       ),
        INVESTISI(          EnumAtlas.GAME.data.atlas.findRegion("investisi")         ),
        KRA(                EnumAtlas.GAME.data.atlas.findRegion("kra")               ),
        LUC(                EnumAtlas.GAME.data.atlas.findRegion("luc")               ),
        OBU(                EnumAtlas.GAME.data.atlas.findRegion("obu")               ),
        POP(                EnumAtlas.GAME.data.atlas.findRegion("pop")               ),
        PRO(                EnumAtlas.GAME.data.atlas.findRegion("pro")               ),
        PRODE(              EnumAtlas.GAME.data.atlas.findRegion("prode")             ),
        PRODED(             EnumAtlas.GAME.data.atlas.findRegion("proded")            ),
        PUT(                EnumAtlas.GAME.data.atlas.findRegion("put")               ),
        SEALE(              EnumAtlas.GAME.data.atlas.findRegion("seale")             ),
        SEALED(             EnumAtlas.GAME.data.atlas.findRegion("sealed")            ),
        SEARCH(             EnumAtlas.GAME.data.atlas.findRegion("search")            ),
        START_WORK(         EnumAtlas.GAME.data.atlas.findRegion("start_work")        ),
        STARTED_WORK(       EnumAtlas.GAME.data.atlas.findRegion("started_work")      ),
        SUBSTRACT_TOP(      EnumAtlas.GAME.data.atlas.findRegion("substract_top")     ),
        TEXTOPOLITIKA(      EnumAtlas.GAME.data.atlas.findRegion("textopolitika")     ),
        ZAPOVID(            EnumAtlas.GAME.data.atlas.findRegion("zapovid")           ),
        ZDO(                EnumAtlas.GAME.data.atlas.findRegion("zdo")               ),

        LOGIN_BACKGROUND(  EnumTexture.LOGIN_BACKGROUND.data.texture.region  ),
        TOPIK_NA_MOTIK(    EnumTexture.TOPIK_NA_MOTIK.data.texture.region    ),
        _1(    EnumTexture._1.data.texture.region    ),
        _2(    EnumTexture._2.data.texture.region    ),
        _3(    EnumTexture._3.data.texture.region    ),
        _4(    EnumTexture._4.data.texture.region    ),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>) : IRegionList {
        COMPANYS( List(20) { EnumAtlas.COMPANYS.data.atlas.findRegion("${it.inc()}")}),
        COMPANYS_MINI( List(20) { EnumAtlas.COMPANYS_MINI.data.atlas.findRegion("${it.inc()}")}),
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