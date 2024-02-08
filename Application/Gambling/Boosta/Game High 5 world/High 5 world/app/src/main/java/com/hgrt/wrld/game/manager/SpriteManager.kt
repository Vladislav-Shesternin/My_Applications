package com.hgrt.wrld.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.hgrt.wrld.game.util.region

object SpriteManager {

    private val parameter = TextureLoader.TextureParameter().apply {
        minFilter = Texture.TextureFilter.Linear
        magFilter = Texture.TextureFilter.Linear
    }

    var loadableAtlasList   = mutableListOf<IAtlas>()
    var loadableTextureList = mutableListOf<ITexture>()



    fun load(assetManager: AssetManager) {
        loadableAtlasList.onEach { assetManager.load(it.data.path, TextureAtlas::class.java) }
        loadableTextureList.onEach { assetManager.load(it.data.path, Texture::class.java, parameter) }
    }

    fun init(assetManager: AssetManager) {
        loadableAtlasList.onEach { it.data.atlas = assetManager[it.data.path, TextureAtlas::class.java] }
        loadableTextureList.onEach { it.data.texture = assetManager[it.data.path, Texture::class.java] }
    }



    enum class EnumAtlas(override val data: TextureAtlasData): IAtlas {
        _1(TextureAtlasData("sprites/atlas/1.atlas")),
    }

    enum class EnumTexture(override val data: TextureData): ITexture {
        BACKGROUND(TextureData("sprites/background/background.png")),
        OFF(TextureData("sprites/background/off.png")),
        ON(TextureData("sprites/background/on.png")),
        PLAY_EXIT(TextureData("sprites/background/play_exit.png")),
    }



    enum class CommonRegion(override val region: TextureRegion): IRegion {
        BACKGROUND(EnumTexture.BACKGROUND.data.texture.region)
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        BALANCE(        EnumAtlas._1.data.atlas.findRegion("balance")        ),
        BET(            EnumAtlas._1.data.atlas.findRegion("bet")            ),
        CB_CHECK(       EnumAtlas._1.data.atlas.findRegion("cb_check")       ),
        CB_DEFF(        EnumAtlas._1.data.atlas.findRegion("cb_deff")        ),
        GLOW(           EnumAtlas._1.data.atlas.findRegion("glow")           ),
        SLOT_GROUP(     EnumAtlas._1.data.atlas.findRegion("slot_group")     ),
        SLOT_GROUP_MASK(EnumAtlas._1.data.atlas.findRegion("slot_group_mask")),
        SPIN_DEF(       EnumAtlas._1.data.atlas.findRegion("spin_def")       ),
        SPIN_DIS(       EnumAtlas._1.data.atlas.findRegion("spin_dis")       ),
        SPIN_PRESS(     EnumAtlas._1.data.atlas.findRegion("spin_press")     ),
        WILD(           EnumAtlas._1.data.atlas.findRegion("wild")           ),

        OFF(EnumTexture.OFF.data.texture.region),
        ON(EnumTexture.ON.data.texture.region),
        PLAY_EXIT(EnumTexture.PLAY_EXIT.data.texture.region),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
        ITEMS(List(9) { EnumAtlas._1.data.atlas.findRegion("${it.inc()}") })
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