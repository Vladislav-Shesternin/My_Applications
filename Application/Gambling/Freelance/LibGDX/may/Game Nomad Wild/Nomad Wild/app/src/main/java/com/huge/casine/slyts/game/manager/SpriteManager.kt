package com.huge.casine.slyts.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.huge.casine.slyts.game.utils.region

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
    }

    enum class EnumTexture(override val data: TextureData): ITexture {
        BRJAJAK( TextureData("textures/background.png") ),
    }


    enum class SplashRegion(override val region: TextureRegion): IRegion {
        BURAKSA(EnumTexture.BRJAJAK.data.texture.region),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        EXIT_DF( EnumAtlas.GAME.data.atlas.findRegion("exit_df") ),
        EXIT_PS( EnumAtlas.GAME.data.atlas.findRegion("exit_ps") ),
        GAM_DF(  EnumAtlas.GAME.data.atlas.findRegion("gam_df")  ),
        GAM_PS(  EnumAtlas.GAME.data.atlas.findRegion("gam_ps")  ),
        JOLTIY(  EnumAtlas.GAME.data.atlas.findRegion("joltiy")  ),
        LOS_WIN( EnumAtlas.GAME.data.atlas.findRegion("los_win") ),
        ST(      EnumAtlas.GAME.data.atlas.findRegion("st")      ),
        TEN_KA(  EnumAtlas.GAME.data.atlas.findRegion("ten_ka")  ),
        TS(      EnumAtlas.GAME.data.atlas.findRegion("ts")      ),
        C1(      EnumAtlas.GAME.data.atlas.findRegion("c1")      ),
        C2(      EnumAtlas.GAME.data.atlas.findRegion("c2")      ),
        RED(     EnumAtlas.GAME.data.atlas.findRegion("red")     ),
        GREEN(   EnumAtlas.GAME.data.atlas.findRegion("green")   ),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
        ITEMS( List(6) { EnumAtlas.GAME.data.atlas.findRegion("${it.inc()}") }),
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