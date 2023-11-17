package italodisco.fernando.lucherano.iopartew.sandes

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
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

class Lodogor(override val game: opOPa): AdvancedScreen() {

    private val generatorSB = FreeTypeFontGenerator(Gdx.files.internal(FontPath.SB))
    private val generatorRG = FreeTypeFontGenerator(Gdx.files.internal(FontPath.RG))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()

    private val font50 = generatorSB.generateFont(parameter.setCharacters(CharType.NUMBERS.chars+".D").setSize(50))
    private val font23 = generatorRG.generateFont(parameter.setCharacters(CharType.ALL).setSize(23))


    private val pon = listOf(
        "Понедельник",
        "Вторник",
        "Среда",
        "Четверг",
        "Пятница",
    )

    private val poriRoYear = listOf(
        "Декабря",
        "Сентября",
        "Октября",
        "Августа",
        "Января",
        "Ноября",
        "Июля",
        "Февраля",
    )

    private val D_dengi_Lbl = Label("0", Label.LabelStyle(font50, Color.WHITE))
    private val D_dengi_Lbl2 = Label("0", Label.LabelStyle(font50, Color.WHITE))
    private val ponedilok_Lbl = Label(pon.random()+", "+poriRoYear.random()+" 2023", Label.LabelStyle(font23, Color.WHITE))
    private val cadar = Image(game.spriteUtil.cadar)

    private val barbe = Actor()

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.Lodogor.region)
        super.show()
        stageUI.root.animShow(KoloVo)
    }

    override fun paLetRa.addActorsOnStageUI() {
        addActors(barbe)
        barbe.apply {
            setBounds(0f, 1159f,98f, 80f)
            setOnClickListener {
                stageUI.root.animHide(italodisco.fernando.lucherano.iopartew.sandes.KoloVo) { game.YTARAT.back() }
            }
        }

        val i = Image(game.spriteUtil.dobavka)
        addActor(i)
        i.apply {
            setBounds(47f, 782f, 487f, 123f)
            setOnClickListener {
                addAction(Actions.fadeOut(0.3f))
                D_dengi_Lbl2. addAction(Actions.fadeIn(0.3f))
            }
        }

        addActor(D_dengi_Lbl)
        D_dengi_Lbl.setBounds(48f, 982f, 426f, 40f)

        addActors(ponedilok_Lbl, cadar, D_dengi_Lbl2)
        ponedilok_Lbl.setBounds(47f, 1043f, 374f, 31f)
        cadar.setBounds(400f, 1043f, 31f, 31f)
        D_dengi_Lbl2.setBounds(48f, 784f, 426f, 40f)

        D_dengi_Lbl2.apply {
            touchable = Touchable.disabled
            addAction(Actions.alpha(0f))
            setText("D "+ numStr(10,10000, 1) +"."+ numStr(0,9,3))
        }

        val s = Actor()
        addActor(s)
        s.apply {
            setBounds(0f, 0f, 590f, 410f)
            setOnClickListener {
                D_dengi_Lbl.setText("D "+ numStr(10,10000, 1) +"."+ numStr(0,9,3))
            }
        }
    }

    override fun dispose() {
        generatorSB.dispose()
        generatorRG.dispose()
        font50.dispose()
        font23.dispose()
        super.dispose()
    }

}