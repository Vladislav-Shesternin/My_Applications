package italodisco.fernando.lucherano.iopartew

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import italodisco.fernando.lucherano.JopaStarTue
import italodisco.fernando.lucherano.NaMaNa
import italodisco.fernando.lucherano.SpriteManagerUUU
import italodisco.fernando.lucherano.TutaSparta
import italodisco.fernando.lucherano.pistorNaD.ladsro.HeromantiaStrun
import italodisco.fernando.lucherano.iopartew.sandes.pistro.kasa
import italodisco.fernando.lucherano.iopartew.pppp098.YUAUAUAUAUUURR4
import italodisco.fernando.lucherano.iopartew.sandes.pistro.log

class opOPa(val activity: JopaStarTue) : kasa() {

    lateinit var IADASDIUSADH     : AssetManager      private set





                     lateinit var YTARAT: NaMaNa private set
    lateinit var spriteManager    : SpriteManagerUUU private set

    val spriteUtil    by lazy { TutaSparta.Gera() }

    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        YTARAT = NaMaNa(this)
        IADASDIUSADH      = AssetManager()
        spriteManager     = SpriteManagerUUU(IADASDIUSADH)

        YTARAT.navigate(HeromantiaStrun::class.java.name)
    }

    override fun render() {
        ScreenUtils.clear(Color.BLACK)
        super.render()
    }

    override fun dispose() {
        try {
            log("dispose LibGDXGame")
            YUAUAUAUAUUURR4(IADASDIUSADH, )




                     disposableSet.YUAUAUAUAUUURR4()
            super.dispose()
        } catch (e: Exception) { log("exception: ${e.message}") }
    }

}