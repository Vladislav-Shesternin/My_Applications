package com.bezdrodovam.cryptoinsightspro.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.bezdrodovam.cryptoinsightspro.game.utils.region

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
        GAME(TextureAtlasData("atlas/game.atlas")  ),
        SPAH(TextureAtlasData("atlas/splaha.atlas")),
        IRIS(TextureAtlasData("atlas/iriski.atlas")),
    }
    enum class EnumTexture(override val data: TextureData): ITexture {
        _A1(TextureData("textures/1.png")),
        _A2(TextureData("textures/2.png")),
        _A3(TextureData("textures/3.png")),
        _A4(TextureData("textures/4.png")),
        CURSOR(TextureData("textures/cursor.png")),
    }



    enum class StartRegion(override val region: TextureRegion): IRegion {
        BLACK_OUT(   EnumAtlas.SPAH.data.atlas.findRegion("black_out")   ),
        LOAD(        EnumAtlas.SPAH.data.atlas.findRegion("load")        ),
        LOGO(        EnumAtlas.SPAH.data.atlas.findRegion("logo")        ),
        POROGRESSKA( EnumAtlas.SPAH.data.atlas.findRegion("porogresska") ),
    }

    enum class IgraRegion(override val region: TextureRegion): IRegion {
        AD(                     EnumAtlas.GAME.data.atlas.findRegion("ad")                     ),
        AH(                     EnumAtlas.GAME.data.atlas.findRegion("ah")                     ),
        AM(                     EnumAtlas.GAME.data.atlas.findRegion("am")                     ),
        AW(                     EnumAtlas.GAME.data.atlas.findRegion("aw")                     ),
        AY(                     EnumAtlas.GAME.data.atlas.findRegion("ay")                     ),
        BACK_BUY_BITCOIN_PRICE( EnumAtlas.GAME.data.atlas.findRegion("back_buy_bitcoin_price") ),
        BEGINNERS_GUIDE(        EnumAtlas.GAME.data.atlas.findRegion("beginners_guide")        ),
        BITCOIN_STAR(           EnumAtlas.GAME.data.atlas.findRegion("bitcoin_star")           ),
        BY_CONTINUING(          EnumAtlas.GAME.data.atlas.findRegion("by_continuing")          ),
        CHECK_GALKA(            EnumAtlas.GAME.data.atlas.findRegion("check_galka")            ),
        CHECK_STAR(             EnumAtlas.GAME.data.atlas.findRegion("check_star")             ),
        CIRCLE_ARROW(           EnumAtlas.GAME.data.atlas.findRegion("circle_arrow")           ),
        COIN_PERFORMANCE(       EnumAtlas.GAME.data.atlas.findRegion("coin_performance")       ),
        CONTINUE_GRAY(          EnumAtlas.GAME.data.atlas.findRegion("continue_gray")          ),
        CONTINUE_PURPLE(        EnumAtlas.GAME.data.atlas.findRegion("continue_purple")        ),
        CUR_INV_RET_TOT(        EnumAtlas.GAME.data.atlas.findRegion("cur_inv_ret_tot")        ),
        HOW_USD_BTC(            EnumAtlas.GAME.data.atlas.findRegion("how_usd_btc")            ),
        INVESTED_COINS(         EnumAtlas.GAME.data.atlas.findRegion("invested_coins")         ),
        MARKETS(                EnumAtlas.GAME.data.atlas.findRegion("markets")                ),
        MENU_LEFT(              EnumAtlas.GAME.data.atlas.findRegion("menu_left")              ),
        MENU_RIGHT(             EnumAtlas.GAME.data.atlas.findRegion("menu_right")             ),
        OTHER_COINS_TOT_INV(    EnumAtlas.GAME.data.atlas.findRegion("other_coins_tot_inv")    ),
        PROFILE_SEARCH_QUAR(    EnumAtlas.GAME.data.atlas.findRegion("profile_search_quar")    ),
        TIMERIK(                EnumAtlas.GAME.data.atlas.findRegion("timerik")                ),
        TOT_BAL(                EnumAtlas.GAME.data.atlas.findRegion("tot_bal")                ),
        WANT_TO_INVEST(         EnumAtlas.GAME.data.atlas.findRegion("want_to_invest")         ),
        YOU_PAY_INCLUDES(       EnumAtlas.GAME.data.atlas.findRegion("you_pay_includes")       ),

        _A1(EnumTexture._A1.data.texture.region),
        _A2(EnumTexture._A2.data.texture.region),
        _A3(EnumTexture._A3.data.texture.region),
        _A4(EnumTexture._A4.data.texture.region),
        CURSOR(EnumTexture.CURSOR.data.texture.region),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>) : IRegionList {
        IRISKI( List(22) { EnumAtlas.IRIS.data.atlas.findRegion("${it.inc()}")}),
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