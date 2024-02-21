package com.rcks.scaloi.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.rcks.scaloi.game.util.region

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
      ACTOR(TextureAtlasData("atlas/actor.atlas")),
      ANIM( TextureAtlasData("atlas/anim.atlas") ),
      ITEM( TextureAtlasData("atlas/item.atlas") ),
    }

    enum class EnumTexture(override val data: TextureData): ITexture {
        BACKGROUND(TextureData("textures/background.png")),
        SNOW(      TextureData("textures/snow.png")      ),

        BACK    (TextureData("textures/all/back.png")),
        MENU    (TextureData("textures/all/menu.png")),
        MUS_OFF (TextureData("textures/all/mus_off.png")),
        MUS_ON  (TextureData("textures/all/mus_on.png")),
        RULES   (TextureData("textures/all/rules.png")),
        SOUN_OFF(TextureData("textures/all/soun_off.png")),
        SOUN_ON (TextureData("textures/all/soun_on.png")),
    }

    enum class CommonRegion(override val region: TextureRegion) : IRegion {
        BACKGROUND(EnumTexture.BACKGROUND.data.texture.region),
        SNOW(      EnumTexture.SNOW.data.texture.region      ),
    }

    enum class GameRegion(override val region: TextureRegion) : IRegion {
        BULANCE(     EnumAtlas.ACTOR.data.atlas.findRegion("bulance")    ),
        KILO_BAM(    EnumAtlas.ACTOR.data.atlas.findRegion("kilo_bam")    ),
        KNOPA_BUM(   EnumAtlas.ACTOR.data.atlas.findRegion("knopa_bum")   ),
        MASKA(       EnumAtlas.ACTOR.data.atlas.findRegion("maska")       ),
        STAFKA_PIRS( EnumAtlas.ACTOR.data.atlas.findRegion("stafka_pirs") ),
        STAVKA_DEF(  EnumAtlas.ACTOR.data.atlas.findRegion("stavka_def")  ),
        SVETCHENIE(  EnumAtlas.ACTOR.data.atlas.findRegion("svetchenie")  ),
        SVITAE(      EnumAtlas.ACTOR.data.atlas.findRegion("svitae")      ),

        BACK    (EnumTexture.BACK.data.texture.region),
        MENU    (EnumTexture.MENU.data.texture.region),
        MUS_OFF (EnumTexture.MUS_OFF.data.texture.region),
        MUS_ON  (EnumTexture.MUS_ON.data.texture.region),
        RULES   (EnumTexture.RULES.data.texture.region),
        SOUN_OFF(EnumTexture.SOUN_OFF.data.texture.region),
        SOUN_ON (EnumTexture.SOUN_ON.data.texture.region),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
       ITEMS(List(15) { EnumAtlas.ITEM.data.atlas.findRegion("item (${it.inc()})") }),
       ANIMS(List(12) { EnumAtlas.ANIM.data.atlas.findRegion("${it.inc()}") }),
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