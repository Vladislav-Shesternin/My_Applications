package com.lakalutic.statisticsmanager.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.lakalutic.statisticsmanager.game.utils.region

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
        GAME(   TextureAtlasData("atlas/game.atlas")   ),
        GAME2(  TextureAtlasData("atlas/game2.atlas")  ),
        COLORS( TextureAtlasData("atlas/colors.atlas") ),
        ITEMS(  TextureAtlasData("atlas/items.atlas")  ),
        RAZNE(  TextureAtlasData("atlas/razne.atlas")  ),

    }
    enum class EnumTexture(override val data: TextureData): ITexture {
        empte(TextureData("textures/empte.png")),
    }


    enum class Sedro(override val region: TextureRegion): IRegion {
        EMPTE(EnumTexture.empte.data.texture.region),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        AGENSTVO(         EnumAtlas.GAME.data.atlas.findRegion("agenstvo")         ),
        DOBROE_UTRECHKO(  EnumAtlas.GAME.data.atlas.findRegion("dobroe_utrechko")  ),
        KEK(              EnumAtlas.GAME.data.atlas.findRegion("kek")              ),
        LORD(             EnumAtlas.GAME.data.atlas.findRegion("lord")             ),
        MEN_HOMIK(        EnumAtlas.GAME.data.atlas.findRegion("men_homik")        ),
        MEN_PROFILIK(     EnumAtlas.GAME.data.atlas.findRegion("men_profilik")     ),
        MEN_TRIGUGOLNIK(  EnumAtlas.GAME.data.atlas.findRegion("men_trigugolnik")  ),
        MEN_VIDEO(        EnumAtlas.GAME.data.atlas.findRegion("men_video")        ),
        MENEDJER(         EnumAtlas.GAME.data.atlas.findRegion("menedjer")         ),
        MOI_ZADACHI(      EnumAtlas.GAME.data.atlas.findRegion("moi_zadachi")      ),
        PRODOLJIT(        EnumAtlas.GAME.data.atlas.findRegion("prodoljit")        ),
        SVOZVRATOM(       EnumAtlas.GAME.data.atlas.findRegion("svozvratom")       ),

        DEATELNOSTIK(     EnumAtlas.GAME2.data.atlas.findRegion("deatelnostik")     ),
        PRIVETVLADIMIRKA( EnumAtlas.GAME2.data.atlas.findRegion("privetvladimirka") ),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>) : IRegionList {
        ITEMS(List(6) { EnumAtlas.ITEMS.data.atlas.findRegion("${it.inc()}")}),
        COLORS(List(6) { EnumAtlas.COLORS.data.atlas.findRegion("${it.inc()}")}),
        BOX_DEF(List(7) { EnumAtlas.RAZNE.data.atlas.findRegion("${it.inc()}_def")}),
        BOX_CHECK(List(7) { EnumAtlas.RAZNE.data.atlas.findRegion("${it.inc()}_check")}),
        RECT(List(4) { EnumAtlas.RAZNE.data.atlas.findRegion("${it.inc()}")}),
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