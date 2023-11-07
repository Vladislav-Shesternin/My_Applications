package italodisco.fernando.lucherano.game.screens

import italodisco.fernando.lucherano.game.manager.util.DomiLai
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import italodisco.fernando.lucherano.game.LibGDXGame
import italodisco.fernando.lucherano.game.utils.TIME_ANIM_SCREEN_ALPHA
import italodisco.fernando.lucherano.game.utils.actor.animHide
import italodisco.fernando.lucherano.game.utils.actor.animShow
import italodisco.fernando.lucherano.game.utils.actor.setOnClickListener
import italodisco.fernando.lucherano.game.utils.advanced.AdvancedScreen
import italodisco.fernando.lucherano.game.utils.advanced.AdvancedStage
import italodisco.fernando.lucherano.game.utils.font.CharType
import italodisco.fernando.lucherano.game.utils.font.FontPath
import italodisco.fernando.lucherano.game.utils.font.setCharacters
import italodisco.fernando.lucherano.game.utils.font.setLinear
import italodisco.fernando.lucherano.game.utils.font.setSize
import italodisco.fernando.lucherano.game.utils.numStr
import italodisco.fernando.lucherano.game.utils.region

class FraoEngel(override val game: LibGDXGame): AdvancedScreen() {

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
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(D_dengi_Lbl, pasternakSkazav, imaget)
        D_dengi_Lbl.setBounds(67f, 1081f, 464f, 35f)
        pasternakSkazav.setBounds(31f, 36f, 526f, 716f)
        imaget.setBounds(50f, 941f, 488f, 61f)

        val line = Actor()
        addActor(line)
        line.apply {
            setBounds(17f, 836f, 543f, 86f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                    game.navigationManager.navigate(Lodogor::class.java.name, this@FraoEngel::class.java.name)
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