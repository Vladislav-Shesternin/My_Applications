package com.srata.financialguru.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.srata.financialguru.game.utils.region

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
        GAME(TextureAtlasData("atlas/game.atlas")),

    }
    enum class EnumTexture(override val data: TextureData): ITexture {
        FIN_GURU(TextureData("textures/fin_guru.png")),
        WHITE(   TextureData("textures/white.png")   ),

        D1(TextureData("textures/d1.png")),
        D2(TextureData("textures/d2.png")),
        D3(TextureData("textures/d3.png")),
        D4(TextureData("textures/d4.png")),
        D5(TextureData("textures/d5.png")),
        D6(TextureData("textures/d6.png")),
        D7(TextureData("textures/d7.png")),
        D8(TextureData("textures/d8.png")),
        D9(TextureData("textures/d9.png")),
    }



    enum class SplashRegion(override val region: TextureRegion): IRegion {
        FIN_GURU(EnumTexture.FIN_GURU.data.texture.region),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        AN(              EnumAtlas.GAME.data.atlas.findRegion("an")               ),
        ANALIZE(         EnumAtlas.GAME.data.atlas.findRegion("analize")          ),
        ANONIM(          EnumAtlas.GAME.data.atlas.findRegion("anonim")           ),
        BAL(             EnumAtlas.GAME.data.atlas.findRegion("bal")              ),
        BEZ_TEN(         EnumAtlas.GAME.data.atlas.findRegion("bez_ten")          ),
        DNI_NEDELA(      EnumAtlas.GAME.data.atlas.findRegion("dni_nedela")       ),
        FRI(             EnumAtlas.GAME.data.atlas.findRegion("fri")              ),
        GOOOO(           EnumAtlas.GAME.data.atlas.findRegion("goooo")            ),
        GRAY_PAN(        EnumAtlas.GAME.data.atlas.findRegion("gray_pan")         ),
        GUR(             EnumAtlas.GAME.data.atlas.findRegion("gur")              ),
        KUP_PROD(        EnumAtlas.GAME.data.atlas.findRegion("kup_prod")         ),
        KURSI_VALUTI(    EnumAtlas.GAME.data.atlas.findRegion("kursi_valuti")     ),
        MAN_WITH_FLOWER( EnumAtlas.GAME.data.atlas.findRegion("man_with_flower")  ),
        MAY_BAL(         EnumAtlas.GAME.data.atlas.findRegion("may_bal")          ),
        MENU_ANALIZE(    EnumAtlas.GAME.data.atlas.findRegion("menu_analize")     ),
        MENU_GA(         EnumAtlas.GAME.data.atlas.findRegion("menu_ga")          ),
        MENU_HOMI(       EnumAtlas.GAME.data.atlas.findRegion("menu_homi")        ),
        MON(             EnumAtlas.GAME.data.atlas.findRegion("mon")              ),
        NEDELA(          EnumAtlas.GAME.data.atlas.findRegion("nedela")           ),
        NEX(             EnumAtlas.GAME.data.atlas.findRegion("nex")              ),
        PEX(             EnumAtlas.GAME.data.atlas.findRegion("pex")              ),
        POPKA(           EnumAtlas.GAME.data.atlas.findRegion("popka")            ),
        RUD(             EnumAtlas.GAME.data.atlas.findRegion("rud")              ),
        SAT(             EnumAtlas.GAME.data.atlas.findRegion("sat")              ),
        SMOTRIK(         EnumAtlas.GAME.data.atlas.findRegion("smotrik")          ),
        SUN(             EnumAtlas.GAME.data.atlas.findRegion("sun")              ),
        TESTIKJ(         EnumAtlas.GAME.data.atlas.findRegion("testikj")          ),
        THU(             EnumAtlas.GAME.data.atlas.findRegion("thu")              ),
        TUE(             EnumAtlas.GAME.data.atlas.findRegion("tue")              ),
        US(              EnumAtlas.GAME.data.atlas.findRegion("us")               ),
        VASA(            EnumAtlas.GAME.data.atlas.findRegion("vasa")             ),
        VASI_VALUTKI(    EnumAtlas.GAME.data.atlas.findRegion("vasi_valutki")     ),
        WED(             EnumAtlas.GAME.data.atlas.findRegion("wed")              ),
        WITH_TEN(        EnumAtlas.GAME.data.atlas.findRegion("with_ten")         ),

        WHITE(EnumTexture.WHITE.data.texture.region),

       D1(EnumTexture.D1.data.texture.region),
       D2(EnumTexture.D2.data.texture.region),
       D3(EnumTexture.D3.data.texture.region),
       D4(EnumTexture.D4.data.texture.region),
       D5(EnumTexture.D5.data.texture.region),
       D6(EnumTexture.D6.data.texture.region),
       D7(EnumTexture.D7.data.texture.region),
       D8(EnumTexture.D8.data.texture.region),
       D9(EnumTexture.D9.data.texture.region),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>) : IRegionList {
        ITEMS( List(10) { EnumAtlas.GAME.data.atlas.findRegion("${it.inc()}")}),
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