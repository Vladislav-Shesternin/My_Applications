package aer.com.gamesas.mobile.slot.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import aer.com.gamesas.mobile.slot.game.utils.region

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
        BACKGROUND( TextureData("textures/background.png") ),
    }


    enum class SplashRegion(override val region: TextureRegion): IRegion {
        BACKGROUND(EnumTexture.BACKGROUND.data.texture.region),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        CIRCLE(     EnumAtlas.GAME.data.atlas.findRegion("circle")     ),
        EXIT_D(     EnumAtlas.GAME.data.atlas.findRegion("exit_d")     ),
        EXIT_P(     EnumAtlas.GAME.data.atlas.findRegion("exit_p")     ),
        GO_D(       EnumAtlas.GAME.data.atlas.findRegion("go_d")       ),
        GO_P(       EnumAtlas.GAME.data.atlas.findRegion("go_p")       ),
        MONY(       EnumAtlas.GAME.data.atlas.findRegion("mony")       ),
        PLAY_D(     EnumAtlas.GAME.data.atlas.findRegion("play_d")     ),
        PLAY_P(     EnumAtlas.GAME.data.atlas.findRegion("play_p")     ),
        RECT(       EnumAtlas.GAME.data.atlas.findRegion("rect")       ),
        POINT(      EnumAtlas.GAME.data.atlas.findRegion("point")      ),
        SHAR_GOLD(  EnumAtlas.GAME.data.atlas.findRegion("shar_gold")  ),
        SHAR_GREEN( EnumAtlas.GAME.data.atlas.findRegion("shar_green") ),
    }


//    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
//        MAN( List(21) { EnumAtlas.MAN.data.atlas.findRegion("man (${it.inc()})") }),
//        FIRE(List(10) { EnumAtlas.FIRE.data.atlas.findRegion("fire (${it.inc()})") }),
//    }



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