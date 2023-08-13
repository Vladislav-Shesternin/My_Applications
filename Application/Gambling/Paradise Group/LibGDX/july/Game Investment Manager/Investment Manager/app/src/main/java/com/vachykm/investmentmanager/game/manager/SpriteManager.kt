package com.vachykm.investmentmanager.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.vachykm.investmentmanager.game.utils.region

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
        GAME(   TextureAtlasData("atlas/game.atlas")    ),
        SPLASH( TextureAtlasData("atlas/splash.atlas")  ),
        ITES(   TextureAtlasData("atlas/ites.atlas")    ),

    }
    enum class EnumTexture(override val data: TextureData): ITexture {
        DEPOZ(TextureData("textures/depoz.png")),
        OTZIV(TextureData("textures/otziv.png")),

        T1(TextureData("textures/t1.png")),
        T2(TextureData("textures/t2.png")),
        T3(TextureData("textures/t3.png")),
        T4(TextureData("textures/t4.png")),
        T5(TextureData("textures/t5.png")),
    }

    enum class SplashRegion(override val region: TextureRegion): IRegion {
        BRODAGA(        EnumAtlas.SPLASH.data.atlas.findRegion("brodaga")        ),
        CENTERAR(       EnumAtlas.SPLASH.data.atlas.findRegion("centerar")       ),
        HAND_WITH_MONY( EnumAtlas.SPLASH.data.atlas.findRegion("hand_with_mony") ),
        HEADER(         EnumAtlas.SPLASH.data.atlas.findRegion("header")         ),
    }

    enum class GameRegion(override val region: TextureRegion): IRegion {
        _1H(            EnumAtlas.GAME.data.atlas.findRegion("1h")           ),
        _1M(            EnumAtlas.GAME.data.atlas.findRegion("1m")           ),
        _1W(            EnumAtlas.GAME.data.atlas.findRegion("1w")           ),
        _1Y(            EnumAtlas.GAME.data.atlas.findRegion("1h")           ),
        _6H(            EnumAtlas.GAME.data.atlas.findRegion("6h")           ),
        _24H(           EnumAtlas.GAME.data.atlas.findRegion("24h")          ),
        ASSETS_ACT(     EnumAtlas.GAME.data.atlas.findRegion("assets_act")     ),
        BASE(           EnumAtlas.GAME.data.atlas.findRegion("base")           ),
        BUTTONS(        EnumAtlas.GAME.data.atlas.findRegion("buttons")        ),
        BUTTONS_OTKL(   EnumAtlas.GAME.data.atlas.findRegion("buttons_otkl")   ),
        BUY(            EnumAtlas.GAME.data.atlas.findRegion("buy")            ),
        BUY_D(          EnumAtlas.GAME.data.atlas.findRegion("buy_d")          ),
        CHECKCLE(       EnumAtlas.GAME.data.atlas.findRegion("checkcle")       ),
        DEATELNOST(     EnumAtlas.GAME.data.atlas.findRegion("deatelnost")     ),
        FOREX(          EnumAtlas.GAME.data.atlas.findRegion("forex")          ),
        GREENKA(        EnumAtlas.GAME.data.atlas.findRegion("greenka")        ),
        HEADER_DARK(    EnumAtlas.GAME.data.atlas.findRegion("header_dark")    ),
        INDEX(          EnumAtlas.GAME.data.atlas.findRegion("index")          ),
        LUCH_AKC(       EnumAtlas.GAME.data.atlas.findRegion("luch_akc")       ),
        M_BUYOK(        EnumAtlas.GAME.data.atlas.findRegion("m_buyok")        ),
        M_DEBTS(        EnumAtlas.GAME.data.atlas.findRegion("m_debts")        ),
        M_HOME(         EnumAtlas.GAME.data.atlas.findRegion("m_home")         ),
        M_WALLET(       EnumAtlas.GAME.data.atlas.findRegion("m_wallet")       ),
        MENU_ALL(       EnumAtlas.GAME.data.atlas.findRegion("menu_all")       ),
        MONEY1(         EnumAtlas.GAME.data.atlas.findRegion("money1")         ),
        MONEY2(         EnumAtlas.GAME.data.atlas.findRegion("money2")         ),
        OBSCH_BAL(      EnumAtlas.GAME.data.atlas.findRegion("obsch_bal")      ),
        OILL(           EnumAtlas.GAME.data.atlas.findRegion("oill")           ),
        POK(            EnumAtlas.GAME.data.atlas.findRegion("pok")            ),
        RECEIPT(        EnumAtlas.GAME.data.atlas.findRegion("receipt")        ),
        RECTIKD(        EnumAtlas.GAME.data.atlas.findRegion("rectikd")        ),
        REDKA(          EnumAtlas.GAME.data.atlas.findRegion("redka")          ),
        SEARCH_FIL(     EnumAtlas.GAME.data.atlas.findRegion("search_fil")     ),
        SELL(           EnumAtlas.GAME.data.atlas.findRegion("sell")           ),
        SELL_D(         EnumAtlas.GAME.data.atlas.findRegion("sell_d")         ),
        SKR(            EnumAtlas.GAME.data.atlas.findRegion("skr")            ),
        SP500(          EnumAtlas.GAME.data.atlas.findRegion("sp500")          ),
        STOCK(          EnumAtlas.GAME.data.atlas.findRegion("stock")          ),
        TEKAS(          EnumAtlas.GAME.data.atlas.findRegion("tekas")          ),
        TIME_LIST(      EnumAtlas.GAME.data.atlas.findRegion("time_list")      ),
        TOTAL_USD(      EnumAtlas.GAME.data.atlas.findRegion("total_usd")      ),
        UVERH(          EnumAtlas.GAME.data.atlas.findRegion("uverh")          ),
        V_N_C(          EnumAtlas.GAME.data.atlas.findRegion("v_n_c")          ),

        DEPOZ(EnumTexture.DEPOZ.data.texture.region),
        OTZIV(EnumTexture.OTZIV.data.texture.region),

        T1(EnumTexture.T1.data.texture.region),
        T2(EnumTexture.T2.data.texture.region),
        T3(EnumTexture.T3.data.texture.region),
        T4(EnumTexture.T4.data.texture.region),
        T5(EnumTexture.T5.data.texture.region),
    }

    enum class ListRegion(override val regionList: List<TextureRegion>) : IRegionList {
        ITEMS( List(20) { EnumAtlas.ITES.data.atlas.findRegion("${it.inc()}")}),
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