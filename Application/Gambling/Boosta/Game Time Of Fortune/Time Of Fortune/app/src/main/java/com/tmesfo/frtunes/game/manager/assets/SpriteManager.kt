package com.tmesfo.frtunes.game.manager.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.tmesfo.frtunes.game.manager.assets.util.TextureAtlasData
import com.tmesfo.frtunes.game.manager.assets.util.TextureData

object SpriteManager {

    private val textureParameter = TextureLoader.TextureParameter().apply {
        minFilter = Texture.TextureFilter.Linear
        magFilter = Texture.TextureFilter.Linear
    }

    var loadableAtlasList   = mutableListOf<IAtlas>()
    var loadableTextureList = mutableListOf<ITexture>()



    fun load(assetManager: AssetManager) {
        loadableAtlasList.onEach { assetManager.load(it.data.path, TextureAtlas::class.java, ) }
        loadableTextureList.onEach { assetManager.load(it.data.path, Texture::class.java, textureParameter) }
    }

    fun init(assetManager: AssetManager) {
        loadableAtlasList.onEach { it.data.atlas = assetManager[it.data.path, TextureAtlas::class.java] }
        loadableTextureList.onEach { it.data.texture = assetManager[it.data.path, Texture::class.java] }
    }



    enum class SourceAtlas(override val data: TextureAtlasData): IAtlas {
        _1(       TextureAtlasData("sprites/atlas/1.atlas")        ),
        SLOT_ITEM(TextureAtlasData("sprites/atlas/slot_item.atlas")),
    }

    enum class SourceTexture(override val data: TextureData): ITexture {
        BACKGROUND(TextureData("sprites/background/1.png")),
    }



    enum class MenuRegion(override val region: TextureRegion): IRegion {
        BUTTON_DEFAULT_1(SourceAtlas._1.data.atlas.findRegion("button_default_1")),
        BUTTON_PRESS_1(  SourceAtlas._1.data.atlas.findRegion("button_press_1")  ),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        AUTOSPIN_DEFAULT(SourceAtlas._1.data.atlas.findRegion("autospin_default")),
        AUTOSPIN_DISABLE(SourceAtlas._1.data.atlas.findRegion("autospin_disable")),
        AUTOSPIN_PRESS(  SourceAtlas._1.data.atlas.findRegion("autospin_press"  )),
        BALANCE_PANEL(   SourceAtlas._1.data.atlas.findRegion("balance_panel"   )),
        BET_PANEL(       SourceAtlas._1.data.atlas.findRegion("bet_panel"       )),
        GLOW(            SourceAtlas._1.data.atlas.findRegion("glow"            )),
        MENU_DEFAULT(    SourceAtlas._1.data.atlas.findRegion("menu_default"    )),
        MENU_PRESS(      SourceAtlas._1.data.atlas.findRegion("menu_press"      )),
        MINUS_DEFAULT(   SourceAtlas._1.data.atlas.findRegion("minus_default"   )),
        MINUS_DISABLE(   SourceAtlas._1.data.atlas.findRegion("minus_disable"   )),
        MINUS_PRESS(     SourceAtlas._1.data.atlas.findRegion("minus_press"     )),
        PLUS_DEFAULT(    SourceAtlas._1.data.atlas.findRegion("plus_default"    )),
        PLUS_DISABLE(    SourceAtlas._1.data.atlas.findRegion("plus_disable"    )),
        PLUS_PRESS(      SourceAtlas._1.data.atlas.findRegion("plus_press"      )),
        SLOT_GROUP_MASK( SourceAtlas._1.data.atlas.findRegion("slot_group_mask" )),
        SLOT_GROUP_PANEL(SourceAtlas._1.data.atlas.findRegion("slot_group_panel")),
        SPIN_DEFAULT(    SourceAtlas._1.data.atlas.findRegion("spin_default"    )),
        SPIN_DISABLE(    SourceAtlas._1.data.atlas.findRegion("spin_disable"    )),
        SPIN_PRESS(      SourceAtlas._1.data.atlas.findRegion("spin_press"      )),

        WILD(            SourceAtlas.SLOT_ITEM.data.atlas.findRegion("wild"     )),
    }



    enum class ListRegion(override val regionList: List<TextureRegion>): IRegionList {
        SLOT_ITEM(List(9) { SourceAtlas.SLOT_ITEM.data.atlas.findRegion("${it.inc()}") })
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

}