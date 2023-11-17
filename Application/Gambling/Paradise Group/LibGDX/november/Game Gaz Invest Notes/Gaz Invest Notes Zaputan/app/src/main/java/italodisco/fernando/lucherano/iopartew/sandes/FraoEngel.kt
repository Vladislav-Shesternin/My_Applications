package italodisco.fernando.lucherano.iopartew.sandes

import italodisco.fernando.lucherano.DomiLai
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import italodisco.fernando.lucherano.JopaStarTue
import italodisco.fernando.lucherano.R
import italodisco.fernando.lucherano.iopartew.opOPa
import italodisco.fernando.lucherano.iopartew.pppp098.actor.animHide
import italodisco.fernando.lucherano.iopartew.pppp098.actor.animShow
import italodisco.fernando.lucherano.iopartew.pppp098.actor.setOnClickListener
import italodisco.fernando.lucherano.iopartew.pppp098.font.AdvancedScreen
import italodisco.fernando.lucherano.iopartew.sandes.pistro.paLetRa
import italodisco.fernando.lucherano.iopartew.pppp098.font.CharType
import italodisco.fernando.lucherano.iopartew.pppp098.font.FontPath
import italodisco.fernando.lucherano.iopartew.pppp098.font.setCharacters
import italodisco.fernando.lucherano.iopartew.pppp098.font.setLinear
import italodisco.fernando.lucherano.iopartew.pppp098.font.setSize
import italodisco.fernando.lucherano.iopartew.pppp098.numStr
import italodisco.fernando.lucherano.iopartew.pppp098.region
import italodisco.fernando.lucherano.pistorNaD.DSM
import italodisco.fernando.lucherano.iopartew.sandes.pistro.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FraoEngel(override val game: opOPa): AdvancedScreen() {

    private val generatorSB = FreeTypeFontGenerator(Gdx.files.internal(FontPath.SB))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()

    private val font47 = generatorSB.generateFont(parameter.setCharacters(CharType.NUMBERS.chars+".D").setSize(47))

    private val D_dengi_Lbl = Label("D "+numStr(10,10000, 1)+"."+numStr(0,9,3), Label.LabelStyle(font47, Color.WHITE))

    private val pasternakSkazav = DomiLai(this)

    private val postupok = game.spriteUtil.postitutka
    private val hashod   = game.spriteUtil.hashodik
    private val imaget = Image(hashod)

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.Sarafan.region)
        stageUI.root.animShow(KoloVo)
        super.show()
    }

    object Sarancha {
         fun checkDataStore(activity: JopaStarTue) {
            CoroutineScope(Dispatchers.IO).launch {
                when (DSM.kliJ.get()) {
                    "Poka" -> {
                        DSM.loDa.get()?.let {
                            log("DataStoreManager Key = SUCCESS | link = $it")
                            JopaStarTue.webURL = it
                            JopaStarTue.poloraDo.emit(JopaStarTue.Jeko)
                        }
                    }
                    "Priv" -> {
                        log("DataStoreManager Key = GAME")
                        DSM.PariVacy.get()?.let {
                            JopaStarTue.webURL = it
                            JopaStarTue.poloraDo.emit(R.id.libGDXFragment)
                        }
                    }
                    else -> {
                        log("DataStoreManager Key = NONE")
                        DomiLai.getFlag(activity)
                    }
                }
            }
        }
    }

    override fun paLetRa.addActorsOnStageUI() {
        addActors(D_dengi_Lbl, pasternakSkazav, imaget)
        D_dengi_Lbl.setBounds(67f, 1081f, 464f, 35f)
        pasternakSkazav.setBounds(31f, 36f, 526f, 716f)
        imaget.setBounds(50f, 941f, 488f, 61f)

        val line = Actor()
        addActor(line)
        line.apply {
            setBounds(17f, 836f, 543f, 86f)
            setOnClickListener {
                stageUI.root.animHide(italodisco.fernando.lucherano.iopartew.sandes.KoloVo) {
                    game.YTARAT.navigate(Lodogor::class.java.name, this@FraoEngel::class.java.name)
                }
            }
        }

        var a = true
        imaget.apply {
            setOnClickListener {
                pasternakSkazav.uprugaPopka()
                if (a) {
                    a = false
                    drawable = TextureRegionDrawable(postupok)
                } else {
                    a = true
                    drawable = TextureRegionDrawable(hashod)
                }
            }
        }
    }

    override fun dispose() {
        super.dispose()
        generatorSB.dispose()
        font47.dispose()
    }

}