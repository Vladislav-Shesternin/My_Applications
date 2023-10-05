package notconvert.notvalue.notvista.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import notconvert.notvalue.notvista.game.LibGDXGame
import notconvert.notvalue.notvista.game.scroll.VerticalGroup
import notconvert.notvalue.notvista.game.utils.actor.animHide
import notconvert.notvalue.notvista.game.utils.actor.setOnClickListener
import notconvert.notvalue.notvista.game.utils.advanced.AdvancedScreen
import notconvert.notvalue.notvista.game.utils.advanced.AdvancedStage
import notconvert.notvalue.notvista.game.utils.numStr

class KurskScreen(override val game: LibGDXGame): AdvancedScreen() {

    // Actor
    private val lodImg = Image(game.spriteUtil.GAME.KUBA)
    private val vertep = VerticalGroup(this, 27f)
    private val scrulio = ScrollPane(vertep)
    private val textField = TextField("0", TextField.TextFieldStyle(game.fontTTFUtil.fontInterBlack.font_66.font, Color.BLACK, null, null, null))
    private val texta = Label("0", Label.LabelStyle(game.fontTTFUtil.fontInterBlack.font_66.font, Color.BLACK))


    override fun show() {
        //stageUI.root.animHide()
//        setUIBackground(game.spriteUtil.SPLASH.BACKGROUND)
        super.show()
        //stageUI.root.animShow(0.3f)

    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addashodImg()
        addScrolio()
        addMakiavelly()

        addField()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addField() {
        addActors(textField)
        textField.apply {
            setBounds(73f, 967f, 244f, 83f)
        }

        addActors(texta)
        texta.apply {
            setBounds(73f, 736f, 206f, 83f)
        }
    }

    private fun AdvancedStage.addashodImg() {
        addActors(lodImg)
        lodImg.apply {
            setBounds(53f, -1100f, 507f, 835f)
            addAction(Actions.moveTo(53f,387f, 0.7f, Interpolation.elasticOut))
        }
    }

    private fun AdvancedStage.addScrolio() {
        addActors(scrulio)
        scrulio.setBounds(52f, 98f, 503f, 388f)
        scrulio.animHide()

        listOf(
            game.spriteUtil.GAME.a_LIST,
            game.spriteUtil.GAME.a_LIST,
            game.spriteUtil.GAME.a_LIST,
            game.spriteUtil.GAME.a_LIST,
            game.spriteUtil.GAME.a_LIST,
        ).flatten().shuffled().onEach {
            val img = Image(it).apply { setSize(503f, 56f) }
            vertep.addActor(img)
        }

        scrulio.addAction(Actions.fadeIn(1f, Interpolation.circle))

    }

    private fun AdvancedStage.addMakiavelly() {
        val a1 = Actor()
        addActor(a1)
        a1.apply {
            setBounds(55f, 1173f, 63f, 49f)
            setOnClickListener { stage.root.animHide(0.5f) { game.navigationManager.back() } }
        }

        val a2 = Actor()
        addActor(a2)
        a2.apply {
            setBounds(270f, 871f, 80f, 78f)
            setOnClickListener { texta.setText("${numStr(1, 9, (1..4).random())},${numStr(1, 9, (1..4).random())}") }
        }

    }

}