package com.veldan.bigwinslots777.manager.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.veldan.bigwinslots777.manager.assets.util.TextureAtlasData
import com.veldan.bigwinslots777.manager.assets.util.TextureData
import com.veldan.bigwinslots777.utils.region

object SpriteManager {

    private val parameter = TextureLoader.TextureParameter().apply {
        minFilter = Texture.TextureFilter.Linear
        magFilter = Texture.TextureFilter.Linear
    }

    var loadableAtlasList       = mutableListOf<IAtlas>()
    var loadableTextureList     = mutableListOf<ITexture>()



    fun load(assetManager: AssetManager) {
        loadableAtlasList.onEach { assetManager.load(it.data.path, TextureAtlas::class.java) }
        loadableTextureList.onEach { assetManager.load(it.data.path, Texture::class.java) }
    }

    fun init(assetManager: AssetManager) {
        loadableAtlasList.onEach { it.data.atlas = assetManager[it.data.path, TextureAtlas::class.java] }
        loadableTextureList.onEach { it.data.texture = assetManager[it.data.path, Texture::class.java] }
    }



    enum class SourceAtlas(override val data: TextureAtlasData): IAtlas {
        _1(       TextureAtlasData("sprites/atlas/1.atlas")         ),
       SLOT_ITEM( TextureAtlasData("sprites/atlas/slot_item.atlas") ),
       SUPER_GAME(TextureAtlasData("sprites/atlas/super_game.atlas")),
      // TUTORIAL(  TextureAtlasData("sprites/atlas/tutorial.atlas")  ),
    }

    enum class SourceTexture(override val data: TextureData): ITexture {
        BACKGROUND(TextureData("sprites/background/1.png")),
    }



    enum class GameRegion(override val region: TextureRegion): IRegion {
        AUTO_SPIN_DEFAULT(SourceAtlas._1.data.atlas.findRegion("auto_spin_default")),
        AUTO_SPIN_DISABLE(SourceAtlas._1.data.atlas.findRegion("auto_spin_disable")),
        AUTO_SPIN_PRESS(  SourceAtlas._1.data.atlas.findRegion("auto_spin_press")  ),
        BALANCE_PANEL(    SourceAtlas._1.data.atlas.findRegion("balance_panel")    ),
        BET_PANEL(        SourceAtlas._1.data.atlas.findRegion("bet_panel")        ),
        MINUS_DEFAULT(    SourceAtlas._1.data.atlas.findRegion("minus_default")    ),
        MINUS_DISABLE(    SourceAtlas._1.data.atlas.findRegion("minus_disable")    ),
        MINUS_PRESS(      SourceAtlas._1.data.atlas.findRegion("minus_press")      ),
        MUSIC_DISABLE(    SourceAtlas._1.data.atlas.findRegion("music_disable")    ),
        MUSIC_ENABLE(     SourceAtlas._1.data.atlas.findRegion("music_enable")     ),
        PLUS_DEFAULT(     SourceAtlas._1.data.atlas.findRegion("plus_default")     ),
        PLUS_DISABLE(     SourceAtlas._1.data.atlas.findRegion("plus_disable")     ),
        PLUS_PRESS(       SourceAtlas._1.data.atlas.findRegion("plus_press")       ),
        SLOT_GROUP_PANEL( SourceAtlas._1.data.atlas.findRegion("slot_group_panel") ),
        SPIN_DEFAULT(     SourceAtlas._1.data.atlas.findRegion("spin_default")     ),
        SPIN_DISABLE(     SourceAtlas._1.data.atlas.findRegion("spin_disable")     ),
        SPIN_PRESS(       SourceAtlas._1.data.atlas.findRegion("spin_press")       ),
        GLOW(             SourceAtlas._1.data.atlas.findRegion("glow")             ),
        DIALOG_PANEL(     SourceAtlas._1.data.atlas.findRegion("dialog_panel")     ),

        SCATTER(         SourceAtlas.SLOT_ITEM.data.atlas.findRegion("scatter")    ),
        WILD(            SourceAtlas.SLOT_ITEM.data.atlas.findRegion("wild")       ),
    }

    enum class SuperGameRegion(override val region: TextureRegion): IRegion {
        CENTER(              SourceAtlas.SUPER_GAME.data.atlas.findRegion("center")              ),
        COEFFICIENT_PANEL(   SourceAtlas.SUPER_GAME.data.atlas.findRegion("coefficient_panel")   ),
        INDICATOR(           SourceAtlas.SUPER_GAME.data.atlas.findRegion("indicator")           ),
        ROULETTE_WILD(       SourceAtlas.SUPER_GAME.data.atlas.findRegion("roulette_wild")       ),
        ROULETTE_COEFFICIENT(SourceAtlas.SUPER_GAME.data.atlas.findRegion("roulette_coefficient")),
        ROULETTE_PANEL(      SourceAtlas.SUPER_GAME.data.atlas.findRegion("roulette_panel")      ),
        ROULETTE_SPIN(       SourceAtlas.SUPER_GAME.data.atlas.findRegion("roulette_spin")       ),
        SPIN_PANEL(          SourceAtlas.SUPER_GAME.data.atlas.findRegion("spin_panel")          ),
        WILD_PANEL(          SourceAtlas.SUPER_GAME.data.atlas.findRegion("wild_panel")          ),
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